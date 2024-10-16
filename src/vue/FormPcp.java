
package vue;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import modele.EmployeCF;


public class FormPcp extends JFrame {
    JMenuBar bar;
    JMenu mDonnes,mFichier,mTrait,mrapp,mParam;
    JMenuItem iemp,iclient,iquitter,icat,icompte,iope,icomptes1c,icomP,iopSurCo;
    JLabel lpres;
    public FormPcp(EmployeCF em){
        //Message d'accueil
       lpres = new JLabel("Bonjour "+em.getNom()+" "+em.getPrenom()+". Bienvenu :)");
       lpres.setBounds(100,0, 300, 100);
       this.getContentPane().add(lpres);
        bar = new JMenuBar();
        
        mDonnes = new JMenu("Données de base");
        
        mFichier = new JMenu("Déconexion");
        
        mTrait = new JMenu("Traitement");
        
        mrapp = new JMenu("Interroger");
        
        mParam = new JMenu("Paramétrages");
        
        iclient = new JMenuItem("Clients");
        iclient.addActionListener(new ActionListener(){
                 public void actionPerformed(ActionEvent e){
                     FormClient fp = new FormClient();
                     fp.setTitle("Gestion des clients");
                     fp.setSize(800,800);
                     fp.setVisible(true);
                     
                 }
        }
        );
        iemp = new JMenuItem("Employés");
        iemp.addActionListener(new ActionListener(){
                 public void actionPerformed(ActionEvent e){
                     FormEmp fc = new FormEmp();
                     fc.setTitle("Gestion des employés");
                     fc.setSize(800,800);
                     fc.setVisible(true);
                     
                 }
        }
        );
        icat = new JMenuItem("Catégories d'opération");
        icat.addActionListener(new ActionListener(){
                 public void actionPerformed(ActionEvent e){
                     FormCatego fc = new FormCatego();
                     fc.setTitle("Gestion des catégories d'opérations");
                     fc.setSize(800,800);
                     fc.setVisible(true);
                     
                 }
        }
        );
        icompte = new JMenuItem("Comptes");
        icompte.addActionListener(new ActionListener(){
                 public void actionPerformed(ActionEvent e){
                     FormCompte fc = new FormCompte();
                     fc.setTitle("Gestion des comptes");
                     fc.setSize(800,800);
                     fc.setVisible(true);
                 }
        }
        );
        iope = new JMenuItem("Opération");
        iope.addActionListener(new ActionListener(){
                 public void actionPerformed(ActionEvent e){
                     FormOperation fc = new FormOperation();
                     fc.setTitle("Gestion des opérations");
                     fc.setSize(800,800);
                     fc.setVisible(true);
                 }
        }
        );
        icomptes1c = new JMenuItem("Trouver les comptes d'un client");
        icomptes1c.addActionListener(new ActionListener(){
                 public void actionPerformed(ActionEvent e){
                     Comptes1Client fc = new Comptes1Client();
                     fc.setTitle("Rapport des comptes d'un client");
                     fc.setSize(800,800);
                     fc.setVisible(true);
                 }
        }
        );
        
        icomP = new JMenuItem("Liste des comptes et de leurs propriétaires");
        icomP.addActionListener(new ActionListener(){
                 public void actionPerformed(ActionEvent e){
                     ComptesEtProprio fc = new ComptesEtProprio();
                     fc.setTitle("Rapport des comptes des clients");
                     fc.setSize(900,800);
                     fc.setVisible(true);
                 }
        }
        );
        
        iopSurCo = new JMenuItem("Trouver les opérations sur un compte");
        iopSurCo.addActionListener(new ActionListener(){
                 public void actionPerformed(ActionEvent e){
                     OperationSurCompte fc = new OperationSurCompte();
                     fc.setTitle("Rapport des opérations sur un compte");
                     fc.setSize(800,800);
                     fc.setVisible(true);
                 }
        }
        );
        iquitter = new JMenuItem("Quitter");
        iquitter.addActionListener(new ActionListener(){
                 public void actionPerformed(ActionEvent e){
                     Authentifier fc = new Authentifier();
                     fc.setTitle("Page de connexion");
                     fc.setSize(600,600);
                     fc.setVisible(true);
                 }
        }
        );
        
        
        mDonnes.add(iclient);
        mDonnes.add(iemp);
        mDonnes.add(icompte);
        mParam.add(icat);
        mTrait.add(iope);
        mrapp.add(icomP);
        mrapp.add(icomptes1c);
        mrapp.add(iopSurCo);
        mFichier.add(iquitter);
        //ajouter bar selon la fonction de l'employé connecté
         
        if((em.getFonction().equals("Admin")) || (em.getFonction().equals("Chef d'agence"))) {
            //JOptionPane.showMessageDialog(null, em.getNomComplet());
            bar.add(mFichier);
            bar.add(mDonnes);
            bar.add(mParam);
            bar.add(mTrait);
            bar.add(mrapp);
        }else if(em.getFonction().equals("Caissier")){
            bar.add(mFichier);
            bar.add(mTrait);
            bar.add(mrapp);
        }else if(em.getFonction().equals("Chef d'agence")){
            
        }
       
        
        this.setJMenuBar(bar);
        
        
    
    }
    
}
