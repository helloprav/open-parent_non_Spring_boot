package org.openframework.commons.config.constants;

public class AppConstants {

	public static final String MESSAGE_TYPE_DASHBOARD = "dashboard";
	public static final String MESSAGE_TYPE_ERRORS = "errors";

	public static final String CONFIG_PATH_PROPERTY_NAME = "config";
	public static final String APPLICATION_CONFIG_DIR = CONFIG_PATH_PROPERTY_NAME;
	public static final String APPLICATION_MESSAGE_DIR = "message";
	public static final String APPLICATION_LOG_DIR = "logs";

    public static final String APP_INITI_FILE = "appInitialization.yml";

	public static final String FORWARD_SLASH = "/";
	public static final String URL_CONTEXT_ROOT = FORWARD_SLASH;

	public static final String SECURITY_SERVICE_APP_URL = "/app";
	public static final String SECURITY_SECURE_MAPPING = "/api/secure";
	public static final String SECURITY_CONFIG = "security.config";
    public static final String SECURITY_CONFIG_MAPPING = "/app/*";
    // tomcat role
    public static final String TOMCAT_ROLE = "manager-gui";
    public static final String TOMCAT_ROLE_ROLE1 = "role1";
	public static final String SPRING_MVC_SERVLET_URL_PATTERN = "/*";
}
