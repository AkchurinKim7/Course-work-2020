/**
 * @author Группа 1425ИСО, Акчурин Ким, Уварков Сергей
 */

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import java.sql.*;

/// создание базы данных
public class QueryCreateDB {
    public static void queryCreateDB() throws SQLException {
        String[] createBD = new String[22];
        createBD[0] = "CREATE database IF NOT EXISTS appaccosys ;\n";

        createBD[1] = "CREATE TABLE IF NOT EXISTS appaccosys.table_role (\n" +
                "  id_role INT NOT NULL AUTO_INCREMENT,\n" +
                "  roles VARCHAR(20) NOT NULL,\n" +
                "  PRIMARY KEY (id_role),\n" +
                "  UNIQUE INDEX role_UNIQUE (roles ASC) VISIBLE,\n" +
                "  UNIQUE INDEX id_role_UNIQUE (id_role ASC) VISIBLE)\n" +
                "ENGINE = InnoDB;";

        createBD[2] = "CREATE TABLE IF NOT EXISTS appaccosys.table_payment (\n" +
                "  id_payment INT NOT NULL AUTO_INCREMENT,\n" +
                "  payment VARCHAR(20) NOT NULL,\n" +
                "  PRIMARY KEY (id_payment),\n" +
                "  UNIQUE INDEX id_payment_UNIQUE (id_payment ASC) VISIBLE,\n" +
                "  UNIQUE INDEX payment_UNIQUE (payment ASC) VISIBLE)\n" +
                "ENGINE = InnoDB;";

        createBD[3] = "CREATE TABLE IF NOT EXISTS appaccosys.table_invitations (\n" +
                "  id_invitations INT NOT NULL AUTO_INCREMENT,\n" +
                "  invitation VARCHAR(25) NOT NULL,\n" +
                "  PRIMARY KEY (id_invitations),\n" +
                "  UNIQUE INDEX invitation_UNIQUE (invitation ASC) VISIBLE,\n" +
                "  UNIQUE INDEX id_invitations_UNIQUE (id_invitations ASC) VISIBLE)\n" +
                "ENGINE = InnoDB;";

        createBD[4] = "CREATE TABLE IF NOT EXISTS appaccosys.table_events (\n" +
                "  id_events INT NOT NULL AUTO_INCREMENT,\n" +
                "  events VARCHAR(25) NOT NULL,\n" +
                "  PRIMARY KEY (id_events),\n" +
                "  UNIQUE INDEX events_UNIQUE (events ASC) VISIBLE,\n" +
                "  UNIQUE INDEX id_events_UNIQUE (id_events ASC) VISIBLE)\n" +
                "ENGINE = InnoDB;";

        createBD[5] = "CREATE TABLE IF NOT EXISTS appaccosys.table_state (\n" +
                "  id_state INT NOT NULL AUTO_INCREMENT,\n" +
                "  state VARCHAR(30) NOT NULL,\n" +
                "  UNIQUE INDEX state_UNIQUE (state ASC) VISIBLE,\n" +
                "  PRIMARY KEY (id_state))\n" +
                "ENGINE = InnoDB;";

        createBD[6] = "CREATE TABLE IF NOT EXISTS appaccosys.table_application_forms (\n" +
                "  id_participant INT NOT NULL AUTO_INCREMENT,\n" +
                "  surname VARCHAR(64) NOT NULL,\n" +
                "  first_name VARCHAR(64) NOT NULL,\n" +
                "  patronymic VARCHAR(64) NOT NULL,\n" +
                "  type_member_role VARCHAR(20) NOT NULL,\n" +
                "  type_payment VARCHAR(20) NOT NULL,\n" +
                "  type_invitation VARCHAR(25) NOT NULL,\n" +
                "  type_event VARCHAR(25) NOT NULL,\n" +
                "  type_state VARCHAR(45) NOT NULL,\n" +
                "  email VARCHAR(45) NOT NULL,\n" +
                "  PRIMARY KEY (id_participant),\n" +
                "  UNIQUE INDEX id_participant_UNIQUE (id_participant ASC) VISIBLE,\n" +
                "  INDEX type_member_role_idx (type_member_role ASC) VISIBLE,\n" +
                "  INDEX payment_idx (type_payment ASC) VISIBLE,\n" +
                "  INDEX invitations_idx (type_invitation ASC) VISIBLE,\n" +
                "  INDEX event_idx (type_event ASC) VISIBLE,\n" +
                "  INDEX state_idx (type_state ASC) VISIBLE,\n" +
                "  CONSTRAINT type_member_role\n" +
                "    FOREIGN KEY (type_member_role)\n" +
                "    REFERENCES appaccosys.table_role (roles)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION,\n" +
                "  CONSTRAINT payment\n" +
                "    FOREIGN KEY (type_payment)\n" +
                "    REFERENCES appaccosys.table_payment (payment)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION,\n" +
                "  CONSTRAINT invitations\n" +
                "    FOREIGN KEY (type_invitation)\n" +
                "    REFERENCES appaccosys.table_invitations (invitation)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION,\n" +
                "  CONSTRAINT event\n" +
                "    FOREIGN KEY (type_event)\n" +
                "    REFERENCES appaccosys.table_events (events)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION,\n" +
                "  CONSTRAINT state\n" +
                "    FOREIGN KEY (type_state)\n" +
                "    REFERENCES appaccosys.table_state (state)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION)\n" +
                "ENGINE = InnoDB;";

        createBD[7] = "CREATE TABLE IF NOT EXISTS appaccosys.table_users_role (\n" +
                "  id_users_role INT NOT NULL AUTO_INCREMENT,\n" +
                "  users_role VARCHAR(16) NULL,\n" +
                "  PRIMARY KEY (id_users_role),\n" +
                "  UNIQUE INDEX id_users_role_UNIQUE (id_users_role ASC) VISIBLE,\n" +
                "  UNIQUE INDEX users_role_UNIQUE (users_role ASC) VISIBLE)\n" +
                "  ENGINE = InnoDB;";

        createBD[8] = "CREATE TABLE IF NOT EXISTS appaccosys.users (\n" +
                "  id_users INT NOT NULL AUTO_INCREMENT,\n" +
                "  login VARCHAR(64) NOT NULL,\n" +
                "  password VARCHAR(64) NOT NULL,\n" +
                "  role VARCHAR(16) NOT NULL,\n" +
                "  PRIMARY KEY (id_users),\n" +
                "  UNIQUE INDEX id_users_UNIQUE (id_users ASC) VISIBLE,\n" +
                "  INDEX table_users_role_idx (role ASC) VISIBLE,\n" +
                "  CONSTRAINT table_users_role\n" +
                "  FOREIGN KEY (role)\n" +
                "  REFERENCES appaccosys.table_users_role (users_role)\n" +
                "  ON DELETE NO ACTION\n" +
                "  ON UPDATE NO ACTION)\n" +
                "  ENGINE = InnoDB;";


        createBD[9] = "INSERT INTO appaccosys.table_invitations (invitation) VALUES ('по приглашению');";
        createBD[10] = "INSERT INTO appaccosys.table_invitations (invitation) VALUES ('без приглашения');";
        createBD[11] = "INSERT INTO appaccosys.table_payment (payment) VALUES ('бесплатно');";
        createBD[12] = "INSERT INTO appaccosys.table_payment (payment) VALUES ('платно');";
        createBD[13] = "INSERT INTO appaccosys.table_role (roles) VALUES ('спикер');";
        createBD[14] = "INSERT INTO appaccosys.table_role (roles) VALUES ('организатор');";
        createBD[15] = "INSERT INTO appaccosys.table_role (roles) VALUES ('волонтер');";
        createBD[16] = "INSERT INTO appaccosys.table_role (roles) VALUES ('посетитель');";
        createBD[17] = "INSERT INTO appaccosys.table_state (state) VALUES ('в ожидании');";
        createBD[18] = "INSERT INTO appaccosys.table_state (state) VALUES ('одобрено');";
        createBD[19] = "INSERT INTO appaccosys.table_state (state) VALUES ('отклонено');";
        createBD[20] = "INSERT INTO appaccosys.table_users_role (users_role) VALUES ('admin');";
        createBD[21] = "INSERT INTO appaccosys.table_users_role (users_role) VALUES ('moder');";


        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        Statement stmt = ConnectionDB.connectionDB(ConnectionDB.passServer);
        try {
            System.out.println("Подключение установлено");

            for (String queryCreate : createBD) {
                stmt.executeUpdate(queryCreate);
            }
            System.out.println("Удалось создать БД");

        } catch (SQLException throwable) {
            throwable.printStackTrace();
            System.out.println("Не удалось подключиться к базе.");
        }

        stmt.close();
    }

    public static void defaultDB() throws SQLException {
        String[] createDefaultBD = new String[]{
                "INSERT INTO appaccosys.table_events (events) VALUES ('конференция');",
                "INSERT INTO appaccosys.table_events (events) VALUES ('воркшоп');",
                "INSERT INTO appaccosys.table_events (events) VALUES ('нетворкинг');",
                "INSERT INTO appaccosys.table_events (events) VALUES ('круглый стол');",
                "INSERT INTO appaccosys.users (login, password, role) VALUES ('admin', '21232f297a57a5a743894a0e4a801fc3', 'admin');",
                "INSERT INTO appaccosys.users (login, password, role) VALUES ('moder', '9ab97e0958c6c98c44319b8d06b29c94', 'moder');"
        };

        Statement stmt = ConnectionDB.connectionDB(ConnectionDB.passServer);
        for (String queryCreate : createDefaultBD) {
            stmt.executeUpdate(queryCreate);
        }
    }
}