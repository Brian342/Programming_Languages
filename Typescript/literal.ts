// literal - is a textual representation (notation) of a value as it is written in source code
let direction: "North" | "East" | "West" | "South"

direction = "North"

let responseCode: 200 | 404 | 201

// enums (Enumeration) - enables developers to establish a collection of named constants (enumerators) 
// each linked with an integer value

enum size {
    small,
    medium,
    large
}

var Size: size = size.small

if (Size === size.small){

}

enum Direction {
    up = "UP",
    Down = "DOWN",
    Left = "LEFT",
    Right = "RIGHT"
}

// enums are treated as data types and you can use them to create sets of constants for use with variables and properties