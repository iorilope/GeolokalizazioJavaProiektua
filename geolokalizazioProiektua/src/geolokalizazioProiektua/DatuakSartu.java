/*
 * 26 abr 2024
 * Ioritz Lopetegi
 */
package geolokalizazioProiektua;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;

/**
 * 
 * @author Ioritz Lopetegi
 */
public class DatuakSartu extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** content pane. */
	private JPanel contentPane;

	/** xx Posizioa. */
	int xx;

	/** yy Posizioa. */
	int yy;

	/**
	 * Datuak Gordetzeko Panela sortu.
	 */
	public DatuakSartu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 832, 461);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBounds(new Rectangle(100, 100, 907, 512));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		String userhome = System.getProperty("user.home");

		JFileChooser fileChooser = new JFileChooser(userhome);
		fileChooser.setBounds(10, 13, 623, 399);
		panel.add(fileChooser);

		JLabel lblNewLabel = new JLabel("X");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setBounds(766, 0, 46, 53);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				dispose();
			}
		});
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Interfazea Mugitu");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				xx = e.getX();
				yy = e.getY();

			}
		});
		lblNewLabel_1.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {

				int x = e.getXOnScreen();

				int y = e.getYOnScreen();

				setLocation(x - xx, y - yy);

			}
		});
		lblNewLabel_1.setBounds(666, 101, 146, 24);
		panel.add(lblNewLabel_1);
	}
}
