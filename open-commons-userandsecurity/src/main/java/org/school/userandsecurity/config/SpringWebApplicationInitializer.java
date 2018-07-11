package org.school.userandsecurity.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.openframework.common.rest.SwaggerConfig;
import org.openframework.common.rest.constants.ApplicationConstants;
import org.openframework.common.rest.filter.StatelessSecurityFilter;
import org.openframework.common.rest.filter.TracerFilter;
import org.openframework.commons.spring.SpringAopConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;

/**
 * https://stackoverflow.com/questions/28877982/spring-java-config-with-multiple-dispatchers
 * 
 * @author Java Developer
 *
 */
public class SpringWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private static final Logger loggers = LoggerFactory.getLogger(SpringWebApplicationInitializer.class);

	static {
		System.setProperty(ApplicationConstants.CONFIG_PATH_PROPERTY_NAME,
				"D:\\Work\\Repository\\JavaTechPoc\\school-parent\\school-user-security\\src\\main\\resources\\env-config\\dev");
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		System.out.println("Inside SpringWebApplicationInitializer.onStartup()");
		loggers.debug("------ Starting Inside SpringWebApplicationInitializer.onStartup()------");

		servletContext.setInitParameter("spring.profiles.active", "production");

		// root context

		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		// configuration class for root context
		rootContext.register(SpringRestWebConfig.class, SpringAopConfig.class, TransactionManagementConfig.class);
		servletContext.addListener(new ContextLoaderListener(rootContext));

		// dispatcher servlet for springRestWebApplicationContext
		configureSpringRestWebApplicationContext(servletContext, rootContext);

		// configure logback filters
		configureLoggingFilters(servletContext);

		// configure security filters
		configureSecurityFilters(servletContext);
	}

	private void configureSecurityFilters(ServletContext servletContext) {

		FilterRegistration.Dynamic mdcInsertingServletFilter = servletContext.addFilter("SecurityFilters",
				new StatelessSecurityFilter());
		mdcInsertingServletFilter.addMappingForUrlPatterns(null, false, "/api/*");
	}

	private void configureLoggingFilters(ServletContext servletContext) {

		FilterRegistration.Dynamic mdcInsertingServletFilter = servletContext.addFilter("MDCInsertingServletFilter",
				new MDCInsertingServletFilter());
		mdcInsertingServletFilter.addMappingForUrlPatterns(null, false, "/*");

		FilterRegistration.Dynamic tracerFilter = servletContext.addFilter("TracerFilter", new TracerFilter());
		tracerFilter.addMappingForUrlPatterns(null, false, "/*");
	}

	private void configureSpringRestWebApplicationContext(ServletContext servletContext,
			AnnotationConfigWebApplicationContext rootContext) {

		AnnotationConfigWebApplicationContext springRestWebApplicationContext = new AnnotationConfigWebApplicationContext();
		springRestWebApplicationContext.setParent(rootContext);
		springRestWebApplicationContext.register(SpringRestWebConfig.class, SwaggerConfig.class);
		ServletRegistration.Dynamic restEntryPoint = servletContext.addServlet("dispatcherRest",
				new DispatcherServlet(springRestWebApplicationContext));
		restEntryPoint.setLoadOnStartup(1);
		restEntryPoint.addMapping("/api/*");
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		System.out.println("getRootConfigClasses");
		return new Class<?>[0];
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		System.out.println("getServletConfigClasses");
		return new Class[0];
	}

	@Override
	protected String[] getServletMappings() {
		System.out.println("getServletMappings");
		return new String[0];
	}

}