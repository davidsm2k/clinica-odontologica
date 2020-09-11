package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Responsavel;
import util.ConnectionFactory;

public class ResponsavelDAO {
	private Responsavel responsavel;
	private Connection conn; //Conecta ao Banco de Dados
	private PreparedStatement ps; // Permite Exeutar um comando SQL
	private ResultSet rs; // O mesmo que uma tabela

	public ResponsavelDAO() throws Exception {
		try {
			conn = ConnectionFactory.getConnection();
		}catch(Exception e){
			throw new Exception("Erro: "+ e.getMessage());
		}
	}

	public void salvar(Responsavel responsavel)throws Exception {
		try {
			String sql = "INSERT INTO responsavel(nomeResponsavel, cpfResponsavel, dataNascResponsavel, idadeResponsavel, emailResponsavel,"
					+ " celularResponsavel, sexoResponsavel, cpf)"
					+ "values(?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, responsavel.getNomeResponsavel());
			ps.setString(2, responsavel.getCpfResponsavel());
			ps.setString(3, responsavel.getDataNascResponsavel());
			ps.setString(4, responsavel.getIdadeResponsavel());
			ps.setString(5, responsavel.getEmailResponsavel());
			ps.setString(6, responsavel.getCelularResponsavel());
			ps.setString(7, responsavel.getSexoResponsavel());
			ps.setLong(8, responsavel.getCpf());
			ps.executeUpdate();			
		}catch(Exception e){
			throw new Exception("Erro ao Salvar Responsavel.\nMotivo:"+e.getMessage());
		}
	}
	public List ListarTodos() throws Exception{

		List<Responsavel> lista = new ArrayList<Responsavel>();
		try{
			String sql = "SELECT * FROM responsavel ORDER BY nomeResponsavel";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) //Enquanto(percorrer toda a tabela)
			{
				int codResponsavel = rs.getInt("codResponsavel");
				String nomeResponsavel = rs.getString("nomeResponsavel");
				String cpfResponsavel = rs.getString("cpfResponsavel");
				String dataNascResponsavel = rs.getString("dataNascResponsavel");
				String idadeResponsavel = rs.getString("idadeResponsavel");
				String emailResponsavel = rs.getString("emailResponsavel");
				String celularResponsavel = rs.getString("celularResponsavel");
				String sexoResponsavel = rs.getString("sexoResponsavel");
				Long cpf = rs.getLong("cpf");
				responsavel = new Responsavel(codResponsavel, nomeResponsavel, cpfResponsavel, dataNascResponsavel, idadeResponsavel, 
						emailResponsavel, celularResponsavel, sexoResponsavel, cpf);
				lista.add(responsavel);
			}
			return lista;

		}catch(Exception e){
			throw new Exception("Erro ao Listar Responsavel.\nMotivo:"+e.getMessage());
		}
	}
	public void alterar(Responsavel responsavel)throws Exception {
		try {
			String sql = "UPDATE responsavel SET nomeResponsavel=?, cpfResponsavel=?, dataNascResponsavel=?, idadeResponsavel=?, "
					+ "emailResponsavel=?, celularResponsavel=?, sexoResponsavel=?"
					+ "WHERE cpf=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, responsavel.getNomeResponsavel());
			ps.setString(2, responsavel.getCpfResponsavel());
			ps.setString(3, responsavel.getDataNascResponsavel());
			ps.setString(4, responsavel.getIdadeResponsavel());
			ps.setString(5, responsavel.getEmailResponsavel());
			ps.setString(6, responsavel.getCelularResponsavel());
			ps.setString(7, responsavel.getSexoResponsavel());
			ps.setLong(8, responsavel.getCpf());
			ps.executeUpdate();			
		}catch(Exception e){
			throw new Exception("Erro ao Alterar Responsavel.\nMotivo: "+e.getMessage());
		}
	}
	public void excluir(Responsavel responsavel)throws Exception {
		try {
			String sql = "DELETE FROM responsavel "
					+ "WHERE cpf=?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, responsavel.getCpf());
			ps.executeUpdate();			
		}catch(Exception e){
			throw new Exception("Erro ao Excluir Responsavel.\nMotivo: "+e.getMessage());
		}
	}
	public Responsavel consultar(long cpf) throws Exception{
		try{
			String sql = "SELECT * FROM responsavel WHERE cpf = ?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, cpf);
			rs = ps.executeQuery();		
			if(rs.next())
			{
				int codResponsavel = rs.getInt("codResponsavel");
				String nomeResponsavel = rs.getString("nomeResponsavel");
				String cpfResponsavel = rs.getString("cpfResponsavel");
				String dataNascResponsavel = rs.getString("dataNascResponsavel");
				String idadeResponsavel = rs.getString("idadeResponsavel");
				String emailResponsavel = rs.getString("emailResponsavel");
				String celularResponsavel = rs.getString("celularResponsavel");
				String sexoResponsavel = rs.getString("sexoResponsavel");
				responsavel = new Responsavel(codResponsavel, nomeResponsavel, cpfResponsavel, dataNascResponsavel, idadeResponsavel, 
						emailResponsavel, celularResponsavel, sexoResponsavel, cpf);
			}
			return responsavel;

		}catch(Exception e){
			throw new Exception("Erro ao Consultar Responsavel.\nMotivo:"+e.getMessage());
		}
	}
}
