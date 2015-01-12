package com.boluogan.domain.whois;

import org.junit.Test;

/**
 * Created by boluogan.com on 1/10/15.
 */
public class ClassResourceTest {
    @Test
    public void path(){
        System.out.printf("getResource(\"\"):%s\n",ClassResourceTest.class.getResource(""));
        System.out.printf("getResource(\"/\"):%s\n",ClassResourceTest.class.getResource("/"));
        System.out.printf("System.getProperty(\"user.dir\"):%s\n",System.getProperty("user.dir"));
        //System.getProperty("user.dir")
    }
}
