package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Endereco;
import util.ConnectionFactory;

public class EnderecoDAO {
	private Endereco endereco;
	private Connection conn; //Conecta ao Banco de Dados
	private PreparedStatement ps; // Permite Exeutar um comando SQL
	private ResultSet rs; // O mesmo que uma tabela

	public EnderecoDAO() throws Exception {
		try {
			conn = ConnectionFactory.getConnection();
		}catch(Exception e){
			throw new Exception("Erro Endereco: "+ e.getMessage());
		}
	}

	public void salvar(Endereco endereco)throws Exception {
		try {
			String sql = "INSERT INTO endereco(cep, rua, numeroCasa, bairro, cidade, estado, cpf)"
					+ "values(?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, endereco.getCep());
			ps.setString(2, endereco.getRua());
			ps.setString(3, endereco.getNumeroCasa());
			ps.setString(4, endereco.getBairro());
			ps.setString(5, endereco.getCidade());
			ps.setString(6, endereco.getEstado());
			ps.setLong(7, endereco.getCpf());
			ps.executeUpdate();			
		}catch(Exception e){
			throw new Exception("Erro ao Salvar Endereco.\nMotivo:"+e.getMessage());
		}
	}
	public List ListarTodos() throws Exception{

		List<Endereco> lista = new ArrayList<Endereco>();
		try{
			String sql = "SELECT * FROM endereco";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) //Enquanto(percorrer toda a tabela)
			{
				int codEndereco = rs.getInt("codEndereco");
				String cep = rs.getString("cep");
				String rua = rs.getString("rua");
				String numeroCasa = rs.getString("numeroCasa");
				String bairro = rs.getString("bairro");
				String cidade = rs.getString("cidade");
				String estado = rs.getString("estado");
				long cpf = rs.getLong("cpf");
				endereco = new Endereco(codEndereco, cep, rua, 
						numeroCasa, bairro, cidade, estado, cpf);
				lista.add(endereco);
			}
			return lista;

		}catch(Exception e){
			throw new Exception("Erro ao Listar Endereco.\nMotivo:"+e.getMessage());
		}
	}
	public void alterar(Endereco endereco)throws Exception {
		try {
			String sql = "UPDATE endereco SET cep=?, rua=?, numeroCasa=?, bairro=?, cidade=?, estado=?"
					+ "WHERE cpf=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, endereco.getCep());
			ps.setString(2, endereco.getRua());
			ps.setString(3, endereco.getNumeroCasa());
			ps.setString(4, endereco.getBairro());
			ps.setString(5, endereco.getCidade());
			ps.setString(6, endereco.getEstado());
			ps.setLong(7, endereco.getCpf());
			ps.executeUpdate();			
		}catch(Exception e){
			throw new Exception("Erro ao Alterar Endereco.\nMotivo: "+e.getMessage());
		}
	}
	public void excluir(Endereco endereco)throws Exception {
		try {
			String sql = "DELETE FROM endereco "
					+ "WHERE cpf=?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, endereco.getCpf());
			ps.executeUpdate();			
		}catch(Exception e){
			throw new Exception("Erro ao Excluir Endereco.\nMotivo: "+e.getMessage());
		}
	}
	public Endereco consultar(long cpf) throws Exception{

		try{
			String sql = "SELECT * FROM endereco WHERE cpf = ?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, cpf);
			rs = ps.executeQuery();		
			if(rs.next())
			{
				int codEndereco = rs.getInt("codEndereco");
				String cep = rs.getString("cep");
				String rua = rs.getString("rua");
				String numeroCasa = rs.getString("numeroCasa");
				String bairro = rs.getString("bairro");
				String cidade = rs.getString("cidade");
				String estado = rs.getString("estado");
				endereco = new Endereco(codEndereco, cep, rua, numeroCasa, bairro, cidade, estado, cpf);
			}
			return endereco;

		}catch(Exception e){
			throw new Exception("Erro ao Consultar Endereco.\nMotivo:"+e.getMessage());
		}
	}
}
