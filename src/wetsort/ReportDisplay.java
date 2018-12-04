/*
 * ReportDisplay.java
 *
 * Created on April 1, 2005, 7:47 AM
 */

package wetsort;

/**
 *
 * @author  b4edhdwj
 */

import static java.awt.event.InputEvent.CTRL_DOWN_MASK;
import static java.awt.event.InputEvent.SHIFT_DOWN_MASK;
import static java.awt.event.InputEvent.META_DOWN_MASK;

import java.io.*;
import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import wetsort.util.TextPrinter;

public class ReportDisplay extends javax.swing.JFrame {
    
    /** Creates new form ReportDisplay */
    public ReportDisplay() {
        initComponents();
        init();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jMenuBar = new javax.swing.JMenuBar();
        jFileMenu = new javax.swing.JMenu();
        jSaveAsItem = new javax.swing.JMenuItem();
        JPrintMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jCloseItem = new javax.swing.JMenuItem();
        jFontMenu = new javax.swing.JMenu();
        jStyleMenu = new javax.swing.JMenu();
        jPlainStyleItem = new javax.swing.JCheckBoxMenuItem();
        jItalicStyleItem = new javax.swing.JCheckBoxMenuItem();
        jBoldStyleItem = new javax.swing.JCheckBoxMenuItem();
        jSizeMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextArea1KeyReleased(evt);
            }
        });

        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jFileMenu.setMnemonic('F');
        jFileMenu.setText("File");
        jSaveAsItem.setMnemonic('S');
        jSaveAsItem.setText("Save As...");
        jSaveAsItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSaveAsItemActionPerformed(evt);
            }
        });

        jFileMenu.add(jSaveAsItem);

        JPrintMenuItem.setMnemonic('p');
        JPrintMenuItem.setText("Print...");
        JPrintMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JPrintMenuItemActionPerformed(evt);
            }
        });

        jFileMenu.add(JPrintMenuItem);

        jFileMenu.add(jSeparator1);

        jCloseItem.setMnemonic('C');
        jCloseItem.setText("Close");
        jFileMenu.add(jCloseItem);

        jMenuBar.add(jFileMenu);

        jFontMenu.setMnemonic('o');
        jFontMenu.setText("Font");
        jMenuBar.add(jFontMenu);

        jStyleMenu.setText("Style");
        jPlainStyleItem.setSelected(true);
        jPlainStyleItem.setText("Plain");
        jPlainStyleItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPlainStyleItemActionPerformed(evt);
            }
        });

        jStyleMenu.add(jPlainStyleItem);

        jItalicStyleItem.setText("Italics");
        jItalicStyleItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jItalicStyleItemActionPerformed(evt);
            }
        });

        jStyleMenu.add(jItalicStyleItem);

        jBoldStyleItem.setText("Bold");
        jBoldStyleItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBoldStyleItemActionPerformed(evt);
            }
        });

        jStyleMenu.add(jBoldStyleItem);

        jMenuBar.add(jStyleMenu);

        jSizeMenu.setText("Size");
        jMenuBar.add(jSizeMenu);

        setJMenuBar(jMenuBar);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-600)/2, (screenSize.height-500)/2, 600, 500);
    }//GEN-END:initComponents

    private void JPrintMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JPrintMenuItemActionPerformed
        print();
    }//GEN-LAST:event_JPrintMenuItemActionPerformed

    private void jSaveAsItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSaveAsItemActionPerformed
        javax.swing.JFileChooser  fc = new javax.swing.JFileChooser();
        
        fc.setDialogTitle("Save As ...");
        fc.setSelectedFile(new File(pFilePath));
        
        int rval = fc.showSaveDialog(this);

        if ( rval == javax.swing.JFileChooser.APPROVE_OPTION )
        {
            java.io.File file = fc.getSelectedFile();
            
            try
            {
                if ( file != null)
                {
                    java.io.FileWriter writer = new FileWriter(file,false);
                    
                    writer.write(getText());
                    
                    writer.close();
                    
                }
            }
            catch(java.io.IOException exp)
            {
                javax.swing.JOptionPane pane = new javax.swing.JOptionPane();
                pane.showInputDialog(this,exp.getMessage(),"SaveError",javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
    }//GEN-LAST:event_jSaveAsItemActionPerformed

    private void jBoldStyleItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBoldStyleItemActionPerformed
        if ( jBoldStyleItem.isSelected() )
        {
            if ( jItalicStyleItem.isSelected() )
            {
                currentStyle = Font.ITALIC + Font.BOLD;
                jPlainStyleItem.setSelected(false);
            }
            else
            {
                currentStyle = Font.ITALIC;
                jPlainStyleItem.setSelected(false);
            }
        }
        else
        {
             if ( jItalicStyleItem.isSelected() )
            {
                currentStyle = Font.ITALIC;
                jPlainStyleItem.setSelected(false);
            }
            else
            {
                currentStyle = Font.PLAIN; 
                jPlainStyleItem.setSelected(true);
            }           
        }
        setCurrentFont();
    }//GEN-LAST:event_jBoldStyleItemActionPerformed

    private void jItalicStyleItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jItalicStyleItemActionPerformed
        if ( jItalicStyleItem.isSelected() )
        {
            if ( jBoldStyleItem.isSelected() )
            {
                currentStyle = Font.ITALIC + Font.BOLD;
                jPlainStyleItem.setSelected(false);
            }
            else
            {
                currentStyle = Font.ITALIC;
                jPlainStyleItem.setSelected(false);
            }
        }
        else
        {
             if ( jBoldStyleItem.isSelected() )
            {
                currentStyle = Font.BOLD;
                jPlainStyleItem.setSelected(false);
            }
            else
            {
                currentStyle = Font.PLAIN; 
                jPlainStyleItem.setSelected(true);
            }           
        }
        setCurrentFont();
    }//GEN-LAST:event_jItalicStyleItemActionPerformed

    private void jPlainStyleItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPlainStyleItemActionPerformed
   

        currentStyle = Font.PLAIN;
            
        jItalicStyleItem.setSelected(false);
        jBoldStyleItem.setSelected(false);
        setCurrentFont();

    }//GEN-LAST:event_jPlainStyleItemActionPerformed

    private void init()
    {
        Font font = jTextArea1.getFont();
        currentFont = font.getFamily();
        currentStyle = font.getStyle();
        currentSize = font.getSize();
        
        makeFontMenu();
        makeSizeMenu();
        makeStyleMenu();
    }
    
    private void makeFontMenu()
    {
        String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        
        int size = jTextArea1.getFont().getSize();
        currentFont = jTextArea1.getFont().getFamily();
        
        jFontMenu.removeAll();
        jFontGroup = new ButtonGroup();
              
        
        for( int i = 0; i < fontNames.length; ++i )
        {
            Font font = new Font(fontNames[i],Font.PLAIN,size);
            
            JRadioButtonMenuItem item = new JRadioButtonMenuItem(fontNames[i]);
            item.setFont(font);
            item.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) 
                {
                    JRadioButtonMenuItem item = (JRadioButtonMenuItem) e.getSource();
                    currentFont = item.getText();
                    setCurrentFont();
                }
            });
            
            jFontMenu.add(item);
            jFontGroup.add(item);
            
            if ( fontNames[i].compareTo(currentFont) == 0 )
            {
                item.setSelected(true);
            }
        }  
    }
    
    private void makeSizeMenu()
    {
        String[] fontSize = {"6","7","8","9","10","11","12","13","14","16","18","20","24"};
        
        jSizeMenu.removeAll();
        jSizeGroup = new ButtonGroup();
        
        for( int i = 0; i < fontSize.length; ++i )
        {
            JRadioButtonMenuItem item = new JRadioButtonMenuItem(fontSize[i]);
            
            item.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) 
                {
                    JRadioButtonMenuItem item = (JRadioButtonMenuItem) e.getSource();
                    

                    currentSize = Integer.parseInt(item.getText());
                    setCurrentFont();

                }
            });
            
            jSizeMenu.add(item);
            jSizeGroup.add(item);
            
            if ( Integer.parseInt(fontSize[i]) == currentSize )
            {
                item.setSelected(true);
            }
        }
    }
    
    private void makeStyleMenu()
    {
        Font font = jStyleMenu.getFont();
        
        
        
        
    }
    
    private void setCurrentFont()
    {
        Font font = new Font(currentFont,currentStyle,currentSize);
        
        jTextArea1.setFont(font);
        
        String s = jTextArea1.getText();
        jTextArea1.setText("");
        jTextArea1.setText(s);
    }
    
    private void jTextArea1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea1KeyReleased
        
       String msg = evt.getKeyText(evt.getKeyCode());
        
       int code = evt.getKeyCode();
       
        switch(code)
        {
            case java.awt.event.KeyEvent.VK_P:
            int onmask = CTRL_DOWN_MASK ;
            int offmask = SHIFT_DOWN_MASK | META_DOWN_MASK;
            int mod = evt.getModifiersEx();
            if ( ( mod & (onmask | offmask) ) == onmask ) 
            {
                print();
            }
            default:
                    
        }
    }//GEN-LAST:event_jTextArea1KeyReleased
    
    
    private void print()
    {
        // set the type of document to application formated
        DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
                
        // get the default printer
        PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
                
        // get a printer job
        //DocPrintJob job = printService.createPrintJob();
                
        // create a printable objects              
        TextPrinter textPrinter = new TextPrinter(jTextArea1.getText(),jTextArea1.getFont());
                
        // get a print request attribute set
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
                
        // get a document attributes set
        DocAttributeSet das = new HashDocAttributeSet();
                
        // make a document
        Doc doc = new SimpleDoc(textPrinter, flavor, das);
                
        PrintService service = ServiceUI.printDialog(null, 200, 200, 
        PrintServiceLookup.lookupPrintServices(flavor, das), printService, flavor, pras);
                
        if ( service != null)
        {
            DocPrintJob job = service.createPrintJob();            
            try
            {
                job.print(doc, pras);
            }
            catch ( PrintException pe)
            {
                 pe.printStackTrace(); 
            }
        }        
    }
    
    /** void setText(String txt)
     *
     *  Set the text buffer that this window will display */
    
    public void setText(String txt)
    {       
        int rows = 0;
        int pos = txt.indexOf("\n");
        while( pos != - 1)
        {
            rows++;
            pos = txt.indexOf("\n",pos+1);
        }
        
        jTextArea1.setRows(rows);
        jTextArea1.setText(txt);     
    }
    
    public void appendText(String txt)
    {
        int rows = jTextArea1.getRows();
     
        int pos = txt.indexOf("\n");
        while( pos != -1 )
        {
            rows++;
            pos = txt.indexOf("\n");
        }
        
        jTextArea1.setRows(rows);
        jTextArea1.append(txt);
        
    }
    
    /** String getText()
     * 
     * get a copy of the text this window displays */
    
    public String getText()
    {
        return jTextArea1.getText();
    }
    
    public void setFilePath(String s)
    {
        pFilePath = s;
    }
    
    public String getFilePath()
    {
        return pFilePath;
    }
    
    private String currentFont;
    int currentStyle;
    int currentSize;
    
    private javax.swing.ButtonGroup jFontGroup;
    private javax.swing.ButtonGroup jSizeGroup;
    
    private String pFilePath;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem JPrintMenuItem;
    private javax.swing.JCheckBoxMenuItem jBoldStyleItem;
    private javax.swing.JMenuItem jCloseItem;
    private javax.swing.JMenu jFileMenu;
    private javax.swing.JMenu jFontMenu;
    private javax.swing.JCheckBoxMenuItem jItalicStyleItem;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JCheckBoxMenuItem jPlainStyleItem;
    private javax.swing.JMenuItem jSaveAsItem;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JMenu jSizeMenu;
    private javax.swing.JMenu jStyleMenu;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
    
}