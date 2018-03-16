package org.csu.coderlee.domain;

import org.csu.coderlee.domain.Account;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author by bixi.lx
 * @created on 2018 03 15 14:41
 */
public class UserRole implements Serializable{

    private static final long serialVersionUID = -2108896641822238785L;

    /**
     * 系统角色表
     */
    private Long id;

    private Long companyId;

    private Long roleId;

    private String operatePrivilegeSetting;

    private String dataPrivilegeSetting;

    private String name;

    private String remark;

    private Date created;

    private Date modified;

    private Integer enableStatus;

    private List<Account> accountList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getOperatePrivilegeSetting() {
        return operatePrivilegeSetting;
    }

    public void setOperatePrivilegeSetting(String operatePrivilegeSetting) {
        this.operatePrivilegeSetting = operatePrivilegeSetting;
    }

    public String getDataPrivilegeSetting() {
        return dataPrivilegeSetting;
    }

    public void setDataPrivilegeSetting(String dataPrivilegeSetting) {
        this.dataPrivilegeSetting = dataPrivilegeSetting;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
}
