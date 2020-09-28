/**
 * @author Группа 1425ИСО, Акчурин Ким, Уварков Сергей
 */

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class Admin extends Moderator {

    public static void admin() {

        moderator();

        jf.setSize(780, 450);

        /// Создание и настройка кнопок
        JLabel adminTitle = new JLabel("<html><body><center><h1>Меню администратора</h></center></body></html>");
        adminTitle.setBounds(240, -70, 300, 200);

        JButton addUser = new JButton("<html><body><center><h3>Добавить нового пользователя</h></center></body></html>");
        addUser.setBounds(400, 70, 160, 70);

        JButton delUser = new JButton("<html><body><center><h3>Удалить пользователя</h></center></body></html>");
        delUser.setBounds(580, 70, 160, 70);

        JButton addEvent = new JButton("<html><body><center><h3>Добавить новое мероприятие</h></center></body></html>");
        addEvent.setBounds(400, 160, 160, 70);

        JCheckBox backupBox = new JCheckBox("<html><body><center><h3>Резервное копирование</h></center></body></html>");
        backupBox.setBounds(580, 340, 160, 70);

        JButton backupButton = new JButton("<html><body><center><h3>Создать резервную копию</h></center></body></html>");
        backupButton.setBounds(580, 160, 160, 70);

        JButton settingsBackup = new JButton("<html><body><center><h3>Параметры резервного копирования</h></center></body></html>");
        settingsBackup.setBounds(400, 250, 160, 70);

        JButton reestablishBackup = new JButton("<html><body><center><h3>Восстановить данные из резервной копии</h></center></body></html>");
        reestablishBackup.setBounds(580, 250, 160, 70);

        JButton listUsers = new JButton("Список пользователей");

        /// Добавление кнопок на панель
        panel.remove(moderTitle);
        panel.add(adminTitle);
        panel.add(addUser);
        panel.add(delUser);
        panel.add(addEvent);
        panel.add(backupButton);
        panel.add(settingsBackup);
        panel.add(reestablishBackup);
        ExitAcc.exitBut.setBounds(240, 340, 280, 60);

        ///резервное копирование
        backupButton.addActionListener(e -> QueryBackup.queryBackup(false));

        ///восстановление
        reestablishBackup.addActionListener(e -> {
            int selection = JOptionPane.showConfirmDialog(null, "Вы уверены, что хотите произвести восстановление базы данных? Текущие данные будут потеряны", "Восстановление базы данных", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (selection == JOptionPane.YES_OPTION) {
                if (QueryRecovery.queryRecovery()) {
                    panel.removeAll();
                    jf.dispose();
                }
            }
        });


        /// Добавление нового пользователя
        addUser.addActionListener(e -> {
            JFrame jf = new JFrame("Добавить нового пользователя");
            jf.setSize(285, 200);
            jf.setResizable(false);
            jf.setVisible(true);
            jf.setLocationRelativeTo(null);
            jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

            JPanel addPanel = new JPanel();
            addPanel.setLayout(null);
            jf.getContentPane().add(addPanel);

            JLabel logLabel = new JLabel("Логин:");
            logLabel.setBounds(40, -70, 200, 200);
            addPanel.add(logLabel);
            JTextField login = new JTextField(15);
            login.setBounds(130, 20, 100, 20);
            addPanel.add(login);

            JLabel passwordLabel = new JLabel("Пароль:");
            passwordLabel.setBounds(40, -40, 200, 200);
            addPanel.add(passwordLabel);
            JTextField password = new JTextField(15);
            password.setBounds(130, 50, 100, 20);
            addPanel.add(password);

            JLabel roleLabel = new JLabel("Роль:");
            roleLabel.setBounds(40, -10, 200, 200);
            addPanel.add(roleLabel);
            String[] roleItems = AddItemFromDB.addItemFromDB("Select users_role FROM appaccosys.table_users_role");
            JComboBox<String> role = new JComboBox<>(roleItems);
            role.setBounds(130, 80, 100, 20);
            addPanel.add(role);

            JButton addButton = new JButton("Добавить");
            addButton.setBounds(85, 115, 100, 30);
            addPanel.add(addButton);

            addButton.addActionListener(e1 -> {
                String log = login.getText();
                String pass = password.getText();
                String hashPass = Verify.getHash(pass);
                String rol = (String) role.getSelectedItem();

                String query = "INSERT INTO appaccosys.users (login, password, role)" +
                        " VALUES ('" + log + "', '" + hashPass + "', '" + rol + "')";
                String querySelect = "SELECT id_users FROM appaccosys.users WHERE id_users = (SELECT MAX(id_users)) AND login= '" +
                        log + "' AND password='" + hashPass + "' AND role='" + rol + "'";

                String successful_message = "Новый пользователь успешно добавлен";
                String unsuccessful_message = "Не удалось добавить нового пользователя";

                QueryCreateRequest.queryCreateRequest(query, querySelect, successful_message, unsuccessful_message);

                Log.writeLog("пользователь " + log + " добавлен");

                login.setText(null);
                password.setText(null);
            });
        });


        /// удаление пользователя
        delUser.addActionListener(e -> {
            JFrame jf = new JFrame("Удалить пользователя");
            jf.setSize(260, 170);
            jf.setResizable(false);
            jf.setVisible(true);
            jf.setLocationRelativeTo(null);
            jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

            JPanel delPanel = new JPanel();
            delPanel.setLayout(null);
            jf.getContentPane().add(delPanel);

            JLabel idLabel = new JLabel("ID пользователя:");
            idLabel.setBounds(40, -70, 200, 200);
            delPanel.add(idLabel);
            JTextField idUser = new JTextField(15);
            idUser.setBounds(160, 20, 50, 20);
            delPanel.add(idUser);
            CheckDB.checkNumber(idUser);

            JButton delButton = new JButton("Удалить");
            delButton.setBounds(40, 50, 170, 25);
            delPanel.add(delButton);

            listUsers.setBounds(40, 85, 170, 25);
            delPanel.add(listUsers);

            delButton.addActionListener(e1 -> {
                int selection = JOptionPane.showConfirmDialog(null, "Вы уверены?", "Удаление пользователя", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (selection == JOptionPane.YES_OPTION) {
                    String user = idUser.getText();

                    String query = "DELETE FROM appaccosys.users WHERE id_users = '" + user + "'";
                    String querySelect = "SELECT id_users FROM appaccosys.users WHERE id_users = '" + user + "'";

                    String successful_message = "Не удалось удалить пользователя";
                    String unsuccessful_message = "Пользователь удалён";

                    QueryCreateRequest.queryCreateRequest(query, querySelect, successful_message, unsuccessful_message);

                    idUser.setText(null);
                }
            });
        });


        /// вывод всех пользователей
        listUsers.addActionListener(e -> {
            JFrame jf = new JFrame("Пользователи");
            jf.setSize(400, 400);
            jf.setResizable(false);
            jf.setVisible(true);
            jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

            JTable dbTable = new JTable();

            JScrollPane pane = new JScrollPane();
            pane.getViewport().add(dbTable);
            jf.getContentPane().add(pane, BorderLayout.CENTER);

            Vector<Vector<String>> values = null;
            try {
                values = getDataFromUsers();
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            Vector<String> header = new Vector<>();

            header.add("ID");
            header.add("Логин");
            header.add("Роль");

            DefaultTableModel dtm = (DefaultTableModel) dbTable.getModel();

            dtm.setDataVector(values, header);
        });


        /// Добавление нового мероприятия
        addEvent.addActionListener(e -> {
            JFrame jf = new JFrame("Добавление нового мероприятия");
            jf.setSize(300, 180);
            jf.setResizable(false);
            jf.setVisible(true);
            jf.setLocationRelativeTo(null);
            jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

            JPanel delPanel = new JPanel();
            delPanel.setLayout(null);
            jf.getContentPane().add(delPanel);

            JLabel titleLabel = new JLabel("<html><body><center><h3>Введите название нового мероприятия</h></center></body></html>");
            delPanel.add(titleLabel);
            titleLabel.setBounds(40, -70, 200, 200);

            JTextField nameEvent = new JTextField(15);
            nameEvent.setBounds(65, 60, 150, 20);
            delPanel.add(nameEvent);
            CheckDB.checkIntegrity(nameEvent, 25);

            JButton addButton = new JButton("Добавить");
            addButton.setBounds(90, 90, 100, 25);
            delPanel.add(addButton);

            addButton.addActionListener(e1 -> {
                String name = nameEvent.getText();

                String query = "INSERT INTO appaccosys.table_events (events)" +
                        " VALUES ('" + name + "')";
                String querySelect = "SELECT events FROM appaccosys.table_events WHERE events = '" + name + "'";

                String successful_message = "Новое мероприятие успешно добавлено";
                String unsuccessful_message = "Не удалось добавить новое мероприятие";

                QueryCreateRequest.queryCreateRequest(query, querySelect, successful_message, unsuccessful_message);

                nameEvent.setText(null);
            });

        });

        ///настройки бэкапа
        settingsBackup.addActionListener(e -> QueryBackup.autoBackup());
    }


    /// получение данных из бд для таблицы "Пользователи"
    public static Vector<Vector<String>> getDataFromUsers() throws Exception {

        Vector<Vector<String>> result = new Vector<>();

        String query = "SELECT id_users, login, role FROM appaccosys.users";

        ResultSet resultSet;

        Statement stmt = ConnectionDB.connectionDB(ConnectionDB.passServer);
        assert stmt != null;
        resultSet = stmt.executeQuery(query);

        String p1, p2, p3;

        while (resultSet.next()) {
            Vector<String> element = new Vector<>();

            p1 = resultSet.getString(1);
            p2 = resultSet.getString(2);
            p3 = resultSet.getString(3);

            element.add(p1);
            element.add(p2);
            element.add(p3);

            result.add(element);
        }

        resultSet.close();
        stmt.close();

        return result;
    }
}