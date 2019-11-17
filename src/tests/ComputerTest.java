/*
 * TCSS 372
 * Project 1
 * MIPS Simulator
 * 
 */
 package tests;

/**
 * This is test class for computer Class.
 * @author Curran Seam
 * @author Rohan Seam
 * @author Sharanjit Singh
 * 
 */

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


import structures.BitString;
import structures.Computer;
import structures.Simulator;

class ComputerTest {
	

	
	/**
	 * This method testing the add method.
	 */
	@Test
	public void testexecuteAdd() {	
		Computer comp = new Computer();
		
		Simulator sim = new Simulator();
		
		
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
	
		sim.getComputer().executeSw(0, addi1);
		sim.getComputer().executeSw(1,addi2);
		sim.getComputer().executeSw(2,addi3);
		sim.getComputer().executeSw(3,addi4);
		
		
		sim.getComputer().executeSw(4,add);
		sim.getComputer().executeSw(5,add1);
		sim.getComputer().executeSw(6,add2);
		sim.getComputer().executeSw(7,add3);
		
		
		
		sim.getComputer().executeSw(8, exit1);
		sim.getComputer().executeSw(9, exit2);
		sim.getComputer().executeSw(10, exit3);
		sim.getComputer().executeSw(11, exit4);

		for (int i = 0 ; i< 3;i++) {
			sim.execute();
			

		}
		assertArrayEquals("00000000000000000000000000010100".toCharArray(), sim.getComputer().getRegister(18).getBits());
		
	}
	
	
	/**
	 * This method test when add method is  in imm mode.
	 */
	@Test
	public void testexecuteAddi() {
		Computer comp = new Computer();
		Simulator sim = new Simulator();
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
	
		sim.getComputer().executeSw(0,addi1);
		sim.getComputer().executeSw(1,addi2);
		sim.getComputer().executeSw(2,addi3);
		sim.getComputer().executeSw(3,addi4);
		
		sim.getComputer().executeSw(4, exit1);
		sim.getComputer().executeSw(5, exit2);
		sim.getComputer().executeSw(6, exit3);
		sim.getComputer().executeSw(7, exit4);

		
		for (int i = 0 ; i< 2;i++) {
			sim.execute();
			

		}
		assertArrayEquals("00000000000000000000000000001010".toCharArray(),sim.getComputer().getRegister(16).getBits());
	}
	/**
	 * This method test add in unsigned.
	 */
	@Test
	public void testexecuteAddu() {
	
		
		Simulator sim = new Simulator();
		
		BitString addi1 = new BitString();
		BitString addi2 = new BitString();
		BitString addi3 = new BitString();
		BitString addi4 = new BitString();
		
		BitString addi11 = new BitString();
		BitString addi22 = new BitString();
		BitString addi33 = new BitString();
		BitString addi44 = new BitString();
		
		
		BitString addu1 = new BitString();
		BitString addu2 = new BitString();
		BitString addu3 = new BitString();
		BitString addu4 = new BitString();
		
		
		
		BitString exit1 = new BitString();
		BitString exit2 = new BitString();
		BitString exit3 = new BitString();
		BitString exit4 = new BitString();
		
	// addi s0,s0,9    00100010 00010000 00000000 00001001
	// addi s1,s1,9     00100010 00110001 00000000 00001001                00110010 00110001 00000000 00001001
	// addu s2,s1,s0	00000010 00110000 10010000 00100001
	
		
		
		addi1.setBits("00100010".toCharArray());
		addi2.setBits("00010000".toCharArray());
		addi3.setBits("00000000".toCharArray());
		addi4.setBits("00001001".toCharArray());
		
		addi11.setBits("00100010".toCharArray());
		addi22.setBits("00110001".toCharArray());
		addi33.setBits("00000000".toCharArray());
		addi44.setBits("00001001".toCharArray());
		
		addu1.setBits("00000010".toCharArray());
		addu2.setBits("00110000".toCharArray());
		addu3.setBits("10010000".toCharArray());
		addu4.setBits("00100001".toCharArray());
		
	
		
		
		exit1.setBits("00000000".toCharArray());
		exit2.setBits("00000000".toCharArray());
		exit3.setBits("00000000".toCharArray());
		exit4.setBits("00000000".toCharArray());
	
		sim.getComputer().executeSw(0,addi1);
		sim.getComputer().executeSw(1,addi2);
		sim.getComputer().executeSw(2,addi3);
		sim.getComputer().executeSw(3,addi4);
		
		sim.getComputer().executeSw(4,addi11);
		sim.getComputer().executeSw(5,addi22);
		sim.getComputer().executeSw(6,addi33);
		sim.getComputer().executeSw(7,addi44);
		
		sim.getComputer().executeSw(8,addu1);
		sim.getComputer().executeSw(9,addu2);
		sim.getComputer().executeSw(10,addu3);
		sim.getComputer().executeSw(11,addu4);
		
		
		
		sim.getComputer().executeSw(12, exit1);
		sim.getComputer().executeSw(13, exit2);
		sim.getComputer().executeSw(14, exit3);
		sim.getComputer().executeSw(15, exit4);
		
		for (int i = 0 ; i< 4;i++) {
			sim.execute();
			

		}
		assertArrayEquals("00000000000000000000000000010010".toCharArray(),sim.getComputer().getRegister(18).getBits());
		
		
	}
	
	
	
	
	/**
	 * This method test add in immediate unsigned.
	 */
	@Test
	public void testexecuteAddiu() {
		
		
		Simulator sim = new Simulator();
		BitString addi1 = new BitString();
		BitString addi2 = new BitString();
		BitString addi3 = new BitString();
		BitString addi4 = new BitString();
		
		BitString addu1 = new BitString();
		BitString addu2 = new BitString();
		BitString addu3 = new BitString();
		BitString addu4 = new BitString();
		
		BitString exit1 = new BitString();
		BitString exit2 = new BitString();
		BitString exit3 = new BitString();
		BitString exit4 = new BitString();
		
	// addi s0,s0,9    00100010 00010000 00000000 00001001

	// addiu s2,s0,9	00100110 00010010 00000000 00001001
	
		
		
		addi1.setBits("00100010".toCharArray());
		addi2.setBits("00010000".toCharArray());
		addi3.setBits("00000000".toCharArray());
		addi4.setBits("00001001".toCharArray());
	
		
		addu1.setBits("00100110".toCharArray());
		addu2.setBits("00010010".toCharArray());
		addu3.setBits("00000000".toCharArray());
		addu4.setBits("00001001".toCharArray());
		
		exit1.setBits("00000000".toCharArray());
		exit2.setBits("00000000".toCharArray());
		exit3.setBits("00000000".toCharArray());
		exit4.setBits("00000000".toCharArray());
	
		sim.getComputer().executeSw(0,addi1);
		sim.getComputer().executeSw(1,addi2);
		sim.getComputer().executeSw(2,addi3);
		sim.getComputer().executeSw(3,addi4);
		
		sim.getComputer().executeSw(4,addu1);
		sim.getComputer().executeSw(5,addu2);
		sim.getComputer().executeSw(6,addu3);
		sim.getComputer().executeSw(7,addu4);
		
		sim.getComputer().executeSw(8, exit1);
		sim.getComputer().executeSw(9, exit2);
		sim.getComputer().executeSw(10, exit3);
		sim.getComputer().executeSw(11, exit4);

		for (int i = 0 ; i < 3;i++) {
			sim.execute();
			

		}
		assertArrayEquals("00000000000000000000000000010010".toCharArray(),sim.getComputer().getRegister(18).getBits());
		
		
	}
	
