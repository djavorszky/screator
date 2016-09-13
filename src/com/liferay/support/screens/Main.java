package com.liferay.support.screens;

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

        FileUtil.createFolderStructureFromPackage(screenletMetadata.getScreenletPackage());
        // Only use this when testing.
        //FileUtil.deleteCreatedStructure();

    }
}

/*
* Stuff that I can completely create based on view.xml:
* 1 - public interface <screenletname>ViewModel extends BaseViewModel
* 2 - public class <screenletname>View extends LinearLayout implements <screenletname>ViewModel
*
* Stuff for which I can only create the shell:
* 1 - public interface <screenletname>Interactor extends Interactor<<screenletname>Listener>
* 2 - public class <screenletname>InteractorImpl extends BaseRemoteInteractor<<screenletname>Listener> implements <screenletname>Interactor
* 3 - public interface <screenletname>Listener
* 4 - <screenletname>Screenlet extends BaseScreenlet<<screenletname>ViewModel, <screenletname>Interactor> implements <screenletname>Listener
*
* Stuff I should check for changes and then update stuff accordingly:
* 1 & 2 & view - todo in the future
*
*/
