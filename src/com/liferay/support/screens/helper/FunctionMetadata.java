package com.liferay.support.screens.helper;

import java.util.*;

/**
 * Created by javdaniel on 21/09/16.
 */
public class FunctionMetadata {

	private List<String> imports = new ArrayList<String>();
	private String accessModifier;
	private String returnType = "";
	private String functionName = "";
	private Map<String, String> signature = new HashMap<>();
	private String functionBody = "";

	private boolean isStatic = false;
	private boolean isInterfaceMethod = false;

	public List<String> getImports() {
		return imports;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		addAccessModifier(sb);

		if (isStatic) {
			sb.append("static ");
		}

		addReturnType(sb);
		addFunctionNameAndSignature(sb);

		if (isInterfaceMethod) {
			sb.append(";");
		}
		else {
			sb.append(" {");
			sb.append(System.lineSeparator());

			sb.append(functionBody);
			sb.append(System.lineSeparator());

			sb.append("}");
		}

		return sb.toString();
	}

	private void addFunctionNameAndSignature(StringBuilder sb) {
		sb.append(functionName);
		sb.append("(");

		List<String> keys = new ArrayList<>(signature.keySet());

		addFirstArgument(sb, keys);
		addRemainingArguments(sb, keys);

		sb.append(")");
	}

	private void addRemainingArguments(StringBuilder sb, List<String> keys) {
		if (keys.size() != 1) {
			for (int i = 1; i < keys.size(); i++) {
				String name = keys.get(i);
				String type = signature.get(name);
				sb.append(", ");
				sb.append(type);
				sb.append(" ");
				sb.append(name);
			}
		}
	}

	private void addFirstArgument(StringBuilder sb, List<String> keys) {
		if (keys.size() != 0) {
			String firstArgumentName = keys.get(0);
			String firstArgumentType = signature.get(firstArgumentName);

			sb.append(firstArgumentType);
			sb.append(" ");
			sb.append(firstArgumentName);
		}
	}

	private void addReturnType(StringBuilder sb) {
		sb.append(returnType);
		sb.append(" ");
	}

	private void addAccessModifier(StringBuilder sb) {
		if (accessModifier != null && ! accessModifier.equals("")) {
			sb.append(accessModifier);
			sb.append(" ");
		}
	}


	public FunctionMetadata access(String type) {
		if (type != null && (!type.equals("public") && !type.equals("private") && !type.equals("protected"))) {
			System.out.println("Invalid access modifier, can only be 'public', 'private', 'protected' or 'null'. Setting it to 'public'");
			type = "public";
		}
		accessModifier = type;

		return this;
	}

	public FunctionMetadata setStatic(boolean isStatic) {
		this.isStatic = isStatic;

		return this;
	}

	public FunctionMetadata returns(String className) {
		if (className == null) {
			System.out.println("Return type is null, setting it to 'void' instead");
			className = "void";
		}
		this.returnType = className;

		String returnPackage = ClassRegistry.getClassPackageByName(className);

		if (returnPackage != null) {
			imports.add(returnPackage + "." + className);
		}

		return this;
	}

	public FunctionMetadata name(String functionName) {
		this.functionName = functionName;

		return this;
	}

	public FunctionMetadata addParameter(String type, String name) {
		String typePackage = ClassRegistry.getClassPackageByName(type);

		if (typePackage != null) {
			imports.add(typePackage + "." + name);
		}

		signature.put(name, type);

		return this;
	}

	public FunctionMetadata addBody(String text) {

		this.functionBody += text;

		return this;
	}

	public FunctionMetadata interfaceMethod(boolean isInterface) {
		this.isInterfaceMethod = isInterface;

		return this;
	}

}
