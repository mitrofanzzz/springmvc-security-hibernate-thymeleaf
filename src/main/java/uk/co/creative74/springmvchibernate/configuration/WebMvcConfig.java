package uk.co.creative74.springmvchibernate.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.timeleaf.spring.support.ThymeleafLayoutInterceptor;

/*
 * If ever there is a problem, see this post and this comment
 * 
 * http://blog.codeleak.pl/2013/11/thymeleaf-template-layouts-in-spring.html
 * 
 * JoakimOctober 29, 2014 at 4:28 PM
 * I had to change WebMvcConfig to extend from WebMvcConfigurerAdapter instead of WebMvcConfigurationSupport to get @AuthenticationPrincipal to work.
 * When extending from WebMvcConfigurationSupport it seems like @EnableWebMvcSecurity is ignored and the AuthenticationPrincipalArgumentResolver is never added to the argument resolvers in the WebMvcSecurityConfiguration class.
 * Extending from WebMvcConfigurerAdapter instead fixed this problem for me.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	static final Logger appLog = LoggerFactory.getLogger("application-log");

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		appLog.info("Registering the ThymeleafLayoutInterceptor");
		registry.addInterceptor(new ThymeleafLayoutInterceptor());
	}

	/*
	 * ***************************************************************
	 * 
	 * RESOURCE FOLDERS CONFIGURATION Dispatcher configuration for serving
	 * static resources
	 * ****************************************************************
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		appLog.info("addResourceHandlers(ResourceHandlerRegistry registry) ...");
		
		registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
	}
}
