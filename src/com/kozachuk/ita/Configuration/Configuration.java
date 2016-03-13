package com.kozachuk.ita.Configuration;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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


    String happy = "───────────────────────────────────────\n" + "───▐▀▄───────▄▀▌───▄▄▄▄▄▄▄─────────────\n" + "───▌▒▒▀▄▄▄▄▄▀▒▒▐▄▀▀▒██▒██▒▀▀▄──────────\n" + "──▐▒▒▒▒▀▒▀▒▀▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▀▄────────\n" + "──▌▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▄▒▒▒▒▒▒▒▒▒▒▒▒▀▄──────\n" + "▀█▒▒▒█▌▒▒█▒▒▐█▒▒▒▀▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▌─────\n" + "▀▌▒▒▒▒▒▒▀▒▀▒▒▒▒▒▒▀▀▒▒▒▒▒▒▒▒▒▒▒▒▒▒▐───▄▄\n" + "▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▌▄█▒█\n" + "▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒█▀─\n" + "▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▀───\n" + "▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▌────\n" + "─▌▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▐─────\n" + "─▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▌─────\n" + "──▌▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▐──────\n" + "──▐▄▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▄▌──────\n" + "────▀▄▄▀▀▀▀▀▄▄▀▀▀▀▀▀▀▄▄▀▀▀▀▀▄▄▀────────\n";

    String[] arguments;
}
