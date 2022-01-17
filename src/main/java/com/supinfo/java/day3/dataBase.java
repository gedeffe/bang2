package com.supinfo.java.day3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class dataBase {
    public static void main(String [] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");

        String url = "jdbc::h2:./build.h2";

        try (Connection connection = DriverManager.getConnection(url, "", "")){
            try(Statement statement = connection.createStatement()){
                //sql script String
                //statement.executeUpdate(Var)
                String sqlCreateTableStudent = "CREATE TABLE IF NOT EXISTS student (" +
                        "id_booster INT PRIMARY KEY NOT NULL," +
                        "name VARCHAR(100)," +
                        "level_of_study VARCHAR(100)" +
                        ")";

                String sqlCreateTableCredential = "CREATE TABLE IF NOT EXISTS credential (" +
                        "id_credential INT PRIMARY KEY NOT NULL" +
                        "check VARCHAR(50)" +
                        ")";

                String sqlCreateTableStudentCredential = "CREATE TABLE IF NOT EXISTS student_credential (" +
                        "id_booster INT(20)," +
                        "id_credential INT(20)" +
                        ")";

                statement.executeUpdate(sqlCreateTableStudent);
                statement.executeUpdate(sqlCreateTableCredential);
                statement.executeUpdate(sqlCreateTableStudentCredential);

            }

            try(Statement statement = connection.createStatement()){
                String sqlJoinStudentCredentialWithStudent = "SELECT * FROM student_credential" +
                        "INNER JOIN student ON" +
                        "student_credential.id_booster = student.id_booster";

                String sqlJoinStudentCredentialWithCredential = "SELECT * FROM student_credential" +
                        "INNER JOIN credential ON" +
                        "student_credential.id_credential = credential.id_credential";

                statement.executeUpdate(sqlJoinStudentCredentialWithStudent);
                statement.executeUpdate(sqlJoinStudentCredentialWithCredential);

            }

            try(Statement statement = connection.createStatement()){
                String sqlInsertStudent = "INSERT TO student (id_booster, name, level_of_study)" +
                        "VALUES" +
                        "( 1, 'Sarah', 'BENG2')," +
                        "( 2, 'Olivier', 'BENG2')," +
                        "( 3, 'Charly', 'BENG2')";

                String sqlInsertCredential = "INSERT TO credential(id_credential, check)" +
                        "VALUES" +
                        "( 1, 'Check')," +
                        "( 2, 'No-check')";

                String sqlInsertStudentCredential = "INSERT TO (id_booster, id_credential)" +
                        "VALUES" +
                        "( 1, 1)," +
                        "( 2, 2), " +
                        "( 3, 1)";

                statement.executeUpdate(sqlInsertStudent);
                statement.executeUpdate(sqlInsertCredential);
                statement.executeUpdate(sqlInsertStudentCredential);
            }



        }





    }
}
