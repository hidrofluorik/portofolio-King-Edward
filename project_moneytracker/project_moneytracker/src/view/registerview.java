package view;

import javax.swing.*;
import java.awt.*;
import controller.registerController;

/**
 *
 * @author Acer
 */
public class registerview extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(registerview.class.getName());
    private registerController controller;
    
    // Components
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField nomorHpField;
    private JButton buttonRegister;
    private JButton buttonMasuk;

    /**
     * Creates new form registerview
     */
    public registerview() {
        initComponents();
        init();
        this.controller = new registerController(this);
    }
    
    private void init() {
        buttonRegister.addActionListener(e -> controller.register());
        buttonMasuk.addActionListener(e -> controller.backToLogin());
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

    public JTextField getNomorHpField() {
        return nomorHpField;
    }

    public void setNomorHpField(JTextField nomorHpField) {
        this.nomorHpField = nomorHpField;
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
        setTitle("Money Tracker - Register");
        setSize(500, 450);
        setLocationRelativeTo(null);
        
        // Main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(245, 245, 245));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Title
        JLabel titleLabel = new JLabel("REGISTER");
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
        
        // Nomor HP label
        JLabel nomorHpLabel = new JLabel("Nomor HP:");
        nomorHpLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(nomorHpLabel, gbc);
        
        // Nomor HP field
        nomorHpField = new JTextField(20);
        nomorHpField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 1; gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(nomorHpField, gbc);
        
        // Register button
        buttonRegister = new JButton("REGISTER");
        buttonRegister.setFont(new Font("Segoe UI", Font.BOLD, 14));
        buttonRegister.setBackground(new Color(40, 167, 69));
        buttonRegister.setForeground(Color.WHITE);
        buttonRegister.setPreferredSize(new Dimension(150, 40));
        buttonRegister.setFocusPainted(false);
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(buttonRegister, gbc);
        
        // Login section
        JPanel loginPanel = new JPanel(new FlowLayout());
        loginPanel.setBackground(new Color(245, 245, 245));
        
        JLabel loginLabel = new JLabel("Sudah punya akun?");
        loginLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        loginPanel.add(loginLabel);
        
        buttonMasuk = new JButton("Login");
        buttonMasuk.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        buttonMasuk.setBackground(new Color(108, 117, 125));
        buttonMasuk.setForeground(Color.WHITE);
        buttonMasuk.setFocusPainted(false);
        loginPanel.add(buttonMasuk);
        
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        mainPanel.add(loginPanel, gbc);
        
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
        java.awt.EventQueue.invokeLater(() -> new registerview().setVisible(true));
    }
}