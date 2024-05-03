/*
 * 30 abr 2024
 * Ioritz Lopetegi
 */
package geolokalizazioProiektua;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputListener;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;

import kotlin.io.path.IllegalFileNameException;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

// TODO: Auto-generated Javadoc
/**
 * 
 * @author Ioritz Lopetegi
 */
public class Mapa extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** content pane. */
	private JPanel contentPane;

	/**  map viewer. */
	JXMapViewer mapViewer = new JXMapViewer();

	/**
	 * Maparen Frame-a sortu.
	 */
	public Mapa() {
		setTitle("Mapa");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\1ag3.iorilope\\Downloads\\icons8-mapa-16 (1).png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 870, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);

		mapViewer = new JXMapViewer();
		mapViewer.setBounds(10, 26, 824, 406);
		TileFactoryInfo info = new OSMTileFactoryInfo();
		DefaultTileFactory tileFactory = new DefaultTileFactory(info);
		mapViewer.setTileFactory(tileFactory);

//Oraingoz Ibarra erakusten du konprobtazen duen ikusteko

		mapViewer.setTileFactory(tileFactory);
		GeoPosition geoPosition = new GeoPosition(43.1316471, -2.0622574);

		mapViewer.setAddressLocation(geoPosition);
		mapViewer.setZoom(3);

		MouseInputListener mm = new PanMouseInputListener(mapViewer);
		mapViewer.addMouseListener(mm);
		mapViewer.addMouseMotionListener(mm);
		mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));

		panel.add(mapViewer);

		/**
		 * Google-eko bista desberdinak ezarri
		 */

		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TileFactoryInfo info;
				int index = comboBox.getSelectedIndex();
				if (index == 0) {
					info = new OSMTileFactoryInfo();

				} else if (index == 1) {
					info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.MAP);

				} else if (index == 2) {
					info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.HYBRID);

				} else {
					info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.SATELLITE);
				}

				DefaultTileFactory tileFactory = new DefaultTileFactory(info);
				mapViewer.setTileFactory(tileFactory);
				mapViewer.setZoom(3);

			}
		});

		/**
		 * Aukerak gehitu Gure ComboBox-era
		 */
		comboBox.setModel(
				new DefaultComboBoxModel(new String[] { "Open Street", "Virtual Earth", "Hybrid", "Satellite" }));

		comboBox.setBounds(723, 0, 111, 22);
		panel.add(comboBox);

	}

	/**
	 * Mapa berri bat hasieratzen du
	 *
	 * @param latitudmapa, textfield-etikan pasatako latitudea
	 * @param longitudmapa textfield-etikan pasatako longitudea;
	 */
	public Mapa(Double latitudmapa, Double longitudmapa) {

		if (latitudmapa == null || longitudmapa == null) {
			return;
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 870, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);

		mapViewer = new JXMapViewer();
		mapViewer.setBounds(10, 26, 824, 406);
		TileFactoryInfo info = new OSMTileFactoryInfo();
		DefaultTileFactory tileFactory = new DefaultTileFactory(info);
		mapViewer.setTileFactory(tileFactory);

		mapViewer.setTileFactory(tileFactory);
		GeoPosition geoPosition = new GeoPosition(latitudmapa, longitudmapa);

		mapViewer.setAddressLocation(geoPosition);
		mapViewer.setZoom(3);

		MouseInputListener mm = new PanMouseInputListener(mapViewer);
		mapViewer.addMouseListener(mm);
		mapViewer.addMouseMotionListener(mm);
		mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));

		panel.add(mapViewer);

		/**
		 * Google-eko bista desberdinak ezarri
		 */

		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TileFactoryInfo info;
				int index = comboBox.getSelectedIndex();
				if (index == 0) {
					info = new OSMTileFactoryInfo();

				} else if (index == 1) {
					info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.MAP);

				} else if (index == 2) {
					info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.HYBRID);

				} else {
					info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.SATELLITE);
				}

				DefaultTileFactory tileFactory = new DefaultTileFactory(info);
				mapViewer.setTileFactory(tileFactory);
				mapViewer.setZoom(3);

			}
		});

		/**
		 * Aukerak gehitu Gure ComboBox-era
		 */

		comboBox.setModel(
				new DefaultComboBoxModel(new String[] { "Open Street", "Virtual Earth", "Hybrid", "Satellite" }));

		comboBox.setBounds(723, 0, 111, 22);
		panel.add(comboBox);

	}

	/**
	 * Sets the map location.
	 *
	 * @param latitude
	 * @param longitude
	 */
	public void setMapLocation(double latitude, double longitude) {
		GeoPosition geoPosition = new GeoPosition(latitude, longitude);
		mapViewer.setAddressLocation(geoPosition);
	}
}
