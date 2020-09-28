/**
 * @author Группа 1425ИСО, Уварков Сергей, Акчурин Ким
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import javax.swing.JOptionPane;

public class Log {

	/// логирование
	public static void writeLog(String eventName) {
		try {
			FileWriter LogWriter = new FileWriter("log.txt", true);
			BufferedWriter bufferWriter = new BufferedWriter(LogWriter);

			Date date = new Date();
			bufferWriter.write(date.toString() + ": " + Login.loginVar + "   "+ eventName);
			bufferWriter.newLine();
			bufferWriter.close();
		}

		catch (IOException ex) {
			System.out.println(ex.getMessage());
			JOptionPane pane = new JOptionPane();
			JOptionPane.showMessageDialog(pane, "Логирование не удалось");
			pane.setSize(100, 100);
			pane.setVisible(false);
		}
	}
}