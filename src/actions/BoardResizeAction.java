/**
 * 
 */
package actions;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import view.BoardPanel;
import view.Tetris;

/**
 * RESIZES BOARD.
 * @author Abdullah
 * @version 3/11/2016
 */
public class BoardResizeAction extends AbstractAction {
    
    /**
     * ID.
     */
    private static final long serialVersionUID = -8545102118191841890L;

    /**
     * Defautl size for board.
     */
    private static final Dimension DEFAULT = new Dimension(10, 20);
    
    /**
     * Medium size option. 20 by 40 grid.
     */
    private static final Dimension MEDIUM = new Dimension(20, 40);
    
    /**
     * Large size option. 30 by 60 grid.
     */
    private static final Dimension LARGE = new Dimension(30, 60);

    /**
     * Name of button.
     */
    private final String myName;
    
    /**
     * Panel which contains board.
     */
    private final BoardPanel myPanel;
    
    /**
     * Frame which contains panel.
     */
    private final Tetris myFrame;


    /**
     * @param theName name of swing component.
     * @param thePanel BoardPanel which will be resized along with the board.
     * @param theFrame theFrame which contains swing component.
     */
    public BoardResizeAction(final String theName, 
                             final BoardPanel thePanel, final Tetris theFrame) {
        super(theName);
        myName = theName;
        myPanel = thePanel;
        myFrame = theFrame;
    }


    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        final int arbitraryResizeVal = 107;
        if ("10 x 20 (Default)".equals(myName)) {
            myPanel.setBoardSize(DEFAULT.width, DEFAULT.height);
        } else if ("20 x  40".equals(myName)) {
            myPanel.setBoardSize(MEDIUM.width, MEDIUM.height);
        } else if ("30 x 60".equals(myName)) {
            myPanel.setBoardSize(LARGE.width, LARGE.height);
        }
        myFrame.setSize(new Dimension(Tetris.EAST_PANEL_WIDTH + BoardPanel.BOARD_GAP * 2 
                                      + ((myPanel.getBlockSize() + 1) 
                                      * myPanel.getBoardWidth()), 
                                      arbitraryResizeVal + (myPanel.getBoardHeight() 
                                      * myPanel.getBlockSize())));
        final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        myFrame.setLocation(d.width / 2 - myFrame.getSize().width / 2, 
                            d.height / 2 - myFrame.getSize().height / 2);

        
        myPanel.repaint();

    }

}
