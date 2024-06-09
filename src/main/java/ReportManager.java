package src.main.java;

import src.main.java.units.Entity;
import src.main.java.user.General;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ReportManager {

    public static void saveGeneralState(General general, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName + ".txt"))) {
            oos.writeObject(general);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static General loadGeneralState(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (General) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void log(General general, int i, Action a) {
        log(general, general.getUnits().get(i), a);
    }
    public static void log(General general, Entity e, Action a) {
        StringBuilder report = new StringBuilder();
        report.append("[").append(e.getLevelAsRank()).append("] - ").append(a.toString()).append("\n");

        Path file = Paths.get(general.getName() + ".txt");

        if (Files.exists(file)) {
            try {
                Files.writeString(file, report.toString(), StandardOpenOption.APPEND);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            try {
                Files.writeString(file, "[" + general.getClass().getSimpleName() + "]\n\n");
                Files.writeString(file, report.toString(), StandardOpenOption.APPEND);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    public static void generalReport(General general, General general2) {
        StringBuilder report = new StringBuilder();

        report.append("Report for Generals: \n");
        report.append("General 1: \n");
        report.append(general.toString()).append("\n");
        report.append("General 2: \n");
        report.append(general2.toString()).append("\n");

        System.out.println(report);
    }

    public static enum Action {
        DIED_IN_ACTION,
        GAIN_EXPERIENCE,
        LOST_EXPERIENCE,
    }

}
