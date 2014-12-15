package com.boluogan.domain.whois;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by boluogan.com on 12/11/14.
 */
public class NicsTest {
    @Test
    public void run() throws Exception {
        //System.out.println(Nics.servers());

        //System.out.println(Nics.selectServer("com"));
        WhoisServer whoisServer = Nics.selectServer("cn");
        whoisServer.query("yuepian.com");
        //System.out.println(whoisServer.query("yuepian.cn"));
    }
}
