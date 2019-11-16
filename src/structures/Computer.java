/*
 * TCSS 372
 * Project 1
 */
package structures;

import java.util.Arrays;

/**
 * Computer class comprises of memory, registers, cc and
 * can execute the instructions based on PC and IR 
 * @author mmuppa, 
 * @author Curran Seam
 * @author Rohan Seam
 * @author Sharanjit Singh
 * 
 */
public class Computer {

	private final static int MAX_MEMORY = 100;
	private final static int MAX_REGISTERS = 32;

	private BitString mRegisters[];
	private BitString mMemory[];
	private BitString mPC;
	private BitString mIR;
//	private BitString mCC;

	/**
	 * Initializes all the memory to 0, registers to 0 to 7
	 * PC, IR to 16 bit 0s and CC to 000 
	 * Represents the initial state 
	 */
	public Computer() {
		mPC = new BitString();
		mPC.setValue(0);
		mIR = new BitString();
		mIR.setValue(0);
//		mCC = new BitString();
//		mCC.setBits(new char[] { '0', '0', '0' });
		mRegisters = new BitString[MAX_REGISTERS];
		for (int i = 0; i < MAX_REGISTERS; i++) {
			mRegisters[i] = new BitString();
			mRegisters[i].setValue(0);
		}

		mMemory = new BitString[MAX_MEMORY];
		for (int i = 0; i < MAX_MEMORY; i++) {
			mMemory[i] = new BitString();
			mMemory[i].setValue(0);
		}
	}

	// TODO - Set CC (remove this after implementing)
//	/**
//	 * Performs not operation by using the data from the register based on bits[7:9] 
//	 * and inverting and storing in the register based on bits[4:6]
//	 */
//	public void executeNot() {
//		BitString destBS = mIR.substring(4, 3);
//		BitString sourceBS = mIR.substring(7, 3);
//		mRegisters[destBS.getValue()] = mRegisters[sourceBS.getValue()].copy();
//		mRegisters[destBS.getValue()].invert();
//		setCC(destBS);
//	}
	
	public void executeAdd() {
		BitString destBS = mIR.substring(16, 5);
		BitString source1BS = mIR.substring(6, 5);
		BitString source2BS = mIR.substring(11, 5);
		int sum = mRegisters[source1BS.getValue()].getValue2sComp()
					+ mRegisters[source2BS.getValue()].getValue2sComp();
		mRegisters[destBS.getValue()].setValue2sComp(sum);
	}
	
	public void executeAddu() {
		BitString destBS = mIR.substring(16, 5);
		BitString source1BS = mIR.substring(6, 5);
		BitString source2BS = mIR.substring(11, 5);
		int sum = mRegisters[source1BS.getValue()].getValue()
					+ mRegisters[source2BS.getValue()].getValue();
		
		mRegisters[destBS.getValue()].setValue(sum);
	}
	
	public void executeAddi() {
		BitString destBS = mIR.substring(11, 5);
		BitString source1BS = mIR.substring(6, 5);
		int immNum = mIR.substring(16, 16).getValue2sComp();
		int sum = mRegisters[source1BS.getValue()].getValue2sComp()
					+ immNum;
		
		mRegisters[destBS.getValue()].setValue2sComp(sum);
	}
	
	public void executeAddiu() {
		BitString destBS = mIR.substring(11, 5);
		BitString source1BS = mIR.substring(6, 5);
		int immNum = mIR.substring(16, 16).getValue();
		int sum = mRegisters[source1BS.getValue()].getValue()
					+ immNum;
		
		mRegisters[destBS.getValue()].setValue(sum);
	}
	
