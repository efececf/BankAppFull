package Pages;
import Entity.Database;
import Models.User;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class OpeningPage extends JFrame {
    private final JButton button0;
    private final JButton button1;
    private final JButton button2;
    //private JavaJDBC con;
    //private final JFrame f;
    private Database myDB;

    


    public OpeningPage() {
        //new JFrame();
        Database myDB=new Database();
        setTitle("VB Pro");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create buttons
        button0=new JButton("Uygulamaya Hos geldiniz");
        button1 = new JButton("Register");
        button2 = new JButton("Login");

        // Set layout to null to manually position components
        setLayout(null);

        // Add buttons to the frame
        add(button0);
        add(button1);
        add(button2);

        //JavaJDBC a=new JavaJDBC();
        //a.

        // Position buttons
        button0.setBounds(150,50,250,50);
        button1.setBounds(150, 150, 150, 50);
        button2.setBounds(150, 250, 150, 50);
        setVisible(true);

        // Add action listeners to buttons
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Your registration logic here
                JFrame registerFrame = new JFrame("Registration");
                registerFrame.setSize(300, 200);
                registerFrame.setLayout(new FlowLayout());

                JLabel nameLabel = new JLabel("Name Surname:");
                JTextField nameField = new JTextField(20);

                JLabel idLabel = new JLabel("Tc kimlik No:");
                JTextField idField = new JTextField(20);

                JLabel positionLabel = new JLabel("Position:");
                //String[] positions = con.resultSet.getString(3); // Define your positions here
                //int[] positionids=con.resultSet.getInt(2);
                String[] positions={"intern","frontend","backend","architect","manager"};
                JComboBox<String> positionComboBox = new JComboBox<>(positions);

                JButton registerButton = new JButton("Register");
                registerButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String name = nameField.getText();
                        String id = idField.getText();
                        String selectedPosition = (String) positionComboBox.getSelectedItem();
                        // for(int i=0;i<positions.length;i++){
                        //     if(selectedPosition.equals(positions[i])){
                        //         int positionId=i;
                        //     }
                        // }
                        
                        //User newUser=new User(name, id,selectedPosition);
                        User newUser=new User(name, id, selectedPosition,0);
                        myDB.registerUserToDb(newUser);


                        //System.out.println("Name Surname: " + name + ", tc no: " + id + ", Position: " + selectedPosition);
                        //JOptionPane.showMessageDialog(null,"Name Surname: " + name + ", tc no: " + id + ", Position: " + selectedPosition);
                    }
                });

                registerFrame.add(nameLabel);
                registerFrame.add(nameField);
                registerFrame.add(idLabel);
                registerFrame.add(idField);
                registerFrame.add(positionLabel);
                registerFrame.add(positionComboBox);
                registerFrame.add(registerButton);

                registerFrame.setVisible(true);
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //login log
                JFrame loginFrame = new JFrame("Login");
                loginFrame.setSize(300, 200);
                loginFrame.setLayout(new FlowLayout());

                JLabel usernameLabel = new JLabel("Ad Soyad:");
                JTextField usernameField = new JTextField(20);

                JLabel passwordLabel = new JLabel("Tc kimlik no:");
                JTextField passwordField = new JTextField(20);

                JButton loginButton = new JButton("Login");
                loginButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        String nameSu=usernameField.getText();
                        String tckim=passwordField.getText();
                        //String orgname=con.resultSet.getString(1)+con.resultSet.getString(2);
                        //String orgId=con.resultSet.getString(3);
                        // if(tckim.equals(orgname) && orgId.equals(tckim)){
                        //     JFrame message=new JFrame()
                        // }
                        
                        if(myDB.loginInfoUser(nameSu, tckim)==true){
                            //User m1=myDB.loginInfoUser(nameSu, tckim).logged;
                            JOptionPane.showMessageDialog(null,"hesaba giris basarili");
                            
    
                            myPage w1 =new myPage(nameSu,tckim);
                            System.out.println(w1);

                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Boyle bir kullanici bulunamadi,bilgilerinizi tekrar gozden gecirip deneyin");
                        }
                        // JOptionPane.showMessageDialog(null,"hesaba giris basrili");
                        // myPage w1 =new myPage();
                        // System.out.println(w1);
                    }
                });

                loginFrame.add(usernameLabel);
                loginFrame.add(usernameField);
                loginFrame.add(passwordLabel);
                loginFrame.add(passwordField);
                loginFrame.add(loginButton);

                loginFrame.setVisible(true);
            }
        });
    }
}