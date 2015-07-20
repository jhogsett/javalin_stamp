import stamp.core.*;
import java.util.*;
import java.io.*;
import RGBValueF.*;

public class TwoLights
{
  public static RGBLight light1 = new RGBLight(CPU.pin5, CPU.pin6, CPU.pin7);
  public static RGBLight light2 = new RGBLight(CPU.pin10, CPU.pin9, CPU.pin8);

  public static int [] rainbowReds =   {255, 255, 255,  0,   0,   127};
  public static int [] rainbowGreens = {0,   127, 255,  255, 0,   0};
  public static int [] rainbowBlues =  {0,   0,   0,    0,   255, 127};

  public static int numRainbowColors = 6;

  public static void main()
  {
    //RGBValue c = new RGBValue();

    //RandomRainbowBeacon(3, false);

    StartUpSequence();

    Bouncer();
//    Bouncer2();
  }

  void AlternatingTwoColors(RGBValue color1, RGBValue color2, int wait)
  {
    while(true)
    {
      light1.Color(color1);
      light2.Color(color2);

      Wait(wait);

      light1.Color(color2);
      light2.Color(color1);

      Wait(wait);
    }
  }

  public static void RandomRainbowBeacon(int speed, boolean flash)
  {
    RGBValue c1 = new RGBValue(RGBValue.Black);
    RGBValue c2 = new RGBValue(RGBValue.Black);
    RGBValue c3 = new RGBValue(RGBValue.Black);
    RGBValue c4 = new RGBValue(RGBValue.Black);

    int [] reds =   {255, 255, 255,  0,   0,  64};
    int [] greens = {0,   64, 255,  255, 0,   0};
    int [] blues =  {0,   0,   0,    0,   255, 127};

    while(true)
    {
//      c1.GetNext(1);
//      c2.GetNext(1);

      for(int j = 0; j <= 5; j++)
      {
        c1.Set(reds[j], greens[j], blues[j]);
        c2.Set(reds[j], greens[j], blues[j]);

        for(int i = 0; i <= 8; i++)
        {
          c3.Scale(c1, i);
          c4.Scale(c2, i);

          light1.Color(c3);
          light2.Color(c4);

          Wait(speed);
        }

        Wait(speed * 13);

        if(flash)
        {
          for(int i = 0; i < 7; i++)
          {

            light1.Start();
            light2.Start();

//            light1.Color(c1);
//            light2.Color(c2);

            Wait(speed);

            light1.Stop();
            light2.Stop();


  //          light1.Color(c2);
  //          light2.Color(c1);

            Wait(speed);
          }
        }

        for(int i = 8; i >= 0; i--)
        {
          c3.Scale(c1, i);
          c4.Scale(c2, i);

          light1.Color(c3);
          light2.Color(c4);

          Wait(speed);
        }

        light1.Color(RGBValue.Black);
        light2.Color(RGBValue.Black);

        Wait(speed * 37);
      }
    }
  }

