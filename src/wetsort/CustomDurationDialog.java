/*
 * CustomDurationDialog.java
 *
 * Created on November 16, 2005, 2:30 PM
 */

package wetsort;

/**
 *
 * @author  b4edhdwj
 */

public class CustomDurationDialog extends javax.swing.JDialog {
    
    /** Creates new form CustomDurationDialog */
    public CustomDurationDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jLabel1 = new javax.swing.JLabel();
        jOkButton = new javax.swing.JButton();
        jCancelButton = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        getContentPane().setLayout(null);

        setTitle("Custom Duration");
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jLabel1.setText("% Duration:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 10, 70, 20);

        jOkButton.setText("Ok");
        jOkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOkButtonActionPerformed(evt);
            }
        });

        getContentPane().add(jOkButton);
        jOkButton.setBounds(245, 50, 80, 25);

        jCancelButton.setText("Cancel");
        jCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCancelButtonActionPerformed(evt);
            }
        });

        getContentPane().add(jCancelButton);
        jCancelButton.setBounds(150, 50, 80, 25);

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        getContentPane().add(jTextField1);
        jTextField1.setBounds(90, 10, 230, 20);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-340)/2, (screenSize.height-119)/2, 340, 119);
    }//GEN-END:initComponents

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        String s = jTextField1.getText();
        
        char ch = evt.getKeyChar();
        
        switch( ch )
        {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '8':
            case '9':

            break;
            
            case '.':

               if ( s.indexOf('.') != - 1 )
               {
                   evt.consume();
               }
                
            break;
            
            default:
                evt.consume();
            break;
        }
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
             
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
      if ( evt.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE )
      {
        cancel();
      }
    }//GEN-LAST:event_formKeyPressed

    private void jOkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOkButtonActionPerformed
        accept();
    }//GEN-LAST:event_jOkButtonActionPerformed

    private void jCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCancelButtonActionPerformed
        cancel();
    }//GEN-LAST:event_jCancelButtonActionPerformed
    
    private void init()
    {
        getRootPane().setDefaultButton(jOkButton);
    }
    
    private void cancel()
    {
        userAbort = true;
        setVisible(false);
    }
    
    private void accept()
    {
        userAbort = false;
        setVisible(false);
        
    }
    
    public double value()
    {
         return Double.parseDouble(jTextField1.getText());
    }
    
    public boolean canceled()
    {
        return userAbort;
    }
    
    private double lastValue;
    private boolean userAbort;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jCancelButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jOkButton;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
    
}