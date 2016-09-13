package com.liferay.support.screens;

import com.liferay.support.screens.util.ConsoleUtil;
import com.liferay.support.screens.util.XmlUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by javdaniel on 13/09/16.
 */
public class ScreenletModel {

    private static final String SCREENLET_NAME = "screenlet-name";
    private static final String SCREENLET_PACKAGE = "screenlet-package";
    private static final String SCREENLET_VIEW_XML = "screenlet-view-xml";

    private HashMap<String, String> parameters = new HashMap<>();

    public static ScreenletModel createFromFile(File file) {
        Document doc = null;
        ScreenletModel model = null;

        try {
            doc = XmlUtil.getNormalizedDocumentFromFile(file);
            NodeList children = XmlUtil.getChildNodes(doc);

            model = new ScreenletModel();
            for (int i = 0; i < children.getLength(); i++) {
                Node node = children.item(i);
                if (XmlUtil.isNodeAnElement(node)) {
                    model.addParameter(node.getNodeName(), node.getTextContent());
                }
            }

            model.validateParameters();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return model;
    }

    public static ScreenletModel createInteractively() throws MissingParameterException, IOException {
        String name = ConsoleUtil.askForAnswer("Screenlet's name?");
        String pkg = ConsoleUtil.askForAnswer("Screenlet's package?");
        String viewXml = ConsoleUtil.askForAnswer("View xml's name?");

        ScreenletModel model = new ScreenletModel();

        model.setScreenletName(name);
        model.setScreenletPackage(pkg);
        model.setScreenletViewXml(viewXml);

        model.validateParameters();

        return model;
    }

    private ScreenletModel() {

    }

    public void addParameter(String key, String value) {
        parameters.put(key, value);
    }

    public String getScreenletName() {
        return parameters.get(SCREENLET_NAME);
    }

    public String getScreenletPackage() {
        return parameters.get(SCREENLET_PACKAGE);
    }

    public String getScreenletViewFilename() {
        return parameters.get(SCREENLET_VIEW_XML);
    }

    public void setScreenletName(String name) {
        parameters.put(SCREENLET_NAME, name);
    }

    public void setScreenletPackage(String pkg) {
        parameters.put(SCREENLET_PACKAGE, pkg);
    }

    public void setScreenletViewXml(String viewXml) {
        parameters.put(SCREENLET_VIEW_XML, viewXml);
    }


    public void validateParameters() throws MissingParameterException {
        if (anyParametersMissing(SCREENLET_NAME, SCREENLET_PACKAGE, SCREENLET_VIEW_XML)) {
            String message = getExceptionMessage();
            throw new MissingParameterException(message);
        }
    }

    private String getExceptionMessage() {

        return "Missing at least one parameter from: " +
                SCREENLET_NAME +
                ", " +
                SCREENLET_PACKAGE +
                ", " +
                SCREENLET_VIEW_XML +
                ".";
    }

    private boolean anyParametersMissing(String... parameters) {
        for (String param : parameters) {
            if (parameterMissing(param)) {
                return true;
            }
        }

        return false;
    }

    private boolean parameterMissing(String key) {
        return ! parameters.containsKey(key);
    }

}
