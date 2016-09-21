package com.liferay.support.screens.model;

import com.liferay.support.screens.helper.ClassRegistry;
import com.liferay.support.screens.helper.Template;

/**
 * Created by javdaniel on 20/09/16.
 */
public class Listener extends Template {

	public static final String TAG = "Listener";

	public Listener(String screenletName) {
		super(screenletName + "Listener", screenletName + "Listener.java");
	}

	@Override
	protected String getFunctions() {
		return null;
	}

	@Override
	public void make() {
		setTemplate(TEMPLATE_INTERFACE);
		ClassRegistry.addClass(TAG, getName(), getPackage());

	}
}
