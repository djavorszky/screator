package com.liferay.support.screens.helper;

import com.liferay.support.screens.util.FileUtil;

import java.io.File;
import java.io.IOException;


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
        addMacroToBeReplaced(IMPORTS, getImports());

        String processedTemplate = processTemplate(template);

        try {
            writeProcessedTemplateToFile(processedTemplate, viewModelFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getPackage() {
        return null;
    }

    @Override
    protected String getImports() {
        return "import com.liferay.mobile.screens.base.view.BaseViewModel;";
    }

    @Override
    protected String getName() {
        return null;
    }

    @Override
    protected String getExtends() {
        return null;
    }

    @Override
    protected String getImplements() {
        return null;
    }

    @Override
    protected String getFunctions() {
        return null;
    }

    @Override
    protected String getClassVariables() {
        return null;
    }
}