package org.openframework.common.rest.advice;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openframework.common.rest.Constants;
import org.openframework.common.rest.beans.ErrorBean;
import org.openframework.common.rest.beans.ResponseBean;
import org.openframework.common.rest.vo.BaseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice(annotations=RestController.class)
public class RestResponseBodyAdvice implements ResponseBodyAdvice<Object> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	//TODO Make this configurable for this maven project
	private boolean convertToList = false;

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {

		Object responseBody;
		HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
		HttpServletResponse servletResponse = ((ServletServerHttpResponse) response).getServletResponse();
		if(null == servletRequest.getAttribute(Constants.START_TIME)) {
			servletRequest.setAttribute("startTime", System.currentTimeMillis());
		}
		Long elapsedTime = System.currentTimeMillis() - (Long) servletRequest.getAttribute(Constants.START_TIME);
		if (body instanceof List) {
			ResponseBean<List<BaseVO>> responseBean = new ResponseBean<>();
			responseBean.setData((List<BaseVO>) body);
			responseBean.setResponseTime(elapsedTime);
			responseBean.setSuccessMessage(Constants.STRING_OK);
			responseBean.setStatusCode(servletResponse.getStatus());
			logger.info("responseBean: {}", responseBean);
			return responseBean;
		} else if (body instanceof BaseVO && convertToList) {
			/**
			 * This approach is not suggested. More Read:
			 * https://www.narwhl.com/resource-specific-responses/
			 */
			ResponseBean<Object> responseBean = new ResponseBean<>();
			List<BaseVO> voList = new ArrayList<>();
			voList.add((BaseVO)body);
			responseBean.setData(voList);
			responseBean.setResponseTime(elapsedTime);
			responseBean.setSuccessMessage(Constants.STRING_OK);
			responseBean.setStatusCode(servletResponse.getStatus());
			logger.info("responseBean: {}", responseBean);
			return responseBean;
		} else if (body instanceof BaseVO) {
			ResponseBean<Object> responseBean = new ResponseBean<>();
			responseBean.setData(body);
			responseBean.setResponseTime(elapsedTime);
			responseBean.setSuccessMessage(Constants.STRING_OK);
			responseBean.setStatusCode(servletResponse.getStatus());
			logger.info("responseBean: {}", responseBean);
			return responseBean;
		} else if (body instanceof ResponseBean) {
			/**
			 * This case may not happen.
			 */
			servletResponse.setStatus(((ResponseBean<Object>) body).getStatusCode());
			((ResponseBean<Object>) body).setResponseTime(elapsedTime);
			logger.info("body: {}", body);
			return body;
		} else if (body instanceof ErrorBean) {
			/**
			 * This case will happen when, an exception caught
			 * in @ControllerAdvice annotated class
			 * RestResponseEntityExceptionHandler. <p>statusCode was already set
			 * in response in RestResponseEntityExceptionHandler.handleXYZ().
			 */
			ResponseBean<Object> responseBean = new ResponseBean<>(servletResponse.getStatus(), null, (ErrorBean) body);
			responseBean.setResponseTime(elapsedTime);
			responseBody = responseBean;
		} else {
			logger.info("body: {}", body);
			return body;
		}
		logger.info("responseBody: {}", responseBody);
		return responseBody;
	}
}