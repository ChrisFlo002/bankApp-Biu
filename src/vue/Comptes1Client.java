
package vue;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import modele.*;
import controlleur.*;



public class Comptes1Client extends JFrame{
    JTable comptes;
    public final DefaultTableModel model;
    ClientCF client=null;
    JLabel lclie;
    JComboBox codeCl;
    JButton bresearch;
    int index = 0;
    public Comptes1Client(){
        lclie = new JLabel("Client");
        lclie.setBounds(50, 50, 100, 20);
        this.getContentPane().add(lclie);
        codeCl = new JComboBox();
        for(ClientCF client: Factory.getClient()){
            codeCl.addItem(client.getNomComplet());
        }
        codeCl.setBounds(170, 50, 250, 20);
        this.getContentPane().add(codeCl);
        codeCl.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
             index = codeCl.getSelectedIndex();
            }
        }
        );
        //pour bouton rechercher
     bresearch = new JButton("Rechercher");
     bresearch.setBounds(450, 50, 100, 20);
     this.getContentPane().add(bresearch);
     bresearch.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent l){
             client = new ClientCF();
             client = Factory.getClientByCode(Factory.getClient().get(index).getCodeClient());
             afficher(client);
         }
     }
     );
       //model de JTable
    model = new DefaultTableModel();
    model.addColumn("Numéro de compte");
    model.addColumn("Catégorie");
    model.addColumn("Date d'ouverture");
    model.addColumn("Agence");
    model.addColumn("Solde");
    
    this.setLayout(null);
    }
    public void afficher(ClientCF cli){
        
            for(CompteCF compte: Factory.getCompteDuClient(cli)){
                model.addRow(new Object[]{compte.getNumero(),compte.getCategorie(),compte.getDatOuv(),compte.getAgence(),compte.getSolde()});
            }
        comptes = new JTable(model);
        JScrollPane c =new JScrollPane(comptes);
        c.setBounds(50,90,700,500);
        this.getContentPane().add(c);
    }
}
