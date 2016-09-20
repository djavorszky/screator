package com.liferay.support.screens.model;

import com.liferay.support.screens.helper.ClassRegistry;
import com.liferay.support.screens.helper.TemplateHelper;

public class ViewModel extends TemplateHelper {

	public static final String TAG = "ViewModel";

	public ViewModel(String screenletName) {
		super(screenletName + "ViewModel", screenletName + "ViewModel.java");
	}

	@Override
	public void make() {
		setTemplate(TEMPLATE_INTERFACE);
		setExtendedClass("BaseViewModel");

		// TODO add functions and class variables.
		ClassRegistry.addClass(TAG, getName(), getPackage());

		super.completeMake();
	}

	@Override
	protected String getFunctions() {
		return null;
	}

}