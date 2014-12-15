package com.boluogan.domain.whois;

/**
 * Created by boluogan.com on 12/11/14.
 */
public enum ConfigFile{
    DIR("config"),
    GTLDS("gtlds.txt"),
    CCTLDS("cctlds.txt"),
    ALLCCTLDS("all_cctlds.txt"),
    NICS("nic.properties"),
    REGISTERED_TAGS("registered_tags.txt"),
    UNREGISTERED_TAGS("unregistered_tags.txt"),
    PRESERVED_TAGS("preserved_tags.txt"),
    ;

    private String filename;

    public String getFilename() {
        return filename;
    }

    ConfigFile(String filename) {
        this.filename = filename;
    }
}
