// Arrays - a collection of items or data stored in contiguous memory location, also known as database systems
var arr: number[] = [1, 2, 3]
var strarr: string[] = ["Hello", "Name"] 

// nested array
var strarr1: string [][] = [[], [], []]

// nested array withiout declaration
var strarr2 = [[], [], []]

// Tuples - fixed length array that has defined values for each position in the array
// const coord:[number, string] = [1, "2"]
// console.log(coord[0])


const coords: [number, number[]][] = [
    [1, [1, 2]],
    [-1, [3, 4]]
] 
const value = coords[0]?.[1]