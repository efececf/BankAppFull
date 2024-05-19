package Pages; 
import Entity.Database;
import Models.Account;
import Models.AccountMove;
import Models.User;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.*;

public class myPage extends JFrame{
    private JFrame f;
    private JButton button0;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private Database myDB;
    private String NameSu;
    private String tckim;
    private LocalDateTime timer;


    public myPage(String NameSu,String tckim){
        this.NameSu=NameSu;
        this.tckim=tckim;
        f=new JFrame();
        f.setTitle("Sayfam");
        f.setSize(700, 800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel baslangicLabel = new JLabel("Menuden yapmak istedigin islemi sec");

        Database myDB=new Database();

        // Create buttons
        //button0=new JButton("Menüden yapmak istediğin işlemi seç");
        button0=new JButton("Hesap ac");
        button1 = new JButton("TL hesap bilgilerimi görüntüle");
        button2 = new JButton("Döviz hesap bilgilerimi görüntüle");
        button3 = new JButton("Para transferi yap");
        button4 = new JButton("Son Hareketlerim");

        // Set layout to null to manually position components
        f.setLayout(new FlowLayout(FlowLayout.CENTER,80,10));
        f.setLayout(new GridLayout(0, 1));


        // Add buttons to the frame
        f.add(baslangicLabel);
        f.add(button0);
        f.add(button1);
        f.add(button2);
        f.add(button3);
        f.add(button4);

        //button0.setBounds(100, 50, 300, 100);
        baslangicLabel.setBounds(100, 20, 300, 20);
        button0.setBounds(100, 50, 300, 70);
        button1.setBounds(100, 150, 300, 70);
        button2.setBounds(100, 250, 300, 70);
        button3.setBounds(100, 350, 300, 70);
        button4.setBounds(100, 600, 300, 70);

        button0.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e){
                JFrame acilisFrame=new JFrame();
                acilisFrame.setSize(400, 300);
                acilisFrame.setLayout(new FlowLayout());
                JButton tlHesabi=new JButton("Vadesiz Tl Hesabi ac");
                JButton dovizhes=new JButton("Vadesiz Doviz Hesabi ac");
                acilisFrame.add(tlHesabi);
                acilisFrame.add(dovizhes);
                button0.setBounds(150,50,400,50);
                button1.setBounds(100, 150, 100, 50);

                // acilisFrame.add(tlHesabi);
                // acilisFrame.add(dovizhes);
                acilisFrame.setVisible(true);

                tlHesabi.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        ArrayList<Account> accounts=myDB.getAccounts();
                        ArrayList<Account> myAccs=new ArrayList<>();
                        for(Account myAcc:accounts){
                            if(myAcc.showAccountOwner().equals(NameSu)){
                                myAccs.add(myAcc);
                        
                            }
                        }
                        int check=0;
                        for(Account myaccounts:myAccs){
            
                            if(myaccounts.getDovizcinsi()==1){
                                check+=1;
                            }
                        }
                        if(check>0){
                            JOptionPane.showMessageDialog(null,"Zaten TL hesabiniz var.Sadece bir tane TL hesabiniz olabilir");
                        }
                        else{
                            JFrame yeniHesap=new JFrame();
                            yeniHesap.setSize(400, 300);
                            yeniHesap.setLayout(new FlowLayout());
                            JLabel hesapnoLabel = new JLabel("Hesap Numarasi(6 haneli bir sayi seciniz:):");
                            JTextField hesapField = new JTextField(20);

                            JLabel baslagicBakiyesiLabel = new JLabel("Baslangicta Yatirmak istediginiz miktar(TL):");
                            JTextField bakiyeField = new JTextField(20);

                            JButton registerButton = new JButton("Hesabi Ac");
                            registerButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    String no = hesapField.getText();
                                    //Integer.parseInt(no);
                                    String baki = bakiyeField.getText();
                                    //Integer.parseInt(baki);
                                    Account newAccount=new Account(1,Integer.parseInt(no),Integer.parseInt(baki),NameSu);
                                    myDB.createAccountinDb(newAccount);
            
            
                                    //System.out.println("Name Surname: " + name + ", tc no: " + id + ", Position: " + selectedPosition);
                                    //JOptionPane.showMessageDialog(null,"Name Surname: " + name + ", tc no: " + id + ", Position: " + selectedPosition);
                                }
                            });

