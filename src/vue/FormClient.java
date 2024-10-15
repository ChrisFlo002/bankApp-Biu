
package vue;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import modele.*;
import controlleur.*;


public class FormClient extends JFrame{
    JLabel lc,ln,lp,lte,lad,lpr,ltep;
    JTextField tc,tn,tp,tte,tad,tpr,ttep;
    JButton bsave,bshow,bupdate,bresearch,bdelet;
    public final DefaultTableModel model;
    JTable tclient;
    ClientCF client=null;
    public FormClient(){
        //pour lc
     lc = new JLabel("Code");
     lc.setBounds(30, 50, 200, 20);
     this.getContentPane().add(lc);
     //pour ln
     ln = new JLabel("Nom");
     ln.setBounds(30, 90, 200, 20);
     this.getContentPane().add(ln);
     //pour lp
     lp = new JLabel("Prenom");
     lp.setBounds(30, 130, 200, 20);
     this.getContentPane().add(lp);
     //pour lte
     lte = new JLabel("Téléphone");
     lte.setBounds(30, 170, 200, 20);
     this.getContentPane().add(lte);
     //pour lad
     lad = new JLabel("Adresse");
     lad.setBounds(30, 210, 200, 20);
     this.getContentPane().add(lad);
     //pour lpr
     lpr = new JLabel("Personne de référence");
     lpr.setBounds(30, 250, 200, 20);
     this.getContentPane().add(lpr);
     //pour ltep
     ltep = new JLabel("Téléphone");
     ltep.setBounds(30, 290, 200, 20);
     this.getContentPane().add(ltep);
     //pour tc
     tc = new JTextField();
     tc.setBounds(250, 50, 100, 20);
     this.getContentPane().add(tc);
     //pour tn
     tn = new JTextField();
     tn.setBounds(250, 90, 100, 20);
     this.getContentPane().add(tn);
     //pour tp
     tp = new JTextField();
     tp.setBounds(250, 130, 100, 20);
     this.getContentPane().add(tp);
     //pour tte
     tte = new JTextField();
     tte.setBounds(250, 170, 100, 20);
     this.getContentPane().add(tte);
     //pour tad
     tad = new JTextField();
     tad.setBounds(250, 210, 100, 20);
     this.getContentPane().add(tad);
     //pour tpr
     tpr = new JTextField();
     tpr.setBounds(250, 250, 100, 20);
     this.getContentPane().add(tpr);
     //pour ttep
     ttep = new JTextField();
     ttep.setBounds(250, 290, 100, 20);
     this.getContentPane().add(ttep);
     //pour bsave
     bsave = new JButton("Enregistrer");
     bsave.setBounds(50, 350, 100, 20);
     this.getContentPane().add(bsave);
     bsave.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent k){
             try{
         client =new ClientCF();
         client.setCodeClient(tc.getText());
         client.setNom(tn.getText());
         client.setPrenom(tp.getText());
         client.setTel(tte.getText());
         client.setAdresse(tad.getText());
         client.setPreference(tpr.getText());
         client.setTelpreference(ttep.getText());
             }
             catch(Exception e){
             JOptionPane.showConfirmDialog(null, e.getMessage());
             }
         Factory.insertClient(client);
         afficher();
         effacer();
         }
     }
     );
     //pour bshow
     bshow = new JButton("Afficher");
     bshow.setBounds(170, 350, 100, 20);
     this.getContentPane().add(bshow);
     bshow.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
             afficher();
         }
     }
     );
     //pour bupdate
     bupdate = new JButton("Modifier");
     bupdate.setBounds(290, 350, 100, 20);
     this.getContentPane().add(bupdate);
     bupdate.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
             client = new ClientCF();
             try{
                client.setCodeClient(tc.getText());
                client.setNom(tn.getText());
                client.setPrenom(tp.getText());
                client.setTel(tte.getText());
                client.setAdresse(tad.getText());
                client.setPreference(tpr.getText());
                client.setTelpreference(ttep.getText());  
             }
             catch(Exception l){}
             Factory.updateClient(client);
             afficher();
             effacer();
             
         }
     }
     );
     //pour bresearch
     bresearch = new JButton("Rechercher");
     bresearch.setBounds(370, 50, 100, 20);
     this.getContentPane().add(bresearch);
     bresearch.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent l){
             client = new ClientCF();
             client = Factory.getClientByCode(tc.getText());
             tn.setText(client.getNom());
             tp.setText(client.getPrenom());
             tte.setText(client.getTel());
             tad.setText(client.getAdresse());
             tpr.setText(client.getPreference());
             ttep.setText(client.getTelpreference());
         }
     }
     );
     //pour bdelet
     bdelet = new JButton("Supprimer");
     bdelet.setBounds(410, 350, 100, 20);
     this.getContentPane().add(bdelet);
     bdelet.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
             client = new ClientCF();
             client.setCodeClient(tc.getText());
             client.setNom(tn.getText());
             client.setPrenom(tp.getText());
             client.setTel(tte.getText());
             client.setAdresse(tad.getText());
             client.setPreference(tpr.getText());
             client.setTelpreference(ttep.getText());
             
             Factory.deleteClient(client);
             afficher();
             effacer();
         }
     }
     );
     
      //model de JTable
        model = new DefaultTableModel();
        model.addColumn("Code ");
        model.addColumn("Nom");
        model.addColumn("Prenom");
        model.addColumn("Téléphone");
        model.addColumn("Adresse");
        model.addColumn("Personne de reference");
        model.addColumn("Téléphone");
        
     this.setLayout(null);
    
    }
    public void effacer(){
     tc.setText("");
     tn.setText("");
     tp.setText("");
     tte.setText("");
     tad.setText("");
     ttep.setText("");
     tpr.setText("");
    } 
    public void afficher(){
        model.setRowCount(0);
        for(ClientCF e:Factory.getClient()){
            model.addRow(new Object[]{e.getCodeClient(),e.getNom(),e.getPrenom(),e.getTel(),e.getAdresse(),e.getPreference(),e.getTelpreference()});
          }
        tclient = new JTable(model);
        JScrollPane c =new JScrollPane(tclient);
        c.setBounds(50,400,500,200);
        this.getContentPane().add(c);
    }
    
}
