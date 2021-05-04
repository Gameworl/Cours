var Hello = /** @class */ (function() {
    function Hello(defaultName) {
        var _this = this;
        this.element = document.createElement("div");
        this.elementInput = document.createElement("input");
        this.elementText = document.createElement("div");
        var elementButton = document.createElement("button");
        elementButton.textContent = "Greet";
        this.element.appendChild(this.elementInput);
        this.element.appendChild(elementButton);
        this.element.appendChild(this.elementText);
        this.elementInput.value = defaultName;
        this.greet();
        elementButton.addEventListener("click", function() { return _this.greet(); });
    }
    Hello.prototype.show = function(parent) {
        parent.appendChild(this.element);
    };
    Hello.prototype.greet = function() {
        this.elementText.textContent = "hello, " + this.elementInput.value + "!";
    };
    return Hello;
}());
var hello = new Hello("world");
hello.show(document.body);