package modelo;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import fechas.LibFechas8;

public class DAOAnimales {

	private Connection conexion;   // Objeto con la conexión a la BD
	private Statement statement;   // Objeto que permite ejecutar sentencias SQL
	private ResultSet rsNavegar; // Resultado de la consulta para navegar por las filas de la tabal
	
	public DAOAnimales() throws ClassNotFoundException, SQLException {
		
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
		
		this.rsNavegar = statement.executeQuery("SELECT * FROM animales");
	}
	
	public void cierraConexion() throws SQLException {
		conexion.close();
		System.out.println("Conexión cerrada");
	}
	
	public void cierraStatement() throws SQLException {
		statement.close();
	}
	
	public Animal crearAnimal() throws SQLException, Exception {

		return new Animal(
					rsNavegar.getInt("idAnimal"),
					rsNavegar.getString("nombre"),
					rsNavegar.getString("tipo"),
					rsNavegar.getString("raza"),
					rsNavegar.getString("descripcion"),
					rsNavegar.getByte("edad"),
					LibFechas8.transformaFecha(
							rsNavegar.getDate("fechaAlojamiento").toLocalDate().toString()),
					rsNavegar.getInt("idCentro"));
	}

	public Animal getPrimero() throws SQLException, Exception {
		
		this.rsNavegar.first();
		return this.crearAnimal();
	}

	public Animal getUltimo() throws SQLException, Exception {
		
		rsNavegar.last();
		return this.crearAnimal();
	}

	public Animal getSiguiente() throws SQLException, Exception {
		
		rsNavegar.next();
		return this.crearAnimal();
	}

	public Animal getAnterior() throws SQLException, Exception {
		
		rsNavegar.previous();
		return this.crearAnimal();
	}
	
	/**
	 * Metodo que devuelve una matriz de los cuatro  primeros animales,
	 * pero antes comprueba que hayan cuatro animales, si no
	 * simplemente devuelve un array en funcion de los animales que hayan
	 * @return Animal[]
	 * @throws SQLException
	 * @throws Exception
	 */
	public Animal[] getCuatroPrimeros() throws SQLException, Exception {		
		
		rsNavegar.first();
		
		Animal[] animales = new Animal[4];
		Animal animal;
		boolean quedanAnimales = true;
		
		for(int i = 0; i < 4 && quedanAnimales; i++) {
						
			animal = this.crearAnimal();
						
			if(animal != null) {
				
				animales[i] = animal;
				
				rsNavegar.next();
				
			} else quedanAnimales = false;
		}
		
		return animales;
	}
	
	/**
	 * Metodo que devuelve una matriz de los cuatro animales siguientes,
	 * pero antes comprueba que hayan cuatro animales, si no
	 * simplemente devuelve un array en funcion de los animales que hayan
	 * @return Animal[]
	 * @throws SQLException
	 * @throws Exception
	 */
	public Animal[] getCuatroSiguientes() throws SQLException, Exception {		
		
		Animal[] animales = new Animal[4];
		Animal animal;
		boolean quedanAnimales = true;
		
		for(int i = 0; i < 4 && quedanAnimales; i++) {
			
			rsNavegar.next();
			
			animal = this.crearAnimal();
			
			if(animal != null) {
				
				animales[i] = animal;
				
			} else quedanAnimales = false;
		}
		
		return animales;
	}
	
	/**
	 * Metodo que devuelve una matriz de los cuatro animales anteriores,
	 * pero antes comprueba que hayan cuatro animales, si no
	 * simplemente devuelve un array en funcion de los animales que hayan
	 * @return Animal[]
	 * @throws SQLException
	 * @throws Exception
	 */
	public Animal[] getCuatroAnteriores() throws SQLException, Exception {		
		
		Animal[] animales = new Animal[4];
		Animal animal;
		boolean quedanAnimales = true;
		
		for(int i = 0; i < 4 && quedanAnimales; i++) {
						
			animal = this.crearAnimal();
						
			if(animal != null) {
				
				animales[i] = animal;
				
				rsNavegar.previous();
				
			} else quedanAnimales = false;
		}
		
		return animales;
	}
	
	/**
	 * Metodo que devuelve una matriz de los cuatro ultimos animales,
	 * pero antes comprueba que hayan cuatro animales, si no
	 * simplemente devuelve un array en funcion de los animales que hayan
	 * @return Animal[]
	 * @throws SQLException
	 * @throws Exception
	 */
	public Animal[] getCuatroUltimos() throws SQLException, Exception {		
		
		rsNavegar.last();
		
		Animal[] animales = new Animal[4];
		Animal animal;
		boolean quedanAnimales = true;
		
		for(int i = 0; i < 4 && quedanAnimales; i++) {
						
			animal = this.crearAnimal();
						
			if(animal != null) {
				
				animales[i] = animal;
				
				rsNavegar.previous();
				
			} else quedanAnimales = false;
		}
		
		return animales;
	}
	
