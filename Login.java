/**
 * @author ������ 1425���, ������� ���, ������� ������
 */

import java.sql.SQLException;
import java.sql.*;
import javax.swing.*;

public class Login {

    public static JTextField login;
    public static String loginVar = "�����������";

    public static void login() {

        JFrame jf = new JFrame("Appaccosys. ���� � �������");
        jf.setSize(365, 260);
        jf.setResizable(false);
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelLogin = new JPanel();
        panelLogin.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        jf.add(panelLogin);

        JLabel hLabel = new JLabel("<html><body><center><h2>������� ����� ������ <br> �� ������������� �����������</h></center></body></html>");
        panelLogin.add(hLabel);

        JLabel logLabel = new JLabel("  �����:  ");
        panelLogin.add(logLabel);
        panelLogin.add(Box.createHorizontalStrut(12));
        login = new JTextField(15);
        panelLogin.add(login);

        JLabel passwordLabel = new JLabel("������:");
        panelLogin.add(passwordLabel);
        panelLogin.add(Box.createHorizontalStrut(12));
        JPasswordField password = new JPasswordField(15);
        panelLogin.add(password);

        JButton enter = new JButton("�����");
        panelLogin.add(enter);

        Statement stmt = ConnectionDB.connectionDB(ConnectionDB.passServer);

        /// ����
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
                    System.out.println("���� ����������");

                    if (resultSet.getString("role").equals("moder")) {
                        jf.setVisible(false);
                        loginVar = "moder";
                        Log.writeLog("�����������");
                        Moderator.moderator();
                        break;
                    }
                    if (resultSet.getString("role").equals("admin")) {
                        jf.setVisible(false);
                        loginVar = "admin";
                        Log.writeLog("�����������");
                        Admin.admin();
                        break;
                    }
                }
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }

            if (!ERRORSUCCESS) {
                /// ����� ��������� � ������������ ������ ��� ������
                JOptionPane pane = new JOptionPane();
                JOptionPane.showMessageDialog(pane, "����� ��� ������ �����������");
                Log.writeLog("������� ����� � �������");
                pane.setSize(100, 100);
                pane.setVisible(false);
            }
        });
    }
}