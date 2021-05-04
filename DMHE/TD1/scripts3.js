var Personne = /** @class */ (function () {
    function Personne(nom) {
        this.nom = nom;
    }
    Personne.prototype.bonjour = function () {
        console.log("Bonjour ," + this.nom + "age :" + this.age);
    };
    return Personne;
}());
var personne1 = new Personne("paul");
personne1.age = 35;
personne1.bonjour();
