package sprint;


// public class AreaCalculator{
//     public static double squareArea(double ... numbers){
//         double sides = 0;
//         for(double number: numbers){
//             sides += number;
//         }
//         return Math.pow(sides, 2);
//     }
//     public static double rectangeArea(double ... numbers){
//         double side = 0;
//         for(double number: numbers){
//             side += number;
//         }
//         return side * side;
//     }
//     public static double circleArea(boolean option, double ... numbers){
//         double side = 0;
//         final double cal  = Math.PI;
//         for(double number: numbers){
//             side += number;
//         }
//         return Math.pow(cal * side, 2);
//     }

//     public static double invalidCircleArea(boolean option, double ... numbers){
//         return Double.NaN;
//     }
    
// }

public class AreaCalculator{
    public static double calculateArea(double side){
    return Math.pow(side, 2);
    } 
    public static double calculateArea(double side1, double side2){
        return side1 * side2;
    }
     public static double calculateArea(double radius, boolean option){
        if(!option){
            return Double.NaN;
        }
         final double cir = Math.PI;

        double res = cir * Math.pow(radius, 2);
    
        return Math.round(res * 100.0) / 100.0;
    }
    public static double calculateArea(boolean option,  double radius){
        return calculateArea(radius);
    }
}
       
    
