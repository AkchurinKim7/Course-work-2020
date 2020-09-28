/**
 * @author Группа 1425ИСО, Акчурин Ким, Уварков Сергей
 */

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Objects;
import java.util.Vector;

public class Moderator {

    public static JPanel panel = new JPanel();
    public static JFrame jf = new JFrame("AppAccoSys");
    public static JLabel moderTitle;

    public static void moderator() {

        BackupPlane.backupPlane();

        jf.setSize(400, 450);
        jf.setResizable(false);
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.setLayout(null);
        jf.getContentPane().add(panel);

        moderTitle = new JLabel("<html><body><center><h1>Меню модератора</h></center></body></html>");
        moderTitle.setBounds(80, -70, 300, 200);

        /// Создание и настройка кнопок
        JButton createApp = new JButton("<html><h3>Создать заявку</h></html>");
        createApp.setBounds(20, 70, 160, 70);

        JButton editApp = new JButton("<html><body><center><h3>Изменить статус заявки</h></center></body></html>");
        editApp.setBounds(200, 70, 160, 70);

        JButton deleteApp = new JButton("<html><h3>Удалить заявку</h></html>");
        deleteApp.setBounds(20, 160, 160, 70);

        JButton showApp = new JButton("<html><h3>Вывести заявки</h></html>");
        showApp.setBounds(200, 160, 160, 70);

        JLabel searchLabel = new JLabel("<html><body><center><h3>Поиск заявок по ФИО</h></center></body></html>");
        searchLabel.setBounds(23, 220, 160, 70);

        JTextField searchField = new JTextField(20);
        searchField.setBounds(20, 270, 160, 20);

        JButton searchApp = new JButton("<html><h3>Искать</h></html>");
        searchApp.setBounds(50, 295, 100, 25);

        /// Добавление кнопок на панель
        panel.add(moderTitle);
        panel.add(createApp);
        panel.add(editApp);
        panel.add(deleteApp);
        panel.add(showApp);
        panel.add(searchLabel);
        panel.add(searchField);
        panel.add(searchApp);
        Help.HelpFunc(panel);
        ExitAcc.exitAccFunc(panel, jf);


        /// Создать новую заявку
        createApp.addActionListener(e -> {
            JFrame jf = new JFrame("Создание новой заявки");
            jf.setSize(290, 355);
            jf.setResizable(false);
            jf.setVisible(true);
            jf.setLocationRelativeTo(null);
            jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

            JPanel addPanel = new JPanel();
            addPanel.setLayout(null);
            jf.getContentPane().add(addPanel);

            JLabel surnameLabel = new JLabel("Фамилия:");
            surnameLabel.setBounds(40, -70, 200, 200);
            addPanel.add(surnameLabel);
            JTextField surname = new JTextField(15);
            surname.setBounds(130, 20, 100, 20);
            addPanel.add(surname);
            CheckDB.checkIntegrity(surname, 64);

            JLabel nameLabel = new JLabel("Имя:");
            nameLabel.setBounds(40, -40, 200, 200);
            addPanel.add(nameLabel);
            JTextField name = new JTextField(15);
            name.setBounds(130, 50, 100, 20);
            addPanel.add(name);
            CheckDB.checkIntegrity(name, 64);

            JLabel patronLabel = new JLabel("Отчество:");
            patronLabel.setBounds(40, -10, 200, 200);
            addPanel.add(patronLabel);
            JTextField patronymic = new JTextField(15);
            patronymic.setBounds(130, 80, 100, 20);
            addPanel.add(patronymic);
            CheckDB.checkIntegrity(patronymic, 64);

            JLabel mailLabel = new JLabel("E-mail:");
            mailLabel.setBounds(40, 20, 200, 200);
            addPanel.add(mailLabel);
            JTextField mail = new JTextField(15);
            mail.setBounds(130, 110, 100, 20);
            addPanel.add(mail);
            CheckDB.checkIntegrity(mail, 64);

            JLabel roleLabel = new JLabel("Роль:");
            roleLabel.setBounds(40, 50, 200, 200);
            addPanel.add(roleLabel);
            String[] roleItems = AddItemFromDB.addItemFromDB("Select roles FROM appaccosys.table_role");
            JComboBox<String> role = new JComboBox<>(roleItems);
            role.setBounds(130, 140, 100, 20);
            addPanel.add(role);

            JLabel paymentLabel = new JLabel("Тип оплаты:");
            paymentLabel.setBounds(40, 80, 200, 200);
            addPanel.add(paymentLabel);
            String[] paymentItems = AddItemFromDB.addItemFromDB("Select payment FROM appaccosys.table_payment");
            JComboBox<String> payment = new JComboBox<>(paymentItems);
            payment.setBounds(130, 170, 100, 20);
            addPanel.add(payment);

            JLabel inviteLabel = new JLabel("Приглашение:");
            inviteLabel.setBounds(40, 110, 200, 200);
            addPanel.add(inviteLabel);
            String[] inviteItems = AddItemFromDB.addItemFromDB("Select invitation FROM appaccosys.table_invitations");
            JComboBox<String> invite = new JComboBox<>(inviteItems);
            invite.setBounds(130, 200, 100, 20);
            addPanel.add(invite);

            JLabel eventLabel = new JLabel("Тип события:");
            eventLabel.setBounds(40, 140, 200, 200);
            addPanel.add(eventLabel);
            String[] eventItems = AddItemFromDB.addItemFromDB("Select events FROM appaccosys.table_events");
            JComboBox<String> event = new JComboBox<>(eventItems);
            event.setBounds(130, 230, 100, 20);
            addPanel.add(event);

            JButton addButton = new JButton("Создать");
            addButton.setBounds(80, 265, 100, 30);
            addPanel.add(addButton);

            addButton.addActionListener(e1 -> {
                String surn = surname.getText();
                String nm = name.getText();
                String patrnm = patronymic.getText();
                String eml = mail.getText();
                String rl = (String) role.getSelectedItem();
                String paym = (String) payment.getSelectedItem();
                String inv = (String) invite.getSelectedItem();
                String eve = (String) event.getSelectedItem();

                String query = "INSERT INTO appaccosys.table_application_forms (surname, first_name, patronymic, " +
                        "type_member_role, type_payment, type_invitation, type_event, type_state, email)" +
                        " VALUES ('" + surn + "', '" + nm + "', '" + patrnm + "', '" + rl +
                        "', '" + paym + "', '" + inv + "', '" + eve + "', 'в ожидании', '" + eml + "')";
                String querySelect = "SELECT id_participant FROM appaccosys.table_application_forms where id_participant = " +
                        "(SELECT MAX(id_participant) FROM appaccosys.table_application_forms) AND surname= '" + surn + "' AND " +
                        "first_name= '" + nm + "' AND patronymic= '" + patrnm + "' AND type_member_role= '" + rl +
                        "' AND type_payment= '" + paym + "' AND type_invitation= '" + inv +
                        "' AND type_event= '" + eve + "' AND type_state='в ожидании' AND email= '" + eml + "'";

                String successful_message = "Заявка успешно добавлена";
                String unsuccessful_message = "Не удалось добавить заявку";

                QueryCreateRequest.queryCreateRequest(query, querySelect, successful_message, unsuccessful_message);

                surname.setText(null);
                name.setText(null);
                patronymic.setText(null);
                mail.setText(null);

            });
        });


        /// Поиск заявок по ФИО
        searchApp.addActionListener(e -> {

            String searchString = searchField.getText();

            String request = "SELECT * FROM appaccosys.table_application_forms WHERE surname like '%" + searchString + "%' or " +
                    "first_name like '%" + searchString + "%' or patronymic like '%" + searchString + "%'";

            Vector<Vector<String>> values = null;
            try {
                values = getDataFromApp(request);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            Output(values);

            searchField.setText(null);
        });


        /// Изменить статус заявки
        editApp.addActionListener(e -> {
            JFrame jf = new JFrame("Изменение статуса заявки");
            jf.setSize(275, 170);
            jf.setResizable(false);
            jf.setVisible(true);
            jf.setLocationRelativeTo(null);
            jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

            JPanel editPanel = new JPanel();
            editPanel.setLayout(null);
            jf.getContentPane().add(editPanel);

            JLabel idLabel = new JLabel("ID заявки:");
            idLabel.setBounds(40, -70, 200, 200);
            editPanel.add(idLabel);
            JTextField idApp = new JTextField(15);
            idApp.setBounds(115, 20, 100, 20);
            editPanel.add(idApp);
            CheckDB.checkNumber(idApp);

            JLabel statusLabel = new JLabel("Статус:");
            statusLabel.setBounds(40, -40, 200, 200);
            editPanel.add(statusLabel);
            String[] statusItems = {"одобрено", "отклонено"};
            JComboBox<String> status = new JComboBox<>(statusItems);
            status.setBounds(115, 50, 100, 20);
            editPanel.add(status);

            JButton acceptButton = new JButton("Принять");
            acceptButton.setBounds(80, 85, 100, 30);
            editPanel.add(acceptButton);

            acceptButton.addActionListener(e1 -> {
                String id = idApp.getText();
                String stat = (String) status.getSelectedItem();

                String query = "UPDATE appaccosys.table_application_forms SET type_state = '" + stat + "' WHERE id_participant = '" + id + "'";
                String querySelect = "SELECT id_participant FROM appaccosys.table_application_forms WHERE type_state = '" + stat +
                        "' AND id_participant = '" + id + "'";
                String successful_message;
                assert stat != null;
                if (stat.equals("одобрено")) {
                    successful_message = "заявка N " + id + " одобрена";
                } else {
                    successful_message = "заявка N " + id + " отклонена";
                }

                String unsuccessful_message = "Не удалось изменить статус заявки";
                QueryCreateRequest.queryCreateRequest(query, querySelect, successful_message, unsuccessful_message);

                idApp.setText(null);
            });
        });


        /// Удаление заявки
        deleteApp.addActionListener(e -> {
            JFrame jf = new JFrame("Удаление заявки");
            jf.setSize(275, 140);
            jf.setResizable(false);
            jf.setVisible(true);
            jf.setLocationRelativeTo(null);
            jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

            JPanel delPanel = new JPanel();
            delPanel.setLayout(null);
            jf.getContentPane().add(delPanel);

            JLabel idLabel = new JLabel("ID заявки:");
            delPanel.add(idLabel);
            idLabel.setBounds(40, -70, 200, 200);
            JTextField idApp = new JTextField(15);
            idApp.setBounds(115, 20, 100, 20);
            delPanel.add(idApp);
            CheckDB.checkNumber(idApp);

            JButton delButton = new JButton("Удалить");
            delButton.setBounds(80, 50, 100, 25);
            delPanel.add(delButton);

            delButton.addActionListener(e1 -> {
                int selection = JOptionPane.showConfirmDialog(null, "Вы уверены?", "Удаление документа из списка заказов", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (selection == JOptionPane.YES_OPTION) {
                    String id = idApp.getText();

                    String query = "DELETE FROM appaccosys.table_application_forms WHERE id_participant = '" + id + "'";
                    String querySelect = "SELECT id_participant FROM appaccosys.table_application_forms WHERE id_participant = '" + id + "'";
                    String successful_message = "Не удалось удалить заявку";
                    String unsuccessful_message = "Заявка удалена";
                    QueryCreateRequest.queryCreateRequest(query, querySelect, successful_message, unsuccessful_message);

                    idApp.setText(null);
                }
            });
        });


        /// Вывод заявок
        showApp.addActionListener(e -> {
            JFrame jf = new JFrame("Вывод заявок");
            jf.setSize(323, 375);
            jf.setResizable(false);
            jf.setVisible(true);
            jf.setLocationRelativeTo(null);
            jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

            JPanel showPanel = new JPanel();
            showPanel.setLayout(null);
            jf.getContentPane().add(showPanel);

            JButton showButton = new JButton("<html><h3>Вывести все заявки</h></html>");
            showButton.setBounds(40, 20, 225, 45);
            showPanel.add(showButton);

            JLabel titleLabel = new JLabel("<html><body><center><h2>Вывод заявок по критериям</h></center></body></html>");
            titleLabel.setBounds(40, 80, 225, 45);
            showPanel.add(titleLabel);

            JLabel paymentLabel = new JLabel("Тип оплаты:");
            paymentLabel.setBounds(40, 50, 200, 200);
            showPanel.add(paymentLabel);
            String[] paymentItems = AddItemFromDB.addItemFromDB("Select payment FROM appaccosys.table_payment");
            JComboBox<String> payment = new JComboBox<>(paymentItems);
            payment.addItem("любое");
            payment.setBounds(150, 140, 100, 20);
            showPanel.add(payment);

            JLabel inviteLabel = new JLabel("Приглашение:");
            inviteLabel.setBounds(40, 80, 200, 200);
            showPanel.add(inviteLabel);
            String[] inviteItems = AddItemFromDB.addItemFromDB("Select invitation FROM appaccosys.table_invitations");
            JComboBox<String> invite = new JComboBox<>(inviteItems);
            invite.addItem("любое");
            invite.setBounds(150, 170, 100, 20);
            showPanel.add(invite);

            JLabel eventLabel = new JLabel("Тип события:");
            eventLabel.setBounds(40, 110, 200, 200);
            showPanel.add(eventLabel);
            String[] eventItems = AddItemFromDB.addItemFromDB("Select events FROM appaccosys.table_events");
            JComboBox<String> event = new JComboBox<>(eventItems);
            event.addItem("любое");
            event.setBounds(150, 200, 100, 20);
            showPanel.add(event);

            JLabel stateLabel = new JLabel("Статус:");
            stateLabel.setBounds(40, 140, 200, 200);
            showPanel.add(stateLabel);
            String[] stateItems = AddItemFromDB.addItemFromDB("Select state FROM appaccosys.table_state");
            JComboBox<String> state = new JComboBox<>(stateItems);
            state.addItem("любое");
            state.setBounds(150, 230, 100, 20);
            showPanel.add(state);

            JButton outButton = new JButton("<html><body><center><h3>Вывести заявки по выбранным критериям</h></center></body></html>");
            outButton.setBounds(40, 270, 225, 45);
            showPanel.add(outButton);


            /// Вывод заявок по выбранным критериям
            outButton.addActionListener(e1 -> {

                String paym, inv, eve, st;

                if (Objects.equals(payment.getSelectedItem(), "любое")) {
                    paym = "";
                } else {
                    paym = (String) payment.getSelectedItem();
                }

                if (Objects.equals(invite.getSelectedItem(), "любое")) {
                    inv = "";
                } else {
                    inv = (String) invite.getSelectedItem();
                }

                if (Objects.equals(event.getSelectedItem(), "любое")) {
                    eve = "";
                } else {
                    eve = (String) event.getSelectedItem();
                }

                if (Objects.equals(state.getSelectedItem(), "любое")) {
                    st = "";
                } else {
                    st = (String) state.getSelectedItem();
                }

                String request = "SELECT * FROM appaccosys.table_application_forms WHERE type_payment like '" + paym + "%' AND " +
                        "type_invitation like '%" + inv + "%' AND type_event like '%" + eve + "%' AND type_state like '%" + st + "%'";

                Vector<Vector<String>> values = null;
                try {
                    values = getDataFromApp(request);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                Output(values);
            });


            /// Вывод всех заявок
            showButton.addActionListener(e2 -> {
                Vector<Vector<String>> values = null;
                try {
                    values = getDataFromApp("SELECT * FROM appaccosys.table_application_forms");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                Output(values);
            });

        });
    }


    /// Получение данных из бд для таблицы "Заявки"
    public static Vector<Vector<String>> getDataFromApp(String request) throws Exception {

        Vector<Vector<String>> result = new Vector<>();

        ResultSet resultSet;

        Statement stmt = ConnectionDB.connectionDB(ConnectionDB.passServer);
        assert stmt != null;
        resultSet = stmt.executeQuery(request);

        String p1, p2, p3, p4, p5, p6, p7, p8, p9, p10;

        while (resultSet.next()) {
            Vector<String> elements = new Vector<>();

            p1 = resultSet.getString(1);
            p2 = resultSet.getString(2);
            p3 = resultSet.getString(3);
            p4 = resultSet.getString(4);
            p5 = resultSet.getString(5);
            p6 = resultSet.getString(6);
            p7 = resultSet.getString(7);
            p8 = resultSet.getString(8);
            p9 = resultSet.getString(9);
            p10 = resultSet.getString(10);

            elements.add(p1);
            elements.add(p2);
            elements.add(p3);
            elements.add(p4);
            elements.add(p5);
            elements.add(p6);
            elements.add(p7);
            elements.add(p8);
            elements.add(p9);
            elements.add(p10);

            result.add(elements);
        }

        resultSet.close();
        stmt.close();

        return result;
    }


    /// Форма для вывода заявок
    public static void Output(Vector<Vector<String>> values) {

        JFrame jf = new JFrame("Заявки");
        jf.setSize(800, 550);
        jf.setResizable(false);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JTable dbTable = new JTable();

        JScrollPane pane = new JScrollPane();
        pane.getViewport().add(dbTable);
        jf.getContentPane().add(pane, BorderLayout.CENTER);

        Vector<String> header = new Vector<>();

        header.add("ID");
        header.add("Фамилия");
        header.add("Имя");
        header.add("Отчество");
        header.add("Роль");
        header.add("Тип оплаты");
        header.add("Приглашение");
        header.add("Тип события");
        header.add("Состояние заявки");
        header.add("E-mail");

        DefaultTableModel dtm = (DefaultTableModel) dbTable.getModel();

        dtm.setDataVector(values, header);
    }
}