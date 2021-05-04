import UIKit

protocol Vehicle : CustomStringConvertible{
    var isRunning : Bool {get set}
    var description : String {get}
    var make : String {get set}
    var model : String {get set}
}

extension Vehicle {
    var makeModel:String {
        return "\(make)\(model)"
    }
    mutating func start() {
        if isRunning {
            print("\(self.description) Already started")
        }else{
            isRunning = true
            print("\(self.description) Vroooooommmmm")
        }
    }
    mutating func turnOff() {
        if isRunning {
            isRunning = false
            print("\(self.description) Crickets")
        }else{
            print(" \(self.description) Already stopped")
        }
    }
}

struct SportsVehicle : Vehicle{
    var isRunning : Bool = false
    var description : String { return self.makeModel}
    var make : String = ""
    var model : String = ""
}

class Semitruck : Vehicle{
    var isRunning : Bool = false
    var description : String { return self.makeModel}
    var make : String = ""
    var model : String = ""
    
    init(isRunning: Bool, make:String, model :String) {
        self.isRunning = isRunning;
        self.make = make
        self.model = model
    }
    
    func blowAirHorn() {
        print("TOOOOOOT")
    }
}


var car1 = SportsVehicle(isRunning: false, make: "Modus", model: "21")
var camion = Semitruck(isRunning: false, make: "berlier", model: "XZD")

car1.start()
camion.start()

extension Double{
    var euroString: String {
        return String(format: "€%.02f", self)
    }
    
}

var montant = 989.456
montant.euroString

