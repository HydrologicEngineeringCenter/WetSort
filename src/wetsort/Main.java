/*
 * Main.java
 *
 * Created on November 7, 2005, 8:14 AM
 */

package wetsort;

/**
 *
 * @author b4edhdwj
 */
public class Main {
    
    /** Creates a new instance of Main */
    public Main() {
    }
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WetSortWin().setVisible(true);
            }
        });
    }
    
    
}
