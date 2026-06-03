package modelo;

import java.awt.Color;
import java.util.Arrays;

import utilidades.UtilidadesVariables;

public enum EnumColores {

	MARRON(UtilidadesVariables.ColoresMarron, "Marron"),
	VERDE(UtilidadesVariables.ColoresVerde, "Verde"),
	GRIS(UtilidadesVariables.ColoresGris, "Gris"),
	AZUL(UtilidadesVariables.ColoresAzul, "Azul"),
	MORADO(UtilidadesVariables.ColoresMorado, "Morado"),
	ROJO(UtilidadesVariables.ColoresRojo, "Rojo"),
	TURQUESA(UtilidadesVariables.ColoresTurquesa, "Turquesa");
	
	private Color[] colores;
	private String nombreColor;
	private static EnumColores[] enumColores;

	static {
		
		enumColores = EnumColores.values();
	}
	
	EnumColores(Color[] colores, String nombreColor) {
		
		this.colores = colores;
		this.nombreColor = nombreColor;
	}
	
	public static Color[] buscaColoresPorNombre(String nombreColor) {
		
		Color[] coloresAux = new Color[UtilidadesVariables.ColoresVisibles.length];
		boolean encontrado = false;
		
		for (int i = 0; i < enumColores.length && !encontrado; i++) {
			
			if(enumColores[i].getNombreColor().equalsIgnoreCase(nombreColor)) {
				
				coloresAux = enumColores[i].getColores();
				encontrado = true;
			}
		}
		
		return coloresAux;
	}
	
	public static String buscaNombrePorColores(Color[] colores) {
		
		String nombreColor = null;
		
		for (EnumColores color : enumColores) {
			
			if(Arrays.equals(color.getColores(), colores)) {
				
				nombreColor = color.nombreColor;
			}
		}
		
		return nombreColor;
	}
	
	public static String[] getNombreColores() {
		
		String[] nombreColores = new String[enumColores.length];
		
		for (int i = 0; i < nombreColores.length; i++) {
			
			nombreColores[i] = enumColores[i].getNombreColor();
		}
		
		return nombreColores;
	}

	public Color[] getColores() {
		return colores;
	}

	public String getNombreColor() {
		return nombreColor;
	}
	
	
}
