package modelo;

import java.time.LocalDate;
import java.util.Objects;

public class Animal implements Comparable<Animal>{

	private String nombre, tipo, raza, descripcion;
	private int codigo;	//el codigo es el id de la tabla
	private byte edad;
	private LocalDate fechaAlojamiento;	//fechaAdopcion
	
	public Animal(int cod, String nombre, String tipo, String raza, String descripcion, byte edad, String fechaAlojamiento) {
		
		this.codigo = cod;
		this.nombre = nombre;
		this.tipo = tipo;
		this.raza = raza;
		this.descripcion = (nombre == null || nombre == ""? "Este " + tipo.toLowerCase() + " " : nombre + " ") + descripcion;
		this.edad = edad;
		
		this.fechaAlojamiento = fechas.LibFechas8.convierteStringToLocalDate(fechaAlojamiento);
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
		
		return toStringSimple() + "\n\tDescripcion: " + descripcion;
	}
	
	public String toStringSimple() {
		
		return "(" + codigo + ") " + tipo + " " + raza + " de " + edad + (edad == 1? " año" : " años");
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
