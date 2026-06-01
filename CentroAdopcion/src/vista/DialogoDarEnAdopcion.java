package vista;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import fechas.LibFechas8;
import modelo.Animal;
import modelo.CentroAdopcion;
import modelo.DAOAnimales;
import modelo.DAOPersonas;
import modelo.Persona;

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
	
	private boolean haElegidoPersona;
	
	private DialogoTablaPersonasConAnimales dialogoP;
	
	public DialogoDarEnAdopcion(JFrame ventanaPadre, CentroAdopcion centro, DAOPersonas daoPersonas, DAOAnimales daoAnimales) {

		super(ventanaPadre, "Dar Animal en Adopcion", true);
		// this.setBackground(Vista.FONDO_PRINCIPAL);

		centroAdopcion = centro;
		
		this.daoPersonas = daoPersonas;
		this.daoAnimales = daoAnimales;
		
		haElegidoPersona = false;

		this.add(panelPrincipal());

		this.control();

		this.pack();
		this.setLocationRelativeTo(ventanaPadre);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private JPanel panelPrincipal() {

		JPanel panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBackground(Vista.FONDO_PRINCIPAL);

		JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
		panel.setBackground(Vista.FONDO_PRINCIPAL);
		panel.setBorder(VistaCentroAdopcion.BORDEVACIO);

		panel.add(panelAnimal());
		panel.add(panelPersona());

		panelPrincipal.add(panel, BorderLayout.CENTER);
		panelPrincipal.add(panelBotones(), BorderLayout.SOUTH);

		return panelPrincipal;
	}

	private void control() {

		this.bElegirFotoAnimal.addActionListener(this);
		this.bElegirPersona.addActionListener(this);

		this.bConfirmar.addActionListener(this);
		this.bCancelar.addActionListener(this);
	}

	private JPanel panelAnimal() {

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Vista.FONDO_ANIMALES);
		panel.setBorder(VistaCentroAdopcion.BordeLinea);

		tfNombreAnimal = new JTextField(ANCHOTF);
		tfNombreAnimal.setBorder(new TitledBorder("Nombre"));
		tfNombreAnimal.setBackground(Vista.FONDO_ANIMALES);
		panel.add(tfNombreAnimal);

		tfTipo = new JTextField(ANCHOTF);
		tfTipo.setBorder(new TitledBorder("Tipo"));
		tfTipo.setBackground(Vista.FONDO_ANIMALES);
		panel.add(tfTipo);

		tfRaza = new JTextField(ANCHOTF);
		tfRaza.setBorder(new TitledBorder("Raza"));
		tfRaza.setBackground(Vista.FONDO_ANIMALES);
		panel.add(tfRaza);

		tfDescripcion = new JTextField(ANCHOTF);
		tfDescripcion.setBorder(new TitledBorder("Descripción"));
		tfDescripcion.setBackground(Vista.FONDO_ANIMALES);
		panel.add(tfDescripcion);

		tfEdadAnimal = new JTextField(ANCHOTF);
		tfEdadAnimal.setBorder(new TitledBorder("Edad"));
		tfEdadAnimal.setBackground(Vista.FONDO_ANIMALES);
		panel.add(tfEdadAnimal);

		panel.add(Box.createVerticalStrut(20));

		ImageIcon imagenSinEscalar = new ImageIcon("./imgs/sinImageen.png");
		imagenEscalada = imagenSinEscalar.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);

		imagenAnimal = new JLabel(new ImageIcon(imagenEscalada));
		imagenAnimal.setBorder(VistaCentroAdopcion.BordeLinea);
		imagenAnimal.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		panel.add(imagenAnimal);

		panel.add(new JLabel(" "));

		this.bElegirFotoAnimal = new JButton("Elegir Imagen");
		bElegirFotoAnimal.setForeground(Vista.TEXTO_CLARO);
		bElegirFotoAnimal.setBackground(Vista.FONDO_BOTON);
		bElegirFotoAnimal.setAlignmentX(JButton.CENTER_ALIGNMENT);
		panel.add(bElegirFotoAnimal);

		return panel;
	}

	private JPanel panelPersona() {

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Vista.FONDO_ANIMALES);
		panel.setBorder(VistaCentroAdopcion.BordeLinea);

		tfNombrePersona = new JTextField(ANCHOTF);
		tfNombrePersona.setBorder(new TitledBorder("Nombre"));
		tfNombrePersona.setBackground(Vista.FONDO_ANIMALES);
		panel.add(tfNombrePersona);

		tfApellido1 = new JTextField(ANCHOTF);
		tfApellido1.setBorder(new TitledBorder("Primer Apellido"));
		tfApellido1.setBackground(Vista.FONDO_ANIMALES);
		panel.add(tfApellido1);

		tfApellido2 = new JTextField(ANCHOTF);
		tfApellido2.setBorder(new TitledBorder("Segundo Apellido"));
		tfApellido2.setBackground(Vista.FONDO_ANIMALES);
		panel.add(tfApellido2);

		tfDni = new JTextField(ANCHOTF);
		tfDni.setBorder(new TitledBorder("DNI"));
		tfDni.setBackground(Vista.FONDO_ANIMALES);
		panel.add(tfDni);

		tfEdadPersona = new JTextField(ANCHOTF);
		tfEdadPersona.setBorder(new TitledBorder("Edad"));
		tfEdadPersona.setBackground(Vista.FONDO_ANIMALES);
		panel.add(tfEdadPersona);

		panel.add(Box.createVerticalStrut(144));

		bElegirPersona = new JButton("Elegir Persona");
		bElegirPersona.setForeground(Vista.TEXTO_CLARO);
		bElegirPersona.setBackground(Vista.FONDO_BOTON);
		bElegirPersona.setAlignmentX(JButton.CENTER_ALIGNMENT);

		panel.add(bElegirPersona);

		return panel;
	}

	private JPanel panelBotones() {

		JPanel panel = new JPanel();
		panel.setBackground(Vista.FONDO_PRINCIPAL);

		this.bConfirmar = new JButton("Confirmar");
		this.bConfirmar.setForeground(Vista.TEXTO_CLARO);
		this.bConfirmar.setBackground(Vista.FONDO_BOTON);
		this.bConfirmar.setFocusable(false);

		this.bCancelar = new JButton("Cancelar");
		this.bCancelar.setForeground(Vista.TEXTO_CLARO);
		this.bCancelar.setBackground(Vista.FONDO_BOTON);
		this.bCancelar.setFocusable(false);

		panel.add(bConfirmar);
		panel.add(bCancelar);

		return panel;
	}

	public File elegirImagen() {

		JFileChooser fc = new JFileChooser();

	    fc.setFileFilter(new FileNameExtensionFilter("Imagenes(.png)", ".png"));

		int opcion = fc.showOpenDialog(this);

		if (opcion == JFileChooser.APPROVE_OPTION) {

			File archivoSeleccionado = fc.getSelectedFile();

			return archivoSeleccionado;
		}

		return null;
	}
	
	private void guardarImagen() {
	    
		try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("./imgs/Animal" + this.animal.getIDAnimal() + ".png")))){
			
			byte[] bytesImagen = Files.readAllBytes(Paths.get(elegirImagen().getAbsolutePath()));
			
			for (int i = 0; i < bytesImagen.length; i++) {
				
				bos.write(bytesImagen[i]);
			}
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(this, "No se ha seleccionado ninguna imagen", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == bElegirFotoAnimal) {

			File f = this.elegirImagen();

			if (f != null) {
				
				this.imagenAnimal.setIcon(new ImageIcon(new ImageIcon(f.getAbsolutePath()).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
/*
				try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("")))) {

				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
*/
			}

		} else if (e.getSource() == bElegirPersona) {

			try {

				dialogoP = new DialogoTablaPersonasConAnimales(this, daoPersonas.getAllMatriz(),
						new String[] { "IdPersona", "Nombre", "DNI", "Primer Apellido", "Segundo Apellido", "Edad" }, daoPersonas);

				// System.out.println(daoPersonas.buscaPersona(dialogoP.getIdPersonaSeleccionada()));

				personaSeleccionada = daoPersonas.buscaPersona(dialogoP.getIdPersonaSeleccionada());

				try {

					añadePersonaACampos();

					haElegidoPersona = true;

					if (dialogoP.isHaSeleccionadoAnimal()) {

						this.animal = dialogoP.getAnimalSeleccionado();
						this.animal.setIdCentro(this.centroAdopcion.getIDCentro());

						this.añadeAnimalACampos();

					}

				} catch (NullPointerException error) {

					JOptionPane.showMessageDialog(this, "No se ha seleccionado a ninguna persona");
				}

			} catch (Exception e1) {

				e1.printStackTrace();
			}

		} else if (e.getSource() == bConfirmar) {

			// System.out.println("confirmar");

			try {
				
				if(haElegidoPersona) {
					
					if(dialogoP.isHaSeleccionadoAnimal()) {

						//this.centroAdopcion.alojaAnimal(animal);
						this.añadeAnimalACampos();
						this.daoAnimales.insertaAnimal(animal);
						
					} else {
						
						//this.centroAdopcion.alojaAnimal(animal);
						verificaAnimal();
						
						Animal anim;
						
						System.out.println(centroAdopcion);
						
						this.daoAnimales.insertaAnimalSinId(anim = new Animal(0, this.tfNombreAnimal.getText(), this.tfTipo.getText(), this.tfRaza.getText(), this.tfDescripcion.getText(), Byte.parseByte(this.tfEdadAnimal.getText()), LibFechas8.getFechaShort(LocalDate.now()), this.centroAdopcion.getIDCentro()));
						
						System.out.println(anim);
						System.out.println(centroAdopcion);
					}
					
					
				} else {
					
					verificaPersona();
					
					verificaAnimal();
					
					daoAnimales.insertaAnimalSinId(this.animal = new Animal(0, this.tfNombreAnimal.getText(), this.tfTipo.getText(), this.tfRaza.getText(), this.tfDescripcion.getText(), Byte.parseByte(this.tfEdadAnimal.getText()), LibFechas8.getFechaShort(LocalDate.now()), this.centroAdopcion.getIDCentro()));

					daoPersonas.insertaPersonaSinId(new Persona(0, this.tfNombrePersona.getText(), this.tfDni.getText(), this.tfApellido1.getText(), this.tfApellido2.getText(), (byte) Byte.parseByte(this.tfEdadPersona.getText())));
					
				}
				
				this.guardarImagen();
				this.dispose();
				
			} catch (Exception e1) {
				
				JOptionPane.showMessageDialog(this, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				
				//e1.printStackTrace();
			}
			
		} else {

			// System.out.println("cancelar");
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

}
