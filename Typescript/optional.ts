// optional chaining and Bang
// Question mark & Exclamation point operators allows us to check 
// and deal with undefined values withing Typescript

const arr = [{name: "tim"}, {name: "joe"}, {name: "jane"}]

const el = arr.pop()?.name
// pop() removes/returns the last element from the array
//? operator is checking and then moving forward


// Exclamation point(!) operator tells the compiler to ignore 
// the possibility of it being undefined it is forcing us to move forward
const arr2 = [[{name:"tim"}]]
const el2 = arr2.pop()!.pop()!.name


