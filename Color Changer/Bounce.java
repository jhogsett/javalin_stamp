import stamp.core.*;
import java.util.*;
import java.io.*;

public class Bounce
{
  public int min;
  public int max;
  public int value;
  public int delta;

  public Bounce(int min, int max, int value, int delta)
  {
    Set(min, max, value, delta);
  }

/*  public Bounce()
  {
    Bounce(Integer.MIN_VALUE + 1, Integer.MAX_VALUE - 1, 0, 1);
  } */

  public void Set(int min, int max, int value, int delta)
  {
    this.min = min;
    this.max = max;
    this.value = value;
    this.delta = delta;
  }

  public void Set(int value, int delta)
  {
    this.value = value;
    this.delta = delta;
  }

  public void Step()
  {
    value += delta;

    if(value < min || value > max)
    {
      ReverseStep();
    }
  }

  public void ReverseStep()
  {
    delta *= -1;
    Step();
  }

  public boolean IsGoingUp()
  {
    return(delta >= 0);
  }

  public boolean IsGoingDown()
  {
    return(delta <= 0);
  }

  public void SetDelta(int delta)
  {
    this.delta = delta;
  }

}