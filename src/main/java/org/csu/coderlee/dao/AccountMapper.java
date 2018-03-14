package org.csu.coderlee.dao;

import org.csu.coderlee.domian.Account;
import org.springframework.stereotype.Repository;

/**
 * @author by bixi.lx
 * @created on 2018 03 14 16:23
 */
public interface AccountMapper {

    Account selectById(Long id);
}
