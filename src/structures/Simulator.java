/*
 * TCSS 372
 * Project 1
 * MIPS Simulator
 * 
 */
package structures;

/**
 * This class represents the MIPS Simulator.
 * 
 * @version 11/10/19
 * @author Curran Seam
 * @author Rohan Seam
 * @author Sharanjit Singh
 *
 */
public class Simulator {
	
	/**
	 * An instance of the Computer which drives the logic of the simulator.
	 */
	private Computer comp;

	/**
	 * Constructs a Simulator.
	 */
	public Simulator() {
		comp = new Computer();
	}
	
	/**
	 * Starts the simulator by loading the machine code instructions of groups 
	 * of 8 bits into memory.
	 */
	public void start() {
		// first 32 bits:	lw a0, 10(s0) = "100011, 10000, 00100, 0000000000001010"
		// second 32 bits:	addi s0, s1, 10 = "001000, 10001, 10000, 0000000000001010"
		// third 32 bits:	add s2, s0, s0 = "000000, 10000, 10000, 10010, 00000 100000" 
		// fourth 32 bits:	or  s3, s0, s2 = "000000, 10000, 10010, 10011, 00000, 100101"
		// fifth 32 bits:	ori s4, s3, 1 = "001101, 10011, 10100, 0000000000000001"
		// sixth 32 bits:	and s5, s4, s0 = "000000, 10100, 10000, 10101, 00000, 100100" 
		// 7th 32 bits:		andi s6, s5, -10 = "001100, 10101, 10110, 1111111111110110"	
		// 8th 32 bits: loop:	addi s6, s6, -1 = "001000, 10110, 10110, 1111111111111111"	 
		// 9th 32 bits:			bne s6, $zero, loop = "000101 10110 00000 1111111111111110"
		// 					addi s7, s7, -1 = "001000, 10111, 10111, 1111111111111111"
	// 10th 32 bits:	loop2: 	addi s7, s7, 1 = "001000, 10111, 10111, 0000000000000001"
		// 11th 32 bits:		beq s1, s7, loop2 = "000100 10001 10111 1111111111111110"
		// 12th 32 bits: 	addi t1, t1, 56 =  "001000 01001 01001 0000000000111000"
		// 13th 32 bits:	jr t1 = 			"000000 01001 000000000000000 001000"
		//				exit:
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
		
		for (int i = 0; i < program.length; i++) {
			BitString instruction = new BitString();
			instruction.setBits(program[i].toCharArray());
			comp.executeSw(i, instruction);
		}
	}
	
	/**
	 * Executes one instruction of the program.
	 */
	public void execute() {
		/* execute one instruction*/
		comp.execute();
	}
	
	/**
	 * Executes the entire program.
	 */
	public void executeAll() {
		/* execute the program */
		for (int i = 0; i < 19; i++) {
			comp.execute();
		}
	}
	
	/**
	 * Returns the instance of the Computer.
	 * @return Computer
	 */
	public Computer getComputer() {
		return comp;
	}
}
