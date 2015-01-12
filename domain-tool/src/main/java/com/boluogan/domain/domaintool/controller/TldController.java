package com.boluogan.domain.domaintool.controller;

import com.boluogan.domain.whois.Nics;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by boluogan.com on 12/17/14.
 */

@RequestMapping("/tlds")
@RestController
public class TldController {
    @RequestMapping("/root/all")
    public List<String> rootTlds(){
        return Nics.rootTlds();
    }

    @RequestMapping("/root/parseable")
    public List<String> parseableRootTlds(){
        return Nics.parseableRootTlds();
    }
}
