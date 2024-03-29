package eu.benjam.hough;
import java.awt.*;
import java.awt.image.*;
import java.applet.*;
import java.net.*;
import java.io.*;
import java.lang.Math;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import javax.swing.event.*;




public class houghDemo extends JApplet {
	
	Image edgeImage, accImage, outputImage;
	MediaTracker tracker = null;
	PixelGrabber grabber = null;
	int width = 0, height = 0;
	String fileNames[] = {"game3.png","game1.png","4.png"};

	javax.swing.Timer timer;
	//slider constraints
	static final int RADIUS_MIN = 5;
	static final int RADIUS_MAX = 45;
	static final int RADIUS_INIT = 8;
	int radius=RADIUS_INIT;

	int imageNumber=0;
	static int progress=0;
	static String status;
	public int orig[] = null;
	
	Image image[] = new Image[fileNames.length];
	
	JProgressBar progressBar;
	JPanel controlPanel, selectionPanel, imagePanel, progressPanel;
	JLabel origLabel,linesLabel, outputLabel,edgeLabel,comboLabel,radiusLabel,intermediateLabel,processing;
	ButtonGroup radiogroup;
	JRadioButton SobelRadio, OverlayRadio, HystRadio, HoughAccRadio;
	JSlider linesSlider;
	JComboBox imSel;
	static sobel sobelObject;
	static nonMaxSuppression nonMaxSuppressionObject;
	static lineHough lineHoughObject;
	static hystThresh hystThreshObject;
	static String intermediateimage;
	static int lines=20;
	
	static Image LinesImage, OverlayImage, SobelImage,MaxSuppImage, HystImage, HoughAccImage;


	   	// Applet init function	
	public void init() {
		
		tracker = new MediaTracker(this);
		for(int i = 0; i < fileNames.length; i++) {
			image[i] = getImage(this.getCodeBase(),fileNames[i]);
			image[i] = image[i].getScaledInstance(256, 256, Image.SCALE_SMOOTH);
			tracker.addImage(image[i], i);
		}
		try {
			tracker.waitForAll();
		}
		catch(InterruptedException e) {
			System.out.println("error: " + e);
		}
		
		Container cont = getContentPane();
		cont.removeAll();
		cont.setBackground(Color.black);
		cont.setLayout(new BorderLayout());
		
		controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(2,3,0,0));
		controlPanel.setBackground(new Color(192,204,226));
		imagePanel = new JPanel();
		imagePanel.setBackground(new Color(192,204,226));
		progressPanel = new JPanel();
		progressPanel.setBackground(new Color(192,204,226));
		progressPanel.setLayout(new GridLayout(2,1));
		selectionPanel = new JPanel();
		selectionPanel.setBackground(new Color(192,204,226));


		comboLabel = new JLabel("IMAGE: ");
		comboLabel.setHorizontalAlignment(JLabel.CENTER);
		controlPanel.add(comboLabel);
		intermediateLabel = new JLabel("Intermediate Output Image:");
		intermediateLabel.setHorizontalAlignment(JLabel.CENTER);
		controlPanel.add(intermediateLabel);
		linesLabel = new JLabel("Number Of Hough Lines");
		linesLabel.setHorizontalAlignment(JLabel.CENTER);
		controlPanel.add(linesLabel);




		processing = new JLabel("Processing...");
		processing.setHorizontalAlignment(JLabel.LEFT);
		progressBar = new JProgressBar(0,100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true); //get space for the string
        progressBar.setString("");          //but don't paint it
		progressPanel.add(processing);
		progressPanel.add(progressBar);
		
		width = image[imageNumber].getWidth(null);
		height = image[imageNumber].getHeight(null);

		imSel = new JComboBox(fileNames);
		imageNumber = imSel.getSelectedIndex();
		imSel.addActionListener( 
			new ActionListener() {  
				public void actionPerformed(ActionEvent e) {
					imageNumber = imSel.getSelectedIndex();
					origLabel.setIcon(new ImageIcon(image[imageNumber]));	
					processImage();
				}
			}
		);
		controlPanel.add(imSel);

