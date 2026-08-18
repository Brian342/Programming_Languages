package javaPractice;
import java.time.LocalDate;
import java.time.DayOfWeek;

public class DayChecker {
    public static String checkDayType(LocalDate date){
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        switch (dayOfWeek){
            case SATURDAY:
            case SUNDAY:
                return "Weekend";
            case WEDNESDAY:
                return "Hump Day";
            default:
                return "Weekday";

        }
    }

    public static void main(String[] args) {
        LocalDate weekdayDate = LocalDate.of(2024, 8, 26);
        LocalDate weekendDate = LocalDate.of(2024, 8, 31);
        LocalDate wednesdayDate = LocalDate.of(2024, 8, 28);
        System.out.println(DayChecker.checkDayType(weekdayDate));
        System.out.println(DayChecker.checkDayType(weekendDate));
        System.out.println(DayChecker.checkDayType(wednesdayDate));

    }
}
