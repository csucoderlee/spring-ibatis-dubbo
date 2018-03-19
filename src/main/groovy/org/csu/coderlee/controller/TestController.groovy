package org.csu.coderlee.controller

import org.csu.coderlee.domain.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author by bixi.lx
 * @created on 2018 03 19 13:46
 */
@Controller
@RequestMapping("/test")
class TestController {

    @RequestMapping("/info")
    @ResponseBody
    Object accountInfo() {
        def account = new Account()

        //tomcat 启动后 修改username 并不会热刷新
        account.setUsername("yaoxiang")
        return account
    }
}
