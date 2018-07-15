function onAppLoad() {
	$(document).ready(function() {
		$("#msgDash").click(function() {
			$.get("app/messages/dashboard", function(data) {
				$(".main").html(data);
				alert("Load was performed." + data);
			});
		});
	});
}

function loadPage(url) {
	$.ajax({
		type : 'GET',
		url : url,
		headers : {
			Accept : "text/plain; charset=utf-8",
			"Content-Type" : "text/plain; charset=utf-8"
		},
		// contentType: 'application/json',
		data : {
			"paramName" : "paramValue"
		},
		success : function(response) {
			$('#content').html(response);
		}
	});
}

function getSystemStatus(url) {
	$.ajax({
		type : 'GET',
		url : url,
		headers : {
			Accept : "text/plain; charset=utf-8",
			"Content-Type" : "text/plain; charset=utf-8"
		},
		success : function(response) {
			$('#content').html(response);
		}
	});
}

function loadLogger(url) {
	$.ajax({
		type : 'GET',
		url : url,
		headers : {
			Accept : "text/plain; charset=utf-8",
			"Content-Type" : "text/plain; charset=utf-8"
		},
		// contentType: 'application/json',
		data : {
			"paramName" : "paramValue"
		},
		success : function(response) {
			$('#content').html(response);
		}
	});
}


function setLogLevel() {
	var levels = $("#levels").val();
	var logger = $("#logger").val();

	if (levels != undefined && levels.length > 0) {

		var url = '/user-security-web/app/logger?levels=' + levels + '&logger=' + logger;
		$
				.ajax({
					type : "POST",
					url : url,
					contentType : "text/html; charset=utf-8",
					dataType : "text",
					success : function(data) {
						 $("#levels").val('');
						showTimeoutDialog('Log level changed to <b><font color="green">'
								+ levels + '</font></b>');
					}
				});
	} else {
		showAlertDialog("Please select any one log level.");
	}

}

function loadMessages() {
	var selectedLanguage = $("#selectLanguage").find( "option:selected" ).prop("value");
	if (-1 == selectedLanguage) {
		return false;
	}
	var messageType = $("#messageType").val();
	var url = '/user-security-web/app/messages/'+messageType+'/'+selectedLanguage;
	$.ajax({
		type : 'GET',
		url : url,
		headers : {
			Accept : "text/plain; charset=utf-8",
			"Content-Type" : "text/plain; charset=utf-8"
		},
		// contentType: 'application/json',
		data : {
			"paramName" : "paramValue"
		},
		success : function(response) {
			$('#messages').html(response);
		}
	});
}

function saveMessage(index, action) {
	var selectedLanguage = $("#selectLanguage").find( "option:selected" ).prop("value");
	var messageType = $("#messageType").val();
	var url = '/user-security-web/app/messages/'+messageType+'/'+selectedLanguage;
	var key1 = $("#key"+index).val();
	var value1 = $("#textArea"+index).val();

	saveMessageAndConfig(index, action, url, key1, value1);
}


function saveConfig(index, action) {

	var configName = $("#configName").val();
	var url = '/user-security-web/app/config/'+configName;
	var key1 = $("#key"+index).val();
	var value1 = $("#textArea"+index).val();

	saveMessageAndConfig(index, action, url, key1, value1);
}

function saveMessageAndConfig(index, action, url, key1, value1) {

	if (key1.length == 0) {
		$('#confirrmDialog').dialogBox({
			hasClose : true,
			hasBtn : true,
			confirmValue : 'OK',
			content : '<b><font color="green">Key is Empty.</font></b> Please provide valid key.'
		});
		return false;
	}
	$.post(url, {
		key : key1,
		value : value1
	}, function(response, status) {
		var msg = '';
		if (action === 'update') {
			msg = '<b><font color="green">' + key1 + '</font></b>'
					+ ' value has been updated.'
		} else {
			msg = '<b><font color="green">' + key1 + '</font></b>'
					+ ' value has been created.'
		}
		showTimeoutDialog(msg);
		$('#messages').html(response);
	});
}


function showTimeoutDialog(msg) {
	$('#timeoutDialog').dialogBox({
		content : msg,
		hasClose : true,
		autoHide : true,
		effect : 'sign',
		time : 3000
	})
}
function showAlertDialog(msg) {
	$('#alertDialog').dialogBox({
		hasClose : true,
		hasBtn : true,
		confirmValue : 'OK',
		content : msg
	});
}
