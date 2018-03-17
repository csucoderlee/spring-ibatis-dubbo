package org.csu.coderlee.service;

import org.csu.coderlee.dao.AccountMapper;
import org.csu.coderlee.domain.Account;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public Account getById(Long id) {
        return null;
    }

    @Override
    public List<Account> list() {
        return null;
    }

    @Override
    public Account addAccount(Account account) {
        return null;
    }

    @Override
    public List<Account> addAccounts(List<Account> accountList) {
        return null;
    }

    @Override
    public void delAccount(Account account) {

    }

    @Override
    public void delAccounts(List<Account> accountList) {

    }

    @Override
    public Account modifyAccount(Account account) {
        return null;
    }

    @Override
    public List<Account> modifyAccounts(List<Account> accountList) {
        return null;
    }
}
