
var isMouseButtonPressed = false;
var gestureNumber = 0;

var pointsArray = [];
var pointsArrayNumber = 0;

var passwordElementsHandler = new PasswordElementsHandler();

$(document).ready(function() {
    $(GESTURE_AREA_NAME).mousedown(function(e) {
        isMouseButtonPressed = true;
        gestureNumber++;

        logDeveloperInfo('Mouse down event');
    });

    $(GESTURE_AREA_NAME).mouseup(function(e) {
        isMouseButtonPressed = false;
        processGestureStoring();

        //change focus to the character input
        $(CHARACTER_AREA_NAME).focus();
        appendGestureCharacterRepresentation();

        logDeveloperInfo('Mouse up event');
    });

    $(GESTURE_AREA_NAME).mousemove(function(e) {

        if(isMouseButtonPressed) {
            pointsArray[pointsArrayNumber] = new Point(e.pageX, e.pageY);
            pointsArrayNumber++;

            logDeveloperInfo('Mouse move event: (' + e.pageX + ', ' + e.pageY + ')');
        }

    });

    $(CHARACTER_AREA_NAME).keypress(function(e) {

        var key = new Key(e.which);
        passwordElementsHandler.addElement(key);

    });

    $(CHARACTER_AREA_NAME).keyup(function(e) {

        //handling backspace
        if(e.which == 8) {
            passwordElementsHandler.removeLastPressedKey();
        }

    });
});

function processGestureStoring() {
    passwordElementsHandler.addElement(pointsArray);
    resetPointArray();
}

function resetPointArray() {
    pointsArray = [];
    pointsArrayNumber = 0;
}

function appendGestureCharacterRepresentation() {
    var characterAreaInput = $(CHARACTER_AREA_NAME).val();
    $(CHARACTER_AREA_NAME).val(characterAreaInput + GESTURE_CHARACTER_REPRESENTATION);
}

function logDeveloperInfo(message) {
    $(DEVELOPER_INFO_AREA_NAME).append(message + ', number: ' + gestureNumber + '<br />');
}