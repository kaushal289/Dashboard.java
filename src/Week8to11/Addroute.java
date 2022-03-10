package Week8to11;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Class to add route
class AddrouteFrame {
    ImageIcon image_bg;
    JLabel lbl_img;
    AddrouteFrame(){
//        Initializing swing components
        JFrame f = new JFrame("Add routes Panel");
        JLabel title = new JLabel("Add routes here");
        title.setFont(new Font("Algerian", Font.BOLD, 50));
        Color myWhite = new Color(7, 64, 122);
        title.setForeground(myWhite);
        f.add(title).setBounds(170, 50, 1000, 50);

        JLabel routeId = new JLabel("Route ID       :");
        routeId.setFont(new Font("Cascadia Code", Font.BOLD, 30));
        f.add(routeId).setBounds(150, 155, 300, 30);


        JLabel routename = new JLabel("Route name :");
        routename.setFont(new Font("Cascadia Code", Font.BOLD, 30));
        f.add(routename).setBounds(150, 215, 1000, 50);


        JTextField routeText  =  new JTextField();
        routeText.setFont(new Font("BankGothic Lt BT", Font.BOLD, 30));
        routeText.setBorder(new LineBorder(Color.black,2));
        f.add(routeText).setBounds(400, 150, 250, 50);

        JTextField distanceText = new JTextField();
        distanceText.setFont(new Font("BankGothic Lt BT", Font.BOLD, 30));
        distanceText.setBorder(new LineBorder(Color.black,2));
        f.add(distanceText).setBounds(400, 220, 250, 50);

        JButton backButton = new JButton(" <--- Back");
        backButton.setFont(new Font("BankGothic Lt BT", Font.BOLD, 30));
        backButton.setBorder(new LineBorder(Color.black,6));
        Color col2 = new Color(255, 0, 0);
        backButton.setBackground(col2);
        backButton.setForeground(Color.white);
        f.add(backButton).setBounds(450,350,200,50);

        JButton addroute  = new JButton("Add route");
        addroute.setFont(new Font("BankGothic Lt BT", Font.BOLD, 30));
        addroute.setBorder(new LineBorder(Color.black,6));
        Color col3 = new Color(1, 84, 4);
        addroute.setBackground(col3);
        addroute.setForeground(Color.white);
        f.add(addroute).setBounds(150, 350, 200, 50);

//      Adding Swing Components in frame






//        Add route action listener
        addroute.addActionListener(e -> {
            String routeName, distanceName, descriptionName;
            routeName = routeText.getText();
            distanceName = distanceText.getText();
            FileWriter fw;

//            Declaring list to store routes data
            List<String> list = new ArrayList<>();
            List<String> routeIdList = new ArrayList<>();
            File file = new File("routes.txt");
            if(file.exists()){
                try {
                    list = Files.readAllLines(file.toPath(), Charset.defaultCharset());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
//            Splitting route list with ';'
            for(String line : list){
                String [] res = line.split(";");
                routeIdList.add(res[0]);
            }
            try {
//                Form Validations
                if(!routeIdList.contains(routeName)){
                    if(routeName.isEmpty() || distanceName.isEmpty()){
                        JOptionPane.showMessageDialog(f,"Fill up the full form");
                    }
                    else {
                        fw = new FileWriter("routes.txt", true);
                        fw.write(routeName + ";" + distanceName + ";"+ "\n");
                        fw.close();
                        JOptionPane.showMessageDialog(f, "Route Added Successfully");
                        routeText.setText("");
                        distanceText.setText("");
                    }

                }
                else {
                    JOptionPane.showMessageDialog(f,"Route ID Already Taken");

                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        backButton.addActionListener(e -> {
            f.dispose();
            Select sel=new Select();
            sel.select();
        });

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
}