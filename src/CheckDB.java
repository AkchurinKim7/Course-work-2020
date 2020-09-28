/**
 * @author ������ 1425���, ������� ���, ������� ������
 */

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckDB {
    /// �������� ������� ���� ������ ��� ������ � ���������� ������� ������
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


    /// ���� �������� � �������������� ��, ���� ������� �� �� �������
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

        JLabel noDBLabel = new JLabel("���� ������ �� �������");
        noDBPanel.add(noDBLabel);
        if (answerNo) {
            JLabel noDBLabel2 = new JLabel("������ ������������ �� ��������� �����?");
            noDBPanel.add(noDBLabel2);
        }

        JButton createButton = new JButton("������� �����");
        noDBPanel.add(createButton);

        ///������ �������� ��
        createButton.addActionListener(e1 -> {
            int selection = JOptionPane.showConfirmDialog(null, "�� �������?", "������� ����� ���� ������", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (selection == JOptionPane.YES_OPTION) {
                try {
                    QueryCreateDB.queryCreateDB();
                    QueryCreateDB.defaultDB();
                    MessagePane.messagePane("����� ���� ������ ������� �������");
                    jf.setVisible(false);
                    Login.login();


                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                    MessagePane.messagePane("�� ������� ������� ���� ������");
                }
            }
        });

        if (answerNo) {
            JButton restButton = new JButton("������������");
            noDBPanel.add(restButton);
            restButton.addActionListener(e1 -> {
                int selection = JOptionPane.showConfirmDialog(null, "�� �������?", "������� ����� ���� ������", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
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
        ///�������� ������������� ������
        File fileTableEvents = new File(QueryBackup.getSecurePath() +
                "aacs_table_events.sql");
        File fileUsers = new File(QueryBackup.getSecurePath() +
                "aacs_users.sql");
        File fileTableAppForms = new File(QueryBackup.getSecurePath() +
                "aacs_table_application_forms.sql");

        return (fileTableEvents.exists() &&
                fileUsers.exists() && fileTableAppForms.exists());
    }


    /// ������ �� ���������� �����
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


    /// ������ �����������
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