	/**
	 * This test method testing the or method.
	 */
	@Test
	public void testexecuteOr() {
		Simulator sim = new Simulator();
		
		
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
		
		
		//00100010 00010000 00000000 00001010  addi s0 s0 10
		// i  addi s2 s2 20    00100010 01010010 00000000 00010100
		
		// or s3 s0 s2   00000010 00010010 10011000 00100101
		
		
		
		addi1.setBits("00100010".toCharArray());
		addi2.setBits("00010000".toCharArray());
		addi3.setBits("00000000".toCharArray());
		addi4.setBits("00001010".toCharArray());
		
		addi11.setBits("00100010".toCharArray());
		addi22.setBits("01010010".toCharArray());
		addi33.setBits("00000000".toCharArray());
		addi44.setBits("00010100".toCharArray());
		
		or1.setBits("00000010".toCharArray());
		or2.setBits("00010010".toCharArray());
	    or3.setBits("10011000".toCharArray());
		or4.setBits("00100101".toCharArray());
		
		
		
		exit1.setBits("00000000".toCharArray());
		exit2.setBits("00000000".toCharArray());
		exit3.setBits("00000000".toCharArray());
		exit4.setBits("00000000".toCharArray());
	
		
		sim.getComputer().executeSw(0,addi1);
		sim.getComputer().executeSw(1,addi2);
		sim.getComputer().executeSw(2,addi3);
		sim.getComputer().executeSw(3,addi4);
		
		
		sim.getComputer().executeSw(4,addi11);
		sim.getComputer().executeSw(5,addi22);
		sim.getComputer().executeSw(6,addi33);
		sim.getComputer().executeSw(7,addi44);
		
		sim.getComputer().executeSw(8,or1);
		sim.getComputer().executeSw(9,or2);
		sim.getComputer().executeSw(10,or3);
		sim.getComputer().executeSw(11,or4);
		
		sim.getComputer().executeSw(12, exit1);
		sim.getComputer().executeSw(13, exit2);
		sim.getComputer().executeSw(14, exit3);
		sim.getComputer().executeSw(15, exit4);

		
		
		for (int i = 0 ; i < 4;i++) {
			sim.execute();
		}
		assertArrayEquals("00000000000000000000000000011110".toCharArray(),sim.getComputer().getRegister(19).getBits());
		
	}
	
