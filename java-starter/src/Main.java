import java.awt.*;
import javax.swing.*;

/**
 * =====================================================================
 *  UI Challenge - Java Starter
 * =====================================================================
 *
 *  This is a NORMAL, perfectly usable sign-up form.
 *  Your job: make it awful. (It still has to work in the end!)
 *
 *  The form has lots of different Swing parts on purpose, so you have
 *  a bunch to mess with:
 *
 *    - text fields      (name, phone)
 *    - a slider         (age)
 *    - a dropdown       (favorite color)
 *    - radio buttons    (how to contact you)
 *    - a checkbox       (agree to terms)
 *    - buttons          (submit, clear)
 *    - a menu bar       (File, Help)
 *    - a status bar     (the text at the bottom)
 *
 *  Look for comments that start with   >>> IDEAS:  for ideas. (go figure lol)
 *
 *  Run:  javac -d out src/*.java   then   java -cp out Main
 * =====================================================================
 */
public class Main {

    // The window and every input are stored here so any method can use them.
    private static JFrame frame;
    private static JTextField nameField;
    private static JTextField phoneField;
    private static JSlider ageSlider;
    private static JLabel ageValueLabel;
    private static JComboBox<String> colorBox;
    private static JRadioButton emailRadio;
    private static JRadioButton textRadio;
    private static JRadioButton pigeonRadio;
    private static JCheckBox termsCheckBox;
    private static JButton submitButton;
    private static JButton clearButton;
    private static JLabel statusLabel;
    

    public static void main(String[] args) {
        // Swing windows should be created on the "Event Dispatch Thread".
        SwingUtilities.invokeLater(Main::createAndShowGui);
        
    }

    // ------------------------------------------------------------------
    //  Building the window
    // ------------------------------------------------------------------
    private static void createAndShowGui() {
        frame = new JFrame("A V e r y Normal Sign-Up Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 480);
        frame.setLocationRelativeTo(null); // center on screen
        // >>> IDEAS: what if the window was 200x200? Or kept moving?

        frame.setJMenuBar(buildMenuBar());

        JPanel form = new JPanel(new GridLayout(0, 2, 10, 12)); // 0 rows = as many as needed
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // --- Name ---------------------------------------------------
        nameField = new JTextField();
        form.add(new JLabel("Name:"));
        form.add(nameField);
        // >>> IDEAS: what if every letter typed came out backwards?

        // --- Phone --------------------------------------------------
        phoneField = new JTextField();
        form.add(new JLabel("Phone number:"));
        form.add(phoneField);
        // >>> IDEAS: phone number via slider? 10 dropdowns? A math quiz?

        // --- Age (slider) -------------------------------------------
        ageSlider = new JSlider(0, 120, 18);
        ageValueLabel = new JLabel("Age: 18");
        ageSlider.addChangeListener(e -> ageValueLabel.setText("Age: " + ageSlider.getValue()));
        form.add(ageValueLabel);
        form.add(ageSlider);
        // >>> IDEAS: what if the slider went 0 to 1,000,000? Or backwards?

        // --- Favorite color (dropdown) ------------------------------
        String[] colors = {"Red", "Orange", "Yellow", "Green", "Blue", "Purple"};
        colorBox = new JComboBox<>(colors);
        form.add(new JLabel("Favorite color:"));
        form.add(colorBox);
        // >>> IDEAS: 500 colors? Names that dont match the color?

        // --- Contact method (radio buttons) -------------------------
        emailRadio = new JRadioButton("Email", true);
        textRadio = new JRadioButton("Text");
        pigeonRadio = new JRadioButton("Carrier pigeon");
        ButtonGroup contactGroup = new ButtonGroup(); // only one can be picked
        contactGroup.add(emailRadio);
        contactGroup.add(textRadio);
        contactGroup.add(pigeonRadio);

        JPanel contactPanel = new JPanel(new GridLayout(0, 1));
        contactPanel.add(emailRadio);
        contactPanel.add(textRadio);
        contactPanel.add(pigeonRadio);
        form.add(new JLabel("Contact me by:"));
        form.add(contactPanel);
        // >>> IDEAS: what if picking one picked a different one?

        // --- Terms (checkbox) ---------------------------------------
        termsCheckBox = new JCheckBox("I agree to the terms");
        form.add(new JLabel(""));
        form.add(termsCheckBox);
        // >>> IDEAS: un-check itself after 3 seconds? 

        // --- Buttons ------------------------------------------------
        submitButton = new JButton("Submit");
        clearButton = new JButton("Clear");
        submitButton.addActionListener(e -> onSubmit());
        clearButton.addActionListener(e -> onClear());
        form.add(clearButton);
        form.add(submitButton);

        // >>> IDEAS: swap their labels? Make Submit run away?
        // DELETE the // to show the example add // before next line to remove it:
        Example.makeRunAway(submitButton);

        // --- Status bar ---------------------------------------------
        statusLabel = new JLabel(" Fill out the form and press Submit.");
        statusLabel.setBorder(BorderFactory.createEtchedBorder());

        frame.setLayout(new BorderLayout());
        frame.add(form, BorderLayout.CENTER);
        frame.add(statusLabel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private static JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);
        // >>> IDEAS: an "Exit" that asks "Are you sure?" 10 times?

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(frame,
                "A V e r y Normal Sign-Up Form v1.0\nNothing weird here."));
        helpMenu.add(aboutItem);
        // >>> IDEAS: "Help" that is extremely unhelpful?

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        return menuBar;
    }

    // ------------------------------------------------------------------
    //  What happens when buttons are pressed
    // ------------------------------------------------------------------
    private static void onSubmit() {
        String error = validateForm();
        if (error != null) {
            setStatus(error);
            JOptionPane.showMessageDialog(frame, error, "Oops", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String summary = "Submitted!\n"
                + "Name: " + nameField.getText() + "\n"
                + "Phone: " + phoneField.getText() + "\n"
                + "Age: " + ageSlider.getValue() + "\n"
                + "Favorite color: " + colorBox.getSelectedItem() + "\n"
                + "Contact by: " + getContactMethod();

        setStatus("Submitted successfully!");
        JOptionPane.showMessageDialog(frame, summary);
        // >>> IDEAS: a fake loading bar first? A "Are you REALLY sure?"

    }

    private static void onClear() {
        nameField.setText("");
        phoneField.setText("");
        ageSlider.setValue(18);
        colorBox.setSelectedIndex(0);
        emailRadio.setSelected(true);
        termsCheckBox.setSelected(false);
        setStatus("Form cleared.");
        // >>> IDEAS: what if Clear... didn't clear? Or cleared slowly?


    }

    /**
     * Checks the form. Returns an error message, or null if everything is fine.
     * >>> IDEAS: rulsnt? "Name must contain a vowel and a
     *     prime number." Just make sure SOME input can pass.
     */
    private static String validateForm() {
        if (nameField.getText().trim().isEmpty()) {
            return "Please enter your name.";
        }
        String digits = phoneField.getText().replaceAll("[^0-9]", "");
        if (digits.length() != 10) {
            return "Phone number must have 10 digits.";
        }
        if (!termsCheckBox.isSelected()) {
            return "You must agree to the terms.";
        }
        return null; // all good
    }

    private static String getContactMethod() {
        if (emailRadio.isSelected()) return "Email";
        if (textRadio.isSelected()) return "Text";
        return "Carrier pigeon";
    }

    private static void setStatus(String message) {
        statusLabel.setText(" " + message);
    }
}
