package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import classeMetier.Commande;

import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class ConfirmationCommande extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField;
	private Commande commande;

	/**
	 * Create the frame.
	 */
	public ConfirmationCommande(Commande commande) {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 562);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.commande=commande;
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Confirmation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 187, 430, 230);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JRadioButton rdbtnQuantiteCommadeEst = new JRadioButton("Quantite Commad\u00E9e est livr\u00E9e");
		rdbtnQuantiteCommadeEst.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		buttonGroup.add(rdbtnQuantiteCommadeEst);
		rdbtnQuantiteCommadeEst.setSelected(true);
		rdbtnQuantiteCommadeEst.setBounds(18, 35, 203, 23);
		panel.add(rdbtnQuantiteCommadeEst);
		
		JRadioButton rdbtnQuantiteCommadeNest = new JRadioButton("Quantite Commad\u00E9e n'est livr\u00E9e");
		rdbtnQuantiteCommadeNest.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		buttonGroup.add(rdbtnQuantiteCommadeNest);
		rdbtnQuantiteCommadeNest.setBounds(18, 71, 203, 23);
		panel.add(rdbtnQuantiteCommadeNest);
		
		textField = new JTextField();
		textField.setBounds(329, 68, 75, 28);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnValider = new JButton("Valider");
		btnValider.setBounds(155, 191, 89, 28);
		panel.add(btnValider);
		
		JLabel lblPrciser = new JLabel("Pr\u00E9ciser:");
		lblPrciser.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		lblPrciser.setBounds(265, 71, 54, 23);
		panel.add(lblPrciser);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 71, 414, 8);
		contentPane.add(separator);
		
		JLabel lblConfirmation = new JLabel("Confirmation de la Reception de la Commande");
		lblConfirmation.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblConfirmation.setBounds(10, 36, 414, 28);
		contentPane.add(lblConfirmation);
		
		JLabel lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		lblX.setForeground(Color.RED);
		lblX.setFont(new Font("Sitka Text", Font.BOLD, 15));
		lblX.setBounds(401, 11, 23, 14);
		contentPane.add(lblX);
		
		 FrameDragListener frameDragListener = new FrameDragListener(this);
         this.addMouseListener(frameDragListener);
         this.addMouseMotionListener(frameDragListener);
         
         setLocationRelativeTo(null);
	}
	
	 public static class FrameDragListener extends MouseAdapter {

	        private final JFrame frame;
	        private Point mouseDownCompCoords = null;

	        public FrameDragListener(JFrame frame) {
	            this.frame = frame;
	        }

	        public void mouseReleased(MouseEvent e) {
	            mouseDownCompCoords = null;
	        }

	        public void mousePressed(MouseEvent e) {
	            mouseDownCompCoords = e.getPoint();
	        }

	        public void mouseDragged(MouseEvent e) {
	            Point currCoords = e.getLocationOnScreen();
	            frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
	        }
	    }
}
