package Models;
public class Account {
    //private User AccountOwner;
    //private User aimedUser;
    private int dövizCinsiID;
    private int hesapNo;
    private double hesapBalance;
    private String owner;
    private AccountMove accMov;
    public Account(int dövizCinsiID,int hesapNo,double hesapBalance,String owner){
        this.dövizCinsiID=dövizCinsiID;
        this.hesapNo=hesapNo;
        this.hesapBalance=hesapBalance;
        this.owner=owner;
    }
    public String showAccountOwner(){
        return this.owner;
    }

    public int showHesapNo(){
        return this.hesapNo;
    }
    public int getDovizcinsi(){
        return this.dövizCinsiID;
    }
    public double getHesapBalance(){
        return this.hesapBalance;
    }
    public boolean moneyTransfer(Account aimedAccount,double amountMoney){
        if(this.hesapBalance==0){
            System.out.println("Your balance is insufficient");
            return false;
        }
        else if(this.hesapBalance<amountMoney){
            System.out.println("You can not transfer this amount of money");
            return false;
        }
        else{
            this.hesapBalance-=amountMoney;
            aimedAccount.hesapBalance+=amountMoney;
            return true;
        }
    }
    public void putMoney(double moneyAm){
        this.hesapBalance+=moneyAm;
    }
    public void setAccountMove(AccountMove accMov){
        this.accMov=accMov;

    }
}
