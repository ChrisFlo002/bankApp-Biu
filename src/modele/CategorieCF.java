
package modele;


public class CategorieCF {
    private String idcat;
    private String libelle;

    public String getIdcat() {
        return idcat;
    }

    public void setIdcat(String idcat) {
        this.idcat = idcat;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public String getInfoComplet(){
    return idcat+" "+libelle+" ";
    }
    
}
