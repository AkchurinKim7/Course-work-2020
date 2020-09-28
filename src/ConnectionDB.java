/**
 * @author ������ 1425���, ������� ������, ������� ���
 */

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

///����������� � �������
public class ConnectionDB {
    public static String passServer;
    public static Statement connectionDB(String pass) {
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        Connection connection;
        Statement stmt = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", pass);
            stmt = connection.createStatement();

            if (!connection.isClosed()) {
                System.out.println("���������� �����������");
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
            System.out.println("�� ������� ������������ � ����.");
        }
        return stmt;
    }

    ///���� ����������� � �������
    public static void connectionForm() {
        JFrame jf = new JFrame("Appaccosys. �������� �����������");
        jf.setSize(350, 100);
        jf.setResizable(false);
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelConnect = new JPanel();
        jf.add(panelConnect);

        JLabel logLabel = new JLabel("  root ������:  ");
        panelConnect.add(logLabel);
        panelConnect.add(Box.createHorizontalStrut(12));
        JTextField FieldConnect = new JTextField(15);
        panelConnect.add(FieldConnect);

        JButton connectButton = new JButton("������������");
        panelConnect.add(connectButton);
        jf.setVisible(true);

        connectButton.addActionListener(e -> {
            if (connectionDB(FieldConnect.getText()) != null) {
                jf.setVisible(false);
                passServer = FieldConnect.getText();
                try {
                    CheckDB.checkBD();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
            else {
                JOptionPane noConnect = new JOptionPane();
                JOptionPane.showMessageDialog(noConnect, "���������� ������������ � �������");
                noConnect.setSize(100, 100);
                noConnect.setVisible(false);
            }
        });
    }

    ///���� �����������
    public static void greeting() {
        Frame jf = new JFrame("Appaccosys");
        jf.setSize(500, 170);
        jf.setResizable(false);
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);

        JPanel panelGreeting = new JPanel();
        panelGreeting.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jf.add(panelGreeting);
        JLabel greetingLabel = new JLabel("<html><body><center><h1>��������� ����� ������<br>�� ������������� �����������</h></center></body></html>");
        panelGreeting.add(greetingLabel);

        try {
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        jf.setVisible(false);
        ConnectionDB.connectionForm();
    }
}