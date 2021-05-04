class Personne{
    age:number;
    constructor(public nom:string){
    }
    bonjour(){
        console.log("Bonjour ,"+this.nom + "age :" + this.age);
    }
}
const personne1 = new Personne("paul");
personne1.age = 35;
personne1.bonjour();