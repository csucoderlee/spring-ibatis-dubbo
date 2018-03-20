package org.csu.coderlee.dao;

import org.csu.coderlee.domain.Account;
import org.csu.coderlee.domain.Page;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * @author by bixi.lx
 * @created on 2018 03 14 16:23
 */
@MapperScan
public interface AccountMapper {

    Account selectById(Long id);

    List<Account> selectAll(Page page);
}
