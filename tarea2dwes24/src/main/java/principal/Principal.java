package principal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import com.mysql.cj.jdbc.MysqlDataSource;

import modelo.*;

public class Principal {

	public static void main(String[] args) {
		System.out.println("INI");

		Scanner in = new Scanner(System.in);
		System.out.println("Dame el codigo de la nueva Planta:");
		String codigo = in.nextLine().trim().toUpperCase();
		System.out.println("Dame el nombre común de la nueva planta");
		String nombre_comun = in.nextLine();
		System.out.println("Dame el nombre cientifico de la nueva planta");
		String nombre_cientifico = in.nextLine();
		
		Planta nueva = new Planta(codigo, nombre_comun, nombre_cientifico);
		
		Connection con;
		MysqlDataSource m = new MysqlDataSource();
		Properties prop = null;
		FileInputStream fis;
		
		String url ;
		String usuario ;
		String password;
		
		try {
			
			fis = new FileInputStream("src/main/resources/db.properties");
			prop.load(fis);
			url = prop.getProperty("url");
			usuario = prop.getProperty("usuario");
			password = prop.getProperty("password");
			m.setUrl(url);
			m.setUser(usuario);
			m.setPassword(password);
			
			con = m.getConnection();
			
			String sql = "INSERT INTO plantas(codigo, nombrecomun, nombrecientifico) VALUES ('"+nueva.getCodigo()+"' , '"+nueva.getNombrecomun()+"', '"+nueva.getNombrecientifico()+"')";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.execute();
			
			con.close();
			
		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException : " +e.getLocalizedMessage());
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("Se ha producido una FileNotFoundException : " +e.getLocalizedMessage());			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Se ha producido una IOException : " +e.getLocalizedMessage());	
			e.printStackTrace();
		}
		
		
		System.out.println("FIN");
		
		System.out.println("FIN");
	}

}
