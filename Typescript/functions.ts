function add(x:number, y:number): number | string{
    if(x == 0){
        return "Invalid"
    }
    return x + y
}

const result  = add(1, 2)


function makeName(firstName: string, lastName: string, middleName?:string){
    if (middleName) return firstName + " " + middleName + " " + lastName
    return firstName + " " + lastName
}

const fullName = makeName("Tim", "James")



function Names(firstName: string, lastName: string, middleName:string = "middle"){
    if (middleName) return firstName + " " + middleName + " " + lastName
    return firstName + " " + lastName
}

const Name = makeName("Tim", "James")

function callFunc(func:(f: string, l:string, m?: string)=>string,
 param1: string, param2:string){


}
callFunc(makeName, "Tim", "James")
//bn