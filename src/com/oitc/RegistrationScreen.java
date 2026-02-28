package com.oitc;

import javax.swing.*;
import java.awt.*;

public class RegistrationScreen {

    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public RegistrationScreen() {

        frame = new JFrame("Register - Java Tutor Bot");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton registerButton = new JButton("Register");
        panel.add(new JLabel());
        panel.add(registerButton);

        frame.add(panel, BorderLayout.CENTER);

        registerButton.addActionListener(e -> register());

        frame.setVisible(true);
    }

    private void register() {

        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame,
                    "Fields cannot be empty!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String hashedPassword = PasswordUtil.hashPassword(password);

        try (var connection = DBConnection.getConnection();
             var statement = connection.prepareStatement(
                     "INSERT INTO users (username, password) VALUES (?, ?)")) {

            statement.setString(1, username);
            statement.setString(2, hashedPassword);

            statement.executeUpdate();

            JOptionPane.showMessageDialog(frame,
                    "Registration Successful!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            frame.dispose();
            new LoginScreen();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame,
                    "Username already exists!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}