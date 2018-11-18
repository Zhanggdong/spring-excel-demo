package com.vivo.soft.excel.springexceldemo.filter;

import com.alibaba.druid.support.http.WebStatFilter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;


/**
 * @author 张贵东
 * @Company TODO
 * @date 2018-11-17.
 * @Time 15:41
 * @Description TODO
 * @Version 2.0.0
 */
/*@WebFilter(
        filterName="druidWebStatFilter",
        urlPatterns= {"*//*"},
        initParams= {
                @WebInitParam(name="exclusions",value="*.js,*.jpg,*.png,*.gif,*.ico,*.css,/druid*//*")//配置本过滤器放行的请求后缀
        }
)*/
public class DruidStatFilter extends WebStatFilter {
}
