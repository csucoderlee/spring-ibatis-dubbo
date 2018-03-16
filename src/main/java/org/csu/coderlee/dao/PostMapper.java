package org.csu.coderlee.dao;

import org.csu.coderlee.domain.Post;
import org.mybatis.spring.annotation.MapperScan;

/**
 * @author by bixi.lx
 * @created on 2018 03 16 17:29
 */
@MapperScan
public interface PostMapper {
    Post selectById();
}
