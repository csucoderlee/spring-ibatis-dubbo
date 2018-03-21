package org.csu.coderlee.service;

import org.csu.coderlee.domain.Account;

import java.util.List;

/**
 * @author by bixi.lx
 * @created on 2018 03 17 21:48
 */
public interface ISustainingService {


    Account addDateSustainingSchedule(Account account);

    List<Account> addDateSustainingSchedule(List<Account> accounts);

    Account addWeekSustainingSchedule(Account account);

    List<Account> addWeekSustainingSchedule(List<Account> accounts);

    void handleDateSustainingSchedule(Account account);

    void handleDateSustainingSchedule(List<Account> accounts);

    void handleWeekSustainingSchedule(Account account);

    void handleWeekSustainingSchedule(List<Account> accounts);
}
