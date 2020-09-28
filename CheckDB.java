/**
 * @author Группа 1425ИСО, Акчурин Ким, Уварков Сергей
 */

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckDB {
    /// проверка наличия базы данных или файлов с резервными копиями данных
    public static void checkBD() throws SQLException {
        Statement stmt = ConnectionDB.connectionDB(ConnectionDB.passServer);
        ResultSet resultSet = stmt.executeQuery("show databases like 'appaccosys'");
        boolean EXISTDB = false;
        while (resultSet.next()) {
            EXISTDB = true;
        }

        if (EXISTDB) {
            Login.login();
        } else {
            createOrRestForm(checkBackup());
        }
    }


    /// окно создания и восстановление бд, если искомая бд не найдена
    public static void createOrRestForm(boolean answerNo) {
        JFrame jf = new JFrame("Appaccosys");
        jf.setSize(300, 145);
        jf.setResizable(false);
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel noDBPanel = new JPanel();
        noDBPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jf.getContentPane().add(noDBPanel);

        JLabel noDBLabel = new JLabel("База данных не найдена");
        noDBPanel.add(noDBLabel);
        if (answerNo) {
            JLabel noDBLabel2 = new JLabel("Хотите восстановить из резервной копии?");
            noDBPanel.add(noDBLabel2);
        }

        JButton createButton = new JButton("Создать новую");
        noDBPanel.add(createButton);

        ///кнопка создания бд
        createButton.addActionListener(e1 -> {
            int selection = JOptionPane.showConfirmDialog(null, "Вы уверены?", "Создать новую базу данных", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (selection == JOptionPane.YES_OPTION) {
                try {
                    QueryCreateDB.queryCreateDB();
                    QueryCreateDB.defaultDB();
                    MessagePane.messagePane("Новая база данных успешно создана");
                    jf.setVisible(false);
                    Login.login();


                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                    MessagePane.messagePane("Не удалось создать базу данных");
                }
            }
        });

        if (answerNo) {
            JButton restButton = new JButton("Восстановить");
            noDBPanel.add(restButton);
            restButton.addActionListener(e1 -> {
                int selection = JOptionPane.showConfirmDialog(null, "Вы уверены?", "Создать новую базу данных", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (selection == JOptionPane.YES_OPTION) {
                    if (QueryRecovery.queryRecovery()) {
                        noDBPanel.removeAll();
                        jf.dispose();
                    }
                }
            });
        }
    }


    public static boolean checkBackup() {
        ///проверка существования бэкапа
        File fileTableEvents = new File(QueryBackup.getSecurePath() +
                "aacs_table_events.sql");
        File fileUsers = new File(QueryBackup.getSecurePath() +
                "aacs_users.sql");
        File fileTableAppForms = new File(QueryBackup.getSecurePath() +
                "aacs_table_application_forms.sql");

        return (fileTableEvents.exists() &&
                fileUsers.exists() && fileTableAppForms.exists());
    }


    /// защита от нецифрвого ввода
    public static void checkNumber(JTextField numberField) {
        numberField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (((e.getKeyChar() < '0') || (e.getKeyChar() > '9'))) {
                    e.consume();
                }
            }
        });
    }


    /// защита целостности
    public static void checkIntegrity(JTextField integrityField, int lengthField){
        integrityField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (integrityField.getText().length() >= lengthField) {
                    e.consume();
                }
            }
        });
    }
}