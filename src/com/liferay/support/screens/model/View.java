package com.liferay.support.screens.model;

import com.liferay.support.screens.helper.ClassRegistry;
import com.liferay.support.screens.helper.Template;


/**
 * Created by javdaniel on 13/09/16.
 */
public class View extends Template {

	public static final String TAG = "View";

	public View(String screenletName) {
		super(screenletName + "View", screenletName + "View.java");
	}

	@Override
	public void make() {

		setTemplate(TEMPLATE_CLASS);

		setExtendedClass("LinearLayout");
		String viewModelInterfaceName = ClassRegistry.getClassNameByTag(ViewModel.TAG);
		addInterface(viewModelInterfaceName);
		// TODO add functions and class variables.

		ClassRegistry.addClass(TAG, getName(), getPackage());

	}

	@Override
	protected String getFunctions() {
		return null;
	}
}
