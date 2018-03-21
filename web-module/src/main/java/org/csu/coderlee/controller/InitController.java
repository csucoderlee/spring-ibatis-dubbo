//package org.csu.coderlee.controller;
//
///**
// * @author by bixi.lx
// * @created on 2018 03 17 21:15
// */
//public class InitController extends RequestResponseController{
//
//    protected void checkPrivilege() {
//        if (!IpUtils.isSelfVisitor(request)) {
//            throw new IllegalArgumentException("您无权访问");
//        }
//    }
//
//    /**
//     * 写opLog
//     *
//     * @param staff
//     * @param domain
//     * @param action
//     * @param content
//     * @param args
//     */
//    protected void writeOpLog(Staff staff, Domain domain, String action, String content, String args) {
//        //记录操作日志
//        OpLog log = new OpLog();
//        log.setDomain(domain.getValue());
//        log.setAction(action);
//        log.setContent(content);
//        log.setArgs(args);
//        log.setIp(IpUtils.getClientIP(request));
//        opLogService.record(staff, log);
//    }
//
//    protected ReportBaseParam checkParam(ReportBaseParam param, boolean export) {
//        if (!export) {
//            if (null == param.getPageNo()) {
//                param.setPageNo(1);
//            }
//
//            if (null == param.getPageSize()) {
//                param.setPageSize(20);
//            }
//        }
//
//        if (null == param.getStartTime()) {
//            throw new IllegalArgumentException("开始时间不能为空");
//        }
//
//        if (null == param.getEndTime()) {
//            throw new IllegalArgumentException("结束时间不能为空");
//        }
//
//        ReportUtils.checkQueryDateIn90Days(param.getStartTime(), param.getEndTime());
//
//        return param;
//    }
//}
