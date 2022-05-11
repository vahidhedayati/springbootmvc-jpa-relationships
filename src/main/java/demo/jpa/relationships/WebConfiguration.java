package demo.jpa.relationships;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class WebConfiguration {

    /**
     * This is being done via application.properties without a configuration entry
     * spring.h2.console.enabled=true
     * spring.h2.console.path=/h2
     */
    //  @Bean
    //ServletRegistrationBean h2ServletRegistration() {
    //    ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
     //   registrationBean.addUrlMappings("/console/*");
     //   return registrationBean;
    //}
}