	/**
	 * This test method testing the or in immediate method.
	 */
	@Test
	public void testexecuteOri() {
		Simulator sim = new Simulator();
		
		BitString or1 = new BitString();
		BitString or2 = new BitString();
		BitString or3 = new BitString();
		BitString or4 = new BitString();
		
		BitString addi1 = new BitString();
		BitString addi2 = new BitString();
		BitString addi3 = new BitString();
		BitString addi4 = new BitString();

		BitString exit1 = new BitString();
		BitString exit2 = new BitString();
		BitString exit3 = new BitString();
		BitString exit4 = new BitString();
		
	
		//"000000, 10000, 10010, 10011, 00000, 100101"
		//00100010 00010000 00000000 00001010  addi s0 s0 10
		// ori s3 s0 20   00110110 00010011 00000000 00010100
	
		addi1.setBits("00100010".toCharArray());
		addi2.setBits("00010000".toCharArray());
		addi3.setBits("00000000".toCharArray());
		addi4.setBits("00001010".toCharArray());
		
		
		or1.setBits("00110110".toCharArray());
		or2.setBits("00010011".toCharArray());
	    or3.setBits("00000000".toCharArray());
		or4.setBits("00010100".toCharArray());
		
		
		
		exit1.setBits("00000000".toCharArray());
		exit2.setBits("00000000".toCharArray());
		exit3.setBits("00000000".toCharArray());
		exit4.setBits("00000000".toCharArray());
	
		
		sim.getComputer().executeSw(0,addi1);
		sim.getComputer().executeSw(1,addi2);
		sim.getComputer().executeSw(2,addi3);
		sim.getComputer().executeSw(3,addi4);

		sim.getComputer().executeSw(4,or1);
		sim.getComputer().executeSw(5,or2);
		sim.getComputer().executeSw(6,or3);
		sim.getComputer().executeSw(7,or4);
		
		sim.getComputer().executeSw(8, exit1);
		sim.getComputer().executeSw(9, exit2);
		sim.getComputer().executeSw(10, exit3);
		sim.getComputer().executeSw(11, exit4);

		for (int i = 0 ; i < 3;i++) {
			sim.execute();
		}
		assertArrayEquals("00000000000000000000000000011110".toCharArray(),sim.getComputer().getRegister(19).getBits());
		
	}
	
