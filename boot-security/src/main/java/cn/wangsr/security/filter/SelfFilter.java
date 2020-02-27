package cn.wangsr.security.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: wjl
 * @description:
 * @time: 2020/1/14 0014 16:33
 */
@Component
@WebFilter(urlPatterns = { "/*" }, filterName = "myFilter")
public class SelfFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(SelfFilter.class);
    private final String REQUEST_ID = "request_id";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("filter init ...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");

        System.out.println("filter:"+request.getAttribute(REQUEST_ID));
        filterChain.doFilter(request, response);
    }


    @Override
    public void destroy() {
        logger.info("filter destroy ...");
    }
}
