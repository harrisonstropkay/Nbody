import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;



public class Body {

    private Pair loc;
    private Pair dir;
    private static final double g = 0.5;
    private List<Pair> trail;
    private Color color;
    private int bodyRadius = 5;

    private int trailRadius = 2;
    private boolean infiniteTrail;
    private int trailLength;




    public Body(Pair loc, Color color, boolean infiniteTrail, int trailLength) {
        this.loc = loc;
        dir = new Pair(0,0);         //Math.random() - 0.5,Math.random() - 0.5);
        trail = new ArrayList<>();
        this.color = color;

        this.infiniteTrail = infiniteTrail;
        this.trailLength = trailLength;
    }

    public Pair getLoc() {
        return loc;
    }

    public Double getX() {
        return loc.x;
    }

    public Double getY() {
        return loc.y;
    }

    public void addToTrail(Pair point) {
        trail.add(point);
        if (!infiniteTrail && trail.size() > trailLength)
            trail.remove(0);
    }

    public void updateDir(Set<Body> bodies) {
        for (Body body: bodies) {
            if (body != this) {
                double magnitude =  g / distanceSquared(this.getLoc(), body.getLoc());
                dir.x -= magnitude * (loc.x - body.getX());
                dir.y -= magnitude * (loc.y - body.getY());
            }
        }
        //cap maximum velocity for Bodies with near-0 distance
        if (dir.x > 50)
            dir.x = 50;
        if (dir.x < -50)
            dir.x = -50;
        if (dir.y > 50)
            dir.y = 50;
        if (dir.y < -50)
            dir.y = -50;

    }

    public void updateLoc() {
        addToTrail(new Pair(loc.x, loc.y));

        loc.x += dir.x;
        loc.y += dir.y;
    }

    public static double distanceSquared(Pair p1, Pair p2) {
        return Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2);
    }

    public void draw(Graphics g) {
        g.setColor(color);

        //draw body
        g.fillOval((int) (loc.x - bodyRadius), (int) loc.y - bodyRadius, bodyRadius * 2, bodyRadius * 2);

        //draw trail
        for (Pair point: trail) {
            g.fillOval((int) point.x - trailRadius, (int) point.y - trailRadius, trailRadius * 2, trailRadius * 2);
        }
        /* lines
        for (int i = 0; i < trail.size() - 1; i++) {
            g.drawLine((int) trail.get(i).x, (int) trail.get(i).y, (int) trail.get(i+1).x, (int) trail.get(i+1).y);

        }
         */


    }
}
