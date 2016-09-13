package com.liferay.support.screens;

import com.liferay.support.screens.helper.ViewModel;

import java.io.*;
import java.util.Map;

/**
 * Created by javdaniel on 13/09/16.
 */
public class SourceBuilder {

    public static final int TEMPLATE_CLASS = 0;
    public static final int TEMPLATE_INTERFACE = 1;

    private String screenletName;
    private String screenletPackage;

    public String loadTemplate(int type) {
        String filename;
        if (type == TEMPLATE_CLASS)
            filename = "class.tpl";
        else
            filename = "interface.tpl";

        String result = "";
        try {
            result = readFileContents(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private String readFileContents(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("templates" + File.separator + filename));

        StringBuilder builder = new StringBuilder();

        String line = "";
        while ((line = reader.readLine()) != null) {
            builder.append(line);
            builder.append(System.lineSeparator());
        }
        reader.close();

        return builder.toString();
    }

    public void createFileFrames(ScreenletMetadata metadata) {
        screenletName = metadata.getScreenletName();
        screenletPackage = metadata.getScreenletPackage();

        ViewModel viewModel = new ViewModel(this);
        viewModel.createViewModel();



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

    public String replaceMacrosFromMap(String template, Map<String, String> macroMap) {
        for (String key : macroMap.keySet()) {
            String value = macroMap.get(key);
            template = replaceMacroWithValue(template, key, value);
        }

        return template;
    }

    public void writeProcessedTemplateToFile(String processedTemplate, File file) throws IOException{
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try {
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(processedTemplate);
        }
        finally {
            if (bufferedWriter != null)
                bufferedWriter.close();
        }

    }

    private String replaceMacroWithValue(String template, String key, String value) {
        return template.replace(key, value);
    }


    public class Macro {
        public static final String PACKAGE = "${PACKAGE}";
        public static final String IMPORTS = "${IMPORTS}";
        public static final String NAME = "${NAME}";
        public static final String EXTENDS = "${EXTENDS}";
        public static final String IMPLEMENTS = "${IMPLEMENTS}";
        public static final String FUNCTIONS = "${FUNCTIONS}";
        public static final String CLASSVARIABLES = "${CLASSVARIABLES}";
    }
}
