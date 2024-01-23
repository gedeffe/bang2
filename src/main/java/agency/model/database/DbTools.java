package agency.model.database;

import liquibase.Liquibase;
import liquibase.Scope;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DbTools {
    private String url = "jdbc:h2:file:./build/tripagency;AUTO_SERVER=true";
    private String user = "sa";
    private String passwd = "";

    public void initDatabase() {

        Map<String, Object> config = new HashMap<>();
        config.put("liquibase.licenseKey", "YOUR_PRO_KEY");

        try {
            Scope.child(config, () -> {
                Connection connection = this.getConnection();
                Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));

                Liquibase liquibase = new Liquibase("changelog.xml", new ClassLoaderResourceAccessor(), database);
                liquibase.update();
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, passwd);

    }
}
