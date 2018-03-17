package org.csu.coderlee.service;

import org.csu.coderlee.domain.Account;
import org.csu.coderlee.domain.Schedule;
import sun.misc.Cache;

import java.util.List;

/**
 * @author by bixi.lx
 * @created on 2018 03 17 21:58
 */
public class AdminSustainingService extends AbstactSustainingService {

    Account account;

    Cache cache;

    List<Account> accounts;

    Schedule schedule;

    List<Schedule> schedules;

    @Override
    public Account addDateSustainingSchedule(Account account) {
        return null;
    }

    @Override
    public List<Account> addDateSustainingSchedule(List<Account> accounts) {
        return null;
    }

    @Override
    void setStrategy(Account account) {

    }

    @Override
    public Account addWeekSustainingSchedule(Account account) {
        return null;
    }

    @Override
    void setStrategy(List<Account> accountList) {

    }

    @Override
    public List<Account> addWeekSustainingSchedule(List<Account> accounts) {
        return null;
    }

    @Override
    void handlerSchedule(Schedule schedule) {

    }

    @Override
    public void handleDateSustainingSchedule(Account account) {

    }

    @Override
    public void handleDateSustainingSchedule(List<Account> accounts) {

    }

    @Override
    public void handleWeekSustainingSchedule(Account account) {

    }

    @Override
    public void handleWeekSustainingSchedule(List<Account> accounts) {

    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Cache getCache() {
        return cache;
    }

    public void setCache(Cache cache) {
        this.cache = cache;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
