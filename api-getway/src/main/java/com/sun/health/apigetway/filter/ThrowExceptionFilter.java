package com.sun.health.apigetway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletResponse;

public class ThrowExceptionFilter extends ZuulFilter {
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
//        RequestContext currentContext = RequestContext.getCurrentContext();
//        return currentContext.containsKey("error.status_code") && !currentContext.getBoolean("SEND_ERROR_FILTER_RAN", false);
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        try {
            doSomething();
        } catch (Exception e) {
            currentContext.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            currentContext.set("error.exception", e);
        }
        return null;
    }

    private void doSomething() {
        throw new RuntimeException("Exist some errors");
    }
}
