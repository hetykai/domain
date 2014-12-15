package com.boluogan.domain.whois;

import org.junit.Test;

/**
 * Created by boluogan.com on 12/9/14.
 */
public class DomainWhoisInfoTest {
    @Test
    public void factory() throws Exception {
        DomainWhoisInfo domainWhoisInfo= DomainWhoisInfo.factory("yuepian.com");
        System.out.println(domainWhoisInfo);

        domainWhoisInfo= DomainWhoisInfo.factory("yuepian.com.cn");
        System.out.println(domainWhoisInfo);

        domainWhoisInfo= DomainWhoisInfo.factory("yuepian");
        System.out.println(domainWhoisInfo);
    }
}
