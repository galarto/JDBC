package database;

public class SqlRequests {
    public static final String CREATE_RESERVATIONS_TABLE =
            "CREATE TABLE IF NOT EXISTS GUESTS (" +
            "ID INT PRIMARY KEY," +
            "TABLE_ID INT REFERENCES TABLES (ID) ON DELETE CASCADE ON UPDATE CASCADE," +
            "GUEST_ID INT REFERENCES GUESTS (ID) ON DELETE CASCADE ON UPDATE CASCADE," +
            "RESERVATION_DATE_START TIMESTAMP," +
            "RESERVATION_DATE_END TIMESTAMP);";

    public static final String CREATE_TABLES_TABLE =
            "CREATE TABLE IF NOT EXISTS TABLE (" +
            "ID SERIAL PRIMARY KEY," +
            "CAPACITY INT," +
            "NUMBER INT);";

    public static final String CREATE_GUESTS_TABLE =
            "CREATE TABLE IF NOT EXISTS (" +
            "ID SERIAL PRIMARY KEY," +
            "NAME VARCHAR(30) NOT NULL," +
            "SURNAME VARCHAR(30) NOT NULL," +
            "PHONE_NUMBER VARCHAR(20) NOT NULL);";

    public static final String DROP_RESERVATIONS_TABLE =
            "DROP TABLE IF EXISTS RESERVATIONS";

    public static final String DROP_TABLES_TABLE =
            "DROP TABLE IF EXISTS TABLES CASCADE";

    public static final String DROP_GUESTS_TABLE =
            "DROP TABLE IF EXISTS GUESTS CASCADE";

    public static final String ADD_RESERVATION =
            "INSERT INTO " +
                    "RESERVATIONS(RESERVATION_DATE_START, RESERVATION_DATE_END, " +
                    "TABLE_ID, GUEST_ID, NUMBER_OF_PERSONS)\r\n" +
                    "VALUES(?,?,?,?,?)";

    public static final String ADD_TABLE =
            "INSERT INTO " +
                    "TABLES(CAPACITY, NUMBER)\r\n" +
                    "VALUES(?,?,?)";

    public static final String ADD_GUEST =
            "INSERT INTO " +
                    "GUESTS(NAME, SURNAME, PHONE_NUMBER)\r\n" +
                    "VALUES(?,?,?)";

    public static final String FREE_TABLES =
            "select * from tables\n" +
                    "where number not in(select tables.number from reservations inner join tables on reservations.table_id = tables.id\n" +
                    "\t\t\t\t\twhere (reservations.reservation_date_start <= ? \n" +
                    "\t\t\t\t\t\t\tand reservations.reservation_date_end >= ?)\n" +
                    "\t\t\t\t\t\t\tor (reservations.reservation_date_start <= ? \n" +
                    "\t\t\t\t\t\t\tand reservations.reservation_date_end >= ?))";

}

