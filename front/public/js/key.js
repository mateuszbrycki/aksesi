function Key(code) {
    this.code = code

    this.getKeyInfo = function() {return "code: " + this.code + ", character" + String.fromCharCode(code);}
}