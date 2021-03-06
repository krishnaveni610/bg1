package org.spo.ifs2.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 * The SpringMVC application context.
 *
 * This is the annotation variation of configuring the SpringMVC application
 * context. An XML configuration is imported so XML based configuration can
 * still be used.
 *
 * Any @Controller classes will be picked up by component scanning. All other
 * components are ignored as they will be picked up by the root application
 * context.
 */
@EnableWebMvc
@Configuration
@ComponentScan(useDefaultFilters = false, basePackages = {
		"org.spo.ifs2.template",
		"org.spo.svc2.xlaccess.service",
		"org.spo.svc2.xlaccess.controller",		
		"org.spo.svc2.pages.gateway.svc",
		"org.spo.svc2.pages.gateway.controller",
		"org.spo.svc2.httpd",
		"org.spo.svc2.trx",
		"org.spo.svc2.trx.pgs",		
		"org.spo.svc2.xlaccess.test"},
        includeFilters = {@ComponentScan.Filter(Controller.class)})

public class MvcConfiguration extends WebMvcConfigurerAdapter {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        /* Mapping to the login view. */
        registry.addViewController("/login1").setViewName("lc/login1");
       // registry.addViewController("/debug").setViewName("lc/debug");
       // registry.addViewController("/x_content").setViewName("lc/x_content");
       // registry.addViewController("/y_content").setViewName("lc/y_content");
        registry.addViewController("/contact").setViewName("lc/contact");
        registry.addViewController("/index1").setViewName("lc/index1");

    }

    /**
     * Allow the default servlet to serve static files from the webapp root.
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public ServletContextTemplateResolver thymeleafTemplateResolver() {
        ServletContextTemplateResolver resolver =
                new ServletContextTemplateResolver();
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        resolver.setCacheable(true);
        return resolver;
    }

    @Bean
    public SpringTemplateEngine thymeleafTemplateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(thymeleafTemplateResolver());
        return engine;
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(thymeleafTemplateEngine());
        return resolver;
    }

}
