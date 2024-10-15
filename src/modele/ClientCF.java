
package modele;


public class ClientCF {

    private String codeClient;
    private String nom;
    private String prenom;
    private String tel;
    private String adresse;
    private String preference;
    private String telpreference;

    
    public String getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(String codeClient) {
        this.codeClient = codeClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public String getTelpreference() {
        return telpreference;
    }

    public void setTelpreference(String telpreference) {
        this.telpreference = telpreference;
    }
    public String getNomComplet(){
        return codeClient+" "+nom+" "+prenom;
    }
}
