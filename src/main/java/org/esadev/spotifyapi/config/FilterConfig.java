package org.esadev.spotifyapi.config;

import org.esadev.spotifyapi.filter.SpotifyCodeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<SpotifyCodeFilter> spotifyCodeFilterFilterRegistrationBean() {
        var registrationBean = new FilterRegistrationBean<SpotifyCodeFilter>();
        registrationBean.setFilter(new SpotifyCodeFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
