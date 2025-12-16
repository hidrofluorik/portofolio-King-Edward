package view;

import javax.swing.*;
import java.awt.*;
import controller.loginController;

/**
 *
 * @author Acer
 */
public class loginview extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(loginview.class.getName());
    private loginController control;
    
    // Components
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton buttonMasuk;
    private JButton buttonRegister;
    
    /**
     * Creates new form loginview
     */
    public loginview() {
        initComponents();
        init();
        this.control = new loginController(this);
    }
    
    public void init(){
        buttonMasuk.addActionListener(e -> control.login());
        buttonRegister.addActionListener(e -> openRegisterView());
    }
    
    private void openRegisterView() {
        new registerview().setVisible(true);
        this.dispose();
    }

    public JButton getButtonMasuk() {
        return buttonMasuk;
    }

    public void setButtonMasuk(JButton buttonMasuk) {
        this.buttonMasuk = buttonMasuk;
    }

    public JButton getButtonRegister() {
        return buttonRegister;
    }

    public void setButtonRegister(JButton buttonRegister) {
        this.buttonRegister = buttonRegister;
    }

    public JTextField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(JTextField passwordField) {
        this.passwordField = passwordField;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(JTextField usernameField) {
        this.usernameField = usernameField;
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Money Tracker - Login");
        setSize(500, 400);
        setLocationRelativeTo(null);
        
        // Main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(245, 245, 245));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Title
        JLabel titleLabel = new JLabel("LOGIN");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);
        
        // Username label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(usernameLabel, gbc);
        
        // Username field
        usernameField = new JTextField(20);
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(usernameField, gbc);
        
        // Password label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(passwordLabel, gbc);
        
        // Password field
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 1; gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(passwordField, gbc);
        
        // Login button
        buttonMasuk = new JButton("MASUK");
        buttonMasuk.setFont(new Font("Segoe UI", Font.BOLD, 14));
        buttonMasuk.setBackground(new Color(0, 123, 255));
        buttonMasuk.setForeground(Color.WHITE);
        buttonMasuk.setPreferredSize(new Dimension(150, 40));
        buttonMasuk.setFocusPainted(false);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(buttonMasuk, gbc);
        
        // Register section
        JPanel registerPanel = new JPanel(new FlowLayout());
        registerPanel.setBackground(new Color(245, 245, 245));
        
        JLabel registerLabel = new JLabel("Belum punya akun?");
        registerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        registerPanel.add(registerLabel);
        
        buttonRegister = new JButton("Register");
        buttonRegister.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        buttonRegister.setBackground(new Color(108, 117, 125));
        buttonRegister.setForeground(Color.WHITE);
        buttonRegister.setFocusPainted(false);
        registerPanel.add(buttonRegister);
        
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        mainPanel.add(registerPanel, gbc);
        
        add(mainPanel);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new loginview().setVisible(true));
    }
}