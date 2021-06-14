package com.infotrends.in.SpringbootV1.Config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.infotrends.in.SpringbootV1.filters.AdminsFilter;
import com.infotrends.in.SpringbootV1.filters.MemberFilter;

@Component
public class FiltersConfig {

    @Bean
    public FilterRegistrationBean<MemberFilter> membersFilter()
    {
    	String excludeUrls = "";
    	
       FilterRegistrationBean<MemberFilter> registrationBean = new FilterRegistrationBean<>();
       registrationBean.setFilter(new MemberFilter());
       registrationBean.addUrlPatterns("/membersdashboard", "/membersdashboard/*", "/retailApp", "/retailApp/*", "/userInfo", "/userInfo**", "/search", "/search**");
       registrationBean.addInitParameter("excludedUrls", excludeUrls);
       return registrationBean;
    }
    
    @Bean
    public FilterRegistrationBean<AdminsFilter> adminAccessFilter()
    {
    	String excludeUrls = "";
    	
       FilterRegistrationBean<AdminsFilter> registrationBean = new FilterRegistrationBean<>();
       registrationBean.setFilter(new AdminsFilter());
       registrationBean.addUrlPatterns("/adminsdashboard", "/adminsdashboard/*");
       registrationBean.addInitParameter("excludedUrls", excludeUrls);
       return registrationBean;
    }
}
