package classeMetier;

public class Fournisseur {
	private Integer numF;
	private String nomF;
	private String adr;
	private String codeP;
	private String ville;
	private String pays;
	private String tele;
	private String fax;
	private String email;

	
	public Fournisseur() {
		
		// TODO Auto-generated constructor stub
	}

	public Fournisseur(Integer numF, String nomF, String adr, String codeP, String ville, String pays, String tele,
			String fax, String email) {
		super();
		this.numF = numF;
		this.nomF = nomF;
		this.adr = adr;
		this.codeP = codeP;
		this.ville = ville;
		this.pays = pays;
		this.tele = tele;
		this.fax = fax;
		this.email = email;
	}

	public Integer getNumF() {
		return numF;
	}

	public void setNumF(Integer numF) {
		this.numF = numF;
	}

	public String getNomF() {
		return nomF;
	}

	public void setNomF(String nomF) {
		this.nomF = nomF;
	}

	public String getAdr() {
		return adr;
	}

	public void setAdr(String adr) {
		this.adr = adr;
	}

	public String getCodeP() {
		return codeP;
	}

	public void setCodeP(String codeP) {
		this.codeP = codeP;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}
