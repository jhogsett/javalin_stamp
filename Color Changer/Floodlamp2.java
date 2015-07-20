import stamp.core.*;
import java.util.*;
import java.io.*;

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

public class Floodlamp2
{

  // Put variables here.
  static int myVar;

  static final int time = 256;

  static final int mask = 0x03;
  static final int div = time / mask;
  static final int shift = 2;

  static PWM pwmR = new PWM(CPU.pin0);
  static PWM pwmG = new PWM(CPU.pin1);
  static PWM pwmB = new PWM(CPU.pin2);

  public static void main()
  {
    // Your code goes here.

    CPU.writePort(CPU.PORTA, (byte)255);

    pwmR.start();
    pwmG.start();
    pwmB.start();

    Random r = new Random();


    while(true)
    {
      int j;
      int k;
      int l;

      int i;
      do
      {
        i = r.next();

        j = ((i >> (0 * shift)) & mask) * div;
        k = ((i >> (1 * shift)) & mask) * div;
        l = ((i >> (2 * shift)) & mask) * div;
      }
      while(j == k && k == l);

      // System.out.println(j);
      // System.out.println(k);
      // System.out.println(l);

      Red(j);
      Green(k);
      Blue(l);

      On();

      Wait(i >> 9);

      Off();

      //Wait(5);

    }
  }

  static void Wait(int nhundredths)
  {
    CPU.delay(105 * nhundredths);
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

  static void Off()
  {
    pwmR.stop();
    pwmG.stop();
    pwmB.stop();
    CPU.writePort(CPU.PORTA, (byte)255);
  }

  static void On()
  {
    pwmR.start();
    pwmG.start();
    pwmB.start();
  }

}