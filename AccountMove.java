package Models;
import java.time.LocalDateTime;
public class AccountMove {
    private int hareketCinsiID;
    private int hareketNo;
    private String hareketAciklamasi;
    private LocalDateTime hareketZamani;
    private String affectedAccount;
    private String accOwner;
    private double effectOfMove;
    public AccountMove(int hareketCinsiID,double effectOfMove,String hareketAciklamasi,String accOwner,LocalDateTime hareketZamani,String affectedAccount){
        this.hareketCinsiID=hareketCinsiID;
        this.effectOfMove=effectOfMove;
        this.hareketZamani=hareketZamani;
        this.affectedAccount=affectedAccount;
        this.hareketAciklamasi=hareketAciklamasi;
        this.accOwner=accOwner;
        
    }
    public String showAccOw(){
        return this.accOwner;
    }
    public String getAciklama(){
        return this.hareketAciklamasi;
    }
    public int getHareketCinsi(){
        return this.hareketCinsiID;
    }
    
    public LocalDateTime showHareketZamani(){
        return this.hareketZamani;
    }
    public double showEffect(){
        return effectOfMove;
    }
    public String showAffectedAccountUser(){
        return affectedAccount;
    }


}
