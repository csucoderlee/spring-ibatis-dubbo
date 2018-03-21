package org.csu.coderlee.service.custom;

import org.csu.coderlee.domain.Account;

import java.util.List;

/**
 * @author by bixi.lx
 * @created on 2018 03 18 17:30
 */
public interface ICustomImportService {

    public List<Account> accountList = null;

    abstract void incImportCustom(List<Account> accountList);

    abstract void fullImportCustom(List<Account> accountList);
}
