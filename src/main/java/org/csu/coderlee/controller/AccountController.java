package org.csu.coderlee.controller;

import org.csu.coderlee.domain.Account;
import org.csu.coderlee.domain.Post;
import org.csu.coderlee.service.AccountService;
import org.csu.coderlee.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author by bixi.lx
 * @created on 2018 03 14 16:20
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Resource
    AccountService accountService;
    @Resource
    PostService postService;

    @RequestMapping("/info")
    @ResponseBody
    public Object accountInfo() {
        Account account =  accountService.info();
        return account;
    }

    @RequestMapping("/post")
    @ResponseBody
    public Object postInfo() {
         Post post =  postService.get();
         return post;
    }
}
