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
  static final int time = 256;

  static PWM pwmR = new PWM(CPU.pin0);
  static PWM pwmG = new PWM(CPU.pin1);
  static PWM pwmB = new PWM(CPU.pin2);


  static RGBValue R1 = new RGBValue();
  static RGBValue R2 = new RGBValue();

  public void asdf()
  {
      RGBValue r1 = new RGBValue();
  }

  public static void main()
  {
    // Your code goes here.



    CPU.writePort(CPU.PORTA, (byte)255);

    pwmR.start();
    pwmG.start();
    pwmB.start();

    On();

    while(true)
    {
      //RGBValue r1 = new RGBValue();
      //RGBValue r2 = new RGBValue();

      Red(R1.red);
      Green(R1.green);
      Blue(R1.blue);

      Wait(10);

      Red(R2.red);
      Green(R2.green);
      Blue(R2.blue);

      R1.GetNext();
      R2.GetNext();

      Wait(10);
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

public class RGBValue
{
  static final int time = 256;

  static final int mask = 0x03;
  static final int div = time / mask;
  static final int shift = 2;

  static Random R = new Random();

  public int red;
  public int green;
  public int blue;

  public RGBValue()
  {
    GetNext();
  }

  public void GetNext()
  {
    do
    {
      int i = R.next();

      red = ((i >> (0 * shift)) & mask) * div;
      green = ((i >> (1 * shift)) & mask) * div;
      blue = ((i >> (2 * shift)) & mask) * div;
    }while(red == green && green == blue);
  }
}
