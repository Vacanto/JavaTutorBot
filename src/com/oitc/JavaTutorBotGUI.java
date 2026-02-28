package com.oitc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class JavaTutorBotGUI {

    private JFrame frame;
    private JTextPane chatPane;
    private JTextField inputField;
    private JButton sendButton;
    private boolean darkMode = false;

    private JavaTutorBot bot;

    public JavaTutorBotGUI(String username) {

        bot = new JavaTutorBot();

        frame = new JFrame("Java Tutor Bot - Welcome " + username);
        frame.setSize(600, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(240, 248, 255));

        // ================= HEADER =================
        JLabel header = new JLabel("Java Coding Tutor Bot", JLabel.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        header.setForeground(new Color(30, 144, 255));
        frame.add(header, BorderLayout.NORTH);
        
        JButton toggleTheme = new JButton("Dark Mode");
        header.setLayout(new BorderLayout());
        header.add(toggleTheme, BorderLayout.EAST);
        toggleTheme.addActionListener(e -> toggleTheme());
        
        

        // ================= CHAT AREA =================
        chatPane = new JTextPane();
        chatPane.setEditable(false);
        chatPane.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        chatPane.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(chatPane);
        frame.add(scrollPane, BorderLayout.CENTER);

        // ================= INPUT PANEL =================
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputField = new JTextField();
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        sendButton = new JButton("Send");
        sendButton.setBackground(new Color(30, 144, 255));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        frame.add(inputPanel, BorderLayout.SOUTH);

        // ================= EVENTS =================
        sendButton.addActionListener(this::handleMessage);
        inputField.addActionListener(this::handleMessage);

        appendMessage("Bot", "Welcome " + username + "! Ask me about Java or type 'quiz' to begin.");

        frame.setVisible(true);
    }

    private void handleMessage(ActionEvent e) {

        String userInput = inputField.getText();

        if (userInput.trim().isEmpty()) return;

        appendMessage("You", userInput);

        String response = bot.getResponse(userInput);

        appendMessage("Bot", response);

        inputField.setText("");
    }

    private void appendMessage(String sender, String message) {

        try {
            chatPane.getDocument().insertString(
                    chatPane.getDocument().getLength(),
                    sender + ": " + message + "\n\n",
                    null
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void toggleTheme() {

        darkMode = !darkMode;

        if (darkMode) {
            frame.getContentPane().setBackground(Color.DARK_GRAY);
            chatPane.setBackground(new Color(40, 40, 40));
            chatPane.setForeground(Color.WHITE);
            sendButton.setBackground(Color.GRAY);
        } else {
            frame.getContentPane().setBackground(new Color(240, 248, 255));
            chatPane.setBackground(Color.WHITE);
            chatPane.setForeground(Color.BLACK);
            sendButton.setBackground(new Color(30, 144, 255));
        }
    }
}