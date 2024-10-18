package View;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import model.Employee;
import model.Hotel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class LoginScreen {
	
	private static LoginScreen instance;
	
	//Fields for username and password
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    
    
    //getters
    public JButton getLoginButton() {
        return loginButton;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }
    

    public LoginScreen() {
        usernameField = new JTextField(20); //Limiting the username field
        passwordField = new JPasswordField(20); //Limiting the password field
        loginButton = new JButton("Login");
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        initialize();
    }
    
    //There's only one login screen in this program
    public static LoginScreen getInstance() {
        if (instance == null) {
            instance = new LoginScreen();
        }
        return instance;
    }
    
    
    private void initialize() {
        JFrame frame = new JFrame("Login Screen");//new frame called login screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //images for the icon of the frame and the background.
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("Screenshot 2023-07-23 104619.png"));
        frame.setIconImage(imageIcon.getImage());
        ImageIcon backgroundImage = new ImageIcon(getClass().getResource("golden-mykonos-sunset-1024x640.jpg"));

        @SuppressWarnings("serial")
		JLabel backgroundLabel = new JLabel(backgroundImage) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(600, 400); // Set the size of the background image
            }
        };

        frame.setContentPane(backgroundLabel);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false); // Make the panel transparent

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10); // Add some spacing between components
        constraints.gridwidth = 2; // Components span 2 columns

        // Add the "Welcome" label
        JLabel welcomeLabel = new JLabel("Welcome");
        Font labelFont = welcomeLabel.getFont();
        welcomeLabel.setFont(labelFont.deriveFont(labelFont.getSize() + 18.0f)); // Increase font size by 18
        welcomeLabel.setForeground(Color.WHITE);

        // Add the "Welcome" label to the center of the content pane
        GridBagConstraints welcomeConstraints = new GridBagConstraints();
        welcomeConstraints.gridx = 0;
        welcomeConstraints.gridy = 0;
        welcomeConstraints.gridwidth = 2;
        welcomeConstraints.insets = new Insets(50, 0, 20, 0); // Add spacing between the "Welcome" label and other components
        panel.add(welcomeLabel, welcomeConstraints);

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        // Set font size for labels
        labelFont = usernameLabel.getFont();
        usernameLabel.setFont(labelFont.deriveFont(labelFont.getSize() + 6.0f)); // Increase font size by 6
        passwordLabel.setFont(labelFont.deriveFont(labelFont.getSize() + 6.0f)); // Increase font size by 6

        // Set the background of labels and text fields to be transparent
        usernameLabel.setOpaque(false);
        passwordLabel.setOpaque(false);
        usernameField.setOpaque(false);
        passwordField.setOpaque(false);

        // Set the foreground color (text color) to be white
        usernameLabel.setForeground(Color.WHITE);
        passwordLabel.setForeground(Color.WHITE);
        usernameField.setForeground(Color.WHITE);
        passwordField.setForeground(Color.WHITE);

        // Set the size of the username and password fields
        usernameField.setPreferredSize(new Dimension(200, 40));
        passwordField.setPreferredSize(new Dimension(200, 40));

        // Add the username label and field
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(usernameLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(usernameField, constraints);

        // Add the password label and field
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(passwordLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(passwordField, constraints);

        // Add the login button
        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(loginButton, constraints);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (isAdminLogin(username, password) || isEmployeeLogin(username, password)) {
                    // Pass the isAdmin value based on whether it's an admin login or employee login
                    boolean isAdmin = isAdminLogin(username, password);
                    showMainDashboard(username, isAdmin);
                } else {
                	// Not an admin and not an employee
                    showInvalidCredentialsMessage();
                }
            }
        });


        // Add the login panel to the center of the content pane
        frame.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.CENTER;
        frame.getContentPane().add(panel, gbc);
        
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setVisible(true);
    }

    
    public boolean isAdminLogin(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }
    
    //If an employee was added previously in the program, he will be saved to the allEmployees HashMap.
    //If he's there, he will be able to log in with his first name as username and ID as password.
    public boolean isEmployeeLogin(String username, String password) {
    	for(Map.Entry<String, Employee> entry : Hotel.getInstance().getAllEmployees().entrySet()) {
    		if(entry.getKey().equals(password) && entry.getValue().getFirstName().equals(username)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    
    private void showMainDashboard(String username, boolean isAdmin) {
        // Hide the login screen
        JFrame frame = (JFrame) SwingUtilities.getRoot(loginButton);
        frame.dispose();

        // Show the main dashboard
        new MainDashboard(username, isAdmin);
    }

    
    private void showInvalidCredentialsMessage() {
        JOptionPane.showMessageDialog(null, "Invalid credentials. Please try again.");
    }

    
    public void showLoginScreen() {
        JFrame frame = (JFrame) SwingUtilities.getRoot(loginButton);
        frame.setVisible(true);
    }
}
