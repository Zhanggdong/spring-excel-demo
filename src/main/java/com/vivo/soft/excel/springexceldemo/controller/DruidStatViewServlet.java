package com.vivo.soft.excel.springexceldemo.controller;

import com.alibaba.druid.support.http.StatViewServlet;
import javax.servlet.Servlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
/**
 * @author 张贵东
 * @Company TODO
 * @date 2018-11-17.
 * @Time 15:42
 * @Description TODO
 * @Version 2.0.0
 */
/*@WebServlet(
        urlPatterns= {"/druid*//*"},
        initParams= {
                @WebInitParam(name="allow",value="127.0.0.1"),
                @WebInitParam(name="loginUsername",value="root"),
                @WebInitParam(name="loginPassword",value="123"),
                @WebInitParam(name="resetEnable",value="true")// 允许HTML页面上的“Reset All”功能

        }
)*/
public class DruidStatViewServlet extends StatViewServlet implements Servlet {
    private static final long serialVersionUID = 1L;


}

