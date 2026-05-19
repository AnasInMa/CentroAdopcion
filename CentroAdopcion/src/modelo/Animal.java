package modelo;

import java.time.LocalDate;
import java.util.Objects;

import fechas.LibFechas8;

public class Animal implements Comparable<Animal>{

	private int codigo;	//el codigo es el id de la tabla
	private String nombre, tipo, raza, descripcion;
	private byte edad;
	private boolean estaAdoptado;
	private LocalDate fechaAlojamiento, fechaAdopcion;
	
	public Animal(int cod, String nombre, String tipo, String raza, String descripcion, byte edad, String fechaAlojamiento) throws Exception {
		
		this.codigo = cod;
		this.nombre = nombre;
		this.tipo = tipo;
		this.raza = raza;
		this.descripcion = (nombre == null || nombre == ""? "Este " + tipo.toLowerCase() + " " : nombre + " ") + descripcion;
		this.edad = edad;
		
		this.estaAdoptado = false;
		
		if(LibFechas8.isFechaCorrecta(fechaAlojamiento)) {
			
			this.fechaAlojamiento = fechas.LibFechas8.convierteStringToLocalDate(fechaAlojamiento);
			
		} else throw new Exception("Fecha incorrecta");
	}
	
	public void esAdoptado() {
		
		estaAdoptado = true;
		fechaAdopcion = LocalDate.now();
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
	
	public int getCodChip() {
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
	
}
