package modelo;

import java.io.*;
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
	
	private static void eliminaTablas() throws SQLException {
		
		PreparedStatement borrar;
		
		borrar = conexion.prepareStatement("DROP TABLE animales");
		borrar.executeUpdate();
		
		borrar = conexion.prepareStatement("DROP TABLE centros");
		borrar.executeUpdate();
		
		borrar = conexion.prepareStatement("DROP TABLE personas");
		borrar.executeUpdate();
	}
	
	private static void creaConexion(String url, String usuario, String contraseña) {
		
		conexion = null;
				
		try {
			
			conexion = DriverManager.getConnection(url, usuario, contraseña);
			statement = conexion.createStatement();
			//System.out.println("Conexion creada");			
			
			eliminaTablas();
			
			//crear las tablas
			creaTablaCentros();
			creaTablaPersonas();
			creaTablaAnimales();
			
			//cargar las tablas
			cargaTablaCentros();
			cargaTablaPersonas();
			cargaTablaAnimales();
			
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
	
	//
	private static void creaTablaCentros() throws SQLException {
		
		String crearTablaCentro = "create table " + NOMBREBD.toUpperCase() + ".CENTROS " +
										"(codigo INT NOT NULL PRIMARY KEY, " +
										"codigoCentro INT(4) NOT NULL," +
										"nombre varchar(40) NOT NULL, " +
										"direccion varchar(80) NOT NULL, " +
										"codigoPostal INT(5) NOT NULL, "+
										"capacidadMaxima INT NOT NULL) ";
		
			statement.executeUpdate(crearTablaCentro);
			System.out.println("La tabla CENTROS se ha creado correctamente");
	}
	
	private static void cargaTablaCentros() throws SQLException {
		
		CentroAdopcion centro = null;
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("./files/Centros.dat")))) {

			String sql;
			
			while (true) {

				sql = "INSERT INTO centros VALUES (?,?,?,?,?,?)";
				
				centro = (CentroAdopcion) ois.readObject();
				
				try (PreparedStatement ps = conexion.prepareStatement(sql)) {

					ps.setInt(1, centro.getCodigo());
					ps.setInt(2, centro.getCodigoCentro());
					ps.setString(3, centro.getNombre());
					ps.setString(4, centro.getDireccion());
					ps.setInt(5, centro.getCodigoPostal());
					ps.setInt(6, centro.getCapacidadMaxima());

					ps.executeUpdate();
				}
			}
			
		} catch(EOFException e) {
			
			System.out.println("datos de los centros cargados correctamente");
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	//
	private static void creaTablaPersonas() throws SQLException {
		
		String crearTablaPersona = "create table " + NOMBREBD.toUpperCase() + ".PERSONAS " +
										"(codigo INT NOT NULL PRIMARY KEY, " +
										"nif varchar(9) NOT NULL," +
										"nombre varchar(40) NOT NULL, " +
										"primerApellido varchar(60) NOT NULL, " +
										"segundoApellido varchar(60) NOT NULL, "+
										"edad INT NOT NULL) ";

		statement.executeUpdate(crearTablaPersona);
		System.out.println("La tabla PERSONAS se ha creado correctamente");
	}
	
	private static void cargaTablaPersonas() throws SQLException {
		
		Persona persona;
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("./files/Personas.dat")))) {

			String sql;
			
			while (true) {

				sql = "INSERT INTO personas VALUES (?,?,?,?,?,?)";
				
				persona = (Persona) ois.readObject();
				
				try (PreparedStatement ps = conexion.prepareStatement(sql)) {

					ps.setInt(1, persona.getCodigo());
					ps.setString(2, persona.getNombre());
					ps.setString(3, persona.getNif());
					ps.setString(4, persona.getPrimerApellido());
					ps.setString(5, persona.getSegundoApellido());
					ps.setInt(6, persona.getEdad());

					ps.executeUpdate();
				}
			}
			
		} catch(EOFException e) {
			
			System.out.println("datos de las personas cargados correctamente");
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	//
	private static void creaTablaAnimales() throws SQLException {
		//int cod, String nombre, String tipo, String raza, String descripcion, byte edad, String fechaAlojamiento
		String crearTablaAnimal = "create table " + NOMBREBD.toUpperCase() + ".ANIMALES " +
										"(codigo INT NOT NULL PRIMARY KEY, " +
										"codCentro INT NOT NULL, " +
										"codPersona INT, " +
										"nombre varchar(40) NOT NULL, " +
										"tipo varchar(60) NOT NULL, " +
										"raza varchar(60) NOT NULL, "+
										"descripcion varchar(280) NOT NULL, "+
										"edad INT NOT NULL, " +
										"fechaAlojamiento DATE NOT NULL, " +
										"fechaAdopcion DATE, " +
										"FOREIGN KEY (codCentro) REFERENCES CENTROS(codigo), " +
										"FOREIGN KEY (codPersona) REFERENCES PERSONAS(codigo))";

		statement.executeUpdate(crearTablaAnimal);
		System.out.println("La tabla ANIMALES se ha creado correctamente");
	}
	
	private static void cargaTablaAnimales() throws SQLException {
		
		Animal animal;
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("./files/Animales.dat")))) {

			String sql;
			
			while (true) {

				sql = "INSERT INTO animales VALUES (?,?,?,?,?,?,?,?,?,?)";
				
				animal = (Animal) ois.readObject();
				
				try (PreparedStatement ps = conexion.prepareStatement(sql)) {

					ps.setInt(1, animal.getCodigo());
					ps.setInt(2, animal.getCodigoCentro());
					ps.setInt(3, animal.getCodigoPersona());
					ps.setString(4, animal.getNombre());
					ps.setString(5, animal.getTipo());
					ps.setString(6, animal.getRaza());
					ps.setString(7, animal.getDescripcion());
					ps.setInt(8, animal.getEdad());
					ps.setDate(9, Date.valueOf(animal.getFechaAlojamiento()));
					ps.setDate(10, (animal.getFechaAdopcion() == null)? null : Date.valueOf(animal.getFechaAdopcion()));

					ps.executeUpdate();
				}
			}
			
		} catch(EOFException e) {
			
			System.out.println("datos de los animales cargados correctamente");
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
}
