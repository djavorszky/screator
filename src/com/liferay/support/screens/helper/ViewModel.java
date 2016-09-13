package com.liferay.support.screens.helper;

import com.liferay.support.screens.SourceBuilder;
import com.liferay.support.screens.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ViewModel extends TemplateHelper {

    public ViewModel(String screenletName) {
        super(screenletName + "ViewModel", screenletName + "ViewModel.java");

    }

    public void createViewModel() {
        File viewModelFile = FileUtil.createFileOnPackagePath(filename);

        String template = loadTemplate(TEMPLATE_INTERFACE);

        addMacroToBeReplaced(PACKAGE, Screenlet.getPackage());
        addMacroToBeReplaced(NAME, className);
        addMacroToBeReplaced(IMPLEMENTS, "");
        addMacroToBeReplaced(EXTENDS, "extends BaseViewModel");
        addMacroToBeReplaced(IMPORTS, getViewModelImports());

        String processedTemplate = processTemplate(template);

        try {
            writeProcessedTemplateToFile(processedTemplate, viewModelFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String getViewModelImports() {
        // todo add additional imports
        return "import com.liferay.mobile.screens.base.view.BaseViewModel;";
    }
}