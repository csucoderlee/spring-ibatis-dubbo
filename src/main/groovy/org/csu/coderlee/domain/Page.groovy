package org.csu.coderlee.domain

/**
 * @author by bixi.lx
 * @created on 2018 03 20 22:46
 * @decription  分页逻辑
 */
class Page {

    public final static int DEFAULT_PAGE_SIZE = 20;
    public final static int DEFAULT_PAGE_NUM = 1;

    Integer pageNo = DEFAULT_PAGE_NUM

    Integer pageSize = DEFAULT_PAGE_SIZE

    Integer startRow

    Integer offsetRow

//    Page setPageNo(Integer page) {
//        if (page == null || page < 0) page = DEFAULT_PAGE_NUM;
//        this.pageNo = page;
//        this.startRow = (page - 1) * this.pageSize;
//        this.offsetRow = this.pageSize;
//        return this;
//    }
//
//    Integer getPageSize() {
//        return pageSize;
//    }
//
//    Page setPageSize(Integer pageSize) {
//        if (pageSize == null || pageSize < 1) pageSize = 1;
//        this.pageSize = pageSize;
//        this.startRow = (pageNo - 1) * this.pageSize;
//        this.offsetRow = this.pageSize;
//        return this;
//    }
//
//    Integer getPageNo() {
//        return pageNo;
//    }
}
