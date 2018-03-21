package org.csu.coderlee.service.custom;

import org.csu.coderlee.domain.Account;
import org.csu.coderlee.service.AccountService;

import java.util.List;

/**
 * @author by bixi.lx
 * @created on 2018 03 18 17:31
 */
public class ManualImportCustomService implements ICustomImportService{

    private String platformInfo;

    AccountService accountService;

    @Override
    public void incImportCustom(List<Account> accountList) {

    }

    @Override
    public void fullImportCustom(List<Account> accountList) {

    }
}
