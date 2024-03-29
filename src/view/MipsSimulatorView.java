/*
 * TCSS 372
 * Project 1
 * MIPS Simulator
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import structures.BitString;
import structures.Simulator;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This class represents the MIPS Simulator GUI.
 * 
 * @version 11/10/19
 * @author Curran Seam
 * @author Rohan Seam
 * @author Sharanjit Singh
 *
 */
public class MipsSimulatorView extends JFrame{

    /**
     * A generated serial version UID for object Serialization.
     */
    private static final long serialVersionUID = -8255134897469998994L;
    
    // constants to capture screen dimensions
    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    
    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    
	/**
	 * Main program data to be contained.
	 */
	private JTextArea myData;
	
	/**
	 * Data Panel for holding the editor tab.
	 */
	private JPanel myDataPanel;
	
	/**
	 * An instance of the MIPS simulator.
	 */
	private Simulator mySimulator;
	
	/**
	 * The program counter of the Computer.
	 */
	private JTextArea myPC;
	
	/**
	 * Registers in the MIPS simulator.
	 */
	private ArrayList<JTextArea> myRegisters;
	
	/**
	 * Memory in the MIPS simulator.
	 */
	private ArrayList<JTextArea> myMemory;
	
	/**
	 * Tabbed pane to display the data panel.
	 */
	private JTabbedPane myEditorTab;
	
	/**
	 * Tabbed pane to display the registers.
	 */
	private JTabbedPane myRegisterTab;
	
	/**
	 * Tabbed pane to display memory.
	 */
	private JTabbedPane myMemoryTab;
    
	/**
	 * Constructs a MipsSimulatorView.
	 */
    public MipsSimulatorView() {
    	super();
    	setupGUI();
    }
    
    /**
     * Sets up the simulator GUI.
     */
    private void setupGUI() {
        // replace the default JFrame icon
        final ImageIcon img = new ImageIcon("MIPSlogo.png");
        setIconImage(img.getImage());
        
        setTitle("MIPS Simulator");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(makeExecutionPanel(), BorderLayout.NORTH);
        
        myDataPanel = new JPanel(new BorderLayout());
        myEditorTab = new JTabbedPane();
        myEditorTab.addTab("", myDataPanel); 
        myEditorTab.setBorder(new EmptyBorder(0, 10, 0, 10));
        myRegisterTab = new JTabbedPane();
        myRegisterTab.setBorder(new EmptyBorder(0, 10, 0, 10));
        myMemoryTab = new JTabbedPane();
        myMemoryTab.setBorder(new EmptyBorder(0, 10, 15, 10));
        myData = new JTextArea(32, 50);
        myData.setEditable(false);
        myData.setFont(myData.getFont().deriveFont(15f));
        JPanel centerPanel = new JPanel();
        
        myDataPanel.add(myData, BorderLayout.CENTER);
        centerPanel.add(myEditorTab, BorderLayout.WEST);
        
        myRegisters = new ArrayList<>();
        myMemory = new ArrayList<>();
        myRegisterTab.addTab("Registers", makeInfoPanel());
        centerPanel.add(myRegisterTab, BorderLayout.WEST);
        
        add(centerPanel, BorderLayout.CENTER);
        
        myMemoryTab.addTab("Memory", makeMemoryPanel());
        add(myMemoryTab, BorderLayout.SOUTH);
        
        mySimulator = new Simulator();
        
        final JMenuBar menuBar = new JMenuBar();
		final JMenu settingsMenu = new JMenu();
		settingsMenu.setText("Settings");
		final JMenuItem aboutPage = createAboutPageItem();
		final JMenuItem morePage = createMorePageItem();
		settingsMenu.add(aboutPage);
		settingsMenu.add(morePage);
		menuBar.add(settingsMenu);
		this.setJMenuBar(menuBar);

        // adjust size to just fit
        pack();
        
        // make the GUI so that it cannot be resized by the user dragging a corner
        setResizable(false);
        
        // position the frame in the center of the screen
        setLocation(SCREEN_SIZE.width / 2 - getWidth() / 2,
                    SCREEN_SIZE.height / 2 - getHeight() / 2);
        setVisible(true);
    }
    
