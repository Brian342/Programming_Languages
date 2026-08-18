/*You need to implement a class named GiveMeThree with a method returnThree that:

Has a return type of int.
Returns the integer value 3. */
public class GiveMeThree {
    int value = 3;
    public int returnThree(){
        return value;  
         
    }
    public static void main(String[] args) {
        GiveMeThree gmt = new GiveMeThree();
        System.out.println(gmt.returnThree());
    }
}
