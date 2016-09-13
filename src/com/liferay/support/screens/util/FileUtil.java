package com.liferay.support.screens.util;

import java.io.File;

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

    private static String getRootFolderFromPath(String folderPath) {
        return folderPath.split(File.separator)[0];
    }

    private static String replaceDotWithFileSeparators(String path) {
        return path.replace(".", File.separator);
    }


    public static String getFolderPath() {
        return folderPath;
    }

}
