package org.firstinspires.ftc.teamcode;

public class HoloDir {
    public double a;
    public double b;
    public double c;
    public double d;

    private double power = 1;

    public HoloDir(int a, int b, int c, int d)
    {
        this.a = a*power;
        this.b = b*power;
        this.c = c*power;
        this.d = d*power;
    }

    public double getPower()
    {
      return power;
    }

    //Sets power for all motors
    public void setPower(double pow)
    {
        this.power = pow;
    }

    //Map for motor values
    public static HoloDir FORWARD = new HoloDir(-1, 1, 1, -1);
    public static HoloDir BACKWARD = new HoloDir(1, -1, -1, 1);
    public static HoloDir LEFT = new HoloDir(1, 1, -1, -1);
    public static HoloDir RIGHT = new HoloDir(-1, -1, 1, 1);
    public static HoloDir FORWARD_LEFT = new HoloDir(0, 1, 0, -1);
    public static HoloDir BACKWARD_LEFT = new HoloDir(1, 0, -1, 0);
    public static HoloDir FORWARD_RIGHT = new HoloDir(-1, 0, 1, 0);
    public static HoloDir BACKWARD_RIGHT = new HoloDir(0, -1, 0, 1);
}
