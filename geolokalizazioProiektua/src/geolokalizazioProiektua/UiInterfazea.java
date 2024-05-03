/*
 * 26 abr 2024
 * Ioritz Lopetegi
 */
package geolokalizazioProiektua;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window.Type;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 
 * @author Ioritz Lopetegi
 */
public class UiInterfazea extends JFrame {

	/** The Constant serialVersionUID. */
	static final long serialVersionUID = 1L;

	/** The content pane 2. */
	private JPanel contentPane2;

	/** The framedatuaksartu. */
	DatuakSartu framedatuaksartu = new DatuakSartu();
	Double latitudmapa;
	Double longitudmapa;
	/** The interfazea mapa. */
	Mapa interfazeMapa = new Mapa(latitudmapa, longitudmapa);

	/** Hirien ListModel. */
	DefaultListModel<String> listModel = new DefaultListModel<>();

	/** Hirien Lista. */
	JList list = new JList(listModel);

	String testua;

	String[] zatiak;

	/** The content pane. */
	JPanel contentPane;

	/** Bilaketa Texfield. */
	JTextField textField;

	/** Hiria erakusten duen label-a. */
	JLabel labelhiria = new JLabel("Hiria:");

	/** Latitude eta longitude label-a. */
	JLabel labelLatLong = new JLabel("Latitudea: Longitudea:");

	/** Latitudea eta longitudea graduetan label. */
	JLabel labelLatLongGradu = new JLabel("Latitude Gradua: Longitude Gradua:");

	/** Hiria. */
	String hiria;
	String hiria2;

	/** Latitudea. */
	String latString;

	/** Longitudea. */
	String longString;

	/** Latitudea Gradu. */
	String latstringgraduString;

	/** Longitudea Gradu. */
	String lonstringgraduString;

	/** xx posizioa. */
	int xx;

	/** yy posizioa. */
	int yy;

	/**
	 * Aplikazioa exekutatu.
	 */

	/**
	 * Frame-a sortu.
	 */

