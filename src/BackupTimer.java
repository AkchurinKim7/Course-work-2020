/**
 * @author ������ 1425���, ������� ���, ������� ������
 */

import java.util.TimerTask;

/// ������ ��� ����������
 public class BackupTimer extends TimerTask{

	public void run() {
		if (BackupPlane.BACKUP_ON){
			QueryBackup.queryBackup(true);
			Log.writeLog("��������� �����������");
		}		
	}
}