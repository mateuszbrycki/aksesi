function AksesiEventHandler() {
    isMouseButtonPressed = false;
    gestureNumber = 0;

    pointsArray = new Array();
    pointsArrayNumber = 0;

    passwordElementsHandler = new PasswordElementsHandler();

    var resetModuleState= function(form) {
        isMouseButtonPressed = false;
        gestureNumber = 0;

        pointsArray = [];
        pointsArrayNumber = 0;

        passwordElementsHandler = new PasswordElementsHandler();

        if (form !== null && form.length > 0) {
            form[0].reset();
        }
    };

    var mouseDownEvent = function(e) {
        isMouseButtonPressed = true;
        gestureNumber++;

        _logDeveloperInfo('Mouse down event');
    };

    var mouseMoveEvent = function(e) {
        if (isMouseButtonPressed) {
            pointsArray[pointsArrayNumber] = new Point(e.pageX, e.pageY);
            pointsArrayNumber++;

            _logDeveloperInfo('Mouse move event: (' + e.pageX + ', ' + e.pageY + ')');
        }
    };

    var mouseUpEvent = function(e) {
        isMouseButtonPressed = false;
        _processGestureStoring();

        //change focus to the character input
        $(CHARACTER_AREA_NAME_DOT).focus();
        _appendGestureCharacterRepresentation();

        _logDeveloperInfo('Mouse up event');
    };

    var keyPressEvent = function(e) {
        var character = String.fromCharCode(e.which);
        var key = new Character(character);
        passwordElementsHandler.addElement(key);
    };

    var keyUpEvent = function(e) {
        //handling backspace
        if (e.which == 8) {
            passwordElementsHandler.removeLastPressedKey();
        }
    };

    var getPassword = function() {
        return passwordElementsHandler.getPassword();
    };

    var _logDeveloperInfo = function(message) {
        $(DEVELOPER_INFO_AREA_NAME).append(message + ', number: ' + gestureNumber + '<br />');
    };

    var _appendGestureCharacterRepresentation = function() {
        var characterAreaInput = $(CHARACTER_AREA_NAME_DOT).val();
        $(CHARACTER_AREA_NAME_DOT).val(characterAreaInput + GESTURE_CHARACTER_REPRESENTATION);
    };

    var _processGestureStoring = function() {
        var element = new Gesture(pointsArray);
        passwordElementsHandler.addElement(element);
        _resetPointArray();
    };

    var _resetPointArray = function() {
        pointsArray = [];
        pointsArrayNumber = 0;
    };


    return ({
        resetModuleState : resetModuleState,
        mouseDownEvent : mouseDownEvent,
        mouseMoveEvent : mouseMoveEvent,
        mouseUpEvent : mouseUpEvent,
        keyPressEvent : keyPressEvent,
        keyUpEvent : keyUpEvent,
        getPassword : getPassword
    });

}