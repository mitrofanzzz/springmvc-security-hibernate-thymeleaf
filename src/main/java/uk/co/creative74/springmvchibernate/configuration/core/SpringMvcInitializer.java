package uk.co.creative74.springmvchibernate.configuration.core;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import uk.co.creative74.springmvchibernate.configuration.AppConfig;
import uk.co.creative74.springmvchibernate.configuration.HibernateConfiguration;
import uk.co.creative74.springmvchibernate.configuration.WebMvcConfig;

public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class, HibernateConfiguration.class, WebMvcConfig.class };
	}
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
}