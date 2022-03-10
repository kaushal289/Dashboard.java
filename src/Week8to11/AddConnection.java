package Week8to11;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;
import java.util.List;

//Class to add connection between route
class AddConnectionFrame {
    ImageIcon image_bg;
    JLabel lbl_img;
    AddConnectionFrame(){
//        Initialising components for swing
        JFrame f = new JFrame("Add Routes Panel");

        JLabel route1 = new JLabel("Start route");
        route1.setFont(new Font("BankGothic Lt BT", Font.BOLD, 30));
        f.add(route1).setBounds(60, 150, 300, 30);

        JLabel dTextLabel = new JLabel("Distance(KM)");
        dTextLabel.setFont(new Font("BankGothic Lt BT", Font.BOLD, 30));
        f.add(dTextLabel).setBounds(300, 150, 300, 30);

        JLabel route2 = new JLabel("End route");
        route2.setFont(new Font("BankGothic Lt BT", Font.BOLD, 30));
        f.add(route2).setBounds(560, 150, 200, 30);

        JLabel distanceLabel = new JLabel("");
        distanceLabel.setFont(new Font("Baskerville Old Face", Font.BOLD, 30));
        f.add(distanceLabel).setBounds(210, 50, 500, 30);



        JLabel pathLabel = new JLabel("");
        pathLabel.setFont(new Font("Baskerville Old Face", Font.BOLD, 25));
        f.add(pathLabel).setBounds(210, 100, 500, 30);



        JButton connect  = new JButton("Connect");
        connect.setFont(new Font("BankGothic Lt BT", Font.BOLD, 30));
        connect.setBorder(new LineBorder(Color.black,2));
        connect.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Color col3 = new Color(1, 84, 2);
        connect.setBackground(col3);
        connect.setForeground(Color.white);
        f.add(connect).setBounds(100, 300, 250, 50);

        JButton disconnect  = new JButton("Disconnect");
        disconnect.setFont(new Font("BankGothic Lt BT", Font.BOLD, 30));
        disconnect.setBorder(new LineBorder(Color.black,2));
        disconnect.setCursor(new Cursor(Cursor.HAND_CURSOR));
        disconnect.setBackground(Color.red);
        disconnect.setForeground(Color.white);
        f.add(disconnect).setBounds(450, 300, 250, 50);

        JButton optimalPath = new JButton("Optimal Path");
        optimalPath.setFont(new Font("BankGothic Lt BT", Font.BOLD, 30));
        optimalPath.setBorder(new LineBorder(Color.black,2));
        optimalPath.setCursor(new Cursor(Cursor.HAND_CURSOR));
        optimalPath.setBackground(col3);
        optimalPath.setForeground(Color.white);
        f.add(optimalPath).setBounds(100,400,250,50);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("BankGothic Lt BT", Font.BOLD, 30));
        backButton.setBorder(new LineBorder(Color.black,2));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setBackground(Color.red);
        backButton.setForeground(Color.white);
        f.add(backButton).setBounds(450,400,250,50);

