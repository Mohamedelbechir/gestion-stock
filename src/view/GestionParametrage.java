package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import classeMetier.Magasin;
import controler.ControlerParametrage;
import utility.VerifyUser;

public class GestionParametrage extends JInternalFrame {
	private JTextField textField_ville;
	private JTextField textField_adr;
	private JTextField textField_pro;
	private JTextField textField_nomMag;
	private JTextField textField_cel;
	private JTextField textField_fax;
	private JTextField textField_mail;
	private JTextField textField_bp;
	private JButton btnValider;
	private Magasin magasin;
	private JLabel err_tel;
	private JLabel err_fix;
	private JLabel err_mail;
	private ControlerParametrage controlerParametrage;

	
	/**
	 * Create the frame.
	 */
	public GestionParametrage(Magasin magasin) {
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBounds(241, 142, 1111, 590);
		getContentPane().setLayout(null);
		
		this.magasin=magasin;
		
		JLabel lblGestionDesUtilisateurs = new JLabel("Parametrage du Magasin");
		lblGestionDesUtilisateurs.setFont(new Font("Sitka Text", Font.BOLD, 13));
		lblGestionDesUtilisateurs.setBounds(10, 11, 224, 30);
		getContentPane().add(lblGestionDesUtilisateurs);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 40, 1091, 13);
		getContentPane().add(separator);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Parametrage", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(10, 138, 1091, 350);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel label = new JLabel("Adresse :");
		label.setBounds(14, 86, 124, 29);
		panel_2.add(label);
		label.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		
		JLabel label_1 = new JLabel("Propri\u00E9taire :");
		label_1.setBounds(14, 59, 124, 28);
		panel_2.add(label_1);
		label_1.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		
		JLabel lblNomDuMagasin = new JLabel("Nom du Magasin:");
		lblNomDuMagasin.setBounds(14, 32, 124, 30);
		panel_2.add(lblNomDuMagasin);
		lblNomDuMagasin.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		
		JLabel label_3 = new JLabel("Ville :");
		label_3.setBounds(14, 113, 124, 30);
		panel_2.add(label_3);
		label_3.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		
		textField_ville = new JTextField();
		textField_ville.setBounds(136, 113, 202, 28);
		panel_2.add(textField_ville);
		textField_ville.setText((String) null);
		textField_ville.setColumns(10);
		
		textField_adr = new JTextField();
		textField_adr.setBounds(136, 86, 202, 28);
		panel_2.add(textField_adr);
		textField_adr.setText((String) null);
		textField_adr.setColumns(10);
		
		textField_pro = new JTextField();
		textField_pro.setBounds(136, 59, 202, 28);
		panel_2.add(textField_pro);
		textField_pro.setText((String) null);
		textField_pro.setColumns(10);
		
		textField_nomMag = new JTextField();
		textField_nomMag.setBounds(136, 32, 202, 28);
		panel_2.add(textField_nomMag);
		textField_nomMag.setText((String) null);
		textField_nomMag.setColumns(10);
		
		JLabel label_6 = new JLabel(" Email:");
		label_6.setBounds(425, 59, 139, 28);
		panel_2.add(label_6);
		label_6.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		
		JLabel label_7 = new JLabel("Code Postal :");
		label_7.setBounds(427, 32, 137, 30);
		panel_2.add(label_7);
		label_7.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		
		JLabel label_4 = new JLabel("T\u00E9l\u00E9phone Cellulaire :");
		label_4.setBounds(427, 112, 149, 31);
		panel_2.add(label_4);
		label_4.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		
		JLabel label_5 = new JLabel("T\u00E9l\u00E9phone Fixe :");
		label_5.setBounds(427, 86, 137, 29);
		panel_2.add(label_5);
		label_5.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		
		textField_mail = new JTextField();
		textField_mail.setBounds(608, 59, 201, 27);
		panel_2.add(textField_mail);
		textField_mail.setText((String) null);
		textField_mail.setColumns(10);
		textField_mail.setBorder(new LineBorder(new Color(171, 173, 179)));
		
