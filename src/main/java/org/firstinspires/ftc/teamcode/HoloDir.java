package org.firstinspires.ftc.teamcode;

public class HoloDir {
    public int a;
    public int b;
    public int c;
    public int d;

    public HoloDir(int a, int b, int c, int d)
    {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public static HoloDir FORWARD = new HoloDir(-1, 1, 1, -1);
    public static HoloDir BACKWARD = new HoloDir(1, -1, -1, 1);
    public static HoloDir LEFT = new HoloDir(1, 1, -1, -1);
    public static HoloDir RIGHT = new HoloDir(-1, -1, 1, 1);
    public static HoloDir FORWARD_LEFT = new HoloDir(0, 1, 0, -1);
    public static HoloDir BACKWARD_LEFT = new HoloDir(1, 0, -1, 0);
    public static HoloDir FORWARD_RIGHT = new HoloDir(-1, 0, 1, 0);
    public static HoloDir BACKWARD_RIGHT = new HoloDir(0, -1, 0, 1);
}
