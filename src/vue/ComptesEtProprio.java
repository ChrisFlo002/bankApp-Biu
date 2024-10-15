
package vue;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import modele.*;
import controlleur.*;


public class ComptesEtProprio extends JFrame{
    JTable comptesEtP;
    public final DefaultTableModel model;
    public ComptesEtProprio(){
         //model de JTable
    model = new DefaultTableModel();
    //model.addColumn("Propriétaire");
    model.addColumn("Nom");
    model.addColumn("Prenom");
    //model.addColumn("Téléphone");
    model.addColumn("Numéro de compte");
    model.addColumn("Catégorie");
    model.addColumn("Date d'ouverture");
    model.addColumn("Solde");
    
    
    afficher();
    
    this.setLayout(null);
        
    }
    
    public void afficher(){
        for(ClientCF client:Factory.getClient()){
            for(CompteCF compte: Factory.getCompteDuClient(client)){
                model.addRow(new Object[]{/*client.getCodeClient(),*/client.getNom(),client.getPrenom(),/*client.getTel(),*/compte.getNumero(),compte.getCategorie(),compte.getDatOuv(),compte.getSolde()});
            }
        }
        comptesEtP = new JTable(model);
        JScrollPane c =new JScrollPane(comptesEtP);
        c.setBounds(50,100,700,500);
        this.getContentPane().add(c);
    }
}
