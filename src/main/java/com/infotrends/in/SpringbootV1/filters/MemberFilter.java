package com.infotrends.in.SpringbootV1.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;


@Component
public class MemberFilter implements Filter {

	
	List<String> excludedUrls = new ArrayList<String>();
    
    public MemberFilter() {
    }


	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		String path = request.getServletPath();
		if(excludedUrls.contains(path)) {
			chain.doFilter(req, resp);
		} else {
			HttpSession newSession = request.getSession();
			if(newSession!=null && newSession.getAttribute("username")!=null && !newSession.getAttribute("username").toString().isEmpty()) {
				chain.doFilter(req, resp);
			} else {

				String redirect_uri = request.getRequestURI();
				String qString = request.getQueryString();
				if(qString!=null && !qString.isEmpty()) {
					redirect_uri += "?" + qString;
				}
				System.out.println("------------------------------------------------------------------------------------"
						+ "\n---------------------------------------------------------------------------------------"
						+ "\n---------------------------------------------------------------------------------------"
						+ "\n" + redirect_uri + "-------------------------------------------------------------------------");
				response.sendRedirect(request.getContextPath() + "/authenticate?redirect=" + redirect_uri);
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		excludedUrls = Arrays.asList(fConfig.getInitParameter("excludedUrls").split(","));
	}
	

}
