/*
 * 17 abr 2024
 * Ioritz Lopetegi
 */
package geolokalizazioProiektua;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Interfazea.
 */
public class Interfazea {
	  
	  /** Frame Nagusia Bertan erakutsiko dugu gure Hiria, koordenatuak etab. */
  	JFrame framenagusia = new JFrame();
	  
  	/** Frame Nagusiko GoikoPanela. */
  	JPanel GoikoPanela = new JPanel();
	  
  	/** Frame nagusiko Erdiko Panela */
  	JPanel ErdikoPanela = new JPanel();
	  
  	/** Panel ONtzia, Goiko Panela eta erdiko panela biltzen dituen panela. */
  	JPanel panelaOntzia = new JPanel();
	  
  	/** Label-en panela. */
  	JPanel panellabelpanel = new JPanel(new BorderLayout()); 

	  /** Latitude eta longitudea Graduetan erakusteko labela. */
  	JLabel labelLatLongGradu = new JLabel();
	  
  	/** Hira edo koordenatuak bilatzeko textfield-a. */
  	JTextField bilaketaTextField = new JTextField(20);
	  
  	/** Bilatutako Hiria erakutsiko den label-a. */
  	JLabel labelhiria = new JLabel();
	  
  	/** Latitudea eta longitudea erakusten duen label-a. */
  	JLabel labelLatLong = new JLabel();
  	
  	 String hiria;
  	 String latString;
  	 String longString;
	  
	  
	  /**
  	 * Frame Nagusia sortu.
  	 */
  	public void sortuframenagusia() {
	    framenagusia.setLayout(new BorderLayout());
	    framenagusia.setTitle("Geolokalizazio Aplikazioa");
	    framenagusia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  }
	  
	  /**
  	 * Frame nagusiari tamaina eman.
  	 */
  	public void FrameNagusiariTamainaEman() {
	    framenagusia.setPreferredSize(new Dimension(1200, 900));
	  }
	  
	  /**
  	 * Frame nagusia pack egin.
  	 */
  	public void FrameNagusiaPack() {
	    framenagusia.pack();
	    framenagusia.setLocationRelativeTo(null);
	  }
	  
	  /**
  	 * Erakutsi frame nagusia.
  	 */
  	public void ErakustiFrameNagusia() {
		  framenagusia.add(panelaOntzia, BorderLayout.CENTER);
	    framenagusia.setVisible(true);
	  }
	  
	  /**
  	 * Sortu goiko panela eta bertako konponenteak ezarri.
  	 */
  	public void SortuGoikoPanela() {
		
		    panellabelpanel = new JPanel();
		    panellabelpanel.setLayout(new BoxLayout(panellabelpanel, BoxLayout.Y_AXIS));

		    bilaketaTextField = new JTextField(20); 
		    
		    bilaketaTextField.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	 if (e.getSource() == bilaketaTextField) {
		        		  hiria = bilaketaTextField.getText();
		        		 try {
		        			 
		        			 Geolokalizazioa geolokalizazioa = new Geolokalizazioa();
		        			 hiria = geolokalizazioa.getHiriaIzena(hiria);
		        			 longString= geolokalizazioa.getLortuLongitude(hiria);
		        			 latString=geolokalizazioa.getLortuLatitudea(hiria);
		        			 
							
						} catch (Exception e2) {
							
						}
		        		 if (hiria != null) {
		         	       
		         	    	  labelhiria.setText("Hiria : " + hiria);
		         	      } else {
		         	        System.err.println("Error Hiria aurkitzerakoan");
		         	      }
		        		 if (longString != null && latString !=null) {
			         	      
			         	    	  labelLatLong.setText("Latitudea : " + latString + " Longitudea : " + longString);
			         	      } else {
			         	        System.err.println("Error Hiria aurkitzerakoan");
			         	      }
		        		 
						
					}
		            
		        }
		    });

	
		    panellabelpanel.add(bilaketaTextField);

	
		    labelhiria = new JLabel("Hiria :");
		    panellabelpanel.add(labelhiria);

		    labelLatLong = new JLabel("Latitudea :  " + " Longitudea: ");
		    panellabelpanel.add(labelLatLong);

		     labelLatLongGradu = new JLabel("Latitudea Gradua :  " + " Longitudea Gradua: ");
		    panellabelpanel.add(labelLatLongGradu);

		 
		    GoikoPanela.setBackground(Color.BLUE);

	
		    GoikoPanela.add(panellabelpanel);

		
		    panelaOntzia.add(GoikoPanela);
		}
	 
	  
	  /**
  	 * Sortu erdiko panela eta bertako konponenteak ezarri.
  	 */
  	public void SortuErdikoPanela() {
	    ErdikoPanela.setBackground(Color.RED);
	    System.out.println("ErdikoPanela background: " + ErdikoPanela.getBackground());
	    panelaOntzia.add(ErdikoPanela);
	  }
	  
	
	  
	  /**
  	 * Gehitu panel guztiak Gure Frame-ra.
  	 */
  	public void GehituPanelGuztiakHomera() {
		  
		  
	    panelaOntzia.setLayout(new GridLayout(2, 1));
	    SortuGoikoPanela();
	    SortuErdikoPanela();
	    
	  
	    
	    
	    framenagusia.add(panelaOntzia);
	    framenagusia.repaint();
	  }
  	
  	
  
  	
  	
  	
  	
	}