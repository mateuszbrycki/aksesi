
var isMouseButtonPressed = false;
var gestureNumber = 0;

var pointsArray = [];
var pointsArrayNumber = 0;

var gesturesHandler = new GesturesHandler();

$(document).ready(function() {
    $(GESTURE_AREA_NAME).mousedown(function(e) {
        isMouseButtonPressed = true;
        gestureNumber++;

        logDeveloperInfo('Mouse down event');
    });

    $(GESTURE_AREA_NAME).mouseup(function(e) {
        isMouseButtonPressed = false;
        processGestureStoring();

        gesturesHandler.printGestures();
        logDeveloperInfo('Mouse up event');
    });

    $(GESTURE_AREA_NAME).mousemove(function(e) {

        if(isMouseButtonPressed) {
            pointsArray[pointsArrayNumber] = new Point(e.pageX, e.pageY);
            pointsArrayNumber++;

            logDeveloperInfo('Mouse move event: (' + e.pageX + ', ' + e.pageY + ')');
        }

    });
});

function processGestureStoring() {
    gesturesHandler.addGesture(pointsArray);
    resetPointArray();
}

function resetPointArray() {
    pointsArray = [];
    pointsArrayNumber = 0;
}

function logDeveloperInfo(message) {
    $(DEVELOPER_INFO_AREA_NAME).append(message + ', number: ' + gestureNumber + '<br />');

}