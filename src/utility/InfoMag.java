package utility;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import classeMetier.Magasin;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingConstants;

public class InfoMag extends JPanel implements Observer {
	private JLabel label_email;
	private JLabel label_bp;
	private JLabel label_cell;
	private JLabel label_fax;
	private JLabel nomMag;
	private JLabel nomPro;
	private JLabel label_adr;
	private JLabel label_ville;
	private JLabel label_Annee;
	private JLabel label_heure;
	private JLabel label_Pm;
	private JLabel label_secon;
	 int tik_Montre = 0;
	/**
	 * Create the panel.
	 */
	public InfoMag() {
		setLayout(null);
		setBounds(0, 0, 1111, 126);
		//JPanel panel = new JPanel();
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(241, 5, 1111, 126);
		
		JLabel label = new JLabel("Nom du Magasin: ");
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		label.setBounds(10, 11, 138, 27);
		add(label);
		
		String filename="Font_Police/digital-7 (mono italic).ttf";
		Font font = null;
		Font fontSeconde=null;
		try {
			font=Font.createFont(Font.TRUETYPE_FONT, new File(filename));
			fontSeconde=Font.createFont(Font.TRUETYPE_FONT, new File(filename));
			font=font.deriveFont(Font.BOLD,40);
			fontSeconde=fontSeconde.deriveFont(Font.BOLD,18);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
			ge.registerFont(fontSeconde);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		nomMag = new JLabel("__________");
		nomMag.setForeground(Color.RED);
		nomMag.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 15));
		nomMag.setBounds(147, 11, 162, 27);
		add(nomMag);
		
		label_heure = new JLabel("11:59:30");
		label_heure.setHorizontalTextPosition(SwingConstants.CENTER);
		label_heure.setHorizontalAlignment(SwingConstants.RIGHT);
		label_heure.setForeground(Color.DARK_GRAY);
		label_heure.setFont(font);
		label_heure.setBorder(null);
		label_heure.setBackground(Color.WHITE);
		label_heure.setBounds(493, 34, 128, 53);
		add(label_heure);
		
		label_Annee = new JLabel("01/01/2017");
		label_Annee.setHorizontalTextPosition(SwingConstants.CENTER);
		label_Annee.setHorizontalAlignment(SwingConstants.CENTER);
		label_Annee.setForeground(Color.DARK_GRAY);
		label_Annee.setFont(font);
		label_Annee.setBounds(267, 34, 243, 53);
		add(label_Annee);
		
		JLabel label_4 = new JLabel("Fax :");
		label_4.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		label_4.setBounds(793, 11, 57, 27);
		add(label_4);
		
		JLabel label_5 = new JLabel("Cell :");
		label_5.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		label_5.setBounds(793, 34, 46, 26);
		add(label_5);
		
		JLabel label_6 = new JLabel("E-mail");
		label_6.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		label_6.setBounds(793, 86, 74, 27);
		add(label_6);
		
		label_fax = new JLabel("____________");
		label_fax.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label_fax.setBounds(901, 11, 200, 27);
		add(label_fax);
		
		label_cell = new JLabel("__________________");
		label_cell.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label_cell.setBounds(901, 34, 200, 26);
		add(label_cell);
		
		label_email = new JLabel("______________");
		label_email.setForeground(Color.BLUE);
		label_email.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		label_email.setBounds(901, 86, 200, 27);
		add(label_email);
		
		JLabel label_10 = new JLabel("Adresse : ");
		label_10.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		label_10.setBounds(10, 60, 106, 27);
		add(label_10);
		
		JLabel label_11 = new JLabel("Ville : ");
		label_11.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		label_11.setBounds(10, 86, 106, 27);
		add(label_11);
		
		JLabel label_12 = new JLabel("Propri\u00E9taire");
		label_12.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		label_12.setBounds(10, 34, 106, 27);
		add(label_12);
		
		nomPro = new JLabel("________________");
		nomPro.setFont(new Font("Dialog", Font.ITALIC, 14));
		nomPro.setBounds(146, 34, 110, 27);
		add(nomPro);
		
		JLabel label_14 = new JLabel("BP :");
		label_14.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		label_14.setBounds(793, 60, 46, 27);
		add(label_14);
		
		label_bp = new JLabel("___________________");
		label_bp.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label_bp.setBounds(901, 60, 200, 27);
		add(label_bp);
		
		label_ville = new JLabel("_________________");
		label_ville.setFont(new Font("Dialog", Font.ITALIC, 14));
		label_ville.setBounds(147, 86, 110, 27);
		add(label_ville);
		
		label_adr = new JLabel("");
		label_adr.setFont(new Font("Dialog", Font.ITALIC, 14));
		label_adr.setBounds(147, 60, 108, 27);
		add(label_adr);
		
		label_secon = new JLabel("30");
		label_secon.setHorizontalTextPosition(SwingConstants.LEFT);
		label_secon.setForeground(Color.DARK_GRAY);
		label_secon.setFont(fontSeconde);
		label_secon.setBounds(618, 34, 35, 26);
		add(label_secon);
		
		label_Pm = new JLabel("PM");
		label_Pm.setForeground(Color.DARK_GRAY);
		label_Pm.setFont(new Font("Dialog", Font.PLAIN, 9));
		label_Pm.setBounds(631, 58, 35, 14);
		add(label_Pm);
		datecourante();

	}
	/*--------------------------- Cette permet de charger la date et heure ------------------*/
    public void datecourante() {
    	
        Thread clock = new Thread() {
            public void run() {
                for (;;) {
                    Calendar Cal = new GregorianCalendar();
                  
                    int scondes = Cal.get(Calendar.SECOND);
                    int minute = Cal.get(Calendar.MINUTE);
                    int heure = Cal.get(Calendar.HOUR_OF_DAY);
                    int AM_PM = Cal.get(Calendar.AM_PM);  
                    String pa;
                    if(AM_PM==1){
                        pa="PM";
                    }else{
                        pa="AM";
                    }
                   
					if(tik_Montre % 2 ==0){
						label_heure.setText( + heure + ":" + (minute)  );
                    }
                    	
                    else
                    	label_heure.setText( + heure + " " + (minute) );
					label_secon.setText(""+scondes);
					label_Pm.setText(pa);
                    tik_Montre++;
                    
                    int mois = Cal.get(Calendar.MONTH);
                    int annee = Cal.get(Calendar.YEAR);
                    int jour = Cal.get(Calendar.DAY_OF_MONTH);

                    label_Annee.setText( + jour + "/" + (mois+1) + "/" + annee);              
                    
                    
                    try {
                        sleep(1000);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(Calendar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

        };
        
        clock.start();
    }
	@Override
	public void update(Observable arg0, Object arg1) {
		
		init((Magasin)arg0);
		
	}
	public void init(Magasin mag){
		try {
			label_adr.setText(mag.getAdr());
			label_bp.setText(mag.getBp());
			label_cell.setText(mag.getTel().toString());
			label_email.setText(mag.getEmail());
			label_fax.setText(mag.getFax().toString());
			label_ville.setText(mag.getVille());
			nomMag.setText(mag.getNomMag());
			nomPro.setText(mag.getProprietaire());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
