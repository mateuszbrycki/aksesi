/**
 * Created by mateusz-mac on 25/03/2017.
 */
var GESTURE_AREA_NAME = "#aksesi-gesture-area";
var CHARACTER_AREA_NAME = "aksesi-character-area";
var CHARACTER_AREA_NAME_DOT = ".aksesi-character-area";
var DEVELOPER_INFO_AREA_NAME = "#aksesi-developer-info-area";
var RESULT_MESSAGE_AREA_NAME = "#aksesi-message";

var CHARACTER_ELEMENT_NAME = "character";
var GESTURE_ELEMENT_NAME = "gesture";

var GESTURE_CHARACTER_REPRESENTATION = '~';

var AKSESI_PROXY_URL = "http://localhost:8081";
var AKSESI_PROXY_METHOD = "POST";

var GESTURE_AREA_CODE = "<div id=\"aksesi-gesture-area\" class=\"entypo-pencil\"></div>";
var DEVELOPER_AREA_CODE = "<div id=\"aksesi-developer-info-area\"></div>";
var RESULT_MESSAGE_AREA = "<div id=\"aksesi-message\" class=\"alert alert-info\" role=\"alert\"></div>";

var AKSESI = (function () {

    var passwordElement;
    var akesiEventHandler;

    var _setupFormForwarding = function (parentElement, authenticationFormId) {
        var form = $(authenticationFormId);

        form.submit(function (e) {
            e.preventDefault();
            $form = $(this);

            var loginInput = getLoginInput($form);

            var inputConfiguration = createInputConfiguration($form);
            var configuration = new Configuration(inputConfiguration, $form.attr("action"), $form.attr("method"));

            var password = akesiEventHandler.getPassword();
            var authenticationRequest = new AuthenticationRequest(loginInput.val(), password, configuration);

            var actionData = JSON.stringify(authenticationRequest);

            $.ajax({
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                type: AKSESI_PROXY_METHOD,
                url: AKSESI_PROXY_URL + "/password",
                data: actionData,
                success: function (callback) {
                    _handleResponse(form, callback.message);
                    akesiEventHandler.resetModuleState($form);
                },
                error: function (xhr) {
                    _handleResponse(form, xhr.responseText);
                }
            });
        });
    };

    var createInputConfiguration = function(form) {
        var loginInput = getLoginInput(form);
        var passwordInput = getPasswordInput(form);

        return new InputConfiguration(loginInput.attr("name"), passwordInput.attr("name"));
    };

    var getLoginInput = function(form) {
        return form.find('input[type="text"]');
    };

    var getPasswordInput = function(form) {
        return form.find('input[type="password"]');
    };

    var resetModuleState = function (form) {
        akesiEventHandler.resetModuleState(form);
    };

    var _handleResponse = function (form, message) {
        $(RESULT_MESSAGE_AREA_NAME).html(message);
        $(RESULT_MESSAGE_AREA_NAME).show();
    };

    var _addAksesiCharacterArea = function (authenticationFormId) {
        //connect Aksesi mechanism
        var form = $(authenticationFormId);
        getPasswordInput(form).addClass(CHARACTER_AREA_NAME);
    };
    var _addAksesiPasswordArea = function (authenticationFormId) {
        //add gesture area
        passwordElement = getPasswordInput($(authenticationFormId));
        passwordElement.after(GESTURE_AREA_CODE);
    };

    var _addAksesiMessageArea = function (authenticationFormId) {
        $(authenticationFormId).after(RESULT_MESSAGE_AREA);
    };

    var _setupAksesiEvents = function (parentObject) {
        $(GESTURE_AREA_NAME).mousedown(function (e) {
            akesiEventHandler.mouseDownEvent(e);
        });

        $(GESTURE_AREA_NAME).mouseup(function (e) {
            akesiEventHandler.mouseUpEvent(e);
        });

        $(GESTURE_AREA_NAME).mousemove(function (e) {

            akesiEventHandler.mouseMoveEvent(e);

        });

        $(CHARACTER_AREA_NAME_DOT).keypress(function (e) {
            akesiEventHandler.keyPressEvent(e);
        });

        $(CHARACTER_AREA_NAME_DOT).keyup(function (e) {
            akesiEventHandler.keyUpEvent(e);
        });

        $(CHARACTER_AREA_NAME_DOT).focus(function (e) {
            $(GESTURE_AREA_NAME).show("blind", {}, 600, function () {
            });
        });
    };

    var _addRequiredFiles = function () {
        var files = [
            "js/aksesi-classes.js",
            "js/aksesi-gestures.js"
        ];

        files.forEach(function (e) {
            var s = document.createElement("script");
            s.type = "text/javascript";
            s.src = e;
            $("head").append(s);
        });
    };

    var _addAksesiDeveloperArea = function (enableDeveloperLog) {
        if (enableDeveloperLog) {
            $(document.body).append(DEVELOPER_AREA_CODE);
        }
    };

    return {
        init: function (configuration) {
            var authenticationFormId = configuration['formName'];
            var enableDeveloperLog = configuration['showDevConsole'];

            akesiEventHandler = new AksesiEventHandler();

            $(document).ready(function() {
                _addAksesiCharacterArea(authenticationFormId);
                _addAksesiPasswordArea(authenticationFormId);
                _addAksesiMessageArea(authenticationFormId);
                _setupAksesiEvents(this);
                _addAksesiDeveloperArea(enableDeveloperLog);
                _setupFormForwarding(this, authenticationFormId);
            });
        }
    }

}());



