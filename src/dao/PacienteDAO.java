package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Paciente;
import util.ConnectionFactory;

public class PacienteDAO { // CRUD

	private Paciente paciente;
	private Connection conn; //Conecta ao Banco de Dados
	private PreparedStatement ps; // Permite Exeutar um comando SQL
	private ResultSet rs; // O mesmo que uma tabela

	public PacienteDAO() throws Exception {
		try {
			conn = ConnectionFactory.getConnection();
		}catch(Exception e){
			throw new Exception("Erro: "+ e.getMessage());
		}
	}

	public void salvar(Paciente paciente)throws Exception {
		try {
			String sql = "INSERT INTO paciente(cpf, nome, dataNasc, idade, email, celular, sexo)"
					+ "values(?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, paciente.getCpf());
			ps.setString(2, paciente.getNome());
			ps.setString(3, paciente.getDataNasc());
			ps.setString(4, paciente.getIdade());
			ps.setString(5, paciente.getEmail());
			ps.setString(6, paciente.getCelular());
			ps.setString(7, paciente.getSexo());
			ps.executeUpdate();			
		}catch(Exception e){
			throw new Exception("Erro ao Salvar Paciente.\nMotivo:"+e.getMessage());
		}
	}
	public List ListarTodos() throws Exception{

		List<Paciente> lista = new ArrayList<Paciente>();
		try{
			String sql = "SELECT * FROM paciente ORDER BY nome";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) //Enquanto(percorrer toda a tabela)
			{
				long cpf = rs.getLong("cpf");
				String nome = rs.getString("nome");
				String dataNasc = rs.getString("dataNasc");
				String idade = rs.getString("idade");
				String email = rs.getString("email");
				String celular = rs.getString("celular");
				String sexo = rs.getString("sexo");
				paciente = new Paciente(cpf, nome, dataNasc, idade, email, celular, sexo);
				lista.add(paciente);
			}
			return lista;

		}catch(Exception e){
			throw new Exception("Erro ao Listar Paciente.\nMotivo:"+e.getMessage());
		}
	}
	public void alterar(Paciente paciente)throws Exception {
		try {
			String sql = "UPDATE paciente SET nome=?, dataNasc=?, idade=?, email=?, celular=?, sexo=?"
					+ "WHERE cpf=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, paciente.getNome());
			ps.setString(2, paciente.getDataNasc());
			ps.setString(3, paciente.getIdade());
			ps.setString(4, paciente.getEmail());
			ps.setString(5, paciente.getCelular());
			ps.setString(6, paciente.getSexo());
			ps.setLong(7, paciente.getCpf());
			ps.executeUpdate();			
		}catch(Exception e){
			throw new Exception("Erro ao Alterar Paciente.\nMotivo: "+e.getMessage());
		}
	}
	public void excluir(Paciente paciente)throws Exception {
		try {
			String sql = "DELETE FROM paciente "
					+ "WHERE cpf=?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, paciente.getCpf());
			ps.executeUpdate();			
		}catch(Exception e){
			throw new Exception("Erro ao Excluir Paciente.\nMotivo: "+e.getMessage());
		}
	}
	public Paciente consultar(long cpf) throws Exception{
		try{
			ps = conn.prepareStatement("SELECT * FROM paciente WHERE cpf=?");
			ps.setLong(1, cpf);
			rs = ps.executeQuery();	
			if(rs.next()){
				String nome = rs.getString("nome");
				String dataNasc = rs.getString("dataNasc");
				String idade = rs.getString("idade");
				String email = rs.getString("email");
				String celular = rs.getString("celular");
				String sexo = rs.getString("sexo");
				paciente = new Paciente(cpf, nome, dataNasc, idade, email, celular, sexo);
			}
			return paciente;
		}catch(Exception e){
			throw new Exception("Erro ao Consultar Paciente.\nMotivo:"+e.getMessage());
		}
	}
}