		textField_bp = new JTextField();
		textField_bp.setBounds(608, 32, 201, 27);
		panel_2.add(textField_bp);
		textField_bp.setText((String) null);
		textField_bp.setColumns(10);
		textField_bp.setBorder(new LineBorder(new Color(171, 173, 179)));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(608, 86, 201, 28);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setForeground(Color.BLACK);
		panel.setBounds(0, 0, 201, 28);
		panel_3.add(panel);
		panel.setLayout(null);
		
		JLabel label_9 = new JLabel("+216");
		label_9.setBounds(0, 0, 41, 28);
		panel.add(label_9);
		label_9.setForeground(Color.BLUE);
		label_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_9.setBorder(null);
		
		textField_fax = new JTextField();
		textField_fax.setBounds(39, 0, 162, 26);
		panel.add(textField_fax);
		textField_fax.setToolTipText("num\u00E9rique \u00E0 8 chiffres");
		textField_fax.setText((String) null);
		textField_fax.setColumns(10);
		textField_fax.setBorder(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(609, 113, 200, 27);
		panel_2.add(panel_4);
		panel_4.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 200, 27);
		panel_4.add(panel_1);
		panel_1.setLayout(null);
		
		textField_cel = new JTextField();
		textField_cel.setBounds(38, 0, 162, 27);
		panel_1.add(textField_cel);
		textField_cel.setToolTipText("num\u00E9rique \u00E0 8 chiffres");
		textField_cel.setText((String) null);
		textField_cel.setColumns(10);
		textField_cel.setBorder(null);
		
		JLabel label_8 = new JLabel("+216");
		label_8.setBounds(0, 0, 42, 27);
		panel_1.add(label_8);
		label_8.setForeground(Color.BLUE);
		label_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_8.setBorder(null);
		
		btnValider = new JButton("Valider");
		btnValider.setIcon(new ImageIcon(GestionParametrage.class.getResource("/image/Ok-icon.png")));
		btnValider.setBounds(363, 268, 124, 39);
		panel_2.add(btnValider);
		
		err_mail = new JLabel("");
		err_mail.setVisible(false);
		err_mail.setIcon(new ImageIcon(GestionParametrage.class.getResource("/image/error.png")));
		err_mail.setBounds(819, 59, 29, 28);
		panel_2.add(err_mail);
		
		err_fix = new JLabel("");
		err_fix.setVisible(false);
		err_fix.setIcon(new ImageIcon(GestionParametrage.class.getResource("/image/error.png")));
		err_fix.setBounds(819, 86, 29, 28);
		panel_2.add(err_fix);
		
		err_tel = new JLabel("");
		err_tel.setVisible(false);
		err_tel.setIcon(new ImageIcon(GestionParametrage.class.getResource("/image/error.png")));
		err_tel.setBounds(819, 113, 29, 28);
		panel_2.add(err_tel);
		
		JLabel errNm = new JLabel("");
		errNm.setVisible(false);
		errNm.setIcon(new ImageIcon(GestionParametrage.class.getResource("/image/error.png")));
		errNm.setBounds(348, 32, 29, 29);
		panel_2.add(errNm);
		
		JLabel errPro = new JLabel("");
		errPro.setVisible(false);
		errPro.setIcon(new ImageIcon(GestionParametrage.class.getResource("/image/error.png")));
		errPro.setBounds(348, 59, 29, 28);
		panel_2.add(errPro);
		
		JLabel errAd = new JLabel("");
		errAd.setVisible(false);
		errAd.setIcon(new ImageIcon(GestionParametrage.class.getResource("/image/error.png")));
		errAd.setBounds(348, 86, 29, 29);
		panel_2.add(errAd);
		
		JLabel errV = new JLabel("");
		errV.setVisible(false);
		errV.setIcon(new ImageIcon(GestionParametrage.class.getResource("/image/error.png")));
		errV.setBounds(348, 113, 29, 30);
		panel_2.add(errV);
		
