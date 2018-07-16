var interceptor = [ '$location', '$q', '$injector',
		function($location, $q, $injector) {
			function success(response) {
				return response;
			}

			function error(response) {

				if (response.status === 401 || response.status === 403) {
					$injector.get('$state').transitionTo('login');
					return $q.reject(response);
				} else {
					return $q.reject(response);
				}
			}

			return function(promise) {
				return promise.then(success, error);
			}
		} ];

var routerApp = angular.module('routerApp');

routerApp.config(function($httpProvider) {
	$httpProvider.responseInterceptors.push(interceptor);
})
