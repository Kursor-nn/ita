import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.*;

import com.kozachuk.ita.Configuration.Configuration;

/**
 * Created by alexanderkozachuk on 15.03.16.
 */

public class ConfigurationTest {
    private String databaseName = "ita";
    private String databaseHost = "jdbc:postgresql://localhost";
    private String databasePort = "5442";
    private String databaseUsername = "postgres";
    private String databasePassword = "123123";
    private String host = "localhost";
    private Integer port = 4555;
    private String typeApp = "server";

    @Test
    public void testDefaultValues() {
        String[] args = null;
        Configuration conf = new Configuration(args);

        assertEquals("Database default : "          + databaseName, databaseName, conf.getDatabaseName());
        assertEquals("Database default:"            + databaseName, databaseName, conf.getDatabaseName());
        assertEquals("Database host default :"      + databaseHost, databaseHost, conf.getDatabaseHost());
        assertEquals("Database Username  default :" + databaseUsername, databaseUsername, conf.getDatabaseUsername());
        assertEquals("Database password  default :" + databasePassword, databasePassword, conf.getDatabasePassword());
        assertEquals("Application host default : "  + host,    host, conf.getHost());
        assertEquals("Application type default : "  + typeApp, typeApp, conf.getTypeApp());
        assertEquals("Application port number default : " + port, port, conf.getPort());
    }

}
