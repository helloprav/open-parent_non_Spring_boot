package org.openframework.commons.config.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openframework.commons.config.constants.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class SecurityServlet
 */
//@WebServlet(urlPatterns = {"/app", "/app/*"}, loadOnStartup=1)
//@ServletSecurity(@HttpConstraint(rolesAllowed = { ApplicationConstants.TOMCAT_ROLE }))
public class SecurityServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void init() throws ServletException {

		System.out.println("SecurityServlet.init()");
		super.init();
		System.out.println("SecurityServlet.init(2)");
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	logger.warn("App Settings page accessd by User");
		String appUrl = request.getContextPath().concat(AppConstants.SECURITY_SERVICE_APP_URL);
        RequestDispatcher rd = null;
        
        // TODO /api/* mapping for the dispather servlet has to be taken care of here
        rd = request.getRequestDispatcher(request.getRequestURI().replaceAll(appUrl,
        		AppConstants.SECURITY_SECURE_MAPPING));
        /*if (appUrl.equals(request.getRequestURI())) {
            rd = request.getRequestDispatcher("/api/secure");
        } else {
            rd = request.getRequestDispatcher(request.getRequestURI().replaceAll(appUrl,
            		AppConstants.SECURITY_SECURE_MAPPING));
        }*/
        request.setAttribute(AppConstants.SECURITY_SECURE_MAPPING, true);
        rd.forward(request, response);
	}

}
