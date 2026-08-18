package sprint;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekendCalculator {
    public static long countWeekendDays(LocalDate startDate, LocalDate endDate){
         if (startDate.isAfter(endDate)) {
            return 0;
        }

        long weekendCount = 0;
        LocalDate currentDate = startDate;
        
        while(!currentDate.isAfter(endDate)){
            DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
            if(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY){
                weekendCount++;
            }
            currentDate = currentDate.plusDays(1);

        }
        return weekendCount;
    }
    
}
