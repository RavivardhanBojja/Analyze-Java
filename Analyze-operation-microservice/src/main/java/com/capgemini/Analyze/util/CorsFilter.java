package com.capgemini.Analyze.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "*");

		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE ,PUT");

		response.setHeader("Access-Control-Max-Age", "3600");
		
		response.setHeader("Access-Control-Allow-Headers",
				"Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
		
		chain.doFilter(req, res);
	}

	public void destroy() {

	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
