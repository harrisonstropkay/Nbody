import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Driver extends JFrame {
    // from DrawingGUI
    public JComponent canvas;						// handles graphics display
    protected int width, height;					// the size of the drawing window
    protected Timer timer;							// one delay-driven event
    private static final int delay = 20;			// default delay for the timer (milliseconds)

    protected Graphthing graphthing;

    public Driver(String title, int width, int height) {

        super(title);
        this.width = width;
        this.height = height;
        graphthing = new Graphthing(width, height);
        graphthing.makeRandom(8);

        // Create a canvas for drawing into, paintComponent() is called on repaint, add our object's draw function
        canvas = new JComponent() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                draw(g);  //our object's drawing method call
            }
        };

        canvas.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                handleMousePress(event.getPoint().x, event.getPoint().y);
            }
        });
        canvas.addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent event) {
                handleMouseMotion(event.getPoint().x, event.getPoint().y);
            }
        });
        addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent event) {
                handleKeyPress(event.getKeyChar());
            }
        });
        timer = new Timer(delay, new AbstractAction("update") {
            public void actionPerformed(ActionEvent e) {
                handleTimer();
            }
        });

        // Boilerplate to finish initializing the GUI to the specified size
        setSize(width, height);
        canvas.setPreferredSize(new Dimension(width, height));
        getContentPane().add(canvas);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);



    }

    /**
     * Start the timer running.
     */

    public void draw(Graphics g) {
        graphthing.draw(g);
    }

    /**
     * Method to handle a mouse press, to be overridden by subclasses.
     * @param x		x coordinate of mouse press
     * @param y		y coordinate of mouse press
     */
    public void handleMousePress(int x, int y) {
        graphthing.add(x, y);
        repaint();
    }

    /**
     * Method to handle mouse motion, to be overridden by subclasses.
     * @param x		x coordinate of mouse
     * @param y		y coordinate of mouse
     */
    public void handleMouseMotion(int x, int y) {
    }

    /**
     * Method to handle a key press, to be overridden by subclasses.
     * @param key	the key that was pressed
     */
    public void handleKeyPress(char key) {
    }

    /**
     * Method to respond to the timer going off, to be overridden by subclasses.
     */
    public void handleTimer() {
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Driver("test", 800,600);
            }
        });
    }
}
