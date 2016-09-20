package com.liferay.support.screens.model;

import com.liferay.support.screens.helper.ClassRegistry;
import com.liferay.support.screens.helper.TemplateHelper;

/**
 * Created by javdaniel on 20/09/16.
 */
public class Interactor extends TemplateHelper {

	public static final String TAG = "Interactor";

	public Interactor(String screenletName) {
		super(screenletName + "Interactor", screenletName + "Interactor.java");
	}

	@Override
	protected String getFunctions() {
		return null;
	}

	@Override
	public void make() {
		setTemplate(TEMPLATE_INTERFACE);

		setExtendedClassWithGeneric("Interactor", ClassRegistry.getClassNameByTag(Listener.TAG));
		ClassRegistry.addClass(TAG, getName(), getPackage());

		super.completeMake();
	}
}
