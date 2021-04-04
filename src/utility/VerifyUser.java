package utility;

import javax.swing.JLabel;
import javax.swing.JTextField;

import classeMetier.Utilisateur;
import dao.implement.UtilisateurDAO;

public class VerifyUser {

	public VerifyUser() {
		// TODO Auto-generated constructor stub
	}
	public static boolean verifierCinAjout(JTextField textField_cin,JLabel err_cin){
		// verifier si chaine vide
		if(textField_cin.getText().length()==0 || textField_cin.getText()==null){
			err_cin.setToolTipText("Donner le cin");
			err_cin.setVisible(true);
			return false;
			
		}else if( !textField_cin.getText().matches("[0-9]*") || ! (textField_cin.getText().length()==8)){
					err_cin.setToolTipText("Cin 8 chiffre !!");
					err_cin.setVisible(true);	
					return false;
		}else{
			// Verifire ci le cin exite deja
			for (Utilisateur utilisateur : UtilisateurDAO.findAll()) {
				int cinUtil=(int)utilisateur.getCin();
				int cinC=(int)Integer.parseInt(textField_cin.getText());
				if(cinC==cinUtil){
					err_cin.setVisible(true);
					err_cin.setToolTipText("Ce Cin existe déja !!");
					return false;
				}
			}
			
			
		}
		err_cin.setVisible(false);
		return true;
	}
	
	// Verifier cin pour la mise à jour
	public static boolean verifierCinApdate(Utilisateur utilisateur,JTextField textField_cin,JLabel err_cin){
		// verifier si chaine vide
		if(textField_cin.getText().length()==0 || textField_cin.getText()==null){
			err_cin.setToolTipText("Donner le cin");
			err_cin.setVisible(true);
			return false;
			
		}else if( !textField_cin.getText().matches("[0-9]*") || ! (textField_cin.getText().length()==8)){
					err_cin.setToolTipText("Cin 8 chiffre !!");
					err_cin.setVisible(true);	
					return false;
		}else{
				// Verifire ci le cin exite deja
				// Si ancien cin est different du nouveau cin
				int cinAncien = (int)utilisateur.getCin();
				int cinNouveau= (int)Integer.parseInt(textField_cin.getText());
				if(cinAncien!=cinNouveau){
					for (Utilisateur utilisateurr : UtilisateurDAO.findAll()) {
						int cinUtil=(int)utilisateurr.getCin();
						cinNouveau=(int)Integer.parseInt(textField_cin.getText());
						if(cinNouveau==cinUtil){
							err_cin.setVisible(true);
							err_cin.setToolTipText("Ce Cin existe déja !!");
							return false;
						}
					}
				}
		}
		err_cin.setVisible(false);
		return true;
	}
	
	public static boolean verifierNom(JTextField textField_nom,JLabel err_nom){
		// chaine vide
		if(textField_nom.getText().length()==0 || textField_nom.getText()==null){
			err_nom.setToolTipText("Donner le nom !!");
			err_nom.setVisible(true);
			return false;
		}
		err_nom.setVisible(false);
		return true;
	}
	public static boolean verifierPrenom(JTextField textField_prenom,JLabel err_prenom){
		// chaine vide
		if(textField_prenom.getText().length()==0 || textField_prenom.getText()==null){
			err_prenom.setToolTipText("Donner le prenom !!");
			err_prenom.setVisible(true);
			return false;
		}
		err_prenom.setVisible(false);
		return true;
	}
	
	
	
	public static boolean verifierLoginAjout(JTextField textField_login, JLabel err_login){
		if(textField_login.getText().length()==0 || textField_login.getText()==null){
			err_login.setVisible(true);
			err_login.setToolTipText("Veillez saisir le login");
			return false;
			
			// Le cas de l'ajout verifier si login appartient à un autre
		}else{
			for (Utilisateur utilisateur : UtilisateurDAO.findAll()) {
				
				if(utilisateur.getLogin().equals(textField_login.getText())){
					err_login.setVisible(true);
					err_login.setToolTipText("Ce login existe déja !!");
					return false;
				}
				
			}
			//La modification verifier si le ancien login differenr
		}
		err_login.setVisible(false);
		return true;
	}
	public static boolean verifierLoginUpdate(Utilisateur utilisateur,JTextField textField_login,JLabel  err_login){
		if(textField_login.getText().length()==0 || textField_login.getText()==null){
			err_login.setVisible(true);
			err_login.setToolTipText("Veillez saisir le login");
			return false;
			
			// Le cas de l'ajout verifier si login appartient à un autre
		}else{
			String logAncien= utilisateur.getLogin();
			String logNouveau= textField_login.getText();
			// Login ancien different de nouveau
			if(!logAncien.equals(logNouveau)){
				// Verifier s'il correspont à un login existant dans la liste
				for (Utilisateur utilisateurr : UtilisateurDAO.findAll()) {
					
					if(utilisateurr.getLogin().equals(textField_login.getText())){
						err_login.setVisible(true);
						err_login.setToolTipText("Ce login existe déja !!");
						return false;
					}
					
				}
			}
		}
		err_login.setVisible(false);
		return true;
	}
	
	public static boolean verifierPass(JTextField textField_pass,JLabel err_pass){
		if(textField_pass.getText().length()==0 || textField_pass.getText()==null){
			
			err_pass.setToolTipText("Veillez saisir le mot de passe !!");
			err_pass.setVisible(true);			
			return false;
		}else{
			// verifier la taille de mot de pass
			if(textField_pass.getText().length()<4){
				
				err_pass.setVisible(true);
				err_pass.setToolTipText("Taille doit etre plus que 4 ! ");
				return false;
			}
			
		}
		
		err_pass.setVisible(false);
		return true;
	}
}
