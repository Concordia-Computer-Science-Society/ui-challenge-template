import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.*;

/**
 * =====================================================================
 *  Example - moving buttons
 * =====================================================================
 *  Won't run unless you call from Main.java.
 * =====================================================================
 */

public class Example {

    private static final Random random = new Random();

    /** The button jumps to a random spot whenever the mouse gets near it. */
    public static void makeRunAway(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Container parent = button.getParent();
                int maxX = Math.max(0, parent.getWidth() - button.getWidth());
                int maxY = Math.max(0, parent.getHeight() - button.getHeight());
                button.setLocation(random.nextInt(maxX + 1), random.nextInt(maxY + 1));
            }
        });
    }
}