package utilidades;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class UtilidadesAnimales {

	public static int buscaIdAnimal(JPanel panelAnimal) {
		
		JPanel panelNombre = new JPanel();
		int idAnimal = 0;
		
		panelNombre = (JPanel) panelAnimal.getComponents()[0];
		
		Component[] componentes = panelNombre.getComponents();
		JLabel l;
		boolean encontrado = false;
		
		for (int i = 0; i < componentes.length && !encontrado; i++) {

			if (componentes[i] instanceof JLabel) {

				l = (JLabel) componentes[i];

				//System.out.println(l.getText());
				
				try {
					
					//System.out.println(Integer.parseInt(l.getText()));
					idAnimal = Integer.parseInt(l.getText());
					
					encontrado = true;
					
				} catch(NumberFormatException e) {
					
					//e.printStackTrace();
				}
			}
		}
		
		return idAnimal;
	}
	
}
