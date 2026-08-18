import sprint.WeatherStation;
import java.io.IOException;

public class Main{
    public static void main(String[] args){
        WeatherStation weather = new WeatherStation();

        System.out.println("Inital State;");
        System.out.println(weather.getState());
        System.out.println();

        try{

            weather.updateStateFromFile("station_update.csv");

            System.out.println("State after loading station_update.csv");
            System.out.println(weather.getState());
        } catch (IOException e){
            System.err.println("Error reading csv file: " + e.getMessage());
        }

        System.out.println();
        System.out.println("Applying a raw string with a NULL value");
        weather.updateState("11.0\n12, NULL");
        System.out.print(weather.getState());
    }
}

//  import sprint.PrimeFinder;
//  import java.util.List;

//  public class Main{
//   public static void main(String[] args) {
//         int limit = 30;
//         List<Integer> primes = PrimeFinder.findPrimesUpTo(limit);
//         System.out.println(primes);
//     }
// }


// import sprint.AreaCalculator;


// public class Main{
//     public static void main(String[] args){
//         double squareArea = AreaCalculator.calculateArea(4);
//         System.out.println("Area of square: " + squareArea);

//         double rectangeArea = AreaCalculator.calculateArea(5, 10);
//         System.out.println("Area of rectangle: " + rectangeArea);

//         double circleArea = AreaCalculator.calculateArea(true, 7);
//         System.out.printf("Area of Circle %.2f", circleArea);
//         System.out.println();

//         double invalidCircleArea = AreaCalculator.calculateArea(false, 7);
//         System.out.println("Area of Circle boolean=False: " + invalidCircleArea);


//     }
// }



// import sprint.ArrayInitializer;

// public class Main {

//         public static void main(String[] args) {
//             ArrayInitializer initializer = new ArrayInitializer();
//             int[] result = initializer.fillArray(5);
//             for (int num : result) {
//                 System.out.print(num + " ");
//             }
//         }
//     }