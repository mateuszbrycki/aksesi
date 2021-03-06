function PasswordElementsHandler() {

    this.elementsArray = [];
    this.elementsArraySize = 0;

    this.addElement = function(element) {
        this.elementsArray[this.elementsArraySize] = element;

        this.elementsArraySize++;
        this.printElements();
    };

    this.printElements = function() {
        console.log(this.elementsArray);
    };

    this.removeLastPressedKey = function() {
        this.elementsArray.splice(--this.elementsArraySize, 1);

        this.printElements();
    };

    this.getPassword = function() {
        return new Password(this.elementsArray);
    }

}

function Password(elements) {
    this.elements = elements;
}

function Character(character) {
    this.character = character;
    this.type = CHARACTER_ELEMENT_NAME;

    this.getKeyInfo = function() {return "code: " + this.code + ", character" + String.fromCharCode(code);}
}

function Gesture(points) {
    this.points = points;
    this.type = GESTURE_ELEMENT_NAME;
}

function Point(x, y) {
    this.x = x;
    this.y = y;

    this.getPointInfo = function() {return "x: " + this.x + ", y:" + this.y;}
}

function AuthenticationRequest(login, password, configuration) {
    this.login = login;
    this.password = password;
    this.configuration = configuration;
}

function InputConfiguration(login, password) {
    this.loginName = login;
    this.passwordName = password;
}

function Configuration(inputConfiguration, headers, url, method) {
    this.headers = headers;
    this.url = url;
    this.method = method;
    this.inputConfiguration = inputConfiguration;
}

