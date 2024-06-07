package src.main.java;

import src.main.java.user.General;

import java.io.*;

public class ReportManager {

    public static void saveGeneralState(General general, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
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

    public static void generalReport(General general, General general2) {
        StringBuilder report = new StringBuilder();

        report.append("Report for Generals: \n");
        report.append("General 1: \n");
        report.append(general.toString()).append("\n");
        report.append("General 2: \n");
        report.append(general2.toString()).append("\n");

        System.out.println(report);
    }

}
