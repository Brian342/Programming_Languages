/*Ternary operator = a shortcut to if() and else statements 
                    helps to assign a varibale based on a 
                    condition ? codeIfTrue : codeifFalse; */
        
// let age =21;
// let message = age >= 18 ? `You are an Adult`: `You are a minor`;

// console.log(message)

/*Switch = can be an efficient replacement to many else if statements */

let day = 2
switch(day){
    case 1:
        console.log("it is Monday")
        break;
    case 2:
        console.log("it is Tuesday")
        break;
    case 3:
        console.log("it is Wednesday")
        break;
    case 4:
        console.log("it is Thursday")
        break;
    case 5:
        console.log("it is Friday")
        break;
    case 6:
        console.log("it is Saturday")
        break;
    case 7:
        console.log("it is Sunday")
        break;
    default:
        console.log(`${day} is not a day`)

}


