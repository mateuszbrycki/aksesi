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

function AksesiConfiguration() {

    this.passwordElement;
    this.akesiEventHandler;

    this.enable = function (authenticationFormId, enableDeveloperLog) {
        this.akesiEventHandler = new AksesiEventHandler();
        // this._addRequiredFiles();
        this._addAksesiCharacterArea(authenticationFormId);
        this._addAksesiPasswordArea(authenticationFormId);
        this._addAksesiMessageArea(authenticationFormId);
        this._setupAksesiEvents(this);
        this._addAksesiDeveloperArea(enableDeveloperLog);
        this._setupFormForwarding(this, authenticationFormId);
    };

    this._setupFormForwarding = function(parentElement, authenticationFormId) {
        var form = $(authenticationFormId);

        form.submit(function(e) {
           e.preventDefault();
            $form = $(this);

            var login = $form.find('input[type="text"]').val();
            var password = parentElement.akesiEventHandler.getPassword();

            var configuration = new Configuration($form.attr("action"), $form.attr("method"));
            var authenticationRequest = new AuthenticationRequest(login, password, configuration);

            $actionData = JSON.stringify(authenticationRequest);

            $.ajax({
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                type: AKSESI_PROXY_METHOD,
                url: AKSESI_PROXY_URL + "/password",
                data: $actionData,
                success: function (callback) {
                    _handleResponse(form, callback.message);
                   parentElement.resetModuleState($form);
                },
                error: function (xhr) {
                    _handleResponse(form, xhr.responseText);
                }
            });
        });
    };

    this.resetModuleState = function(form){
        this.akesiEventHandler.resetModuleState(form);
    };

    function _handleResponse(form, message) {
        $(RESULT_MESSAGE_AREA_NAME).html(message);
        $(RESULT_MESSAGE_AREA_NAME).show();
    };

    this._addAksesiCharacterArea = function (authenticationFormId) {
        //connect Aksesi mechanism
        $(authenticationFormId + " input[type=password]").addClass(CHARACTER_AREA_NAME);
    };
    this._addAksesiPasswordArea = function (authenticationFormId) {
        //add gesture area
        this.passwordElement = $(authenticationFormId + " input[type=password]");
        this.passwordElement.after(GESTURE_AREA_CODE);
    };

    this._addAksesiMessageArea = function(authenticationFormId){
        $(authenticationFormId).after(RESULT_MESSAGE_AREA);
    };

    this._setupAksesiEvents = function (parentObject) {
        $(GESTURE_AREA_NAME).mousedown(function (e) {
            parentObject.akesiEventHandler.mouseDownEvent(e);
        });

        $(GESTURE_AREA_NAME).mouseup(function (e) {
            parentObject.akesiEventHandler.mouseUpEvent(e);
        });

        $(GESTURE_AREA_NAME).mousemove(function (e) {

            parentObject.akesiEventHandler.mouseMoveEvent(e);

        });

        $(CHARACTER_AREA_NAME_DOT).keypress(function (e) {
            parentObject.akesiEventHandler.keyPressEvent(e);
        });

        $(CHARACTER_AREA_NAME_DOT).keyup(function (e) {
            parentObject.akesiEventHandler.keyUpEvent(e);
        });

        $(CHARACTER_AREA_NAME_DOT).focus(function (e) {
            $(GESTURE_AREA_NAME).show("blind", {}, 600, function () {
            });
        });
    };

    this._addRequiredFiles = function () {
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

    this._addAksesiDeveloperArea = function (enableDeveloperLog) {
        if (enableDeveloperLog) {
            $(document.body).append(DEVELOPER_AREA_CODE);
        }
    };
}



