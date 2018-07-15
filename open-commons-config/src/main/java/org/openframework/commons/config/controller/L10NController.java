package org.openframework.commons.config.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.openframework.common.rest.constants.ApplicationConstants;
import org.openframework.commons.config.model.LanguageBean;
import org.openframework.commons.config.service.I18nService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/l10n")
@Api(value = "L10N Controller", description = "REST APIs related to Student Entity!!!!")
public class L10NController {

	/** Logger that is available to subclasses */
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private I18nService i18nService;

	@Autowired
	private LocaleResolver localeResolver;

	@RequestMapping(method = RequestMethod.GET, value = "/languages")
	public @ResponseBody List<LanguageBean> getLanguages() {
		logger.debug("Retrieve all available languages");
		return i18nService.retrieveSupportedLanguages();
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/switchLocale")
	public String switchLocale(final HttpSession session, String language) {

		logger.debug("Change locale in : [{}]", language);
		return language;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/messages", produces = { "application/json" })
	public @ResponseBody Map<String, String> getMessages(HttpServletRequest request) {
		final Locale locale = localeResolver.resolveLocale(request);
		return getMessagesFromTypeAndLocale(ApplicationConstants.MESSAGE_TYPE_DASHBOARD, locale.getLanguage());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/messages/{messageType}")
	public @ResponseBody Map<String, String> getMessagesFromType(HttpServletRequest request,
			@PathVariable String messageType) {
		final Locale locale = localeResolver.resolveLocale(request);
		return getMessagesFromTypeAndLocale(messageType, locale.getLanguage());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/messages/{messageType}/{language}")
	public @ResponseBody Map<String, String> getMessagesFromTypeAndLocale(@PathVariable String messageType,
			@PathVariable String language) {

		// get the below boolean value from config
		boolean inheritanceRequired = true;
		return i18nService.getMessageProperties(language, messageType, inheritanceRequired);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/config")
	public @ResponseBody Map<String, Properties> getConfig() {

		return i18nService.getAppConfigsMap();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/config/{configName}")
	public @ResponseBody Properties getConfig(@PathVariable String configName) {

		return i18nService.getAppConfigsMap().get(configName);
	}

}
