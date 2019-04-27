package com.sun.health.apigetway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class AccessFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(AccessFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        logger.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());
        String accessToken = request.getParameter("accessToken");
        if(accessToken == null) {
            logger.warn("access token is empty");
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(404);
            return null;
        }
        logger.info("access token ok");
        return null;
    }
}
