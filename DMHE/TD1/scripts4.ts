var maxFinder = {
    max:0,
    find: function(numbers){
        // let's iterate
        numbers.forEach(element => {
            // if element is greater, set it as the max
            if (element > this.max) {
                this.max = element;
            }
        });
    }
};
maxFinder.find([2,3,4]);
//log the result
console.log(maxFinder.max);