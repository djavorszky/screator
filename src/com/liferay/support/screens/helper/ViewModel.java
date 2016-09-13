package com.liferay.support.screens.helper;

import com.liferay.support.screens.SourceBuilder;
import com.liferay.support.screens.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ViewModel {

    private SourceBuilder sourceBuilder;

    private String interfaceName;
    private String filename;

    private File viewModelFile;

    public ViewModel(SourceBuilder sourceBuilder) {
        this.sourceBuilder = sourceBuilder;
        this.interfaceName = Screenlet.getNameWithoutScreenlet() + "ViewModel";
        this.filename = interfaceName + ".java";
    }

    public void createViewModel() {
        viewModelFile = FileUtil.createFileOnPackagePath(filename);

        String processedTemplate = getProcessedFileContents();

        try {
            sourceBuilder.writeProcessedTemplateToFile(processedTemplate, viewModelFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getProcessedFileContents() {
        String template = sourceBuilder.loadTemplate(SourceBuilder.TEMPLATE_INTERFACE);

        Map<String, String> macroMap = new HashMap<String, String>();
        macroMap.put(SourceBuilder.Macro.PACKAGE, Screenlet.getPackage());
        macroMap.put(SourceBuilder.Macro.NAME, interfaceName);
        macroMap.put(SourceBuilder.Macro.IMPLEMENTS, "");
        macroMap.put(SourceBuilder.Macro.EXTENDS, "extends BaseViewModel");
        macroMap.put(SourceBuilder.Macro.IMPORTS, getViewModelImports());

        // todo replace functions
        // todo replace classvariables

        return sourceBuilder.replaceMacrosFromMap(template, macroMap);
    }

    private String getViewModelImports() {
        // todo add additional imports
        return "import com.liferay.mobile.screens.base.view.BaseViewModel;";
    }
}