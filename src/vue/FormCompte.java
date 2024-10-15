
package vue;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import modele.*;
import controlleur.*;
import java.util.Date;

public class FormCompte extends JFrame{
    JLabel lnum,lcodeClient,lcat,ldatouv,lsolde,lag,letat;
    JTextField tnum,tcat,tdatouv,tsolde,tetat;
    JComboBox codeCli,tag;
    JButton bsave,bshow,bupdate,bresearch,bdelet;
    public final DefaultTableModel model;
    JTable tCompte;
    CompteCF compte = null;
    int index = 0,indexage = 0;
    
    public FormCompte(){
        //pour letat
        letat = new JLabel("Etat");
        letat.setBounds(50, 10, 200, 20);
        this.getContentPane().add(letat);
        //pour tetat
        tetat = new JTextField();
        tetat.setBounds(270, 10, 100, 20);
        tetat.setEditable(false);
        this.getContentPane().add(tetat);
        //pour lnum
        lnum = new JLabel("Numéro ");
        lnum.setBounds(50, 50, 200, 20);
        this.getContentPane().add(lnum);
        //pour lcodeClient
        lcodeClient = new JLabel("Client");
        lcodeClient.setBounds(50, 90, 200, 20);
        this.getContentPane().add(lcodeClient);
        //pour lcat
        lcat = new JLabel("Categorie");
        lcat.setBounds(50, 130, 200, 20);
        this.getContentPane().add(lcat);
        //pour ldatouv
        ldatouv = new JLabel("Date d'ouverture");
        ldatouv.setBounds(50, 170, 200, 20);
        this.getContentPane().add(ldatouv);
        //pour lsolde
        lsolde = new JLabel("Solde");
        lsolde.setBounds(50, 210, 200, 20);
        this.getContentPane().add(lsolde);
        //pour lag
        lag = new JLabel("Agence");
        lag.setBounds(50, 250, 200, 20);
        this.getContentPane().add(lag);
        //pour tnum
        tnum = new JTextField();
        tnum.setBounds(270, 50, 100, 20);
        this.getContentPane().add(tnum);
        //pour codeCli
        codeCli = new JComboBox();
        for(ClientCF p: Factory.getClient()){
            codeCli.addItem(p.getNomComplet());
        }
        codeCli.setBounds(270, 90, 100, 20);
        this.getContentPane().add(codeCli);
        codeCli.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
             index = codeCli.getSelectedIndex();
            }
        }
        );
        //pour tcat
        tcat = new JTextField();
        tcat.setBounds(270, 130, 100, 20);
        this.getContentPane().add(tcat);
        //por tdatouv
        tdatouv = new JTextField();
        tdatouv.setBounds(270, 170, 100, 20);
        tdatouv.setEditable(false);
        this.getContentPane().add(tdatouv);
        //pour tsolde
        tsolde = new JTextField();
        tsolde.setBounds(270, 210, 100, 20);
        this.getContentPane().add(tsolde);
        //pour tag
        tag =  new JComboBox();
        for(Agence age: Factory.getAgence()){
            tag.addItem(age.getNom());
        }
        tag.setBounds(270, 250, 100, 20);
        this.getContentPane().add(tag);
        tag.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
             indexage = tag.getSelectedIndex();
            }
        }
        );
        //pour bsave
     bsave = new JButton("Enregistrer");
     bsave.setBounds(50, 300, 100, 20);
     this.getContentPane().add(bsave);
     bsave.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
             compte = new CompteCF();
             try{
                 compte.setNumero(tnum.getText());
                 compte.setCodeClient(Factory.getClient().get(index).getCodeClient());
                 compte.setCategorie(tcat.getText());
                 compte.setDatOuvL();
                 compte.setSolde(Float.valueOf(tsolde.getText()));
                 compte.setAgence(Factory.getAgence().get(indexage).getNom());
                 Factory.insertCompte(compte);
             }
             catch(Exception k){
                 JOptionPane.showConfirmDialog(null, k.getMessage());
             } 
             afficher();
             effacer();
         }
     }
     );
     //pour bshow
     bshow = new JButton("Afficher");
     bshow.setBounds(170, 300, 100, 20);
     this.getContentPane().add(bshow);
     bshow.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent l){
           afficher();
         }
     }
     );
     //pour bupdate
     /*bupdate = new JButton("Mettre à jour");
     bupdate.setBounds(290, 300, 100, 20);
     this.getContentPane().add(bupdate);
     bupdate.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            
        }
     }
     );*/
     //pour bresearch
     bresearch = new JButton("Rechercher");
     bresearch.setBounds(390, 50, 100, 20);
     this.getContentPane().add(bresearch);
     bresearch.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent l){
            compte = new CompteCF();
            compte = Factory.getCompteByNum(tnum.getText());
            if(compte != null){
            codeCli.setSelectedItem(compte.getCodeClient());
            tcat.setText(compte.getCategorie());
            tdatouv.setText(String.valueOf(compte.getDatOuv()));
            tsolde.setText(String.valueOf(compte.getSolde()));
            tag.setSelectedItem(compte.getAgence());
         }
         }
     }
     );
     //pour bdelet
    bdelet = new JButton("Supprimer");
     bdelet.setBounds(290, 300, 100, 20);
     this.getContentPane().add(bdelet);
     bdelet.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            Factory.deleteCompte(tnum.getText());
            afficher();
            effacer();
         }
     
     }
     );
     
     
    //model de JTable
    model = new DefaultTableModel();
    model.addColumn("Numéro de compte");
    model.addColumn("Propriétaire");
    model.addColumn("Catégorie");
    model.addColumn("Date d'ouverture");
    model.addColumn("Solde");
    model.addColumn("Agence");
    model.addColumn("Etat");
        
        
        this.setLayout(null);
    }
    //tnum,tcat,tdatouv,tsolde,tag
    public void effacer(){
        tnum.setText("");
        tcat.setText("");
        tdatouv.setText("");
        tsolde.setText("");
        
    }
    public void afficher(){
        model.setRowCount(0);
        for(CompteCF cf: Factory.getCompte()){
            model.addRow(new Object[]{cf.getNumero(),cf.getCodeClient(),cf.getCategorie(),cf.getDatOuv(),cf.getSolde(),cf.getAgence(),cf.getEtat()}
            );
            
        }
        tCompte = new JTable(model);
        JScrollPane c =new JScrollPane(tCompte);
        c.setBounds(50,400,600,100);
        this.getContentPane().add(c);
    }
    
}
