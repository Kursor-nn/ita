package com.kozachuk.ita.Configuration;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

/**
 * Created by alexanderkozachuk on 12.03.16.
 */
public class Configuration {
    public Configuration(String arguments[]){
        this.arguments = arguments;
    }

    public void parse() throws IOException {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(this.arguments);
            if( this.arguments.length == 0 ) {
                throw new CmdLineException(parser, "No argument is given");
            }

        } catch( CmdLineException e ) {
            System.err.println(e.getMessage());
            return;
        }
    }

    public Integer getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }

    public String getTypeApp() {
        return typeApp;
    }

    public String getDatabaseUsername() {
        return databaseUsername;
    }

    public void setDatabaseUsername(String databaseUsername) {
        this.databaseUsername = databaseUsername;
    }

    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public String getDatabaseHost() {
        return databaseHost;
    }

    public String getDatabasePort() {
        return databasePort;
    }

    public void setDatabaseHost(String databaseHost) {
        this.databaseHost = databaseHost;
    }

    public void setDatabasePort(String databasePort) {
        this.databasePort = databasePort;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    @Option(name="-N", usage="database name. default value : ita")
    private String databaseName = "ita";

    @Option(name="-H", usage="database host-name. default value : localhost")
    private String databaseHost = "jdbc:postgresql://localhost";

    @Option(name="--port", usage="database host-name. default value : 5442")
    private String databasePort = "5442";

    @Option(name="-U", usage="database host-user. default value : postgres")
    private String databaseUsername = "postgres";

    @Option(name="-P", usage="database password. default value : 123123")
    private String databasePassword = "123123";

    @Option(name="-h", usage="host-name. default value : localhost")
    private String host = "localhost";

    @Option(name="-p", usage="port number. default value : 4555")
    private Integer port = 4555;

    @Option(name="-t", usage="Application mode : server/client. For example : -t=server/-t=client. Default value = server")
    private String typeApp = "server";

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("host : ", getHost())
                .append("port : ", getPort())
                .append("type : ", getTypeApp())
                .toString();
    }

    String[] arguments;
}
