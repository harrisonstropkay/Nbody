import java.awt.*;

public class Pair {
    public double x, y;

    public Pair(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point toPoint() {
        return new Point((int) x, (int) y);
    }
}