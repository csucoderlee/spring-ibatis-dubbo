package org.csu.coderlee.service;

import org.csu.coderlee.domain.Account;

import java.util.List;

/**
 * @author by bixi.lx
 * @created on 2018 03 14 16:21
 */
public interface IAccountService {

    Account info();

    Account getById(Long id);

    List<Account> list();

    Account addAccount(Account account);

    List<Account> addAccounts(List<Account> accountList);

    void delAccount(Account account);

    void delAccounts(List<Account> accountList);

    Account modifyAccount(Account account);

    List<Account> modifyAccounts(List<Account> accountList);
}
