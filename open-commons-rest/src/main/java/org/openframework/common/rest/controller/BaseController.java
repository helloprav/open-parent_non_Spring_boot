/**
 * 
 */
package org.openframework.common.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.ObjectError;

/**
 * @author pmis30
 *
 */
//@RequestMapping("restapi")
public class BaseController {

	/** Logger that is available to subclasses */
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected static final String[] SUPPORTED_MEDIA_TYPES = { MediaType.APPLICATION_JSON_VALUE };

	protected List<String> convertToStringErros(List<ObjectError> allErrors) {

		List<String> errorsList = new ArrayList<String>(allErrors.size());
		ListIterator<ObjectError> iterator = allErrors.listIterator();
		while (iterator.hasNext()) {
			ObjectError objectError = iterator.next();
			errorsList.add(objectError.getDefaultMessage());
		}
		return errorsList;
	}

}
