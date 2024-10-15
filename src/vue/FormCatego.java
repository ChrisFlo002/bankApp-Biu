
package vue;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import modele.*;
import controlleur.*;

public class FormCatego extends JFrame{
    JLabel lcat,llib;
    JTextField tcat;
    JComboBox clib;
    JButton bsave,bshow,bupdate,bresearch,bdelet;
     public final DefaultTableModel model;
    JTable tcatego;
    CategorieCF categ = null;
    String[] sorte = {" ","Ouverture","Retrait","Versement","Desactiver"};
     public FormCatego(){
         //pour lcat
         lcat = new JLabel("Identifiant");
         lcat.setBounds(50, 50, 200, 20);
         this.getContentPane().add(lcat);
         //pour llib
         llib = new JLabel("Libellé");
         llib.setBounds(50, 90, 200, 20);
         this.getContentPane().add(llib);
         //pour tcat
         tcat = new JTextField();
         tcat.setBounds(270, 50, 100, 20);
         this.getContentPane().add(tcat);
         //pour clib
         clib = new JComboBox(sorte);
         clib.setBounds(270, 90, 100, 20);
         this.getContentPane().add(clib);
         //pour bsave
     bsave = new JButton("Enregistrer");
     bsave.setBounds(50, 120, 100, 20);
     this.getContentPane().add(bsave);
    bsave.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
             categ = new CategorieCF();
             try{
                 
                 categ.setIdcat(tcat.getText());
                 categ.setLibelle(clib.getSelectedItem().toString());
                
             }
             catch(Exception k){
                 JOptionPane.showConfirmDialog(null, k.getMessage());
             
             }
             Factory.insertCategorie(categ);
             
             afficher();
             effacer();
           
         }
     }
     );
     //pour bshow
     bshow = new JButton("Afficher");
     bshow.setBounds(170, 120, 100, 20);
     this.getContentPane().add(bshow);
     bshow.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent l){
             afficher();
         }
     }
     );
     //pour bupdate
     bupdate = new JButton("Modifier");
     bupdate.setBounds(290, 120, 100, 20);
     this.getContentPane().add(bupdate);
     bupdate.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
             try{
                categ = new CategorieCF();
                categ.setIdcat(tcat.getText());
                categ.setLibelle(clib.getSelectedItem().toString());

             }
             catch(Exception l){}
             Factory.updateCategorie(categ);
             afficher();
             effacer();
         }
     }
     );
     //pour bresearch
     bresearch = new JButton("Rechercher");
     bresearch.setBounds(390, 50, 100, 20);
     this.getContentPane().add(bresearch);
     bresearch.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent l){
            categ = new CategorieCF();
            categ = Factory.getCategById(tcat.getText());
            tcat.setText(categ.getIdcat());
            clib.setSelectedItem(categ.getLibelle());
         }
     }
     );
     //pour bdelet
     bdelet = new JButton("Supprimer");
     bdelet.setBounds(410, 120, 100, 20);
     this.getContentPane().add(bdelet);
     bdelet.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            
             categ = new CategorieCF();
             categ.setIdcat(tcat.getText());
             categ.setLibelle(clib.getSelectedItem().toString());
            Factory.deleteCat(categ);
            afficher();
            effacer();
         }
     
     }
     );
     
     
    //model de JTable
    model = new DefaultTableModel();
    model.addColumn("Identifiant");
    model.addColumn("Libellé");
         
    this.setLayout(null);
         
     }
     public void effacer(){
      tcat.setText("");
      clib.setSelectedItem(" ");
     }
     public void afficher(){
         model.setRowCount(0);
         for(CategorieCF cf: Factory.getCategorie()){
             model.addRow(new Object[]{cf.getIdcat(),cf.getLibelle()}
             );
         }
         tcatego = new JTable(model);
        JScrollPane c =new JScrollPane(tcatego);
        c.setBounds(50,300,600,200);
        this.getContentPane().add(c);
     }
    
}
