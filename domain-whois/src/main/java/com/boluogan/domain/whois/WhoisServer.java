package com.boluogan.domain.whois;

import org.apache.commons.net.whois.WhoisClient;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

/**
 * Created by boluogan.com on 12/11/14.
 */
public class WhoisServer {
    private final static int DEFAULT_PORT=43;
    private String host;
    private int port;

    public WhoisServer(String host) {
        this(host,DEFAULT_PORT);
    }

    public WhoisServer(String host, int port) {
        this.host = host;
        this.port = port;
    }



    public String query(String domainname)   throws UnknownHostException, SocketException, IOException {

        WhoisClient whois = new WhoisClient();
        whois.setCharset(Charset.forName("UTF-8"));
        whois.connect(host, port);

        String content = whois.query(domainname);
        //System.out.println("-----------\n"+content+"\n-----------\n");

        whois.disconnect();



        return content;

    }

    public String toString(){
        String str = String.format("[host:%s,port:%d]",host,port) ;
        return str;
    }
}
