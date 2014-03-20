package draw_ui;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import classes.FactoryFunctions;
import classes.FirstFunction;
import classes.FuzzySet;
import classes.TypeFuzzySet;


public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private StateUI state = StateUI.addFirst;
	private FuzzySet fsFirst;
	private FuzzySet fsSeccond;
	
	private Canvas 				canvas;
	private JButton 			btnDrawGraph;
	private JButton				btnWorkGraph;
	private JComboBox<String>	cbAction;
	private JLabel 				lblSwitshGraph;
	private JList<String> 		lSwitshGraph;
	private JTextField 			tfParameterA;
	private JTextField 			tfParameterB;
	private JTextField 			tfParameterC;
	private JTextField 			tfParameterD;
	private JTextArea			taConsole;
	private GroupLayout 		lmMain;

	public MainFrame() {
		
		try {
			
			jbInit();
		} catch(Exception e) {
			
			e.printStackTrace();
	    }
	}
	
	private void jbInit() throws Exception {

		String [] actions = new String [] {
				"addition", 
				"subtraction", 
				"multiplication", 
				"division"
		};
		
		String [] nameFunction = new String[] {
				"First Function",
				"Second Function",
				"Third Function",
				"Fourth Function",
				"Fifth Function",
				"Sixth Function",
				"Seventh Function",
				"Eighth Function",
		};
		
		canvas = new Canvas();

		lblSwitshGraph	= new JLabel("Select chart:");
		lSwitshGraph	= new JList<String>();
		
		tfParameterA	= new JTextField();
		tfParameterB	= new JTextField();
		tfParameterC	= new JTextField();
		tfParameterD	= new JTextField();
		
		taConsole		= new JTextArea();
		
		cbAction		= new JComboBox<String>(actions);
		
		btnDrawGraph 	= new JButton("Нарисовать график");
		btnWorkGraph	= new JButton("Добавить 1-й график");
	    
		lmMain			= new GroupLayout(getContentPane());

		lblSwitshGraph.setMaximumSize(new Dimension(120, 20));
		lSwitshGraph.setMaximumSize(new Dimension(200, 365));
		lSwitshGraph.setMinimumSize(new Dimension(200, 365));
		btnDrawGraph.setMaximumSize(new Dimension(200, 20));
		btnWorkGraph.setMaximumSize(new Dimension(200, 20));
		
		tfParameterA.setMaximumSize(new Dimension(120, 20));
		tfParameterA.setText("");
		tfParameterB.setMaximumSize(new Dimension(120, 20));
		tfParameterB.setText("");
		tfParameterC.setMaximumSize(new Dimension(120, 20));
		tfParameterC.setText("");
		tfParameterD.setMaximumSize(new Dimension(120, 20));
		tfParameterD.setText("");
		
		taConsole.setMaximumSize(new Dimension(600, 100));
		taConsole.setMinimumSize(new Dimension(600, 100));
		
		canvas.setMaximumSize(new Dimension(600, 400));
		canvas.setMinimumSize(new Dimension(600, 400));
		
		cbAction.setMaximumSize(new Dimension(200, 20));
		cbAction.setMinimumSize(new Dimension(200, 20));
		
		getContentPane().setLayout(lmMain);
		lmMain.setAutoCreateGaps(true);
		lmMain.setAutoCreateContainerGaps(true);

		lSwitshGraph.setListData(nameFunction);
		lSwitshGraph.addListSelectionListener(new ListListener());
		btnDrawGraph.addActionListener(new ButtonListener());
		btnWorkGraph.addActionListener(new BtnWorcGraphListener());
		
		lmMain.setHorizontalGroup(lmMain.createSequentialGroup()
			.addGroup(lmMain.createSequentialGroup()
				.addGroup(lmMain.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(lblSwitshGraph)
						.addComponent(lSwitshGraph)
						.addComponent(btnDrawGraph)
						.addComponent(btnWorkGraph)
						.addComponent(cbAction))
				.addGroup(lmMain.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(lmMain.createSequentialGroup()
						.addGroup(lmMain.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addComponent(tfParameterA))
						.addGroup(lmMain.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addComponent(tfParameterB))
						.addGroup(lmMain.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addComponent(tfParameterC))
						.addGroup(lmMain.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addComponent(tfParameterD)))
					.addComponent(canvas)
					.addComponent(taConsole))));
				
		lmMain.setVerticalGroup(lmMain.createSequentialGroup()
			.addGroup(lmMain.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(lblSwitshGraph)
				.addGroup(lmMain.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(tfParameterA))
				.addGroup(lmMain.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(tfParameterB))
				.addGroup(lmMain.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(tfParameterC))
				.addGroup(lmMain.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(tfParameterD)))
			.addGroup(lmMain.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addGroup(lmMain.createSequentialGroup()
					.addComponent(lSwitshGraph)
					.addComponent(btnDrawGraph))
				.addComponent(canvas))
			.addGroup(lmMain.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addGroup(lmMain.createSequentialGroup()
					.addComponent(btnWorkGraph)
					.addComponent(cbAction))
				.addComponent(taConsole)));
		
		setTitle("Лабораторная работа №1");
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
        
        tfParameterA.setText("2");
		tfParameterB.setText("3");
		tfParameterC.setText("0");
		tfParameterD.setText("0");
		lSwitshGraph.setSelectedIndex(0);
        this.draw(new FirstFunction(2, 3));
        this.setVisible(true);
	}	
	
	public void draw(FuzzySet fuzzySet) {
		
		canvas.setFuzzySet(fuzzySet);
		this.repaint();
	}
	
	public void draw() {

		this.repaint();
	}
	
	private class ListListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			
			switch (lSwitshGraph.getSelectedIndex()) {
			case 0:
				tfParameterA.setText("2");
				tfParameterB.setText("3");
				tfParameterC.setText("0");
				tfParameterD.setText("0");
				break;
			case 1:
				tfParameterA.setText("2");
				tfParameterB.setText("8");
				tfParameterC.setText("14");
				tfParameterD.setText("0");
				break;
			case 2:
				tfParameterA.setText("2");
				tfParameterB.setText("10");
				tfParameterC.setText("4");
				tfParameterD.setText("0");
				break;
			case 3:
				tfParameterA.setText("1");
				tfParameterB.setText("6");
				tfParameterC.setText("3");
				tfParameterD.setText("0");
				break;
			case 4:
				tfParameterA.setText("2");
				tfParameterB.setText("6");
				tfParameterC.setText("4");
				tfParameterD.setText("5");
				break;
			case 5:
				tfParameterA.setText("5");
				tfParameterB.setText("2");
				tfParameterC.setText("0");
				tfParameterD.setText("0");
				break;
			case 6:
				tfParameterA.setText("2");
				tfParameterB.setText("5");
				tfParameterC.setText("0");
				tfParameterD.setText("0");
				break;
			case 7:
				tfParameterA.setText("2");
				tfParameterB.setText("4");
				tfParameterC.setText("8");
				tfParameterD.setText("0");
				break;
			}
			
			int indexSelectGraph = lSwitshGraph.getSelectedIndex();
			double [] parameters = new double[4];
			parameters[0] = Double.parseDouble(tfParameterA.getText());
			parameters[1] = Double.parseDouble(tfParameterB.getText());
			parameters[2] = Double.parseDouble(tfParameterC.getText());
			parameters[3] = Double.parseDouble(tfParameterD.getText());
			
			FuzzySet fs = FactoryFunctions.getFuzzySet(
					TypeFuzzySet.values()[indexSelectGraph], 
					parameters);
			
			draw(fs);
		}
	}
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			int indexSelectGraph = lSwitshGraph.getSelectedIndex();
			double [] parameters = new double[4];
			parameters[0] = Double.parseDouble(tfParameterA.getText());
			parameters[1] = Double.parseDouble(tfParameterB.getText());
			parameters[2] = Double.parseDouble(tfParameterC.getText());
			parameters[3] = Double.parseDouble(tfParameterD.getText());
			
			FuzzySet fs = FactoryFunctions.getFuzzySet(
					TypeFuzzySet.values()[indexSelectGraph], 
					parameters);
			
			draw(fs);
		}
	}
	
	private class BtnWorcGraphListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			int indexSelectGraph = lSwitshGraph.getSelectedIndex();
			double [] parameters = new double[4];
			parameters[0] = Double.parseDouble(tfParameterA.getText());
			parameters[1] = Double.parseDouble(tfParameterB.getText());
			parameters[2] = Double.parseDouble(tfParameterC.getText());
			parameters[3] = Double.parseDouble(tfParameterD.getText());
			
			FuzzySet fs = FactoryFunctions.getFuzzySet(
					TypeFuzzySet.values()[indexSelectGraph], 
					parameters);
			
			switch (state) {
			case addFirst:
				taConsole.setText(taConsole.getText() + "\n" + fs);
				fsFirst = fs;
				state = StateUI.addSeccond;
				btnWorkGraph.setText("Добавть 2-й график");
				fsFirst.setColor(new Color(0xFF00FF));
				canvas.addFuzzySet(fsFirst);
				break;
			case addSeccond:
				taConsole.setText(taConsole.getText() + "\n" + fs);
				fsSeccond = fs;
				state = StateUI.Calculate;
				btnWorkGraph.setText("Расчитать");
				fsFirst.setColor(new Color(0xFF00FF));
				canvas.addFuzzySet(fsSeccond);
				break;
			case Calculate:
				
				switch (cbAction.getSelectedIndex()) {
				case 0:
					draw(fsFirst.add(fsSeccond));
					break;
				case 1:
					draw(fsFirst.sub(fsSeccond));
					break;
				case 2:
					draw(fsFirst.mul(fsSeccond));
					break;
				case 3:
					draw(fsFirst.div(fsSeccond));
					break;
				}
				
				state = StateUI.Clear;
				btnWorkGraph.setText("Отчистить");
				break;
			case Clear:
				taConsole.setText("");
				fsFirst = null;
				fsSeccond = null;
				state = StateUI.addFirst;
				btnWorkGraph.setText("Добавть 1-й график");
				canvas.clear();
				
				indexSelectGraph = lSwitshGraph.getSelectedIndex();
				parameters = new double[4];
				parameters[0] = Double.parseDouble(tfParameterA.getText());
				parameters[1] = Double.parseDouble(tfParameterB.getText());
				parameters[2] = Double.parseDouble(tfParameterC.getText());
				parameters[3] = Double.parseDouble(tfParameterD.getText());
				
				fs = FactoryFunctions.getFuzzySet(
						TypeFuzzySet.values()[indexSelectGraph], 
						parameters);
				
				draw(fs);
				
				break;
			}
			
			draw();
		}
	}
	
	private class Canvas extends JPanel {

		private static final long serialVersionUID = 1L;
		
		private BorderLayout borderLayout1 = new BorderLayout();
		private List<FuzzySet> fuzzySetList;
		private FuzzySet fuzzySet;
		
		public Canvas() {
			
			try {
				
				jbInit();
		    } catch(Exception ex) {
		    	
		    	ex.printStackTrace();
		    }
		}

		public void paint(Graphics g) {
			
			super.paint(g);
			
			g.setColor(new Color(0xfff5ee));
			g.fillRect(0, 0, getSize().width - 1, getSize().height - 1);
			
			BasicStroke pen = new BasicStroke(1.3f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 30);
			g.setColor(new Color(0x708090));
			((Graphics2D) g).setStroke(pen);
			g.drawRect(100, 100, 450, 250);
			g.setColor(new Color(0xFFFFFF));
			g.fillRect(101, 101, 448, 248);
			g.setColor(new Color(0x708090));
			g.drawLine(100, 225, 550, 225);
			pen = new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 30);
			((Graphics2D) g).setStroke(pen);
			g.setColor(new Color(0x000000));
			g.drawLine(100, 100, 100, 350);
			g.drawLine(95, 100, 105, 100);
			g.drawLine(95, 350, 105, 350);
			g.drawLine(95, 225, 105, 225);
			g.drawString("0", 80, 355);
			g.drawString("0.5", 75, 230);
			g.drawString("1", 80, 105);
			pen = new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 30);
			((Graphics2D) g).setStroke(pen);
			g.setColor(new Color(0xFF0000));
			
			fuzzySet.draw(g);
			
			for(FuzzySet fs : fuzzySetList)
				fs.draw(g);
			
			g.setColor(new Color(0xFF0000));
		}
		
		public void setFuzzySet(FuzzySet fuzzySet) {
			
			this.fuzzySet = fuzzySet;
		}
		
		public void addFuzzySet(FuzzySet fuzzySet) {
			
			this.fuzzySetList.add(fuzzySet);
		}
		
		public void clear() {
			
			this.fuzzySetList.clear();
		}
		
		private void jbInit() throws Exception {
			
			this.setLayout(borderLayout1);
			this.fuzzySetList = new ArrayList<FuzzySet>();
		}
	}
}
