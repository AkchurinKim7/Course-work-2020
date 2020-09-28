/**
 * @author ������ 1425���, ������� ���, ������� ������
 */

import java.sql.*;

/// �������������� ���� ������
public class QueryRecovery {
    public static boolean queryRecovery() {
        ///������ �� �������������� ���� �� ������� table_application_forms
        String queryRestTableApplicationForms = "LOAD DATA INFILE '" + QueryBackup.getSecurePath() +
                "aacs_table_application_forms.sql' INTO TABLE appaccosys.table_application_forms " +
                "FIELDS TERMINATED BY '\t' (id_participant,surname, first_name, " +
                "patronymic, type_member_role, type_payment, type_invitation, type_event, type_state, email)";
        String queryRestTableEvents = "LOAD DATA INFILE '" + QueryBackup.getSecurePath() +
                "aacs_table_events.sql' INTO TABLE appaccosys.table_events " +
                "FIELDS TERMINATED BY '\t' (id_events, events)";
        String queryRestUsers = "LOAD DATA INFILE '" + QueryBackup.getSecurePath() +
                "aacs_users.sql' INTO TABLE appaccosys.users " +
                "FIELDS TERMINATED BY '\t' (id_users," +
                "login, password, role)";

        Statement stmt = ConnectionDB.connectionDB(ConnectionDB.passServer);
        try {
            if (CheckDB.checkBackup()) {
                stmt.execute("drop database if exists appaccosys");
                QueryCreateDB.queryCreateDB();
                stmt.execute(queryRestUsers);
                stmt.execute(queryRestTableEvents);
                System.out.println("������� ������� �������");
                stmt.execute(queryRestTableApplicationForms);
                MessagePane.messagePane("������� ������������ ���� ������");
                Login.login();
                return(true);
            } else {
                MessagePane.messagePane("�� ������� ������������ ���� ������");
                return (false);
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
            MessagePane.messagePane("�� ������� ������������ ���� ������");
            return(false);
        }
    }
}