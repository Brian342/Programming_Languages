package javaPractice;

public class varargs {
    public static void main(String[] args){
        /* varargs = allow a method to accept a varying # of arguments
                    makes methods more flexible, no need for overloaded methods java will pack the arguments into an array.
                    ...(ellipsis)
        */

        System.out.println(add(1, 2, 3, 4));
        System.out.println(average(1, 2, 3, 4, 5, 6, 7));
    }
    static int add(int... numbers){

        int sum = 0;
        for(int number:numbers){
            sum += number;
        }
        return sum;

    }

    static double average(double... values){
        int avg = 0;
        for (double number: values){
            avg += (int) number;
        }
        return (double) avg / values.length;

    }
}
