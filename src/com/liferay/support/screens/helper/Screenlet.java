package com.liferay.support.screens.helper;

import com.liferay.support.screens.ScreenletMetadata;

/**
 * Created by javdaniel on 13/09/16.
 */
public class Screenlet {

    private static ScreenletMetadata metadata;

    public static ScreenletMetadata getMetadata() {
        return metadata;
    }

    public static void setMetadata(ScreenletMetadata metadata) {
        Screenlet.metadata = metadata;
    }

    public static String getName() {
        return metadata.getScreenletName();
    }

    public static String getNameWithoutScreenlet() {
        return metadata.getScreenletName().replace("Screenlet", "");

    }

    public static String getPackage() {
        return metadata.getScreenletPackage();
    }

    public static String getViewXml() {
        return metadata.getScreenletViewFilename();
    }

}
