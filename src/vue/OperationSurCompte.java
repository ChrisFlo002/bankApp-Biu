
package vue;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import modele.*;
import controlleur.*;
import java.sql.*;
import java.util.ArrayList;

public class OperationSurCompte extends JFrame {
    JTable operation;
    public final DefaultTableModel model;
    CompteCF compte=null;
    JLabel lcompte;
    JComboBox numero;
    JButton bresearch;
    int index = 0;
    
    public OperationSurCompte(){
        
        lcompte = new JLabel("Compte");
        lcompte.setBounds(50, 50, 100, 20);
        this.getContentPane().add(lcompte);
        numero = new JComboBox();
        for(CompteCF co: Factory.getCompte()){
            numero.addItem(co.getInfoComplet());
        }
        numero.setBounds(170, 50, 250, 20);
        this.getContentPane().add(numero);
        numero.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
             index = numero.getSelectedIndex();
            }
        }
        );
        //pour bouton rechercher
     bresearch = new JButton("Rechercher");
     bresearch.setBounds(450, 50, 100, 20);
     this.getContentPane().add(bresearch);
     bresearch.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent l){
             compte = new CompteCF();
             compte = Factory.getCompteByNum(Factory.getCompte().get(index).getNumero());
             afficher(compte);
         }
     }
     );
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
    public void afficher(CompteCF hi){
        model.setRowCount(0);
        for(OperationCF op: Factory.getOPerationSurCompte(hi)){
            model.addRow(new Object[]{op.getCodeOp(),op,op.getCode(),op.getIdcat(),op.getNumero(),op.getDateop(),op.getMontant()}
            );
        }
         operation = new JTable(model);
        JScrollPane c =new JScrollPane(operation);
        c.setBounds(50,90,700,500);
        this.getContentPane().add(c);
    }
}
