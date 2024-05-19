package Entity;
import Models.Account;
import Models.AccountMove;
//import com.sun.nio.file.SensitivityWatchEventModifier;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import Models.User;
//import java.lang.reflect.Array;
//import javax.crypto.KEM;
//import javax.naming.spi.DirStateFactory;
import javax.swing.JOptionPane;



//import com.mysql.cj.protocol.Resultset;
//import java.lang.reflect.Array;

public class Database {
    Connection connection;
    Statement statement;
    User user;
    Account account;

    public Database(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:8889/VB_Proje", "root", "root"
            );
            this.statement = connection.createStatement();
        
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public String[] showDovizCinsi(){
        ArrayList<String> dovizTurleri=new ArrayList<String>();
        try {
            ResultSet resultSet = statement.executeQuery("select * from DövizCinsi");
            while(resultSet.next()){
                dovizTurleri.add(resultSet.getString(2));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        String[] dTstr=new String[dovizTurleri.size()];
        dovizTurleri.toArray(dTstr);
        return  dTstr;

    }
    public String[] showPositions(){
        ArrayList<String> positionsList=new ArrayList<String>();

        try {
            ResultSet resultset=statement.executeQuery("select * from Position");
            
            while(resultset.next()){
                positionsList.add(resultset.getString(2));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        String[] positions=new String[positionsList.size()];
        for(int i=0;i<positionsList.size();i++){
            positions[i]=positionsList.get(i);
        }
        return positions;
    }
   
    public ArrayList<User> getUsers(){
        ArrayList<User> users=new ArrayList<>();
        try {
            ResultSet resultset=statement.executeQuery("select * from Users");
            
            while(resultset.next()){
                User user=new User(resultset.getString(1),resultset.getString(2),resultset.getString(3),resultset.getDouble(4));
                users.add(user);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return users;

    }
    public ArrayList<Account> getAccounts(){
        ArrayList<Account> accs=new ArrayList<>();
        try {
            ResultSet resultset=statement.executeQuery("select * from Accounts");
            
            while(resultset.next()){
                Account acc=new Account(resultset.getInt(1),resultset.getInt(2),resultset.getDouble(3),resultset.getString(4));
                accs.add(acc);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return accs;

    }
    public boolean loginInfoUser(String name,String id){
        ArrayList<User> usersDb=new ArrayList<>();
        usersDb=getUsers();
        //User logged;
        
        for(User myUser:usersDb){
            if(myUser.getnameSurname().equals(name) && myUser.getUserId().equals(id)){
                //logged=myUser;
                return true;
            }
        }
        return false;
    }
    public void registerUserToDb(User u){
        try {
            // String sql="Insert into Users('Name Surname','Personal ID','Position','Balance')"+"values('" + u.getnameSurname()() + "','" + u.getUserId()
            // + "','" + u.getPosition() + "','" + u.getBalance() "')"
            String sql = "INSERT INTO Users (NameSurname, PersonalID, Position, Balance) VALUES ('" +
             u.getnameSurname() + "','" + u.getUserId() + "','" + u.getPosition() + "','" + u.getBalance() + "')";


            int i = statement.executeUpdate(sql);
            if(i>0){
                System.out.println("Succesful registration");
                JOptionPane.showMessageDialog(null,"Bankaya basariyla kaydoldunuz");
            }
            else{
                System.out.println("Failed registration, Try again");
                JOptionPane.showMessageDialog(null,"Beklenmeyen bir hata olustu, tekrar deneyin");
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    public void createAccountinDb(Account a){
        // int b=a.getDovizcinsi();
        // String k="TL";
        // String z="EURO";
        // String h="";
        // if(b==1){
        //     h=k;
        // }
        // else{
        //     h=z;
        // }
        try {
            String sql = "INSERT INTO Accounts (DovizcinsiID,AccountNumber,Balance,AccountOwner) VALUES ('" +
             a.getDovizcinsi() + "','" + a.showHesapNo() + "','" + a.getHesapBalance() + "','" + a.showAccountOwner() + "')";


            int i = statement.executeUpdate(sql);
            if(i>0){
                System.out.println("Succesful registration");
                JOptionPane.showMessageDialog(null,"Banka hesabiniz bassariyla acildi");
            }
            else{
                System.out.println("Failed registration, Try again");
                JOptionPane.showMessageDialog(null,"Beklenmeyen bir hata olustu, tekrar deneyin");
            }
        }
        
        catch (Exception e) {
            System.out.println(e);
        }
    }
    public void saveAccMovDb(AccountMove x){
        try {
            String sql = "INSERT INTO HesapHareketi (HareketId,HesaptakiParaDegisimi,Aciklama,AccountOwner,HareketZamani,AccountTo) VALUES ('" +
             x.getHareketCinsi() + "','" + x.showEffect() + "','" + x.getAciklama() + "','" + x.showAccOw() + "','" + x.showHareketZamani() +"','" + x.showAffectedAccountUser() + "')";


            int i = statement.executeUpdate(sql);
            if(i>0){
                System.out.println("Succesfully");
                JOptionPane.showMessageDialog(null,"Yaptiginiz hareket basariyla sisteme kaydedildi");
            }
            else{
                System.out.println("Failed registration, Try again");
                JOptionPane.showMessageDialog(null,"Beklenmeyen bir hata olustu, tekrar deneyin");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public ArrayList<AccountMove> getAccountMoves(){
        ArrayList<AccountMove> accMoves=new ArrayList<>();
        try {
            ResultSet resultset=statement.executeQuery("select * from HesapHareketi");
            
            while(resultset.next()){
                AccountMove acc=new AccountMove(resultset.getInt(1),resultset.getDouble(2),resultset.getString(3),resultset.getString(4),resultset.getTimestamp(5).toLocalDateTime(),resultset.getString(6));
                accMoves.add(acc);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return accMoves;
    }
    public void updateDbBalance(Account a,Account b,double z){
        ArrayList<Account> accounts=new ArrayList<>();
        //Account p=null;
        //Account t=null;
        try {
            ResultSet resultSet=statement.executeQuery("select * from Accounts");
            while(resultSet.next()){
                Account acc=new Account(resultSet.getInt(1),resultSet.getInt(3),resultSet.getDouble(4),resultSet.getString(5));
                accounts.add(acc);

            }
            // for(Account myAccount:accounts){
            //     if(a.showHesapNo()==myAccount.showHesapNo()){
            //         p=a;
            //     }
            //     else if(b.showHesapNo()==myAccount.showHesapNo()){
            //         t=b;
            //     }
            //     else{
            //         JOptionPane.showMessageDialog(null, "Check the accounts");
            //     }
            // }
            boolean s=a.moneyTransfer(b,z);
            if(s==true){
                String updateDB="UPDATE Accounts SET Balance-="+ z+ "WHERE AccountNumber ='"+a.showHesapNo()+"'";
                String updateDB1="UPDATE Accounts SET Balance+="+z+ "WHERE AccountNumber ='"+b.showHesapNo()+"'";
                statement.executeUpdate(updateDB);
                statement.executeUpdate(updateDB1);
                JOptionPane.showMessageDialog(null, "Transfer is successful");
            }
            else{
                JOptionPane.showMessageDialog(null, "Invalid balance to transfer");
            }

            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    }
