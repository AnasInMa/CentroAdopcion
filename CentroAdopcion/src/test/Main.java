package test;

import javax.swing.*;

import controlador.Controlador;
import vista.Vista;

public class Main {

	public static void main(String[] args) {
		
		Vista v = new Vista();
		new Controlador(v);
		
		JFrame f = new JFrame("CEADOP");
		f.setContentPane(v);
		
		f.setIconImage(new ImageIcon("./imgs/logo.png").getImage());
		
		f.pack();
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
	}

}
