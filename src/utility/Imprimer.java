package utility;

import javax.swing.JOptionPane;

import connxion_Requete.Connexion;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class Imprimer {

	public Imprimer() {
		// TODO Auto-generated constructor stub
	}
	/*----------------------- Cette method permet de faire Impression ----------------------------*/
	public static void print(String pathFile){

		org.apache.log4j.BasicConfigurator.configure();
		 try{
			 
			//JasperReport report = JasperCompileManager.compileReport("Rapport\\Liste_Des_Utilisateurs.jrxml");
			JasperReport report = JasperCompileManager.compileReport(pathFile);
			JasperPrint print = JasperFillManager.fillReport(report, null, new Connexion().getConnection());
			JasperViewer.viewReport(print,false);
	        
	        }
		 catch(Exception e)
	    {
	           JOptionPane.showMessageDialog(null, e);

	    }

	}
}
