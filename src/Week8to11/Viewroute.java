package Week8to11;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Viewroute {
    ImageIcon image_bg;
    JLabel lbl_img;
    Viewroute(){
//        Initialising components in swing
        JButton  backBtn, deleteBtn;
        JFrame f=new JFrame("View Routes");

        deleteBtn = new JButton("Delete");
        deleteBtn.setFont(new Font("BankGothic Lt BT", Font.BOLD, 30));
        deleteBtn.setBorder(new LineBorder(Color.black,6));
        Color col2 = new Color(255, 0, 0);
        deleteBtn.setBackground(col2);
        deleteBtn.setForeground(Color.white);
        f.add(deleteBtn).setBounds(150,400,200,50);


        backBtn = new JButton("<--- Back");
        backBtn.setFont(new Font("BankGothic Lt BT", Font.BOLD, 30));
        backBtn.setBorder(new LineBorder(Color.black,6));
        Color col3 = new Color(1, 84, 4);
        backBtn.setBackground(col3);
        backBtn.setForeground(Color.white);
        f.add(backBtn).setBounds(450,400,200,50);

//      Adding components in frame




//      Declaring arraylist to store list of data from routes
        String[] column = {"Route ID","Route Names"};
        List<String> list = new ArrayList<>();
        ArrayList<String[]> route = new ArrayList<>();
        File file = new File("routes.txt");
        if(file.exists()){
            try {
                list = Files.readAllLines(file.toPath(), Charset.defaultCharset());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        for(String line : list){
            String [] res1 = line.split(";");
            route.add(res1);

        }
//        Storing route data
        String [][] data =new String[route.size()][route.get(0).length];
        for(int i=0;i<route.size();i++){
            System.arraycopy(route.get(i), 0, data[i], 0, route.get(i).length);
        }
        JTable jtEmp = new JTable(data, column);
        JTableHeader tableHeader = jtEmp.getTableHeader();
        tableHeader.setFont(new Font("BankGothic Lt BT", Font.BOLD, 30));
        tableHeader.setBackground(Color.black);
        tableHeader.setForeground(Color.white);


        jtEmp.setFont(new Font("Areal", Font.PLAIN, 17));
        jtEmp.setForeground(Color.black);
        jtEmp.setBackground(Color.white);
        jtEmp.setRowHeight(30);

        JScrollPane sp = new JScrollPane(jtEmp);
        f.add(sp);
        sp.setBackground(Color.white);
        sp.setBounds(50,50,700,300);

//        Delete Button Action Listener

        deleteBtn.addActionListener(e -> {

                int row = jtEmp.getSelectedRow();
                if (row >= 0) {
                    String id = (String) jtEmp.getValueAt(row, 0);
//                    Remove  data in 2D array equivalent to selected row route id
                    String[][] dataDelete = Arrays.stream(data)
                            .filter(rows -> Arrays.stream(rows).noneMatch(i -> i.equals(id)))
                            .toArray(String[][]::new);
//                    Updating new data to connection file
                    StringBuilder data_to_write = new StringBuilder();
                    for (String[] datum : dataDelete) {
                        int k = 0;
                        for (int j = 0; j < datum.length - 1; j++) {
                            data_to_write.append(datum[j]).append(";");
                            k++;
                        }
                        data_to_write.append(datum[k]).append("\n");
                    }
                    try {
                        FileWriter myWriter = new FileWriter("routes.txt");
                        myWriter.write(data_to_write.toString());
                        myWriter.close();
                        JOptionPane.showMessageDialog(f, "Deleted Successfully");
                        f.dispose();
                        new Viewroute();

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    jtEmp.setModel(new DefaultTableModel(data, column));

                } else {
                    JOptionPane.showMessageDialog(sp, "Select Any Row to Delete");
                }

        });

        backBtn.addActionListener(e -> {
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