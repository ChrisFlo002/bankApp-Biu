
package modele;
import java.util.Date;
import java.time.LocalDate;
import java.text.*;

public class CompteCF {
    private String numero;
    private String categorie;
    private Date datOuv;
    private LocalDate datOuvL;
    private String agence;
    private String codeClient;
    private float solde;
    private String etat;
    

   
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Date getDatOuv() {
        return datOuv;
    }
    
    public LocalDate getDatOuvL() {
        return datOuvL;
    }
    
    public void setDatOuvL(){
        this.datOuvL =datOuvL.now() ;
    }
    public void setDatOuv(Date k) {
        this.datOuv = k;
    }

    public String getAgence() {
        return agence;
    }

    public void setAgence(String agence) {
        this.agence = agence;
    }


    public String getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(String codeClient) {
        this.codeClient = codeClient;
    }

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }
    
    public String getInfoComplet(){
        return numero+" "+codeClient+" "+agence;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
}
