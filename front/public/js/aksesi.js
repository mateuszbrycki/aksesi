/**
 * Created by mateusz-mac on 25/03/2017.
 */


var GESTURE_AREA_NAME = "#aksesi-gesture-area";
var CHARACTER_AREA_NAME = "aksesi-character-area";
var CHARACTER_AREA_NAME_DOT = ".aksesi-character-area";
var DEVELOPER_INFO_AREA_NAME = "#aksesi-developer-info-area";

var CHARACTER_ELEMENT_NAME = "character";
var GESTURE_ELEMENT_NAME = "gesture";

var GESTURE_CHARACTER_REPRESENTATION = '~';

var GESTURE_AREA_CODE = "<div id=\"aksesi-gesture-area\" class=\"entypo-pencil\"></div>";
var DEVELOPER_AREA_CODE = "<div id=\"aksesi-developer-info-area\"></div>";

function AksesiConfiguration() {

    this.enable = function (authenticationFormId, enableDeveloperLog) {

        // this._addRequiredFiles();
        this._addAksesiCharacterArea(authenticationFormId);
        this._addAksesiPasswordArea(authenticationFormId);
        this._setupAksesiEvents();
        this._addAksesiDeveloperArea(enableDeveloperLog);
        this._setupFormForwarding(authenticationFormId);

    };

    this._setupFormForwarding = function(authenticationFormId) {
        var form = $(authenticationFormId);

        form.submit(function(e) {
           e.preventDefault();
            $form = $(this);

            $actionUrl = $form.attr("action");
            $actionMethod = $form.attr("method");
            $actionData = JSON.stringify(passwordElementsHandler.getPassword());

            $.ajax({
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                type: $actionMethod,
                url: $actionUrl,
                data: $actionData,
                success: function (callback) {
                   console.log(callback.message);
                },
                error: function (xhr) {
                    console.log(xhr);
                }
            });
        });
    }

    this._addAksesiCharacterArea = function (authenticationFormId) {
        //connect Aksesi mechanism
        $(authenticationFormId + " input[type=password]").addClass(CHARACTER_AREA_NAME);
    };
    this._addAksesiPasswordArea = function (authenticationFormId) {
        //add gesture area
        $(authenticationFormId + " input[type=password]").after(GESTURE_AREA_CODE);
    };

    this._setupAksesiEvents = function () {
        $(GESTURE_AREA_NAME).mousedown(function (e) {
            isMouseButtonPressed = true;
            gestureNumber++;

            logDeveloperInfo('Mouse down event');
        });

        $(GESTURE_AREA_NAME).mouseup(function (e) {
            isMouseButtonPressed = false;
            processGestureStoring();

            //change focus to the character input
            $(CHARACTER_AREA_NAME_DOT).focus();
            appendGestureCharacterRepresentation();

            logDeveloperInfo('Mouse up event');
        });

        $(GESTURE_AREA_NAME).mousemove(function (e) {

            if (isMouseButtonPressed) {
                pointsArray[pointsArrayNumber] = new Point(e.pageX, e.pageY);
                pointsArrayNumber++;

                logDeveloperInfo('Mouse move event: (' + e.pageX + ', ' + e.pageY + ')');
            }

        });

        $(CHARACTER_AREA_NAME_DOT).keypress(function (e) {

            var character = String.fromCharCode(e.which);
            var key = new Character(character);
            passwordElementsHandler.addElement(key);

        });

        $(CHARACTER_AREA_NAME_DOT).keyup(function (e) {

            //handling backspace
            if (e.which == 8) {
                passwordElementsHandler.removeLastPressedKey();
            }

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



