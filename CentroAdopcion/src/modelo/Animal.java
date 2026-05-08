package modelo;

import java.time.LocalDate;
import java.util.Objects;

public class Animal {

	private String nombre, tipo, raza, descripcion;
	private int codChip;	//el codigo del chip es simplemente para poder diferenciar entre un animal y otro
	private byte edad;
	private LocalDate fechaAlojamiento;	//fechaAdopcion
	
	public Animal(int codChip, String nombre, String tipo, String raza, String descripcion, byte edad, String fechaAlojamiento) {
		
		this.codChip = codChip;
		this.nombre = nombre;
		this.tipo = tipo;
		this.raza = raza;
		this.descripcion = (nombre == null || nombre == ""? "Este " + tipo.toLowerCase() + " " : nombre + " ") + descripcion;
		this.edad = edad;
		
		this.fechaAlojamiento = fechas.LibFechas8.convierteStringToLocalDate(fechaAlojamiento);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(codChip);
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
		return codChip == other.codChip;
	}

	@Override
	public String toString() {
		
		return codChip + ": " + tipo + " " + raza + " de " + edad + (edad == 1? " año" : " años")
				+ "\n\tDescripcion: " + descripcion;
	}
	
	public int getCodChip() {
		return codChip;
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
