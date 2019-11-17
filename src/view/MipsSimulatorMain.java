/*
 * TCSS 372
 * Project 1
 * MIPS Simulator
 * 
 */
package view;

import java.awt.EventQueue;

/**
 * Main driver class of the MIPS Simulator.
 * @author Curran Seam
 * @author Rohan Seam
 * @author Sharanjit Singh
 *
 */
public class MipsSimulatorMain {

	/**
	 * Driver method of program. 
	 * Invokes an instance of the MipsSimulatorView.
	 * @param Command line arguments.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MipsSimulatorView();     
            }
        });
	}
}
