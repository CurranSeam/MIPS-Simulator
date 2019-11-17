/*
 * TCSS 372
 * Project 1
 * MIPS Simulator
 * 
 */
package structures;

/**
 * Computer class comprises of memory, registers, and
 * can execute the instructions based on PC and IR 
 * @author mmuppa
 * @author Curran Seam
 * @author Rohan Seam
 * @author Sharanjit Singh
 * 
 */
public class Computer {

	/**
	 * Defines the max amount of memory of the Computer.
	 */
	private final static int MAX_MEMORY = 100;
	
	/**
	 * Defines the max amount of registers of the Computer.
	 */
	private final static int MAX_REGISTERS = 32;

	/**
	 * Collection that represents the Computer's registers.
	 */
	private BitString mRegisters[];
	
	/**
	 * Collection that represents the Computer's memory.
	 */
	private BitString mMemory[];
	
	/**
	 * BitString that represents the program counter of the Computer.
	 */
	private BitString mPC;
	
	/**
	 * BitString that represents the instruction in the instruction register 
	 * of the Computer.
	 */
	private BitString mIR;

	/**
	 * Initializes all the memory to 0, registers to 0 to 32,
	 * and PC and IR to 32 bit 0s.
	 * Represents the initial state of the Computer.
	 */
	public Computer() {
		mPC = new BitString();
		mPC.setValue(0);
		mIR = new BitString();
		mIR.setValue(0);
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
	
	/**
	 * Executes the add instruction of the MIPS ISA.
	 */
	public void executeAdd() {
		BitString destBS = mIR.substring(16, 5);
		BitString source1BS = mIR.substring(6, 5);
		BitString source2BS = mIR.substring(11, 5);
		int sum = mRegisters[source1BS.getValue()].getValue2sComp()
					+ mRegisters[source2BS.getValue()].getValue2sComp();
		mRegisters[destBS.getValue()].setValue2sComp(sum);
	}
	
	/**
	 * Executes the addu instruction of the MIPS ISA.
	 */
	public void executeAddu() {
		BitString destBS = mIR.substring(16, 5);
		BitString source1BS = mIR.substring(6, 5);
		BitString source2BS = mIR.substring(11, 5);
		int sum = mRegisters[source1BS.getValue()].getValue()
					+ mRegisters[source2BS.getValue()].getValue();
		
		mRegisters[destBS.getValue()].setValue(sum);
	}
	
	/**
	 * Executes the addi instruction of the MIPS ISA.
	 */
	public void executeAddi() {
		BitString destBS = mIR.substring(11, 5);
		BitString source1BS = mIR.substring(6, 5);
		int immNum = mIR.substring(16, 16).getValue2sComp();
		int sum = mRegisters[source1BS.getValue()].getValue2sComp()
					+ immNum;
		
		mRegisters[destBS.getValue()].setValue2sComp(sum);
	}
	
	/**
	 * Executes the addiu instruction of the MIPS ISA.
	 */
	public void executeAddiu() {
		BitString destBS = mIR.substring(11, 5);
		BitString source1BS = mIR.substring(6, 5);
		int immNum = mIR.substring(16, 16).getValue();
		int sum = mRegisters[source1BS.getValue()].getValue()
					+ immNum;
		
		mRegisters[destBS.getValue()].setValue(sum);
	}
	
	/**
	 * Executes the and instruction of the MIPS ISA.
	 */
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
	
	/**
	 * Executes the andi instruction of the MIPS ISA.
	 */
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
	
	/**
	 * Executes the or instruction of the MIPS ISA.
	 */
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
	
	/**
	 * Executes the ori instruction of the MIPS ISA.
	 */
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
	
	/**
	 * Executes the lw instruction of the MIPS ISA.
	 */
	public void executeLw() {
		BitString destBS = mIR.substring(11, 5);
		BitString baseBS = mIR.substring(6, 5);
		BitString pcOffset = mIR.substring(16, 16);
		BitString valueAtLocation = mMemory[mRegisters[baseBS.getValue()].getValue2sComp()
		                                    + pcOffset.getValue()];
		mRegisters[destBS.getValue()].setValue2sComp(valueAtLocation.getValue2sComp());
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
	
	/**
	 * Executes the beq instruction of the MIPS ISA.
	 */
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
	
	/**
	 * Executes the bne instruction of the MIPS ISA.
	 */
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
	
	/**
	 * Executes the j instruction of the MIPS ISA.
	 */
	public void executeJ() {
		BitString addressBS = mIR.substring(6, 26);
		BitString fullAddressBS = addressBS.signExtend();
		mPC.setValue(fullAddressBS.getValue());
	}
	
	/**
	 * Executes the jr instruction of the MIPS ISA.
	 */
	public void executeJr() {
		BitString registerBS = mIR.substring(6, 5);
		BitString addressBS = mRegisters[registerBS.getValue()];
		mPC.setValue(addressBS.getValue());
	}

	/**
	 * This method will execute one instruction per call, starting at address 0.
	 */
	public void execute() {
		BitString opCodeStr;
		int opCode;
		BitString instruction = new BitString();
		instruction.setBits(new char[0]);
		
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
		
		if (opCode == 35) {
			executeLw();
		} else if (opCode == 0) {
			int functCode = mIR.substring(26, 6).getValue();
			
			if (functCode == 8) {
				executeJr();
			} else if (functCode == 32) {
				executeAdd();
			} else if (functCode == 33) {
				executeAddu();
			} else if (functCode == 37) {
				executeOr();
			} else if (functCode == 36) {
				executeAnd();
			}
		} else if (opCode == 2) {
			executeJ();
		} else if (opCode == 4) {
			executeBeq();
		} else if (opCode == 5) {
			executeBne();
		} else if (opCode == 8) {
			executeAddi();
		} else if (opCode == 9) {
			executeAddiu();
		} else if (opCode == 12) {
			executeAndi();
		} else if (opCode == 13) {
			executeOri();
		}
		instruction = new BitString();
		instruction.setBits(new char[0]);
	}
	
	/**
	 * Returns the BitString in the given register.
	 * @param register number which is specified in the MIPS ISA.
	 * @return BitString value inside the register.
	 */
	public BitString getRegister(int register) {
		return mRegisters[register].copy();
	}
	
	/**
	 * Returns the BitString in the given memory location.
	 * @param memory location.
	 * @return BitString value inside the memory location.
	 */
	public BitString getMemory(int memory) {
		return mMemory[memory].copy();
	}
	
	/**
	 * Returns the BitString of the program counter.
	 * @return BitString value of pc.
	 */
	public BitString getPC() {
		return mPC.copy();
	}
}
