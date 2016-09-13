package com.liferay.support.screens;

import com.liferay.support.screens.helper.View;
import com.liferay.support.screens.helper.ViewModel;

import java.io.*;
import java.util.Map;

/**
 * Created by javdaniel on 13/09/16.
 */
public class SourceBuilder {

    private String screenletName;
    private String screenletPackage;


    public void createFileFrames(ScreenletMetadata metadata) {
        screenletName = metadata.getScreenletName();
        screenletPackage = metadata.getScreenletPackage();

        ViewModel viewModel = new ViewModel(this);
        viewModel.createViewModel();

        View view = new View(this);
        view.createView();

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



    }
}
