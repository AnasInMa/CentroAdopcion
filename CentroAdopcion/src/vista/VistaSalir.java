package vista;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

import controlador.ControladorSalir;

public class VistaSalir extends JPanel{

	private static final long serialVersionUID = 6877306105475220920L;

	private JButton bConfirmarSalida, bCancelarSalida;

	public VistaSalir() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(Vista.FONDO_PRINCIPAL);
		
		iniciaComponentes();
		
		this.add(Box.createVerticalGlue());
		this.add(panelTexto());
		this.add(panelBotones());
		this.add(Box.createVerticalGlue());
	}
	
	public void control(ControladorSalir c) {
		
		this.bConfirmarSalida.addActionListener(c);
		this.bCancelarSalida.addActionListener(c);
	}
	
	private void iniciaComponentes() {
		
		bConfirmarSalida = new JButton("Confirmar");
		bConfirmarSalida.setFont(Vista.FUENTE_BOTONES);
		bConfirmarSalida.setForeground(Vista.TEXTO_CLARO);
		bConfirmarSalida.setBackground(Vista.FONDO_BOTON);
		
		bCancelarSalida = new JButton("Cancelar");
		bCancelarSalida.setFont(Vista.FUENTE_BOTONES);
		bCancelarSalida.setForeground(Vista.TEXTO_CLARO);
		bCancelarSalida.setBackground(Vista.FONDO_BOTON);
	}
	
	private JPanel panelTexto() {
		
		JPanel panel = new JPanel();
		panel.setBackground(Vista.FONDO_PRINCIPAL);
		
		JLabel texto = new JLabel("¿Esta seguro que desea salir?");
		texto.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		texto.setForeground(Vista.TEXTO_OSCURO);
		
		panel.add(texto);
		
		return panel;
	}
	
	private JPanel panelBotones() {
		
		JPanel panel = new JPanel();
		//panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBackground(Vista.FONDO_PRINCIPAL);
		
		panel.add(bConfirmarSalida);
		panel.add(Box.createRigidArea(new Dimension(100,0)));
		panel.add(bCancelarSalida);
		
		return panel;
	}
	
	public void actualizarColores() {
		
	    this.setBackground(Vista.FONDO_PRINCIPAL);
	    bConfirmarSalida.setBackground(Vista.FONDO_BOTON);
	    bConfirmarSalida.setForeground(Vista.TEXTO_CLARO);
	    bCancelarSalida.setBackground(Vista.FONDO_BOTON);
	    bCancelarSalida.setForeground(Vista.TEXTO_CLARO);
	}

	public JButton getbConfirmarSalida() {
		return bConfirmarSalida;
	}

	public JButton getbCancelarSalida() {
		return bCancelarSalida;
	}
	
}
