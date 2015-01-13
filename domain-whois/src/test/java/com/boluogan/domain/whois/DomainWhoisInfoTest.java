package com.boluogan.domain.whois;

import com.boluogan.domain.whois.utils.DomainWhoisInfoBuilder;
import org.junit.Test;

/**
 * Created by boluogan.com on 12/9/14.
 */
public class DomainWhoisInfoTest {
    @Test
    public void factory() throws Exception {
        DomainWhoisInfo domainWhoisInfo= DomainWhoisInfoBuilder.build("yuepian.com");
        System.out.println(domainWhoisInfo);

        domainWhoisInfo= DomainWhoisInfoBuilder.build("yuepian.com.cn");
        System.out.println(domainWhoisInfo);

        domainWhoisInfo= DomainWhoisInfoBuilder.build("yuepian");
        System.out.println(domainWhoisInfo);
    }
}