  public static void Bouncer()
  {
    final int max = 31;
    final int volume = 7;
    final int mask = 0x3f;
    final int negRange = mask >> 1;
    final int speed = 3;

    Bounce rb1 = new Bounce(0, 255, 127, 1);
    Bounce gb1 = new Bounce(0, 255, 127, 1);
    Bounce bb1 = new Bounce(0, 255, 127, 1);

    Bounce rb2 = new Bounce(0, 255, 127, 1);
    Bounce gb2 = new Bounce(0, 255, 127, 1);
    Bounce bb2 = new Bounce(0, 255, 127, 1);

    RGBValue color1 = new RGBValue();
    RGBValue color2 = new RGBValue();

    Random R = new Random();

    int times1 = 0;
    int times2 = 0;

    rb1.Set(R.next() & 0xff, (R.next() & mask) - negRange);
    gb1.Set(R.next() & 0xff, (R.next() & mask) - negRange);
    bb1.Set(R.next() & 0xff, (R.next() & mask) - negRange);

    rb2.Set(R.next() & 0xff, (R.next() & mask) - negRange);
    gb2.Set(R.next() & 0xff, (R.next() & mask) - negRange);
    bb2.Set(R.next() & 0xff, (R.next() & mask) - negRange);

    while(true)
    {
      if(times1 <= 0)
      {
        rb1.SetDelta((R.next() & mask) - negRange);
        gb1.SetDelta((R.next() & mask) - negRange);
        bb1.SetDelta((R.next() & mask) - negRange);
        times1 = (R.next() & 0xff) + 0x7f;
      }

      if(times2 <= 0)
      {
        rb2.SetDelta((R.next() & mask) - negRange);
        gb2.SetDelta((R.next() & mask) - negRange);
        bb2.SetDelta((R.next() & mask) - negRange);
        times2 = (R.next() & 0xff) + 0x7f;
      }

      //int w = R.next() & 0x07;

      while(times1 > 0 && times2 > 0)
      {
        times1--;
        times2--;

        rb1.Step();
        gb1.Step();
        bb1.Step();

        rb2.Step();
        gb2.Step();
        bb2.Step();

        int r1 = rb1.value;
        int g1 = gb1.value;
        int b1 = bb1.value;

        int r2 = rb2.value;
        int g2 = gb2.value;
        int b2 = bb2.value;

        if(g1 > max && b1 > max && rb1.IsGoingUp())
        {
          rb1.ReverseStep();
        }

        if(r1 > max && b1 > max && gb1.IsGoingUp())
        {
          gb1.ReverseStep();
        }

        if(r1 > max && g1 > max && bb1.IsGoingUp())
        {
          bb1.ReverseStep();
        }

        if(g2 > max && b2 > max && rb2.IsGoingUp())
        {
          rb2.ReverseStep();
        }

        if(r2 > max && b2 > max && gb2.IsGoingUp())
        {
          gb2.ReverseStep();
        }

        if(r2 > max && g2 > max && bb2.IsGoingUp())
        {
          bb2.ReverseStep();
        }

/*
        System.out.print(rb1.value);
        System.out.print(" ");
        System.out.print(gb1.value);
        System.out.print(" ");
        System.out.print(bb1.value);
        System.out.print(" ");
        System.out.print(rb2.value);
        System.out.print(" ");
        System.out.print(gb2.value);
        System.out.print(" ");
        System.out.println(bb2.value);
*/

        color1.Scale(rb1.value, gb1.value, bb1.value, volume);
        color2.Scale(rb2.value, gb2.value, bb2.value, volume);

        light1.Color(color1);
        light2.Color(color2);

        Wait(speed);
      }

//      light1.Stop();
//      light2.Stop();

//      Wait(100);

//      light1.Start();
//      light2.Start();
    }
  }

