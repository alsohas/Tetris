package actions;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.AbstractAction;

import view.BoardPanel;
import view.Tetris;

/**
 * @author Abdullah
 * @version 3/8/2016
 */
public class NewGameAction extends AbstractAction {
    
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -7620633558477783987L;
    
    /**
     * Board which will called newGame() on.
     */
    private final BoardPanel myBoard;
    
    /**
     * Frame which will called repaint() on after newGame().
     */
    private final Tetris myFrame;

    /**
     * @param theBoard This is going to be set as myBoard.
     * @param theName This is name of the button.
     * @param theFrame This is the frame which myFrame will reference.
     */
    public NewGameAction(final String theName, 
                         final BoardPanel theBoard, final Tetris theFrame) {
        super(theName);
        myBoard = theBoard;
        myFrame = theFrame;
    }


    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        try {
            myBoard.newGame();
        
        } catch (final UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        myFrame.repaint();
        

    }

}
