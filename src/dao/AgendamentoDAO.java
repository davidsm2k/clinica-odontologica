package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Agendamento;
import util.ConnectionFactory;

public class AgendamentoDAO {
	
	private Agendamento agendamento;
	private Connection conn; //Conecta ao Banco de Dados
	private PreparedStatement ps; // Permite Exeutar um comando SQL
	private ResultSet rs; // O mesmo que uma tabela
	
	public AgendamentoDAO() throws Exception{
		try {
			conn = ConnectionFactory.getConnection();
		}catch(Exception e){
			throw new Exception("Erro: "+ e.getMessage());
		}
	}
	
	public void salvar (Agendamento agendamento) throws Exception{
		try {
			String sql = "INSERT INTO agendamento(codAgendamento, dataAgendamento, horaAgendamento, tipoAgendamento, turno, cpf)"
					+ "values(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, agendamento.getCodAgendamento());
			ps.setString(2, agendamento.getDataAgendamento());
			ps.setString(3, agendamento.getHoraAgendamento());
			ps.setString(4, agendamento.getTipoAgendamento());
			ps.setString(5, agendamento.getTurno());
			ps.setLong(6, agendamento.getCpf());
			ps.executeUpdate();			
		}catch(Exception e){
			throw new Exception("Erro ao Salvar Agendamento.\nMotivo:"+e.getMessage());
		}
	}
	
	public List ListarTodos() throws Exception{

		List<Agendamento> lista = new ArrayList<Agendamento>();
		try{
			String sql = "SELECT * FROM agendamento";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) //Enquanto(percorrer toda a tabela)
			{	
				int codAgendamento = rs.getInt("codAgendamento");
				String dataAgendamento = rs.getString("dataAgendamento");
				String horaAgendamento = rs.getString("horaAgendamento");
				String tipoAgendamento = rs.getString("tipoAgendamento");
				String turno = rs.getString("turno");
				long cpf = rs.getLong("cpf");
				
				agendamento = new Agendamento(codAgendamento, dataAgendamento, horaAgendamento, tipoAgendamento, turno, cpf);
				lista.add(agendamento);
			}
			return lista;

		}catch(Exception e){
			throw new Exception("Erro ao Listar Agendamento.\nMotivo:"+e.getMessage());
		}
	}
	
	public void alterar (Agendamento agendamento)throws Exception{
		try {
			String sql = "UPDATE agendamento SET codAgendamento=?, dataAgendamento=?, horaAgendamento=?, tipoAgendamento=?, turno=?"
					+ "WHERE codAgendamento=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, agendamento.getDataAgendamento());
			ps.setString(2, agendamento.getHoraAgendamento());
			ps.setString(3, agendamento.getTipoAgendamento());
			ps.setString(4, agendamento.getTurno());
			ps.setInt(5, agendamento.getCodAgendamento());
			ps.executeUpdate();			
		}catch(Exception e){
			throw new Exception("Erro ao Alterar Agendamento.\nMotivo: "+e.getMessage());
		}
	}
	
	public void excluir (Agendamento agendamento)throws Exception{
		try {
			String sql = "DELETE FROM agendamento "
					+ "WHERE codAgendamento=?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, agendamento.getCodAgendamento());
			ps.executeUpdate();			
		}catch(Exception e){
			throw new Exception("Erro ao Excluir Agendamento.\nMotivo: "+e.getMessage());
		}
	}
	
	public Agendamento consultar(long cpf)throws Exception{
		try{
			ps = conn.prepareStatement("SELECT * FROM agendamento WHERE cpf=?");
			ps.setLong(1, cpf);
			rs = ps.executeQuery();	
			if(rs.next()){
				int codAgendamento = rs.getInt("codAgendamento");
				String dataAgendamento = rs.getString("dataAgendamento");
				String horaAgendamento = rs.getString("horaAgendamento");
				String tipoAgendamento = rs.getString("tipoAgendamento");
				String turno = rs.getString("turno");
				
				agendamento = new Agendamento(codAgendamento, dataAgendamento, horaAgendamento, tipoAgendamento, turno, cpf);
			}
			return agendamento;
		}catch(Exception e){
			throw new Exception("Erro ao Consultar Agendamento.\nMotivo:"+e.getMessage());
		}
	}

}
