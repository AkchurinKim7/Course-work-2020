/**
 * @author Группа 1425ИСО, Уварков Сергей, Акчурин Ким
 */

import javax.swing.*;

public class MessagePane {
    ///вызов панели сообщения
    public static void messagePane(String message){
        JOptionPane pane = new JOptionPane();
        JOptionPane.showMessageDialog(pane, message);
        pane.setSize(100, 100);
        pane.setVisible(false);
    }
}