package com.liferay.support.screens;

import com.liferay.support.screens.helper.ScreenletModel;
import com.liferay.support.screens.helper.Template;
import com.liferay.support.screens.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javdaniel on 13/09/16.
 */
public class SourceBuilder {

	public void createFileFrames(ScreenletMetadata metadata) {

		List<Template> templates = new ArrayList<>();

		String screenletName = ScreenletModel.getNameWithoutScreenlet();

		templates.add(new ViewModel(screenletName));
		templates.add(new View(screenletName));
		templates.add(new Listener(screenletName));
		templates.add(new Interactor(screenletName));
		templates.add(new InteractorImpl(screenletName));
		templates.add(new Screenlet((screenletName)));

		for (Template template : templates) {
			template.make();
		}

		for (Template template : templates) {
			template.finishImports();
		}

		for (Template template : templates) {
			template.createFileFromTemplate();
		}
	}
}
