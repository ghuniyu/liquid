package id.liqu.laundry.liquid;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by iamnubs on 29/03/2018.
 */

public class Utils {
    static SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy");

    public static String getToday() {
        return sdf.format(new Date());
    }

    public static String getHours() {
        return String.format("%02d:00", Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
    }

    public static String formatHours(int h, int m) {
        return String.format("%02d:%02d", h, m);
    }

    public static String formatDate(int y, int m, int d) {
        Calendar c = new GregorianCalendar();
        c.set(y, m, d);
        return sdf.format(c.getTime());
    }

    public static String formatDateSQL(Calendar c) {
        SimpleDateFormat sqlFormat = new SimpleDateFormat("Y-m-d H:i:s");
        return sqlFormat.format(c.getTime());
    }

    public static String formatRupiah(double amount) {
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(amount);
    }
}
