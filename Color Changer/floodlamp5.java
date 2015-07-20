import stamp.core.*;
import java.util.*;
import java.io.*;
import RGBValueF.*;

public class floodlamp5
{
  static final int time = 256;

  static PWM pwmR = new PWM(CPU.pin0);
  static PWM pwmG = new PWM(CPU.pin1);
  static PWM pwmB = new PWM(CPU.pin2);

  static RGBValue R1 = new RGBValue(0x03, 2);
  //static RGBValue R2 = new RGBValue();

  public static void main()
  {
    CPU.writePort(CPU.PORTA, (byte)255);

    pwmR.start();
    pwmG.start();
    pwmB.start();

    On();

    while(true)
    {
      R1.GetNext();
      // R2.GetNext();

      Red(R1.red);
      Green(R1.green);
      Blue(R1.blue);

      for(int i = 0; i < 750; i++)
        ;

/*        On();
      {


        Wait(1);

        Off();

        Wait(1);

        Red(R2.red);
        Green(R2.green);
        Blue(R2.blue);

        On();

        Wait(1);

        Off();

        Wait(1);
      }
*/
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