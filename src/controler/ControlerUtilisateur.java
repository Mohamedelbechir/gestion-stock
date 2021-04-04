package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import classeMetier.Utilisateur;
import connxion_Requete.Connexion;
import dao.DAO;
import dao.implement.UtilisateurDAO;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import utility.Imprimer;
import utility.ModelTableau;
import view.GestionUtilisateur;
import view.MainApp;

public class ControlerUtilisateur implements ActionListener, MouseListener{
	
	private UtilisateurDAO utilisateurDAO;
	private Vector<Utilisateur> listeUtilisateur;
	private JTable table;
	private ModelTableau model_table;
	
	public  JDateChooser dateChooser_age;
	private GestionUtilisateur gestionUtilisateur;
	
	private JButton btnAjouter ;
	private JButton btnModifier;
	
	private JTextField textField_nom;
	private JTextField textField_prenom;
	private JTextField textField_cin;
	private JTextField textField_login;
	private JTextField textField_pass;
	
	private JRadioButton rdbtnM;
	private JRadioButton rdbtnF;
	private JRadioButton rdbtnAdmin;
	private JRadioButton rdbtnUtilisateur;
	
	public ControlerUtilisateur(GestionUtilisateur gestionUtilisateur ) {
		
		this.gestionUtilisateur=gestionUtilisateur;
		btnAjouter=gestionUtilisateur.getBtnAjouter();
		btnModifier=gestionUtilisateur.getBtnModifier();
		dateChooser_age= gestionUtilisateur.getDateChooser_age();
		
		// les champs de texte et date
		textField_nom=gestionUtilisateur.getTextField_nom();
		textField_prenom=gestionUtilisateur.getTextField_prenom();
		textField_cin=gestionUtilisateur.getTextField_cin();
		textField_login=gestionUtilisateur.getTextField_login();
		textField_pass=gestionUtilisateur.getTextField_pass();
		dateChooser_age=gestionUtilisateur.getDateChooser_age();
		
		model_table=gestionUtilisateur.getModel_table();
		table=gestionUtilisateur.getTable();
		
		// Radio button
		rdbtnM=gestionUtilisateur.getRdbtnM();
		rdbtnF=gestionUtilisateur.getRdbtnF();
		rdbtnAdmin=gestionUtilisateur.getRdbtnAdmin();
		rdbtnUtilisateur=gestionUtilisateur.getRdbtnUtilisateur();
		
		utilisateurDAO= new UtilisateurDAO();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		String nameButton = ((JButton)arg0.getSource()).getText();
		
		// Ajout d'un utilisateur
		if(nameButton.equals(btnAjouter.getText())){
			if(verifierAjout()){
				Utilisateur utilisateur= gestionUtilisateur.creer_Objet_Utilisateur_Saisi_Champ_PourAjout();
				ajouterUtilisateur(utilisateur);
			}
				
				
		// La modification d'un utilisateur
		}else if(nameButton.equals(btnModifier.getText())){
			if(verifierUpdate()){
				Utilisateur utilisateur1;
				Utilisateur utilisateur= gestionUtilisateur.creer_Objet_Utilisateur_Saisi_Champ_PourAjout();
				utilisateur.setIdUtil((int)table.getValueAt(table.getSelectedRow(), 0));
				modifierUtilisateur(utilisateur);
				// verifier si l'utilisateur modifier est l'utilisateur courant
				if((int)utilisateur.getIdUtil()==MainApp.getUtilisateur().getIdUtil())	
				
					utilisateur1 =MainApp.getUtilisateur();
			}
					
		}else if(nameButton.equals(gestionUtilisateur.getBtnNettoyer().getText())){
				gestionUtilisateur.nettoyer();;
				
		}else if(nameButton.equals(gestionUtilisateur.getBtnSupprimer().getText())){
			if(gestionUtilisateur.obtenir_Utilisateur_Selection()!=null){
				supprimerUti(gestionUtilisateur.obtenir_Utilisateur_Selection());
			}else{
				JOptionPane.showMessageDialog(null, "Selectionner un utilisateur !");
			}
			
			
		}else if(nameButton.equals(gestionUtilisateur.getBtnImprimer().getText())){
			Imprimer.print("Rapport\\Liste_Des_Utilisateurs.jrxml");
		}
	}
	
	// Créer un object pour l'ajouter dans le taleau
	public Object[] newRow(Utilisateur utilisateur){
		
		Object[] newRow= new Object[]{
				utilisateur.getIdUtil(),
				utilisateur.getLogin(),
				utilisateur.getPass(),
				utilisateur.getCin(),
				utilisateur.getNom(),
				utilisateur.getPrenom(),
				utilisateur.getDate_nais(),
				utilisateur.getStatue(),
				utilisateur.getSexe()
		};
		return newRow;
	}
	public void ajouterUtilisateur(Utilisateur utilisateur){
		
		// Verifiecation
		if(utilisateurDAO.create(utilisateur)){
			JOptionPane.showMessageDialog(null, "Succès !");
			// Ajouter une nouvelle ligne au tableau sans le charger dans la base			
			//model_table.addRow(newRow(utilisateur));
			gestionUtilisateur.charger();
		}
						
	}
	public void modifierUtilisateur(Utilisateur utilisateur){
		
		// Mise à jour 
		if(utilisateurDAO.update(utilisateur)){
			JOptionPane.showMessageDialog(null, "Succès !!");
			int row = table.getSelectedRow();
			//model_table.updateRow(newRow(utilisateur),row);
			gestionUtilisateur.charger();
				
		}
		
	}
	public void supprimerUti(Utilisateur utilisateur){
		
		if(JOptionPane.showConfirmDialog(null,"Voulez-vous supprimer cet utilisateur ?")==JOptionPane.OK_OPTION){
			if(gestionUtilisateur.getUtilisateurDAO().delete(utilisateur)){
				model_table.removeRow(table.getSelectedRow());
				JOptionPane.showMessageDialog(null, "Succès !!");
			}
		}
			
		
	}
	public Vector<Utilisateur> getListeUtilisateur(){
		if(listeUtilisateur!=null){
			return listeUtilisateur;
		}else{
			return UtilisateurDAO.findAll();
		}
	}
	public boolean verifierAjout(){
		
		return gestionUtilisateur.verifierLoginAjout() & gestionUtilisateur.verifierCinAjout() &
				gestionUtilisateur.verifierPass() & gestionUtilisateur.verifierNom() &
				gestionUtilisateur.verifierPrenom();
	}
	public boolean verifierUpdate(){
		return gestionUtilisateur.verifierLoginApdate() & gestionUtilisateur.verifierCinUpdate() &
				gestionUtilisateur.verifierPass() & gestionUtilisateur.verifierNom() &
				gestionUtilisateur.verifierPrenom();
	}
	
	public Vector<Utilisateur> findCollection(String terme,String facteur){
		return  utilisateurDAO.findAll(facteur,terme);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		Object inst=arg0.getSource();
		if (inst instanceof JTable) {
			Utilisateur utilisateur= gestionUtilisateur.obtenir_Utilisateur_Selection();
			if(utilisateur!=null)
				gestionUtilisateur.remplir_Champ_Utilisateur(utilisateur);
			
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
