package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	private static final String USERNAME = "postgres";
	
	private static final String PASSWORD = "123456";
	
	private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/agenda";
	
	public static Connection createConnectionToPostgres() throws Exception{
		Connection con = DriverManager.getConnection(DATABASE_URL,USERNAME,PASSWORD);
	    return con;
	}
	
	public static void main(String[] args) throws Exception{ 
		//Cria conexão com o banco
		Connection con = createConnectionToPostgres();
	 
		//Testa conexão
		if(con != null){
			System.out.println("Conexão obtida com sucesso!" + con);
			con.close();
		}
	}

}
