function Point(x, y) {
    this.x = x;
    this.y = y;

    this.getPointInfo = function() {return "x: " + this.x + ", y:" + this.y;}
}