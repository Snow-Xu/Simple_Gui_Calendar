// Event class represent an event with time interval and enent description

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Event {
    private long start;
    private long end;
    private long today;
    private long timeInterval;
    private String event;
    private int year;

    private Calendar cal;

    public Event(int sYear, int sMonth, int sDate, int sHour, int sMinute, int sSecond, int eHour, int eMinute, int eSecond, String event) {
        //cal = new GregorianCalendar();
        //cal.set(year, month, date, hour, minute, second);
        start = sSecond + 100*sMinute + 10000*sHour + 1000000*sDate + 100000000*sMonth;
        end = eSecond + 100*eMinute + 10000*eHour + 1000000*sDate + 100000000*sMonth;
        this.event = event;
        this.year = sYear;
        this.today = start / 10000000 * 10000000;
    }

    public boolean isNOTConflictWith(Event e) {
        if (this.end <= e.start) {
            return true;
        } else if (this.start >= e.end) {
            return true;
        }
        
        /** test comflict
         *  System.out.println("this.start: "+this.start + " this.end: " + this.end);
        System.out.println("     start: "+e.start + "      end: " + e.end);
        */
        return false;
    }

    public Event(long start,long end, String event) {
        this.start = start;
        this.end = end;
        this.event = event;
    }

    public long getStartTime() {
        return start;
    }

    public long getEndTime() {
        return end;
    }

    public String getEventString() {
        return event;
    }

    public long getTimeInterval() {
        return timeInterval;
    }
    public long getToday() {
        return today;
    }

}