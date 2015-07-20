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

public class FloodlampRainbow2
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
//      FadeIn(0, 0, 0, 255, 1);

      Red();
      Wait(200);

      Black();
      Wait(25);

//      Orange();
      DarkOrange();
      Wait(200);

      Black();
      Wait(25);

      Yellow();
      Wait(200);

      Black();
      Wait(25);

      Green();
      Wait(200);

      Black();
      Wait(25);

      Blue();
      Wait(200);

      Black();
      Wait(25);

//      Violet();
      DarkViolet();
      Wait(200);

      Black();
      Wait(25);

      Wait (200);
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

  static void DarkOrange()
  {
    Red(per / 2);
    Green(per / 4);
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

  static void DarkViolet()
  {
    Red(per / 4);
    Green(0);
    Blue(per / 2);
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

/*
  static void FadeIn(int fromR, int fromG, int fromB, int toR, int toG, int toB, int count, int step, int delay)
  {



    for(int i = 0; i < count; i++)
    {
      Red(r);
      Green(g);
      Blue(b);

      r++;
      g++;
      b++;

      Wait(delay);
    }
  }
*/




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