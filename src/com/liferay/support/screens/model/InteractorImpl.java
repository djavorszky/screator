package com.liferay.support.screens.model;

import com.liferay.support.screens.helper.ClassRegistry;
import com.liferay.support.screens.helper.TemplateHelper;

/**
 * Created by javdaniel on 20/09/16.
 */
public class InteractorImpl extends TemplateHelper {

	public InteractorImpl(String screenletName) {
		super(screenletName + "InteractorImpl", screenletName + "InteractorImpl.java");
	}

	@Override
	protected String getFunctions() {
		return null;
	}

	@Override
	public void make() {
		setTemplate(TEMPLATE_CLASS);

		setExtendedClassWithGeneric("BaseRemoteInteractor", ClassRegistry.getClassNameByTag(Listener.TAG));
		addInterface(ClassRegistry.getClassNameByTag(Interactor.TAG));

		super.completeMake();
	}
}
