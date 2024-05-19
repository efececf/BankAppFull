package Models;

//import java.lang.classfile.instruction.ThrowInstruction;

public class User{
    //private Database db;
    private Account account;
    private String nameSurname;
    private double balance;
    
    private String personalID;
    private String position;
    
    public User(String nameSurname,String personalID,String position,double balance){
        this.nameSurname=nameSurname;
        this.personalID=personalID;
        this.position=position;
        this.balance=balance;
        

    }
    public String getnameSurname(){
        return this.nameSurname;
    }
    public String getUserId(){
        return this.personalID;
    }
    public String getPosition(){
        return this.position;
    }
    public double getBalance(){
        return this.balance;
    }
    public void openAccount(int x,int y,double z){
        Account newAcc=new Account(x, y,z,this.getnameSurname());
    }
    

}