    /**
     * Creates the top panel of the simulator which holds the execution buttons.
     * @return JPanel
     */
    private JPanel makeExecutionPanel() {
        final JPanel p = new JPanel((LayoutManager) new FlowLayout(FlowLayout.LEFT));
        p.setBackground(Color.LIGHT_GRAY);
        
        JLabel groupName = new JLabel("DESI CREW MIPS SIMULATOR");
        groupName.setBorder(new EmptyBorder(10, 10, 10, 50));
        p.add(groupName);
        
        final JButton loadButton = new JButton("Load Program");
        loadButton.setBackground(Color.orange);
        
        final JButton runButton = new JButton("Execute Next");
        runButton.setBackground(Color.YELLOW);
        
        final JButton executeButton = new JButton("Execute All");
        executeButton.setBackground(Color.GREEN);
        
        p.add(loadButton);
        p.add(runButton);
        p.add(executeButton);
        
        runButton.setEnabled(false);
        executeButton.setEnabled(false);
        
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	myData.setText("	lw $a0, 10(s0) \n" + 
            			"	addi $s0, $s1, 10 \n" + 
            			"	add $s2, $s0, $s0 \n" + 
            			"	or  $s3, $s0, $s2 \n" + 
            			"	ori $s4, $s3, 1 \n" + 
            			"	and $s5, $s4, $s0 \n" + 
            			"	andi $s6, $s5, -10 \n" + 
            			"loop:	addi $s6, $s6, -1 \n" + 
            			"	bne $s6, $zero, loop \n" + 
            			"	addi $s7, $s7, -1 \n" + 
            			"loop2:	addi $s7, $s7, 1 \n" + 
            			"	beq $s1, $s7, loop2 \n" + 
            			"	addi $t1, $t1, 56 \n" + 
            			"	jr $t1 \n" + 
            			"exit:             ");
            	myData.setEditable(false);
            	myEditorTab.setTitleAt(0, "Program.asm");
            	loadButton.setEnabled(false);
            	runButton.setEnabled(true);
                executeButton.setEnabled(true);
            	mySimulator.start();
            	updateMemory();
            }
        });
        
        runButton.addActionListener(new ActionListener() {
        	int clicked = 0;
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	clicked++;
            	if (clicked == 19) {
            		runButton.setEnabled(false);
            	}
            	mySimulator.execute();
            	updateInfo();
            	updateMemory();
            	executeButton.setEnabled(false);
            }
        });
        
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	mySimulator.executeAll();
            	updateInfo();
            	updateMemory();
            	executeButton.setEnabled(false);
            	runButton.setEnabled(false);
            }
        });
        
    	JLabel name = new JLabel("PC");
    	name.setBorder(new EmptyBorder(0, 110, 0, 0));
    	myPC = new JTextArea("0");
    	myPC.setEditable(false);
    	
    	p.add(name);
    	p.add(myPC);
        
        return p;
    }
    
    /**
     * Creates the info panel which contains the registers and their values.
     * @return JPanel
     */
    private JPanel makeInfoPanel() {
    	String[] registerNames = {"$zero", "$at", "$v0", "$v1", "$a0", "$a1", "$a2", "$a3",
        		"$t0", "$t1", "$t2", "$t3", "$t4", "$t5", "$t6", "$t7", "$s0", "$s1", "$s2",
        		"$s3", "$s4", "$s5", "$s6", "$s7", "$t8", "$t9","$k0", "$k1", "$gp", "$sp",
        		"$fp", "$ra"};
    	final JPanel info = new JPanel(new GridLayout(registerNames.length, 2));
        info.setBackground(Color.LIGHT_GRAY);
        info.setPreferredSize(new Dimension(500, 650));
        
        for (int i = 0; i < 32; i++) {
        	JLabel name = new JLabel(registerNames[i]);
        	JTextArea register = new JTextArea("0");
        	register.setPreferredSize(new Dimension(1, 1));
        	register.setEditable(false);
        	myRegisters.add(register);
        	info.add(name);
        	info.add(register);
        }
        return info;
    }
    
    /**
     * Creates the memory panel which contains the locations in memory and their values.
     * @return JPanel.
     */
    private JPanel makeMemoryPanel() {
    	final JPanel memory = new JPanel(new GridLayout(4, 25));
        memory.setBackground(Color.LIGHT_GRAY);
        
        for (int i = 0; i < 100; i++) {
        	JTextArea memoryValue = new JTextArea("0");
        	memoryValue.setEditable(false);
        	myMemory.add(memoryValue);
        	memory.add(memoryValue);
        }
        
        return memory;
    }
    
    /**
     * Updates the register values.
     */
    private void updateInfo() {
    	BitString pcBS = mySimulator.getComputer().getPC();
    	myPC.setText(Integer.toString(pcBS.getValue()));
    	for (int i = 0; i < 32; i++) {
    		BitString registerBS = mySimulator.getComputer().getRegister(i);
    		myRegisters.get(i).setText(Integer.toString(registerBS.getValue2sComp()));
    	}
    }
    
    /**
     * Updates values in memory.
     */
    private void updateMemory() {
    	for (int i = 0; i < 100; i++) {
    		BitString memoryBS = mySimulator.getComputer().getMemory(i);
    		myMemory.get(i).setText(Integer.toString(memoryBS.getValue2sComp()));
    	}
    }
    
    /**
     * Creates an about page regarding information about students, class, and project.
     * @return JMenuItem
     */
    private JMenuItem createAboutPageItem() {
		final JMenuItem aboutPage = new JMenuItem();

		aboutPage.setText("About...");
		aboutPage.addActionListener(event -> {
            JOptionPane.showMessageDialog(null,
            		"\tDeveloped by:\nCurran Seam\nRohan Seam\nSharanjit Singh\n\n"
            		+ "\tAutumn 2019\n\tTCSS 372 Project 1\n\tMIPS Simulator",
                    "About", JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon());
		});
		return aboutPage;
	}
    
    /**
     * Creates a more page.
     * @return JMenuItem
     */
    private JMenuItem createMorePageItem() {
		final JMenuItem aboutPage = new JMenuItem();

		aboutPage.setText("More...");
		aboutPage.addActionListener(event -> {
            JOptionPane.showMessageDialog(null,
            		"( ͡° ͜ʖ ͡°)_/               Yo",
                    "About", JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon());
		});
		return aboutPage;
	}
}
