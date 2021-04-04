package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import classeMetier.Utilisateur;
import controler.ControlerLogin;
import dao.implement.UtilisateurDAO;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JComboBox comboBox_util;
	private JButton btnConnecter;
	
	private ControlerLogin contolerLogin ;
	private JLabel errLogin;
	private JLabel errPass;
	private JLabel errLogPass;
	private UtilisateurDAO utilisateurDAO;
	private JLabel label;
	private JLabel lblCopy;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/image/database.png")));
		setResizable(false);
		setTitle("Authentification");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 552, 450);
		utilisateurDAO = new  UtilisateurDAO();
		contentPane = new JPanel();
		contentPane.setRequestFocusEnabled(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		passwordField = new JPasswordField("**********");
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setForeground(new Color(128, 128, 128));
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(passwordField.getText().equals("")){
					passwordField.setText("**********");
					passwordField.setForeground(new Color(128, 128, 128));
				}
					
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				
					passwordField.setForeground(Color.BLACK);
					passwordField.setText("");
				}
				
			
		});
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setBounds(180, 245, 180, 28);
		contentPane.add(passwordField);
		
		comboBox_util = new JComboBox();
		comboBox_util.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		remplir_Combo_affectation();
		
		comboBox_util.setBounds(180, 156, 180, 28);
		
		contentPane.add(comboBox_util);
		
		JLabel lblLogin = new JLabel("Login",SwingConstants.CENTER);
		lblLogin.setForeground(Color.BLUE);
		lblLogin.setFont(new Font("Sitka Text", Font.BOLD, 14));
		lblLogin.setBounds(180, 114, 180, 28);
		contentPane.add(lblLogin);
		
		JLabel lblMotDePasse = new JLabel("Mot de Passe",SwingConstants.CENTER);
		lblMotDePasse.setForeground(Color.BLUE);
		lblMotDePasse.setFont(new Font("Sitka Text", Font.BOLD, 14));
		lblMotDePasse.setBounds(180, 206, 180, 28);
		contentPane.add(lblMotDePasse);
		
		btnConnecter = new JButton("Connecter");
		btnConnecter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
				btnConnecter.setForeground(SystemColor.window);
		    	//buton.setBackground(SystemColor.textInactiveText);
				btnConnecter.setBackground(new Color(51, 130, 255));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnConnecter.setForeground(null);
				btnConnecter.setBackground(null);		
			}
		});
		btnConnecter.setSelected(true);
		btnConnecter.setFont(new Font("Sitka Text", Font.BOLD, 13));
		btnConnecter.setBounds(180, 312, 180, 32);
		contentPane.add(btnConnecter);
		
		errLogin = new JLabel("");
		errLogin.setVisible(false);
		errLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
		errLogin.setIcon(new ImageIcon(Login.class.getResource("/image/error.png")));
		errLogin.setBounds(370, 156, 46, 28);
		contentPane.add(errLogin);
		
		errPass = new JLabel("");
		errPass.setVisible(false);
		errPass.setAlignmentX(Component.CENTER_ALIGNMENT);
		errPass.setIcon(new ImageIcon(Login.class.getResource("/image/error.png")));
		errPass.setBounds(370, 245, 46, 28);
		contentPane.add(errPass);
		
		errLogPass = new JLabel("<html><color='red'><center>V\u00E9rifier le login et le mot de Passe !!!</center></color></html>",SwingConstants.CENTER);
		errLogPass.setVisible(false);
		errLogPass.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		errLogPass.setAlignmentX(Component.CENTER_ALIGNMENT);
		errLogPass.setBounds(135, 351, 283, 32);
		contentPane.add(errLogPass);
		
		JLabel lblDvellopePar = new JLabel("D\u00E9vellop\u00E9e par : El B\u00E9chir");
		lblDvellopePar.setForeground(Color.BLACK);
		lblDvellopePar.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 11));
		lblDvellopePar.setBounds(10, 378, 144, 32);
		contentPane.add(lblDvellopePar);
		
		JLabel lblGestionDeStoks = new JLabel("Gestion de Stoks",SwingConstants.CENTER);
		lblGestionDeStoks.setFont(new Font("Informal Roman", Font.BOLD, 51));
		lblGestionDeStoks.setBounds(10, 0, 526, 66);
		contentPane.add(lblGestionDeStoks);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(Login.class.getResource("/image/login-icon.png")));
		label.setBounds(10, 52, 150, 127);
		contentPane.add(label);
		
		JLabel lblAthentification = new JLabel("Athentification",SwingConstants.CENTER);
		lblAthentification.setForeground(Color.BLUE);
		lblAthentification.setFont(new Font("Lucida Calligraphy", Font.BOLD, 23));
		lblAthentification.setBounds(10, 66, 526, 32);
		contentPane.add(lblAthentification);
		
		lblCopy = new JLabel("(c) Copyright 2017-2018 FSB depInfo",SwingConstants.CENTER);
		lblCopy.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		lblCopy.setBounds(10, 396, 526, 14);
		contentPane.add(lblCopy);
		
		// Ajouter le controler
		contolerLogin= new ControlerLogin(this);
		btnConnecter.addActionListener(contolerLogin);
		setLocationRelativeTo(null);
	}
	public void remplir_Combo_affectation(){
		try {
			Vector<Utilisateur> v = new Vector<Utilisateur>();
			v=UtilisateurDAO.findAll();
			comboBox_util.removeAllItems();
			for (Utilisateur classe : v) {
				comboBox_util.addItem(classe.getLogin());
			}
			comboBox_util.setSelectedItem(null);
			comboBox_util.setSelectedIndex(0);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Désoler aucun utilisateur dans la base !");
		}
		
		
	}
	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public JComboBox getComboBox_util() {
		return comboBox_util;
	}

	public JButton getBtnConnecter() {
		return btnConnecter;
	}

	public JLabel getErrLogin() {
		return errLogin;
	}

	public JLabel getErrPass() {
		return errPass;
	}

	public JLabel getErrLogPass() {
		return errLogPass;
	}
}
