package modele;

public class Agence {
     private String idagence;
    private String nom;

    public String getIdagence() {
        return idagence;
    }

    public void setIdagence(String idage) {
        this.idagence = idage;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getInfoComplet(){
    return idagence+" "+nom+" ";
    }
    
}
