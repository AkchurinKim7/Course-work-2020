/**
 * @author ������ 1425���, ������� ������, ������� ���
 */

import javax.swing.*;

public class ExitAcc {

    public static JButton exitBut;

    ///������ ������ �� ��������, ��������� ������, � ������� ��������
    public static void exitAccFunc(JPanel panel, JFrame jf) {

        exitBut = new JButton("<html><body><center><h3>����� �� ������� ������</h></center></body></html>");
        exitBut.setBounds(20, 340, 340, 50);
        panel.add(exitBut);

        exitBut.addActionListener(e -> {

            int selection = JOptionPane.showConfirmDialog(null, "�� ������������� ������ �����?", "����� �� ������� ������", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (selection == JOptionPane.YES_OPTION) {

                panel.removeAll();
                jf.dispose();
                Login.login();
            }
        });
    }
}