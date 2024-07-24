import com.phonebook.data.DbData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.*;

public class SqlConnectorTest {

    Connection connection;

    @BeforeMethod
    public void loadDb() {

        try {
            //load mysql jdbc driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //create connection to DB
            connection = DriverManager.getConnection(DbData.dbUrl,DbData.username,DbData.password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getDataTableTest() {
        try {
            //create statement Object
            Statement statement = connection.createStatement();
            //execute SQl query, store result in resultSet
            ResultSet resultSet = statement.executeQuery(DbData.query);

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterMethod
    public void closeDb() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deleteRowTest() {
        try {
            //modify statement object
            PreparedStatement statement = connection.prepareStatement(DbData.query1);
            statement.executeUpdate();

            ResultSet resultSet = statement.executeQuery(DbData.query);

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
