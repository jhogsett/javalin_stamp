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

public class Floodlamp3
{

  // Put variables here.
  static int myVar;

  static final int per = 256;

  static final int mask = 0x01;
  static final int div = per / mask;
  static final int shift = 1;

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
      for(int i = 0; i < 13; i++)
      {
        Red();
        Wait(2);
        Black();
        Wait(2);
        Blue();
        Wait(2);
        Black();
        Wait(2);
      }

      Wait(50);

      for(int i = 0; i < 26; i++)
      {
        White();
        Wait(2);
        Black();
        Wait(2);
      }

      Wait(50);
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
      for(int i = 0; i < per; i++)
      {
        for(int j = 0; j < per; j++)
        {
          for(int k = 0; k < per; k++)
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
    pwmR.update(per - value, value);
  }

  static void Green(int value)
  {
    pwmG.update(per - value, value);
  }

  static void Blue(int value)
  {
    pwmB.update(per - value, value);
  }

  static void Red()
  {
    Red(per);
    Green(0);
    Blue(0);
  }

  static void Green()
  {
    Red(0);
    Green(per);
    Blue(0);
  }

  static void Blue()
  {
    Red(0);
    Green(0);
    Blue(255);
  }

  static void Cyan()
  {
    Red(0);
    Green(per);
    Blue(per);
  }

  static void Yellow()
  {
    Red(per);
    Green(per);
    Blue(0);
  }

  static void Magenta()
  {
    Red(per);
    Green(0);
    Blue(per);
  }

  static void Orange()
  {
    Red(per);
    Green(per / 2);
    Blue(0);
  }

  static void BabyBlue()
  {
    Red(0);
    Green(per / 2);
    Blue(per);
  }

  static void CoolYellow()
  {
    Red(per / 2);
    Green(per);
    Blue(0);
  }

  static void BrightCyan()
  {
    Red(0);
    Green(per);
    Blue(per / 2);
  }

  static void Pink()
  {
    Red(per);
    Green(0);
    Blue(per / 2);
  }

  static void Violet()
  {
    Red(per / 2);
    Green(0);
    Blue(per);
  }


  static void White()
  {
    Red(per);
    Green(per);
    Blue(per);
  }

  static void Black()
  {
    Red(0);
    Green(0);
    Blue(0);
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