/**
 * @author Группа 1425ИСО, Акчурин Ким, Уварков Сергей
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/// Добавление элементов из бд в массив для ComboBox
public class AddItemFromDB {
    public static String[] addItemFromDB(String query) {

        Statement stmt = ConnectionDB.connectionDB(ConnectionDB.passServer);
        assert stmt != null;

        ArrayList<String> items = new ArrayList();
        ResultSet result;
        try {
            result = stmt.executeQuery(query);
            while (result.next()) {
                items.add(result.getString(1));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return items.toArray(new String[0]);
    }
}