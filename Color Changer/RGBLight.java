import stamp.core.*;
import java.util.*;
import java.io.*;
import RGBValueF.*;

public class RGBLight
{
  //static final int time = 256; // causes more blinking

  static final int time = 255;
//  static final int time = 256;

  private int red;
  private int green;
  private int blue;

  private boolean redOn;
  private boolean greenOn;
  private boolean blueOn;

  private PWM pwmR;
  private PWM pwmG;
  private PWM pwmB;

  public RGBLight()
  {
    redOn = false;
    greenOn = false;
    blueOn = false;
  }

  public RGBLight(int redPin, int greenPin, int bluePin)
  {
    this();

    // for PNP devices
    CPU.writePin(redPin, true);
    CPU.writePin(greenPin, true);
    CPU.writePin(bluePin, true);

    pwmR = new PWM(redPin);
    pwmG = new PWM(greenPin);
    pwmB = new PWM(bluePin);

    // for PNP devices
    pwmR.stoppedState = true;
    pwmG.stoppedState = true;
    pwmB.stoppedState = true;

    red = 0;
    green = 0;
    blue = 0;
  }

  public void Stop()
  {
    pwmR.stop();
    pwmG.stop();
    pwmB.stop();

    redOn = false;
    greenOn = false;
    blueOn = false;
  }

  public void Start()
  {
    if(red != 0)
    {
      pwmR.start();
      redOn = true;
     }

    if(green != 0)
    {
      pwmG.start();
      greenOn = true;
     }

    if(blue != 0)
    {
      pwmB.start();
      blueOn = true;
     }
  }

  public void Color(int red, int green, int blue)
  {
    if(red == 0)
    {
      if(redOn)
      {
        pwmR.stop();
        redOn = false;
      }
    }
    else
    {
      if(!redOn)
      {
        pwmR.start();
        redOn = true;
      }

      pwmR.update(time - red, red);
      //pwmR.update(time, red);  // reduces dynamic range, looks bland

      this.red = red;
    }

    if(green == 0)
    {
      if(greenOn)
      {
        pwmG.stop();
        greenOn = false;
      }
    }
    else
    {
      if(!greenOn)
      {
        pwmG.start();
        greenOn = true;
      }

      pwmG.update(time - green, green);
      //pwmG.update(time, green);  // reduces dynamic range, looks bland

      this.green = green;
    }

    if(blue == 0)
    {
      if(blueOn)
      {
        pwmB.stop();
        blueOn = false;
      }
    }
    else
    {
      if(!blueOn)
      {
        pwmB.start();
        blueOn = true;
      }

      pwmB.update(time - blue, blue);
      //pwmB.update(time, blue);  // reduces dynamic range, looks bland

      this.blue = blue;
    }
  }

  public void Color(RGBValue color)
  {
    Color(color.red, color.green, color.blue);
  }
}