                            yeniHesap.add(hesapnoLabel);
                            yeniHesap.add(hesapField);
                            yeniHesap.add(baslagicBakiyesiLabel);
                            yeniHesap.add(bakiyeField);
                            yeniHesap.add(registerButton);
                            yeniHesap.setVisible(true);


                        }
                
                    }
                });

                dovizhes.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        ArrayList<Account> accounts=myDB.getAccounts();
                        ArrayList<Account> myAccs=new ArrayList<>();
                        for(Account myAcc:accounts){
                            if(myAcc.showAccountOwner().equals(NameSu)){
                                myAccs.add(myAcc);
                        
                            }
                        }
                        int check=0;
                        for(Account myaccounts:myAccs){
            
                            if(myaccounts.getDovizcinsi()==2){
                                check+=1;
                            }
                        }
                        if(check>0){
                            JOptionPane.showMessageDialog(null,"Zaten Euro hesabiniz var.Sadece bir tane TL hesabiniz olabilir");
                        }
                        else{
                            JFrame yeniHesap=new JFrame();
                            yeniHesap.setSize(400, 300);
                            yeniHesap.setLayout(new FlowLayout());
                            JLabel hesapnoLabel = new JLabel("Hesap Numarasi(6 haneli bir sayi seciniz:):");
                            JTextField hesapField = new JTextField(20);

                            JLabel baslagicBakiyesiLabel = new JLabel("Baslangicta Yatirmak istediginiz miktar(EU):");
                            JTextField bakiyeField = new JTextField(20);

                            JButton registerButton = new JButton("Hesabi Ac");
                            registerButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    String no = hesapField.getText();
                                    //Integer.parseInt(no);
                                    String baki = bakiyeField.getText();
                                    //Integer.parseInt(baki);
                                    Account newAccount=new Account(2,Integer.parseInt(no),Integer.parseInt(baki),NameSu);
                                    myDB.createAccountinDb(newAccount);
            
            
                                    //System.out.println("Name Surname: " + name + ", tc no: " + id + ", Position: " + selectedPosition);
                                    //JOptionPane.showMessageDialog(null,"Name Surname: " + name + ", tc no: " + id + ", Position: " + selectedPosition);
                                }
                            });

                            yeniHesap.add(hesapnoLabel);
                            yeniHesap.add(hesapField);
                            yeniHesap.add(baslagicBakiyesiLabel);
                            yeniHesap.add(bakiyeField);
                            yeniHesap.add(registerButton);
                            yeniHesap.setVisible(true);


                        }
                    }
                });



            }
        });
        

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                JFrame tlFrame=new JFrame();
                tlFrame.setSize(400, 300);
                tlFrame.setLayout(new FlowLayout());

                JLabel nameLabel = new JLabel("Vadesiz TL");

                ArrayList<User> users=myDB.getUsers();
                User myUser=null;
                for(User user:users){
                    if(user.getnameSurname().equals(NameSu)&&user.getUserId().equals(tckim)){
                        myUser=user;
                    }
                }
                int hesNo=0;
                double bakiye=0;
                ArrayList<Account> accounts=myDB.getAccounts();
                for(Account myAcc:accounts){
                    if(myAcc.showAccountOwner().equals(myUser.getnameSurname())){
                        if(myAcc.getDovizcinsi()==2){
                            continue;
                        }
                        else if (myAcc.getDovizcinsi()==1) {
                            hesNo=myAcc.showHesapNo();
                            bakiye=myAcc.getHesapBalance();
                        }
                        // else{

                        // }
                        
                    }
                }
                
                JLabel hesapnoLabel = new JLabel(String.format("Hesap numarası: %d", hesNo));
                JLabel bakiyelabel=new JLabel(String.format("Kullanılabilir bakiye: %.2f", bakiye));

                tlFrame.add(nameLabel);
                tlFrame.add(hesapnoLabel);
                tlFrame.add(bakiyelabel);

                tlFrame.setVisible(true);
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                JFrame euFrame=new JFrame();
                euFrame.setSize(400, 300);
                euFrame.setLayout(new FlowLayout());

                JLabel nameLabel = new JLabel("Vadesiz Doviz");

                ArrayList<User> users=myDB.getUsers();
                User myUser=null;
                for(User user:users){
                    if(user.getnameSurname().equals(NameSu)&&user.getUserId().equals(tckim)){
                        myUser=user;
                    }
                }
                int hesNo=0;
                double bakiye=0;
                ArrayList<Account> accounts=myDB.getAccounts();
                for(Account myAcc:accounts){
                    if(myAcc.showAccountOwner().equals(myUser.getnameSurname())){
                        if (myAcc.getDovizcinsi()==1) {
                            continue;
                        }
                        else if (myAcc.getDovizcinsi()==2) {
                            hesNo=myAcc.showHesapNo();
                            bakiye=myAcc.getHesapBalance();
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Ilk once doviz hesabi acmalisiniz");
                        }
                    
                        
                    }
                    
                }
                
                JLabel hesapnoLabel = new JLabel(String.format("Hesap numarası: %d", hesNo));
                JLabel bakiyelabel=new JLabel(String.format("Kullanılabilir bakiye: %.2f", bakiye));

                euFrame.add(nameLabel);
                euFrame.add(hesapnoLabel);
                euFrame.add(bakiyelabel);

                euFrame.setVisible(true);
            }
        });

        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                JFrame transFrame=new JFrame();
                transFrame.setSize(500, 400);
                transFrame.setLayout(new FlowLayout());

                JButton tlGonder=new JButton("TL");
                JButton euGonder=new JButton("EU");
                transFrame.add(tlGonder);
                transFrame.add(euGonder);
                transFrame.setVisible(true);

                tlGonder.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        JFrame tlTransfer=new JFrame();
                        tlTransfer.setSize(500, 400);
                        tlTransfer .setLayout(new FlowLayout());
                        
                        JLabel aliciLabel = new JLabel("Alıcı Ad Soyad:");
                        JTextField alicitext=new JTextField(40);
                        String alici=alicitext.getText();


                        JLabel tutarLabel = new JLabel("Tutar:");
                        JTextField tutartext=new JTextField(40);
                        String tutar=tutartext.getText();
                        
                        


                        JLabel aciklamaLabel = new JLabel("Açıklama:");
                        JTextField aciklamaText=new JTextField(40);
                        String aciklma=aciklamaText.getText();

                        tlTransfer.add(aliciLabel);
                        tlTransfer.add(alicitext);
                        tlTransfer.add(tutarLabel);
                        tlTransfer.add(tutartext);
                        tlTransfer.add(aciklamaLabel);
                        tlTransfer.add(aciklamaText);
                        System.out.println("Kullanıcının girdiği tutar: " + tutar);
                        
                        //tlTransfer.setVisible(true);

                        JButton gonderButton=new JButton("Gonder");
                        tlTransfer.add(gonderButton);
                        tlTransfer.setVisible(true);
                        gonderButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e){
                                double tutar1=0;
                                try {
                                    // if(tutar.isEmpty()==true){
                                    //     JOptionPane.showMessageDialog(null,"Tutar alanini bos birakamazsiniz");
                                    //     return;
                                    //     //tutar1=Double.parseDouble(tutartext.getText());
                                    // }
                                    // else{
                                    //     //JOptionPane.showMessageDialog(null,"Tutar alanini bos birakamazsiniz");
                                    //     tutar1=Double.parseDouble(tutar);
            
                                    //     //return;
                                    // }
                                    tutar1=Double.parseDouble(tutar);
                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(null,"Gecersiz bir deger girdiniz");
                                    return;
                                }
                                //System.out.println("Kullanıcının girdiği tutar: " + tutar);
                                ArrayList<Account> accounts;
                                accounts=myDB.getAccounts();
                                ArrayList<Account> myAccounts=new ArrayList<>();
                                ArrayList<Account> aimedAccounts=new ArrayList<>();
                                int i=1;
                                int j=1;
                                Account paraGonderen=null;
                                Account paraAlan=null;
                                for(Account myAcc:accounts){
                                    if(myAcc.showAccountOwner().equals(NameSu)){
                                        myAccounts.add(myAcc);
                                    }
                                    else if(myAcc.showAccountOwner().equals(alici)){
                                        aimedAccounts.add(myAcc);
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null,"Boyle bir kullanici bulunamadi");
                                    }
                                }
                                for(Account acc:myAccounts){
                                    if(acc.getDovizcinsi()==1){
                                        paraGonderen=new Account(1,acc.showHesapNo(),acc.getHesapBalance(),acc.showAccountOwner());
                                        i-=1;
                                    }
                                }
                                for(Account otherAcc:aimedAccounts){
                                    if(otherAcc.getDovizcinsi()==1){
                                        paraAlan=new Account(1,otherAcc.showHesapNo(), otherAcc.getHesapBalance(),otherAcc.showAccountOwner());
                                        j-=1;
                                    }
                                }
                                if(i==0&&j==0){
                                    //paraGonderen.moneyTransfer(paraAlan,tutar1);
                                    myDB.updateDbBalance(paraGonderen,paraAlan,tutar1);
                                    LocalDateTime now=LocalDateTime.now();
                                    AccountMove m=new AccountMove(1,tutar1,aciklma, paraGonderen.showAccountOwner(),now,paraAlan.showAccountOwner());
                                    myDB.saveAccMovDb(m);
                                }
                                else{
                                    JOptionPane.showMessageDialog(null,"Alicinin veya sizin boyle bir hesabiniz yok.");
                                }
                            }
                        });
                        //tlTransfer.add(gonderButton);
                        //tlTransfer.setVisible(true);
            }
        });
                
        euGonder.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JFrame euTransfer=new JFrame();
                euTransfer.setSize(500, 400);
                euTransfer.setLayout(new FlowLayout());
                        
                JLabel aliciLabel = new JLabel("Alıcı Ad Soyad:");
                JTextField alicitext=new JTextField(10);
                String alici=alicitext.getText();


                JLabel tutarLabel = new JLabel("Tutar:");
                JTextField tutartext=new JTextField(10);
                String tutar=tutartext.getText();
                        

                JLabel aciklamaLabel = new JLabel("Açıklama:");
                JTextField aciklamaText=new JTextField(10);
                String aciklma=aciklamaText.getText();

                euTransfer.add(aliciLabel);
                euTransfer.add(alicitext);
                euTransfer.add(tutarLabel);
                euTransfer.add(tutartext);
                euTransfer.add(aciklamaLabel);
                euTransfer.add(aciklamaText);
                        //euTransfer.setVisible(true);

                JButton euGonderButton=new JButton("Gonder");
                euGonderButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        double tutar1=0;
                        try {
                            if(tutar.isEmpty()){
                                JOptionPane.showMessageDialog(null,"Tutar alanini bos birakamazsiniz");
                                return;
                                //tutar1=Double.parseDouble(tutartext.getText());
                            }
                            else{
                                //JOptionPane.showMessageDialog(null,"Tutar alanini bos birakamazsiniz");
                                //return;
                                tutar1=Double.parseDouble(tutartext.getText());
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null,"Gecersiz bir deger girdiniz");
                            return;
                        }
                        ArrayList<Account> accounts=new ArrayList<>();
                        ArrayList<Account> myAccounts=new ArrayList<>();
                        ArrayList<Account> aimedAccounts=new ArrayList<>();
                        int i=1;
                        int j=1;
                        Account paraGonderen=null;
                        Account paraAlan=null;
                        for(Account myAcc:accounts){
                            if(myAcc.showAccountOwner().equals(NameSu)){
                                 myAccounts.add(myAcc);
                            }
                            else if(myAcc.showAccountOwner().equals(alici)){
                                aimedAccounts.add(myAcc);
                            }
                        }
                        for(Account acc:myAccounts){
                            if(acc.getDovizcinsi()==2){
                                paraGonderen=new Account(1,acc.showHesapNo(),acc.getHesapBalance(),acc.showAccountOwner());
                                i-=1;
                            }
                        }
                        for(Account otherAcc:aimedAccounts){
                            if(otherAcc.getDovizcinsi()==2){
                                paraAlan=new Account(1,otherAcc.showHesapNo(), otherAcc.getHesapBalance(),otherAcc.showAccountOwner());
                                j-=1;
                            }
                        }
                        if(i==0&&j==0){
                            paraGonderen.moneyTransfer(paraAlan,tutar1);
                            myDB.updateDbBalance(paraGonderen,paraAlan,tutar1);
                            LocalDateTime now=LocalDateTime.now();
                            AccountMove m=new AccountMove(2,tutar1,aciklma, paraGonderen.showAccountOwner(),now,paraAlan.showAccountOwner());
                            myDB.saveAccMovDb(m);
                        }        
                    }
                        });
                        euTransfer.add(euGonderButton);
                        euTransfer.setVisible(true);



                    }
                });
            
                
                

            }
        });

        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                JFrame hareketFrame=new JFrame();
                ///JTable j;
                hareketFrame.setSize(600, 50000);
                hareketFrame.setLayout(new FlowLayout());
                hareketFrame.setTitle("Hareketlerim");

                ArrayList<AccountMove> accmo=myDB.getAccountMoves();
                ArrayList<AccountMove> mymoves=null;
                for(AccountMove a:accmo){
                    if(a.showAccOw().equals(NameSu)||a.showAffectedAccountUser().equals(NameSu)){
                        mymoves.add(a);
                    }
                }
                if(mymoves!=null){
                    String[][] data = new String[mymoves.size()][6];
                    for (int i = 0; i < mymoves.size(); i++) {

                        AccountMove move = mymoves.get(i);
                        data[i][0] = String.valueOf(move.getHareketCinsi());
                        data[i][1] = String.valueOf(move.showEffect());
                        data[i][2] = move.getAciklama();
                        data[i][3] = move.showAccOw();
                        data[i][4] = move.showHareketZamani().toString();
                        data[i][5] = move.showAffectedAccountUser();
                    }

                    String[] columnNames = {"Hareket Tipi(1=tl,2=eu)", "Miktar", "Aciklama", "Kimden", "Tarih", "Kime"};
                    JTable j = new JTable(data, columnNames);

                    JScrollPane sp = new JScrollPane(j);
                    hareketFrame.add(sp);
                    hareketFrame.setVisible(true);
                    hareketFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
                }
                else{
                    JOptionPane.showMessageDialog(null,"Kayitli hicbir hareketininiz bulunmamaktadir.");
                }
                
                // hareketFrame.add(sp);
                // hareketFrame.setVisible(true);
                // hareketFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
                // JLabel nameLabel = new JLabel("Hareketlerim");
                // // Bu alana kullanıcının hesap hareketleri listelenebilir
                // JLabel tarihLabel=new JLabel("Tarih");
                // JLabel tipLabel=new JLabel("Para Tipi");
                // JLabel transferLabel=new JLabel("Transfer Tipi");




                // hareketFrame.add(nameLabel);
                // hareketFrame.add(tipLabel);
                // hareketFrame.add(tarihLabel);
                // hareketFrame.add(transferLabel);


                // nameLabel.setBounds(50, 50, 200, 50);
                // tarihLabel.setBounds(50, 100, 100, 100);
                // tipLabel.setBounds(450,100 , 100, 100);
                // transferLabel.setBounds(250, 100, 100, 100);

                // hareketFrame.setVisible(true);
            }
        });

        f.setVisible(true);
    }
}
