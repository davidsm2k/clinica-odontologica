package model;

public class Responsavel {
	//Responsavel
	private int codResponsavel;
	private String nomeResponsavel;
	private String cpfResponsavel;
	private String dataNascResponsavel;
	private String idadeResponsavel;
	private String emailResponsavel;
	private String celularResponsavel;
	private String sexoResponsavel;
	private long cpf;
	
	public Responsavel() {
	}

	public Responsavel(int codResponsavel, String nomeResponsavel, String cpfResponsavel, String dataNascResponsavel,
			String idadeResponsavel, String emailResponsavel, String celularResponsavel, String sexoResponsavel,
			long cpf) {
		super();
		this.codResponsavel = codResponsavel;
		this.nomeResponsavel = nomeResponsavel;
		this.cpfResponsavel = cpfResponsavel;
		this.dataNascResponsavel = dataNascResponsavel;
		this.idadeResponsavel = idadeResponsavel;
		this.emailResponsavel = emailResponsavel;
		this.celularResponsavel = celularResponsavel;
		this.sexoResponsavel = sexoResponsavel;
		this.cpf = cpf;
	}

	public int getCodResponsavel() {
		return codResponsavel;
	}

	public void setCodResponsavel(int codResponsavel) {
		this.codResponsavel = codResponsavel;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getCpfResponsavel() {
		return cpfResponsavel;
	}

	public void setCpfResponsavel(String cpfResponsavel) {
		this.cpfResponsavel = cpfResponsavel;
	}

	public String getDataNascResponsavel() {
		return dataNascResponsavel;
	}

	public void setDataNascResponsavel(String dataNascResponsavel) {
		this.dataNascResponsavel = dataNascResponsavel;
	}

	public String getIdadeResponsavel() {
		return idadeResponsavel;
	}

	public void setIdadeResponsavel(String idadeResponsavel) {
		this.idadeResponsavel = idadeResponsavel;
	}

	public String getEmailResponsavel() {
		return emailResponsavel;
	}

	public void setEmailResponsavel(String emailResponsavel) {
		this.emailResponsavel = emailResponsavel;
	}

	public String getCelularResponsavel() {
		return celularResponsavel;
	}

	public void setCelularResponsavel(String celularResponsavel) {
		this.celularResponsavel = celularResponsavel;
	}

	public String getSexoResponsavel() {
		return sexoResponsavel;
	}

	public void setSexoResponsavel(String sexoResponsavel) {
		this.sexoResponsavel = sexoResponsavel;
	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	
}
