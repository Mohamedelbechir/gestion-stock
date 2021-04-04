package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import classeMetier.Utilisateur;
import dao.implement.UtilisateurDAO;
import view.GestionMonProfil;
import view.MainApp;

public class ControlerGestionMonProfil implements ActionListener {
	private GestionMonProfil gestionMonProfil;
	private UtilisateurDAO utilisateurDAO;

	public ControlerGestionMonProfil(GestionMonProfil gestionMonProfil) {
		this.gestionMonProfil=gestionMonProfil;
		utilisateurDAO=new UtilisateurDAO();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String nameButton = ((JButton)arg0.getSource()).getText();
		if(nameButton.equals(gestionMonProfil.getBtnEnregister().getText())){
			if(verifierAjout()){
				Utilisateur utilisateur= gestionMonProfil.creer_Objet_Utilisateur_Saisi_Champ_PourAjout();
				utilisateur.setIdUtil(MainApp.getUtilisateur().getIdUtil());
				if(utilisateurDAO.update(utilisateur)){
					JOptionPane.showMessageDialog(null, "Succès !");
				}
			}
				
				
		// La modification d'un utilisateur
		}
		
	}
	public boolean verifierAjout(){
		return gestionMonProfil.verifierCinUpdate()& gestionMonProfil.verifierNom()
				& gestionMonProfil.verifierPrenom() &gestionMonProfil.verifierLoginApdate()
				&gestionMonProfil.verifierPass();
	}
	
	public Utilisateur getUtilisateur(Integer idUtil) {
		return utilisateurDAO.find(idUtil);
		
	}

}
