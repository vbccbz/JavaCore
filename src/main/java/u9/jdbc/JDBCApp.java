package u9.jdbc;

import java.sql.*;

public class JDBCApp {
  public static void main(String[] args) {
    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    long timer = 0;
    int[] results = null;
    System.out.println("HELLO");
    try {
      Class.forName("org.sqlite.JDBC");//isn't needed in new versions???
//          class JDBC implements Driver{
//            static {
//                try {
//                    java.sql.DriverManager.registerDriver(new JDBC());// DM is a singleton.
//                    java.sql.DriverManager.registerDriver(new org.sqlite.JDBC()); classpath from Maven allows to find org.sqlite.JDBC class
//                } catch (SQLException var1) {
//                    var1.printStackTrace();
//                }
//            }
//            ...
//          }
      connection = DriverManager.getConnection("jdbc:sqlite:main.db");
//          sqllite creates new db if there isn't
//          java.util.Properties properties = new java.util.Properties();
//          properties.setProperty("user", "me");
//          properties.setProperty("password", "1234");
//          connection = DriverManager.getConnection("jdbc:sqlite:main.db", properties);
//          "jdbc:posgresql://localhost:5432/jc_student","login","password"
      statement = connection.createStatement();
//            query = "CREATE DATABASE lol.db;";// doesn't work this way...
      int r = 0;
      r = statement.executeUpdate("INSERT INTO students(name, score) VALUES ('Alice', 111)");
      r = statement.executeUpdate("INSERT INTO students(name, score) VALUES ('Alice', 111);INSERT INTO students(name, score) VALUES ('Alice', 111);");
      preparedStatement = connection.prepareStatement("INSERT INTO students(name, score) VALUES(?,?)");
      //careful: doesn't check types corresponding, SQL easy load String into INTEGER...
      preparedStatement.setString(1, "Bob");
      preparedStatement.setInt(2, 666);
      //preparedStatement.setObject(); ???
      preparedStatement.execute();
      statement.executeUpdate("DELETE FROM students;");

      connection.setAutoCommit(true);// by default; setAutoCommit(true) does commit and does set this connection's auto-commit mode
      timer = System.currentTimeMillis();
      for (int i = 0; i < 1000; ++i) {
        statement.executeUpdate("INSERT INTO students(name, score) VALUES ('XXX', 111)");
      }
      System.out.println(System.currentTimeMillis() - timer + " statement ac true ");

      connection.setAutoCommit(false);
      timer = System.currentTimeMillis();
      for (int i = 0; i < 1000; ++i) {
        statement.executeUpdate("INSERT INTO students(name, score) VALUES ('XXX', 111)");
      }
      connection.commit();
      System.out.println(System.currentTimeMillis() - timer + " statement ac false ");

      connection.setAutoCommit(false);
      timer = System.currentTimeMillis();
      for (int i = 0; i < 1000; ++i) {
        statement.addBatch("INSERT INTO students(name, score) VALUES ('XXX', 111)");
      }
      connection.setAutoCommit(true);
      results = statement.executeBatch();
      System.out.println(System.currentTimeMillis() - timer + " statement batch ac true  ");

      connection.setAutoCommit(true);
      timer = System.currentTimeMillis();
      for (int i = 0; i < 1000; ++i) {
        statement.addBatch("INSERT INTO students(name, score) VALUES ('XXX', 111)");
      }
      connection.setAutoCommit(false);
      results = statement.executeBatch();
      System.out.println(System.currentTimeMillis() - timer + " statement batch ac false  ");

      connection.setAutoCommit(true);
      timer = System.currentTimeMillis();
      for (int i = 0; i < 1000; ++i) {
        preparedStatement.setString(1, "partofbatch");
        preparedStatement.setInt(2, i);
        preparedStatement.executeUpdate();
      }
      System.out.println(System.currentTimeMillis() - timer + " prp statement ac true  ");

      connection.setAutoCommit(false);
      timer = System.currentTimeMillis();
      for (int i = 0; i < 1000; ++i) {
        preparedStatement.setString(1, "partofbatch");
        preparedStatement.setInt(2, i);
        preparedStatement.executeUpdate();
      }
      connection.commit();
      System.out.println(System.currentTimeMillis() - timer + " prp statement ac false  ");

      connection.setAutoCommit(true);
      timer = System.currentTimeMillis();
      for (int i = 0; i < 1000; ++i) {
        preparedStatement.setString(1, "partofbatch");
        preparedStatement.setInt(2, i);
        preparedStatement.addBatch();
      }
      connection.setAutoCommit(false);
      results = preparedStatement.executeBatch();// is needed commit(), results becomes BEFORE end of transaction
      connection.commit();
      System.out.println(System.currentTimeMillis() - timer + " prp statement batch ac false");
      statement.executeUpdate("DELETE FROM students;");
      statement.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob1', 80);");
      Savepoint sp1 = connection.setSavepoint();// and disables auto commit mode
      statement.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob2', 80);");
      connection.rollback(sp1);
      statement.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob3', 80);");
      connection.commit();//all savepoints are cleared
      ResultSet rs = statement.executeQuery("SELECT (id, name, score) FROM students;");
      while (rs.next()) {
        System.out.println(rs.getInt(1) + " " + rs.getString("name") + " " + rs.getInt("score"));
      }
//        } catch (SQLException | ClassNotFoundException exception) {// в случае static disconnect() вместо двух разных удобнее бросать один
    } catch (SQLException exception) {
      exception.printStackTrace();
    } catch (ClassNotFoundException exception) {
      exception.printStackTrace();
    } finally {
      if (statement != null) {
        try {
          statement.close();// better to be before connection.close()
        } catch (SQLException exception) {
          exception.printStackTrace();
        }
      }
      if (preparedStatement != null) {
        try {
          preparedStatement.close();// do not mistake with autoclose feature ;-)
        } catch (SQLException exception) {
          exception.printStackTrace();
        }
      }
      if (connection != null) {
        try {
          connection.close();
        } catch (SQLException exception) {
          exception.printStackTrace();
        }
      }
    }
  }
}
