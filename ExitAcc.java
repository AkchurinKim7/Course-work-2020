/**
 * @author Группа 1425ИСО, Уварков Сергей, Акчурин Ким
 */

import javax.swing.*;

public class ExitAcc {

    public static JButton exitBut;

    ///кнопка выхода из аккаунта, принимает панель, к которой крепится
    public static void exitAccFunc(JPanel panel, JFrame jf) {

        exitBut = new JButton("<html><body><center><h3>Выход из учётной записи</h></center></body></html>");
        exitBut.setBounds(20, 340, 340, 50);
        panel.add(exitBut);

        exitBut.addActionListener(e -> {

            int selection = JOptionPane.showConfirmDialog(null, "Вы действительно хотите выйти?", "Выход из учётной записи", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (selection == JOptionPane.YES_OPTION) {

                panel.removeAll();
                jf.dispose();
                Login.login();
            }
        });
    }
}