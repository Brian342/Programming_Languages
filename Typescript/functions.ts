function add(x:number, y:number): number | string{
    if(x == 0){
        return "Invalid"
    }
    return x + y
}

const result  = add(1, 2)