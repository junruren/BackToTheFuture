import java.util.Scanner;

public class BTTF
{
  // Constants
  private final static String TOGGLE = "t", 
                              DEST_TIME = "d", 
                              CHANGE = "c", 
                              SHOW = "s",
                              QUIT = "q";

  public static void main(String[] args)
  {
    // Construct a Scanner Object 
    // that reads user's input from keyboard (System.in)
    Scanner sc = new Scanner( System.in );

    // Construct a DeLorean
    DeLorean outatime = new DeLorean();

    // Print the welcome message
    printWelcome();
    // Print the current status of DeLorean
    System.out.println( outatime );
    // Read user's command
    do
    {
      // Print the usage
      String command = printUsage( sc );
      switch ( command )
      {
        case TOGGLE:
          outatime.timecircuitSwitch();
          outatime.update( 0 );
          break;
        case DEST_TIME:
          MyTime destTime;
          try
          {
            destTime = readTime( sc );
          }
          catch (IllegalArgumentException e)
          {
            System.err.println( e );
            break;
          }
          outatime.setDestinationTime( destTime );
          outatime.update( 0 );
          break;
        case CHANGE:
          int speedChange = readSpeed( sc );
          outatime.update( speedChange );
          break;
        case SHOW:
          outatime.update( 0 );
          break;
        case QUIT:
          return;
        default:
          break;
      } // End Switch
    } while ( true );
  } // End Main

  private static void printWelcome()
  {
    String message = 
      "Good evening!\n" + 
      "My name is Dr. Emmett Brown.\n" +
      "My friend Marty McFly over slept,\n" + 
      "so now I need you to help me test my latest invention:\n" +
      "The DeLorean Time Machine...\n" +
      "Why not give a try?\n";
    System.out.println( message );
    try
    {
      Thread.sleep(3000);
    }
    catch (InterruptedException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private static String printUsage( Scanner sc )
  {
    String usage = 
      "\tt - toggle the Time Circuit Switch\n" +
      "\td - destination time setting\n" + 
      "\tc - change speed\n" + 
      "\ts - show current status\n" + 
      "\tq - quit\n" +
      "[Please enter a command:] ";
    System.out.print( usage );
    return sc.next();
  }

  private static MyTime readTime( Scanner sc ) throws IllegalArgumentException
  {
    System.out.print("\n\t[Year:] ");
    int year = sc.nextInt();
    System.out.print("\n\t[Month (1-12):] ");
    int month = sc.nextInt();
    System.out.print("\n\t[Date (Depends on your month):] ");
    int date = sc.nextInt();
    System.out.print("\n\t[Hour (0-23):] ");
    int hour = sc.nextInt();
    System.out.print("\n\t[Minute (0-59):] ");
    int minute = sc.nextInt();
    return new MyTime( year, month, date, hour, minute );
  }

  private static int readSpeed( Scanner sc )
  {
    System.out.print("\n\t[Speed Change in MPH:] ");
    int speedChange = sc.nextInt();
    return speedChange;
  }
} //End of BTTF class