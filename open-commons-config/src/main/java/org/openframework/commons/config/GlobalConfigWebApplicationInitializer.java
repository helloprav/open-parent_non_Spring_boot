package org.openframework.commons.config;

import java.io.File;
import java.util.Map;
import java.util.Properties;

import javax.servlet.HttpConstraintElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletSecurityElement;
import javax.servlet.annotation.ServletSecurity.TransportGuarantee;

import org.openframework.common.rest.util.YamlUtils;
import org.openframework.commons.config.constants.AppConstants;
import org.openframework.commons.config.servlet.SecurityServlet;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * https://stackoverflow.com/questions/28877982/spring-java-config-with-multiple-dispatchers
 * 
 * @author Java Developer
 *
 */
public class GlobalConfigWebApplicationInitializer {//extends AbstractAnnotationConfigDispatcherServletInitializer {

	//@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		System.out.println("Inside GlobalConfigWebApplicationInitializer.onStartup()");

		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		// configuration class for root context
		rootContext.register(GlobalConfigSpringMvcWebConfig.class);
		rootContext.setServletContext(servletContext);

		// security servlet
		configureSecurityServlet(servletContext);
	}

	public static void configureSecurityServlet(ServletContext servletContext) {

		Map<String, ? extends ServletRegistration> servletReg = servletContext.getServletRegistrations();
		System.out.println(servletReg);
		String fileName = System.getProperty(AppConstants.CONFIG_PATH_PROPERTY_NAME).concat(File.separator)
				.concat(AppConstants.APP_INITI_FILE);
		Properties appInitializationProp = YamlUtils.loadYamlProperty(fileName);
		String securityConfig = appInitializationProp.getProperty(AppConstants.SECURITY_CONFIG);

		// create the security servlet

		// apply security to the servlet
		if (Boolean.valueOf(securityConfig)) {
			ServletRegistration.Dynamic securityServletConfig = servletContext
					.addServlet(SecurityServlet.class.getName(), SecurityServlet.class);
			securityServletConfig.addMapping(AppConstants.SECURITY_SERVICE_APP_URL,
					AppConstants.SECURITY_CONFIG_MAPPING);
			securityServletConfig.setLoadOnStartup(0);
			HttpConstraintElement httpConstraintElement = new HttpConstraintElement(TransportGuarantee.NONE,
					AppConstants.TOMCAT_ROLE_ROLE1);
			ServletSecurityElement servletSecurityElement = new ServletSecurityElement(httpConstraintElement);
			securityServletConfig.setServletSecurity(servletSecurityElement);
			System.out.println("Tomcat Security Enabled");
		} else {
			ServletRegistration.Dynamic securityServletConfig = servletContext
					.addServlet(SecurityServlet.class.getName(), SecurityServlet.class);
			securityServletConfig.addMapping(AppConstants.SECURITY_CONFIG_MAPPING);
			securityServletConfig.setLoadOnStartup(0);
			System.out.println("Tomcat Security NOT Enabled");
		}

	}

	//@Override
	protected Class<?>[] getRootConfigClasses() {
		// return new Class<?>[] { AppConfig.class };
		return null;
	}

	//@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return null;
	}

}