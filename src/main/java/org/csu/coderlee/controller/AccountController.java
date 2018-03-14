package org.csu.coderlee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author by bixi.lx
 * @created on 2018 03 14 16:20
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @RequestMapping("/info")
    @ResponseBody
    public Object accountInfo() {
        return null;
    }
}
