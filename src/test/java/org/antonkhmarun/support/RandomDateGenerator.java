package org.antonkhmarun.support;

import com.github.javafaker.Faker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RandomDateGenerator {

    private final Calendar calendar;

    public RandomDateGenerator() throws ParseException {
        Faker faker = new Faker();
        this.calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date startDate = formatter.parse("01-01-1900");
        Date date = faker.date().between(startDate, new Date());
        calendar.setTime(date);
    }

    public String getYear() {
        return new SimpleDateFormat("yyyy").format(calendar.getTime());
    }

    public String getMonth() {
        return String.valueOf(calendar.get(Calendar.MONTH) + 1);
    }

    public String getDay() {
        return String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
    }
}
