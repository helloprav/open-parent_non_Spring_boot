/**
 * 
 */
package org.openframework.common.rest.service;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openframework.common.rest.service.model.LanguageBean;
import org.openframework.common.rest.service.model.MessageResourceLocale;

/**
 * @author pmis30
 *
 */
public interface MessageResourceAS {

	public Map<String, MessageResourceLocale> getMessageResourceMap();

	List<LanguageBean> getSupportedLanguages();

	Map<String, Properties> getAppConfigsMap();

	public void updateMessageProperties(String language, String messageType, String key, String value);

	public void updateConfigProperties(String configName, String key, String value);

}
