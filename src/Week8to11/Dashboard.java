package Week8to11;

import javax.swing.*;
import java.awt.*;

public class Dashboard {
    public static void main(String[] args) {

//        Initializing components in swing
        JButton loginBtn, registerBtn;
        JLabel titleLabel;
        JFrame f = new JFrame("DASHBOARD");
        loginBtn = new JButton("LOGIN");
        loginBtn.setFont(new Font("Arial", Font.PLAIN, 30));
        loginBtn.setBackground(Color.BLACK);
        loginBtn.setForeground(Color.white);
        registerBtn = new JButton("REGISTER");
        registerBtn.setFont(new Font("Arial", Font.PLAIN, 30));
        registerBtn.setBackground(Color.BLACK);
        registerBtn.setForeground(Color.white);
        titleLabel = new JLabel("Everest Transportation ");
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 45));

//      Adding components to frame
        f.add(titleLabel).setBounds(100, 30, 500, 80);
        f.add(loginBtn).setBounds(200, 150, 200, 80);
        f.add(registerBtn).setBounds(200, 250, 200, 80);

        loginBtn.addActionListener(e -> {
            f.dispose();
            new LoginFrame();
        });

        registerBtn.addActionListener(e -> {
            new RegisterFrame();
            f.dispose();
        });


        f.setLayout(null);
        f.setVisible(true);
        f.setResizable(false);
        f.setBounds(800, 200, 600, 500);
        f.getContentPane().setBackground(Color.yellow);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}