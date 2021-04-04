package controler;

import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import classeMetier.Utilisateur;
import dao.implement.UtilisateurDAO;
import view.Login;
import view.MainApp;

public class ControlerLogin implements ActionListener {
	private static final GraphicsConfiguration Utilisateur = null;
	private UtilisateurDAO  utilisateurDAO;
	private Utilisateur utilisateur;
	
	private JPasswordField passwordField;
	private JComboBox comboBox_util;
	private JButton btnConnecter;
	private JLabel errLogin;
	private JLabel errPass;
	private JLabel errLogPass;
	private Login login;
	
	public ControlerLogin(Login login) {
		this.login=login;
		this.errLogin=login.getErrLogin();
		this.errPass=login.getErrPass();
		this.errLogPass=login.getErrLogPass();
		comboBox_util=login.getComboBox_util();
		passwordField=login.getPasswordField();
		btnConnecter=login.getBtnConnecter();
		
		
		utilisateurDAO= new UtilisateurDAO();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Verifier champsS
		if(verifier_util()){
			// Verifier utilisateur
			login.dispose();
			MainApp main =  new MainApp(utilisateur);
			main.setVisible(true);
		}
		
	}
	public boolean verifier_Champ(){
		boolean bol= true;
		errLogin.setVisible(false);
		errPass.setVisible(false);
		// Verifier si les champs sont vide
		if(comboBox_util.getSelectedItem()==null || comboBox_util.getEditor().getItem()==null){
			errLogin.setVisible(true);
			errLogin.setToolTipText("SVP donner le login");
			bol=false;
		}else{
			// Verifier si le login est bon
			Utilisateur l= utilisateurDAO.findByLogin(new String(comboBox_util.getEditor().getItem().toString()));
			if(l.getLogin()==null){
				errLogin.setVisible(true);
				errLogin.setToolTipText("Login incorrect !");
				bol= false;
			}
		}
		if(passwordField.getText()==null){
			errPass.setToolTipText("SVP donner le mot de passe");
			errPass.setVisible(true);
			bol=false;
		}
		
		return bol;
	}
	
	public boolean verifier_util(){
		boolean bol = true;
		utilisateur=utilisateurDAO.find(comboBox_util.getSelectedItem().toString(),passwordField.getText());
		if(utilisateur== null){
			errLogPass.setVisible(true);
			bol=false;			
		}
		return bol;
	}
	

}
