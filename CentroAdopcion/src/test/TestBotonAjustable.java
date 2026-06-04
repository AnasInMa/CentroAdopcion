package test;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Esto era una experimentacion para entender mejor como
 * funcionaba la escabilidad de los componentes,
 * al final no lo utilize, porque quedaba un poco raro
 * como se veía el boton en relacion a la ventana,
 * y me decanté por simplemente cambiar el tamaño cada
 * vez que el usuario cambie la opcion de la pantalla completa
 */
public class TestBotonAjustable extends JPanel implements ActionListener{

	private static final long serialVersionUID = 3387197714874902604L;

	public static void main(String[] args) {
		
		JFrame f = new JFrame();
		f.setContentPane(new TestBotonAjustable());
		
		f.pack();
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	private static  JTextField campoTexto1, campoTexto2;
	private static JButton b;
	
	private static int ancho, alto;
	
	static {
		
		ancho = 720;
		alto = 480;
	}
	
	private TestBotonAjustable() {
		
		this.setPreferredSize(new Dimension(ancho, alto));
		
		añadeComponentes();
		control();
	}
	
	private void añadeComponentes() {
		
		campoTexto1 = new JTextField(10);
		campoTexto2 = new JTextField(10);

		b = new JButton("BOTON");
		b.setPreferredSize(new Dimension(ancho / 9, alto / 9));
		
		this.add(new JLabel("Ancho: "));
		this.add(campoTexto1);
		this.add(new JLabel("Alto: "));
		this.add(campoTexto2);
		this.add(b);
	}
	
	public void control() {
		
		b.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == b) {
			
			this.setPreferredSize(new Dimension(Integer.parseInt(campoTexto1.getText()), Integer.parseInt(campoTexto2.getText())));

			b.setPreferredSize(new Dimension(Integer.parseInt(campoTexto1.getText()) / 9, Integer.parseInt(campoTexto2.getText()) / 9));
			
			SwingUtilities.getWindowAncestor(this).pack();
			SwingUtilities.getWindowAncestor(this).setLocationRelativeTo(null);
			//System.out.println("anchura: " + Integer.parseInt(campoTexto1.getText()));
			//System.out.println("altura: " + Integer.parseInt(campoTexto2.getText()));
			
			//System.out.println("anchura: " + this.getWidth());
			//System.out.println("altura: " + this.getHeight());
		}
		
	}

}
