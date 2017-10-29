package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 * @author Abdullah
 * @version 3/11/2016
 * Used for buttons in the help menu.
 */
public class HelpAction extends AbstractAction {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -797779727156494576L;
    
    /**
     * Used the string "About" twice in the class so made it a constant. 
     */
    private static final String ABOUT = "About";
    
    /**
     * Used the string "About" twice in the class so made it a constant. 
     */
    private static final String SCORING = "Scoring";
    
    /**
     * Name of the button. 
     */
    private final String myName;
    

    /**
     * @param theName Sets the name of the button.
     */
    public HelpAction(final String theName) {
        super(theName);
        myName = theName;
    }

    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        if (ABOUT.equals(myName)) {
            JOptionPane.showMessageDialog(null, "Tetris Project" 
                            + "\nTCSS 305" + "\nby Abdullah Islam" , 
                            ABOUT, 0, null);
        } else if (SCORING.equals(myName)) {
            JOptionPane.showMessageDialog(null, "Points for every piece dropped: 4"
                                          + "\nLevel up: every 5 lines cleared"
                                          + "\nPoints if one line cleared: level X 40"
                                          + "\nPoints if two lines cleared: level X 100"
                                          + "\nPoints if three lines cleared: level X 300"
                                          + "\nPoints if four+ lines cleared: level X 1200"
                                          + "\nFalling speed at level 1: 1000 miliseconds"
                                          + "\nSpeed increase per level: 100 miliseconds", 
                                          SCORING, 0);
        }

    }



}
