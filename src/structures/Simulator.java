package structures;
public class Simulator {

	public static void main(String[] args) {

		Computer comp;

		/************************************** */
		/** The next two variables - program and programSize - */
		/** allow someone using the simulator (such as a grader) */
		/** to decide what program will be simulated. */
		/** The simulation must load and execute */
		/** instructions found in the "program" array. */
		/** For grading purposes, it must be possible for me to */
		/**
		 * - paste in a different set of binary strings to replace the existing
		 * ones
		 */
		/** - recompile your program without further changes */
		/** and see the simulator load and execute the new program. */
		/** Your grade will depend largely on how well that works. */
		/************************************** */

//		String program[] = { "0010000000000111", "0010001000000111",
//				"0001010000000001", "0000010000000011", "1111000000100001",
//				"0001000000111111", "0000111111111011", "1111000000100101",
//				"0000000000111001", "1111111111010000" };
		
		// first 32 bits:	lw a0, 10(s0) = "100011, 10000, 00100, 0000000000001010"
		// second 32 bits:	addi s0, s1, 10 = "001000, 10001, 10000, 0000000000001010"
		// third 32 bits:	add s2, s0, s0 = "000000, 10000, 10000, 10010, 00000 100000" 
		// fourth 32 bits:	or  s3, s0, s2 = "000000, 10000, 10010, 10011, 00000, 100101"
		// fifth 32 bits:	ori s4, s3, 1 = "001101, 10011, 10100, 0000000000000001"
		// sixth 32 bits:	and s5, s4, s0 = "000000, 10100, 10000, 10101, 00000, 100100" 
		// 7th 32 bits:		andi s6, s5, -10 = "001100, 10101, 10110, 1111111111110110"	
		String program[] = {"10001110", "00000100", "00000000", "00001010",
				"00100010", "00110000", "00000000", "00001010", 
				"00000010", "00010000", "10010000", "00100000",
				"00000010", "00010010", "10011000", "00100101", 
				"00110110", "01110100", "00000000", "00000001",
				"00000010", "10010000", "10101000", "00100100",
				"00110010", "10110110", "11111111", "11110110"};

		/*
		 * This is the assembly program that was compiled into the binary
		 * program shown above. 
		 * 		.ORIG x3000
		 * 
		 * 		LD R0 START 
		 * 		LD R1 END 
		 * TOP 	ADD R2 R0 R1 
		 * 		BRZ DONE 
		 * 		OUT 
		 * 		ADD R0 R0 -1
		 * 		BRNZP TOP 
		 * DONE HALT
		 * 
		 * START .FILL x39 
		 * END .FILL x-30
		 * 
		 * 		.END
		 */
		comp = new Computer();
//		comp.display();

		/* TO DO: load the instructions in the "program" array */
//		loadInstructions(program, comp);

//		/* Next 3 lines are a test of NOT */
//		/* Once you are confident that single instructions work, you will */
//		/* want to replace this with code that loads all the instructions */
//		/* from the array shown above. */
//		BitString notInstr = new BitString();
//		notInstr.setBits("1001100101111111".toCharArray());
//		comp.loadWord(0, notInstr);
			
//		BitString load = new BitString();
//		load.setBits("0010000000000111".toCharArray());
//		comp.loadWord(0, load);
//		
//		BitString branch = new BitString();
//		branch.setBits("0000111000000100".toCharArray());
//		comp.loadWord(1, branch);
//		
//		BitString halt = new BitString();
//		halt.setBits("1111000000100101".toCharArray());
//		comp.loadWord(2, halt);
//		
//		BitString halt1 = new BitString();
//		halt1.setBits("1111000000100101".toCharArray());
//		comp.loadWord(6, halt1);
		
		for (int i = 0; i < program.length; i++) {
			BitString instruction = new BitString();
			instruction.setBits(program[i].toCharArray());
			comp.executeSw(i, instruction);
		}

		/* execute the program */
		/* During execution, the only output to the screen should be */
		/* the result of executing OUT. */
		comp.execute();

		/* shows final configuration of computer */
//		comp.display();
	}

}

