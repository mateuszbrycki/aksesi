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