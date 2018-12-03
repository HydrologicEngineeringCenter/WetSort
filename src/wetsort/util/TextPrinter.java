/*
 * TextPrinter.java
 *
 * Created on November 17, 2005, 11:38 AM
 */

package wetsort.util;


import javax.print.*;
import javax.print.event.*;
import javax.print.attribute.*;
import java.awt.print.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.awt.font.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author Donald Johnson
 *
 *  This allows printing of stored text using a user defined font
 */
public class TextPrinter implements Printable{
    
    /** Creates a new Text Printer object that can print the text in s using the font f */
    public TextPrinter(String s, Font f ) 
    {
        mText = s;
        mFont = f;
        
        pagesKnown = false;
        numPages = 0;
           
        mLine = new ArrayList<String>();
        
        tabsPerLine = 6;
        
       parseText();
    }
    
    /** This methode is called by the print system to print each page */
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
    {
        if ( pagesKnown == false )
        {
            countPages(graphics,pageFormat);
        }
        
        if ( pageIndex < numPages )
        {
            renderPage(graphics,pageFormat,pageIndex);
            return PAGE_EXISTS;
        }
        else
        {
            return NO_SUCH_PAGE;
        }
    }
    
    /** This breaks the inital string into a list of string that each end with a newline character */
    private void parseText()
    {
        String s;
        
        int pos1 = 0;
        int pos2 = mText.indexOf("\n");
        mLine.clear();
        
        while ( pos2 != -1 )
        {    
            // get the current line
            s = mText.substring(pos1,pos2);
            
            mLine.add(s);
            
            pos1 = pos2+1;
            pos2 = mText.indexOf("\n",pos2+1);
        }
        
        
        s = mText.substring(pos1);
        mLine.add(s);
        
        
    }
    
    /** This function determins how many lines of text will be printed and then determins how many pages
     *  are nessessary to print */
    private void countPages(Graphics g, PageFormat pageFormat)
    {
        // set the font that will be used and get a font metrics objects
        g.setFont(mFont);
        FontMetrics metrics = g.getFontMetrics();
        
        // get the height of a line of text;
        int lineHeight = metrics.getHeight();
        
        // get the width of a page
        double pageWidth = pageFormat.getImageableWidth();
        double pageHeight = pageFormat.getImageableHeight();
        
        for( int i = 0; i < mLine.size(); ++i )
        {
            String s = mLine.get(i);
            
            int lineWidth = metrics.stringWidth(s);
            
            // if the line is longer than can be printed on a line
            if ( lineWidth > pageWidth )
            {
                // find the last character that can be printed on a single line
                int pos = s.length() - 1;
                while( metrics.stringWidth(s.substring(0,pos)) > pageWidth )
                {
                    --pos;
                }
                
                ++pos;
                
                // now try to move back to a white space character
                int pos2 = pos;
                while ( pos2 > 0 && 
                        Character.isLetterOrDigit(s.charAt(pos2)) && 
                        s.codePointAt(pos2) != Character.valueOf('/') &&
                        s.charAt(pos2) != Character.valueOf('\\') )
                {
                    --pos2;
                }
                
                // split the string
                if ( pos2 > 0)
                {
                    mLine.set(i,s.substring(0,pos2+1) );
                    mLine.add(i+1,s.substring(pos2+1));
                }
                else
                {
                    mLine.set(i,s.substring(0,pos));
                    mLine.add(i+1,s.substring(pos,s.length()));
                }
            }
        }
        
        linesPerPage = (int) java.lang.Math.floor(pageHeight / lineHeight);        
             
        numPages = (int) java.lang.Math.ceil( ( (double) mLine.size() ) / linesPerPage );
        pagesKnown = true;
    }
    
    /** This function draws the requested page onto the suplied Graphics context */
    
    private void renderPage(Graphics g, PageFormat format, int pageIndex)
    {
        FontMetrics metrics = g.getFontMetrics();
        int lineHeight = metrics.getHeight();
        
        int xInit = (int) format.getImageableX();
        int yInit = (int) format.getImageableY();
        
        int x;
        int y = 0;
        
        g.translate(xInit,yInit+lineHeight);
        
        int start = pageIndex * linesPerPage;
        
        g.setFont(mFont);
        
        int tabWidth = (int)( format.getImageableWidth() / tabsPerLine );
        
        for(int i = 0; i < linesPerPage; ++i )
        {
            if ( start + i < mLine.size())
            {
                String[] s = mLine.get(start+i).split("\t");
                
                x = 0;
                
                for( int j = 0; j < s.length; ++j )
                {  
                    g.drawString(s[j],x,y);
                    
                    x += metrics.stringWidth(s[j]);
                    
                    double val = java.lang.Math.ceil( ((double) x) / tabWidth) * tabWidth;
                    x = (int) val;
                }
          
                y += lineHeight;
            }
        }
    }
    
    /** Flag indicating if the number of pages i sknown */
    private boolean pagesKnown;
    /** The number of pages required to print this text in the current font */
    private int numPages;
    /** The number of lines of text that will fit on a page */
    private int linesPerPage;
    
    private int tabsPerLine;

    /** list that holds individual lines of text */
    private ArrayList<String> mLine;
    /** the text to be printed */
    private String mText;
    /** The font to print in */
    private Font mFont;
}
