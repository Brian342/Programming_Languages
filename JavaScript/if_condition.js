/*IF Condition - if a condition is true, execute some code 
                 if not, do something else */

let age = 19
let hasLicence = false

if(age>= 18){
    console.log("You are old enough to drive")
    if(hasLicence){
        console.log("You have your licence")
    }else{
        console.log("You dont have a licence")
    }
}else{
    console.log("You must be 18+ to have a licence!!")
}
