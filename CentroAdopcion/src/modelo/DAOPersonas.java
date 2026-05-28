package modelo;

import java.sql.*;
import java.util.*;

/**
 * Esta clase SOLO se utilziara para insertar personas y visualizarlas en un JTable
 */
public class DAOPersonas {
	
	private Connection conexion;   // Objeto con la conexión a la BD
	private Statement statement;   // Objeto que permite ejecutar sentencias SQL
	private ResultSet rsNavegar; // Resultado de la consulta para navegar por las filas de la tabal
	
	public DAOPersonas() throws ClassNotFoundException, SQLException {
		
		this.estableceConexion();  // Dar valor a la variable con (Connection)
		this.crearStatement();  // Dar valor a la variable stmt (Statement)
		this.crearConsulta();  // Dar valor a la variable rsNavegar (ResultSet)
		
	}

	public void estableceConexion() throws ClassNotFoundException, SQLException {
		
		this.conexion = BDConnection.getConnection();
		//System.out.println("Conexión establecida");
	}	
	

	public void crearStatement() throws SQLException {
		
		this.statement = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
										ResultSet.CONCUR_UPDATABLE); 
	}
	
	public void crearConsulta() throws SQLException {
		
		this.rsNavegar = statement.executeQuery("SELECT * FROM personas");
	}
	
	public void cierraConexion() throws SQLException {
		conexion.close();
		System.out.println("Conexión cerrada");
	}
	
	public void cierraStatement() throws SQLException {
		statement.close();
	}
	
	public Persona crearPersona() throws SQLException, Exception {

		//int cod, String nombre, String nif, String apellido1, String apellido2, byte edad
		return new Persona(
					rsNavegar.getInt("idPersona"),
					rsNavegar.getString("nombre"),
					rsNavegar.getString("nif"),
					rsNavegar.getString("primerApellido"),
					rsNavegar.getString("segundoApellido"),
					rsNavegar.getByte("edad"));
	}
	
	public SortedSet<Persona> getAll() throws SQLException, Exception {
		
		rsNavegar.beforeFirst(); // Para posicionar la consulta al principio
		
		SortedSet<Persona> listaPersonas = new TreeSet<>();

		while (rsNavegar.next()) {
			listaPersonas.add(crearPersona());
		}

		rsNavegar.beforeFirst();
		
		return listaPersonas;
	}

}
