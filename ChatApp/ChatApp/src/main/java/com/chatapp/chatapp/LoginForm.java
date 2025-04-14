/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatapp.chatapp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.chatapp.chatapp.ChatAppAuth;

public class LoginForm {
    JFrame frame;
    JLabel messageLabel; 
    

    private ChatAppAuth auth; 
    

    public LoginForm(ChatAppAuth auth) {
        this.auth = auth;  
        frame = new JFrame("Login");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(150, 20, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(150, 50, 165, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(120, 130, 100, 25);
        panel.add(loginButton);

        // Add the message label for errors/success
        messageLabel = new JLabel(""); 
        messageLabel.setBounds(10, 170, 350, 25);
        messageLabel.setForeground(Color.RED);  // Default to red (error)
        panel.add(messageLabel);


        JLabel registerLink = new JLabel("<HTML><U>Don't have an account? Register here</U></HTML>");
        registerLink.setForeground(Color.BLUE);
        registerLink.setBounds(90, 220, 250, 25);
        registerLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(registerLink);

        registerLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new RegisterForm(auth);  
            }
        });

        loginButton.addActionListener(e -> {
            String username = userText.getText().trim();
            String password = new String(passwordText.getPassword()).trim();
            
            boolean isLoggedIn = auth.loginUser(username, password);

            if (isLoggedIn) {
                setMessage("Login Successful!", true);  
                frame.dispose();
                User loggedInUser = auth.getLoggedInUser();
                new HomePage(loggedInUser.getFirstname(),loggedInUser.getLastname());  
            } else {
                setMessage("Invalid Username or Password.", false);  
            }
        });
    }

    private void setMessage(String message, boolean isSuccess) {
        messageLabel.setText(message);
        if (isSuccess) {
            messageLabel.setForeground(Color.GREEN); 
        } else {
            messageLabel.setForeground(Color.RED); 
        }
    }
}
