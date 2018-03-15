package org.csu.coderlee.domian

/**
 * @author by bixi.lx
 * @created on 2018 03 15 14:40
 */
public class Account {

    /**
     * id
     */
    private Long id;
    /**
     * 公司id
     */
    private Long companyId;

    /**
     * 平台类型，如tb，jd，sys（系统,用户自己输入）
     */
    private String platform;
    /**
     * 帐号名称
     */
    private String userName;
    /**
     * 登录密码
     */
    private String password;

    private Date lastLoginDate;

    private Date created;

    private Date modified;

    private Integer enableStatus;

    /**
     * '是否允许登陆:0:否，1:是'
     */
    private Integer allowLogin;
    /**
     * 是否是默认员工的账号（公司注册时的账号）:0:否，1:是
     */
    private Integer isDefault;

    /**
     * 记录初始化账户是否已修改密码，默认0，修改后为1
     */
    private Integer isUpdatePwd;

    /**
     * 本账号连续输错5次密码会被锁定
     * 账户是否被锁定  0 否  1 是
     */
    private Integer isLock;
}
