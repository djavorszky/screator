package com.liferay.support.screens;

import com.liferay.support.screens.helper.Screenlet;
import com.liferay.support.screens.util.FileUtil;

import java.io.File;
import java.nio.file.NoSuchFileException;

public class Main {

    private static final String PROJECT_XML = "project.xml";

    public static void main(String[] args) throws NoSuchFileException {
        File file = new File(PROJECT_XML);

        ScreenletMetadata screenletMetadata = null;

        try {
            if (file.exists())
                screenletMetadata = ScreenletMetadata.createFromFile(file);
            else
                screenletMetadata = ScreenletMetadata.createInteractively();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Screenlet.setMetadata(screenletMetadata);

        FileUtil.createFolderStructureFromPackage(Screenlet.getPackage());

        SourceBuilder sb = new SourceBuilder();
        sb.createFileFrames(screenletMetadata);

        // Only use this when testing.
        //FileUtil.deleteCreatedStructure();
    }
}
