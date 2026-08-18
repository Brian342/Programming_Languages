package sprint;

public class StringConcatenator {
    
    public String concatenate(String... strings) {
        if (strings == null || strings.length == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (String str : strings) {
            if (str != null) {
                sb.append(str);
            }
        }

        return sb.toString();
    }
}
