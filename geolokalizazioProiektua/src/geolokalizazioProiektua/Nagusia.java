/*
nadas ib * 26 abr 2024
 * Ioritz Lopetegi
 */
package geolokalizazioProiektua;

import java.awt.EventQueue;

import javax.swing.*;

import kotlin.LateinitKt;

/**
 * 
 * @author Ioritz Lopetegi
 */
public class Nagusia {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UiInterfazea frame = new UiInterfazea();
					DatuakSartu framedatuaksartu = new DatuakSartu();
					Mapa interfazeaMapa = new Mapa();

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
