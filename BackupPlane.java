/**
 * @author Группа 1425ИСО, Акчурин Ким, Уварков Сергей
 */

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

/// настройки автобэкапа
public class BackupPlane {
    public static boolean BACKUP_ON = false;
    public static int backupFrequency = 3600;

    public static void backupPlane() {
        readConfig();
        if (BACKUP_ON) {
            Timer timer = new Timer();
            TimerTask task = new BackupTimer();
            timer.schedule(task, backupFrequency * 1000, backupFrequency * 1000);
        }
    }


    public static void readConfig() {
        try (BufferedReader buffReAD = new BufferedReader(new FileReader("config.txt"))) {

            String[] parsString;
            String stringConfig;

            /// чтение построчно
            while ((stringConfig = buffReAD.readLine()) != null) {
                parsString = stringConfig.split(" ", 2);
                if (parsString[0].equals("backup_on") && (!parsString[1].equals("0")))
                    BACKUP_ON = true;
                if (parsString[0].equals("backup_on") && (parsString[1].equals("0")))
                    BACKUP_ON = false;
                if (parsString[0].equals("backup_frequency"))
                    backupFrequency = Integer.parseInt(parsString[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
            MessagePane.messagePane("Файл config.txt не найден");
        }
    }


    public static void writeConfig() {
        try (FileWriter writer = new FileWriter("config.txt", false)) {
            String config;
            if (BACKUP_ON) {
                config = "backup_on 1\r\n";
            } else config = "backup_on 0\r\n";

            config += "backup_frequency " + backupFrequency;
            writer.write(config);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}