/**
 * 
 */

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
					+ "Black Ink: " + inkCartridge.blackInkLeft);
		}
		
		public void refillInk() {
			blackInkLeft = 100;
			redInkLeft = 100;
			greenInkLeft = 100;
			blueInkLeft = 100;
		}
	} // end class InkCartridge
	
	
	public class PaperCartridge {
		
		public PaperCartridge() {
			numberOfPapers = maxNumOfPapers;
			isOpen = false;
		}
		
		private boolean isOpen;
		private double numberOfPapers;

		private double maxNumOfPapers = 500;
		
		public void displayNumberOfPapers() {
			System.out.println("Number of pages remaining: " + numberOfPapers);
		}
		public void refillPaper() {
			numberOfPapers = maxNumOfPapers;
		}

	} // end class PaperCartridge
	
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
	
	/**
	 * @param numOfCopies
	 * @return true if print was successful
	 */
	public boolean printColor(int numOfCopies) {
		if (!checkForEnough(numOfCopies, false))
			return false;
		
		System.out.println("Printing " + numOfCopies + " pages in color.");
		
		paperCartridge.numberOfPapers -= numOfCopies;
		inkCartridge.blueInkLeft -= numOfCopies * 0.2;
		inkCartridge.greenInkLeft -= numOfCopies * 0.2;
		inkCartridge.redInkLeft -= numOfCopies * 0.2;
		
		inkCartridge.getInkStatus();
		paperCartridge.displayNumberOfPapers();
		
		System.out.println();
		
		return true;
				
	} // end printColor()
	
	public boolean printBlackandWhite(int numOfCopies) {
		if (!checkForEnough(numOfCopies, false))
			return false;
		
		System.out.println("Printing " + numOfCopies + " pages in black.");
		
		paperCartridge.numberOfPapers -= numOfCopies;
		inkCartridge.blackInkLeft -= numOfCopies * 0.2;

		
		inkCartridge.getInkStatus();
		paperCartridge.displayNumberOfPapers();
		
		System.out.println();
		
		return true;
	}
	
	public boolean checkForEnough(int numOfCopies, boolean color) {
		if (!this.isOn())
		{
			System.err.println("The printer is not on.\n");
			return false;
		}
		
		if ( (paperCartridge.numberOfPapers - numOfCopies) < 0) {
			System.err.println("Printer either does not have enough ink or paper to print " + 
				numOfCopies + " pages.\n");
			
			paperCartridge.displayNumberOfPapers();
			
			return false;
		}
		
		if ( color == false && (inkCartridge.blackInkLeft - (numOfCopies * 0.2) < 0)) {
			System.err.println("Printer does not have enough black ink left to print " +
		numOfCopies + " pages.\n");
			
			inkCartridge.getInkStatus();
			
			return false;
		}
		if ( color == true && (inkCartridge.blueInkLeft - (numOfCopies * 0.2) < 0)) {
			System.err.println("Printer does not have enough color ink left to print " +
		numOfCopies + " pages.\n");
			
			inkCartridge.getInkStatus();
			
			return false;
		}
		return true;

	}
	
	
	
	// ------------------------- END Methods -------------------------
	
	
	public static void main(String[] args) {
		Printer printer = new Printer();
		printer.printColor(500);
		printer.turnOn();
		printer.printColor(250);
		printer.printColor(251);
		
		printer.printBlackandWhite(250);
		
	}
}
