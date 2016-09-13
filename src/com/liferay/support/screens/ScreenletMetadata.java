package com.liferay.support.screens;

import com.liferay.support.screens.util.ConsoleUtil;
import com.liferay.support.screens.util.RegexUtil;
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
public class ScreenletMetadata {

    private static final String PKG_INVALID_CHARACTERS = "[^.a-zA-Z]+";
    private static final String PKG_START_ALLOWED_CHARACTERS = "^[^a-zA-Z]";
    private static final String PKG_END_ALLOWED_CHARACTERS = "[^a-zA-Z]$";

    private static final String SCREENLET_NAME = "screenlet-name";
    private static final String SCREENLET_PACKAGE = "screenlet-package";
    private static final String SCREENLET_VIEW_XML = "screenlet-view-xml";

    private HashMap<String, String> parameters = new HashMap<>();

    public static ScreenletMetadata createFromFile(File file) {
        Document doc = null;
        ScreenletMetadata metadata = null;

        try {
            doc = XmlUtil.getNormalizedDocumentFromFile(file);
            NodeList children = XmlUtil.getChildNodes(doc);

            metadata = new ScreenletMetadata();
            for (int i = 0; i < children.getLength(); i++) {
                Node node = children.item(i);
                if (XmlUtil.isNodeAnElement(node)) {
                    metadata.addParameter(node.getNodeName(), node.getTextContent());
                }
            }

            metadata.validateParameters();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return metadata;
    }

    private void ensureNameEndsWithScreenlet() {
        String name = getScreenletName();

        if (! name.endsWith("Screenlet")) {
            setScreenletName(name + "Screenlet");
        }
    }

    public static ScreenletMetadata createInteractively() throws MissingParameterException, IOException {
        String name = ConsoleUtil.askForAnswer("Screenlet's name?");
        String pkg = ConsoleUtil.askForAnswer("Screenlet's package?");
        String viewXml = ConsoleUtil.askForAnswer("View xml's name?");

        ScreenletMetadata metadata = new ScreenletMetadata();

        metadata.setScreenletName(name);
        metadata.setScreenletPackage(pkg);
        metadata.setScreenletViewXml(viewXml);

        try {
            metadata.validateParameters();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return metadata;
    }

    private ScreenletMetadata() {

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


    public void validateParameters() throws MissingParameterException, InvalidPackageNameException, IOException {
        checkForMissingParameters();
        ensureNameEndsWithScreenlet();
        validateScreenletPackage();
        validateScreenletViewXml();
    }

    private void checkForMissingParameters() throws MissingParameterException {
        if (anyParametersMissing(SCREENLET_NAME, SCREENLET_PACKAGE, SCREENLET_VIEW_XML)) {
            throw new MissingParameterException(
                    "Missing at least one parameter from: " +
                    SCREENLET_NAME +
                    ", " +
                    SCREENLET_PACKAGE +
                    ", " +
                    SCREENLET_VIEW_XML +
                    ".");
        }
    }

    private void validateScreenletViewXml() throws IOException {
        String filename = getScreenletViewFilename();
        File file = new File(filename);

        if (!file.exists())
            throw new IOException("Specified viewfile '" + filename + "' does not exist.");
    }

    public void validateScreenletPackage() throws InvalidPackageNameException {
        String pkg = getScreenletPackage();

        if (RegexUtil.findPatternInText(ScreenletMetadata.PKG_INVALID_CHARACTERS, pkg))
            throw new InvalidPackageNameException("Package name contains invalid characters.");

        if (RegexUtil.findPatternInText(ScreenletMetadata.PKG_START_ALLOWED_CHARACTERS, pkg))
            throw new InvalidPackageNameException("Package starts with invalid characters.");

        if (RegexUtil.findPatternInText(ScreenletMetadata.PKG_END_ALLOWED_CHARACTERS, pkg))
            throw new InvalidPackageNameException("Package ends with invalid characters.");
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