		JLabel errCp = new JLabel("");
		errCp.setVisible(false);
		errCp.setIcon(new ImageIcon(GestionParametrage.class.getResource("/image/error.png")));
		errCp.setBounds(819, 32, 29, 30);
		panel_2.add(errCp);
		controlerParametrage =new ControlerParametrage(this);
		btnValider.addActionListener(controlerParametrage);
		remove_title_bar() ;
		chargerChamp();
	}
	public boolean verfierNomMa(){
		if(textField_nomMag.getText()==null || textField_nomMag.getText()==""){
			
			return false;
		}
		return true;
	}
	public boolean verifier(){
		return true;
	}
	void remove_title_bar() {
        putClientProperty("Gestion.isPalette", Boolean.TRUE);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setBorder(new LineBorder(new Color(0, 0, 0)));

    }
	public void chargerChamp(){
		try {
			textField_adr.setText(magasin.getAdr());
			textField_bp.setText(magasin.getBp());
			textField_cel.setText(magasin.getTel().toString());
			textField_fax.setText(magasin.getFax().toString());
			textField_mail.setText(magasin.getEmail());
			textField_nomMag.setText(magasin.getNomMag());
			textField_pro.setText(magasin.getProprietaire());
			textField_ville.setText(magasin.getVille());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public void modif(){
		try {
			magasin.setAdr(textField_adr.getText());
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			magasin.setBp(textField_bp.getText());
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			magasin.setTel(Integer.parseInt(textField_cel.getText()));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			magasin.setFax(Integer.parseInt(textField_fax.getText()));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			magasin.setEmail(textField_mail.getText());
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			magasin.setNomMag(textField_nomMag.getText());
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			magasin.setProprietaire(textField_pro.getText());
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			magasin.setVille(textField_ville.getText());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public JTextField getTextField_ville() {
		return textField_ville;
	}
	public void setTextField_ville(JTextField textField_ville) {
		this.textField_ville = textField_ville;
	}
	public JTextField getTextField_adr() {
		return textField_adr;
	}
	public void setTextField_adr(JTextField textField_adr) {
		this.textField_adr = textField_adr;
	}
	public JTextField getTextField_pro() {
		return textField_pro;
	}
	public void setTextField_pro(JTextField textField_pro) {
		this.textField_pro = textField_pro;
	}
	public JTextField getTextField_nomMag() {
		return textField_nomMag;
	}
	public void setTextField_nomMag(JTextField textField_nomMag) {
		this.textField_nomMag = textField_nomMag;
	}
	public JTextField getTextField_cel() {
		return textField_cel;
	}
	public void setTextField_cel(JTextField textField_cel) {
		this.textField_cel = textField_cel;
	}
	public JTextField getTextField_fax() {
		return textField_fax;
	}
	public void setTextField_fax(JTextField textField_fax) {
		this.textField_fax = textField_fax;
	}
	public JTextField getTextField_mail() {
		return textField_mail;
	}
	public void setTextField_mail(JTextField textField_mail) {
		this.textField_mail = textField_mail;
	}
	public JTextField getTextField_bp() {
		return textField_bp;
	}
	public void setTextField_bp(JTextField textField_bp) {
		this.textField_bp = textField_bp;
	}
	public JButton getBtnValider() {
		return btnValider;
	}
	public void setBtnValider(JButton btnValider) {
		this.btnValider = btnValider;
	}
	public Magasin getMagasin() {
		return magasin;
	}
	public void setMagasin(Magasin magasin) {
		this.magasin = magasin;
	}
	public JLabel getErr_tel() {
		return err_tel;
	}
	public void setErr_tel(JLabel err_tel) {
		this.err_tel = err_tel;
	}
	public JLabel getErr_fix() {
		return err_fix;
	}
	public void setErr_fix(JLabel err_fix) {
		this.err_fix = err_fix;
	}
	public JLabel getErr_mail() {
		return err_mail;
	}
	public void setErr_mail(JLabel err_mail) {
		this.err_mail = err_mail;
	}
	
	/*public boolean verifierTel(){
		if(textField_tel.getText().length()!=0){
			
			try {
			Integer.parseInt(textField_tel.getText().toString());
			
			} catch (NumberFormatException e) {
				err_tel.setVisible(true);
				err_tel.setToolTipText("8 chiffres !!");
				return false;
			}
			if(textField_tel.getText().length()>8){
				err_tel.setVisible(true);
				err_tel.setToolTipText("8 chiffres !!");
			}
		}
		err_tel.setVisible(false);
		return true;
		
	}
	public boolean verif*/
}
