package model;

public class Agendamento {
	
	private int codAgendamento;
	private String dataAgendamento;
	private String horaAgendamento;
	private String tipoAgendamento;
	private String turno;
	private long cpf;
	
	public Agendamento() {
	}

	public Agendamento(int codAgendamento, String dataAgendamento, String horaAgendamento, String tipoAgendamento, String turno, long cpf) {
		this.codAgendamento = codAgendamento;
		this.dataAgendamento = dataAgendamento;
		this.horaAgendamento = horaAgendamento;
		this.tipoAgendamento = tipoAgendamento;
		this.turno = turno;
		this.cpf = cpf;
	}

	public int getCodAgendamento() {
		return codAgendamento;
	}

	public void setCodAgendamento(int codAgendamento) {
		this.codAgendamento = codAgendamento;
	}

	public String getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(String dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public String getHoraAgendamento() {
		return horaAgendamento;
	}

	public void setHoraAgendamento(String horaAgendamento) {
		this.horaAgendamento = horaAgendamento;
	}

	public String getTipoAgendamento() {
		return tipoAgendamento;
	}

	public void setTipoAgendamento(String tipoAgendamento) {
		this.tipoAgendamento = tipoAgendamento;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}
	
}
