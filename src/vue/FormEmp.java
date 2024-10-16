
package vue;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import modele.*;
import controlleur.*;

public class FormEmp extends JFrame {
    JLabel lc,ln,lp,lte,lad,lus,lpswd,lfonc,lagence;
    JTextField tc,tn,tp,tte,tad,tus,tpswd,tfonc;
    JButton bsave,bshow,bupdate,bresearch,bdelet;
    JComboBox tag;
    public final DefaultTableModel model;
    JTable templ;
    EmployeCF em = null;
    int indexage = 0;
    EmployeCF emploCon = Authentifier.emploConnected;
    public FormEmp(){
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
     //pour lus
     lus = new JLabel("Username");
     lus.setBounds(30, 250, 200, 20);
     this.getContentPane().add(lus);
     //pour lpswd
     lpswd = new JLabel("Password");
     lpswd.setBounds(30, 290, 200, 20);
     this.getContentPane().add(lpswd);
     //pour lfonc
     lfonc = new JLabel("Fonction");
     lfonc.setBounds(30, 330, 200, 20);
     this.getContentPane().add(lfonc);
     //pour lagence
        lagence = new JLabel("Agence");
        lagence.setBounds(30, 370, 200, 20);
        this.getContentPane().add(lagence);
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
     //pour tus
     tus = new JTextField();
     tus.setBounds(250, 250, 100, 20);
     this.getContentPane().add(tus);
     //pour tpswd
     tpswd = new JTextField();
     tpswd.setBounds(250, 290, 100, 20);
     this.getContentPane().add(tpswd);
     //pour tfonc
     tfonc = new JTextField();
     tfonc.setBounds(250, 330, 100, 20);
     this.getContentPane().add(tfonc);
     //pour tag
        tag =  new JComboBox();
        for(Agence age: Factory.getAgence()){
            tag.addItem(age.getNom());
        }
        tag.setBounds(250, 370, 100, 20);
        this.getContentPane().add(tag);
        tag.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
             indexage = tag.getSelectedIndex();
            }
        }
        );
     //pour bsave if admin
     bsave = new JButton("Enregistrer");
     bsave.setBounds(50, 420, 100, 20);
     if(emploCon.getFonction().equals("Admin")){
          this.getContentPane().add(bsave);
     }
    bsave.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
             em = new EmployeCF();
             try{
                 
                 em.setCode(tc.getText());
                 em.setNom(tn.getText());
                 em.setPrenom(tp.getText());
                 em.setTel(tte.getText());
                 em.setAdresse(tad.getText());
                 em.setUsername(tus.getText());
                 em.setPassword(tpswd.getText());
                 em.setFonction(tfonc.getText());
                 em.setAgence(Factory.getAgence().get(indexage).getNom());
             }
             catch(Exception k){
                 JOptionPane.showConfirmDialog(null, k.getMessage());
             
             }
             Factory.insertEmploye(em);
             
             afficher();
             effacer();
             
         }
     }
     );
     //pour bshow
     bshow = new JButton("Afficher");
     bshow.setBounds(170, 420, 100, 20);
     this.getContentPane().add(bshow);
     bshow.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent l){
             if(emploCon.getFonction().equals("Chef d'agence")){
                 afficherChef();
            }else {
                 afficher();
             }
             
         }
     }
     );
     //pour bupdate
     bupdate = new JButton("Modifier");
     bupdate.setBounds(290, 420, 100, 20);
     if(emploCon.getFonction().equals("Admin")){
        this.getContentPane().add(bupdate);
     }
     
     bupdate.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
             em = new EmployeCF();
             try{
             
                em.setCode(tc.getText());
                em.setNom(tn.getText());
                em.setPrenom(tp.getText());
                em.setTel(tte.getText());
                em.setAdresse(tad.getText());
                em.setUsername(tus.getText());
                em.setPassword(tpswd.getText());
                em.setFonction(tfonc.getText());
             }
             catch(Exception l){}
             Factory.updateEmplo(em);
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
             em = new EmployeCF();
             em = Factory.getEmplByCode(tc.getText());
             tn.setText(em.getNom());
             tp.setText(em.getPrenom());
             tte.setText(em.getTel());
             tad.setText(em.getAdresse());
             tus.setText(em.getUsername());
             tpswd.setText(em.getPassword());
             tfonc.setText(em.getFonction());
             tag.setSelectedItem(em.getAgence());
         }
     }
     );
     //pour bdelet
     bdelet = new JButton("Supprimer");
     bdelet.setBounds(410, 420, 100, 20);
     this.getContentPane().add(bdelet);
     bdelet.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
             em = new EmployeCF();
             em.setCode(tc.getText());
             em.setNom(tn.getText());
             em.setPrenom(tp.getText());
             em.setTel(tte.getText());
             em.setAdresse(tad.getText());
             em.setUsername(tus.getText());
             em.setPassword(tpswd.getText());
             em.setFonction(tfonc.getText());
             
             Factory.deleteEmplo(em);
             afficher();
             effacer();
         }
     
     }
     );
     
     
    //model de JTable
    model = new DefaultTableModel();
    model.addColumn("Code");
    model.addColumn("Nom");
    model.addColumn("Prenom");
    model.addColumn("Téléphone");
    model.addColumn("Adresse");
    model.addColumn("Username");
    model.addColumn("Password");
    model.addColumn("Fonction");
    model.addColumn("Agence");
     
     this.setLayout(null);
    }
    public void effacer(){
     tc.setText("");
     tn.setText("");
     tp.setText("");
     tte.setText("");
     tad.setText("");
     tus.setText("");
     tpswd.setText("");
     tfonc.setText("");
     
    }
    public void afficherChef(){
        model.setRowCount(0);
        for(EmployeCF e:Factory.getEmploAgence(emploCon.getAgence())){
          model.addRow(new Object[]{e.getCode(),e.getNom(),e.getPrenom(),e.getTel(),e.getAdresse(),e.getUsername(),e.getPassword(),e.getFonction(),e.getAgence()}
                  );
        }
        templ = new JTable(model);
        JScrollPane c =new JScrollPane(templ);
        c.setBounds(50,450,600,200);
        this.getContentPane().add(c);
    }
    public void afficher(){
        model.setRowCount(0);
        for(EmployeCF e:Factory.getEmplo()){
          model.addRow(new Object[]{e.getCode(),e.getNom(),e.getPrenom(),e.getTel(),e.getAdresse(),e.getUsername(),e.getPassword(),e.getFonction(),e.getAgence()}
                  );
        }
        templ = new JTable(model);
        JScrollPane c =new JScrollPane(templ);
        c.setBounds(50,450,600,200);
        this.getContentPane().add(c);
    }
    
}
