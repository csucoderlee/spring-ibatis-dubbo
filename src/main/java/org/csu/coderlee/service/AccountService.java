package org.csu.coderlee.service;

import org.csu.coderlee.dao.AccountMapper;
import org.csu.coderlee.domian.Account;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author by bixi.lx
 * @created on 2018 03 14 16:21
 */
@Service
public class AccountService implements IAccountService{

    @Resource
    AccountMapper accountMapper;

    @Override
    public Account info() {
        return accountMapper.selectById(1L);
    }
}
