/*
 * TCSS 372
 * Project 1
 * MIPS Simulator
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import structures.Simulator;

/**
 * This class testing Simulator.
 * @author Curran Seam
 * @author Rohan Seam
 * @author Sharanjit Singh
 * 
 */
class SimulatorTest {

	/**
	 * Tests the start method.
	 */
	@Test
	public void testStart() {
		String[] program = {"10001110", "00000100", "00000000", "00001010",
				"00100010", "00110000", "00000000", "00001010", 
				"00000010", "00010000", "10010000", "00100000",
				"00000010", "00010010", "10011000", "00100101", 
				"00110110", "01110100", "00000000", "00000001",
				"00000010", "10010000", "10101000", "00100100",
				"00110010", "10110110", "11111111", "11110110",
				"00100010", "11010110", "11111111", "11111111",
				"00010110", "11000000", "11111111", "11111110",
				"00100010", "11110111", "11111111", "11111111",
				"00100010", "11110111", "00000000", "00000001",
				"00010010", "00110111", "11111111", "11111110",
				"00100001", "00101001", "00000000", "00111000",
				"00000001", "00100000", "00000000", "00001000",
				"00000000", "00000000", "00000000", "00000000"};
		
		Simulator sim = new Simulator();
		sim.start();
		for (int i = 0; i < 60; i++) {
			StringBuilder builder = new StringBuilder();
			char[] bits = sim.getComputer().getMemory(i).getBits();
			for (int j = 0; j < 8; j++) {
				builder.append(bits[j]);
			}
			assertEquals(program[i], builder.toString());
		}
	}
	
	
	/**
	 * Tests the execute method.
	 */
	@Test
	public void testExecute() {
		Simulator sim = new Simulator();
		sim.start();
		sim.execute();
		assertEquals(-112, sim.getComputer().getRegister(4).getValue2sComp());
	}
	
	/**
	 * Tests the executeAll method.
	 */
	@Test
	public void testExecuteAll() {
		Simulator sim = new Simulator();
		sim.start();
		sim.executeAll();
		assertEquals(60, sim.getComputer().getPC().getValue());
	}
}
