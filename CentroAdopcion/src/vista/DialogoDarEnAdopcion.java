package vista;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDate;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import modelo.Animal;
import modelo.CentroAdopcion;
import modelo.DAOAnimales;
import modelo.DAOPersonas;
import modelo.Persona;
import utilidades.LibFechas8;
import utilidades.UtilidadesImagenes;
import utilidades.UtilidadesPaneles;
import utilidades.UtilidadesVariables;

public class DialogoDarEnAdopcion extends JDialog implements ActionListener {

	private static final long serialVersionUID = 2928607922227909036L;

	private static final byte ANCHOTF = 15;

	private CentroAdopcion centroAdopcion;
	private Persona personaSeleccionada;
	private Animal animal;
	
	private DAOPersonas daoPersonas;
	private DAOAnimales daoAnimales;

	private JTextField tfNombrePersona, tfDni, tfApellido1, tfApellido2, tfEdadPersona,
						tfNombreAnimal, tfTipo, tfRaza, tfDescripcion, tfEdadAnimal;
	
	private Image imagenEscalada;
	private JLabel imagenAnimal;
	private JButton bConfirmar, bCancelar, bElegirFotoAnimal, bElegirPersona;
	
	private boolean haElegidoPersona, haPulsadoBotonConfirmar;
	
	private File imagenElegida;
	private String extensionImagen;
	
	private DialogoTablaPersonasConAnimales dialogoP;
	
	public DialogoDarEnAdopcion(JFrame ventanaPadre, CentroAdopcion centro, DAOPersonas daoPersonas, DAOAnimales daoAnimales) {

		super(ventanaPadre, "Dar Animal en Adopcion", true);
		
		iniciaComponentes();

		centroAdopcion = centro;
		
		this.daoPersonas = daoPersonas;
		this.daoAnimales = daoAnimales;
		
		haElegidoPersona = false;
		haPulsadoBotonConfirmar = false;

		this.add(panelPrincipal());

		this.control();

		this.pack();
		this.setLocationRelativeTo(ventanaPadre);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private void control() {

		this.bElegirFotoAnimal.addActionListener(this);
		this.bElegirPersona.addActionListener(this);

		this.bConfirmar.addActionListener(this);
		this.bCancelar.addActionListener(this);
	}
	
	private JPanel panelPrincipal() {

		JPanel panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBackground(UtilidadesVariables.FONDO_PRINCIPAL);

		JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
		panel.setBackground(UtilidadesVariables.FONDO_PRINCIPAL);
		panel.setBorder(UtilidadesVariables.BORDEVACIO);

		panel.add(panelAnimal());
		panel.add(UtilidadesPaneles.panelPersona(tfNombrePersona, tfApellido1, tfApellido2, tfDni, tfEdadPersona, bElegirPersona));

		panelPrincipal.add(panel, BorderLayout.CENTER);
		panelPrincipal.add(panelBotones(), BorderLayout.SOUTH);

		return panelPrincipal;
	}

	private JPanel panelAnimal() {

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(UtilidadesVariables.FONDO_ANIMALES);
		panel.setBorder(UtilidadesVariables.BordeLinea);

		tfNombreAnimal.setBorder(new TitledBorder("Nombre"));
		tfNombreAnimal.setBackground(UtilidadesVariables.FONDO_ANIMALES);
		panel.add(tfNombreAnimal);

		tfTipo.setBorder(new TitledBorder("Tipo"));
		tfTipo.setBackground(UtilidadesVariables.FONDO_ANIMALES);
		panel.add(tfTipo);

		tfRaza.setBorder(new TitledBorder("Raza"));
		tfRaza.setBackground(UtilidadesVariables.FONDO_ANIMALES);
		panel.add(tfRaza);
		
		tfDescripcion.setBorder(new TitledBorder("Descripción"));
		tfDescripcion.setBackground(UtilidadesVariables.FONDO_ANIMALES);
		panel.add(tfDescripcion);

		tfEdadAnimal.setBorder(new TitledBorder("Edad"));
		tfEdadAnimal.setBackground(UtilidadesVariables.FONDO_ANIMALES);
		panel.add(tfEdadAnimal);

		panel.add(Box.createVerticalStrut(20));

		ImageIcon imagenSinEscalar = new ImageIcon("./imgs/sinImagen.png");
		imagenEscalada = imagenSinEscalar.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);

		imagenAnimal = new JLabel(new ImageIcon(imagenEscalada));
		imagenAnimal.setBorder(UtilidadesVariables.BordeLinea);
		imagenAnimal.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		panel.add(imagenAnimal);

		panel.add(new JLabel(" "));

		bElegirFotoAnimal.setForeground(UtilidadesVariables.TEXTO_CLARO);
		bElegirFotoAnimal.setBackground(UtilidadesVariables.FONDO_BOTON);
		bElegirFotoAnimal.setAlignmentX(JButton.CENTER_ALIGNMENT);
		panel.add(bElegirFotoAnimal);

		return panel;
	}

