package vista;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;

public class VistaSalir extends JPanel{

	private static final long serialVersionUID = 6877306105475220920L;

	private JButton bConfirmarSalida, bCancelarSalida;

	public VistaSalir() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(Vista.MARRON_CLARO3);
		
		iniciaComponentes();
		
		this.add(Box.createVerticalGlue());
		this.add(panelTexto());
		this.add(panelBotones());
		this.add(Box.createVerticalGlue());
	}
	
	private void iniciaComponentes() {
		
		bConfirmarSalida = new JButton("CONFIRMAR");
		bConfirmarSalida.setFont(new Font(Font.SERIF, Font.BOLD, 20));
		bConfirmarSalida.setForeground(Vista.MARRON_OSCURO4);
		bConfirmarSalida.setBackground(Vista.MARRON_CLARO4);
		
		bCancelarSalida = new JButton("CANCELAR");
		bCancelarSalida.setFont(new Font(Font.SERIF, Font.BOLD, 20));
		bCancelarSalida.setForeground(Vista.MARRON_OSCURO4);
		bCancelarSalida.setBackground(Vista.MARRON_CLARO4);
	}
	
	private JPanel panelTexto() {
		
		JPanel panel = new JPanel();
		panel.setBackground(Vista.MARRON_CLARO3);
		
		JLabel texto = new JLabel("¿Esta seguro que desea salir?");
		texto.setFont(Vista.FUENTE_BOTONES);
		texto.setForeground(Vista.MARRON_OSCURO4);
		
		panel.add(texto);
		
		return panel;
	}
	
	private JPanel panelBotones() {
		
		JPanel panel = new JPanel();
		//panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBackground(Vista.MARRON_CLARO3);
		
		panel.add(bConfirmarSalida);
		panel.add(Box.createRigidArea(new Dimension(100,0)));
		panel.add(bCancelarSalida);
		
		return panel;
	}
	
}
