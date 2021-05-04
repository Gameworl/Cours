import UIKit

var double:Double = 1.99
var int:Int = 12
var str:String = "12.8"

var doubleStr:Double? = Double(str)
print(doubleStr!)

var intStr:Int? = Int(str)
//print(intStr!) car c'est un double dedans donc convert impossible

var Str2:String = "111"
var intStr2:Int? = Int(Str2)
print(intStr2!)

var int3:Int = Int(double)
print(int3)

var strBool = "true"
var bool = Bool(strBool)
print(bool!)