	public void executeAnd() {
		BitString destBS = mIR.substring(16, 5);
		BitString source1BS = mIR.substring(6, 5);
		BitString source2BS = mIR.substring(11, 5);
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < mRegisters[source1BS.getValue()].getLength(); i++) {
			if (mRegisters[source1BS.getValue()].getBits()[i] == '1' 
					&& mRegisters[source2BS.getValue()].getBits()[i] == '1') {
				result.append("1");
			} else {
				result.append("0");	
			}
		}
		char[] binChar = new char[result.length()];
        for (int i = 0; i < result.length(); i++) { 
            binChar[i] = result.charAt(i); 
        }
		mRegisters[destBS.getValue()].setBits(binChar);	
	}
	
	public void executeAndi() {
		BitString destBS = mIR.substring(11, 5);
		BitString source1BS = mIR.substring(6, 5);
		BitString immBS = mIR.substring(16, 16);
		BitString immNum = immBS.signExtend();
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < mRegisters[source1BS.getValue()].getLength(); i++) {
			if (mRegisters[source1BS.getValue()].getBits()[i] == '1' 
					&& immNum.getBits()[i] == '1') {
				result.append("1");
			} else {
				result.append("0");	
			}
		}
		char[] binChar = new char[result.length()];
        for (int i = 0; i < result.length(); i++) { 
            binChar[i] = result.charAt(i); 
        }
		mRegisters[destBS.getValue()].setBits(binChar);	
	}
	
	public void executeOr() {
		BitString destBS = mIR.substring(16, 5);
		BitString source1BS = mIR.substring(6, 5);
		BitString source2BS = mIR.substring(11, 5);
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < mRegisters[source1BS.getValue()].getLength(); i++) {
			if (mRegisters[source1BS.getValue()].getBits()[i] == '1'
					|| mRegisters[source2BS.getValue()].getBits()[i] == '1') {
				result.append("1");
			} else {
				result.append("0");	
			}
		}
		char[] binChar = new char[result.length()];
        for (int i = 0; i < result.length(); i++) { 
            binChar[i] = result.charAt(i); 
        }
		mRegisters[destBS.getValue()].setBits(binChar);
	}
	
	public void executeOri() {
		BitString destBS = mIR.substring(11, 5);
		BitString source1BS = mIR.substring(6, 5);
		BitString immBS = mIR.substring(16, 16);
		BitString immNum = immBS.signExtend();
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < mRegisters[source1BS.getValue()].getLength(); i++) {
			if (mRegisters[source1BS.getValue()].getBits()[i] == '1'
					|| immNum.getBits()[i] == '1') {
				result.append("1");
			} else {
				result.append("0");	
			}
		}
		char[] binChar = new char[result.length()];
        for (int i = 0; i < result.length(); i++) { 
            binChar[i] = result.charAt(i); 
        }
		mRegisters[destBS.getValue()].setBits(binChar);		
	}
	
	// FIX THIS METHOD, NOT LOADING COMPLETELY CORRECTLY
	public void executeLw() {
		BitString destBS = mIR.substring(11, 5);
		BitString baseBS = mIR.substring(6, 5);
		BitString pcOffset = mIR.substring(16, 16);
//		int location = mPC.getValue() + pcOffset.getValue2sComp();
		BitString valueAtLocation = mMemory[baseBS.getValue() 
		                                    + pcOffset.getValue()];
//		System.out.println(valueAtLocation.getValue());
		mRegisters[destBS.getValue()].setValue2sComp(valueAtLocation.getValue2sComp());
//		setCC(destBS);
	}
	
	/**
	 * Loads a 32 bit word into memory at the given address. 
	 * @param address memory address
	 * @param word data or instruction or address to be loaded into memory
	 */
	public void executeSw(int address, BitString word) {
		if (address < 0 || address >= MAX_MEMORY) {
			throw new IllegalArgumentException("Invalid address");
		}
		mMemory[address] = word;
	}
	
//	public void executeBranch() {
//		BitString pcOffset = mIR.substring(7, 9);
//		int negative = mIR.substring(4, 1).getValue();
//		int zero = mIR.substring(5, 1).getValue();
//		int positive = mIR.substring(6, 1).getValue();
//		int cc0 = mCC.substring(0, 1).getValue();
//		int cc1 = mCC.substring(1, 1).getValue();
//		int cc2 = mCC.substring(2, 1).getValue();
//		if (negative + cc0 == 2 || zero + cc1 == 2 || positive + cc2 == 2 ) {
//			mPC.setValue2sComp(mPC.getValue() + pcOffset.getValue2sComp());
//		}
//	}
	
	public void executeBeq() {
		BitString source1BS = mIR.substring(6, 5);
		BitString source2BS = mIR.substring(11, 5);
		BitString labelBS = mIR.substring(16, 16);
		int offset = labelBS.getValue2sComp() * 4;
		
		BitString source1 = mRegisters[source1BS.getValue()];
		BitString source2 = mRegisters[source2BS.getValue()];
		
		if (source1.getValue() == source2.getValue()) {
			mPC.setValue(mPC.getValue() + offset);
		}
	}
	
	public void executeBne() {
		BitString source1BS = mIR.substring(6, 5);
		BitString source2BS = mIR.substring(11, 5);
		BitString labelBS = mIR.substring(16, 16);
		int offset = labelBS.getValue2sComp() * 4;
		
		BitString source1 = mRegisters[source1BS.getValue()];
		BitString source2 = mRegisters[source2BS.getValue()];
		
		if (source1.getValue() != source2.getValue()) {
			mPC.setValue(mPC.getValue() + offset);
		}
	}
	
	public void executeJ() {
		BitString addressBS = mIR.substring(6, 26);
		BitString fullAddressBS = addressBS.signExtend();
		mPC.setValue(fullAddressBS.getValue());
	}
	
	public void executeJr() {
		BitString registerBS = mIR.substring(6, 5);
		BitString addressBS = mRegisters[registerBS.getValue()];
		mPC.setValue(addressBS.getValue());
	}
	
