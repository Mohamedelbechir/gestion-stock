package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

import classeMetier.Utilisateur;
import controler.ControlerGestionMonProfil;
import utility.VerifyUser;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;

public class GestionMonProfil extends JInternalFrame {
	private JTextField textField_cin;
	private JTextField textField_nom;
	private JTextField textField_prenom;
	private JTextField textField_login;
	private JTextField textField_pass;

	private JDateChooser dateChooser_age;
	private JRadioButton rdbtnAdmin;
	private JRadioButton rdbtnUtilisateur;
	private JRadioButton rdbtnM;
	private JRadioButton rdbtnF;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private ControlerGestionMonProfil controlerGestionMonProfil;
	private JLabel err_login;
	private JLabel err_pass;
	private JLabel err_nom;
	private JLabel err_prenom;
	private JLabel err_cin;
	private JButton btnEnregister;
	private JButton btnImprimer;
	/**
	 * Launch the application.
	
	/**
	 * Create the frame.
	 */
	public GestionMonProfil() {
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBounds(241, 142, 1111, 590);
		getContentPane().setLayout(null);
		
		JLabel lblGestionDesUtilisateurs = new JLabel("Gestion de mon  Profil");
		lblGestionDesUtilisateurs.setFont(new Font("Sitka Text", Font.BOLD, 13));
		lblGestionDesUtilisateurs.setBounds(10, 11, 224, 30);
		getContentPane().add(lblGestionDesUtilisateurs);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 40, 1091, 13);
		getContentPane().add(separator);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Operation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 465, 353, 86);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		btnImprimer = new JButton("Imprimer");
		btnImprimer.setIcon(new ImageIcon(GestionMonProfil.class.getResource("/image/print-icon.png")));
		btnImprimer.setBounds(191, 24, 140, 47);
		panel_1.add(btnImprimer);
		
		btnEnregister = new JButton("Valider");
		btnEnregister.setIcon(new ImageIcon(GestionMonProfil.class.getResource("/image/Ok-icon.png")));
		btnEnregister.setBounds(41, 24, 140, 47);
		panel_1.add(btnEnregister);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Mes Informations", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 60, 672, 382);
		getContentPane().add(panel);
		
		dateChooser_age = new JDateChooser();
		dateChooser_age.setDateFormatString("dd/MM/yyyy");
		dateChooser_age.setBounds(138, 237, 147, 28);
		((JTextField)dateChooser_age.getDateEditor().getUiComponent()).setEditable(false);
		panel.add(dateChooser_age);
		
		JLabel label = new JLabel("Date de Naissance");
		label.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		label.setBounds(26, 237, 108, 26);
		panel.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(279, 198, 16, 26);
		panel.add(label_1);
		
		textField_cin = new JTextField();
		textField_cin.setColumns(10);
		textField_cin.setBounds(136, 204, 147, 26);
		panel.add(textField_cin);
		
		textField_nom = new JTextField();
		textField_nom.setColumns(10);
		textField_nom.setBounds(136, 95, 179, 26);
		panel.add(textField_nom);
		
		textField_prenom = new JTextField();
		textField_prenom.setColumns(10);
		textField_prenom.setBounds(136, 130, 179, 28);
		panel.add(textField_prenom);
		
		JLabel label_3 = new JLabel("");
		label_3.setBounds(299, 99, 29, 26);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("<html>Cin: <font color= 'red'>*</font></html>");
		label_4.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		label_4.setBounds(26, 204, 54, 26);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("Pr\u00E9nom");
		label_5.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		label_5.setBounds(26, 130, 54, 28);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("Nom");
		label_6.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		label_6.setBounds(26, 95, 54, 26);
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("");
		label_7.setBounds(277, 245, 16, 16);
		panel.add(label_7);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "Identit\u00E9", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(422, 32, 179, 192);
		panel.add(panel_2);
		
		JLabel label_8 = new JLabel("");
		label_8.setIcon(new ImageIcon(GestionMonProfil.class.getResource("/image/file_image.png")));
		label_8.setBounds(10, 25, 158, 158);
		panel_2.add(label_8);
		
		JButton button = new JButton("parcourir");
		button.setBounds(422, 242, 121, 47);
		panel.add(button);
		
		JLabel label_9 = new JLabel("<html>Login: <font color= 'red'>*</font></html>");
		label_9.setBounds(26, 25, 101, 28);
		panel.add(label_9);
		
		JLabel label_10 = new JLabel("<html>Mot de Passe: <font color= 'red'>*</font></html>");
		label_10.setBounds(26, 59, 101, 28);
		panel.add(label_10);
		
		JLabel label_11 = new JLabel("Stattue");
		label_11.setBounds(26, 270, 46, 28);
		panel.add(label_11);
		
		rdbtnAdmin = new JRadioButton("Admin");
		rdbtnAdmin.setEnabled(false);
		buttonGroup.add(rdbtnAdmin);
		rdbtnAdmin.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		rdbtnAdmin.setBounds(132, 271, 67, 28);
		panel.add(rdbtnAdmin);
		
		rdbtnUtilisateur = new JRadioButton("Utilisateur");
		rdbtnUtilisateur.setEnabled(false);
		buttonGroup.add(rdbtnUtilisateur);
		rdbtnUtilisateur.setSelected(true);
		rdbtnUtilisateur.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		rdbtnUtilisateur.setBounds(196, 272, 91, 28);
		panel.add(rdbtnUtilisateur);
		
		textField_login = new JTextField();
		textField_login.setColumns(10);
		textField_login.setBounds(137, 25, 179, 28);
		panel.add(textField_login);
		
		textField_pass = new JTextField();
		textField_pass.setColumns(10);
		textField_pass.setBounds(137, 59, 179, 28);
		panel.add(textField_pass);
		
		JLabel label_12 = new JLabel("Sexe");
		label_12.setBounds(26, 169, 46, 28);
		panel.add(label_12);
		
		rdbtnF = new JRadioButton("F");
		buttonGroup_1.add(rdbtnF);
		rdbtnF.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		rdbtnF.setBounds(196, 174, 38, 23);
		panel.add(rdbtnF);
		
		rdbtnM = new JRadioButton("M");
		buttonGroup_1.add(rdbtnM);
		rdbtnM.setSelected(true);
		rdbtnM.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		rdbtnM.setBounds(132, 174, 46, 23);
		panel.add(rdbtnM);
		
		err_login = new JLabel("");
		err_login.setVisible(false);
		err_login.setIcon(new ImageIcon(GestionMonProfil.class.getResource("/image/error.png")));
		err_login.setBounds(326, 25, 46, 28);
		panel.add(err_login);
		
		err_pass = new JLabel("");
		err_pass.setVisible(false);
		err_pass.setIcon(new ImageIcon(GestionMonProfil.class.getResource("/image/error.png")));
		err_pass.setBounds(326, 59, 46, 29);
		panel.add(err_pass);
		
		err_nom = new JLabel("");
		err_nom.setVisible(false);
		err_nom.setIcon(new ImageIcon(GestionMonProfil.class.getResource("/image/error.png")));
		err_nom.setBounds(325, 95, 46, 26);
		panel.add(err_nom);
		
		err_prenom = new JLabel("");
		err_prenom.setVisible(false);
		err_prenom.setIcon(new ImageIcon(GestionMonProfil.class.getResource("/image/error.png")));
		err_prenom.setBounds(325, 130, 46, 28);
		panel.add(err_prenom);
		
		err_cin = new JLabel("");
		err_cin.setVisible(false);
		err_cin.setIcon(new ImageIcon(GestionMonProfil.class.getResource("/image/error.png")));
		err_cin.setBounds(295, 198, 46, 32);
		panel.add(err_cin);
		
		controlerGestionMonProfil= new ControlerGestionMonProfil(this);
		btnEnregister.addActionListener(controlerGestionMonProfil);
		remplir_Champ_Utilisateur();
		remove_title_bar();
	}
	void remove_title_bar() {
        putClientProperty("Gestion.isPalette", Boolean.TRUE);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setBorder(new LineBorder(new Color(0, 0, 0)));

    }
	/*------------ La permettant de créer un objet Utilisateur à partir des champs text--------------*/
	public Utilisateur creer_Objet_Utilisateur_Saisi_Champ_PourAjout(){
		
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setLogin(textField_login.getText());
		utilisateur.setPass(textField_pass.getText());
		utilisateur.setCin(Integer.parseInt(textField_cin.getText()));
		utilisateur.setNom(textField_nom.getText());
		utilisateur.setPrenom(textField_prenom.getText());
		utilisateur.setDate_nais(((JTextField)dateChooser_age.getDateEditor().getUiComponent()).getText());
		if(rdbtnM.isSelected())
			utilisateur.setSexe("masculin");
		
		else if(rdbtnF.isSelected())
			utilisateur.setSexe("féminin");
		
		/*------------------------------*/
		if(rdbtnAdmin.isSelected())
			utilisateur.setStatue("admin");
		
		else if(rdbtnUtilisateur.isSelected())
			utilisateur.setStatue("utilisateur");
		
		return utilisateur;
	}
	/*------------------ la methode pour remplir les champs Salle------------------------*/
	public void remplir_Champ_Utilisateur(){
		
		Utilisateur utilisateur =controlerGestionMonProfil.getUtilisateur(MainApp.getUtilisateur().getIdUtil());
		textField_login.setText(utilisateur.getLogin());
		textField_pass.setText(utilisateur.getPass());
		textField_cin.setText(utilisateur.getCin().toString());
		textField_nom.setText(utilisateur.getNom());
		textField_prenom.setText(utilisateur.getPrenom());
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
		Date date_Naissance=null;
				
		try {
			date_Naissance = dateFormat.parse(utilisateur.getDate_nais());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		dateChooser_age.setDate(date_Naissance);
		try {
			if (utilisateur.getStatue().equals("admin")){
				rdbtnAdmin.setSelected(true);
				
			} else {
				rdbtnUtilisateur.setSelected(true);
			}
			if (utilisateur.getSexe().equals("masculin")) {
				rdbtnM.setSelected(true);
				
			}else{
				rdbtnF.setSelected(true);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

	public boolean verifierCinUpdate(){
		Utilisateur utilisateur =controlerGestionMonProfil.getUtilisateur(MainApp.getUtilisateur().getIdUtil());
		return VerifyUser.verifierCinApdate(utilisateur,textField_cin, err_cin);
		
	}
	public boolean verifierNom(){
		return VerifyUser.verifierNom(textField_nom, err_nom);
	}
	public boolean verifierPrenom(){
		return VerifyUser.verifierPrenom(textField_prenom, err_prenom);
	}
	public boolean verifierLoginApdate(){
		Utilisateur utilisateur =controlerGestionMonProfil.getUtilisateur(MainApp.getUtilisateur().getIdUtil());
		return VerifyUser.verifierLoginUpdate(utilisateur, textField_login, err_login);
	}
	public boolean verifierPass(){
		return VerifyUser.verifierPass(textField_pass, err_pass);
	}
	
	public JTextField getTextField_cin() {
		return textField_cin;
	}
	public JTextField getTextField_nom() {
		return textField_nom;
	}
	public JTextField getTextField_prenom() {
		return textField_prenom;
	}
	public JTextField getTextField_login() {
		return textField_login;
	}
	public JTextField getTextField_pass() {
		return textField_pass;
	}
	
	public JDateChooser getDateChooser() {
		return dateChooser_age;
	}
	public JRadioButton getRadioButton_admin() {
		return rdbtnAdmin;
	}
	public JRadioButton getRadioButton_utilisateur() {
		return rdbtnUtilisateur;
	}
	public JRadioButton getRadioButton_m() {
		return rdbtnM;
	}
	public JRadioButton getRadioButton_f() {
		return rdbtnF;
	}
	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}
	public ButtonGroup getButtonGroup_1() {
		return buttonGroup_1;
	}
	public ControlerGestionMonProfil getControlerGestionMonProfil() {
		return controlerGestionMonProfil;
	}
	public JLabel getErr_login() {
		return err_login;
	}
	public JLabel getErr_pass() {
		return err_pass;
	}
	public JLabel getErr_nom() {
		return err_nom;
	}
	public JLabel getErr_prenom() {
		return err_prenom;
	}
	public JLabel getErr_cin() {
		return err_cin;
	}
	public JButton getBtnEnregister() {
		return btnEnregister;
	}
	public JButton getBtnImprimer() {
		return btnImprimer;
	}
	
}
