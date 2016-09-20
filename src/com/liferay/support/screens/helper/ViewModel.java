package com.liferay.support.screens.helper;

import com.liferay.support.screens.util.FileUtil;

import java.io.File;
import java.io.IOException;

public class ViewModel extends TemplateHelper {

	public static final String TAG = "ViewModel";

	public ViewModel(String screenletName) {
		super(screenletName + "ViewModel", screenletName + "ViewModel.java");
	}

	@Override
	public void make() {
		File viewModelFile = FileUtil.createFileOnPackagePath(filename);

		setExtendedClass("BaseViewModel");

		// TODO add functions and class variables.

		String template = loadTemplate(TEMPLATE_INTERFACE);

		addMacrosToBeReplaced();

		String processedTemplate = processTemplate(template);

		try {
			writeProcessedTemplateToFile(processedTemplate, viewModelFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		ClassRegistry.addClass(TAG, getName(), getPackage());
	}

	@Override
	protected String getFunctions() {
		return null;
	}

}