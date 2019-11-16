package tests;
import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.Test;


import structures.BitString;
import structures.Computer;

class ComputerTest {
	

	
	/**
	 * This method testing the add method.
	 */
	@Test
	public void testexecuteAdd() {	
		Computer comp = new Computer();
		BitString add = new BitString();
		BitString add1 = new BitString();
		BitString add2 = new BitString();
		BitString add3 = new BitString();
		BitString addi1 = new BitString();
		BitString addi2 = new BitString();
		BitString addi3 = new BitString();
		BitString addi4 = new BitString();
		BitString exit1 = new BitString();
		BitString exit2 = new BitString();
		BitString exit3 = new BitString();
		BitString exit4 = new BitString();
		
	
	
		
		add.setBits("00000010".toCharArray());
		add1.setBits("00010000".toCharArray());
		add2.setBits("10010000".toCharArray());
		add3.setBits("00100000".toCharArray());
		
		addi1.setBits("00100010".toCharArray());
		addi2.setBits("00110000".toCharArray());
		addi3.setBits("00000000".toCharArray());
		addi4.setBits("00001010".toCharArray());
		
		exit1.setBits("00000000".toCharArray());
		exit2.setBits("00000000".toCharArray());
		exit3.setBits("00000000".toCharArray());
		exit4.setBits("00000000".toCharArray());
	
		comp.executeSw(0,addi1);
		comp.executeSw(1,addi2);
		comp.executeSw(2,addi3);
		comp.executeSw(3,addi4);
		
		
		comp.executeSw(4,add);
		comp.executeSw(5,add1);
		comp.executeSw(6,add2);
		comp.executeSw(7,add3);
		
		
		
		comp.executeSw(8, exit1);
		comp.executeSw(9, exit2);
		comp.executeSw(10, exit3);
		comp.executeSw(11, exit4);

		
		
		
		comp.execute();
//		System.out.println(comp.getRegister(18).getBits());
		
		assertArrayEquals("00000000000000000000000000010100".toCharArray(),comp.getRegister(18).getBits());


		
	}
	
	
	/**
	 * This method test when add method is  in imm mode.
	 */
	@Test
	public void testexecuteAddi() {
		Computer comp = new Computer();
		BitString addi1 = new BitString();
		BitString addi2 = new BitString();
		BitString addi3 = new BitString();
		BitString addi4 = new BitString();
		BitString exit1 = new BitString();
		BitString exit2 = new BitString();
		BitString exit3 = new BitString();
		BitString exit4 = new BitString();
		
	
	
		
		
		addi1.setBits("00100010".toCharArray());
		addi2.setBits("00110000".toCharArray());
		addi3.setBits("00000000".toCharArray());
		addi4.setBits("00001010".toCharArray());
		
		exit1.setBits("00000000".toCharArray());
		exit2.setBits("00000000".toCharArray());
		exit3.setBits("00000000".toCharArray());
		exit4.setBits("00000000".toCharArray());
	
		comp.executeSw(0,addi1);
		comp.executeSw(1,addi2);
		comp.executeSw(2,addi3);
		comp.executeSw(3,addi4);
		

		
		
		comp.executeSw(4, exit1);
		comp.executeSw(5, exit2);
		comp.executeSw(6, exit3);
		comp.executeSw(7, exit4);

		
		
		
		comp.execute();
//		System.out.println(comp.getRegister(18).getBits());
		
		assertArrayEquals("00000000000000000000000000001010".toCharArray(),comp.getRegister(16).getBits());
	}
//	
//	@Test
//	public void testexecuteAnd() {
//		BitString add1 = new BitString();
//		Computer comp = new Computer();
//		BitString add2 = new BitString();
//		
//		
//		add1.setBits("00000000000000000000000000000000".toCharArray());
//		add2.setBits("00000000000000000000000000000000".toCharArray());
//		
//		comp.executeSw(0,add1);
//		comp.executeSw(1,add2);
//	    comp.executeAnd();
//	    comp.executeBeq();
//		assertArrayEquals("00000000000000000000000000000000".toCharArray(),comp.getRegister(1).getBits());
//		
//		
//		
//		
//		
//	}
//	
//	
	
