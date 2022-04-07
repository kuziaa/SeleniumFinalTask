package org.antonkhmarun.supply;

import java.util.Calendar;
import java.util.Random;

public class RandomDataGenerator {

    Random random;

    public RandomDataGenerator() {
        random = new Random();
    }

    public int generateValidYear() {
        int minYear = 1900;
        int maxYear = Calendar.getInstance().get(Calendar.YEAR);

        return random.nextInt(maxYear - minYear) + minYear;
    }

    public int generateValidMonth(int year) {
        int maxMonth = 12;
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        if (year == currentYear) maxMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;

        return random.nextInt(maxMonth - 1) + 1;
    }

    public int generateValidDay(int year, int month) {
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
}
