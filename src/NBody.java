import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class NBody {
    private Set<Body> bodies;

    public NBody() {
        bodies = new HashSet<>();
    }

    public void addToBodies(Body body) {
        bodies.add(body);
    }

    public void update() {
        for (Body body: bodies) {
            body.updateDir(bodies);
        }
        for (Body body: bodies) {
            body.updateLoc();
        }
    }

    public void draw(Graphics g) {
        update();
        for (Body body: bodies) {
            body.draw(g);
        }
    }
}