	/**
	 * This test method testing the or method.
	 */
	@Test
	public void testexecuteOr() {
		Computer comp = new Computer();
		BitString or1 = new BitString();
		BitString or2 = new BitString();
		BitString or3 = new BitString();
		BitString or4 = new BitString();
		
		BitString addi1 = new BitString();
		BitString addi2 = new BitString();
		BitString addi3 = new BitString();
		BitString addi4 = new BitString();
		
		
		BitString addi11 = new BitString();
		BitString addi22 = new BitString();
		BitString addi33 = new BitString();
		BitString addi44= new BitString();
		
	
		BitString exit1 = new BitString();
		BitString exit2 = new BitString();
		BitString exit3 = new BitString();
		BitString exit4 = new BitString();
		
	
		//"000000, 10000, 10010, 10011, 00000, 100101"
		
		
		//00100010 00010000 00000000 00001010  s0 s0 10
		// s2 s2 20    00100010 01010010 00000000 00010100
		
		
		
		addi1.setBits("00100010".toCharArray());
		addi2.setBits("00010000".toCharArray());
		addi3.setBits("00000000".toCharArray());
		addi4.setBits("00001010".toCharArray());
		
		addi11.setBits("00100010".toCharArray());
		addi22.setBits("01010010".toCharArray());
		addi33.setBits("00000000".toCharArray());
		addi44.setBits("00010100".toCharArray());
		
		exit1.setBits("00000000".toCharArray());
		exit2.setBits("00000000".toCharArray());
		exit3.setBits("00000000".toCharArray());
		exit4.setBits("00000000".toCharArray());
	
		
		comp.executeSw(0,addi1);
		comp.executeSw(1,addi2);
		comp.executeSw(2,addi3);
		comp.executeSw(3,addi4);
		
		
		comp.executeSw(4,addi11);
		comp.executeSw(5,addi22);
		comp.executeSw(6,addi33);
		comp.executeSw(7,addi44);
		
		
		
		comp.executeSw(8, exit1);
		comp.executeSw(9, exit2);
		comp.executeSw(10, exit3);
		comp.executeSw(11, exit4);

		
		
		
		comp.execute();
//		System.out.println(comp.getRegister(18).getBits());
		
		assertArrayEquals("00000000000000000000000000010100".toCharArray(),comp.getRegister(18).getBits());
		
	}
	
	
	//needs to fix - it is same  code as or for now.
	@Test
	public void testexecuteAnd() {
		
		Computer comp = new Computer();
		BitString or1 = new BitString();
		BitString or2 = new BitString();
		BitString or3 = new BitString();
		BitString or4 = new BitString();
		
		BitString addi1 = new BitString();
		BitString addi2 = new BitString();
		BitString addi3 = new BitString();
		BitString addi4 = new BitString();
		
		
		BitString addi11 = new BitString();
		BitString addi22 = new BitString();
		BitString addi33 = new BitString();
		BitString addi44= new BitString();
		
	
		BitString exit1 = new BitString();
		BitString exit2 = new BitString();
		BitString exit3 = new BitString();
		BitString exit4 = new BitString();
		
	
		//"000000, 10000, 10010, 10011, 00000, 100101"
		
		
		//00100010 00010000 00000000 00001010  s0 s0 10
		// s2 s2 20    00100010 01010010 00000000 00010100
		
		
		
		addi1.setBits("00100010".toCharArray());
		addi2.setBits("00010000".toCharArray());
		addi3.setBits("00000000".toCharArray());
		addi4.setBits("00001010".toCharArray());
		
		addi11.setBits("00100010".toCharArray());
		addi22.setBits("01010010".toCharArray());
		addi33.setBits("00000000".toCharArray());
		addi44.setBits("00010100".toCharArray());
		
		exit1.setBits("00000000".toCharArray());
		exit2.setBits("00000000".toCharArray());
		exit3.setBits("00000000".toCharArray());
		exit4.setBits("00000000".toCharArray());
	
		
		comp.executeSw(0,addi1);
		comp.executeSw(1,addi2);
		comp.executeSw(2,addi3);
		comp.executeSw(3,addi4);
		
		
		comp.executeSw(4,addi11);
		comp.executeSw(5,addi22);
		comp.executeSw(6,addi33);
		comp.executeSw(7,addi44);
		
		
		
		comp.executeSw(8, exit1);
		comp.executeSw(9, exit2);
		comp.executeSw(10, exit3);
		comp.executeSw(11, exit4);

		
		
		
		comp.execute();
//		System.out.println(comp.getRegister(18).getBits());
		
		assertArrayEquals("00000000000000000000000000010100".toCharArray(),comp.getRegister(18).getBits());
		
	}
	
	
	
	
	
	@Test
	public void testexecutebeq() {
		Computer comp = new Computer();
		BitString num1 = new BitString();
		BitString num2 = new BitString();
		BitString num3 = new BitString();
		BitString num4 = new BitString();
		
//		BitString num1 = new BitString();
//		BitString num2 = new BitString();
//		BitString num3 = new BitString();
//		BitString num4 = new BitString();
//		
//		
//		BitString num1 = new BitString();
//		BitString num2 = new BitString();
//		BitString num3 = new BitString();
//		BitString num4 = new BitString();
		
		  
		
		
		
		
		
		
		
		
	}
	

}
