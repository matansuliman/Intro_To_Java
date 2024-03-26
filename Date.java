/**
 * This class represents a Date object
 * 
 * @author Matan Suliman
 * @version 31/10/2022
 */
public class Date
{
    //instance variables
    private int _day;
    private int _month;
    private int _year;
    
    //constants
    private static final int CONST_1 = 1, CONST_4 = 4, CONST_100 = 100; //numbers to validate leap year
    private static final int DAY_MAX = 31, DAY_MIN = 1, DAY_30 = 30, DAY_29 = 29, DEFAULT_DAY = 1; //days
    private static final int MONTH_MAX = 12, MONTH_MIN = 1, DEFAULT_MONTH = 1, FEB = 2, APR = 4, JUN = 6, SEP = 9, NOV = 11; //months
    private static final int YEAR_MIN = 1000, YEAR_MAX = 9999, DEFAULT_YEAR = 2000; //years
    
    /**
     * If the given date is valid - creates a new Date object, otherwise creates the date 1/1/2000
     * @param day the day in the month (1-31)
     * @param month the month in the year (1-12)
     * @param year the year (4 digits)
     */
    public Date(int day, int month, int year)
    {   
        //default date 01/01/2000
        _day = DEFAULT_DAY;
        _month = DEFAULT_MONTH;
        _year = DEFAULT_YEAR;
        if (isDateValid(day, month, year))
        {
            _day = day;
            _month = month;
            _year = year;
        }
    }
    
    /**
     * Copy constructor
     * @param other the Date to be copied
     */
    public Date(Date other)
    {
        _day = other.getDay();
        _month = other.getMonth();
        _year = other.getYear();
    }
    
    /**
     * Gets the day
     * @return the day
     */
    public int getDay()
    {
        return _day;
    }
    
    /**
     * Gets the month
     * @return the month
     */
    public int getMonth()
    {
        return _month;
    }
    
    /**
     * Gets the year
     * @return the year
     */
    public int getYear()
    {
        return _year;
    }
    
    /**
     * Set the day (only if date remains valid)
     * @param the day value to be set
     */
    public void setDay(int dayToSet)
    {
        if (isDateValid(dayToSet, _month, _year))
            _day = dayToSet;
    }
    
    /**
     * Set the month (only if date remains valid)
     * @param the month value to be set
     */
    public void setMonth(int monthToSet)
    {
        if (isDateValid(_day, monthToSet, _year))
            _month = monthToSet;
    }
    
    /**
     * Set the year (only if date remains valid)
     * @param the year value to be set
     */
    public void setYear(int yearToSet)
    {
        if (isDateValid(_day, _month, yearToSet))
            _year = yearToSet;
    }
    
    /**
     * Checks if date is valid.
     * @param day is the day of the date.
     * @param month is the month of the date.
     * @param year is the year of the date.
     * @return true if date is valid, otherwise false
     */
    public boolean isDateValid(int day, int month, int year)
    {
        //checks if day between 1-31, month between 1-12, year between 1000-9999.
        if ( DAY_MIN <= day && day <= DAY_MAX && MONTH_MIN <= month && month <= MONTH_MAX && YEAR_MIN <= year && year <= YEAR_MAX) 
        {
            boolean leapYear = false; //checks if year is leap year
            if (year % CONST_4 == 0)
            {
                if (year % CONST_100 == 0)
                    leapYear = (year % (CONST_4 * CONST_100) == 0);
                else
                    leapYear = true;
            }
            //checks if a day can be at a month and at a leap year
            switch (month)
            {    
                case FEB: // valid day 1-28, in leap year day can be 29
                    if (day == DAY_30 || day == DAY_MAX) //day 30 or 31
                        return false;
                    else if (day == DAY_29)//day 29
                        return leapYear;
                    else 
                        return true;
                case APR:// valid day 1-30
                case JUN:
                case SEP:
                case NOV:
                    return (day != DAY_MAX);
                default: // all other cases are valid
                    return true;
            }
        }
        else
            return false;
    }
    
    /**
     * Calculates the days passed since counting began
     * @param day is the day of the date.
     * @param month is the month of the date.
     * @param year is the year of the date.
     * @return days count
     */
    public int calculateDate(int day, int month, int year)
    {
        if (month < 3)
        {
            year--;
            month = month + 12;
        }
        return 365 * year + year/4 - year/100 + year/400 + ((month+1) * 306)/10 + (day - 62);
    }
    
    /**
     * Check if 2 dates are the same
     * @param other the date to compare this date to
     * @return true if the dates are the same, otherwise false
     */
    public boolean equals (Date other)
    {
        return _day == other.getDay() && _month == other.getMonth() && _year == other.getYear();
    }
    
    /**
     * Check if this date is before other date
     * @param other date to compare this date to
     * @return true if this date is before other date, otherwise false
     */
    public boolean before(Date other)
    {
        return calculateDate(_day, _month, _year) < calculateDate(other.getDay(), other.getMonth(), other.getYear());
    }
    
    /**
     * Check if this date is after other date
     * @param other date to compare this date to
     * @return true if this date is after other date, otherwise false
     */
    public boolean after(Date other)
    {
        return other.before(this);
    }
    
    /**
     * Calculates the difference in days between two dates
     * @param other the date to calculate the difference between
     * @return the number of days between the dates (non negative value)
     */
    public int difference(Date other)
    {
        return Math.abs(calculateDate(_day, _month, _year) - calculateDate(other.getDay(), other.getMonth(), other.getYear()));
    }
    
    /**
     * Returns a String that represents this date
     * @return String that represents this date in the following format: day (2 digits) / month(2 digits) / year (4 digits) for example: 02/03/1998
     */
    public String toString()
    {
        String strDay = _day + "";
        if (strDay.length() == CONST_1)//need to add a 0
            strDay = "0" + _day;
        String strMonth= _month + "";
        if (strMonth.length() == CONST_1)//need to add a 0
            strMonth = "0" + _month;
        return strDay + "/" + strMonth + "/" + _year;
    }
    
    /**
     * Calculate the date of tomorrow
     * @return the date of tomorrow
     */
    public Date tomorrow()
    {
        //first option is 1st day of the same month and year.
        if (isDateValid(_day + CONST_1, _month, _year))
           return (new Date(_day + CONST_1, _month, _year));
        //validity falied - next option is 1st day of the next month of same year and check validity.
        else if (isDateValid(CONST_1, _month + CONST_1, _year))
           return (new Date(CONST_1, _month + CONST_1, _year));
        //validity falied - last opstion is 1st day at the 1st month of the next year (assume year isnt 9999).
        else
           return (new Date(CONST_1, CONST_1, _year + CONST_1));
    }
}
