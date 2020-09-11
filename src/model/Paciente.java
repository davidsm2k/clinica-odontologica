package model;

public class Paciente {
	//=== Atributos ===
	//Paciente
	private long cpf;
	private String nome;
	private String dataNasc;
	private String idade;
	private String email;
	private String celular;
	private String sexo;
		
	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Paciente(long cpf, String nome, String dataNasc, String idade, String email, String celular, String sexo) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.dataNasc = dataNasc;
		this.idade = idade;
		this.email = email;
		this.celular = celular;
		this.sexo = sexo;
	}

	//=== Construtor ===
	public Paciente() {
	}

}
