package classeMetier;

public class Reception {
	private Integer numR;
	private String daterep;
	private Integer qtelivre;
	private Integer numcmd;

	public Reception(Integer numR, String daterep, Integer qtelivre, Integer numcmd) {
		super();
		this.numR = numR;
		this.daterep = daterep;
		this.qtelivre = qtelivre;
		this.numcmd = numcmd;
	}
	public void setNumR(Integer numR) {
		this.numR = numR;
	}
	public String getDaterep() {
		return daterep;
	}
	public void setDaterep(String daterep) {
		this.daterep = daterep;
	}
	public Integer getQtelivre() {
		return qtelivre;
	}
	public void setQtelivre(Integer qtelivre) {
		this.qtelivre = qtelivre;
	}
	public Integer getNumcmd() {
		return numcmd;
	}
	public void setNumcmd(Integer numcmd) {
		this.numcmd = numcmd;
	}


}
