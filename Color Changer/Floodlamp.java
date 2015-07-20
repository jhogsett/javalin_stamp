import stamp.core.*;
import java.util.*;

/**
 * Put a one line description of your class here.
 * <p>
 * This comment should contain a description of the class. What it
 * is for, what it does and how to use it.
 *
 * You should rename the class and then save it in a file with
 * exactly the same name as the class.
 *
 * @version 1.0 Date
 * @author Your Name Here
 */

public class Floodlamp
{

  // Put variables here.
  static int myVar;

  static final int time = 256;

  static PWM pwmR = new PWM(CPU.pin0);
  static PWM pwmG = new PWM(CPU.pin1);
  static PWM pwmB = new PWM(CPU.pin2);

  public static void main()
  {
    // Your code goes here.

    CPU.writePort(CPU.PORTA, (byte)0);

    pwmR.start();
    pwmG.start();
    pwmB.start();

//    pwmR.update(240, 10);
//    pwmG.update(10, 240);
//    pwmB.update(240, 10);

    Red(10);
    Green(240);
    Blue(10);

    Random r = new Random();

/*
    for(int i = 0; i < time; i++)
    {
      Red(i);
      Green(i);
      Blue(i);

      CPU.delay(5000);
    }
*/

    while(true)
    {
/*
      System.out.println(Random.MAX_RAND);
      System.out.println(r.next() * time);
      System.out.println("");
*/

/*
      Red((r.next() & 0x7f) + 0x80);
      Green((r.next() & 0x7f) + 0x80);
      Blue((r.next() & 0x7f) + 0x80);
*/

      Red((r.next() & 0xff) + 0x00);
      Green((r.next() & 0xff) + 0x00);
      Blue((r.next() & 0xff) + 0x00);

      CPU.delay(32767);
    }
  }

  static void Cycle()
  {
    while(true)
    {
      for(int i = 0; i < time; i++)
      {
        for(int j = 0; j < time; j++)
        {
          for(int k = 0; k < time; k++)
          {
            Red(i);
            Green(j);
            Blue(k);
          }
        }
      }
    }
  }

  static void Red(int value)
  {
    pwmR.update(time - value, value);
  }

  static void Green(int value)
  {
    pwmG.update(time - value, value);
  }

  static void Blue(int value)
  {
    pwmB.update(time - value, value);
  }

}