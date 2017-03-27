function PasswordElementsHandler() {

    this.elementsArray = [];
    this.elementsArraySize = 0;

    this.addElement = function(element) {
        this.elementsArray[this.elementsArraySize] = element;

        this.elementsArraySize++;
        this.printElements();
    }

    this.printElements = function() {
        console.log(this.elementsArray);
    }

    this.removeLastPressedKey = function() {
        this.elementsArray.splice(--this.elementsArraySize, 1);

        this.printElements();
    }
}

function Key(code) {
    this.code = code

    this.getKeyInfo = function() {return "code: " + this.code + ", character" + String.fromCharCode(code);}
}

function Point(x, y) {
    this.x = x;
    this.y = y;

    this.getPointInfo = function() {return "x: " + this.x + ", y:" + this.y;}
}