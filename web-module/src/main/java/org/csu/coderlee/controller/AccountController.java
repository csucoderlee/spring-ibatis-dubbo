package org.csu.coderlee.controller;

import org.csu.coderlee.domain.Account;
import org.csu.coderlee.domain.Page;
import org.csu.coderlee.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author by bixi.lx
 * @created on 2018 03 14 16:20
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Resource
    AccountService accountService;

    @RequestMapping("/info")
    @ResponseBody
    public Object accountInfo() {
        Account account =  accountService.info();
        return account;
    }

    @RequestMapping("/list")
    @ResponseBody
    public Object list(Page page) {
        List<Account> accountList = accountService.list(page);
        return accountList;
    }
}