//	public void setCC(BitString destBS) {
//		BitString bitsInRegister = mRegisters[destBS.getValue()];
//		int valueInRegister = bitsInRegister.getValue();
//		if (valueInRegister > 0) {
//			mCC.setBits(new char[] {'0', '0', '1'});
//		} else if (valueInRegister == 0) {
//			mCC.setBits(new char[] {'0', '1', '0'});
//		} else if (valueInRegister < 0) {
//			mCC.setBits(new char[] {'1', '0', '0'});
//		}
//	}
	
//	public void executeOut() {
//		int asciiCode = mRegisters[0].getValue();
//		System.out.println((char) asciiCode);
//	}

	/**
	 * This method will execute all the instructions starting at address 0 
	 * till HALT instruction is encountered. 
	 */
	public void execute() {
		BitString opCodeStr;
		int opCode;
		BitString instruction = new BitString();
		instruction.setBits(new char[0]);
		
		while (true) {
			// Fetch the instruction
			for (int i = 0; i < 4; i++) {
				instruction = instruction.append(mMemory[mPC.getValue()]);
				mPC.addOne();
			}
			mIR = instruction;
			if (mIR.getValue() == 0) {
				System.out.println("null reached");
				return;
			}

			// Decode the instruction's first 4 bits 
			// to figure out the opcode
			opCodeStr = mIR.substring(0, 6);
			opCode = opCodeStr.getValue();

			// What instruction is this?
//			if (opCode == 9) { // NOT
//				executeNot();
//			} else if (opCode == 1) { // ADD
//				executeAdd();
//			} else if (opCode == 2) { // LOAD
//				executeLoad();
//			} else if (opCode == 0) { // BRANCH
//				executeBranch();
//			} else if (opCode == 15) { // TRAP
//				int trapVect = mIR.substring(8, 8).getValue();
//				if (trapVect == 37) {
//					return;
//				} else if (trapVect == 33) {
//					executeOut();
//				}
//			} 
			if (opCode == 35) {
				executeLw();
//				System.out.println(mRegisters[4].getValue2sComp());
			} else if (opCode == 0) {
				int functCode = mIR.substring(26, 6).getValue();
				
				if (functCode == 8) {
					executeJr();
				} else if (functCode == 32) {
					executeAdd();
//					System.out.println(mRegisters[18].getValue2sComp());
				} else if (functCode == 33) {
					executeAddu();
				} else if (functCode == 37) {
					executeOr();
//					System.out.println(mRegisters[19].getValue2sComp());
				} else if (functCode == 36) {
					executeAnd();
//					System.out.println(mRegisters[21].getValue2sComp());
				}
			} else if (opCode == 4) {
				executeBeq();
//				System.out.println(mRegisters[23].getValue2sComp());
			} else if (opCode == 5) {
				executeBne();
//				System.out.println(mRegisters[22].getValue2sComp());
			} else if (opCode == 8) {
				executeAddi();
				System.out.println(mRegisters[9].getValue2sComp());
			} else if (opCode == 12) {
				executeAndi();
//				System.out.println(mRegisters[22].getValue2sComp());
			} else if (opCode == 13) {
				executeOri();
//				System.out.println(mRegisters[20].getValue2sComp());
			}
			instruction = new BitString();
			instruction.setBits(new char[0]);
		}
	}

	/**
	 * Displays the computer's state
	 */
	public void display() {
		System.out.print("\nPC ");
		mPC.display(true);
		System.out.print("   ");

		System.out.print("IR ");
		mPC.display(true);
		System.out.print("   ");

//		System.out.print("CC ");
//		mCC.display(true);
//		System.out.println("   ");

		for (int i = 0; i < MAX_REGISTERS; i++) {
			System.out.printf("R%d ", i);
			mRegisters[i].display(true);
			if (i % 3 == 2) {
				System.out.println();
			} else {
				System.out.print("   ");
			}
		}
		System.out.println();

		for (int i = 0; i < MAX_MEMORY; i++) {
			System.out.printf("%3d ", i);
			mMemory[i].display(true);
			if (i % 3 == 2) {
				System.out.println();
			} else {
				System.out.print("   ");
			}
		}
		System.out.println();

	}
	
	public BitString getRegisters(int register) {
		return mRegisters[register].copy();
	}
	
	public BitString getMemory(int memory) {
		return mMemory[memory].copy();
	}
	
	public BitString getPC() {
		return mPC.copy();
	}
}
