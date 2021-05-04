// il affiche une alert (fenetre) qui affiche i tout les 10*i seconde
// Cela ne fonctione pas avec le var 
for (let i = 0; i < 10; i++) {
    setTimeout(function(){
        alert(i);
    },10*i);
    
}