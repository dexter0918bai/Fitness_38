package edu.neu.fitness_38;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Date {

    public static List<String> getData() {
        ArrayList<String> dates = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, 1 - 1);

        for (int i = 0; i < 366; i++) {
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH) + 1, 00, 00, 00);
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
            String format = sdf.format(cal.getTime());
            dates.add(format);
        }

        return dates;
    }
}
