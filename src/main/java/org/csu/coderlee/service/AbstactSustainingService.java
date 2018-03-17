package org.csu.coderlee.service;

import org.csu.coderlee.domain.Account;
import org.csu.coderlee.domain.Schedule;

import java.util.List;

/**
 * @author by bixi.lx
 * @created on 2018 03 17 21:55
 */
public abstract class AbstactSustainingService implements ISustainingService{

    abstract void setStrategy(Account account);

    abstract void setStrategy(List<Account> accountList);

    abstract void handlerSchedule(Schedule schedule);
}
