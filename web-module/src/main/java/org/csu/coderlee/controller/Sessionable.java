//package org.csu.coderlee.controller;
//
//import org.apache.log4j.Logger;
//import org.csu.coderlee.dao.ICache;
//import org.csu.coderlee.dao.ISession;
//import org.csu.coderlee.dao.ISessionManager;
//
//import javax.annotation.Resource;
//import javax.servlet.http.Cookie;
//import java.util.*;
//
///**
// * @author by bixi.lx
// * @created on 2018 03 17 21:09
// */
//public class Sessionable {
//    @Resource
//    protected ISessionManager sessionManager;
//    @Resource
//    protected ICache cache;
//
//    private final Logger logger = Logger.getLogger(this.getClass());
//    public static final String SDKInfo = "www.csu.org";
//
//    protected final String domain;
//
//    public Sessionable() {
//        this.domain = SDKInfo;
//    }
//
//    protected boolean isDevMode() {
//        String devOpen = System.getProperty("dev.open");
//        return StringUtils.equals(devOpen, "true");
//    }
//
//    protected ISession getSession(String sessionId) throws SessionException {
//        ISession session = this.sessionManager.getSession(sessionId, this.request, this.response);
//        if(null == session) {
//            throw new SessionException("用户会话失效！");
//        } else {
//            return session;
//        }
//    }
//
//    protected ISession getSession() throws SessionException {
//        try {
//            return this.getSession((String)null);
//        } catch (SessionException var4) {
//            if(this.isDevMode() && !this.sessionManager.isCentralSession()) {
//                Cookie staffId1 = this.getSessionCookie();
//                if(null != staffId1) {
//                    ISession staff1 = this.simulatSession(staffId1);
//                    if(null != staff1) {
//                        return staff1;
//                    }
//                }
//            } else if(this.sessionManager.isCentralSession()) {
//                Long staffId = this.validateMasterSession();
//                if(staffId == null) {
//                    throw new SessionException("会话异常，请重新登录");
//                }
//
//                Staff staff = this.staffService.get(staffId);
//                if(null == staff) {
//                    throw new SessionException("会话异常，请重新登录:staffId:" + staffId);
//                }
//
//                return this.createSession(staff);
//            }
//
//            throw var4;
//        }
//    }
//
//    private Cookie getSessionCookie() {
//        Cookie[] cookies = this.request.getCookies();
//        Cookie[] arr$ = cookies;
//        int len$ = cookies.length;
//
//        for(int i$ = 0; i$ < len$; ++i$) {
//            Cookie cookie = arr$[i$];
//            if("auth".equals(cookie.getName())) {
//                return cookie;
//            }
//        }
//
//        return null;
//    }
//
//    private ISession simulatSession(Cookie cookie) throws SessionException {
//        String sessionId = cookie.getValue();
//        if(StringUtils.isEmpty(sessionId)) {
//            return null;
//        } else if(!sessionId.contains("_")) {
//            return null;
//        } else {
//            String staffId = sessionId.split("_")[1];
//            Staff staff = this.staffService.get(Long.valueOf(Long.parseLong(staffId)));
//            if(null == staff) {
//                return null;
//            } else {
//                ISession session = this.sessionManager.simulateStaffSession(sessionId, this.request, this.response);
//                this.setStaff(session, staff);
//                return session;
//            }
//        }
//    }
//
//    protected ISession createSession(Staff staff) throws SessionException {
//        if(!this.sessionManager.isCentralSession()) {
//            this.request.setAttribute("_domain", this.getDomain());
//        }
//
//        ISession session = this.sessionManager.createStaffSession(staff.getId(), this.request, this.response);
//        this.setStaff(session, staff);
//        return session;
//    }
//
//    protected Long validateMasterSession() throws SessionException {
//        if(!this.sessionManager.isCentralSession()) {
//            throw new SessionException("SessionManager dosen\'t support central session");
//        } else {
//            String sessionId = this.findCentralSessionId();
//            if(StringUtils.isEmpty(sessionId)) {
//                return null;
//            } else {
//                try {
//                    Long e = (Long)this.cache.getAndTouch(sessionId, 'ꣀ');
//                    return null == e?null:e;
//                } catch (Exception var3) {
//                    return null;
//                }
//            }
//        }
//    }
//
//    protected Staff handleSessionStaff(Staff staff) {
//        staff.setMenus(this.staffService.getStaffMenu(staff));
//        staff.setUsers(this.getUsers(staff));
//        staff.setUserIdMap(this.toUserIdMap(staff));
//        return staff;
//    }
//
//    private void initCentralSession(ISession session, Staff staff) throws CacheException, SessionException {
//        String sessionId = this.findCentralSessionId();
//        if(null == sessionId) {
//            sessionId = session.getSessionId();
//        }
//
//        if(null == sessionId) {
//            throw new SessionException("会话失效，请重新登录");
//        } else {
//            Cookie cookie = new Cookie("_censeid", sessionId);
//            cookie.setDomain("." + this.getDomain());
//            cookie.setPath("/");
//            this.response.addCookie(cookie);
//            this.cache.set(sessionId, staff.getId(), 'ꣀ');
//            this.cache.set(this.buildCentralSessionKey(staff), sessionId, 'ꣀ');
//        }
//    }
//
//    protected String buildCentralSessionKey(Staff staff) {
//        return "" + staff.getId() + "_censeid";
//    }
//
//    protected Status successResponse() {
//        return Status.buildSuccessStatus();
//    }
//
//    protected String getDomain() {
//        String domain = System.getProperty("menu.domain");
//        if(domain == null) {
//            domain = SDKInfo.DOMAIN;
//        }
//
//        return domain;
//    }
//
//}
