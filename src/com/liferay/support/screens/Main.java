package com.liferay.support.screens;

import java.io.File;
import java.nio.file.NoSuchFileException;

public class Main {

    private static final String PROJECT_XML = "project.xml";

    public static void main(String[] args) throws NoSuchFileException {
        File file = new File(PROJECT_XML);

        ScreenletModel screenletModel = null;

        try {
            if (file.exists())
                screenletModel = ScreenletModel.createFromFile(file);
            else
                screenletModel = ScreenletModel.createInteractively();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(screenletModel.getScreenletName());
        System.out.println(screenletModel.getScreenletPackage());
        System.out.println(screenletModel.getScreenletViewFilename());

    }
}
