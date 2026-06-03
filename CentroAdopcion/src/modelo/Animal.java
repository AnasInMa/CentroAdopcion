package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import fechas.LibFechas8;

public class Animal implements Comparable<Animal>, Serializable {

	private static final long serialVersionUID = -6601920334084112132L;
	
	private int idAnimal,	//el codigo es el id de la tabla
				idPersona, idCentro;
	private String nombre, tipo, raza, descripcion;
	private byte edad;
	private boolean estaAdoptado;
	private LocalDate fechaAlojamiento, fechaAdopcion;
	
	private String rutaImagen, extension; //la extension es jpg, png, etc

	public Animal(int cod, String nombre, String tipo, String raza, String descripcion, byte edad, String fechaAlojamiento) throws Exception {
		
		this.idAnimal = cod;
		this.nombre = (nombre == null || nombre.isEmpty())? "Sin Nombre" : nombre;
		this.tipo = tipo;
		this.raza = raza;
		this.descripcion = descripcion;
		this.edad = edad;
		
		this.estaAdoptado = false;
		
		if(LibFechas8.isFechaCorrecta(fechaAlojamiento)) {
			
			this.fechaAlojamiento = fechas.LibFechas8.convierteStringToLocalDate(fechaAlojamiento);
			
		} else throw new Exception("Fecha incorrecta");
		
		if(this.extension == null) this.rutaImagen = "./imgs/Animal" + idAnimal + ".png"; //por defecto sera png
	}
	
	public Animal(int cod, String nombre, String tipo, String raza, String descripcion, byte edad, String fechaAlojamiento, int codCentro) throws Exception {
		
		this(cod, nombre, tipo, raza, descripcion, edad, fechaAlojamiento);
		
		this.idCentro = codCentro;
	}
	
	public Animal(int cod, String nombre, String tipo, String raza, String descripcion, byte edad, String fechaAlojamiento, int codCentro, int codPersona) throws Exception {
		
		this(cod, nombre, tipo, raza, descripcion, edad, fechaAlojamiento, codCentro);
		
		this.idPersona = codPersona;
	}

	public Animal(Animal animal, String extensionImagen) throws Exception {
		
		this(animal.idAnimal, animal.nombre, animal.tipo, animal.raza, animal.descripcion, animal.edad, LibFechas8.getFechaShort(animal.fechaAlojamiento), animal.idCentro, animal.idPersona);
		
		this.extension = extensionImagen;
		
		this.rutaImagen = (extensionImagen == null)? null : "./imgs/Animal" + idAnimal + "." + extensionImagen;
		//System.out.println(this.rutaImagen);
	}

	public void esAdoptado(int codPersona) {
		
		idPersona = codPersona;
		estaAdoptado = true;
		fechaAdopcion = LocalDate.now();
	}
	
	public void esAdoptado(Persona persona) {

		esAdoptado(persona.getIDPersona());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idAnimal);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		return idAnimal == other.idAnimal;
	}
	
	@Override
	public int compareTo(Animal animal) {
		
		return this.idAnimal - animal.idAnimal;	//orden 
	}

	@Override
	public String toString() {
		
		return toStringSimple() + "\n\tDescripcion: " + toStringDescripcion() +
				"\n\tEstado: " + (this.estaAdoptado? "Adoptado (" + LibFechas8.getFechaFull(this.fechaAdopcion) + ")"
										: "No adoptado");
	}
	
	public String toStringSimple() {
		
		return "(" + idAnimal + ") " + toStringSinCodigo() + " de " + edad + (edad == 1? " año" : " años");
	}
	
	public String toStringSinCodigo() {
		
		return tipo + " " + raza;
	}
	
	public String toStringDescripcion() {
		
		 return ((nombre == null || nombre.isEmpty())? "Este " + raza.toLowerCase() + " " : nombre + " ") + this.descripcion;
	}
	
	public int getIDAnimal() {
		return idAnimal;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public String getRaza() {
		return raza;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public byte getEdad() {
		return edad;
	}
	
	public LocalDate getFechaAlojamiento() {
		return fechaAlojamiento;
	}

	public int getIDPersona() {
		return idPersona;
	}

	public int getIDCentro() {
		return idCentro;
	}

	public LocalDate getFechaAdopcion() {
		return fechaAdopcion;
	}

	public String getRutaFicheroImagen() {
		return rutaImagen;
	}

	public void setIdCentro(int idCentro) {
		this.idCentro = idCentro;
	}

	public void setIdAnimal(int idAnimal) {
		
		this.idAnimal = idAnimal;
		this.rutaImagen = "./imgs/Animal" + this.idAnimal + this.extension;
	}
}