        JTextField dTextField = new JTextField();
        dTextField.setFont(new Font("BankGothic Lt BT", Font.BOLD, 30));
        dTextField.setText("0");
        f.add(dTextField).setBounds(330, 200, 150, 30);

//      Adding components in frame

//        Getting list of routes from txt file
        List<String> list = new ArrayList<>();
        File file = new File("routes.txt");
        ArrayList<String> route1List = new ArrayList<>();
        if(file.exists()){
            try {
                list = Files.readAllLines(file.toPath(), Charset.defaultCharset());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if(list.isEmpty())
                return;
        }
//      Add list route data in route list
        for(String line : list){
                String [] res = line.split(";");
            route1List.add(res[1]);
        }

//      Adding route to a separate 1D list
        String[] addedroutes = new String[route1List.size()];
        for (int i=0; i<route1List.size(); i++) {
            addedroutes[i] = route1List.get(i);
        }

//        Declaring combobox to select route data
        JComboBox<String> combobox = new JComboBox<>(new Vector<>(route1List));
        combobox.setFont(new Font("BankGothic Lt BT", Font.BOLD, 20));
        f.add(combobox).setBounds(60,200,180,30);


        JComboBox<String> combobox2 = new JComboBox<>(new Vector<>(route1List));
        combobox2.setFont(new Font("BankGothic Lt BT", Font.BOLD, 20));
        f.add(combobox2).setBounds(560,200,180,30);

//        Declaring arraylist to store connected data
        ArrayList<String []> connectedData = new ArrayList<>();
        try {
            File file_Obj = new File("connection.txt");
            Scanner myReader = new Scanner(file_Obj);

            while(myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arr = data.split(",");
                connectedData.add(arr);
            }
            myReader.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

//        Reading matrix from txt file if data exist
        Graph graphObj = new Graph(20);
        int len = connectedData.size();

        if (len!=0) {
            for (int i=0; i<len; i++) {
                for (int j=0; j<connectedData.get(i).length; j++) {
                    graphObj.adjacencyMatrix[i][j] = Integer.parseInt(connectedData.get(i)[j]);
                }
            }
        }
        else {
            return;
        }

//        Action event for connection button
        connect.addActionListener(e -> {
            String route1Name, route2name;
            Integer  distance;
            route1Name = (String) combobox.getSelectedItem();
            route2name = (String) combobox2.getSelectedItem();
            distance =  Integer.parseInt(dTextField.getText());

            if(distance<=0){
                JOptionPane.showMessageDialog(f,"Please fillup distance greater than 0");
                return;
            }

//          Storing selected item from combobox
            assert route1Name != null;
            if (!(route1Name.equals(route2name))){
                int firstIndex = Week8to11.linearSearch.getIndex(addedroutes,route1Name);
                int secondIndex = Week8to11.linearSearch.getIndex(addedroutes, route2name);
                StringBuilder builder = new StringBuilder();

//             Calling graph object and storing connection data to adjacency matrix
                graphObj.addEdge(firstIndex, secondIndex,distance );
                int[][] matrix = graphObj.adjacencyMatrix;
                for (int[] ints : matrix) {
                    int k = 0;
                    for (int j = 0; j < ints.length - 1; j++) {
                        builder.append(ints[j]).append(",");
                        k += 1;
                    }
                    builder.append(ints[k]).append("\n");
                }

                try {
                    FileWriter myWriter = new FileWriter("connection.txt");
                    myWriter.write(builder.toString());
                    myWriter.close();
                    JOptionPane.showMessageDialog(f, "Connection success");
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            else {
                JOptionPane.showMessageDialog(f,"Cannot Connect Same route");

            }
        });

//      Action event for disconnect button

        disconnect.addActionListener(e -> {
            String route1Name, route2name;
            route1Name = (String) combobox.getSelectedItem();
            route2name = (String) combobox2.getSelectedItem();
//          If route is already connected
            assert route1Name != null;
            if (!(route1Name.equals(route2name))){
                int firstIndex = new linearSearch().getIndex(addedroutes,route1Name);
                int secondIndex = new linearSearch().getIndex(addedroutes, route2name);
                StringBuilder changedData = new StringBuilder();

//                Replacing 1 with 0 if connection is removed
                graphObj.removeEdge(firstIndex, secondIndex);
                int[][] matrix = graphObj.adjacencyMatrix;
                for (int[] ints : matrix) {
                    int k = 0;
                    for (int j = 0; j < ints.length - 1; j++) {
                        changedData.append(ints[j]).append(",");
                        k += 1;
                    }
                    changedData.append(ints[k]).append("\n");
                }

//                Rewriting updated file into connection.txt
                try {
                    FileWriter myWriter = new FileWriter("connection.txt");
                    myWriter.write(changedData.toString());
                    myWriter.close();
                    JOptionPane.showMessageDialog(f, "Disconnected Successfully");
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            else {
                JOptionPane.showMessageDialog(f,"route are not connected to disconnect");

            }
        });

//        Action listener for optimal path button
        optimalPath.addActionListener(e -> {
            String route1Name, route2name;
            route1Name = (String) combobox.getSelectedItem();
            route2name = (String) combobox2.getSelectedItem();
            assert route1Name != null;
            if (!(route1Name.equals(route2name))){
                int firstIndex = Week8to11.linearSearch.getIndex(addedroutes,route1Name);
                int secondIndex = Week8to11.linearSearch.getIndex(addedroutes, route2name);
                int[] previousPath = graphObj.shortestPath(firstIndex);
                StringBuilder path = new StringBuilder();
                String pathDistance = "";
                int lastIndex = secondIndex;
                ArrayList<Integer> path_arr = new ArrayList<>();
                path_arr.add(lastIndex);
                while(previousPath[lastIndex]!=-1) {
                    path_arr.add(previousPath[lastIndex]);
                    lastIndex = previousPath[lastIndex];
                }

                int distance = graphObj.distance(firstIndex,secondIndex);
                pathDistance += "The shortest distance is: " + distance +" km";
                if (path_arr.size()!=1) {
                    int k=path_arr.size()-1;
                    for (int i=path_arr.size()-1; i>=1; i--) {
                        path.append(addedroutes[path_arr.get(i)]).append(" -> ");
                        k--;
                    }
                    path.append(addedroutes[path_arr.get(k)]);
                }

                distanceLabel.setText("");
                distanceLabel.setText(pathDistance);

                pathLabel.setText("");
                pathLabel.setText(path.toString());

            }
            else {
                JOptionPane.showMessageDialog(f,"Cannot Connect Same route");

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
        f.getContentPane().setBackground(Color.yellow);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}