package com.liferay.support.screens.helper;

/**
 * Created by javdaniel on 20/09/16.
 */
public class ClassMetadata {
	private String className;
	private String classPackage;

	public ClassMetadata(String className, String classPackage) {
		this.className = className;
		this.classPackage = classPackage;
	}

	public String getClassName() {
		return className;
	}

	public String getClassPackage() {
		return classPackage;
	}
}
