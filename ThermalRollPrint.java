package naveed.practice.test;

//reference LINK for source code
//http://stackoverflow.com/questions/17977510/printing-on-a-thermal-printer-java

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class ThermalRollPrint {


	  public static void printCard(final String bill ){
	final PrinterJob job = PrinterJob.getPrinterJob();


	   Printable contentToPrint = new Printable(){
	     @Override
	   public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws  PrinterException {


	       Graphics2D g2d = (Graphics2D) graphics;

	    g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
	    g2d.setFont(new Font("Monospaced", Font.BOLD, 7));
	    pageFormat.setOrientation(PageFormat.PORTRAIT);
	   Paper pPaper = pageFormat.getPaper();



	pPaper.setImageableArea(0, 0, pPaper.getWidth() , pPaper.getHeight() -2);
	pageFormat.setPaper(pPaper);

	       if (pageIndex >0){return NO_SUCH_PAGE;} //Only one page

	      String Bill [] = bill.split(";");

	      int y = 0;
	    for (int i = 0; i < Bill.length; i++) {

	        g2d.drawString(Bill[i], 0, y);
	        y = y + 15;
	    }

	    return PAGE_EXISTS;

	   }


	};  

	boolean don = job.printDialog();

	job.setPrintable(contentToPrint);

	try {
	    job.print();

	} catch (PrinterException e) {
	    System.err.println(e.getMessage());
	}
	}
	}