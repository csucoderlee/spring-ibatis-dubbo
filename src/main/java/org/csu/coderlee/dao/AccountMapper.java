package org.csu.coderlee.dao;

import org.csu.coderlee.domian.Account;
import org.mybatis.spring.annotation.MapperScan;

/**
 * @author by bixi.lx
 * @created on 2018 03 14 16:23
 */
@MapperScan
public interface AccountMapper {

    Account selectById(Long id);
}
