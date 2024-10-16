
package vue;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import modele.*;
import controlleur.*;

public class Authentifier extends JFrame{
    JLabel lnom,lpass;
    JTextField tnom,tpass;
    JButton bcon;
    public static EmployeCF emploConnected = null;//employe à utiliser ailleurs
    public Authentifier(){
        //pour lnom
        lnom = new JLabel("Username");
        lnom.setBounds(50, 50, 100, 20);
        this.getContentPane().add(lnom);
        //pour lpass
        lpass = new JLabel("Password");
        lpass.setBounds(50, 90, 100, 20);
        this.getContentPane().add(lpass);
        //pour tnom
        tnom = new JTextField();
        tnom.setBounds(170, 50, 100, 20);
        this.getContentPane().add(tnom);
        //pour tpass
        tpass = new JTextField();
        tpass.setBounds(170, 90, 100, 20);
        this.getContentPane().add(tpass);
        //pour bcon
         bcon = new JButton("Connection");
     bcon.setBounds(170, 170, 100, 20);
     this.getContentPane().add(bcon);
     bcon.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent l){
             boolean trouve=false; 
             for(EmployeCF em: Factory.getEmplo()){
                 if((em.getUsername().equals(tnom.getText()) ) && (em.getPassword().equals(tpass.getText()))) {
                    emploConnected = new EmployeCF();
                    emploConnected = em;
                    //JOptionPane.showMessageDialog(null, emploConnected.getNomComplet());
                    FormPcp fp = new FormPcp(em);
                    fp.setTitle("Banque KANEZA");
                    fp.setSize(600, 600);
                    fp.setVisible(true);
                    trouve=true;
                    break;
                 }
                 
             }
             if(trouve == false){JOptionPane.showMessageDialog(null, "Paramètres incorrects");}
            effacer();
         }
     }
     );
       
     this.setLayout(null);
    }
    public void effacer(){
      tnom.setText("");
      tpass.setText("");
    }
    
}
