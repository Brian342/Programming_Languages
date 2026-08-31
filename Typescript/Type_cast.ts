// any type allows for flexibility typing 
// but sacrifices type safety as it lacks compile time type checking

// you use "any type" typically when you're in a very complex 
// situation and you're not able to predict what the type of 
// the variables gonna be


let x: any = 1

x.lenth

// The unknown type is a type-safe counterpart to the any type
// Unknown type provides a powerful way to handle values of uncertain types
// while maintaining type safety
let y: unknown = 1;

if (typeof y == "number"){
    const result = x + 1;
} else if (typeof y == "string"){
    const result = y.length
}

// type casting
let z: unknown = 1

const result = (z as number) + 1

