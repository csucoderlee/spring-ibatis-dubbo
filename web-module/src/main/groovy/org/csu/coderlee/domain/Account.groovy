package org.csu.coderlee.domain

/**
 * @author by bixi.lx
 * @created on 2018 03 15 14:40
 */
class Account {

    /**
     * id
     */
    def id
    /**
     * 公司id
     */
     def companyId;

    /**
     * 平台类型，如tb，jd，sys（系统,用户自己输入）
     */
     def platform;
    /**
     * 帐号名称
     */
     def username;
    /**
     * 登录密码
     */
     def password;

     def lastLogindef;

     def created;

     def modified;

     def enableStatus;

    /**
     * '是否允许登陆:0:否，1:是'
     */
     def allowLogin;
    /**
     * 是否是默认员工的账号（公司注册时的账号）:0:否，1:是
     */
     def isDefault;

    /**
     * 记录初始化账户是否已修改密码，默认0，修改后为1
     */
     def isUpdatePwd;

    /**
     * 本账号连续输错5次密码会被锁定
     * 账户是否被锁定  0 否  1 是
     */
     def isLock;
}
