package com.liferay.support.screens.model;

import com.liferay.support.screens.helper.ClassRegistry;
import com.liferay.support.screens.helper.FunctionMetadata;
import com.liferay.support.screens.helper.Template;

public class ViewModel extends Template {

	public static final String TAG = "ViewModel";

	public ViewModel(String screenletName) {
		super(screenletName + "ViewModel", screenletName + "ViewModel.java");
	}

	@Override
	public void make() {
		setTemplate(TEMPLATE_INTERFACE);
		setExtendedClass("BaseViewModel");

		ClassRegistry.addClass(TAG, getName(), getPackage());

	}

	@Override
	protected String getFunctions() {
		return null;
	}

}