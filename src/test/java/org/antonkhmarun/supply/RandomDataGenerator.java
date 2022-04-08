package org.antonkhmarun.supply;

import com.github.javafaker.Faker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class RandomDataGenerator {

    private static final Random random;
    private static final Faker faker;

    static {
        random = new Random();
        faker = new Faker();
    }

//    public static String getYearFromDate(Date date) {
//        int minYear = 1900;
//        int maxYear = Calendar.getInstance().get(Calendar.YEAR);
//
//        return random.nextInt(maxYear - minYear) + minYear;
//    }

    public static int generateValidMonth(int year) {
        int maxMonth = 12;
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        if (year == currentYear) maxMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;

        return random.nextInt(maxMonth - 1) + 1;
    }

    public static int generateValidDay(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        if (year == currentYear & month == currentMonth) maxDay = currentDay;

        return random.nextInt(maxDay - 1) + 1;

    }

    public static Date getValidDate() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date startDate = formatter.parse("01-01-1900");

        return faker.date().between(startDate, new Date());
    }
}