  public static void Bouncer2()
  {
    final int max = 31;
    final int volume = 7;
    final int mask = 0x3f;
    final int negRange = mask >> 1;
    final int speed = 4;

    Bounce rb1 = new Bounce(0, 255, 127, 1);
    Bounce gb1 = new Bounce(0, 255, 127, 1);
    Bounce bb1 = new Bounce(0, 255, 127, 1);

    Bounce rb2 = new Bounce(0, 255, 127, 1);
    Bounce gb2 = new Bounce(0, 255, 127, 1);
    Bounce bb2 = new Bounce(0, 255, 127, 1);

    RGBValue color1 = new RGBValue();
    RGBValue color2 = new RGBValue();

//    Random R = new Random(32352);
    Random R = new Random(7445);

    int times1 = 0;
    int times2 = 0;

    rb1.Set(R.next() & 0xff, (R.next() & mask) - negRange);
    gb1.Set(R.next() & 0xff, (R.next() & mask) - negRange);
    bb1.Set(R.next() & 0xff, (R.next() & mask) - negRange);

    rb2.Set(R.next() & 0xff, (R.next() & mask) - negRange);
    gb2.Set(R.next() & 0xff, (R.next() & mask) - negRange);
    bb2.Set(R.next() & 0xff, (R.next() & mask) - negRange);

    while(true)
    {
      if(times1 <= 0)
      {
        rb1.SetDelta((R.next() & mask) - negRange);
        gb1.SetDelta((R.next() & mask) - negRange);
        bb1.SetDelta((R.next() & mask) - negRange);
        times1 = (R.next() & 0xff) + 0x7f;
      }

      if(times2 <= 0)
      {
        rb2.SetDelta((R.next() & mask) - negRange);
        gb2.SetDelta((R.next() & mask) - negRange);
        bb2.SetDelta((R.next() & mask) - negRange);
        times2 = (R.next() & 0xff) + 0x7f;
      }

      //int w = R.next() & 0x07;

      while(times1 > 0 && times2 > 0)
      {
        times1--;
        times2--;

        boolean rm1 = !rb1.IsGoingUp() ? false : rb1.value > max;
        boolean gm1 = !gb1.IsGoingUp() ? false : gb1.value > max;
        boolean bm1 = !bb1.IsGoingUp() ? false : bb1.value > max;

        boolean rm2 = !rb2.IsGoingUp() ? false : rb2.value > max;
        boolean gm2 = !gb2.IsGoingUp() ? false : gb2.value > max;
        boolean bm2 = !bb2.IsGoingUp() ? false : bb2.value > max;

        boolean d1 = false;
        boolean d2 = false;

        if(!gm1 || !bm1)
        {
          rb1.Step();
          d1 = true;
        }

        if(!rm1 || !bm1)
        {
          gb1.Step();
          d1 = true;
        }

        if(!rm1 || !gm1)
        {
          bb1.Step();
          d1 = true;
        }

        if(!d1)
        {
          rb1.ReverseStep();
          gb1.ReverseStep();
          bb1.ReverseStep();
        }

        if(!gm2 || !bm2)
        {
          rb2.Step();
          d2 = true;
        }

        if(!rm2 || !bm2)
        {
          gb2.Step();
          d2 = true;
        }

        if(!rm2 || !gm2)
        {
          bb2.Step();
          d2 = true;
        }

        if(!d2)
        {
          rb2.ReverseStep();
          gb2.ReverseStep();
          bb2.ReverseStep();
        }

        color1.Scale(rb1.value, gb1.value, bb1.value, volume);
        color2.Scale(rb2.value, gb2.value, bb2.value, volume);

        light1.Color(color1);
        light2.Color(color2);

        Wait(speed);
      }
    }
  }

  static void StartUpSequence()
  {
    light1.Color(RGBValue.White);
    light2.Color(RGBValue.White);
    Wait(50);

    light1.Color(RGBValue.Black);
    light2.Color(RGBValue.Black);
    Wait(50);

/*
    for(int i = 0; i < 20; i++)
    {
      light1.Color(RGBValue.White);
      light2.Color(RGBValue.White);
      Wait(1);
      light1.Color(RGBValue.Black);
      light2.Color(RGBValue.Black);
      Wait(1);
    }

    light1.Color(RGBValue.Black);
    light2.Color(RGBValue.Black);
    Wait(50);

    for(int i = 0; i < 20; i++)
    {
      light1.Color(RGBValue.White);
      light2.Color(RGBValue.Black);
      Wait(1);
      light1.Color(RGBValue.Black);
      light2.Color(RGBValue.White);
      Wait(1);
    }

    light1.Color(RGBValue.Black);
    light2.Color(RGBValue.Black);
    Wait(50);
*/

    RGBValue color1 = new RGBValue();
    RGBValue color2 = new RGBValue();

    for(int i = 0; i < numRainbowColors; i++)
    {
      color1.Set(rainbowReds[i], rainbowGreens[i], rainbowBlues[i]);
      color2.Set(rainbowReds[i], rainbowGreens[i], rainbowBlues[i]);

      light1.Color(color1);
      light2.Color(color2);
      Wait(50);

      light1.Color(RGBValue.Black);
      light2.Color(RGBValue.Black);
      Wait(50);
    }

//    light1.Color(RGBValue.Black);
//    light2.Color(RGBValue.Black);
//    Wait(100);
  }

  static void Wait(int nhundredths)
  {
    CPU.delay(105 * nhundredths);
  }

}