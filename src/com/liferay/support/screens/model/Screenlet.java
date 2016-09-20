package com.liferay.support.screens.model;

import com.liferay.support.screens.helper.ClassRegistry;
import com.liferay.support.screens.helper.TemplateHelper;

/**
 * Created by javdaniel on 20/09/16.
 */
public class Screenlet extends TemplateHelper {

	public Screenlet(String screenletName) {
		super(screenletName + "Screenlet", screenletName + "Screenlet.java");
	}

	@Override
	protected String getFunctions() {
		return null;
	}

	@Override
	public void make() {
		setTemplate(TEMPLATE_CLASS);
		setExtendedClassWithMultipleGenerics(
				"BaseScreenlet",
				ClassRegistry.getClassNameByTag(ViewModel.TAG),
				ClassRegistry.getClassNameByTag(Interactor.TAG));

		addInterface(ClassRegistry.getClassNameByTag(Listener.TAG));

		super.completeMake();
	}
}
