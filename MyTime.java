import java.time.*;
/**
 * A class that represents date and time for the BTTF project
 * @version 1.0
 * @author Junru (Thomas) Ren
 * @since 2016-04-07
 */ 
public class MyTime
{
  // Data Field
  private int myYear;
  private int myMonth;
  private int myDay;
  private int myHour; // In 24hrs
  private int myMin;
  private String myMonthString; // String representation of Month

  /* Constants */
  // Months in numeric values
  private final static int JAN = 1;
  private final static int FEB = 2;
  private final static int MAR = 3;
  private final static int APR = 4;
  private final static int MAY = 5;
  private final static int JUN = 6;
  private final static int JUL = 7;
  private final static int AUG = 8;
  private final static int SEP = 9;
  private final static int OCT = 10;
  private final static int NOV = 11;
  private final static int DEC = 12;
  // The maximum days of a month
  private final static int THIRTY = 30;
  private final static int THIRTYONE = 31;
  // Months in String values
  private final static String STR_JAN = "JAN";
  private final static String STR_FEB = "FEB";
  private final static String STR_MAR = "MAR";
  private final static String STR_APR = "APR";
  private final static String STR_MAY = "MAY";
  private final static String STR_JUN = "JUN";
  private final static String STR_JUL = "JUL";
  private final static String STR_AUG = "AUG";
  private final static String STR_SEP = "SEP";
  private final static String STR_OCT = "OCT";
  private final static String STR_NOV = "NOV";
  private final static String STR_DEC = "DEC";

 /**
  * Set the default to the time when Doc sent his dog to the future of 1 minute later
  * 1:21 AM of Oct. 26, 1985
  */
  public MyTime()
  {
    myYear = 1985;
    myMonth = 10;
    myDay = 26;
    myHour = 1;
    myMin = 21;
    myMonthString = STR_OCT;
  }

 /**
  * Creates a MyTime with specified values
  * @param year: the year of the date
  * @param month: the month of the date [1-12]
  * @param day: the day of the date [depends on the month]
  * @param hour: the hour of the time [0-23]
  * @param min: the minute of the time [0-59]
  * @throws IllegalArgumentException if the Time is invalid
  */
  public MyTime( int year, 
                 int month, 
                 int day, 
                 int hour, 
                 int min) throws IllegalArgumentException
  {
    myYear = year;
    myMonth = month;
    myDay = day;
    myHour = hour;
    myMin = min;
    // Call the validation method to check if the time is valid
    // throws IllegalArgumentException if invalid
    validation();
  }

/**
  * Copy constructor of MyTime
  * @param anotherTime: another MyTime object to be copied
  * @throws IllegalArgumentException if the Time is invalid
  */
  public MyTime( MyTime anotherTime )
  {
    myYear = anotherTime.getYear();
    myMonth = anotherTime.getMonth();
    myDay = anotherTime.getDay();
    myHour = anotherTime.getHour();
    myMin = anotherTime.getMinute();
    // Call the validation method to check if the time is valid
    // throws IllegalArgumentException if invalid
    validation();
  }

 /**
  * @return the value of the year
  */
  public int getYear()
  {
    return myYear;
  }

 /**
  * @return the value of the month [1-12]
  */
  public int getMonth()
  {
    return myMonth;
  }

 /**
  * @return the value of the day [range depends on month]
  */
  public int getDay()
  {
    return myDay;
  }

 /**
  * @return the value of the hour [0-23]
  */
  public int getHour()
  {
    return myHour;
  }

 /**
  * @return the value of the minute [0-59]
  */
  public int getMinute()
  {
    return myMin;
  }

 /**
  * Sets the MyTime with designated values
  * @param year: the year of the date
  * @param month: the month of the date [1-12]
  * @param day: the day of the date [depends on the month]
  * @param hour: the hour of the time [0-23]
  * @param min: the minute of the time [0-59]
  * @throws IllegalArgumentException if the Time is invalid
  */
  public void setTime( int year, 
                       int month, 
                       int day, 
                       int hour, 
                       int min) throws IllegalArgumentException
  {
    myYear = year;
    myMonth = month;
    myDay = day;
    myHour = hour;
    myMin = min;
    // Call the validation method to check if the time is valid
    // throws IllegalArgumentException if invalid
    validation();
  }

 /**
  * Update the MyTime by increasing the change in milliseconds
  * @param msChange: the time change in milliseconds
  */
  public void clockTick( long msChange )
  {
    LocalDateTime before 
      = LocalDateTime.of( myYear, myMonth, myDay, myHour, myMin, 0, 0 );
    LocalDateTime after 
      = before.plusSeconds( msChange / 1000 );
    this.myYear = after.getYear();
    this.myMonth = after.getMonthValue();
    this.myDay = after.getDayOfMonth();
    this.myHour = after.getHour();
    this.myMin = after.getMinute();
    validation();
  }

