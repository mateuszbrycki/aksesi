function AksesiEventHandler() {
    this.isMouseButtonPressed = false;
    this.gestureNumber = 0;

    this.pointsArray = new Array();
    this.pointsArrayNumber = 0;

    this.passwordElementsHandler = new PasswordElementsHandler();

    this.resetModuleState= function(form) {
        this.isMouseButtonPressed = false;
        this.gestureNumber = 0;

        this.pointsArray = [];
        this.pointsArrayNumber = 0;

        this.passwordElementsHandler = new PasswordElementsHandler();

        if (form !== null && form.length > 0) {
            form[0].reset();
        }
    };

    this.mouseDownEvent = function(e) {
        this.isMouseButtonPressed = true;
        this.gestureNumber++;

        this._logDeveloperInfo('Mouse down event');
    };

    this.mouseMoveEvent = function(e) {
        if (this.isMouseButtonPressed) {
            this.pointsArray[this.pointsArrayNumber] = new Point(e.pageX, e.pageY);
            this.pointsArrayNumber++;

            this._logDeveloperInfo('Mouse move event: (' + e.pageX + ', ' + e.pageY + ')');
        }
    };

    this.mouseUpEvent = function(e) {
        this.isMouseButtonPressed = false;
        this._processGestureStoring();

        //change focus to the character input
        $(CHARACTER_AREA_NAME_DOT).focus();
        this._appendGestureCharacterRepresentation();

        this._logDeveloperInfo('Mouse up event');
    };

    this.keyPressEvent = function(e) {
        var character = String.fromCharCode(e.which);
        var key = new Character(character);
        this.passwordElementsHandler.addElement(key);
    };

    this.keyUpEvent = function(e) {
        //handling backspace
        if (e.which == 8) {
            this.passwordElementsHandler.removeLastPressedKey();
        }
    };

    this.getPassword = function() {
        return this.passwordElementsHandler.getPassword();
    };

    this._logDeveloperInfo = function(message) {
        $(DEVELOPER_INFO_AREA_NAME).append(message + ', number: ' + this.gestureNumber + '<br />');
    };

    this._appendGestureCharacterRepresentation = function() {
        var characterAreaInput = $(CHARACTER_AREA_NAME_DOT).val();
        $(CHARACTER_AREA_NAME_DOT).val(characterAreaInput + GESTURE_CHARACTER_REPRESENTATION);
    };

    this._processGestureStoring = function() {
        var element = new Gesture(this.pointsArray);
        this.passwordElementsHandler.addElement(element);
        this._resetPointArray();
    };

    this._resetPointArray = function() {
        this.pointsArray = [];
        this.pointsArrayNumber = 0;
    };

}