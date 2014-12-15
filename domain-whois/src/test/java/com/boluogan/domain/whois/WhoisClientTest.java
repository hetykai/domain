package com.boluogan.domain.whois;

import org.apache.commons.net.whois.WhoisClient;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by yan on 12/9/14.
 */
public class WhoisClientTest {
    @Test
    public void query(){
        WhoisClient whois;

        whois = new WhoisClient();

        try {
            whois.connect(WhoisClient.DEFAULT_HOST);
            //whois.connect("whois.cnnic.cn");
            System.out.println(whois.query("yuepian.com"));
            //System.out.println(whois.query("x.com.cn"));
            whois.disconnect();
        } catch(IOException e) {
            System.err.println("Error I/O exception: " + e.getMessage());
            return;
        }

    }
}
