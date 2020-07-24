package healthcheck.application.configuration;

import healthcheck.application.filters.RequestResponseLoggingFilter;
import healthcheck.application.filters.UserConnectionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FilterConfiguration {

    @Autowired
    private UserConnectionFilter userConnectionFilter;

    @Autowired
    private RequestResponseLoggingFilter requestResponseLoggingFilter;

    @Bean
    public FilterRegistrationBean<RequestResponseLoggingFilter> loggingFilterFilterRegistrationBean() {
        FilterRegistrationBean<RequestResponseLoggingFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(requestResponseLoggingFilter);
        registrationBean.setOrder(1);

        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }


    @Bean
    public FilterRegistrationBean<UserConnectionFilter> userConnectionFilterFilterRegistrationBean() {
        FilterRegistrationBean<UserConnectionFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(userConnectionFilter);
        registrationBean.setOrder(2);

        registrationBean.addUrlPatterns("/api/report/*");
        registrationBean.addUrlPatterns("/api/activity/*");
        registrationBean.addUrlPatterns("/api/hospital/*");
        registrationBean.addUrlPatterns("/api/proposal/*");
        registrationBean.addUrlPatterns("/api/vote/*");

        return registrationBean;
    }

}
