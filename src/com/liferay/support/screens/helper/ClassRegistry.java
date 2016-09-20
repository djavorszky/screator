package com.liferay.support.screens.helper;

import com.liferay.support.screens.UnknownClassException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by javdaniel on 20/09/16.
 */
public class ClassRegistry {

	private static Map<String, String> classTagRegister = new HashMap<>();
	private static Map<String, ClassMetadata> classRegisterByTag = new HashMap<>();

	public static void registerClassMetadata(String TAG, ClassMetadata metadata) {
		classRegisterByTag.put(TAG, metadata);
		classTagRegister.put(metadata.getClassName(), TAG);
	}

	static {
		registerClassMetadata("BaseViewModel",
				new ClassMetadata("BaseViewModel", "com.liferay.mobile.screens.base.view"));
		registerClassMetadata("LinearLayout",
				new ClassMetadata("LinearLayout", "android.widget"));
		registerClassMetadata("Interactor",
				new ClassMetadata("Interactor", "com.liferay.mobile.screens.base.interactor"));
		registerClassMetadata("BaseRemoteInteractor",
				new ClassMetadata("BaseRemoteInteractor", "com.liferay.mobile.screens.base.interactor"));
	}

	public static String getClassPackageByTag(String TAG) {
		return getClassMetadataIfExistsByTag(TAG).getClassPackage();
	}

	public static String getClassPackageByName(String className) {
		String tag = getTagFromClassName(className);
		return getClassMetadataIfExistsByTag(tag).getClassPackage();
	}

	public static String getTagFromClassName(String className) {
		return classTagRegister.get(className);
	}

	private static ClassMetadata getClassMetadataIfExistsByTag(String TAG) {

		ClassMetadata classMetadata = null;
		try {
			classMetadata = getClassMetadata(TAG);
		} catch (UnknownClassException e) {
			e.printStackTrace();
		}

		return classMetadata;
	}

	public static ClassMetadata getClassMetadata(String TAG) throws UnknownClassException {
		ClassMetadata classMetadata = classRegisterByTag.get(TAG);

		if (classMetadata == null) {
			throw new UnknownClassException("Couldn't fetch the class for tag " + TAG);
		}

		return classMetadata;
	}

	public static String getClassNameByTag(String TAG) {
		return getClassMetadataIfExistsByTag(TAG).getClassName();
	}

	public static void addClass(String TAG, String className, String packageName) {
		registerClassMetadata(TAG, new ClassMetadata(className, packageName));
	}



}
