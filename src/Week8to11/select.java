package Week8to11;
import javax.swing.SwingConstants;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Objects;


class Select {
    ImageIcon image_bg;
    JLabel lbl_img;


    public void select() {
        JFrame f = new JFrame("Everest Transportation");
        JButton addroute, showroute, addConnection, showConnection;
        JLabel titleLabel;

        titleLabel = new JLabel("Everest Transportation");
        titleLabel.setFont(new Font("Algerian", Font.BOLD, 50));
        Color myWhite = new Color(7, 64, 122);
        titleLabel.setForeground(myWhite);
        f.add(titleLabel);
        titleLabel.setBounds(50, 30, 1000, 100);

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("loc.png")));
        addroute = new JButton("Add route", icon);
        addroute.setVerticalTextPosition(SwingConstants.BOTTOM);
        addroute.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addroute.setHorizontalTextPosition(SwingConstants.CENTER);
        addroute.setFont(new Font("BankGothic Lt BT", Font.BOLD, 30));
        f.add(addroute);
        Color col2 = new Color(255, 238, 116);
        addroute.setBorder(new LineBorder(Color.green,6));
        addroute.setForeground(Color.black);
        addroute.setBackground(col2);
        addroute.setBounds(50, 150, 330, 160);


        ImageIcon icon1 = new ImageIcon(Objects.requireNonNull(getClass().getResource("glov.png")));
        showroute = new JButton("View Routes", icon1);
        showroute.setVerticalTextPosition(SwingConstants.BOTTOM);
        showroute.setCursor(new Cursor(Cursor.HAND_CURSOR));
        showroute.setHorizontalTextPosition(SwingConstants.CENTER);
        showroute.setFont(new Font("BankGothic Lt BT", Font.BOLD, 30));
        showroute.setBorder(new LineBorder(Color.green,6));
        showroute.setBackground(col2);
        f.add(showroute);
        showroute.setBounds(400, 150, 330, 160);


        ImageIcon icon2 = new ImageIcon(Objects.requireNonNull(getClass().getResource("conn.png")));
        addConnection = new JButton("Add Connection", icon2);
        addConnection .setVerticalTextPosition(SwingConstants.BOTTOM);
        addConnection.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addConnection .setHorizontalTextPosition(SwingConstants.CENTER);
        addConnection .setFont(new Font("BankGothic Lt BT", Font.BOLD, 30));
        addConnection .setBorder(new LineBorder(Color.green,6));
        addConnection .setBackground(col2);
        f.add(addConnection);
        addConnection.setBounds(50, 350, 330, 160);

        ImageIcon icon3 = new ImageIcon(Objects.requireNonNull(getClass().getResource("show.png")));
        showConnection = new JButton("Show Connection", icon3);
        showConnection .setVerticalTextPosition(SwingConstants.BOTTOM);
        showConnection.setCursor(new Cursor(Cursor.HAND_CURSOR));
        showConnection .setHorizontalTextPosition(SwingConstants.CENTER);
        showConnection .setFont(new Font("BankGothic Lt BT", Font.BOLD, 30));
        showConnection .setBorder(new LineBorder(Color.green,6));
        showConnection .setBackground(col2);
        f.add(showConnection);
        showConnection.setBounds(400, 350, 330, 160);

        addroute.addActionListener(e -> {
            f.dispose();
            new AddrouteFrame();
        });

        showroute.addActionListener(e -> {
            f.dispose();
            new Viewroute();
        });

        addConnection.addActionListener(e -> {
            f.dispose();
            new AddConnectionFrame();
        });

        showConnection.addActionListener(e -> {
            f.dispose();
            new ViewConnection();
        });


        f.setLayout(null);
        f.setBounds(500, 100, 600, 450);
        f.setVisible(true);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);

        image_bg=new ImageIcon(Objects.requireNonNull(getClass().getResource("map.png")));
        lbl_img=new JLabel(image_bg);
        lbl_img.setBounds(0,0,800,600);
        f.add(lbl_img);

        f.setLayout(null);
        f.setVisible(true);
        f.setResizable(false);
        f.setBounds(400, 100, 800, 600);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public static void main(String[] args){
        Select sel=new Select();
        sel.select();
    }
}