        timer = new javax.swing.Timer(50, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
				int val=sobelObject.getProgress()/3+nonMaxSuppressionObject.getProgress()/3+hystThreshObject.getProgress()/3+(lineHoughObject.getProgress()*2);
				val=val/3;
                progressBar.setValue(val);
				processing.setText("Processing "+ status);
            }
        });

		origLabel = new JLabel("Original Image", new ImageIcon(image[imageNumber]), JLabel.CENTER);
		origLabel.setVerticalTextPosition(JLabel.BOTTOM);
		origLabel.setHorizontalTextPosition(JLabel.CENTER);
		origLabel.setForeground(Color.blue);
		imagePanel.add(origLabel);
		
		edgeLabel = new JLabel("Intermediate Image", new ImageIcon(image[imageNumber]), JLabel.CENTER);
		edgeLabel.setVerticalTextPosition(JLabel.BOTTOM);
		edgeLabel.setHorizontalTextPosition(JLabel.CENTER);
		edgeLabel.setForeground(Color.blue);
		imagePanel.add(edgeLabel);
	
		outputLabel = new JLabel("Hough Lines Overlay", new ImageIcon(image[imageNumber]), JLabel.CENTER);
		outputLabel.setVerticalTextPosition(JLabel.BOTTOM);
		outputLabel.setHorizontalTextPosition(JLabel.CENTER);
		outputLabel.setForeground(Color.blue);
		imagePanel.add(outputLabel);

		SobelRadio = new JRadioButton("Sobel");
    	SobelRadio.setActionCommand("Sobel");
		SobelRadio.setBackground(new Color(192,204,226));
		HystRadio = new JRadioButton("Hyst");
    	HystRadio.setActionCommand("Hysteresis");
		HystRadio.setBackground(new Color(192,204,226));
		HoughAccRadio = new JRadioButton("Acc");
    	HoughAccRadio.setActionCommand("Hough Acc");
		HoughAccRadio.setBackground(new Color(192,204,226));
		OverlayRadio = new JRadioButton("Lines");
    	OverlayRadio.setActionCommand("Hough Lines");
		OverlayRadio.setBackground(new Color(192,204,226));
    	HoughAccRadio.setSelected(true);
		intermediateimage="Hough Acc";

	    radiogroup = new ButtonGroup();
	    radiogroup.add(SobelRadio);
	    radiogroup.add(OverlayRadio);
	    radiogroup.add(HystRadio);
	    radiogroup.add(HoughAccRadio);

	    SobelRadio.addActionListener(new radiolistener());
	    OverlayRadio.addActionListener(new radiolistener());
	    HystRadio.addActionListener(new radiolistener());
	    HoughAccRadio.addActionListener(new radiolistener());

		selectionPanel.add(SobelRadio);
		selectionPanel.add(HystRadio);
		selectionPanel.add(HoughAccRadio);
		selectionPanel.add(OverlayRadio);
		controlPanel.add(selectionPanel);

		linesSlider = new JSlider(JSlider.HORIZONTAL, 1, 20, lines);
		linesSlider.addChangeListener(new linesListener());
		linesSlider.setMajorTickSpacing(10);
		linesSlider.setMinorTickSpacing(5);
		linesSlider.setPaintTicks(true);
		linesSlider.setPaintLabels(true);
		linesSlider.setBackground(new Color(192,204,226));
		controlPanel.add(linesSlider);

		cont.add(controlPanel, BorderLayout.NORTH);
		cont.add(imagePanel, BorderLayout.CENTER);
		cont.add(progressPanel, BorderLayout.SOUTH);

		processImage();

	}
	class radiolistener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
			intermediateimage=e.getActionCommand();
			swapintermediateimage();
	    }
	}
	class linesListener implements ChangeListener {
	    public void stateChanged(ChangeEvent e) {
	        JSlider source = (JSlider)e.getSource();
	        if (!source.getValueIsAdjusting()) {
				System.out.println("lines="+source.getValue());
				lines=source.getValue();
				processImage();
	        }    
	    }
	}
	private void swapintermediateimage(){
			if(intermediateimage=="Hough Lines"){
				edgeLabel.setIcon(new ImageIcon(LinesImage));
				edgeLabel.setText("Hough Line Image");
				System.out.println("LinesImage");
				}
			if(intermediateimage=="Sobel"){
				edgeLabel.setIcon(new ImageIcon(SobelImage));
				edgeLabel.setText("Sobel Output");
				System.out.println("SobelImage");
				}
			if(intermediateimage=="Non Max Supp"){
				edgeLabel.setIcon(new ImageIcon(MaxSuppImage));
				edgeLabel.setText("Non Max Supp Output");
				System.out.println("MaxSuppImage");
				}
			if(intermediateimage=="Hysteresis"){
				edgeLabel.setIcon(new ImageIcon(HystImage));
				edgeLabel.setText("Hysteresis Threshold Output");
				System.out.println("HystImage");
				}
			if(intermediateimage=="Hough Acc"){
				edgeLabel.setIcon(new ImageIcon(HoughAccImage));
				edgeLabel.setText("Hough Accumulator Output");
				System.out.println("HoughAccImage");
				}
	}
	private int[] overlayImage(int[] input){
		
		int[] myImage=new int[width*height];
		
		PixelGrabber grabber = new PixelGrabber(image[imageNumber], 0, 0, width, height, myImage, 0, width);
		try {
			grabber.grabPixels();
		}
		catch(InterruptedException e2) {
			System.out.println("error: " + e2);
		}


		for(int x=0;x<width;x++) {
			for(int y=0;y<height;y++) {
				if((input[y*width+x]&0xff)>0)
					myImage[y*width+x]= 0xffff0000;
			}
		}

		return myImage;

	}
	private void processImage(){
		orig=new int[width*height];
		PixelGrabber grabber = new PixelGrabber(image[imageNumber], 0, 0, width, height, orig, 0, width);
		try {
			grabber.grabPixels();
		}
		catch(InterruptedException e2) {
			System.out.println("error: " + e2);
		}
		progressBar.setMaximum(width-radius);

		processing.setText("Processing...");
		imSel.setEnabled(false);
		linesSlider.setEnabled(false);		
		SobelRadio.setEnabled(false);
		HystRadio.setEnabled(false);
		HoughAccRadio.setEnabled(false);
		OverlayRadio.setEnabled(false);
		
		sobelObject = new sobel();
		nonMaxSuppressionObject = new nonMaxSuppression();
		hystThreshObject = new hystThresh();
		lineHoughObject = new lineHough();
		
		timer.start();

		new Thread(){
			public void run(){

				status="Sobel";
				sobelObject.init(orig,width,height);
				orig = sobelObject.process();
				double direction[] = new double[width*height];
				direction=sobelObject.getDirection();
				SobelImage = createImage(new MemoryImageSource(width, height, orig, 0, width));

				status="Non Maximum Suppression";
				nonMaxSuppressionObject.init(orig,direction,width,height);
				orig = nonMaxSuppressionObject.process();
				MaxSuppImage = createImage(new MemoryImageSource(width, height, orig, 0, width));

				status="Hysteresis Threshold";
				hystThreshObject.init(orig,width,height, 50,100);
				orig = hystThreshObject.process();

				HystImage = createImage(new MemoryImageSource(width, height, orig, 0, width));

				status="Hough Transform";
				lineHoughObject.init(orig,width,height);
				lineHoughObject.setLines(lines);
				orig = lineHoughObject.process();
				OverlayImage = createImage(new MemoryImageSource(width, height, overlayImage(orig), 0, width));

				int rmax = (int)Math.sqrt(width*width + height*height);
				int acc[] = new int[rmax*180];
				acc=lineHoughObject.getAcc();
				HoughAccImage = createImage(new MemoryImageSource(180, rmax, acc, 0, 180)).getScaledInstance(256, 256, Image.SCALE_SMOOTH);

				LinesImage = createImage(new MemoryImageSource(width, height, orig, 0, width));

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						outputLabel.setIcon(new ImageIcon(OverlayImage));	

						swapintermediateimage();
						status="Done";
						processing.setText("Done");
						imSel.setEnabled(true);
						linesSlider.setEnabled(true);
						SobelRadio.setEnabled(true);
						HystRadio.setEnabled(true);
						HoughAccRadio.setEnabled(true);
						OverlayRadio.setEnabled(true);

					}
				});
			}
		}.start();
	}

}