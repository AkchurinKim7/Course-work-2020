/**
 * @author Группа 1425ИСО, Акчурин Ким, Уварков Сергей
 */

import java.util.TimerTask;

/// таймер для автобэкапа
 public class BackupTimer extends TimerTask{

	public void run() {
		if (BackupPlane.BACKUP_ON){
			QueryBackup.queryBackup(true);
			Log.writeLog("Резервное копирование");
		}		
	}
}