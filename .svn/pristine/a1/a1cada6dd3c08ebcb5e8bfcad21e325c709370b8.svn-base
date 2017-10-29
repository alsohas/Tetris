package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import view.BoardPanel;

/**
 * @author Abdullah
 * @version 3/8/16
 */
public class EndGameAction extends AbstractAction {

    /**
     * 
     */
    private static final long serialVersionUID = 3878470051550513765L;
    /**
     * Board on which we end game.
     */
    private final BoardPanel myBoard;
    

    /**
     * @param theBoard This is going to be set as myBoard.
     * @param theName This is name of the button.
     */
    public EndGameAction(final String theName, 
                         final BoardPanel theBoard) {
        super(theName);
        myBoard = theBoard;
    }

    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myBoard.endGame();

    }

}
