package com.liferay.support.screens.helper;

import com.liferay.support.screens.DuplicateVariableNameException;
import com.liferay.support.screens.UnknownClassException;
import com.liferay.support.screens.util.FileUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by javdaniel on 13/09/16.
 */
public abstract class TemplateHelper {

	public static final int TEMPLATE_CLASS = 0;
	public static final int TEMPLATE_INTERFACE = 1;

	public static final String PACKAGE = "${PACKAGE}";
	public static final String IMPORTS = "${IMPORTS}";
	public static final String NAME = "${NAME}";
	public static final String EXTENDS = "${EXTENDS}";
	public static final String IMPLEMENTS = "${IMPLEMENTS}";
	public static final String FUNCTIONS = "${FUNCTIONS}";
	public static final String CLASSVARIABLES = "${CLASSVARIABLES}";

	protected String className;
	protected String filename;
	protected String extendedClass;
	private List<String> imports = new ArrayList<>();
	private List<String> interfaces = new ArrayList<>();
	private Map<String, String> classVariables = new HashMap<>();
	private Map<String, String> macroMap = new HashMap<>();
	private int template;

	public TemplateHelper(String className, String filename) {
		this.className = className;
		this.filename = filename;
	}

	public void addClassVariable(String clazz, String variableName)
			throws DuplicateVariableNameException {

		failIfDuplicateVariableName(variableName);

		classVariables.put(variableName, clazz);
		addImport(variableName);

	}

	private void failIfDuplicateVariableName(String variableName)
			throws DuplicateVariableNameException {

		if (classVariables.get(variableName) != null) {
			throw new DuplicateVariableNameException(
					"Class level variable name " + variableName + " already exists.");
		}
	}

	public void addImport(String className) {
		String packageName = ClassRegistry.getClassPackageByName(className);

		String importLine = packageName + "." + className;

		if (! imports.contains(importLine))
			imports.add(importLine);
	}

	public void setTemplate(int template) {
		this.template = template;
	}

	public void removeClassVariable(String variableName) {
		if (classVariables.containsKey(variableName))
			classVariables.remove(variableName);
	}

	public void setExtendedClassWithGeneric(String className, String generic) {
		clearCurrentExtendedClassIfNecessary();

		addImport(className);
		addImport(generic);

		extendedClass = className + "<" + generic + ">";
	}

	public void setExtendedClassWithMultipleGenerics(String className, String... generics) {
		if (generics.length == 1) {
			setExtendedClassWithGeneric(className, generics[0]);
		}
		else {
			clearCurrentExtendedClassIfNecessary();

			addImport(className);

			StringBuilder sb = new StringBuilder();

			sb.append(className);
			sb.append("<");
			sb.append(generics[0]);

			for (int i = 1; i < generics.length; i++) {
				sb.append(",");
				sb.append(generics[i]);
			}

			sb.append(">");

			extendedClass = sb.toString();
		}

	}

	public void setExtendedClass(String className) {
		clearCurrentExtendedClassIfNecessary();

		extendedClass = className;
		addImport(className);
	}

	public void clearCurrentExtendedClassIfNecessary() {
		if (extendedClass != null)
			removeCurrentExtendedClass();
	}

	private void removeCurrentExtendedClass() {
		try {
			removeImport(extendedClass);
		}
		catch (UnknownClassException e) {
			e.printStackTrace();
		}
	}

	public void removeImport(String className) throws UnknownClassException {
		imports.remove(ClassRegistry.getClassPackageByTag(className));
	}

	public void addInterface(String interfaceName) {
		interfaces.add(interfaceName);
		addImport(interfaceName);
	}

	public String processTemplate(String template) {
		return replaceMacrosFromMap(template, macroMap);
	}

	private String replaceMacrosFromMap(String template, Map<String, String> macroMap) {
		for (String key : macroMap.keySet()) {
			String value = macroMap.get(key);
			if (value != null) {
				template = replaceMacroWithValue(template, key, value);
			}

		}

		return template;
	}

	private String replaceMacroWithValue(String template, String key, String value) {
		return template.replace(key, value);
	}

	public String loadTemplate(int type) {
		String filename;
		if (type == TEMPLATE_CLASS)
			filename = "class.tpl";
		else
			filename = "interface.tpl";

		String result = "";
		try {
			result = FileUtil.readFileContents(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public void writeProcessedTemplateToFile(String processedTemplate, File file) throws IOException{
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;

		try {
			fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);

			bufferedWriter.write(processedTemplate);
		}
		finally {
			if (bufferedWriter != null) bufferedWriter.close();
		}

	}

	public void addMacrosToBeReplaced() {
		addMacroToBeReplaced(PACKAGE, getPackage());
		addMacroToBeReplaced(NAME, getName());
		addMacroToBeReplaced(IMPLEMENTS, getInterfaces());
		addMacroToBeReplaced(EXTENDS, getExtends());
		addMacroToBeReplaced(IMPORTS, getImports());
		addMacroToBeReplaced(FUNCTIONS, getFunctions());
		addMacroToBeReplaced(CLASSVARIABLES, getClassVariables());
	}

	public void addMacroToBeReplaced(String macro, String value) {
		macroMap.put(macro, value);
	}

	public String getPackage() {
		return Screenlet.getPackage();
	}

	public String getName() {
		return className;
	}

	public String getInterfaces() {
		if (interfaces.size() == 0) return "";

		StringBuilder sb = new StringBuilder();

		sb.append("implements ");
		sb.append(interfaces.get(0));

		for (int i = 1; i < interfaces.size(); i++) {
			sb.append(",");
			sb.append(interfaces.get(i));
		}

		return sb.toString();
	}

	public String getExtends() {
		if (extendedClass == null) return "";

		return "extends " + extendedClass;
	}

	public String getImports() {
		if (imports.size() == 0) return "";

		StringBuilder sb = new StringBuilder();

		for (String clazz : imports) {
			sb.append("import ");
			sb.append(clazz);
			sb.append(";");
			sb.append(System.getProperty("line.separator"));
		}

		return sb.toString();
	}

	protected abstract String getFunctions();

	public String getClassVariables() {
		StringBuilder sb = new StringBuilder();

		for (String varName : classVariables.keySet()) {
			String clazz = classVariables.get(varName);

			sb.append("private ");
			sb.append(clazz);
			sb.append(" ");
			sb.append(varName);
			sb.append(";");
			sb.append(System.getProperty("line.separator"));
		}

		return sb.toString();
	}

	public abstract void make();

	public void completeMake() {
		File viewFile = FileUtil.createFileOnPackagePath(filename);

		String template = loadTemplate(this.template);

		addMacrosToBeReplaced();

		String processedTemplate = processTemplate(template);

		try {
			writeProcessedTemplateToFile(processedTemplate, viewFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
