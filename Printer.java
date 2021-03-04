package edu.cuny.csi.csc330.lab4;

import java.util.Arrays;
import java.util.Date;

import edu.cuny.csi.csc330.util.Randomizer;


public class Printer {

	// ------------------------- Encapsulated classes -------------------------
	
	public class InkCartridge {
		
		public InkCartridge() {
			blackInkLeft = 100;
			redInkLeft = 100;
			greenInkLeft = 100;
			blueInkLeft = 100;
		}		
		
		private double blackInkLeft;
		private double redInkLeft;
		private double greenInkLeft;
		private double blueInkLeft;
		
		public void getInkStatus() {
			System.out.println("Remaining ink levels are: \n"
					+ "Blue Ink:  " + inkCartridge.blueInkLeft + "%\n"
					+ "Green Ink: " + inkCartridge.greenInkLeft + "%\n"
					+ "Red Ink:   " + inkCartridge.redInkLeft + "%\n"
					+ "Black Ink: " + inkCartridge.blackInkLeft + "%\n");
		}
		
		public void refillInk() {
			blackInkLeft = 100;
			redInkLeft = 100;
			greenInkLeft = 100;
			blueInkLeft = 100;
		}
		
	}
	
	public class PaperCartridge {
		
		public PaperCartridge() {
			numberOfPapers = maxNumOfPapers;
			isOpen = false;
		}
		
		private boolean isOpen;
		private double numberOfPapers;
		private double maxNumOfPapers = 500;
		public void getNumberOfPapers() {
			
			
		}
	}
	
	// ------------------------- END encapsulated classes -------------------------
	
	// ------------------------- Constructors -------------------------
	public Printer() {
		isOn = false;
		setPrinterName("Printer");
	}

	// ------------------------- END Constructors -------------------------
	
	
	
	// ------------------------- Members -------------------------
	private boolean isOn;
	private String printerName;

	PaperCartridge paperCartridge = new PaperCartridge();
	InkCartridge inkCartridge = new InkCartridge();
	
	// ------------------------- END Members -------------------------
	
	// ------------------------- Methods -------------------------
	
	public String getPrinterName() {
		return printerName;
	}

	public void setPrinterName(String printerName) {
		this.printerName = printerName;
	}

	public void turnOn() {
		isOn = true;
		System.out.println(this.getPrinterName() + " is now on.");
	}
	
	public void turnOff() {
		isOn = false;
	}
	
	public boolean isOn() {
		return isOn;
	}
	
	public boolean printColor(int numOfCopies) {
		
		if (!this.isOn())
		{
			System.err.println("The printer is not on.");
			return false;
		}
		
		if ( ((paperCartridge.numberOfPapers - numOfCopies) < 0) || 
				(inkCartridge.blueInkLeft - (numOfCopies * 0.2) < 0)
				) {
			System.err.println("Printer does either not have enough ink or paper to print " + numOfCopies + " pages.");
			inkCartridge.getInkStatus();
			paperCartridge.getNumberOfPapers();
			return false;
		}
		
		
		System.out.println("Printing " + numOfCopies + " pages in color.");
		
		paperCartridge.numberOfPapers -= numOfCopies;
		inkCartridge.blueInkLeft -= numOfCopies * 0.2;
		inkCartridge.greenInkLeft -= numOfCopies * 0.2;
		inkCartridge.redInkLeft -= numOfCopies * 0.2;
		
		inkCartridge.getInkStatus();
		
//		System.out.println("Print complete. Remaining ink levels are: \n"
//			+ "Blue Ink: " + inkCartridge.blueInkLeft + "\n"
//			+ "Green Ink: " + inkCartridge.greenInkLeft + "\n"
//			+ "Red Ink: " + inkCartridge.redInkLeft + "\n");
		
		return true;
				
	}
	
	
	
	// ------------------------- END Methods -------------------------
	
	
	public static void main(String[] args) {
		Printer printer = new Printer();
		printer.printColor(500);
		printer.turnOn();
		printer.printColor(1);
		
	}
}
