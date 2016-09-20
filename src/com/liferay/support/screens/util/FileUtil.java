package com.liferay.support.screens.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by javdaniel on 13/09/16.
 */
public class FileUtil {

    private static String folderPath;

    public static void createFolderStructureFromPackage(String path) {

        folderPath = replaceDotWithFileSeparators(path);

        File file = new File(folderPath);
        file.mkdirs();
    }

    public static void deleteCreatedStructure() {

        String rootFolder = getRootFolderFromPath(folderPath);
        deleteFilesAndFoldersRecursively(new File(rootFolder));
    }

    private static void deleteFilesAndFoldersRecursively(File path) {

        if (path.isDirectory()) {
            for (File file : path.listFiles()) {
                deleteFilesAndFoldersRecursively(file);
            }
        }

        path.delete();
    }

    public static File createFileOnPackagePath(String filename) {
        String folderPath = FileUtil.getFolderPath();
        File file = new File(folderPath + File.separator + filename);

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    private static String getRootFolderFromPath(String folderPath) {
        return folderPath.split(File.separator)[0];
    }

    private static String replaceDotWithFileSeparators(String path) {
        return path.replace(".", File.separator);
    }


    public static String getFolderPath() {
        return folderPath;
    }

    public static String readFileContents(String filename) throws IOException {

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
}
