package org.firstinspires.ftc.teamcode;

public class HoloDir {
    public double a;
    public double b;
    public double c;
    public double d;


    public HoloDir(int a, int b, int c, int d)
    {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public static HoloDir getRotatedDir(HoloDir base, double rot)
    {
        int newA = (int)((base.a + rot) / 2);
        int newB = (int)((base.b + rot) / 2);
        int newC = (int)((base.c + rot) / 2);
        int newD = (int)((base.d + rot) / 2);

        return new HoloDir(newA, newB, newC, newD);
    }

    //Constant directional values
    public static final HoloDir STATIONARY = new HoloDir(0, 0, 0, 0);

    public static final HoloDir FORWARD = new HoloDir(-1, 1, 1, -1);
    public static final HoloDir BACKWARD = new HoloDir(1, -1, -1, 1);
    public static final HoloDir LEFT = new HoloDir(1, 1, -1, -1);
    public static final HoloDir RIGHT = new HoloDir(-1, -1, 1, 1);
    //
    public static final HoloDir FORWARD_LEFT = new HoloDir(0, 1, 0, -1);
    public static final HoloDir BACKWARD_LEFT = new HoloDir(1, 0, -1, 0);
    public static final HoloDir FORWARD_RIGHT = new HoloDir(-1, 0, 1, 0);
    public static final HoloDir BACKWARD_RIGHT = new HoloDir(0, -1, 0, 1);
}
