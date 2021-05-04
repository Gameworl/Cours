var maxFinder = {
    max: 0,
    find: function (numbers) {
        var _this = this;
        // let's iterate
        numbers.forEach(function (element) {
            // if element is greater, set it as the max
            if (element > _this.max) {
                _this.max = element;
            }
        });
    }
};
maxFinder.find([2, 3, 4]);
//log the result
console.log(maxFinder.max);
