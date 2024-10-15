
package modele;
import java.util.Date;
import java.time.LocalDate;

public class OperationCF {
    private String codeOp;
    private String codeClient;
    private String code;
    private String numero;
    private String idcat;
    private float montant;
    private Date dateop;
    private LocalDate datOp;

    public String getCodeOp() {
        return codeOp;
    }


    public void setCodeOp(String codeOp) {
        this.codeOp = codeOp;
    }

    public String getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(String codeClient) {
        this.codeClient = codeClient;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getIdcat() {
        return idcat;
    }

    public void setIdcat(String idcat) {
        this.idcat = idcat;
    }


    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public Date getDateop() {
        return dateop;
    }

    public void setDateop(Date dateop) {
        this.dateop = dateop;
    }

    
    public LocalDate getDatOp() {
        return datOp;
    }

    public void setDatOp() {
        this.datOp =datOp.now();
        
    }
}
