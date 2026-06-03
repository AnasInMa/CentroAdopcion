package test;

import javax.swing.*;

import controlador.Controlador;
import utilidades.UtilidadesVariables;
import vista.Vista;

public class Main {

	public static void main(String[] args) {
		
		Vista v = new Vista();
		
		JFrame f = new JFrame("CEADOP");
		
		f.setContentPane(v);
		
		new Controlador(v);
		
		f.setIconImage(new ImageIcon("./imgs/logo.png").getImage());
		
		//f.setUndecorated(true);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setExtendedState(UtilidadesVariables.EsPantallaCompleta? JFrame.MAXIMIZED_BOTH : JFrame.NORMAL);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
	}

}
