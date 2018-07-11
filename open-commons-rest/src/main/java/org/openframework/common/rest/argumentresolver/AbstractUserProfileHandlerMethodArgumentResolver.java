package org.openframework.common.rest.argumentresolver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.openframework.common.rest.constants.ApplicationConstants;
import org.openframework.common.rest.filter.StatelessSecurityFilter;
import org.openframework.common.rest.vo.UserVO;
import org.openframework.commons.domain.exceptions.AuthenticationException;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractUserProfileHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	private static ThreadLocal<UserVO> userProfileHolder = new ThreadLocal<>();
	private static ThreadLocal<List<String>> userAccessHolder = new ThreadLocal<>();

	@Override
	public Object resolveArgument(MethodParameter arg0, ModelAndViewContainer arg1, NativeWebRequest nativeWebRequest,
			WebDataBinderFactory webDataBinderFactory) throws Exception {

		HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();
		if(isTestEnv(request)) {
			UserVO userVO = new UserVO();
			userVO.setId(1l);
			return userVO;
		}
		UserVO userProfile = null;

		String encryptedCookieValue = (String) request.getAttribute(StatelessSecurityFilter.COOKIE_LIU_VALUE);
		validateAuthString(encryptedCookieValue);

		String plainText = decrypt(encryptedCookieValue);
		validateAuthString(plainText);

		ObjectMapper objectMapper = new ObjectMapper();
		userProfile = objectMapper.readValue(plainText, UserVO.class);

		AbstractUserProfileHandlerMethodArgumentResolver.setUserProfile(userProfile);
		List<String> accessList = new ArrayList<>();
		accessList.add(userProfile.getRole());
		AbstractUserProfileHandlerMethodArgumentResolver.userAccessHolder.set(accessList);
		//AbstractUserProfileHandlerMethodArgumentResolver.setUserAccess(userProfile.getOtherData());
		return userProfile;
	}

	private void validateAuthString(String authString) {
		if (StringUtils.isBlank(authString)) {
			throw new AuthenticationException("Unauthenticated request");
		}
	}

	public abstract String decrypt(String value);

	public boolean isTestEnv(HttpServletRequest request) {
		return null != request.getAttribute(ApplicationConstants.SPRING_TEST_ENV);
	}

	@Override
	public boolean supportsParameter(MethodParameter methodParameter) {
		return methodParameter.getParameterType().equals(UserVO.class);
	}

	public static UserVO getUserProfile() {
		return userProfileHolder.get();
	}

	private static void setUserProfile(UserVO userProfile) {
		AbstractUserProfileHandlerMethodArgumentResolver.userProfileHolder.set(userProfile);
	}

	public static List<String> getUserAccess() {
		return userAccessHolder.get();
	}

	@SuppressWarnings({ "unchecked", "unused" })
	private static void setUserAccess(Map<String, Object> map) {
		List<String> functionList = new ArrayList<>();
		Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Object> object = iterator.next();
			if(object.getValue() instanceof List) {
				List<String> list = (List<String>) object.getValue();
				ListIterator<String> iterator2 = list.listIterator();
				while (iterator2.hasNext()) {
					String string = iterator2.next();
					functionList.add(string);
				}
			}
		}
		AbstractUserProfileHandlerMethodArgumentResolver.userAccessHolder.set(functionList);
	}

}
