package com.liferay.support.screens;

import com.liferay.support.screens.helper.Screenlet;
import com.liferay.support.screens.helper.View;
import com.liferay.support.screens.helper.ViewModel;

/**
 * Created by javdaniel on 13/09/16.
 */
public class SourceBuilder {

    public void createFileFrames(ScreenletMetadata metadata) {

        String screenletName = Screenlet.getNameWithoutScreenlet();

        ViewModel viewModel = new ViewModel(screenletName);
        viewModel.make();

        View view = new View(screenletName);
        view.make();

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
