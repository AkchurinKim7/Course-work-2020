/**
 * @author Группа 1425ИСО, Уварков Сергей, Акчурин Ким
 */

import javax.swing.*;
import java.io.File;
import java.sql.SQLException;
import java.sql.*;

public class QueryBackup {

    public static String getSecurePath() {

        String querySecureFile = "SHOW VARIABLES LIKE 'secure_file_priv'";
        Statement stmt = ConnectionDB.connectionDB(ConnectionDB.passServer);
        String verifyPath = "";
        try {
            ResultSet resultSet = stmt.executeQuery(querySecureFile);

            while (resultSet.next()) {
                verifyPath = resultSet.getString(2).replace("\\", "\\\\");
                System.out.println(verifyPath);
                break;
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return (verifyPath);
    }


    public static void queryBackup(boolean AUTO_BACKUP) {
        Statement stmt = ConnectionDB.connectionDB(ConnectionDB.passServer);
        String queryBackupApplicationForms = toStringQuery(getSecurePath(), "table_application_forms");
        String queryBackupEvents = toStringQuery(getSecurePath(), "table_events");
        String queryBackupUsers = toStringQuery(getSecurePath(), "users");

        try {
            //удаление старого бэкапа
            File fileDelApplication_forms = new File(getSecurePath() + "aacs_table_application_forms.sql");
            if (fileDelApplication_forms.delete())
                System.out.println("старый бэкап-файл главной таблицы удален");
            else System.out.println("не удалось удалить бэкап главной таблицы");

            File fileDelEvents = new File(getSecurePath() + "aacs_table_events.sql");
            if (fileDelEvents.delete())
                System.out.println("старый бэкап-файл таблицы событий удален");
            else System.out.println("не удалось удалить бэкап таблицы событий");

            File fileDelUsers = new File(getSecurePath() + "aacs_users.sql");
            if (fileDelUsers.delete())
                System.out.println("старый бэкап-файл таблицы пользователей удален");
            else System.out.println("не удалось удалить бэкап таблицы пользователей");

            stmt.execute(queryBackupEvents);
            stmt.execute(queryBackupUsers);
            stmt.execute(queryBackupApplicationForms);

            if (!AUTO_BACKUP)
                MessagePane.messagePane("Резервная копия базы данных сохранена");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            MessagePane.messagePane("Не удалось сохранить резервную копию");
        }
    }

    public static String toStringQuery(String path, String table) {
        return ("SELECT * INTO OUTFILE '" + path +
                "aacs_" + table + ".sql' FROM appaccosys." + table);
    }

    public static void autoBackup() {
        JFrame jf = new JFrame("Настройки резервного копирования");
        jf.setSize(350, 190);
        jf.setResizable(false);
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JPanel backupPanel = new JPanel();
        backupPanel.setLayout(null);
        jf.getContentPane().add(backupPanel);

        /// флаг вкл/выкл автобэкап
        JRadioButton backupRadioButton = new JRadioButton("Автоматическое резервное копирование");
        backupRadioButton.setBounds(31, 20, 400, 20);
        backupPanel.add(backupRadioButton);
        backupRadioButton.setSelected(BackupPlane.BACKUP_ON);

        /// частота автобэкапа
        JLabel frequencyLabel = new JLabel("Частота резервного копирования(сек):");
        frequencyLabel.setBounds(33, 50, 400, 20);
        backupPanel.add(frequencyLabel);
        JTextField frequencyField = new JTextField(Integer.toString(BackupPlane.backupFrequency), 15);
        frequencyField.setBounds(33, 75, 100, 20);
        backupPanel.add(frequencyField);
        CheckDB.checkNumber(frequencyField);

        ///сохранение настроек
        JButton backupSettingSave = new JButton("Сохранить");
        backupSettingSave.setBounds(125, 100, 100, 27);
        backupPanel.add(backupSettingSave);

        backupSettingSave.addActionListener(e1 -> {
            jf.setVisible(false);

            int selection = JOptionPane.showConfirmDialog(null,
                    "Сохранить изменения настроек резервного копирования?",
                    "изменения настроек резервного копирования", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (selection == JOptionPane.YES_OPTION) {
                BackupPlane.BACKUP_ON = backupRadioButton.isSelected();
                BackupPlane.backupFrequency = Integer.parseInt(frequencyField.getText());
                BackupPlane.writeConfig();
                BackupPlane.readConfig();
                BackupPlane.backupPlane();
            }
        });
    }
}