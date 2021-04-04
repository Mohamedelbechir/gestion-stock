package classeMetier;

import java.util.Observable;

public class Magasin extends Observable {
	private Integer idMag;
	private String nomMag;
	private String proprietaire;
	private String ville;
	private String adr;
	private String email;
	private String bp;
	private Integer tel;
	private Integer fax;
	
	public Magasin() {
		// TODO Auto-generated constructor stub
	}
	
	public Magasin(Integer idMag, String nomMag, String proprietaire, String ville, String adr, String email, String bp,
			Integer tel, Integer fax) {
		super();
		this.idMag = idMag;
		this.nomMag = nomMag;
		this.proprietaire = proprietaire;
		this.ville = ville;
		this.adr = adr;
		this.email = email;
		this.bp = bp;
		this.tel = tel;
		this.fax = fax;
	}


	

	public Integer getIdMag() {
		return idMag;
	}




	public void setIdMag(Integer idMag) {
		this.idMag = idMag;
	}




	public String getNomMag() {
		return nomMag;
	}


	public void setNomMag(String nomMag) {
		this.nomMag = nomMag;
		
	}


	public String getProprietaire() {
		return proprietaire;
	}


	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
		
	}


	public String getVille() {
		return ville;
	}


	public void setVille(String ville) {
		this.ville = ville;
		
	}


	public String getAdr() {
		return adr;
	}


	public void setAdr(String adr) {
		this.adr = adr;
		
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
		
	}


	public String getBp() {
		return bp;
	}


	public void setBp(String bp) {
		this.bp = bp;
		
	}


	public Integer getTel() {
		return tel;
	}


	public void setTel(Integer tel) {
		this.tel = tel;
		
	}


	public Integer getFax() {
		return fax;
	}


	public void setFax(Integer fax) {
		this.fax = fax;
		
	}
	public void Changer(){
		
		try {
			
			this.setChanged();
			this.notifyObservers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
