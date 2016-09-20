package com.liferay.support.screens;

import com.liferay.support.screens.helper.ScreenletModel;
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

        ScreenletModel.setMetadata(screenletMetadata);

        FileUtil.createFolderStructureFromPackage(ScreenletModel.getPackage());

        SourceBuilder sb = new SourceBuilder();
        sb.createFileFrames(screenletMetadata);

        // Only use this when testing.
        //FileUtil.deleteCreatedStructure();
    }
}
