import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ${DPudov} on 31.10.2016.
 */
public class Calculator {
    public static String generate(String date) throws InputException {
        Pattern pattern = Pattern.compile("^\\d\\d.\\d\\d.\\d\\d\\d\\d$");
        Matcher matcher = pattern.matcher(date);
        if (matcher.matches()) {

            int day = Integer.valueOf(date.substring(0, 2));
            System.out.printf("Day %d \n", day);
            int month = date.charAt(4) != '0' ? Integer.valueOf(date.substring(3, 5)) : Integer.valueOf(date.charAt(5));
            System.out.printf("Month %d\n", month);
            int year = Integer.valueOf(date.substring(6, 10));
            System.out.printf("Year %d\n", year);

            int monthCode = -1;
            int yearCode = -1;
            int centuryCode = -1;
            switch ((year / 100) % 4) {
                case 0:
                    centuryCode = 6;
                    break;
                case 1:
                    centuryCode = 4;
                    break;
                case 2:
                    centuryCode = 2;
                    break;
                case 3:
                    centuryCode = 0;
                    break;
            }
            switch (month) {
                case 1:
                    monthCode = 1;
                    break;
                case 2:
                    monthCode = 4;
                    break;
                case 3:
                    monthCode = 4;
                    break;
                case 4:
                    monthCode = 0;
                    break;
                case 5:
                    monthCode = 2;
                    break;
                case 6:
                    monthCode = 5;
                    break;
                case 7:
                    monthCode = 0;
                    break;
                case 8:
                    monthCode = 3;
                    break;
                case 9:
                    monthCode = 6;
                    break;
                case 10:
                    monthCode = 1;
                    break;
                case 11:
                    monthCode = 4;
                    break;
                case 12:
                    monthCode = 6;
                    break;
            }
            yearCode = (centuryCode + year % 100 + (year % 100) / 4) % 7;
            if (monthCode != -1 && yearCode != -1 && centuryCode != -1) {
                int weekDay = day + monthCode + yearCode;
                if (isLeap(year) && month <= 2) {
                    weekDay--;
                }
                weekDay %= 7;
                switch (weekDay) {
                    case 0:
                        System.out.println("Saturday");
                        return "Saturday";
                    case 1:
                        System.out.println("Sunday");
                        return "Sunday";
                    case 2:
                        System.out.println("Monday");
                        return "Monday";
                    case 3:
                        System.out.println("Tuesday");
                        return "Tuesday";
                    case 4:
                        System.out.println("Wednesday");
                        return "Wednesday";
                    case 5:
                        System.out.println("Thursday");
                        return "Thursday";

                    case 6:
                        System.out.println("Friday");
                        return "Friday";

                }
            } else {
                throw new InputException();
            }
        } else {
            throw new InputException();
        }
        throw new InputException();
    }

    private static boolean checkDate(int day, int month, int year) {
        if (month > 0 && month < 13) {
            switch (month) {
                case 1:
                    return day > 0 && day <= 31;
                case 2:
                    return (day > 0 && day <= 29 && isLeap(year)) || (day > 0 && day < 29 && !isLeap(year));
                case 3:
                    return day > 0 && day <= 31;
                case 4:
                    return day > 0 && day <= 30;
                case 5:
                    return day > 0 && day <= 31;
                case 6:
                    return day > 0 && day <= 30;
                case 7:
                    return day > 0 && day <= 31;
                case 8:
                    return day > 0 && day <= 31;
                case 9:
                    return day > 0 && day <= 30;
                case 10:
                    return day > 0 && day <= 31;
                case 11:
                    return day > 0 && day <= 30;
                case 12:
                    return day > 0 && day <= 31;
            }
        }
        return false;
    }

    private static boolean isLeap(int year) {
        return ((year % 4 == 0 && year % 100 != 0)) || ((year % 400) == 0);
    }
}
