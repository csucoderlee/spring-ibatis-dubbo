package org.csu.coderlee.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author by bixi.lx
 * @created on 2018 03 15 14:51
 */
public class Privilege implements Serializable{

    private static final long serialVersionUID = 8231331835816980771L;

    private Long id;

    /**
     * 父权限id
     */
    private Long parentId;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限所对应的路径，可能会有多个路径。
     */
    private String path;

    /**
     * 菜单对应域名路径。
     */
    private String domain;

    /**
     * 排序id
     */
    private Integer orderId;

    /**
     * 是否是扩展的权限,企业自定义权限
     */
    private Integer isExtend;

    /**
     * 权限层级
     */
    private Integer level;


    private Date created;

    private Date modified;

    private Integer enableStatus;

    /**
     * 是否是菜单
     * 0:false
     * 1:true
     */
    private Integer isMenu;

    /**
     * 直属孩子
     */
    private List<Privilege> childrenList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getIsExtend() {
        return isExtend;
    }

    public void setIsExtend(Integer isExtend) {
        this.isExtend = isExtend;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public Integer getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(Integer isMenu) {
        this.isMenu = isMenu;
    }


    public List<Privilege> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<Privilege> childrenList) {
        this.childrenList = childrenList;
    }
}
