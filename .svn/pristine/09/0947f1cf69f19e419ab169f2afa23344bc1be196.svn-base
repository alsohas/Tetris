/**
 * 
 */
package actions;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import view.BoardPanel;
import view.Tetris;

/**
 * @author Abdullah
 * @version 3/4/2016
 */
public class ResizeAction extends AbstractAction {
    
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -1784061102630935763L;

    /**
     * Small blocks.
     */
    private static final int SMALL = 20;
    
    /**
     * Medium blocks.
     */
    private static final int MEDIUM = 35;
    
    /**
     * Large blocks.
     */
    private static final int LARGE = 40;
    
    /**
     * Panel to work on.
     */
    private final BoardPanel myPanel;
    
    /**
     * Action to perform.
     */
    private final String myAction;
    
    /**
     * This frame will get called repaint() on.
     */
    private final JFrame myFrame;
    
    /**
     * @param theAction Action.
     * @param thePanel The panel to resize.
     * @param theFrame The frame which myFrame will reference.
     */
    public ResizeAction(final String theAction, 
                        final BoardPanel thePanel, final Tetris theFrame) {
        super(theAction);
        myPanel = thePanel;
        myAction = theAction;
        myFrame = theFrame;
    }

    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        if ("Small".equals(myAction)) {
            myPanel.setBlockSize(SMALL);
        } else if ("Medium".equals(myAction)) {
            myPanel.setBlockSize(MEDIUM);
        } else if ("Large".equals(myAction)) {
            myPanel.setBlockSize(LARGE);
        }
        final int arbitraryResizeVal = 97;
        myPanel.repaint();
        myFrame.setSize(new Dimension(Tetris.EAST_PANEL_WIDTH + BoardPanel.BOARD_GAP * 2 
                                      + ((myPanel.getBlockSize() + 1) 
                                      * myPanel.getBoardWidth()), 
                                      arbitraryResizeVal + (myPanel.getBoardHeight() 
                                      * myPanel.getBlockSize())));
             
     
        final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        myFrame.setLocation(d.width / 2 - myFrame.getSize().width / 2, 
                      d.height / 2 - myFrame.getSize().height / 2);
        myFrame.setResizable(false);
        

    }
        
}