	/**
	 * This method testing And method.
	 */
	@Test
	public void testexecuteAnd() {
		
		Simulator sim = new Simulator();

		BitString addi1 = new BitString();
		BitString addi2 = new BitString();
		BitString addi3 = new BitString();
		BitString addi4 = new BitString();
		
		
		BitString addi11 = new BitString();
		BitString addi22 = new BitString();
		BitString addi33 = new BitString();
		BitString addi44= new BitString();
		
		BitString or1 = new BitString();
		BitString or2 = new BitString();
		BitString or3 = new BitString();
		BitString or4 = new BitString();
		
	
		BitString exit1 = new BitString();
		BitString exit2 = new BitString();
		BitString exit3 = new BitString();
		BitString exit4 = new BitString();
	
		//"000000, 10000, 10010, 10011, 00000, 100101"
		//00100010 00010000 00000000 00000010  addi s0 s0 2
		// addi s2 s2 3    00100010 01010010 00000000 00000011
		// and s3 s2 s0   00000010 01010000 10011000 00100100
		addi1.setBits("00100010".toCharArray());
		addi2.setBits("00010000".toCharArray());
		addi3.setBits("00000000".toCharArray());
		addi4.setBits("00000010".toCharArray());
		
		addi11.setBits("00100010".toCharArray());
		addi22.setBits("01010010".toCharArray());
		addi33.setBits("00000000".toCharArray());
		addi44.setBits("00000011".toCharArray());
		
		
		or1.setBits("00000010".toCharArray());
		or2.setBits("01010000".toCharArray());
	    or3.setBits("10011000".toCharArray());
		or4.setBits("00100100".toCharArray());
		
		
		
		exit1.setBits("00000000".toCharArray());
		exit2.setBits("00000000".toCharArray());
		exit3.setBits("00000000".toCharArray());
		exit4.setBits("00000000".toCharArray());
	
		
		sim.getComputer().executeSw(0,addi1);
		sim.getComputer().executeSw(1,addi2);
		sim.getComputer().executeSw(2,addi3);
		sim.getComputer().executeSw(3,addi4);
		
		
		sim.getComputer().executeSw(4,addi11);
		sim.getComputer().executeSw(5,addi22);
		sim.getComputer().executeSw(6,addi33);
		sim.getComputer().executeSw(7,addi44);
		
		
		
		sim.getComputer().executeSw(8,or1);
		sim.getComputer().executeSw(9,or2);
		sim.getComputer().executeSw(10,or3);
		sim.getComputer().executeSw(11,or4);
		
		sim.getComputer().executeSw(12, exit1);
		sim.getComputer().executeSw(13, exit2);
		sim.getComputer().executeSw(14, exit3);
		sim.getComputer().executeSw(15, exit4);

		for (int i = 0 ; i < 4;i++) {
			sim.execute();
		}
		assertArrayEquals("00000000000000000000000000000010".toCharArray(),sim.getComputer().getRegister(19).getBits());
		
	}
	/**
	 * This method testing And method in immediate mode.
	 */

