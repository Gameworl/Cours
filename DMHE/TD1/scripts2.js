var _loop_1 = function (i) {
    setTimeout(function () {
        alert(i);
    }, 10 * i);
};
// il affiche une alert (fenetre) qui affiche i tout les 10*i seconde
// Cela ne fonctione pas avec le var 
for (var i = 0; i < 10; i++) {
    _loop_1(i);
}
