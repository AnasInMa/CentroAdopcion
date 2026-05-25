package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.swing.ImageIcon;

import fechas.LibFechas8;

public class Animal implements Comparable<Animal>, Serializable{

	private static final long serialVersionUID = -6601920334084112132L;
	
	private int codigo,	//el codigo es el id de la tabla
				codigoPersona, codigoCentro;
	private String nombre, tipo, raza, descripcion;
	private byte edad;
	private boolean estaAdoptado;
	private LocalDate fechaAlojamiento, fechaAdopcion;
	
	private ImageIcon imagen;

	public Animal(int cod, String nombre, String tipo, String raza, String descripcion, byte edad, String fechaAlojamiento) throws Exception {
		
		this.codigo = cod;
		this.nombre = (nombre.isEmpty() || nombre == null)? "Sin Nombre" : nombre;
		this.tipo = tipo;
		this.raza = raza;
		this.descripcion = (nombre == null || nombre == ""? "Este " + raza.toLowerCase() + " " : nombre + " ") + descripcion;
		this.edad = edad;
		
		this.estaAdoptado = false;
		
		if(LibFechas8.isFechaCorrecta(fechaAlojamiento)) {
			
			this.fechaAlojamiento = fechas.LibFechas8.convierteStringToLocalDate(fechaAlojamiento);
			
		} else throw new Exception("Fecha incorrecta");
	}
	
	public Animal(int cod, String nombre, String tipo, String raza, String descripcion, byte edad, String fechaAlojamiento, int codCentro) throws Exception {
		
		this(cod, nombre, tipo, raza, descripcion, edad, fechaAlojamiento);
		
		this.codigoCentro = codCentro;
	}
	
	public Animal(int cod, String nombre, String tipo, String raza, String descripcion, byte edad, String fechaAlojamiento, int codCentro, int codPersona) throws Exception {
		
		this(cod, nombre, tipo, raza, descripcion, edad, fechaAlojamiento, codCentro);
		
		this.codigoPersona = codPersona;
	}
	
	public Animal(Animal animal, ImageIcon imagen) throws Exception {
		
		this(animal.codigo, animal.nombre, animal.tipo, animal.raza, animal.descripcion, animal.edad, LibFechas8.getFechaFull(animal.fechaAlojamiento), animal.codigoCentro, animal.codigoCentro);
		
		this.imagen = imagen;
		
	}
	
	public void esAdoptado(int codPersona) {
		
		codigoPersona = codPersona;
		estaAdoptado = true;
		fechaAdopcion = LocalDate.now();
	}
	
	public void esAdoptado(Persona persona) {

		esAdoptado(persona.getCodigo());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(codigo);
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
		return codigo == other.codigo;
	}
	
	@Override
	public int compareTo(Animal animal) {
		
		return this.codigo - animal.codigo;	//orden 
	}

	@Override
	public String toString() {
		
		return toStringSimple() + "\n\tDescripcion: " + descripcion +
				"\n\tEstado: " + (this.estaAdoptado? "Adoptado (" + LibFechas8.getFechaFull(this.fechaAdopcion) + ")"
										: "No adoptado");
	}
	
	public String toStringSimple() {
		
		return "(" + codigo + ") " + toStringSinCodigo() + " de " + edad + (edad == 1? " año" : " años");
	}
	
	public String toStringSinCodigo() {
		
		return tipo + " " + raza;
	}
	
	public int getCodigo() {
		return codigo;
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

	public int getCodigoPersona() {
		return codigoPersona;
	}

	public int getCodigoCentro() {
		return codigoCentro;
	}

	public LocalDate getFechaAdopcion() {
		return fechaAdopcion;
	}
	
	public ImageIcon getImagen() {
		return imagen;
	}
	
}
