//TODO mbrycki close all those loginc in a class
var isMouseButtonPressed = false;
var gestureNumber = 0;

var pointsArray = [];
var pointsArrayNumber = 0;

var passwordElementsHandler = new PasswordElementsHandler();

function processGestureStoring() {
    var element = new Gesture(pointsArray);
    passwordElementsHandler.addElement(element);
    resetPointArray();
}

function resetPointArray() {
    pointsArray = [];
    pointsArrayNumber = 0;
}

function appendGestureCharacterRepresentation() {
    var characterAreaInput = $(CHARACTER_AREA_NAME_DOT).val();
    $(CHARACTER_AREA_NAME_DOT).val(characterAreaInput + GESTURE_CHARACTER_REPRESENTATION);
}

function logDeveloperInfo(message) {
    $(DEVELOPER_INFO_AREA_NAME).append(message + ', number: ' + gestureNumber + '<br />');
}