	public UiInterfazea() {

		setIconImage(
				Toolkit.getDefaultToolkit().getImage("C:\\Users\\1ag3.iorilope\\Downloads\\marcador-de-posicion.png"));
		setTitle("GeolokalizazioAplikazioa");
		setUndecorated(true);

		setBounds(100, 100, 907, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();

		contentPane.add(panel);

		textField = new JTextField();
		textField.setBounds(368, 11, 86, 20);

		// Aldatu Jlist-era Panelean edo frame-ean ez du keypressed detektatzen

		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String bilaketastring = textField.getText();
				char lehenkarakterea = bilaketastring.charAt(0);

				if (lehenkarakterea == '1' || lehenkarakterea == '2' || lehenkarakterea == '3' || lehenkarakterea == '4'
						|| lehenkarakterea == '5' || lehenkarakterea == '6' || lehenkarakterea == '7'
						|| lehenkarakterea == '8' || lehenkarakterea == '9' || lehenkarakterea == '-') {

					testua = textField.getText();
					
					Geolokalizazioa geolokalizazioa = new Geolokalizazioa();
					
					

					zatiak = testua.split(",");
					latitudmapa = Double.parseDouble(zatiak[0]);
					longitudmapa = Double.parseDouble(zatiak[1]);
					
					try {
						hiria2 = geolokalizazioa.getHiriaIzenaKoordenatuBidez(latitudmapa, longitudmapa);
						latstringgraduString = geolokalizazioa.getLortuLatitudeagradu(hiria2);
						lonstringgraduString = geolokalizazioa.getLortuLongitudeagradu(hiria2);
					    
					} catch (IOException e1) {
					   
					    e1.printStackTrace();
					}

					if (hiria2 != null) {
					    labelhiria.setText("Hiria : " + hiria2);
					    listModel.addElement(hiria2);
					    labelLatLong.setText("Latitudea : " + latitudmapa + " Longitudea : " + longitudmapa);
					} else {
					    System.err.println("Errorea Hiria aurkitzerakoan");
					}
					if (latstringgraduString != null && lonstringgraduString != null) {

						labelLatLongGradu.setText("Latitudea Gradu: " + latstringgraduString + " Longitudea Gradu :"
								+ lonstringgraduString);
					} else {
						System.err.println("Error Latitudea eta longitudea graduetan aurkitzerakoan");
					}

					

					interfazeMapa = new Mapa(latitudmapa, longitudmapa);

					System.out.println(latitudmapa);

				} else {
					if (e.getSource() == textField) {
						hiria = textField.getText();
						try {

							Geolokalizazioa geolokalizazioa = new Geolokalizazioa();
							hiria = geolokalizazioa.getHiriaIzena(hiria);
							longString = geolokalizazioa.getLortuLongitude(hiria);
							latString = geolokalizazioa.getLortuLatitudea(hiria);
							latstringgraduString = geolokalizazioa.getLortuLatitudeagradu(hiria);
							lonstringgraduString = geolokalizazioa.getLortuLongitudeagradu(hiria);

						} catch (Exception e2) {

						}
						if (hiria != null) {
							labelhiria.setText("Hiria : " + hiria);
							listModel.addElement(hiria);

						} else {
							System.err.println("Errorea Hiria aurkitzerakoan");
						}
						if (longString != null && latString != null) {

							labelLatLong.setText("Latitudea : " + latString + " Longitudea : " + longString);
						} else {
							System.err.println("Error Latitudea eta longitudea aurkitzerakoan");
						}
						if (latstringgraduString != null && lonstringgraduString != null) {

							labelLatLongGradu.setText("Latitudea Gradu: " + latstringgraduString + " Longitudea Gradu :"
									+ lonstringgraduString);
						} else {
							System.err.println("Error Latitudea eta longitudea graduetan aurkitzerakoan");
						}
						latitudmapa = Double.parseDouble(latString);
						longitudmapa = Double.parseDouble(longString);
						interfazeMapa = new Mapa(latitudmapa, longitudmapa);

						System.out.println(latitudmapa);

					}

				}

			}
		});

		/*
		 * 
		 * Control + fletxa egin , errorea ctrol b eta ctrl c
		 * 
		 * 
		 */

		panel.setLayout(null);
		panel.add(textField);
		textField.setColumns(10);

		labelhiria = new JLabel("Hiria:");
		labelhiria.setIcon(new ImageIcon("C:\\Users\\1ag3.iorilope\\Downloads\\icons8-edificios-de-la-ciudad-16.png"));
		labelhiria.setBounds(357, 42, 253, 20);
		panel.add(labelhiria);

		labelLatLong = new JLabel("Latitudea: Longitudea:");
		labelLatLong.setIcon(new ImageIcon("C:\\Users\\1ag3.iorilope\\Downloads\\icons8-geografía-16.png"));
		labelLatLong.setBounds(356, 73, 345, 29);
		panel.add(labelLatLong);

		labelLatLongGradu = new JLabel("Latitude Gradua: Longitude Gradua:");
		labelLatLongGradu.setIcon(new ImageIcon("C:\\Users\\1ag3.iorilope\\Downloads\\icons8-geografía-16.png"));
		labelLatLongGradu.setBounds(357, 113, 497, 29);
		panel.add(labelLatLongGradu);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\1ag3.iorilope\\Downloads\\icons8-location-48.png"));
		lblNewLabel.setBounds(303, 0, 55, 48);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("X");
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				System.exit(0);
			}
		});
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(832, 0, 39, 29);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("MugituInterfazea");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				xx = e.getX();
				yy = e.getY();

			}
		});
		lblNewLabel_2.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {

				int x = e.getXOnScreen();

				int y = e.getYOnScreen();

				setLocation(x - xx, y - yy);

			}
		});
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(0, 0, 115, 36);
		panel.add(lblNewLabel_2);

		list = new JList(listModel);
		list.setToolTipText("Bilaketa Historiala");
		list.setVisibleRowCount(2);
		list.setBounds(10, 30, 108, 175);
		list.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_9 && e.isControlDown()) {
		            System.out.println("Elementua  mugitu da!");
		            int selectedIndex = list.getSelectedIndex();
		            if (selectedIndex != -1 && selectedIndex < listModel.getSize() - 1) {
		               
		                String temp = listModel.getElementAt(selectedIndex);
		                listModel.set(selectedIndex, listModel.getElementAt(selectedIndex + 1));
		                listModel.set(selectedIndex + 1, temp);
		            
		                list.setSelectedIndex(selectedIndex + 1);
		            }
		            // Hemen ezarri behera metodoak egiteko
		        }
		    }
		});
		panel.add(list);
		list.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_8 && e.isControlDown()) {
		            System.out.println("Elementua  mugitu da!");
		            int selectedIndex = list.getSelectedIndex();
		            if (selectedIndex != -1 && selectedIndex < listModel.getSize() - 1) {
		               
		                String temp = listModel.getElementAt(selectedIndex);
		                listModel.set(selectedIndex, listModel.getElementAt(selectedIndex + 1));
		                listModel.set(selectedIndex + 1, temp);
		               
		                list.setSelectedIndex(selectedIndex + 1);
		            }
		            // Hemen ezarri behera metodoak egiteko
		        }
		    }
		});
		panel.add(list);
		
		try {
            File file = new File("datuak_lista.txt");
            if (file.exists()) {
                FileReader reader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(reader);

               
                String linea;
                while ((linea = bufferedReader.readLine()) != null) {
                    listModel.addElement(linea);
                }

                bufferedReader.close();
                reader.close();
                System.out.println("Lista kargatuta fitxategitikan");
            } else {
                System.out.println("Fitxategia ez da existitzen.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

		JButton btnNewButton = new JButton("Gorde");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\1ag3.iorilope\\Downloads\\icons8-guardar-16.png"));

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				framedatuaksartu.setVisible(true);
				try {
		            File file = new File("datuak_lista.txt");
		            FileWriter writer = new FileWriter(file);
		            BufferedWriter bufferedWriter = new BufferedWriter(writer);

		           
		            for (int i = 0; i < listModel.size(); i++) {
		                bufferedWriter.write(listModel.getElementAt(i) + "\n");
		            }

		            bufferedWriter.close();
		            writer.close();
		            System.out.println("Lista gordeta");
		        } catch (IOException e1) {
		            e1.printStackTrace();
		        }

			}
		});

		btnNewButton.setBounds(20, 212, 89, 23);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Ikusi Mapa");
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\1ag3.iorilope\\Downloads\\icons8-mapa-16.png"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				interfazeMapa.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(730, 209, 126, 29);
		panel.add(btnNewButton_1);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
	}

	/**
	 * Datuak sartu.
	 */
	public void DatuakSartu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 832, 461);
		contentPane2 = new JPanel();
		contentPane2.setBounds(new Rectangle(100, 100, 907, 512));
		contentPane2.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane2);
		contentPane2.setLayout(new BoxLayout(contentPane2, BoxLayout.X_AXIS));

		JPanel panel2 = new JPanel();
		contentPane2.add(panel2);
		panel2.setLayout(null);

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setBounds(10, 13, 623, 399);
		panel2.add(fileChooser);
	}
}
