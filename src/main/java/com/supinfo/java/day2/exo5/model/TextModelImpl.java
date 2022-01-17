package com.supinfo.java.day2.exo5.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Concrete class of data layer (model).
 */
public class TextModelImpl implements TextModel {

    private final List<TextEvents> listeners = new ArrayList<>();
    private final Connection connection;

    /**
     * Default constructor to initialize connection to database
     */
    public TextModelImpl() throws ClassNotFoundException, SQLException {
        // load H2 driver
        Class.forName("org.h2.Driver");
        // Start database connection
        String url = "jdbc:h2:./build/h2";
        this.connection = DriverManager.getConnection(url, "sa", "");
        try (Statement statement = connection.createStatement()) {
            // create our table structure (if needed) to store our text
            String sqlCreateCommand = "CREATE TABLE IF NOT EXISTS supinfo (text VARCHAR(1500) NOT NULL);";
            statement.executeUpdate(sqlCreateCommand);
            ResultSet resultSet = statement.executeQuery("SELECT text FROM supinfo;");
            if (!resultSet.next()) {
                // insert a default value
                String sqlInsertCommand = "INSERT INTO supinfo VALUES ('initial value');";
                statement.executeUpdate(sqlInsertCommand);
            }
        }
    }

    public static void createNewTableStudents() {

        String sql = "CREATE TABLE IF NOT EXISTS students (\n"
                + "	id_booster integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	level text NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createNewTableCredentials() {

        String sql = "CREATE TABLE IF NOT EXISTS credentials (\n"
                + "	id_credential integer PRIMARY KEY,\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createNewTableStudentCredentials() {

        String sql = "CREATE TABLE IF NOT EXISTS student_credentials (\n"
                + "	id_booster integer ,\n"
                + "	id_credential integer,\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void updateTextData(final String text) {
        // update our persistence layer
        this.writeTextToDatabase(text);
        // send a events to update listeners
        this.listeners.forEach((listener) -> listener.notifyTextModified(text));
    }

    private void writeTextToDatabase(String text) {
        try (Statement statement = this.connection.createStatement()) {
            statement.executeUpdate("UPDATE supinfo SET text='" + text +"';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getTextData() {
        String text = "";
        try (Statement statement = this.connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT text FROM supinfo;");
            while(resultSet.next()) {
                text = resultSet.getString("text");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }

    @Override
    public void register(final TextEvents listener) {
        if (!this.listeners.contains(listener)) {
            this.listeners.add(listener);
        }
    }

    @Override
    public void unregister(final TextEvents listener) {
        this.listeners.remove(listener);
    }
}
