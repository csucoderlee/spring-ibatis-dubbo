package org.csu.coderlee.service;

import org.csu.coderlee.dao.PostMapper;
import org.csu.coderlee.domain.Post;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author by bixi.lx
 * @created on 2018 03 16 17:31
 */
@Service
public class PostService implements IPostService{

    @Resource
    PostMapper postMapper;

    @Override
    public Post get() {
        return postMapper.selectById();
    }
}
