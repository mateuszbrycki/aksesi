function GesturesHandler() {

    this.gesturesArray = [];
    this.gesturesArraySize = 0;

    this.addGesture = function(gesture) {
        this.gesturesArray[this.gesturesArraySize] = gesture;

        this.gesturesArraySize++;
    }

    this.printGestures = function() {
        console.log(this.gesturesArray);
    }
}