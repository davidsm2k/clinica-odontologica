package model;

public class Prontuario {
	private int codProntuario;
	private String tipoParecer;
	private String dataParecer;
	private String horaParecer;
	private String obs;
	private long cpf;
	
	public Prontuario() {
	}

	public Prontuario(int codProntuario, String tipoParecer, String dataParecer, String horaParecer, String obs, long cpf) {
		this.codProntuario = codProntuario;
		this.tipoParecer = tipoParecer;
		this.dataParecer = dataParecer;
		this.horaParecer = horaParecer;
		this.obs = obs;
		this.cpf = cpf;
	}

	public int getCodProntuario() {
		return codProntuario;
	}

	public void setCodProntuario(int codProntuario) {
		this.codProntuario = codProntuario;
	}

	public String getTipoParecer() {
		return tipoParecer;
	}

	public void setTipoParecer(String tipoParecer) {
		this.tipoParecer = tipoParecer;
	}

	public String getDataParecer() {
		return dataParecer;
	}

	public void setDataParecer(String dataParecer) {
		this.dataParecer = dataParecer;
	}

	public String getHoraParecer() {
		return horaParecer;
	}

	public void setHoraParecer(String horaParecer) {
		this.horaParecer = horaParecer;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}
}
