package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
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

public class MipsSimulatorView extends JFrame{

    /**
     * A generated serial version UID for object Serialization.
     */
    private static final long serialVersionUID = -8255134897469998994L;
    
    /**
     * Constant String for a title name.
     */
    private static final String TITLE = "MIPS Simulator";
    
    // constants to capture screen dimensions
    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    
    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    
	/**
	 * Main data to be contained
	 */
	private JTextArea myData;
	
	/**
	 * Data Panel
	 */
	private JPanel myDataPanel;
	
	private Simulator mySimulator;
	
	private JTextArea myPC;
	
	/**
	 * Registers
	 */
	private ArrayList<JTextArea> myRegisters;
	
	/**
	 * Memory
	 */
	private ArrayList<JTextArea> myMemory;
	
	private JTabbedPane myEditorTab;
	
	private JTabbedPane myRegisterTab;
	
	private JTabbedPane myMemoryTab;
    
    public MipsSimulatorView() {
    	super();
    	setupGUI();
    }
    
    private void setupGUI() {
        // hide the default JFrame icon
        //final Image icon = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE);
        
        
        // replace the default JFrame icon
        final ImageIcon img = new ImageIcon("MIPSlogo.png");
        setIconImage(img.getImage());
        
        setTitle("MIPS Simulator");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(makeExecutionPanel(), BorderLayout.NORTH);
        
        myDataPanel = new JPanel(new BorderLayout());
        myEditorTab = new JTabbedPane();
        myEditorTab.addTab("", myDataPanel); 
        myRegisterTab = new JTabbedPane();
        myMemoryTab = new JTabbedPane();
        myMemoryTab.setBorder(new EmptyBorder(0, 5, 10, 5));
        myData = new JTextArea(32, 50);
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
		settingsMenu.add(aboutPage);
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
    
    private JPanel makeExecutionPanel() {
        final JPanel p = new JPanel((LayoutManager) new FlowLayout(FlowLayout.LEFT));
        p.setBackground(Color.LIGHT_GRAY);
        
        JLabel groupName = new JLabel("DESI CREW MIPS SIMULATOR");
        groupName.setBorder(new EmptyBorder(10, 10, 10, 50));
        p.add(groupName);
        
        final JButton loadButton = new JButton("Load Program");
        loadButton.setBackground(Color.orange);
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
            }
        });
        p.add(loadButton);
        
        final JButton runButton = new JButton("Execute");
        runButton.setBackground(Color.GREEN);
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	mySimulator.start();
            	updateInfo();
            	updateMemory();
            	runButton.setEnabled(false);
            }
        });
        p.add(runButton);
        
    	JLabel name = new JLabel("PC");
    	name.setBorder(new EmptyBorder(0, 220, 0, 0));
    	myPC = new JTextArea("0");
//    	myPC.setPreferredSize(new Dimension(5, 1));
    	myPC.setEditable(false);
    	
    	p.add(name);
    	p.add(myPC);
        
        return p;
    }
    
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
        	register.setPreferredSize(new Dimension(5, 1));
        	register.setEditable(false);
        	myRegisters.add(register);
        	info.add(name);
        	info.add(register);
        }
        return info;
    }
    
    private JPanel makeMemoryPanel() {
    	final JPanel memory = new JPanel(new GridLayout(4, 25));
        memory.setBackground(Color.LIGHT_GRAY);
//        memory.setPreferredSize(new Dimension(500, 750));
        
        for (int i = 0; i < 100; i++) {
        	JTextArea memoryValue = new JTextArea("0");
//        	memoryValue.setPreferredSize(new Dimension(5, 1));
        	memoryValue.setEditable(false);
        	myMemory.add(memoryValue);
        	memory.add(memoryValue);
        }
        
        return memory;
    }
    
    private void updateInfo() {
    	BitString pcBS = mySimulator.getComputer().getPC();
    	myPC.setText(Integer.toString(pcBS.getValue()));
    	for (int i = 0; i < 32; i++) {
    		BitString registerBS = mySimulator.getComputer().getRegisters(i);
    		myRegisters.get(i).setText(Integer.toString(registerBS.getValue2sComp()));
    	}
    }
    
    private void updateMemory() {
    	for (int i = 0; i < 100; i++) {
    		BitString memoryBS = mySimulator.getComputer().getMemory(i);
    		myMemory.get(i).setText(Integer.toString(memoryBS.getValue2sComp()));
    	}
    }
    
    private JMenuItem createAboutPageItem() {
		final JMenuItem aboutPage = new JMenuItem();

		aboutPage.setText("About...");
		aboutPage.addActionListener(event -> {
            JOptionPane.showMessageDialog(null,
            		"\tCreated by\nCurran Seam,\nRohan Seam,\nSharanjit Singh\n\n"
            		+ "\tAutumn 2019\n\tTCSS 372 Project 1\n\tMIPS Simulator",
                    "About", JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon());
		});
		return aboutPage;
	}
}
