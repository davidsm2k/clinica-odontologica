package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Prontuario;
import util.ConnectionFactory;

public class ProntuarioDAO {
	
	private Prontuario prontuario;
	private Connection conn; //Conecta ao Banco de Dados
	private PreparedStatement ps; // Permite Exeutar um comando SQL
	private ResultSet rs; // O mesmo que uma tabela
	
	public ProntuarioDAO () throws Exception{
		try {
			conn = ConnectionFactory.getConnection();
		}catch(Exception e){
			throw new Exception("Erro: "+ e.getMessage());
		}
	}
	
	public void salvar(Prontuario prontuario) throws Exception {
		try {
			String sql = "INSERT INTO prontuario(codProntuario, tipoParecer, dataParecer, horaParecer, obs, cpf)"
					+ "values(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, prontuario.getCodProntuario());
			ps.setString(2, prontuario.getTipoParecer());
			ps.setString(3, prontuario.getDataParecer());
			ps.setString(4, prontuario.getHoraParecer());
			ps.setString(5, prontuario.getObs());
			ps.setLong(6, prontuario.getCpf());
			ps.executeUpdate();			
		}catch(Exception e){
			throw new Exception("Erro ao Salvar Prontuario.\nMotivo:"+e.getMessage());
		}
	}
	
	public void excluir(Prontuario prontuario) throws Exception{
		try {
			String sql = "DELETE FROM prontuario "
					+ "WHERE cpf=? AND dataParecer=? AND horaParecer=?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, prontuario.getCpf());
			ps.setString(2, prontuario.getDataParecer());
			ps.setString(3,prontuario.getHoraParecer());
			ps.executeUpdate();			
		}catch(Exception e){
			throw new Exception("Erro ao Excluir Prontuario.\nMotivo: "+e.getMessage());
		}
	}
	
	public Prontuario consultar(long cpf)throws Exception{
		try{
			ps = conn.prepareStatement("SELECT * FROM prontuario WHERE cpf=?");
			ps.setLong(1, cpf);
			rs = ps.executeQuery();	
			if(rs.next()){
				int codProntuario = rs.getInt("codProntuario");
				String tipoParecer = rs.getString("tipoParecer");
				String dataParecer = rs.getString("dataParecer");
				String horaParecer = rs.getString("horaParecer");
				String obs = rs.getString("obs");
				prontuario = new Prontuario(codProntuario, tipoParecer, dataParecer, horaParecer, obs, cpf);
			}
			return prontuario;
		}catch(Exception e){
			throw new Exception("Erro ao Consultar Prontuario.\nMotivo:"+e.getMessage());
		}
	}
	public List ListarTodos() throws Exception{

		List<Prontuario> lista = new ArrayList<Prontuario>();
		try{
			String sql = "SELECT * FROM prontuario";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) //Enquanto(percorrer toda a tabela)
			{
				int codProntuario = rs.getInt("codProntuario");
				String tipoParecer = rs.getString("tipoParecer");
				String dataParecer = rs.getString("dataParecer");
				String horaParecer = rs.getString("horaParecer");
				String obs = rs.getString("obs");
				long cpf = rs.getLong("cpf");
				prontuario = new Prontuario(codProntuario, tipoParecer, dataParecer, horaParecer, obs, cpf);
				lista.add(prontuario);
			}
			return lista;

		}catch(Exception e){
			throw new Exception("Erro ao Listar Prontuario.\nMotivo:"+e.getMessage());
		}
	}
}