	public void insertaAnimal(Animal animal) throws SQLException {
		
		PreparedStatement ps = 
				conexion.prepareStatement("insert into centroadopcion.animales values (?,?,?,?,?,?,?,?,?)");

		ps.setInt(1, animal.getIDAnimal());
		ps.setString(2, animal.getNombre());
		ps.setString(3, animal.getTipo());
		ps.setString(4, animal.getRaza());
		ps.setString(5, animal.getDescripcion());
		ps.setInt(6, animal.getEdad());
		ps.setDate(7, Date.valueOf(animal.getFechaAlojamiento()));
		ps.setInt(8, animal.getIDCentro());
		ps.setInt(9, animal.getIDPersona());
		
		ps.executeUpdate();
		ps.close();
	
		this.crearConsulta();
	}
	
	public void modificaAnimal(Animal animal) throws SQLException {
		
		PreparedStatement ps = conexion.prepareStatement(
				"UPDATE animales SET nombre = ?, tipo = ?, raza = ?, descripcion = ?, "
					+ "edad = ?, fechaAlojamiento = ?, codCentro = ?, codPersona = ?"
					+ "WHERE idAnimal = ?");
		
		ps.setString(1, animal.getNombre());
		ps.setString(2, animal.getTipo());
		ps.setString(3, animal.getRaza());
		ps.setString(4, animal.getDescripcion());
		ps.setInt(5, animal.getEdad());
		ps.setDate(6, Date.valueOf(animal.getFechaAlojamiento()));
		ps.setInt(7, animal.getIDCentro());
		ps.setInt(8, animal.getIDPersona());
		ps.setInt(9, animal.getIDAnimal());
		
		ps.executeUpdate();
		ps.close();
		
		this.crearConsulta();		
	}
	
	public void adoptaAnimal(int cod) throws SQLException{
		PreparedStatement ps = 
				conexion.prepareStatement("DELETE FROM animales WHERE idAnimal = ?");
		
		ps.setInt(1, cod);

		ps.executeUpdate();
		ps.close();

		this.crearConsulta();
	}
	
	public SortedSet<Animal> getAnimalesCentro(CentroAdopcion centro) throws SQLException, Exception {
		
		rsNavegar.beforeFirst(); // Para posicionar la consulta al principio
		
		SortedSet<Animal> listaAnimales = new TreeSet<>();

		while (rsNavegar.next()) {
			
			if(rsNavegar.getInt("idCentro") == centro.getIDCentro()) {
				
				listaAnimales.add(crearAnimal());	
			}
		}

		rsNavegar.beforeFirst();
		
		return listaAnimales;
	}

	public SortedSet<Animal> getAll() throws SQLException, Exception {
	
		rsNavegar.beforeFirst(); // Para posicionar la consulta al principio
		
		SortedSet<Animal> listaAnimales = new TreeSet<>();

		while (rsNavegar.next()) {
			listaAnimales.add(crearAnimal());
		}

		rsNavegar.beforeFirst();
		
		return listaAnimales;
	}
	
	public Animal buscaAnimal(int cod) throws SQLException, Exception {

		PreparedStatement ps = conexion.prepareStatement("SELECT * FROM animales WHERE idAnimal = ?");
		ps.setInt(1, cod);

		ResultSet rs = ps.executeQuery();

		Animal animal = null;

		if (rs.next()) {
			animal = 
					new Animal(
							rs.getInt("idAnimal"),
							rs.getString("nombre"),
							rs.getString("tipo"),
							rs.getString("raza"),
							rs.getString("descripcion"),
							rs.getByte("edad"),
							LibFechas8.transformaFecha(
									rs.getDate("fechaAlojamiento").toLocalDate().toString()),
							rs.getInt("idCentro"));
		}

		rs.close();
		ps.close();

		return animal;

	}
	
	/**
	 * Método que devuelve una matriz con una matriz que contiene el 
	 * resultado de una consulta
	 * @return
	 * @throws SQLException
	 */
	/*public String[][] datosConsulta1() throws SQLException{
		
		// Crear la consulta, mejor parametrizarla si la fecha se le va a pedir al usuario
		java.sql.Date fechaAdopcion = Date.valueOf(LocalDate.of(2010, 01, 01));
		String consulta = "select * from animales where fechaAdopcion >'"+ fechaAdopcion+ "'";
	
		ResultSet rsConsulta = null;  
		rsConsulta = this.stmt.executeQuery(consulta);
		
		// Averiguar el numero de filas devueltas
		rsConsulta.last();
		int numFilas = rsConsulta.getRow();
		rsConsulta.first();
		
		// Ir volcando cada fila en una fila de la matriz
		String [][]datos = new String[numFilas][5];
		
		for(int fila = 0; fila < numFilas; fila++){
			datos[fila][0] = Integer.toString(rsConsulta.getInt("codigo"));
			datos[fila][1] = rsConsulta.getString("titulo");
			datos[fila][2] = rsConsulta.getString("autor");
			datos[fila][3] = LibFechas8.transformaFecha(
									rsConsulta.getDate("fechaPubli").toLocalDate().toString());
			datos[fila][4] = Double.toString(rsConsulta.getDouble("precio"));
			
			rsConsulta.next();
		}
		
		this.crearConsulta();
		
		return datos;
	}*/
	
}

