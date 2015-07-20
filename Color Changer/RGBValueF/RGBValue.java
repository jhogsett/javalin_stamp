package RGBValueF;

import stamp.core.*;
import java.util.*;
import java.io.*;

public class RGBValue
{
  // PWM period
  static final int TIME = 256;

  static final int DEF_QUANTIZATION = 2;

  Random R;

  // color values
  public int red;
  public int green;
  public int blue;

  // standard colors
  public static final RGBValue Black = new RGBValue(0, 0, 0);
  public static final RGBValue White = new RGBValue(255, 255, 255);
  public static final RGBValue Gray = new RGBValue(127, 127, 127);
  public static final RGBValue Red = new RGBValue(255, 0, 0);
  public static final RGBValue Green = new RGBValue(0, 255, 0);
  public static final RGBValue Blue = new RGBValue(0, 0, 255);
  public static final RGBValue Cyan = new RGBValue(0, 255, 255);
  public static final RGBValue Yellow = new RGBValue(255, 255, 0);
  public static final RGBValue Magenta = new RGBValue(0, 255, 255);
  public static final RGBValue Orange = new RGBValue(255, 127, 0);

  public RGBValue()
  {
    byte seed = EEPROM.read(0);
    R = new Random(seed);
    EEPROM.write(0, ++seed);
  }

  public RGBValue(int red, int green, int blue)
  {
    this();

    Set(red, green, blue);
  }

  public RGBValue(RGBValue valueFrom)
  {
    this(valueFrom.red, valueFrom.green, valueFrom.blue);
  }

  public void Set(int red, int green, int blue)
  {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  public void Scale(int red, int green, int blue, int scale)
  {
    //if(scale < 8)
    {
      int shift = 8 - scale;
      this.red = red >>> shift;
      this.green = green >>> shift;
      this.blue = blue >>> shift;
    }
  }

  public void Scale(RGBValue fromColor, int scale)
  {
    Scale(fromColor.red, fromColor.green, fromColor.blue, scale);
  }

  public void Scale(int scale)
  {
    Scale(this, scale);
  }

  public void GetNext(int quantization, boolean noBlack, boolean allDifferent)
  {
    // produce the mask based on the number of quantization bits
    int mask = 0xff >>> (8 - quantization);

//    System.out.println(mask);

    // the quantization multiplier
    int div = TIME / mask;

//    System.out.println(div);

    do
    {
      int i = R.next();

      red = ((i >> (0 * quantization)) & mask) * div;
      green = ((i >> (1 * quantization)) & mask) * div;
      blue = ((i >> (2 * quantization)) & mask) * div;
    }while(
      (noBlack && red == 0 && green == 0 && blue == 0)
      || (allDifferent && red == green && green == blue));
  }

  public void GetNext()
  {
    GetNext(DEF_QUANTIZATION, true, true);
  }

  public void GetNext(int quantization)
  {
    GetNext(quantization, true, true);
  }
}