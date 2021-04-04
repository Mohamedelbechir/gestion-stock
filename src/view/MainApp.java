package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import classeMetier.Magasin;
import classeMetier.Utilisateur;
import controler.ControlerMain;
import utility.*;

public class MainApp extends JFrame {
	
	
	private JPanel contentPane;
	private JButton buttonHome;
	private JButton btnUtilisateur;
	private JButton btnParametre;
	private JButton btnMonProfil;
	private JPanel panel_Menu;
	//private JPanel panel_Bande;
	public static JDesktopPane desk;
	
	private ControlerMain controlerMain;
	private Magasin magasin;
	private InfoMag infoMag;
	private GestionUtilisateur gUtilisateur;
	
	private static Utilisateur utilisateur;
	private JButton btnDeconnecter;
	private ArrayList<JButton> listButtom;
	private JButton btnArticleCategoris;
	private JButton btnEntrssorties;
	private JButton btnFournisseurs;
	private JButton btnApropos;
	private JLabel lblNomDuMagasin;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;
	private JLabel label_9;
	private JLabel label_10;
	private JLabel label_11;
	private JLabel label_12;
	private JLabel label_13;
	private JLabel label_14;
	private JLabel label_15;
	private JLabel label_16;
	private JLabel label_17;
	private JLabel label_18;
	private JLabel label_19;
	private InfoMag bande;
	private JInternalFrame logo;

	/**
	 * Launch the application.
	 * @param utilisateur 
	 * 
	 * @throws InstantiationException 
	 */ 
	public static void main(String[] args)  {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApp frame = new MainApp(null);
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
	public MainApp(Utilisateur utilisateur) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainApp.class.getResource("/image/database.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setBounds(100, 100, 1500, 726);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		desk = new JDesktopPane();
		desk.setBorder(new EmptyBorder(0, 0, 0, 0));
		desk.setBounds(241, 142, 1111, 562);
		
		contentPane.add(desk);
		desk.setLayout(new BorderLayout(0, 0));
		logo= new JInternalFrame(){
					{
						putClientProperty("Gestion.isPalette", Boolean.TRUE);
				        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
				        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
				        this.setBorder(new LineBorder(new Color(0, 0, 0)));
					}	
		};
		
		logo.setBorder(new EmptyBorder(0, 0, 0, 0));
		logo.setBounds(241, 142, 1111, 590);
		logo.getContentPane().setLayout(new BorderLayout());
		JLabel logoMag =  new JLabel("");
		logoMag.setBorder(new LineBorder(new Color(0, 0, 0)));
		logoMag.setHorizontalTextPosition(SwingConstants.CENTER);
		logoMag.setHorizontalAlignment(SwingConstants.CENTER);
		logoMag.setIcon(new ImageIcon(MainApp.class.getResource("/image/Inventory-Management.png")));
		logoMag.setBounds(0, 0, 1111, 590);
		logo.getContentPane().add(logoMag);
		desk.add(logo);
		logo.show();
		
		try {
			logo.setMaximum(true);
		} catch (Exception e) {
			
		}
		//logo.add(new JPanel().add(new ImageIcon(MainApp.class.getResource())));
		//logo.(new ImageIcon(MainApp.class.getResource("/image/Inventory-Management.png")));
		
		
		this.utilisateur=utilisateur;
		listButtom= new ArrayList<>();
		
		
		//panel_Bande = new InfoMag();
		
		//bande.setLocation(241, 5);
		bande= new InfoMag();
		contentPane.add(bande);
		//panel_Bande.setBorder(new LineBorder(new Color(0, 0, 0)));
		//panel_Bande.setBounds(241, 5, 1111, 126);
		//contentPane.add(panel_Bande);
		//panel_Bande.setLayout(null);
		
		lblNomDuMagasin = new JLabel("Nom du Magasin: ");
		lblNomDuMagasin.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNomDuMagasin.setHorizontalAlignment(SwingConstants.LEFT);
		lblNomDuMagasin.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 15));
		lblNomDuMagasin.setBounds(10, 11, 138, 27);
		///panel_Bande.add(lblNomDuMagasin);
		
