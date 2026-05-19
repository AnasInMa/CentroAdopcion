package modelo;

import java.sql.*;

public class CreaYCargaTablas {

	private static final String NOMBREBD;
	private static Connection conexion;
	private static Statement statement;
	
	static {
		
		NOMBREBD = "centroadopcion";
	}
	
	public static void main(String[] args) {
		
		creaConexion("jdbc:mysql://localhost:3306/" + NOMBREBD, "anas", "1234");
	}
	
	private static void creaConexion(String url, String usuario, String contraseña) {
		
		conexion = null;
		
		try {
			
			conexion = DriverManager.getConnection(url, usuario, contraseña);
			statement = conexion.createStatement();
			//System.out.println("Conexion creada");
			
			creaTablaCentros();
			creaTablaPersonas();
			creaTablaAnimales();
			
		} catch (SQLException e) {
			
			System.err.println("Error sql: " + e.getMessage());
			
		} finally {
			
			try {
				
				conexion.close();
				
			} catch (SQLException e1) {
				
				System.err.println("Error sql cierre: " + e1.getMessage());
				
			} catch (NullPointerException e2) {
				
				System.err.println("Conexion no creada");
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
	}
	
	private static void creaTablaCentros() throws SQLException {
		
		String crearTablaCentro = "create table " + NOMBREBD.toUpperCase() + ".CENTROS " +
										"(id_centro INT NOT NULL PRIMARY KEY, " +
										"cod_centro INT(4) NOT NULL," +
										"nombre varchar(40) NOT NULL, " +
										"direccion varchar(80) NOT NULL, " +
										"cod_postal INT(5) NOT NULL, "+
										"capacidad_maxima INT NOT NULL) ";
		
			statement.executeUpdate(crearTablaCentro);
			System.out.println("La tabla CENTROS se ha creado correctamente");
	}
	
	private static void creaTablaPersonas() throws SQLException {
		
		String crearTablaPersona = "create table " + NOMBREBD.toUpperCase() + ".PERSONAS " +
										"(id_persona INT NOT NULL PRIMARY KEY, " +
										"nif varchar(9) NOT NULL," +
										"nombre varchar(40) NOT NULL, " +
										"primer_apellido varchar(60) NOT NULL, " +
										"segundo_apellido varchar(60) NOT NULL, "+
										"edad INT NOT NULL) ";

		statement.executeUpdate(crearTablaPersona);
		System.out.println("La tabla PERSONAS se ha creado correctamente");
	}
	
	private static void creaTablaAnimales() throws SQLException {
		//int cod, String nombre, String tipo, String raza, String descripcion, byte edad, String fechaAlojamiento
		String crearTablaAnimal = "create table " + NOMBREBD.toUpperCase() + ".ANIMALES " +
										"(id_animal INT NOT NULL PRIMARY KEY, " +
										"id_centro INT NOT NULL, " +
										"id_persona INT, " +
										"nombre varchar(40) NOT NULL, " +
										"tipo varchar(60) NOT NULL, " +
										"raza varchar(60) NOT NULL, "+
										"descripcion varchar(280) NOT NULL, "+
										"edad INT NOT NULL, " +
										"fecha_alojamiento DATE NOT NULL, " +
										"fecha_adopcion DATE, " +
										"FOREIGN KEY (id_centro) REFERENCES CENTROS(id_centro), " +
										"FOREIGN KEY (id_persona) REFERENCES PERSONAS(id_persona))";

		statement.executeUpdate(crearTablaAnimal);
		System.out.println("La tabla ANIMALES se ha creado correctamente");
	}
	
	
	
}
