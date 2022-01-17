package com.supinfo.java.day3;

import java.sql.*;

/**
 * Create a Mysql SupStudent database containing 3 tables:
 * student (id_booster, name, level of study)
 * credential (id_credential) // possible values : read/write/update/delete
 * student_credential (id_booster, id_credential)
 * <p>
 * First, make sure that the connection with the JDBC driver is possible. This statement is protected by a try statement.
 * Class.forName ("com.mysql.jdbc.Driver");
 * System.out.println ("The JDBC MySQL driver has been loaded");
 * <p>
 * In the second step, establish a connection with the  database using getConnection function with ("jdbc: mysql: // localhost / xxxx", "root", "");
 * <p>
 * Thirdly create a Statement object. Through it, you can execute SQL commands to query the database and obtain the corresponding results.
 * <p>
 * In a fourth step, we define an SQL query by creating a ResultSet object from the Statement object defined in the previous step:
 * <p>
 * In a fifth step, we retrieve the metadata from the table.
 */
public class JdbcRoots {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");

        // Start database connection
        String url = "jdbc:h2:./build/roots";
        try (Connection connection = DriverManager.getConnection(url, "sa", "")) {
            try (Statement statement = connection.createStatement()) {
                // create our table structure (if needed) to store our text
                String studentsTable = "CREATE TABLE IF NOT EXISTS student (id_booster INT NOT NULL, name VARCHAR(50) NOT NULL, level_of_study VARCHAR(50) );";
                statement.executeUpdate(studentsTable);

                String credentialTable = "CREATE TABLE IF NOT EXISTS credential (id_credential VARCHAR(50) NOT NULL check (id_credential in ('read', 'write', 'update', 'delete')));";
                statement.executeUpdate(credentialTable);

                String studentsCredentialTable = "CREATE TABLE IF NOT EXISTS student_credential (id_booster INT NOT NULL, id_credential VARCHAR(50) NOT NULL );";
                statement.executeUpdate(studentsCredentialTable);
            }

            boolean init = false;
            if (init) {
                try (Statement statement = connection.createStatement()) {
                    // insert students ...
                    String insertStudents = "INSERT INTO student VALUES (1, 'Charly', 'Supinfo 2'), (2, 'Sarah', 'Supinfo 2'), (3, 'Olivier', 'Supinfo 2');";
                    statement.executeUpdate(insertStudents);
                    String insertCredential = "INSERT INTO credential VALUES ('read'), ('write');";
                    statement.executeUpdate(insertCredential);
                    String insertMapping = "INSERT INTO student_credential VALUES (1, 'read');";
                    statement.executeUpdate(insertMapping);
                }
            }

            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM student;");
                while (resultSet.next()) {
                    int id_booster = resultSet.getInt("id_booster");
                    String studentName = resultSet.getString("name");
                    String level = resultSet.getString("level_of_study");

                    System.out.println("Student found is: " + studentName + " at grade " + level + " for identifier " + id_booster);
                }
                System.out.println("Metadata are:");
                System.out.println(resultSet.getMetaData());
                System.out.println(resultSet.getMetaData().getColumnName(1));
                System.out.println(resultSet.getMetaData().getColumnName(2));
                System.out.println(resultSet.getMetaData().getColumnName(3));
            }
        }
    }
}
