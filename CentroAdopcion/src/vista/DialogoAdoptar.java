package vista;

import java.awt.*;

import javax.swing.*;

public class DialogoAdoptar extends JDialog{

	private static final long serialVersionUID = 7518864593720141813L;

	private JTextField tfNombre, tfDni, tfApellido1, tfApellido2, tfEdad;
	private JButton bConfirmar, bCancelar, bElegirPersona;
	
	public DialogoAdoptar(JFrame ventanaPadre, JPanel panelAnimal) {
		
		super(ventanaPadre, ventanaPadre.getTitle(), true);	//el true es para que no se pueda cambiar de ventana hasta que se cierre
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		
		JPanel panelAnimalPersona = new JPanel(new GridLayout(1, 2, 10, 10));
		panelAnimalPersona.setBorder(VistaCentroAdopcion.BORDEVACIO);
		panelAnimalPersona.setBackground(Vista.FONDO_PRINCIPAL);
		panelAnimalPersona.add(panelAnimal);
		panelAnimalPersona.add(panelPersona());
		
		panelPrincipal.add(panelAnimalPersona);
		panelPrincipal.add(panelBotones());
		
		this.add(panelPrincipal);
		
		this.pack();
		this.setLocationRelativeTo(ventanaPadre);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	private JPanel panelPersona() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(VistaCentroAdopcion.BORDELINEA);
		panel.setBackground(Vista.FONDO_PRINCIPAL);
		
		JPanel panelNombre = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelNombre.setBackground(Vista.FONDO_PRINCIPAL);
		panelNombre.add(new JLabel("Nombre: "));
		panelNombre.add(tfNombre = new JTextField(10));
		
		JPanel panelDni = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelDni.setBackground(Vista.FONDO_PRINCIPAL);
		panelDni.add(new JLabel("DNI: "));
		panelDni.add(tfDni = new JTextField(10));
		
		JPanel panelApellido1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelApellido1.setBackground(Vista.FONDO_PRINCIPAL);
		panelApellido1.add(new JLabel("Primer Apellido: "));
		panelApellido1.add(tfApellido1 = new JTextField(10));
		
		JPanel panelApellido2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelApellido2.setBackground(Vista.FONDO_PRINCIPAL);
		panelApellido2.add(new JLabel("Segundo Apellido: "));
		panelApellido2.add(tfApellido2 = new JTextField(10));
		
		JPanel panelEdad = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelEdad.setBackground(Vista.FONDO_PRINCIPAL);
		panelEdad.add(new JLabel("Edad: "));
		panelEdad.add(tfEdad = new JTextField(10));

		panel.add(panelNombre);
		panel.add(panelDni);
		panel.add(panelApellido1);
		panel.add(panelApellido2);
		panel.add(panelEdad);
		
		panel.add(new JLabel(" "));
		panel.add(new JLabel(" "));
		panel.add(new JLabel(" "));
		panel.add(new JLabel(" "));
		panel.add(new JLabel(" "));
		panel.add(new JLabel(" "));
		panel.add(new JLabel(" "));
		panel.add(new JLabel(" "));
		panel.add(bElegirPersona = new JButton("Elegir Persona"));

		return panel;
	}
		
	private JPanel panelBotones() {
		
		JPanel panel = new JPanel();
		panel.setBackground(Vista.FONDO_PRINCIPAL);
		
		bConfirmar = new JButton("Confirmar");
		bCancelar = new JButton("Cancelar");
		
		modificaBotones();
		
		panel.add(bConfirmar);
		panel.add(bCancelar);
		
		return panel;
	}
	
	private void modificaBotones() {
		
		JButton[] botones = {this.bConfirmar, this.bCancelar, this.bElegirPersona};
		
		for (JButton boton : botones) {
			
			boton.setBackground(Vista.FONDO_BOTON);
		}
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}

	public JTextField getTfDni() {
		return tfDni;
	}

	public JTextField getTfApellido1() {
		return tfApellido1;
	}

	public JTextField getTfApellido2() {
		return tfApellido2;
	}

	public JTextField getTfEdad() {
		return tfEdad;
	}

	public JButton getbConfirmar() {
		return bConfirmar;
	}

	public JButton getbCancelar() {
		return bCancelar;
	}
}
