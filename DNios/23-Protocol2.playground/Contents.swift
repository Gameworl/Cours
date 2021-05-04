import UIKit

protocol Vehicle : CustomStringConvertible{
    var isRunning : Bool {get set}
    //var description : String {get}
    mutating func start ()
    mutating func turnOff()
}

struct SportsCar: Vehicle{
    
    var description: String {
        if isRunning {
            return "SportsCar is running"
        }else{
            return "SportsCar is stopped"
        }
    }
    
    var isRunning: Bool = false
    
    // var description: String
    
    mutating func start() {
        if isRunning {
            print("Already started")
        }else{
            isRunning = true
            print("Vroooooommmmm")
        }
    }
    
    mutating func turnOff() {
        if isRunning {
            isRunning = false
            print("Crickets")
        }else{
            print("Already stopped")
        }
    }
}

class SemiTruck : Vehicle {
    
     var description: String {
        if isRunning {
            return "SemiTruck is running"
        }else{
            return "SemiTruck is stopped"
        }
    }
    
    var isRunning: Bool = false
    
    // var description: String
    
    func start() {
        if isRunning {
            print("Already started")
        }else{
            isRunning = true
            print("Ruuuummmmmbbbblllllleee")
        }
    }
    
    func turnOff() {
        if isRunning {
            isRunning = false
            print("put ... put ... put")
        }else{
            print("Already stopped")
        }
    }
    
    func blowAirHorn() {
        print("TTTTTTTTOOOOOOOOOOOOTTTTTT")
    }
}

var car1 = SportsCar()
var truck1 = SemiTruck()
car1.start()
car1.turnOff()
truck1.start()
truck1.blowAirHorn()
truck1.turnOff()

var vehicleArray: Array<Vehicle> = [car1, truck1]
for vehicle in vehicleArray {
    print("\(vehicle.description)")
    if let vehicle = vehicle as? SemiTruck {
        vehicle.blowAirHorn()
    }
}
