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
	
	public int numPersonas() throws SQLException {
		
		return statement.executeQuery("SELECT COUNT(idPersona) FROM personas").getInt("idPersona");
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
	
	public void insertaPersona(Persona persona) throws SQLException {
		
		PreparedStatement ps = 
				conexion.prepareStatement("insert into centroadopcion.personas values (?,?,?,?,?,?)");

		ps.setInt(1, persona.getIDPersona());
		ps.setString(2, persona.getNombre());
		ps.setString(3, persona.getNif());
		ps.setString(4, persona.getPrimerApellido());
		ps.setString(5, persona.getSegundoApellido());
		ps.setInt(6, persona.getEdad());
		
		ps.executeUpdate();
		ps.close();
	
		this.crearConsulta();
	}
	
	public String[][] getAllMatriz() throws SQLException, Exception {
		
		rsNavegar.first();
		
		Persona persona;
		
		String[][] matrizPersonas = new String[numPersonas()][6];
		
		for (int fila = 0; fila < matrizPersonas.length; fila++) {
			
			persona = crearPersona();
			
			matrizPersonas[fila][0] = persona.getIDPersona() + "";
			matrizPersonas[fila][1] = persona.getNombre();
			matrizPersonas[fila][2] = persona.getNif();
			matrizPersonas[fila][3] = persona.getPrimerApellido();
			matrizPersonas[fila][4] = persona.getSegundoApellido();
			matrizPersonas[fila][5] = persona.getEdad() + "";
			
			rsNavegar.next();
		}
		
		return matrizPersonas;
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
