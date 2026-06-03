package vista;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

import controlador.ControladorSalir;
import utilidades.UtilidadesVariables;

public class VistaSalir extends JPanel{

	private static final long serialVersionUID = 6877306105475220920L;

	private JPanel panelT, panelB;
	private JButton bConfirmarSalida, bCancelarSalida;

	public VistaSalir() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		iniciaComponentes();
		
		this.add(Box.createVerticalGlue());
		this.add(panelTexto());
		this.add(panelBotones());
		this.add(Box.createVerticalGlue());

		actualizarColores();
	}
	
	public void control(ControladorSalir c) {
		
		this.bConfirmarSalida.addActionListener(c);
		this.bCancelarSalida.addActionListener(c);
	}
	
	private void iniciaComponentes() {
		
		bConfirmarSalida = new JButton("Confirmar");
		bConfirmarSalida.setFont(UtilidadesVariables.FUENTE_BOTONES);
		
		bCancelarSalida = new JButton("Cancelar");
		bCancelarSalida.setFont(UtilidadesVariables.FUENTE_BOTONES);
	}
	
	private JPanel panelTexto() {
		
		panelT = new JPanel();
		panelT.setBackground(UtilidadesVariables.FONDO_PRINCIPAL);
		
		JLabel texto = new JLabel("¿Esta seguro que desea salir?");
		texto.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		
		panelT.add(texto);
		
		return panelT;
	}
	
	private JPanel panelBotones() {
		
		panelB = new JPanel();
		
		panelB.add(bConfirmarSalida);
		panelB.add(Box.createRigidArea(new Dimension(100,0)));
		panelB.add(bCancelarSalida);
		
		return panelB;
	}
	
	public void actualizarColores() {
		
	    this.setBackground(UtilidadesVariables.FONDO_PRINCIPAL);
	    
	    bConfirmarSalida.setBackground(UtilidadesVariables.FONDO_BOTON);
	    bConfirmarSalida.setForeground(UtilidadesVariables.TEXTO_CLARO);
	    
	    bCancelarSalida.setBackground(UtilidadesVariables.FONDO_BOTON);
	    bCancelarSalida.setForeground(UtilidadesVariables.TEXTO_CLARO);
	    
	    panelT.setBackground(UtilidadesVariables.FONDO_PRINCIPAL);
	    panelB.setBackground(UtilidadesVariables.FONDO_PRINCIPAL);
	}

	public JButton getbConfirmarSalida() {
		return bConfirmarSalida;
	}

	public JButton getbCancelarSalida() {
		return bCancelarSalida;
	}
	
}
