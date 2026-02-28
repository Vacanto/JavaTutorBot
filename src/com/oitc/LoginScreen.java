package com.oitc;

import javax.swing.*;
import java.awt.*;

public class LoginScreen {

    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginScreen() {

        frame = new JFrame("Login - Java Tutor Bot");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        panel.add(loginButton);
        panel.add(registerButton);

        frame.add(panel, BorderLayout.CENTER);

        loginButton.addActionListener(e -> login());
        registerButton.addActionListener(e -> {
            frame.dispose();
            new RegistrationScreen();
        });

        frame.setVisible(true);
    }

    private void login() {

        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        String hashedPassword = PasswordUtil.hashPassword(password);

        try (var connection = DBConnection.getConnection();
             var statement = connection.prepareStatement(
                     "SELECT * FROM users WHERE username=? AND password=?")) {

            statement.setString(1, username);
            statement.setString(2, hashedPassword);

            var result = statement.executeQuery();

            if (result.next()) {

                frame.dispose();
                new JavaTutorBotGUI(username);

            } else {
                JOptionPane.showMessageDialog(frame,
                        "Invalid Credentials",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame,
                    "Database Error",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new LoginScreen();
    }
}