	@Test
	public void testexecuteAndi() {
		
		Simulator sim = new Simulator();
		BitString or1 = new BitString();
		BitString or2 = new BitString();
		BitString or3 = new BitString();
		BitString or4 = new BitString();
		
		BitString addi1 = new BitString();
		BitString addi2 = new BitString();
		BitString addi3 = new BitString();
		BitString addi4 = new BitString();

		BitString exit1 = new BitString();
		BitString exit2 = new BitString();
		BitString exit3 = new BitString();
		BitString exit4 = new BitString();
		
	
		//"000000, 10000, 10010, 10011, 00000, 100101"
		//00100010 00010000 00000000 00001010  addi s0 s0 10
		// andi s3 s0 3   00110010 00010011 00000000 00000011

		addi1.setBits("00100010".toCharArray());
		addi2.setBits("00010000".toCharArray());
		addi3.setBits("00000000".toCharArray());
		addi4.setBits("00001010".toCharArray());
	
		or1.setBits("00110010".toCharArray());
		or2.setBits("00010011".toCharArray());
	    or3.setBits("00000000".toCharArray());
		or4.setBits("00000011".toCharArray());
	
		exit1.setBits("00000000".toCharArray());
		exit2.setBits("00000000".toCharArray());
		exit3.setBits("00000000".toCharArray());
		exit4.setBits("00000000".toCharArray());
	
		
		sim.getComputer().executeSw(0,addi1);
		sim.getComputer().executeSw(1,addi2);
		sim.getComputer().executeSw(2,addi3);
		sim.getComputer().executeSw(3,addi4);
		
	
		sim.getComputer().executeSw(4,or1);
		sim.getComputer().executeSw(5,or2);
		sim.getComputer().executeSw(6,or3);
		sim.getComputer().executeSw(7,or4);
		
		sim.getComputer().executeSw(8, exit1);
		sim.getComputer().executeSw(9, exit2);
		sim.getComputer().executeSw(10, exit3);
		sim.getComputer().executeSw(11, exit4);

		for (int i = 0 ; i < 3;i++) {
			sim.execute();

		}
		assertArrayEquals("00000000000000000000000000000010".toCharArray(),sim.getComputer().getRegister(19).getBits());
	
	}
	/**
	 * This method testing Jr method.
	 */
	@Test
	public void testexecuteJr() {

		Simulator sim = new Simulator();

		BitString addi1 = new BitString();
		BitString addi2 = new BitString();
		BitString addi3 = new BitString();
		BitString addi4 = new BitString();
		
		BitString jr1 = new BitString();
		BitString jr2 = new BitString();
		BitString jr3 = new BitString();
		BitString jr4 = new BitString();

		BitString exit1 = new BitString();
		BitString exit2 = new BitString();
		BitString exit3 = new BitString();
		BitString exit4 = new BitString();
	     
		// addi s2 s2 16        00100010 01010010 00000000 00010000
		//jr s2          00000010 01000000 00000000 00000000

		addi1.setBits("00100010".toCharArray());
		addi2.setBits("01010010".toCharArray());
		addi3.setBits("00000000".toCharArray());
		addi4.setBits("00010000".toCharArray());
		
		
		jr1.setBits("00000010".toCharArray());
		jr2.setBits("01000000".toCharArray());
		jr3.setBits("00000000".toCharArray());
		jr4.setBits("00000000".toCharArray());

		exit1.setBits("00000000".toCharArray());
		exit2.setBits("00000000".toCharArray());
		exit3.setBits("00000000".toCharArray());
		exit4.setBits("00000000".toCharArray());

		sim.getComputer().executeSw(0,addi1);
		sim.getComputer().executeSw(1,addi2);
		sim.getComputer().executeSw(2,addi3);
		sim.getComputer().executeSw(3,addi4);

		sim.getComputer().executeSw(4,jr1);
		sim.getComputer().executeSw(5,jr2);
		sim.getComputer().executeSw(6,jr3);
		sim.getComputer().executeSw(7,jr4);

		sim.getComputer().executeSw(8, exit1);
		sim.getComputer().executeSw(9, exit2);
		sim.getComputer().executeSw(10, exit3);
		sim.getComputer().executeSw(11, exit4);

		for (int i = 0 ; i < 3;i++) {
			sim.execute();
			

		}
		
		assertArrayEquals("00000000000000000000000000010000".toCharArray(),sim.getComputer().getRegister(18).getBits());
		
		
		
	}
	
	
	/**
	 * This method testing executeJ method.
	 */
	@Test
	public void testexecuteJ() {
		Simulator sim = new Simulator();

		BitString addi1 = new BitString();
		BitString addi2 = new BitString();
		BitString addi3 = new BitString();
		BitString addi4 = new BitString();
		
		BitString jr1 = new BitString();
		BitString jr2 = new BitString();
		BitString jr3 = new BitString();
		BitString jr4 = new BitString();

		BitString exit1 = new BitString();
		BitString exit2 = new BitString();
		BitString exit3 = new BitString();
		BitString exit4 = new BitString();
	     
		// addi s2 s2 16             00100010010100100000000000010000
		//j 0          00001000 00000000 00000000 00000000

		addi1.setBits("00100010".toCharArray());
		addi2.setBits("01010010".toCharArray());
		addi3.setBits("00000000".toCharArray());
		addi4.setBits("00010000".toCharArray());
		
		
		jr1.setBits("00001000".toCharArray());
		jr2.setBits("00000000".toCharArray());
		jr3.setBits("00000000".toCharArray());
		jr4.setBits("00000000".toCharArray());

		exit1.setBits("00000000".toCharArray());
		exit2.setBits("00000000".toCharArray());
		exit3.setBits("00000000".toCharArray());
		exit4.setBits("00000000".toCharArray());

		sim.getComputer().executeSw(0,addi1);
		sim.getComputer().executeSw(1,addi2);
		sim.getComputer().executeSw(2,addi3);
		sim.getComputer().executeSw(3,addi4);

		sim.getComputer().executeSw(4,jr1);
		sim.getComputer().executeSw(5,jr2);
		sim.getComputer().executeSw(6,jr3);
		sim.getComputer().executeSw(7,jr4);

		sim.getComputer().executeSw(8, exit1);
		sim.getComputer().executeSw(9, exit2);
		sim.getComputer().executeSw(10, exit3);
		sim.getComputer().executeSw(11, exit4);

		for (int i = 0 ; i < 1;i++) {
			sim.execute();

		}
		System.out.println(sim.getComputer().getPC().getBits());
	
		assertArrayEquals("00000000000000000000000000000100".toCharArray(),sim.getComputer().getPC().getBits());
		
	}
	
	
	/**
	 * This method testing Beq method.
	 */
	@Test
	public void testexecuteBeq() {
		
		Simulator sim = new Simulator();

		BitString addi1 = new BitString();
		BitString addi2 = new BitString();
		BitString addi3 = new BitString();
		BitString addi4 = new BitString();

		
		BitString addi11 = new BitString();
		BitString addi22 = new BitString();
		BitString addi33 = new BitString();
		BitString addi44 = new BitString();
		
		BitString beq1 = new BitString();
		BitString beq2 = new BitString();
		BitString beq3 = new BitString();
		BitString beq4 = new BitString();
	
		BitString exit1 = new BitString();
		BitString exit2 = new BitString();
		BitString exit3 = new BitString();
		BitString exit4 = new BitString();

		// 		 addi s1 s1 1   	00100010 00110001 00000000 00000001  	   
		// loop: addi s0,s0,1       00100010 00010000 00000000 00000001
		// 		 beq s1,s0, -2      00010010 00110000 11111111 11111110
		
		addi1.setBits("00100010".toCharArray());
		addi2.setBits("00110001".toCharArray());
		addi3.setBits("00000000".toCharArray());
		addi4.setBits("00000001".toCharArray());
		
		addi11.setBits("00100010".toCharArray());
		addi22.setBits("00010000".toCharArray());
		addi33.setBits("00000000".toCharArray());
		addi44.setBits("00000001".toCharArray());
		
		beq1.setBits("00010010".toCharArray());
		beq2.setBits("00110000".toCharArray());
		beq3.setBits("11111111".toCharArray());
		beq4.setBits("11111110".toCharArray());
		
		exit1.setBits("00000000".toCharArray());
		exit2.setBits("00000000".toCharArray());
		exit3.setBits("00000000".toCharArray());
		exit4.setBits("00000000".toCharArray());
		
		sim.getComputer().executeSw(0,addi1);
		sim.getComputer().executeSw(1,addi2);
		sim.getComputer().executeSw(2,addi3);
		sim.getComputer().executeSw(3,addi4);
		
		sim.getComputer().executeSw(4,addi11);
		sim.getComputer().executeSw(5,addi22);
		sim.getComputer().executeSw(6,addi33);
		sim.getComputer().executeSw(7,addi44);
		
		sim.getComputer().executeSw(8,beq1);
		sim.getComputer().executeSw(9,beq2);
		sim.getComputer().executeSw(10,beq3);
		sim.getComputer().executeSw(11,beq4);
		
		sim.getComputer().executeSw(12, exit1);
		sim.getComputer().executeSw(13, exit2);
		sim.getComputer().executeSw(14, exit3);
		sim.getComputer().executeSw(15, exit4);


		
		for (int i = 0 ; i < 4;i++) {
			sim.execute();

		}

		assertArrayEquals("00000000000000000000000000000010".toCharArray(),sim.getComputer().getRegister(16).getBits());

	}

