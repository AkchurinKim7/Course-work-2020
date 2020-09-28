/**
 * @author ������ 1425���, ������� ���, ������� ������
 */

import java.sql.SQLException;
import java.sql.*;

/// ��������� ��������
public class QueryCreateRequest {
    public static void queryCreateRequest(String query, String querySelect, String successful_message, String unsuccessful_message) {
                try {
                    Statement stmt = ConnectionDB.connectionDB(ConnectionDB.passServer);
                    stmt.executeUpdate(query);
                    ResultSet resultSet = stmt.executeQuery(querySelect);
                    boolean ERRORSUCCESS = false;
                    while (resultSet.next()) {
                        ERRORSUCCESS = true;
                    }

                    ///����� ��������� �� ��� ���
                    if (ERRORSUCCESS) {
                        MessagePane.messagePane(successful_message);
                    } else {
                        MessagePane.messagePane(unsuccessful_message);
                    }

                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
    }
}