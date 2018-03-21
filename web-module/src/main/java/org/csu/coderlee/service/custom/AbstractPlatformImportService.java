package org.csu.coderlee.service.custom;

import org.csu.coderlee.domain.Account;

import java.util.List;

/**
 * @author by bixi.lx
 * @created on 2018 03 18 17:32
 */
public abstract class AbstractPlatformImportService implements ICustomImportService, IProcessImportService, RedisUtil{

    abstract void importCustom(List<Account> accountList);

    @Override
    public void getProcess(Long processId) {

    }

    @Override
    public void setProcess(Process process) {

    }

    @Override
    public void cacheProcess(Process process) {

    }

    @Override
    public void incImportCustom(List<Account> accountList) {

    }

    @Override
    public void fullImportCustom(List<Account> accountList) {

    }
}