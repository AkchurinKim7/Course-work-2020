/**
 * @author Группа 1425ИСО, Акчурин Ким, Уварков Сергей
 */

import java.sql.SQLException;
import java.sql.*;
import javax.swing.*;

public class Login {

    public static JTextField login;
    public static String loginVar = "неизвестный";

    public static void login() {

        JFrame jf = new JFrame("Appaccosys. Вход в систему");
        jf.setSize(365, 260);
        jf.setResizable(false);
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelLogin = new JPanel();
        panelLogin.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        jf.add(panelLogin);

        JLabel hLabel = new JLabel("<html><body><center><h2>Система учета заявок <br> на инновационное мероприятие</h></center></body></html>");
        panelLogin.add(hLabel);

        JLabel logLabel = new JLabel("  Логин:  ");
        panelLogin.add(logLabel);
        panelLogin.add(Box.createHorizontalStrut(12));
        login = new JTextField(15);
        panelLogin.add(login);

        JLabel passwordLabel = new JLabel("Пароль:");
        panelLogin.add(passwordLabel);
        panelLogin.add(Box.createHorizontalStrut(12));
        JPasswordField password = new JPasswordField(15);
        panelLogin.add(password);

        JButton enter = new JButton("Войти");
        panelLogin.add(enter);

        Statement stmt = ConnectionDB.connectionDB(ConnectionDB.passServer);

        /// вход
        enter.addActionListener(e -> {
            String pass = new String(password.getPassword());
            String queryLogin = "SELECT * FROM appaccosys.users WHERE( login = '" + login.getText() + "' AND password = '" + Verify.getHash(pass) + "')";

            ResultSet resultSet = null;
            try {
                resultSet = stmt.executeQuery(queryLogin);
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }

            boolean ERRORSUCCESS = false;
            try {
                while (true) {
                    assert resultSet != null;
                    if (!resultSet.next()) break;
                    ERRORSUCCESS = true;
                    System.out.println("Вход произведен");

                    if (resultSet.getString("role").equals("moder")) {
                        jf.setVisible(false);
                        loginVar = "moder";
                        Log.writeLog("авторизация");
                        Moderator.moderator();
                        break;
                    }
                    if (resultSet.getString("role").equals("admin")) {
                        jf.setVisible(false);
                        loginVar = "admin";
                        Log.writeLog("авторизация");
                        Admin.admin();
                        break;
                    }
                }
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }

            if (!ERRORSUCCESS) {
                /// вывод сообщения о неправильном логине или пароле
                JOptionPane pane = new JOptionPane();
                JOptionPane.showMessageDialog(pane, "Логин или пароль некорректны");
                Log.writeLog("попытка входа в систему");
                pane.setSize(100, 100);
                pane.setVisible(false);
            }
        });
    }
}