 /**
  * Compare this myTime object and another myTime object
  * @return negative integer if this time is earlier than another time;
  *         positive integer if this time is later than another time;
  *         zero if they are the same
  */
  public int compareTo( MyTime anotherTime )
  {
    if ( this.getYear() != anotherTime.getYear() )
      return this.getYear() - anotherTime.getYear();
    else
    {
      // Years are same, compare months
      if ( this.getMonth() != anotherTime.getMonth() )
        return this.getMonth() - anotherTime.getMonth();
      else
      {
        // Months are same, compare Days
        if ( this.getDay() != anotherTime.getDay() )
          return this.getDay() - anotherTime.getDay();
        else
        {
          // Days are same, compare Hours
          if ( this.getHour() != anotherTime.getHour() )
            return this.getHour() - anotherTime.getHour();
          else
          {
            // Hours are same, compare minutes
            if ( this.getMinute() != anotherTime.getMinute() )
              return this.getMinute() - anotherTime.getMinute();
            else
            {
              // All values match, same myTime!
              return 0;
            }
          }
        }
      }
    }
  }

 /**
  * @return the String representation of the MyTime
  */
  @Override
  public String toString()
  {
    return (myMonthString + " " 
              + String.format( "%02d", myDay) + " "
              + String.format( "%04d", myYear) + " "
              + String.format( "%02d", myHour) + ":"
              + String.format( "%02d", myMin));
  }

 /** Private helper methods */
 /**
  * Helper method that checks if a newly initialized time is valid.
  * Also initialize the String values of months if valid
  * @throws IllegalArgumentException if the Time is invalid
  */
  private void validation() throws IllegalArgumentException
  {
    // First we need to check if we are in a leap year
    // If yes, we should allow Feb. 29
    // (statement) ? val1 : val2;
    // If the statement is true, val1 is selected; otherwise, val2
    int febUpperBound = ( isLeapYear(myYear) )? 29 : 28;
    // Then check if the date is valid
    // Case by case
    boolean dayIsInRange;
    switch ( myMonth )
    {
      case JAN:
        dayIsInRange = ((1 <= myDay) && (myDay <= THIRTYONE));
        myMonthString = STR_JAN;
        break;
      case FEB:
        dayIsInRange = ((1 <= myDay) && (myDay <= febUpperBound));
        myMonthString = STR_FEB;
        break;
      case MAR:
        dayIsInRange = ((1 <= myDay) && (myDay <= THIRTYONE));
        myMonthString = STR_MAR;
        break;
      case APR:
        dayIsInRange = ((1 <= myDay) && (myDay <= THIRTY));
        myMonthString = STR_APR;
        break;
      case MAY:
        dayIsInRange = ((1 <= myDay) && (myDay <= THIRTYONE));
        myMonthString = STR_MAY;
        break;
      case JUN:
        dayIsInRange = ((1 <= myDay) && (myDay <= THIRTY));
        myMonthString = STR_JUN;
        break;
      case JUL:
        dayIsInRange = ((1 <= myDay) && (myDay <= THIRTYONE));
        myMonthString = STR_JUL;
        break;
      case AUG:
        dayIsInRange = ((1 <= myDay) && (myDay <= THIRTYONE));
        myMonthString = STR_AUG;
        break;
      case SEP:
        dayIsInRange = ((1 <= myDay) && (myDay <= THIRTY));
        myMonthString = STR_SEP;
        break;
      case OCT:
        dayIsInRange = ((1 <= myDay) && (myDay <= THIRTYONE));
        myMonthString = STR_OCT;
        break;
      case NOV:
        dayIsInRange = ((1 <= myDay) && (myDay <= THIRTY));
        myMonthString = STR_NOV;
        break;
      case DEC:
        dayIsInRange = ((1 <= myDay) && (myDay <= THIRTYONE));
        myMonthString = STR_DEC;
        break;
      default:
        // If a month is out of the range, complain to user by throwing an
        // Exception
        throw new IllegalArgumentException( 
          "Month is not in the range of [1-12]" );
    } // End of switch ( myMonth )

    // Check if the day is in range
    if ( !dayIsInRange )
    {
      // Not in the range? Complain!
      throw new IllegalArgumentException( 
        "Day is not in the range of corresponding to the month" );
    } // Otherwise, carry on

    // Now check the hour
    if ( myHour < 0 || myHour > 23 )
    {
      // Not in the range? Complain!
      throw new IllegalArgumentException( 
        "Hour is not in the range of [0-23]" );
    }

    // Now check the minute
    if ( myMin < 0 || myMin > 59 )
    {
      // Not in the range? Complain!
      throw new IllegalArgumentException( 
        "Minute is not in the range of [0-59]" );
    }

    // If the method is executed until here without throwing any exceptions
    // The date and time are valid!
  }

 /**
  * Helper method that reads a value of year and check if it is a leap year
  * Algorithm:
  * if (year is not divisible by 4) then (it is a common year)
  * else if (year is not divisible by 100) then (it is a leap year)
  * else if (year is not divisible by 400) then (it is a common year)
  * else (it is a leap year)
  * @return true if myYear is a leap year; false otherwise
  */
  private boolean isLeapYear( int year)
  {
    if ( year % 4 != 0)
      return false;
    else if ( year % 100 != 0 )
      return true;
    else if ( year % 400 != 0 )
      return false;
    else
      return true;
  }

} // End of MyTime class