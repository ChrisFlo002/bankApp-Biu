
package controlleur;
import java.sql.*;
import java.util.ArrayList;
import modele.*;
import javax.swing.*;
import modele.*;
import java.time.LocalDate;

public class Factory {
    private static Connection conn= null;
    private static Statement stm;
    private static PreparedStatement prtstm;
    private static ResultSet rs= null;
    private static ArrayList<EmployeCF> liste= new ArrayList();
    private static ArrayList<ClientCF> listeC = new ArrayList();
    //private static ArrayList<CategorieCF> listeCat = new ArrayList();
    private static ArrayList<CompteCF> listeCom = new ArrayList();
    private static ArrayList<OperationCF> listeOp = new ArrayList();
    private static EmployeCF empl = null;
    private static ClientCF client =null;
    private static CategorieCF catego= null;
    private static Agence agence = null;
    private static CompteCF compte= null;
    private static OperationCF ope = null;
    
    //fonction pour la connection
      public static Connection getConnection(){
          String server = "localhost";
          int port= 3306;
          String database = "banquekaneza";
          String user = "root";
          String paswd="";
          try{
              Class.forName("com.mysql.jdbc.Driver").newInstance();
              String url = "jdbc:mysql://"+server+":"+port+"/"+database;
              conn =DriverManager.getConnection(url, user, paswd);
              return conn;
          }
          catch(Exception k){
          k.printStackTrace();
          return null;
          }
      }
      //fonction pour afficher tous les employés
      public static ArrayList<EmployeCF> getEmplo(){
          ArrayList<EmployeCF> listeemp = new ArrayList();
          try{
            conn = getConnection();
            stm = conn.createStatement();
            rs = stm.executeQuery("Select * from banquekaneza.employe");
            while(rs.next()){
            empl = new EmployeCF();
            empl.setCode(rs.getString("code"));
            empl.setNom(rs.getString("nom"));
            empl.setPrenom(rs.getString("prenom"));
            empl.setTel(rs.getString("tel"));
            empl.setAdresse(rs.getString("adresse"));
            empl.setUsername(rs.getString("username"));
            empl.setPassword(rs.getString("password"));
            empl.setFonction(rs.getString("fonction"));
            
            liste.add(empl);
            }
            conn.close();
            stm.close();
          }
          catch(Exception k){
          JOptionPane.showMessageDialog(null, k.getMessage());
          return null; 
          }
      
      return liste;
      }
       //fonction pour afficher tous les clients
      public static ArrayList<ClientCF> getClient(){
          ArrayList<ClientCF> listecli = new ArrayList();
          try{
            conn = getConnection();
            stm =conn.createStatement();
            rs = stm.executeQuery("Select * from banquekaneza.clients");
            while(rs.next()){
            client = new ClientCF();
            client.setCodeClient(rs.getString("codeClient"));
            client.setNom(rs.getString("nom"));
            client.setPrenom(rs.getString("prenom"));
            client.setTel(rs.getString("tel"));
            client.setAdresse(rs.getString("adresse"));
            client.setPreference(rs.getString("preference"));
            client.setTelpreference(rs.getString("telpreference"));
            
            
            listecli.add(client);
            }
            conn.close();
            stm.close();
          }
          catch(Exception k){
          JOptionPane.showMessageDialog(null, k.getMessage());
          return null; 
          }
      
      return listecli;
      }
      //fonction pour inserer un employe
      public static void insertEmploye(EmployeCF f){
          try{
              conn = getConnection();
              prtstm = conn.prepareStatement("insert into banquekaneza.employe(code,nom,prenom,tel,adresse,username,password,fonction) values(?,?,?,?,?,?,?,?)");
              prtstm.setString(1, f.getCode());
              prtstm.setString(2, f.getNom());
              prtstm.setString(3, f.getPrenom());
              prtstm.setString(4, f.getTel());
              prtstm.setString(5, f.getAdresse());
              prtstm.setString(6, f.getUsername());
              prtstm.setString(7, f.getPassword());
              prtstm.setString(8, f.getFonction());
              
              prtstm.executeUpdate();
              conn.close();
              prtstm.close();
          }
          catch(Exception k){}
      }
      //fonction pour inserer un client
      public static void insertClient(ClientCF f){
          try{
              conn = getConnection();
              prtstm = conn.prepareStatement("insert into banquekaneza.clients(codeClient,nom,prenom,tel,adresse,preference,telpreference) values(?,?,?,?,?,?,?)");
              prtstm.setString(1, f.getCodeClient());
              prtstm.setString(2, f.getNom());
              prtstm.setString(3, f.getPrenom());
              prtstm.setString(4, f.getTel());
              prtstm.setString(5, f.getAdresse());
              prtstm.setString(6, f.getPreference());
              prtstm.setString(7, f.getTelpreference());
              
              prtstm.executeUpdate();
              conn.close();
              prtstm.close();
          }
          catch(Exception k){}
      }
      //fonction pour mettre à jour un client
      public static void updateClient(ClientCF h){
           try{
              conn = getConnection();
              prtstm = conn.prepareStatement("update banquekaneza.clients set nom ='"+h.getNom()+"',prenom='"+h.getPrenom()+"',tel='"+h.getTel()+"',adresse='"+h.getAdresse()+"',preference='"+h.getPreference()+"',telpreference='"+h.getTelpreference()+"' where codeClient='"+h.getCodeClient()+"'");
             
              prtstm.executeUpdate();
              conn.close();
              prtstm.close();
           }
           catch(Exception k){}
              
      }
      //fonction pour mettre à jour un employe
      public static void updateEmplo(EmployeCF em){
          try{
             conn = getConnection();
             prtstm = conn.prepareStatement("update banquekaneza.employe set nom='"+em.getNom()+"',prenom='"+em.getPrenom()+"',tel='"+em.getTel()+"',adresse='"+em.getAdresse()+"',username='"+em.getUsername()+"',password='"+em.getPassword()+"',fonction='"+em.getFonction()+"' where code='"+em.getCode()+"'");
             
            prtstm.executeUpdate();
            conn.close();
            prtstm.close(); 
          }
          catch(Exception e){}
      
      }
      //fonction pour rechercher un client
      public static ClientCF getClientByCode(String co){
          try{
            conn = getConnection();
            stm =conn.createStatement();
            rs = stm.executeQuery("select * from banquekaneza.clients where codeClient='"+co+"'");
            while(rs.next()){
                client = new ClientCF();
                client.setCodeClient(rs.getString("codeClient"));
                client.setNom(rs.getString("nom"));
                client.setPrenom(rs.getString("prenom"));
                client.setTel(rs.getString("tel"));
                client.setAdresse("adresse");
                client.setPreference(rs.getString("preference"));
                client.setTelpreference(rs.getString("telpreference"));
                
            }
            conn.close();
            stm.close();
          }
          
          catch(Exception l){}
      
      return client;
      }
      //fonction pour rechercher un employe
      public static EmployeCF getEmplByCode(String co){
           try{
            conn = getConnection();
            stm =conn.createStatement();
            rs = stm.executeQuery("select * from banquekaneza.employe where code='"+co+"'");
            while(rs.next()){
                empl = new EmployeCF();
                empl.setCode(rs.getString("code"));
                empl.setNom(rs.getString("nom"));
                empl.setPrenom(rs.getString("prenom"));
                empl.setTel(rs.getString("tel"));
                empl.setAdresse("adresse");
                empl.setUsername(rs.getString("username"));
                empl.setPassword(rs.getString("password"));
                empl.setFonction(rs.getString("fonction"));
                
            }
            conn.close();
            stm.close();
          }
          
          catch(Exception l){}
          return empl;
      }
      //fonction pour supprimer un employe
      public static void deleteEmplo(EmployeCF em){
          try{
            conn = getConnection();
            prtstm = conn.prepareStatement("delete from banquekaneza.employe where code='"+em.getCode()+"'");
              
            prtstm.executeUpdate();
            conn.close();
            prtstm.close();
              
        }
        catch(Exception k){
        }
          
      }
      //fonctionpour supprimer un client
      public static void deleteClient(ClientCF cl){
          try{
            conn = getConnection();
            prtstm = conn.prepareStatement("delete from banquekaneza.clients where codeClient='"+cl.getCodeClient()+"'");
              
            prtstm.executeUpdate();
            conn.close();
            prtstm.close();
              
        }
        catch(Exception k){
        }
      }
      //fonction pour inserer une catégorie
      public static void insertCategorie(CategorieCF ca){
          try{
              conn = getConnection();
              prtstm = conn.prepareStatement("insert into banquekaneza.categorie(idcat,libelle) values('"+ca.getIdcat()+"','"+ca.getLibelle()+"')");
              
              prtstm.executeUpdate();
              conn.close();
              prtstm.close();
          }
          catch(Exception l){}
      }
      //fonction pour inserer un compte
      public static void insertCompte(CompteCF co){
           try{
              conn = getConnection();
              prtstm = conn.prepareStatement("insert into banquekaneza.compte(numero,codeClient,categorie,solde,agence) values(?,?,?,?,?)");
              prtstm.setString(1, co.getNumero());
              prtstm.setString(2, co.getCodeClient());
              prtstm.setString(3, co.getCategorie());
              prtstm.setFloat(4, co.getSolde());
              prtstm.setString(5, co.getAgence());
              
              prtstm.executeUpdate();
              conn.close();
              prtstm.close();
          }
          catch(Exception l){
            JOptionPane.showMessageDialog(null, l.getMessage());
          }
      }
      //fonction pour afficher les categories
      public static ArrayList<CategorieCF> getCategorie(){
          ArrayList<CategorieCF> listeCat = new ArrayList();
          try{
             
            conn = getConnection();
            stm =conn.createStatement();
            rs = stm.executeQuery("Select * from banquekaneza.categorie");
            while(rs.next()){
                catego = new CategorieCF();
                catego.setIdcat(rs.getString("idcat"));
                catego.setLibelle(rs.getString("libelle"));
                
                listeCat.add(catego);
            }
            stm.close();
            conn.close();
          }
          catch(Exception l){
              JOptionPane.showMessageDialog(null, l.getMessage());
              return null; 
          }
          
          return listeCat;
      }
      //fonction pour retourner les agences
       public static ArrayList<Agence> getAgence(){
          ArrayList<Agence> listeagence = new ArrayList();
          try{
             
            conn = getConnection();
            stm =conn.createStatement();
            rs = stm.executeQuery("Select * from banquekaneza.agence");
            while(rs.next()){
                agence = new Agence();
                agence.setIdagence("");
                agence.setNom(rs.getString("nom"));
                
                listeagence.add(agence);
            }
            stm.close();
            conn.close();
          }
          catch(Exception l){
              JOptionPane.showMessageDialog(null, l.getMessage());
              return null; 
          }
          
          return listeagence;
      }
      //fonction pour afficher les comptes
      public static ArrayList<CompteCF> getCompte(){
          ArrayList<CompteCF> listecom = new ArrayList();
           try{
            conn = getConnection();
            stm =conn.createStatement();
            rs = stm.executeQuery("Select * from banquekaneza.compte");
            while(rs.next()){
                compte = new CompteCF();
                compte.setNumero(rs.getString("numero"));
                compte.setCodeClient(rs.getString("codeClient"));
                compte.setCategorie(rs.getString("categorie"));
                compte.setDatOuv(rs.getDate("date_ouverture"));
                compte.setSolde(Float.valueOf(rs.getString("solde")));
                compte.setAgence(rs.getString("agence"));
                compte.setEtat(rs.getString("etat"));
                
                listecom.add(compte);
            }
            stm.close();
            conn.close();
          }
          catch(Exception l){
              JOptionPane.showMessageDialog(null, l.getMessage());
              return null; 
          }
          
          return listecom;
      }
      //fonction pour rechercher une catégorie
      public static CategorieCF getCategById(String id){
          try{
            conn = getConnection();
            stm =conn.createStatement();
            rs = stm.executeQuery("select * from banquekaneza.categorie where idcat='"+id+"'");
            while(rs.next()){
                catego = new CategorieCF();
                catego.setIdcat(rs.getString("idcat"));
                catego.setLibelle(rs.getString("libelle"));
                
            }
            conn.close();
            stm.close();
          }
          
          catch(Exception l){}
          
          return catego;
      }
      //fonction pour rechercher un compte
      public static CompteCF getCompteByNum(String n)
      {
          try{
            conn = getConnection();
            stm =conn.createStatement();
            rs = stm.executeQuery("select * from banquekaneza.compte where numero='"+n+"'");
            while(rs.next()){
                compte = new CompteCF();
                compte.setNumero(rs.getString("numero"));
                compte.setCodeClient(rs.getString("codeClient"));
                compte.setCategorie(rs.getString("categorie"));
                compte.setDatOuv(rs.getDate("date_ouverture"));
                compte.setSolde(rs.getFloat("solde"));
                compte.setAgence(rs.getString("agence"));
                compte.setEtat(rs.getString("etat"));
                
            }
            conn.close();
            stm.close();
          }
          
          catch(Exception l){}
          return compte;
      }
      //fonction pour mettre à jour un compte
      public static void updateCompte(Float so, String num){
          try{
              conn = getConnection();
              prtstm = conn.prepareStatement("update banquekaneza.compte set solde='"+so+"' where numero='"+num+"'");
             
              prtstm.executeUpdate();
              conn.close();
              prtstm.close();
           }
           catch(Exception k){}
      }
      //fonction pour desactiver un compte
       public static void updateCompteEtat(String num, String etat){
          try{
              conn = getConnection();
              prtstm = conn.prepareStatement("update banquekaneza.compte set etat='"+etat+"' where numero='"+num+"'");
             
              prtstm.executeUpdate();
              conn.close();
              prtstm.close();
           }
           catch(Exception k){}
      }
      //fonction pour modifier une categorie
       public static void updateCategorie(CategorieCF ct){
           try{
            conn = getConnection();
            prtstm = conn.prepareStatement("update banquekaneza.categorie set libelle='"+ct.getLibelle()+"' where idcat='"+ct.getIdcat()+"'");
              
            prtstm.executeUpdate();
            conn.close();
            prtstm.close();
              
        }
        catch(Exception k){
        } 
      
       }
      //fonction pour supprimer un compte
      public static void deleteCompte(String nu){
          try{
            conn = getConnection();
            prtstm = conn.prepareStatement("delete from banquekaneza.compte where numero='"+nu+"'");
              
            prtstm.executeUpdate();
            conn.close();
            prtstm.close();
              
        }
        catch(Exception k){
        }
      }
      //fonction pour supprimer une categorie
      public static void deleteCat(CategorieCF ca){
         try{
            conn = getConnection();
            prtstm = conn.prepareStatement("delete from banquekaneza.categorie where idcat='"+ca.getIdcat()+"'");
              
            prtstm.executeUpdate();
            conn.close();
            prtstm.close();
              
        }
        catch(Exception k){
        } 
      }
      //fonction pour inserer une opération
      public static void insertOperation(OperationCF op){
          try{
              conn = getConnection();
              prtstm = conn.prepareStatement("insert into banquekaneza.operation(codeop,codeClient,numero,idcat,code,dateop,montant) values(?,?,?,?,?,?,?)");
              prtstm.setString(1, op.getCodeOp());
              prtstm.setString(2, op.getCodeClient());
              prtstm.setString(3, op.getNumero());
              prtstm.setString(4, op.getIdcat());
              prtstm.setString(5, op.getCode());
              prtstm.setString(6, op.getDatOp().toString());
              prtstm.setFloat(7, op.getMontant());
              
              prtstm.executeUpdate();
              
              conn.close();
              prtstm.close();
          }
          catch(Exception e){}
          //recuperation du compte selectionné
              compte = new CompteCF();
              compte = Factory.getCompteByNum(op.getNumero());
              Float solde = compte.getSolde();//recuperation du solde
              Float mont = op.getMontant();//recuperation du montant de l'opération
              if(Integer.valueOf(op.getIdcat()) == 1){
                  updateCompte(op.getMontant(), op.getNumero());
                  updateCompteEtat(op.getNumero(), "Activé");
              }
              else if(Integer.valueOf(op.getIdcat()) == 2){
                  Float nouveau = solde - mont;
                  updateCompte(nouveau, op.getNumero());
              }
              else if(Integer.valueOf(op.getIdcat()) == 3){
                  Float nouveau = solde + mont;
                  updateCompte(nouveau, op.getNumero());
              }
              else if(Integer.valueOf(op.getIdcat()) == 4){
                  updateCompteEtat(op.getNumero(), "Désactivé");
              }
              
      }
      //fonction pour afficher une opération
      public static ArrayList<OperationCF> getOPeration(){
          ArrayList<OperationCF> listeop = new ArrayList();
          try{
            conn = getConnection();
            stm =conn.createStatement();
            rs = stm.executeQuery("Select * from banquekaneza.operation");
            while(rs.next()){
                ope = new OperationCF();
                ope.setCodeOp(rs.getString("codeop"));
                ope.setCodeClient(rs.getString("codeClient"));
                ope.setCode(rs.getString("code"));
                ope.setNumero(rs.getString("numero"));
                ope.setIdcat(rs.getString("idcat"));
                ope.setDateop(rs.getDate("dateop"));
                ope.setMontant(rs.getFloat("montant"));
                
                listeop.add(ope);
            }
            stm.close();
            conn.close();
          }
          catch(Exception l){
              JOptionPane.showMessageDialog(null, l.getMessage());
              return null; 
          }
          return listeop;
      }
      //fonction pour rechercher une opération
      public static OperationCF getOperatByCode(String code)
     {
           try{
            conn = getConnection();
            stm =conn.createStatement();
            rs = stm.executeQuery("select * from banquekaneza.operation where codeop='"+code+"'");
            while(rs.next()){
                ope = new OperationCF();
                ope.setCodeOp(rs.getString("codeop"));
                ope.setCodeClient(rs.getString("codeClient"));
                ope.setCode(rs.getString("code"));
                ope.setNumero(rs.getString("numero"));
                ope.setIdcat(rs.getString("idcat"));
                ope.setDateop(rs.getDate("dateop"));
                ope.setMontant(rs.getFloat("montant"));
            }
            conn.close();
            stm.close();
          }
          
          catch(Exception l){}
          return ope;
      }
      //fonction pour modifier une partie de l'operation
      public static void updateOperation(OperationCF op){
          try{
              conn = getConnection();
              prtstm = conn.prepareStatement("update banquekaneza.operation set codeClient='"+op.getCodeClient()+"',idcat='"+op.getIdcat()+"' where codeop='"+op.getCodeOp()+"'");
             
              prtstm.executeUpdate();
              conn.close();
              prtstm.close();
           }
           catch(Exception k){}
          //recuperation du compte selectionné
              compte = new CompteCF();
              compte = Factory.getCompteByNum(op.getNumero());
              Float solde = compte.getSolde();//recuperation du solde
              Float mont = op.getMontant();//recuperation du montant de l'opération
              if(Integer.valueOf(op.getIdcat()) == 1){
                  updateCompte(op.getMontant(), op.getNumero());
              }
              if(Integer.valueOf(op.getIdcat()) == 2){
                  Float nouveau = solde - mont;
                  updateCompte(nouveau, op.getNumero());
              }
              if(Integer.valueOf(op.getIdcat()) == 3){
                  Float nouveau = solde + mont;
                  updateCompte(nouveau, op.getNumero());
              }
      }
      //fonction pour afficher un compte d'un client
       public static ArrayList<CompteCF> getCompteDuClient(ClientCF ci){
           ArrayList<CompteCF> listecompte = new ArrayList();
           try{
            conn = getConnection();
            stm =conn.createStatement();
            rs = stm.executeQuery("Select * from banquekaneza.compte where codeClient='"+ci.getCodeClient()+"' ");
            while(rs.next()){
                compte = new CompteCF();
                compte.setNumero(rs.getString("numero"));
                compte.setCodeClient(rs.getString("codeClient"));
                compte.setCategorie(rs.getString("categorie"));
                compte.setDatOuv(rs.getDate("date_ouverture"));
                compte.setSolde(Float.valueOf(rs.getString("solde")));
                compte.setAgence(rs.getString("agence"));
                compte.setEtat(rs.getString("etat"));
                
                listecompte.add(compte);
            }
            stm.close();
            conn.close();
          }
          catch(Exception l){
              JOptionPane.showMessageDialog(null, l.getMessage());
              return null; 
          }
          
          return listecompte;
      }
       //fonction pour afficher une operation sur un compte
       
      public static ArrayList<OperationCF> getOPerationSurCompte(CompteCF co){
          ArrayList<OperationCF> listeop= new ArrayList();
          try{
            conn = getConnection();
            stm =conn.createStatement();
            rs = stm.executeQuery("Select * from banquekaneza.operation where numero='"+co.getNumero()+"'");
            while(rs.next()){
                ope = new OperationCF();
                ope.setCodeOp(rs.getString("codeop"));
                ope.setCodeClient(rs.getString("codeClient"));
                ope.setCode(rs.getString("code"));
                ope.setNumero(rs.getString("numero"));
                ope.setIdcat(rs.getString("idcat"));
                ope.setDateop(rs.getDate("dateop"));
                ope.setMontant(rs.getFloat("montant"));
                
                listeop.add(ope);
            }
            stm.close();
            conn.close();
          }
          catch(Exception l){
              JOptionPane.showMessageDialog(null, l.getMessage());
              return null; 
          }
          return listeop;
      }
}
