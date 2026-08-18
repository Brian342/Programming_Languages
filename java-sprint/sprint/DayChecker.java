package sprint;

import java.time.*;

public class DayChecker {

public static String checkDayType(LocalDate date){
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        switch (dayOfWeek){
            case SATURDAY:
            case SUNDAY:
                return "Weekend";
            case WEDNESDAY:
                return "Hump Day!";
            default:
                return "Weekday";

        }
    }
}