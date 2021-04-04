package classeMetier;

public class Utilisateur {
	private Integer idUtil;
	private String login;
	private String pass;
	private Integer cin;
	
	private String  nom;
	private String  prenom;
	private String  date_nais;
	private String statue;
	private String sexe;

	public Utilisateur() {
		// TODO Auto-generated constructor stub
	}


	public Utilisateur(Integer idUtil,Integer cin, String login, String pass, String nom, String prenom, String date_nais,String statue,String sexe) {
		super();
		this.idUtil=idUtil;
		this.cin = cin;
		this.login=login;
		this.pass=pass;
		this.nom = nom;
		this.prenom = prenom;
		this.date_nais = date_nais;
		this.statue=statue;
		this.sexe=sexe;
	}


	public Integer getCin() {
		return cin;
	}


	public void setCin(Integer cin) {
		this.cin = cin;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getDate_nais() {
		return date_nais;
	}


	public void setDate_nais(String date_nais) {
		this.date_nais = date_nais;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public String getStatue() {
		return statue;
	}


	public void setStatue(String statue) {
		this.statue = statue;
	}

	

	public String getSexe() {
		return sexe;
	}


	public void setSexe(String sexe) {
		this.sexe = sexe;
	}


	public Integer getIdUtil() {
		return idUtil;
	}


	public void setIdUtil(Integer idUtil) {
		this.idUtil = idUtil;
	}


	

}
