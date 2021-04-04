package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import dao.implement.MagasinDAO;
import view.GestionParametrage;

public class ControlerParametrage implements ActionListener{
	GestionParametrage parametrage;
	MagasinDAO magasinDAO;
	public ControlerParametrage(GestionParametrage gestionParametrage) {
		this.parametrage=gestionParametrage;
		magasinDAO=new MagasinDAO();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==parametrage.getBtnValider()){
			if(parametrage.verifier()){
				parametrage.modif();
				parametrage.getMagasin().Changer();
				if(magasinDAO.update(parametrage.getMagasin())){
					JOptionPane.showMessageDialog(null, "Succès !!");
				};
				
			}
			
		}
		
		
		
	}
	
}
