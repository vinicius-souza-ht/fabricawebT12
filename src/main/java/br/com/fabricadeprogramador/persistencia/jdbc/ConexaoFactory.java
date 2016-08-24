package br.com.fabricadeprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

	public static Connection getConnection(){
		try{
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/fabricaweb_db", "postgres", "postgres");
		} catch(SQLException e){
			throw new RuntimeException("Não conectou com o banco!", e);
		}
	}
}