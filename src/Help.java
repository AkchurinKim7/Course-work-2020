/**
 * @author ������ 1425���, ������� ������, ������� ���
 */

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Help {

    ///������� ������ ������. ��������� ������, � ������� ����� ���������
    public static void HelpFunc(JPanel panel) {
        JButton buttonHelp = new JButton("<html><h3>������</h></html>");
        buttonHelp.setBounds(200, 250, 160, 70);
        panel.add(buttonHelp);
        buttonHelp.addActionListener(e -> {
            JFrame helpFrame = new JFrame("Appaccosys ������");
            helpFrame.setSize(500, 600);
            helpFrame.setResizable(true);
            helpFrame.setVisible(true);
            helpFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

            JTextArea helpText = new JTextArea(15, 15);
            helpText.setText("������ ��� ������ � �������� ����� ������ �� ������������� " +
                    "����������� Appaccosys:\n\n");
            helpText.append("   �   ��� ���������� ����� ������ ���������� " +
                    "������ �� ������� ������ ������ \"������� ������\", " +
                    "��������� ��������������� ���� � ������ ������ \"�������\".\n\n");
            helpText.append("   �    ��� ��������� ������� ������ ���������� ������ �� ������� ������ ������ " +
                    "\"�������� ������ ������\", ����� ���� ������ ID ������, ������� ����������� ������" +
                    " � ����������� ���������, ����� �� ������ \"�������\".\n\n");
            helpText.append("   �    ��� �������� ������ ���������� ������ �� ������� ������ ������ " +
                    "\"������� ������\", ����� ���� ������ ID ������ � ����������� ��������, ����� �� ������" +
                    " \"�������\".\n\n");
            helpText.append("   �   ��� ��������� ������� ������ ���������� ������ �� ������ " +
                    "\"������� ������\". ��� ��������� ���� ������ ���������� ������ " +
                    "�� ������ \"������� ��� ������\". ��� ��������� ������ ������������ ��������� �������� " +
                    "����������� �������� � ������� �� ������ \"������� ������ �� ��������� ���������\"\n\n");
            helpText.append("   �    ��� ������ ������ ���������� ������ �������, ��� ��� �������� ��� �� ����� " +
                    "� ���� \"����� ������\" �� ������� ������, ����� ���� ������ ������" +
                    " \"������\".\n\n");
            helpText.append("   �    ��� ��������� ������ ���������� ������ �� ������� ������ ������ " +
                    "\"������\".\n\n");
            helpText.append("   �    ��� ������ �� ������� ������ ���������� ������ �� ������� ������" +
                    " ������ \"����� �� ������� ������\".\n\n");


            helpText.append("������������� ����� ������������ ��� �� ������������, ��� � ���������, �� ��� �� �������� " +
                    "���������������� �������������:\n\n");
            helpText.append("   �   ��� ���������� ������ ������������ " +
                    "���������� ������ �� ������� ������ ������ \"�������� ������ ������������\", " +
                    "����� ���� ������ � �������������� ���� ����� � ������, � ������ ���� (moder ��� admin).\n\n");
            helpText.append("   �   ��� �������� ������������ ���������� ������ �� ������� ������ ������ " +
                    "\"������� ������������\", �������� � ��������������� ���� ID ������������ " +
                    "� ������ ������ \"�������\".\n\n");
            helpText.append("   �   ��� ���������� ������ ���� ����������� ���������� ������ �� " +
                    "������ \"�������� ����� �����������\"\n\n");
            helpText.append("   �   ��� ��������� ��������������� ���������� ����������� ���� " +
                    "������ ���������� ������ �� ������� ������ ������ " +
                    "\"�������� ��������� ���������� �����������\".\n\n" +
                    "���� \"������� ���������� �����������\" ��������� ������������� ��������������� " +
                    "���������� ����������� ���� ������ � ��������. " +
                    "��������� \"�������\", ����� �������� �������������� ��������� �����������.\n\n");
            helpText.append("   �   ��� ������� ���������� ����������� ������� �� ������" +
                    " \"������� ��������� �����\"\n\n");
            helpText.append("   �   ��� �������������� ���� ������ �� ��������� ����� ���������� ������ " +
                    "�� ������� ������ ������ \"������������ ������ �� ��������� �����\".\n\n");
            helpText.append("   �   ��� ��������� ��������� ��������� ���������� ������� ���� log.txt.\n\n");

            // ��������� �������� ����
            helpText.setLineWrap(true);
            helpText.setWrapStyleWord(true);

            helpFrame.add(new JScrollPane(helpText));
            JScrollPane scrollHelp = new JScrollPane(helpText,
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

            helpText.setEditable(false);
            helpFrame.add(scrollHelp);
        });
    }
}