		label_1 = new JLabel("__________");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 15));
		label_1.setBounds(147, 11, 162, 27);
		//panel_Bande.add(label_1);
		
		label_2 = new JLabel("11:59:30");
		label_2.setHorizontalTextPosition(SwingConstants.CENTER);
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setForeground(Color.DARK_GRAY);
		label_2.setFont(null);
		label_2.setBorder(null);
		label_2.setBackground(Color.WHITE);
		label_2.setBounds(493, 34, 128, 53);
		//panel_Bande.add(label_2);
		
		label_3 = new JLabel("01/01/2017");
		label_3.setHorizontalTextPosition(SwingConstants.CENTER);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(Color.DARK_GRAY);
		label_3.setFont(null);
		label_3.setBounds(267, 34, 243, 53);
		//panel_Bande.add(label_3);
		
		label_4 = new JLabel("Fax :");
		label_4.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 15));
		label_4.setBounds(793, 11, 57, 27);
		//panel_Bande.add(label_4);
		
		label_5 = new JLabel("Cell :");
		label_5.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 15));
		label_5.setBounds(793, 34, 46, 26);
		//panel_Bande.add(label_5);
		
		label_6 = new JLabel("E-mail");
		label_6.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 15));
		label_6.setBounds(793, 86, 74, 27);
		//panel_Bande.add(label_6);
		
		label_7 = new JLabel("____________");
		label_7.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label_7.setBounds(901, 11, 200, 27);
		//panel_Bande.add(label_7);
		
		label_8 = new JLabel("__________________");
		label_8.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label_8.setBounds(901, 34, 200, 26);
		//panel_Bande.add(label_8);
		
		label_9 = new JLabel("______________");
		label_9.setForeground(Color.BLUE);
		label_9.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		label_9.setBounds(901, 86, 200, 27);
		//panel_Bande.add(label_9);
		
		label_10 = new JLabel("Adresse : ");
		label_10.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 15));
		label_10.setBounds(10, 60, 106, 27);
		//panel_Bande.add(label_10);
		
		label_11 = new JLabel("Ville : ");
		label_11.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 15));
		label_11.setBounds(10, 86, 106, 27);
		//panel_Bande.add(label_11);
		
		label_12 = new JLabel("Propri\u00E9taire");
		label_12.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 15));
		label_12.setBounds(10, 34, 106, 27);
		//panel_Bande.add(label_12);
		
		label_13 = new JLabel("________________");
		label_13.setFont(new Font("Dialog", Font.ITALIC, 14));
		label_13.setBounds(146, 34, 110, 27);
		//panel_Bande.add(label_13);
		
		label_14 = new JLabel("BP :");
		label_14.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 14));
		label_14.setBounds(793, 60, 46, 27);
		//panel_Bande.add(label_14);
		
		label_15 = new JLabel("___________________");
		label_15.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label_15.setBounds(901, 60, 200, 27);
		//panel_Bande.add(label_15);
		
		label_16 = new JLabel("_________________");
		label_16.setFont(new Font("Dialog", Font.ITALIC, 14));
		label_16.setBounds(147, 86, 110, 27);
		//panel_Bande.add(label_16);
		
		label_17 = new JLabel("");
		label_17.setFont(new Font("Dialog", Font.ITALIC, 14));
		label_17.setBounds(147, 60, 108, 27);
		//panel_Bande.add(label_17);
		
		label_18 = new JLabel("30");
		label_18.setHorizontalTextPosition(SwingConstants.LEFT);
		label_18.setForeground(Color.DARK_GRAY);
		label_18.setFont(null);
		label_18.setBounds(618, 34, 35, 26);
		//panel_Bande.add(label_18);
		
		label_19 = new JLabel("PM");
		label_19.setForeground(Color.DARK_GRAY);
		label_19.setFont(new Font("Dialog", Font.PLAIN, 9));
		label_19.setBounds(631, 58, 35, 14);
		//panel_Bande.add(label_19);
		
		panel_Menu = new JPanel();
		panel_Menu.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_Menu.setBounds(10, 5, 221, 699);
		contentPane.add(panel_Menu);
		panel_Menu.setLayout(null);
		
		btnUtilisateur = new JButton("Utilisateurs");
		btnUtilisateur.setIcon(new ImageIcon(MainApp.class.getResource("/image/icons8_User_Groups_20px.png")));
		btnUtilisateur.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		btnUtilisateur.setBounds(10, 385, 201, 50);
		listButtom.add(btnUtilisateur);
		panel_Menu.add(btnUtilisateur);
		
		btnParametre = new JButton("Parametrage Magasin");
		btnParametre.setIcon(new ImageIcon(MainApp.class.getResource("/image/icons8_Settings_20px_2.png")));
		btnParametre.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		btnParametre.setBounds(10, 507, 201, 50);
		listButtom.add(btnParametre);
		panel_Menu.add(btnParametre);
		
		buttonHome = new JButton("Accueil");
		buttonHome.setOpaque(false);
		buttonHome.setIcon(new ImageIcon(MainApp.class.getResource("/image/icons8_Home_50px.png")));
		buttonHome.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		buttonHome.setBounds(10, 21, 201, 124);
		listButtom.add(buttonHome);
		panel_Menu.add(buttonHome);
		
		btnDeconnecter = new JButton("D\u00E9connecter");
		btnDeconnecter.setIcon(new ImageIcon(MainApp.class.getResource("/image/icons8_Export_20px.png")));
		btnDeconnecter.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		btnDeconnecter.setBounds(10, 568, 201, 50);
		listButtom.add(btnDeconnecter);
		panel_Menu.add(btnDeconnecter);
		
		btnArticleCategoris = new JButton("<html><center>Article et Categories</center></html>");
		btnArticleCategoris.setIcon(new ImageIcon(MainApp.class.getResource("/image/icons8_Sell_Stock_20px.png")));
		listButtom.add(btnArticleCategoris);
		btnArticleCategoris.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		btnArticleCategoris.setBounds(10, 324, 201, 50);
		panel_Menu.add(btnArticleCategoris);
		
		btnEntrssorties = new JButton("<html><center>Entr\u00E9s Sorties<br>Commandes</center></html>");
		btnEntrssorties.setIcon(new ImageIcon(MainApp.class.getResource("/image/icons8_Ticket_Purchase_20px.png")));
		listButtom.add(btnEntrssorties);
		btnEntrssorties.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		btnEntrssorties.setBounds(10, 263, 201, 50);
		panel_Menu.add(btnEntrssorties);
		
		btnFournisseurs = new JButton("Fournisseurs");
		btnFournisseurs.setIcon(new ImageIcon(MainApp.class.getResource("/image/icons8_Supplier_20px.png")));
		listButtom.add(btnFournisseurs);
		btnFournisseurs.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		btnFournisseurs.setBounds(10, 202, 201, 50);
		panel_Menu.add(btnFournisseurs);
		
		btnApropos = new JButton("Apropos");
		btnApropos.setIcon(new ImageIcon(MainApp.class.getResource("/image/icons8_Info_20px.png")));
		listButtom.add(btnApropos);
		btnApropos.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		btnApropos.setBounds(10, 629, 201, 59);
		panel_Menu.add(btnApropos);
		
		btnMonProfil = new JButton("Mon Profil");
		btnMonProfil.setBounds(10, 446, 201, 50);
		panel_Menu.add(btnMonProfil);
		btnMonProfil.setIcon(new ImageIcon(MainApp.class.getResource("/image/icons8_Profile_20px.png")));
		btnMonProfil.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		listButtom.add(btnMonProfil);
		btnMonProfil.addActionListener(controlerMain);
		
		
		controlerMain= new ControlerMain(this);
		
		// Creer le magasin
		magasin= controlerMain.getMagasin();
		bande.init(magasin);
		magasin.addObserver(bande);
		
		// Ajouter comme ecouteur
		btnUtilisateur.addActionListener(controlerMain);
		btnFournisseurs.addActionListener(controlerMain);
		btnParametre.addActionListener(controlerMain);
		btnMonProfil.addActionListener(controlerMain);
		btnArticleCategoris.addActionListener(controlerMain);
		btnEntrssorties.addActionListener(controlerMain);
		btnDeconnecter.addActionListener(controlerMain);
		buttonHome.addActionListener(controlerMain);
		btnApropos.addActionListener(controlerMain);
		privilege();
		repaint();
		
		
		
	}
	
	public void privilege(){
		if(utilisateur.getStatue().equals("utilisateur")){
			btnUtilisateur.setEnabled(false);
		}
	}
	public void colorButton(JButton buton){
    	/*for (	JButton jButton : listButtom) {
    		jButton.setForeground(null);
    		jButton.setBackground(null);		
		}
    	buton.setForeground(SystemColor.window);
    	//buton.setBackground(SystemColor.textInactiveText);
    	buton.setBackground(new Color(0, 0, 139));
    	*/
    }
	public JDesktopPane getPanel_Logo() {
		return desk;
	}


	public JPanel getContentPane() {
		return contentPane;
	}

	public JButton getbuttonHome() {
		return buttonHome;
	}

	public JButton getBtnUtilisateur() {
		return btnUtilisateur;
	}

	public JButton getBtnParametre() {
		return btnParametre;
	}
	
	public JButton getButtonHome() {
		return buttonHome;
	}


	public JButton getBtnDeconnecter() {
		return btnDeconnecter;
	}


	public JButton getBtnMonProfil() {
		return btnMonProfil;
	}

	public JPanel getPanel_Menu() {
		return panel_Menu;
	}

	public JPanel getPanel_Bande() {
		return null;
	}


	
	public ControlerMain getControlerMain() {
		return controlerMain;
	}

	public ArrayList<JButton> getListButtom() {
		return listButtom;
	}

	public JButton getBtnArticleCategoris() {
		return btnArticleCategoris;
	}

	public JButton getBtnEntrssorties() {
		return btnEntrssorties;
	}

	public static JDesktopPane getDesk() {
		return desk;
	}

	public static void setDesk(JDesktopPane desk) {
		MainApp.desk = desk;
	}

	public GestionUtilisateur getgUtilisateur() {
		return gUtilisateur;
	}

	public JButton getBtnFournisseurs() {
		return btnFournisseurs;
	}

	public static Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public JButton getBtnApropos() {
		return btnApropos;
	}

	public Magasin getMagasin() {
		return magasin;
	}

	public void setMagasin(Magasin magasin) {
		this.magasin = magasin;
	}

	public JPanel getBande() {
		return bande;
	}

	public void setBande(InfoMag bande) {
		this.bande = bande;
	}
	public JInternalFrame getLogo(){
		return logo;
	}
}
