package JavaSeminar5.BackupMaster;

import java.io.*;
import java.util.Objects;

public class Backup {
    public static void makeBackup(String dirAbsolutePath) {
        File workingDir = new File(dirAbsolutePath);
        if (!workingDir.isDirectory()) {
            System.out.println("Path provided is not a directory.");
            return;
        }

        File backupDir = new File(dirAbsolutePath + "//backup");

        if (backupDir.mkdir()) {
            for (File file : Objects.requireNonNull(workingDir.listFiles())) {
                File newFile = new File(backupDir, file.getName());
                if (!file.isDirectory()) {
                    try (FileInputStream fis = new FileInputStream(file);
                         FileOutputStream fos = new FileOutputStream(newFile)) {
                        fos.write(fis.readAllBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Backup has been created successfully.");
        } else {
            System.out.println("Folder 'backup' exists already.");
        }
    }

    public static void main(String[] args) {
        Backup.makeBackup("C://Обучение в GeekBrains//Курс по Java//WEB Java//Лекции//Sample//src//JavaSeminar5//files");
    }
}
