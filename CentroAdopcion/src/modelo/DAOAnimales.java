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
				conexion.prepareStatement("insert into centroadopcion.animales values (?,?,?,?,?,?,?,?,?,?)");

		ps.setInt(1, animal.getIDAnimal());
		ps.setInt(2, animal.getIDCentro());
		
		if(animal.getIDPersona() == 0) {
			
			ps.setNull(3, Types.INTEGER);
			
		} else {
			
			ps.setInt(3, animal.getIDPersona());
		}
		
		ps.setString(4, animal.getNombre());
		ps.setString(5, animal.getTipo());
		ps.setString(6, animal.getRaza());
		ps.setString(7, animal.getDescripcion());
		ps.setInt(8, animal.getEdad());
		ps.setDate(9, Date.valueOf(animal.getFechaAlojamiento()));
		
		if(animal.getFechaAdopcion() == null) {
			
			ps.setNull(10, Types.DATE);
			
		} else {
			
			ps.setDate(10, Date.valueOf(animal.getFechaAdopcion()));
		}
		
		ps.executeUpdate();
		ps.close();
	
		this.crearConsulta();
	}
	
	public int idUltimoAnimal() throws SQLException {

		rsNavegar.last();

		return rsNavegar.getInt("idAnimal");
	}
	
	public void insertaAnimalSinId(Animal animal) throws SQLException {
		
		PreparedStatement ps = 
				conexion.prepareStatement("insert into centroadopcion.animales values (?,?,?,?,?,?,?,?,?,?)");

		ps.setInt(1, this.idUltimoAnimal() + 1);
		ps.setInt(2, animal.getIDCentro());
		ps.setNull(3, Types.INTEGER);
		ps.setString(4, animal.getNombre());
		ps.setString(5, animal.getTipo());
		ps.setString(6, animal.getRaza());
		ps.setString(7, animal.getDescripcion());
		ps.setInt(8, animal.getEdad());
		ps.setDate(9, Date.valueOf(animal.getFechaAlojamiento()));
		ps.setNull(10, Types.INTEGER);
		
		ps.executeUpdate();
		ps.close();
	
		this.crearConsulta();
	}
	
	public void modificaAnimal(Animal animal) throws SQLException {
		
		PreparedStatement ps = conexion.prepareStatement(
				"UPDATE animales SET nombre = ?, tipo = ?, raza = ?, descripcion = ?, "
					+ "edad = ?, fechaAlojamiento = ?, fechaAdopcion = ?, idCentro = ?, idPersona = ? "
					+ "WHERE idAnimal = ?");
		
		ps.setString(1, animal.getNombre());
		ps.setString(2, animal.getTipo());
		ps.setString(3, animal.getRaza());
		ps.setString(4, animal.getDescripcion());
		ps.setInt(5, animal.getEdad());
		ps.setDate(6, Date.valueOf(animal.getFechaAlojamiento()));
		ps.setDate(7, Date.valueOf(animal.getFechaAdopcion()));
		ps.setInt(8, animal.getIDCentro());
		ps.setInt(9, animal.getIDPersona());
		ps.setInt(10, animal.getIDAnimal());
		
		ps.executeUpdate();
		ps.close();
		
		this.crearConsulta();		
	}
	
	public Animal adoptaAnimal(int idAnimal, int idPersona) throws Exception {
		
		Animal animal = null;
		
		PreparedStatement psS = conexion.prepareStatement("SELECT * FROM animales WHERE idAnimal = ?");

		psS.setInt(1, idAnimal);
		rsNavegar = psS.executeQuery();
		
		if (rsNavegar.next()) {

			animal = crearAnimal();
		    animal.esAdoptado(idPersona);
		}
		
	    psS.close();
		
		PreparedStatement ps = 
				conexion.prepareStatement("DELETE FROM animales WHERE idAnimal = ?");
		
		ps.setInt(1, idAnimal);

		ps.executeUpdate();
		ps.close();

		this.crearConsulta();
		
		return animal;
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
	
	public int numAnimales() throws SQLException {
		
		rsNavegar.last();
		
		return rsNavegar.getRow();
	}
	
	public int numAnimalesPorPersona(int idPersona) throws SQLException {

		//System.out.println(idPersona);
		
	    PreparedStatement ps = conexion.prepareStatement("SELECT COUNT(idAnimal) FROM animales WHERE idPersona = ?");
	    ps.setInt(1, idPersona);

	    ResultSet rs = ps.executeQuery();

	    return (rs.next())? rs.getInt(1) : -1; //en caso de que no tenga ningun animal adoptado (o en la base de datos) devuelve -1, porque si no daria un error de que el cursor solo se puede mover hacia delante
	}
	
	public String[][] getMatrizAnimalesPorPersona(int idPersona) throws SQLException, Exception {
		
		//y aqui controlo que me llegue ese -1, y lanzare una exepcion, que despues obtendre el mensaje y lo mostrare al usuario con un JOptionPane
		if(numAnimalesPorPersona(idPersona) <= 0) throw new Exception("Esta persona no tiene ningun animal adoptado registrado");
		
		String[][] animales = new String[numAnimalesPorPersona(idPersona)][10];

		rsNavegar.first();

		Animal animal;

		for (int fila = 0; fila < animales.length; fila++) {

			animal = crearAnimal();
			//int cod, String nombre, String tipo, String raza, String descripcion, byte edad,
			//String fechaAlojamiento, int codCentro, int codPersona
			animales[fila][0] = animal.getIDAnimal() + "";
			animales[fila][1] = animal.getIDCentro() + "";
			animales[fila][2] = animal.getIDPersona() + "";
			animales[fila][3] = animal.getNombre();
			animales[fila][4] = animal.getTipo();
			animales[fila][5] = animal.getRaza();
			animales[fila][6] = animal.getDescripcion();
			animales[fila][7] = animal.getEdad() + "";
			animales[fila][8] = LibFechas8.getFechaShort(animal.getFechaAdopcion());
			animales[fila][9] = LibFechas8.getFechaShort(animal.getFechaAlojamiento());

			rsNavegar.next();
		}
		
		return animales;
	}
	
	public Animal[][] getAllMatrizVista(int idCentro) throws SQLException, Exception {
		
		Animal[][] matrizAnimales = new Animal[(int) Math.ceil(numAnimales() / 4f)][4];
		Animal animal;

		rsNavegar.first();
		
		for (int fila = 0; fila < matrizAnimales.length; fila++) {
			
			for (int columna = 0; columna < matrizAnimales[fila].length; columna++) {
			
			animal = crearAnimal();
			
			if(animal.getIDCentro() == idCentro) {
								
				matrizAnimales[fila][columna] = animal;
				
			} else matrizAnimales[fila][columna] = null;
			
			rsNavegar.next();
			
			}
		}
		
		return matrizAnimales;
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

