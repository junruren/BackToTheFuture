import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * A class that represents the DeLorean Time Machine which can simulate the 
 * time traveling experience from the movie "Back to the Future"
 * @version 1.0
 * @author Junru (Thomas) Ren
 * @since 2016-04-08
 */ 
public class DeLorean
{
  // Data Field
  private MyTime destinationTime;
  private MyTime presentTime;
  private MyTime lastTimeDeparted;
  private int speed;
  private boolean timeCircuitOn;
  private LocalDateTime internalClock;

  /* Constants */
  private final static int TIME_TRAVEL_SPEED = 88;
  private final static String NULL_TIME = "--- -- ---- --:--";


 /**
  * The no-args constructor which initializes the precondition of a new DeLorean
  */
  public DeLorean()
  {
    destinationTime = new MyTime( getCurrentTime() );
    presentTime = new MyTime( getCurrentTime() );
    lastTimeDeparted = null;
    speed = 0;
    timeCircuitOn = false;
    internalClock = LocalDateTime.now();
  }

 /**
  * @return the value of the speed in MPH
  */
  public int getSpeed()
  {
    return speed;
  }

 /**
  * Toggle the Time Circuit. Set to OFF if it was ON; vice versa
  */
  public void timecircuitSwitch()
  {
    this.timeCircuitOn = !this.timeCircuitOn;
  }

 /**
  * Set the Destination Time.
  * @param time: the new Destination Time. Assume valid
  */
  public void setDestinationTime( MyTime time )
  {
    destinationTime = new MyTime( time );
  }

 /**
  * Change the speed of DeLorean based on user assigned speed change. Also 
  * updates the status of DeLorean, especially the Present Time. If the new 
  * speed is going to exceed TIME_TRAVEL_SPEED and the time circuit is set ON, 
  * the new speed stays at TIME_TRAVEL_SPEED and initiate time traveling. 
  * Otherwise, add the value of speedChange to the original speed of DeLorean. 
  * If speedChange is negative, the DeLorean slow down. If the resulting speed 
  * is negative, simply set speed to zero
  * @param speedChange: the change of speed in MPH
  */
  public void update( int speedChange )
  {
    updatePresentTime();
    int newSpeed = speed + speedChange;
    if ( newSpeed < 0 )
    {
      speed = 0;
    }
    else if ( newSpeed >= TIME_TRAVEL_SPEED && timeCircuitOn )
    {
      speed = TIME_TRAVEL_SPEED;
      System.out.print( this ); // Print the status before time traveling
      timeTravel();
      System.out.print( this ); // Print the status after time traveling
    }
    else
    {
      speed += speedChange;
      System.out.print( this ); // Print the status after speed change
    }
  }

 /**
  * @return the String representation of the MyTime
  */
  @Override
  public String toString()
  {
    String strDest = (destinationTime != null) ? destinationTime.toString() : NULL_TIME;
    String strPres = (presentTime != null) ? presentTime.toString() : NULL_TIME;
    String strLast = (lastTimeDeparted != null) ? lastTimeDeparted.toString() : NULL_TIME;
    String result = 
      "=======================\n" +
      " Current Speed: " + speed + " MPH\n" +
      " Timecircuit is " + (timeCircuitOn?"ON":"OFF") + "\n" +
      "-----------------------\n" +
      "   " + strDest + "   \n" +
      "    Destination Time   \n" +
      "-----------------------\n" +
      "   " + strPres + "   \n" +
      "     Present Time      \n" +
      "-----------------------\n" +
      "   " + strLast + "   \n" +
      "   Last Time Departed  \n" +
      "=======================\n\n";
    return result;
  }

 /**
  * Update the Present Time of DeLorean so that it ticks like real time even in
  * different time space.
  * THE IMPLEMENTATION IS STILL NOT PERFECT.
  */
  private void updatePresentTime()
  {
    LocalDateTime toDateTime = LocalDateTime.now();
    int years = (int)internalClock.until( toDateTime, ChronoUnit.YEARS);
    internalClock = internalClock.plusYears( years );

    int months = (int)internalClock.until( toDateTime, ChronoUnit.MONTHS);
    internalClock = internalClock.plusMonths( months );

    int days = (int)internalClock.until( toDateTime, ChronoUnit.DAYS);
    internalClock = internalClock.plusDays( days );

    int hours = (int)internalClock.until( toDateTime, ChronoUnit.HOURS);
    internalClock = internalClock.plusHours( hours );

    int minutes = (int)internalClock.until( toDateTime, ChronoUnit.MINUTES);

    presentTime = new MyTime( presentTime.getYear() + years, 
                              presentTime.getMonth() + months, 
                              presentTime.getDay() + days, 
                              presentTime.getHour() + hours, 
                              presentTime.getMinute() + minutes );
    internalClock = LocalDateTime.now();
  }

 /**
  * The very core of the DeLorean. Update the time values as following:
  * Set Last Time Departed as the previous Present Time
  * Set Present Time as the previous Destination Time
  * No change on the Destination Time
  */
  private void timeTravel()
  {
    this.lastTimeDeparted = new MyTime( this.presentTime );
    this.presentTime = new MyTime( this. destinationTime );
    // Print out time traveling message
    String message = 
      " * * * * * * * * * * * * * \n" +
      "  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~  \n" +
      "   + + + + + + + + + + +   \n" +
      "    # # # # # # # # # #    \n" +
      "   Just try  |             \n" +
      "             | to be       \n" +
      "        fan- |             \n" +
      "             | cy          \n" +
      "    # # # # # # # # # #    \n" +
      "   + + + + + + + + + + +   \n" +
      "  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~  \n" +
      " * * * * * * * * * * * * * \n";
    try
    {
		  Thread.sleep(1000);
	  }
    catch (InterruptedException e)
    {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
	  }
    System.out.println( message );
    try
    {
		  Thread.sleep(2000);
	  }
    catch (InterruptedException e)
    {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
	  }
    // Time Circuit auto OFF
    this.timeCircuitOn = false;
  }

  private MyTime getCurrentTime()
  {
	  LocalDateTime t = LocalDateTime.now();
    return new MyTime( t.getYear(),
                       t.getMonthValue(), 
                       t.getDayOfMonth(), 
                       t.getHour(),
                       t.getMinute() );
  }
}