package org.example;

import java.io.*;
import java.nio.file.*;
import java.util.Arrays;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Main {
    static String PATH = "YOUR_DOWNLOADS_DIRECTORY";
    static String[] dirs = {"/txt", "/docs", "/vids", "/compressed", "/pics", "/others",};
    public static void main(String[] args) {
        mkDirs();
        run();
    }

    private static void run() {
        var path = Paths.get(PATH);
        var dir = new File(path.toString());
        var files = dir.listFiles();
        for (var i = 0; i < files.length; i++) {
            var oldPath = "";
            var newPath = "";
            if (files[i].isFile()) {
                if (files[i].getName().endsWith(".txt")) {
                    oldPath = path + "/" + files[i].getName();
                    newPath = path + "/txt/" + files[i].getName();
                    var tmp = rename(oldPath, newPath);
                    if (tmp != null) {
                        newPath = tmp;
                    }
                    try {
                        Files.move(Path.of(oldPath), Path.of(newPath), REPLACE_EXISTING);
                    } catch (IOException e) {
                    }
                } else if (files[i].getName().endsWith(".pdf") || files[i].getName().endsWith(".odt") || files[i].getName().endsWith(".dox") || files[i].getName().endsWith(".pptx")) {
                    oldPath = path + "/" + files[i].getName();
                    newPath = path + "/docs/" + files[i].getName();
                    var tmp = rename(oldPath, newPath);
                    if (tmp != null) {
                        newPath = tmp;
                    }
                    try {
                        Files.move(Path.of(oldPath), Path.of(newPath), REPLACE_EXISTING);
                    } catch (IOException e) {
                    }
                } else if (files[i].getName().endsWith(".mp4") || files[i].getName().endsWith(".webm")) {
                    oldPath = path + "/" + files[i].getName();
                    newPath = path + "/vids/" + files[i].getName();
                    var tmp = rename(oldPath, newPath);
                    if (tmp != null) {
                        newPath = tmp;
                    }
                    try {
                        Files.move(Path.of(oldPath), Path.of(newPath), REPLACE_EXISTING);
                    } catch (IOException e) {
                    }
                } else if (files[i].getName().endsWith(".zip") || files[i].getName().endsWith(".rar") || files[i].getName().endsWith(".tar.gz") || files[i].getName().endsWith(".jar") || files[i].getName().endsWith(".iso")) {
                    oldPath = path + "/" + files[i].getName();
                    newPath = path + "/compressed/" + files[i].getName();
                    var tmp = rename(oldPath, newPath);
                    if (tmp != null) {
                        newPath = tmp;
                    }
                    try {
                        Files.move(Path.of(oldPath), Path.of(newPath), REPLACE_EXISTING);
                    } catch (IOException ignored) {
                    }
                } else if (files[i].getName().endsWith(".jpeg") || files[i].getName().endsWith(".jpg") || files[i].getName().endsWith(".png")) {
                    oldPath = path + "/" + files[i].getName();
                    newPath = path + "/pics/" + files[i].getName();
                    var tmp = rename(oldPath, newPath);
                    if (tmp != null) {
                        newPath = tmp;
                    }
                    try {
                        Files.move(Path.of(oldPath), Path.of(newPath), REPLACE_EXISTING);
                    } catch (IOException ignored) {
                    }
                } else {
                    oldPath = path + "/" + files[i].getName();
                    newPath = path + "/others/" + files[i].getName();
                    var tmp = rename(oldPath, newPath);
                    if (tmp != null) {
                        newPath = tmp;
                    }
                    try {
                        Files.move(Path.of(oldPath), Path.of(newPath), REPLACE_EXISTING);
                    } catch (IOException ignored) {

                    }
                }
            }
        }

    }

    static void mkDirs() {
        for (int i = 0; i < dirs.length; i++) {
            new File(PATH + dirs[i]).mkdirs();
        }
    }

    static boolean checkIfExists(String oldFile, String newFile) {
        var f = new File(oldFile);
        var len = newFile.split("/").length;
        File dir = new File(Arrays.stream(newFile.split("/")).limit(len - 1).reduce((str1, str2) -> str1 + "/" + str2).get());
        var files = dir.listFiles();
        for (var i = 0; i < files.length; i++) {
            if (files[i].getName().equals(f.getName())) {
                return true;
            }
        }
        return false;
    }

    static String rename(String oldFile, String newFile) {
        if (checkIfExists(oldFile, newFile)) {
            var len = newFile.split("\\.").length;
            var ending = newFile.split("\\.")[newFile.split("\\.").length - 1];
            newFile = Arrays.stream(newFile.split("\\.")).limit(len - 1).reduce((str1, str2) -> str1 + "." + str2).get() + "_1." + ending;
            return newFile;
        }
        return null;
    }
}