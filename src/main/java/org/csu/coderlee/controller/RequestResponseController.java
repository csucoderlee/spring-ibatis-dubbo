//package org.csu.coderlee.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.ModelAttribute;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @author by bixi.lx
// * @created on 2018 03 17 21:09
// */
//public class RequestResponseController extends Sessionable{
//    @Autowired(
//            required = true
//    )
//    protected HttpServletRequest request;
//    protected HttpServletResponse response;
//
//    public RequestResponseController() {
//    }
//
//    @ModelAttribute
//    public void setResponse(HttpServletResponse response) {
//        this.response = response;
//    }
//
//    public HttpServletRequest getRequest() {
//        return this.request;
//    }
//
//    public void setRequest(HttpServletRequest request) {
//        this.request = request;
//    }
//
//    public HttpServletResponse getResponse() {
//        return this.response;
//    }
//
//}
