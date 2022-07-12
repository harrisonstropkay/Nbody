
//much credit to Chris Bailey-Kellogg for GUI and listeners

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Drive extends JFrame {

    public JComponent canvas;						// handles graphics display
    protected int width, height;					// the size of the drawing window
    protected Timer timer;							// one delay-driven event
    private static final int delay = 30;			// default delay for the timer (milliseconds)

    private NBody nBody;


    public Drive(String title, int width, int height) {

        super(title);
        this.width = width;
        this.height = height;

        nBody = new NBody();

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
        canvas.addKeyListener(new KeyAdapter() {
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

        timer.start();
    }

    public void draw(Graphics g) {
        nBody.draw(g);
    }


    public void handleMousePress(int x, int y) {
        nBody.addToBodies(new Body(new Pair(x, y), new Color((int) (16777216 * Math.random())), false, 0));
        repaint();
    }


    public void handleMouseMotion(int x, int y) {
    }


    public void handleKeyPress(char key) {
    }


    public void handleTimer() {
        nBody.update();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Drive("test", 800,800);
            }
        });
    }
}
