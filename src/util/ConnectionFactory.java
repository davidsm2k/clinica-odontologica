package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	public static Connection getConnection() throws Exception{ //throws Exception -> Joga o erro pra quem a chamou.
		try {
			//Informar o nome do BANCO de DADOS
			String nomeBD = "oydconsult";
			
			//qual é o banco e o driver
			Class.forName("com.mysql.jdbc.Driver");
			String login = "root";
			String senha = "";		
			String url = "jdbc:mysql://localhost:3306/" + nomeBD;
			return DriverManager.getConnection(url,login,senha);
		}
		catch(Exception e) {
			//fica somente em quanto está sendo desenvolvido para mostrar os erros
			throw new Exception(e.getMessage());		
		}
	}
}