	/**
	 * This method testing Bne method.
	 */
	@Test
	public void testexecuteBne() {
		
		Simulator sim = new Simulator();

		BitString addi1 = new BitString();
		BitString addi2 = new BitString();
		BitString addi3 = new BitString();
		BitString addi4 = new BitString();

		BitString addi11 = new BitString();
		BitString addi22 = new BitString();
		BitString addi33 = new BitString();
		BitString addi44 = new BitString();
		
		BitString beq1 = new BitString();
		BitString beq2 = new BitString();
		BitString beq3 = new BitString();
		BitString beq4 = new BitString();
	
		BitString exit1 = new BitString();
		BitString exit2 = new BitString();
		BitString exit3 = new BitString();
		BitString exit4 = new BitString();

		// 		 addi s1 s1 2   	00100010 00110001 00000000 00000010  	   
		// loop: addi s0,s0,1       00100010 00010000 00000000 00000001
		// 		 bne s1,s0, -2      00010110 00110000 11111111 11111110
		
		addi1.setBits("00100010".toCharArray());
		addi2.setBits("00110001".toCharArray());
		addi3.setBits("00000000".toCharArray());
		addi4.setBits("00000010".toCharArray());
	
		addi11.setBits("00100010".toCharArray());
		addi22.setBits("00010000".toCharArray());
		addi33.setBits("00000000".toCharArray());
		addi44.setBits("00000001".toCharArray());
		
		beq1.setBits("00010110".toCharArray());
		beq2.setBits("00110000".toCharArray());
		beq3.setBits("11111111".toCharArray());
		beq4.setBits("11111110".toCharArray());
		
		exit1.setBits("00000000".toCharArray());
		exit2.setBits("00000000".toCharArray());
		exit3.setBits("00000000".toCharArray());
		exit4.setBits("00000000".toCharArray());
		
		sim.getComputer().executeSw(0,addi1);
		sim.getComputer().executeSw(1,addi2);
		sim.getComputer().executeSw(2,addi3);
		sim.getComputer().executeSw(3,addi4);
		
		sim.getComputer().executeSw(4,addi11);
		sim.getComputer().executeSw(5,addi22);
		sim.getComputer().executeSw(6,addi33);
		sim.getComputer().executeSw(7,addi44);
		
		sim.getComputer().executeSw(8,beq1);
		sim.getComputer().executeSw(9,beq2);
		sim.getComputer().executeSw(10,beq3);
		sim.getComputer().executeSw(11,beq4);
		
		sim.getComputer().executeSw(12, exit1);
		sim.getComputer().executeSw(13, exit2);
		sim.getComputer().executeSw(14, exit3);
		sim.getComputer().executeSw(15, exit4);

		for (int i = 0 ; i < 4;i++) {
			sim.execute();
			
		}

		assertArrayEquals("00000000000000000000000000000010".toCharArray(),sim.getComputer().getRegister(16).getBits());

	}
	@Test
	public void testexecuteLw() {
		

		Simulator sim = new Simulator();
		//// first 32 bits:	lw a0, 10(s0) = "10001110 00000100 00000000 00001010"
		BitString addi1 = new BitString();
		BitString addi2 = new BitString();
		BitString addi3 = new BitString();
		BitString addi4 = new BitString();
		
		BitString exit1 = new BitString();
		BitString exit2 = new BitString();
		BitString exit3 = new BitString();
		BitString exit4 = new BitString();
		
		addi1.setBits("10001110".toCharArray());
		addi2.setBits("00000100".toCharArray());
		addi3.setBits("00000000".toCharArray());
		addi4.setBits("00001010".toCharArray());
		
		exit1.setBits("00000000".toCharArray());
		exit2.setBits("00000000".toCharArray());
		exit3.setBits("00000000".toCharArray());
		exit4.setBits("00000000".toCharArray());
		
		
		sim.getComputer().executeSw(0,addi1);
		sim.getComputer().executeSw(1,addi2);
		sim.getComputer().executeSw(2,addi3);
		sim.getComputer().executeSw(3,addi4);
		
		

		sim.getComputer().executeSw(4, exit1);
		sim.getComputer().executeSw(5, exit2);
		sim.getComputer().executeSw(6, exit3);
		sim.getComputer().executeSw(7, exit4);
		
		
		for (int i = 0 ; i < 2;i++) {
			sim.execute();
			
		}
		assertEquals(0, sim.getComputer().getRegister(4).getValue2sComp());
		
		
		
		
	}
	
	
	
	
	
	
	
	
}