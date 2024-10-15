
package vue;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import modele.*;
import controlleur.*;

public class FormOperation extends JFrame{
    JLabel lcodOP,lcodecl,lcode,lidcat,lnum,ldate,lmont;
    JComboBox codecli,code,idcat,numero;
    JTextField tcodeOp,tdate,tmont;
    JButton bsave,bshow,bupdate,bresearch,bdelet;
    public final DefaultTableModel model;
    JTable toperation;
    OperationCF ope = null;
    int indexCli = 0, indexEmploye = 0, indexCompte = 0,indexCate = 0;
    public FormOperation(){
        //pour lcodOp
        lcodOP = new JLabel("Code opération");
        lcodOP.setBounds(50, 50, 200, 20);
        this.getContentPane().add(lcodOP);
        //pour lcodecl
        lcodecl = new JLabel("Client");
        lcodecl.setBounds(50, 90, 200, 20);
        this.getContentPane().add(lcodecl);
        //pour lcode
        lcode = new JLabel("Employé");
        lcode.setBounds(50, 130, 200, 20);
        this.getContentPane().add(lcode);
        //pour lidcat
        lidcat = new JLabel("Catégorie d'opération");
        lidcat.setBounds(50, 170, 200, 20);
        this.getContentPane().add(lidcat);
        //pour lnum
        lnum = new JLabel("Compte");
        lnum.setBounds(50, 210, 200, 20);
        this.getContentPane().add(lnum);
        //pour ldate
        ldate = new JLabel("Date");
        ldate.setBounds(50, 250, 200, 20);
        this.getContentPane().add(ldate);
        //pour lmont
        lmont = new JLabel("Montant");
        lmont.setBounds(50, 290, 200, 20);
        this.getContentPane().add(lmont);
        //pour tcodeOp
        tcodeOp = new JTextField();
        tcodeOp.setBounds(270, 50, 100, 20);
        this.getContentPane().add(tcodeOp);
        //pour codecli
        codecli = new JComboBox();
        for(ClientCF client: Factory.getClient()){
            codecli.addItem(client.getNomComplet());
        }
        codecli.setBounds(270, 90, 250, 20);
        this.getContentPane().add(codecli);
        codecli.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
             indexCli = codecli.getSelectedIndex();
            }
        }
        );
        //pour code
        code = new JComboBox();
        for(EmployeCF em: Factory.getEmplo()){
            code.addItem(em.getNomComplet());
        }
        code.setBounds(270, 130, 250, 20);
        this.getContentPane().add(code);
        code.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
             indexEmploye = code.getSelectedIndex();
            }
        }
        );
        //pour idcat
        idcat = new JComboBox();
        for(CategorieCF cat: Factory.getCategorie()){
            idcat.addItem(cat.getInfoComplet());
        }
        idcat.setBounds(270, 170, 250, 20);
        this.getContentPane().add(idcat);
        idcat.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
             indexCate = idcat.getSelectedIndex();
            }
        }
        );
        // pour numero
        numero = new JComboBox();
        for(CompteCF co: Factory.getCompte()){
            numero.addItem(co.getInfoComplet());
        }
        numero.setBounds(270, 210, 250, 20);
        this.getContentPane().add(numero);
        numero.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
             indexCompte = numero.getSelectedIndex();
            }
        }
        );
        //pour tdate 
        tdate = new JTextField();
        tdate.setBounds(270, 250, 100, 20);
        tdate.setEditable(false);
        this.getContentPane().add(tdate);
        // pour tmont
        tmont = new JTextField();
        tmont.setBounds(270, 290, 100, 20);
        this.getContentPane().add(tmont);
        //pour bsave
        bsave = new JButton("Enregistrer");
        bsave.setBounds(50, 400, 100, 20);
        this.getContentPane().add(bsave);
        bsave.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            ope = new OperationCF();
             try{
                ope.setCodeOp(tcodeOp.getText());
                ope.setCodeClient(Factory.getClient().get(indexCli).getCodeClient());
                ope.setCode(Factory.getEmplo().get(indexEmploye).getCode());
                ope.setIdcat(Factory.getCategorie().get(indexCate).getIdcat());
                ope.setNumero(Factory.getCompte().get(indexCompte).getNumero());
                ope.setDatOp();
                ope.setMontant(Float.valueOf(tmont.getText()));
             }
             catch(Exception k){
                 JOptionPane.showConfirmDialog(null, k.getMessage());
             
             }
             Factory.insertOperation(ope);
             
             afficher();
             effacer();
             
         }
     }
     );
     //pour bshow
     bshow = new JButton("Afficher");
     bshow.setBounds(170, 400, 100, 20);
     this.getContentPane().add(bshow);
     bshow.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent l){
             afficher();
         }
     }
     );
     //pour bupdate
     /*bupdate = new JButton("Modifier");
     bupdate.setBounds(290, 400, 100, 20);
     this.getContentPane().add(bupdate);
     bupdate.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            try{
                ope.setCodeOp(tcodeOp.getText());
                ope.setCodeClient(Factory.getClient().get(indexCli).getCodeClient());
                ope.setCode(Factory.getEmplo().get(indexEmploye).getCode());
                ope.setIdcat(Factory.getCategorie().get(indexCate).getIdcat());
                ope.setNumero(Factory.getCompte().get(indexCompte).getNumero());
                ope.setDatOp();
                ope.setMontant(Float.valueOf(tmont.getText()));
             }
             catch(Exception k){
                 JOptionPane.showConfirmDialog(null, k.getMessage());
             
             } 
            Factory.updateOperation(ope);
         }
     }
     );*/
     //pour bresearch
     bresearch = new JButton("Rechercher");
     bresearch.setBounds(390, 50, 100, 20);
     this.getContentPane().add(bresearch);
     bresearch.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent l){
            ope = new OperationCF();
           ope = Factory.getOperatByCode(tcodeOp.getText());
           codecli.setSelectedItem(ope.getCodeClient());
           code.setSelectedItem(ope.getCode());
           idcat.setSelectedItem(ope.getIdcat());
           numero.setSelectedItem(ope.getNumero());
           tdate.setText(ope.getDateop().toString());
           tmont.setText(String.valueOf(ope.getMontant()));
         }
     }
     );
     //pour bdelet
    /* bdelet = new JButton("Supprimer");
     bdelet.setBounds(410, 400, 100, 20);
     this.getContentPane().add(bdelet);
     bdelet.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
           
         }
     
     }
     );*/
     
     
    //model de JTable
    model = new DefaultTableModel();
    model.addColumn("Code d'opération");
    model.addColumn("Client");
    model.addColumn("Employé");
    model.addColumn("Catégorie d'opération");
    model.addColumn("Numero de compte");
    model.addColumn("Date");
    model.addColumn("Montant");
        
        this.setLayout(null);
        
    }
    public void effacer(){
        tcodeOp.setText("");
        tdate.setText("");
        tmont.setText("");
        codecli.setSelectedItem("");
        code.setSelectedItem("");
        idcat.setSelectedItem("");
        numero.setSelectedItem("");
        
    }
    public void afficher(){
        model.setRowCount(0);
        for(OperationCF op: Factory.getOPeration()){
            model.addRow(new Object[]{op.getCodeOp(),op.getCodeClient(),op.getCode(),op.getIdcat(),op.getNumero(),op.getDateop(),op.getMontant()}
            );
        }
        toperation = new JTable(model);
        JScrollPane c =new JScrollPane(toperation);
        c.setBounds(50,460,600,100);
        this.getContentPane().add(c);
    }
    
}