	private void iniciaComponentes() {
		
		this.tfNombrePersona = new JTextField(10);
		this.tfApellido1 = new JTextField(10);
		this.tfApellido2 = new JTextField(10);
		this.tfDni = new JTextField(10);
		this.tfEdadPersona = new JTextField(10);
		
		this.bElegirPersona = new JButton("Elegir Persona");
		
		this.tfNombreAnimal = new JTextField(ANCHOTF);
		this.tfTipo = new JTextField(ANCHOTF);
		this.tfRaza = new JTextField(ANCHOTF);
		this.tfDescripcion = new JTextField(ANCHOTF);
		this.tfEdadAnimal = new JTextField(ANCHOTF);
		
		this.bElegirFotoAnimal = new JButton("Elegir Imagen");
		
		this.bConfirmar = new JButton("Confirmar");
		this.bCancelar = new JButton("Cancelar");
		
	}
	
	private JPanel panelBotones() {

		JPanel panel = new JPanel();
		panel.setBackground(UtilidadesVariables.FONDO_PRINCIPAL);

		this.bConfirmar.setForeground(UtilidadesVariables.TEXTO_CLARO);
		this.bConfirmar.setBackground(UtilidadesVariables.FONDO_BOTON);
		this.bConfirmar.setFocusable(false);

		this.bCancelar.setForeground(UtilidadesVariables.TEXTO_CLARO);
		this.bCancelar.setBackground(UtilidadesVariables.FONDO_BOTON);
		this.bCancelar.setFocusable(false);

		panel.add(bConfirmar);
		panel.add(bCancelar);

		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == bElegirFotoAnimal) {

			File f = UtilidadesImagenes.elegirImagen(this);
			
			if (f != null) {
				
				this.extensionImagen = f.getName().split("\\.")[1]; //primero obtengo el nombre del fichero (algo como animal.png) y despues lo divido por un punto, para asi obtener el tipo de extension (y las dos barras \\ es para escapar el punto)

				if(this.extensionImagen.equalsIgnoreCase("png") || this.extensionImagen.equalsIgnoreCase("jpg") || this.extensionImagen.equalsIgnoreCase("jpeg")) {
					
					this.imagenElegida = f;
					this.imagenAnimal.setIcon(new ImageIcon(new ImageIcon(f.getAbsolutePath()).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
					
				} else {
					
					JOptionPane.showMessageDialog(this, "La imagen no es compatible (.png, .jpg, .jpeg)", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
			} else {
				
				JOptionPane.showMessageDialog(this, "No se ha seleccionado ninguna imagen", "ERROR", JOptionPane.ERROR_MESSAGE);
			}

		} else if (e.getSource() == bElegirPersona) {

			try {

				dialogoP = new DialogoTablaPersonasConAnimales(this, daoPersonas.getAllMatriz(),
						new String[] { "IdPersona", "Nombre", "DNI", "Primer Apellido", "Segundo Apellido", "Edad" }, daoPersonas);

				try {

					//asi no solo no se crea la persona sino que ademas no se añaden los campos
					if(!dialogoP.isHaPulsadoBotonConfirmar()) throw new NullPointerException();
				
					personaSeleccionada = daoPersonas.buscaPersona(dialogoP.getIdPersonaSeleccionada());

					añadePersonaACampos();

					haElegidoPersona = true;

					if (dialogoP.isHaSeleccionadoAnimal()) {

						this.animal = dialogoP.getAnimalSeleccionado();
						this.animal.setIdAnimal(daoAnimales.idUltimoAnimal() + 1);
						this.animal.setIdCentro(this.centroAdopcion.getIDCentro());

						this.añadeAnimalACampos();

					}

				} catch (NullPointerException error) {
					
					haElegidoPersona = false;

					JOptionPane.showMessageDialog(this, "No se ha seleccionado a ninguna persona");
				}

			} catch (Exception e1) {

				e1.printStackTrace();
			}

		} else if (e.getSource() == bConfirmar) {
			
			try {
				
				if(haElegidoPersona) {
					
					if(dialogoP.isHaSeleccionadoAnimal()) {

						//this.centroAdopcion.alojaAnimal(animal);
						this.añadeAnimalACampos();
						this.daoAnimales.insertaAnimal(animal);
						
					} else {		//ha elegido una persona pero no ha elegido ningun animal
						
						verificaAnimal();
						
						this.daoAnimales.insertaAnimalSinId(new Animal(0, this.tfNombreAnimal.getText(), this.tfTipo.getText(), this.tfRaza.getText(), this.tfDescripcion.getText(), Byte.parseByte(this.tfEdadAnimal.getText()), LibFechas8.getFechaShort(LocalDate.now()), this.centroAdopcion.getIDCentro()));
						
					}
					
				} else {		//no ha elegido a ninguna persona
					
					verificaPersona();
					
					verificaAnimal();
					
					daoAnimales.insertaAnimalSinId(this.animal = new Animal(0, this.tfNombreAnimal.getText(), this.tfTipo.getText(), this.tfRaza.getText(), this.tfDescripcion.getText(), Byte.parseByte(this.tfEdadAnimal.getText()), LibFechas8.getFechaShort(LocalDate.now()), this.centroAdopcion.getIDCentro()));

					daoPersonas.insertaPersonaSinId(new Persona(0, this.tfNombrePersona.getText(), this.tfDni.getText(), this.tfApellido1.getText(), this.tfApellido2.getText(), (byte) Byte.parseByte(this.tfEdadPersona.getText())));
				}
				
				UtilidadesImagenes.guardarImagen(new Animal(daoAnimales.getUltimo(), this.extensionImagen), this.imagenElegida);
				
				this.haPulsadoBotonConfirmar = true;
				
				this.dispose();
				
			} catch(NumberFormatException error) {
				
				JOptionPane.showMessageDialog(this, "Edad no valida", "ERROR", JOptionPane.ERROR_MESSAGE);
				
			} catch (Exception error) {
				
				JOptionPane.showMessageDialog(this, error.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				
			}
			
		} else {		//cancelar

			haElegidoPersona = false;
			haPulsadoBotonConfirmar = false;	//los tengo que poner aqui tambien en caso de que se haya pulsado el boton confirmar y se haya producido un error, lo que haria que ese boton estuviera todavia a true
			
			this.dispose();
		}

	}

	private void verificaPersona() throws Exception {
		
		if(this.tfNombrePersona.getText().isBlank()) {
			
			throw new Exception("El campo Nombre de la Persona no puede estar vacio");
			
		} else if(this.tfApellido1.getText().isBlank()) {
			
			throw new Exception("El campo Primer Apelllido no puede estar vacio");
			
		} else if(this.tfApellido2.getText().isBlank()) {
			
			throw new Exception("El campo Segundo Apelllido no puede estar vacio");
			
		} else if(this.tfDni.getText().isBlank()) {
			
			throw new Exception("El campo DNI no puede estar vacio");
			
		} else if(this.tfEdadPersona.getText().isBlank()) {
			
			throw new Exception("El campo Edad de la Persona no puede estar vacio");
			
		} else if(Integer.parseInt(this.tfEdadPersona.getText()) < 18) {
			
			throw new Exception("Lo siento. No se puede adoptar a un animal siendo menor de edad");
			
		} else if(Integer.parseInt(this.tfEdadPersona.getText()) > Byte.MAX_VALUE) {
			
			throw new Exception("Edad de la Persona no válida");
		}
		
		Persona.validaDni(this.tfDni.getText());
	}
	
	private void verificaAnimal() throws Exception {
		
		if(this.tfNombreAnimal.getText().isBlank()) {
			
			throw new Exception("El campo Nombre del Animal no puede estar vacio");
			
		} else if(this.tfTipo.getText().isBlank()) {
			
			throw new Exception("El campo Tipo no puede estar vacio");
			
		} else if(this.tfRaza.getText().isBlank()) {
			
			throw new Exception("El campo Raza no puede estar vacio");
			
		} else if(this.tfDescripcion.getText().isBlank()) {
			
			throw new Exception("El campo Descripcion no puede estar vacio");
			
		} else if(this.tfEdadAnimal.getText().isBlank()) {
			
			throw new Exception("El campo Edad del Animal no puede estar vacio");
			
		} else if(Integer.parseInt(this.tfEdadAnimal.getText()) > Byte.MAX_VALUE) {
			
			throw new Exception("Edad del Animal no válida");
		}
		
	}

	private void añadePersonaACampos() {

		this.tfNombrePersona.setText(personaSeleccionada.getNombre());
		this.tfApellido1.setText(personaSeleccionada.getPrimerApellido());
		this.tfApellido2.setText(personaSeleccionada.getSegundoApellido());
		this.tfDni.setText(personaSeleccionada.getNif());
		this.tfEdadPersona.setText(personaSeleccionada.getEdad() + "");

		this.tfNombrePersona.setEditable(false);
		this.tfNombrePersona.setFocusable(false);

		this.tfApellido1.setEditable(false);
		this.tfApellido1.setFocusable(false);

		this.tfApellido2.setEditable(false);
		this.tfApellido2.setFocusable(false);

		this.tfDni.setEditable(false);
		this.tfDni.setFocusable(false);

		this.tfEdadPersona.setEditable(false);
		this.tfEdadPersona.setFocusable(false);
	}
	
	private void añadeAnimalACampos() {
		
		this.tfNombreAnimal.setText(animal.getNombre());
		this.tfTipo.setText(animal.getTipo());
		this.tfRaza.setText(animal.getRaza());
		this.tfDescripcion.setText(animal.getDescripcion());
		this.tfEdadAnimal.setText(animal.getEdad() + "");

		this.tfNombreAnimal.setEditable(false);
		this.tfNombreAnimal.setFocusable(false);

		this.tfTipo.setEditable(false);
		this.tfTipo.setFocusable(false);
		
		this.tfRaza.setEditable(false);
		this.tfRaza.setFocusable(false);

		this.tfDescripcion.setEditable(false);
		this.tfDescripcion.setFocusable(false);
		
		this.tfEdadAnimal.setEditable(false);
		this.tfEdadAnimal.setFocusable(false);
	}

	public boolean isHaPulsadoBotonConfirmar() {
		return haPulsadoBotonConfirmar;
	}
	
}
