/**
 * 
 */
package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import model.Block;
import model.Board;
import model.TetrisPiece;
import sound.ClearSound;
import sound.DropSound;
import sound.LevelUpSound;
import sound.OverSound;

/**
 * @author Abdullah
 * @version 3/1/2016
 */
public class BoardPanel extends JPanel { //I need all the fields and if statements. 
                                        //Didn't have time to decompose.
    
    /**
     * This is to ensure the board is displayed on a 
     * consistent form accross different resolution monitors.
     */
    public static final int BOARD_GAP = 40;

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -3256290564832372809L;
    
    /**
     * Default timer is 1000 in milliseconds.
     */
    private static final int DEFAULT_TIMER = 1000;
    
    /**
     * This is used for holi mode, random color generator.
     */
    private static final Random R = new Random();
    
    
    /**
     * This is used for scoring.
     */
    private static final Integer[] SCORE_LIST = {40, 100, 300, 1200};
    
    /**
     * Default size of each square of tetris blocks.
     */
    private static final int BLOCK_SIZE = 20;
    
    /**
     * Default size of each square of tetris blocks.
     */
    private static final int BOARD_SCALE = 10;    
    
    /**
     * Icon for game over pane.
     */
    private static final Icon IMAGE_ICON = new ImageIcon("logo3.png");
    
    /**
     * Going to set these as block colors.
     */
    private static final Color[] BLOCK_COLOR = 
        {new Color(204, 102, 102), 
        new Color(102, 204, 102), 
        new Color(102, 102, 204), 
        new Color(204, 204, 102), 
        new Color(204, 102, 204), 
        new Color(102, 204, 204), 
        new Color(218, 170, 0)};

    /**
     * Font size for pause text.
     */
    private static final int FONT_SIZE = 30;

    /**
     * Highest level.
     */
    private static final int MAX_LEVEL = 10;

    /**
     * Every level-up the timer delay is increased.
     */
    private static final int DIFFICULTY = 100;

    /**
     * The scoring algorith used adds 4 points everytime a next piece is passed to the Board. 
     * -4 is being used to initialize the score to compensate for that.
     */
    private static final int INITIAL_SCORE = -4;

    /**
     * Number of lines needed to be cleared before next level.
     */
    private static final int LEVEL_REQUIREMENT = 5;
        
    /**
     * The tetris board that will be drawn.
     */
    private Board myBoard;
    
    /**
     * Swing timer for moving tetris pieces in milliseconds..
     */
    private final Timer myTimer;
        
    /**
     * Frozen blocks to be drawn.
     */
    private List<Block[]> myBlocks;

    /**
     * Is my game over?
     */
    private boolean myGameOver;
    
    /**
     * Next piece to be drawn.
     */
    private TetrisPiece myNextPiece;
    
    /**
     * Width of the drawn tetris shape and each grid.
     */
    private int myWidth;

    /**
     * Height of the drawn tetris shape and each grid.
     */
    private int myHeight;
    
    /**
     * 
     */
    private int myBlockSize = BLOCK_SIZE;
    
    /**
     * 
     */
    private int myBoardScale = BOARD_SCALE;
    
    
    /**
     * This gets added to myBoard so the panel can get input.
     */
    private final BoardObserver myObserver;
    

    /**
     * Display this when the game is paused.
     */
    private final JLabel myPauseLabel;
    
    /**
     * Initial score is set to -4. So just declaring this.
     */
    private int myScore = INITIAL_SCORE;
    

    /**
     * 
     */
    private int myLevel = 1;

    /**
     * Total number of lines cleared within one game.
     */
    private int myCleared;

    /**
     * This is being used to check if the level has changed. If the current level is different 
     * from this value, increase the difficulty level and change its value.
     */
    private int myOldLevel = 1;

    /**
     * Sets holi mode on/off.
     */
    private boolean myColorMode;
    
    /**
     * 
     */
    private ClearSound myClearSound;

    /**
     * 
     */
    private OverSound myOverSound;

    /**
     * 
     */
    private LevelUpSound myLevelSound;

    /**
     * 
     */
    private DropSound myDropSound;

    
    
    
    /**
     * Draws the tetris board and lays out the pieces.
     * @throws Exception ok
     * @throws UnsupportedAudioFileException 
     */
    public BoardPanel() throws UnsupportedAudioFileException, Exception {
        super();
        setBackground(Color.DARK_GRAY);
        myBoard = new Board();
        myObserver = new BoardObserver();
        myPauseLabel = new JLabel("PAUSED");

        
        myTimer = new Timer(DEFAULT_TIMER, new ActionListener() {
            @Override
                public void actionPerformed(final ActionEvent theEvent) {
                    myBoard.step();
            }
        });
        
        this.setFocusable(true);
        this.requestFocusInWindow();

        
        setUpBoard();      
        
        setPreferredSize(new Dimension(myBoard.getWidth() * myBoardScale + BOARD_GAP,
                                       myBoard.getHeight() * myBoardScale + BOARD_GAP));
    }
    
    /**
     * @throws UnsupportedAudioFileException ok
     * @throws IOException ok
     */
    private void setUpSound() throws UnsupportedAudioFileException, IOException {
        myClearSound = new ClearSound();
        myOverSound = new OverSound();
        myLevelSound = new LevelUpSound();
        myDropSound = new DropSound();
        
    }

    /**
     * Using keybindings since there was issues with KeyAdapter.
     * Sets up the key bindings.
     */
    private void setKeyBinding() { 
        //If statements needed so the user isn't able to move piece while paused.
        final String down = "down";
        final String right = "right";
        final String left = "left";
        final String drop = "drop";
        final String cw = "cw";
        final String ccw = "ccw";
        final String pause = "pause";
        
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put
            (KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), left);
        getActionMap().put(left, new AbstractAction() {

            private static final long serialVersionUID = -2767561233542192131L;
            
            public void actionPerformed(final ActionEvent theE) {
                if (myTimer.isRunning() && !myGameOver) {
                    myBoard.left();
                }
            }
        });       
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put
            (KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), right);
        getActionMap().put(right, new AbstractAction() {

            private static final long serialVersionUID = -2767561233542192131L;

            public void actionPerformed(final ActionEvent theE) {
                if (myTimer.isRunning() && !myGameOver) {
                    myBoard.right();
                }
            }
        });
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put
            (KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), down);
        getActionMap().put(down, new AbstractAction() {
            
            private static final long serialVersionUID = -2767561233542192131L;

            public void actionPerformed(final ActionEvent theE) {
                if (myTimer.isRunning() && !myGameOver) {
                    myBoard.down();
                }
            }
        });        
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put
            (KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), drop);
        getActionMap().put(drop, new AbstractAction() {

            private static final long serialVersionUID = -2767561233542192131L;

            public void actionPerformed(final ActionEvent theE) {
                if (myTimer.isRunning() && !myGameOver) {
                    myBoard.drop();
                }
                
            }
        });   
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put
            (KeyStroke.getKeyStroke(KeyEvent.VK_X, 0), cw);
        getActionMap().put(cw, new AbstractAction() {

            private static final long serialVersionUID = -2767561233542192131L;

            public void actionPerformed(final ActionEvent theE) {
                if (myTimer.isRunning() && !myGameOver) {
                    myBoard.rotateCW();
                }
            }
        });           
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put
            (KeyStroke.getKeyStroke(KeyEvent.VK_Z, 0), ccw);
        getActionMap().put(ccw, new AbstractAction() {

            private static final long serialVersionUID = -2767561233542192131L;

            public void actionPerformed(final ActionEvent theE) {
                if (myTimer.isRunning() && !myGameOver) {
                    myBoard.rotateCCW();
                }
            }
        });      
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put
            (KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), pause);
        getActionMap().put(pause, new AbstractAction() {
            private static final long serialVersionUID = -2767561233542192131L;
            public void actionPerformed(final ActionEvent theE) {
                if (myTimer.isRunning()) {
                    pause();
                } else if (!myTimer.isRunning() && !myGameOver) {
                    resume();
                }
                repaint();
            }
        });
    }

    /**
     * Setting up the board and some other information to go along.
     * Seperate method for cleaning up clutter from constructor.
     * @throws IOException 
     * @throws UnsupportedAudioFileException 
     */
    private void setUpBoard() throws UnsupportedAudioFileException, IOException {
        setUpSound();
        myBoard.addObserver(myObserver);
        myBoard.newGame();

        myTimer.start();
        setKeyBinding();


        myWidth = myBoard.getWidth();
        myHeight = myBoard.getHeight();
        

    }

    @Override
    protected void paintComponent(final Graphics theG) { 
        //I need the complexity. Didn't have time to decompose.
        super.paintComponent(theG);
        if (isPaused() && !isGameOver()) {
            myPauseLabel.setFont(new Font("Verdana", 1, FONT_SIZE));
            this.add(myPauseLabel);
            return;
        }
        remove(myPauseLabel);
        final Graphics2D g2 = (Graphics2D) theG;
        g2.setColor(Color.GRAY);

        g2.setStroke(new BasicStroke(1));
    
        for (int i = 0; i < myWidth + 1; i++) { //||||
            g2.drawLine(i * myBlockSize + BOARD_GAP, BOARD_GAP, i * myBlockSize + BOARD_GAP, 
                        myHeight * myBlockSize + BOARD_GAP);
        }
        
        for (int i = 0; i < myHeight + 1; i++) { //----
            g2.drawLine(BOARD_GAP, i * myBlockSize + BOARD_GAP, 
                        myWidth * myBlockSize + BOARD_GAP, 
                        i * myBlockSize + BOARD_GAP);
        }
        
        //
        g2.setStroke(new BasicStroke(2));
        
        int currentY = 0;
        int currentX = 0;
        if (Objects.nonNull(myBlocks)) {
            final int extraRows = 5;
            for (int i = myBlocks.size() - extraRows; i >= 0; i--) {
                for (final Block j: myBlocks.get(i)) {
                    try {
                        j.toString();
                        
                        g2.setColor(BLOCK_COLOR[j.ordinal() - 1]);
                        if (myColorMode) {
                            final int num = R.nextInt(j.ordinal()) + 1;
                            g2.setColor(BLOCK_COLOR[num - 1]);
                        }
                        g2.fillRect(currentX + BOARD_GAP, currentY 
                                    + BOARD_GAP, myBlockSize, myBlockSize);
                        g2.setColor(Color.GRAY);
                        g2.drawRect(currentX + BOARD_GAP, currentY 
                                    + BOARD_GAP, myBlockSize, myBlockSize);
                        
                    } catch (final NullPointerException e) {
                        //System.out.print("");
                        
                    }
                    currentX += myBlockSize;                   
                }
                currentX = 0;
                currentY += myBlockSize;
            
            }
        }
    }

    /**
     * @return The next piece that will be dropped.
     */
    public TetrisPiece getNextPiece() {
        return myNextPiece;
        
    }
    
    /**
     * Ends the game.
     */
    public void endGame() {
        if (!myGameOver) {
            myGameOver = true;
            
            repaint();
            myTimer.stop(); 
            JOptionPane.showMessageDialog(null, "Game is over", 
                            "Game Over", 0, IMAGE_ICON);
        }
    }
    
    /**
     * @return Is the game over?
     */
    public boolean isGameOver() {
        return myGameOver;
    }
    
    /**
     * Paused the game.
     */
    private void pause() {
        myTimer.stop();
//        myPause = true;
    }
    
    /**
     * Resumes the game.
     */
    private void resume() {
        this.remove(myPauseLabel);
        myTimer.start();
        
    }
    
   
    /**
     * @return Is the game paused?
     */
    public boolean isPaused() {
        boolean choice = false;
        if (isGameOver()) {
            choice = true;
        } else {
            choice = myTimer.isRunning();            
        }
        return !choice;
    }
    
    /**
     * Starts new game.
     * @throws IOException 
     * @throws UnsupportedAudioFileException 
     */
    public void newGame() throws UnsupportedAudioFileException, IOException {
        remove(myPauseLabel);
        myBoard.newGame();
        myGameOver = false;
        myLevel = 1;
        myScore = 0;
        myCleared = 0;
        myOldLevel = myLevel;
        myTimer.setDelay(DEFAULT_TIMER);
        myTimer.start();
        myClearSound.start();
        
    }

    /**
     * @param theSize Change the size of blocks.
     */
    public void setBlockSize(final int theSize) {
        myBlockSize = theSize;
        setBoardScale();
    }
    
    /**
     * @return The block size.
     */
    public int getBlockSize() {
        return myBlockSize;
    }
    
    /**
     * @return Board width.
     */
    public int getBoardWidth() {
        return myWidth;
    }
    
    /**
     * @return Board height.
     */
    public int getBoardHeight() {
        return myHeight;
    }
    
    /**
     * @param theWidth Width of board
     * @param theHeight Height of board.
     */
    public void setBoardSize(final int theWidth, final int theHeight) {
        endGame();
        myWidth = theWidth;
        myHeight = theHeight;
        myBoard = new Board(myWidth, myHeight);
        myBoard.addObserver(myObserver);
        setBoardScale();
        //myBoard.newGame();
        
        
    }
    
    /**
     * Set scaling for board.
     */
    private void setBoardScale() {
        myBoardScale = (myHeight * myWidth) / myBlockSize;
    }
    
    /**
     * @return Get scaling for board.
     */
    public int getBoardScale() {
        return myBoardScale;
    }
    
    /**
     * @return Current level.
     */
    public int getCurrentLevel() {
        return myLevel;
    }
    
    /**
     * @return Current score.
     */
    public int getScore() {
        return myScore;
    }
    
    /**
     * @return How many lines cleared.
     */
    public int getLineCleared() {
        return myCleared;
    }
    
    /**
     * @return How many lines until next level.
     */
    public int getLineUntilLevel() {
        return myLevel * LEVEL_REQUIREMENT - myCleared;
    }
    
    /**
     * Activates holi mode.
     * @throws IOException 
     * @throws UnsupportedAudioFileException 
     */
    public void activateColorMode() throws UnsupportedAudioFileException, IOException {
        myLevelSound.start();
        //myLevelSound = new LevelUpSound();
        myColorMode = true;
    }
    
    /**
     * Deactivates holi mode.
     */
    public void deactivateColorMode() {
        myColorMode = false;
    }
    
    /**
     * Whenever a line is cleared, difficulty is increased.
     */
    public void increaseDifficulty() {
        if (myLevel != myOldLevel && myLevel != MAX_LEVEL) {
            myOldLevel = myLevel;
            myTimer.setDelay(DEFAULT_TIMER - ((myLevel - 1) 
                            * DIFFICULTY));    //decrement timer by 100 ms.
            //System.out.println("miliseconds : " + (DEFAULT_TIMER - ((myLevel - 1) * 100)));
        }
        
    }
    
    /**
     * @return The current status of game.
     */
    public String getStatus() {
        final String status;
        if (isGameOver()) {
            status = "Game over";
        } else if (isPaused() && !isGameOver()) {
            status = "Paused";
        } else {
            status = "Running";
        }
        return status;
    }
    
    /**
     * This gets added to myBoard to retrieve data.
     * @author Abdullah
     * @version 3/3/2016
     */
    private class BoardObserver implements Observer {


        /* (non-Javadoc)
         * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
         */
        @Override
        public void update(final Observable theBoard, final Object theArgument) {
            if (theArgument instanceof List) {
                myBlocks = (List<Block[]>) theArgument;  //Couldn't fix it.
                myBoard = (Board) theBoard;
                //System.out.println(myBoard.toString());          
            } else if (theArgument instanceof Integer[]) {
                final Integer[] rowCleared = (Integer[]) theArgument;
                if (rowCleared.length != 0) {
                    try {
                        myScore += SCORE_LIST[rowCleared.length - 1] * myLevel;
                    } catch (final IndexOutOfBoundsException e) {
                        final int highestPossibleScoreIndex = 3;
                        myScore += SCORE_LIST[highestPossibleScoreIndex] * myLevel;
                    }
                    
                }
                myCleared += rowCleared.length;
                myLevel = (myCleared / LEVEL_REQUIREMENT) + 1;
                increaseDifficulty();
                
            } else if (theArgument instanceof Boolean) {
                myGameOver = !(boolean) theArgument; 
                //I don't know how to fix this warning.
                try {
                    myOverSound.start();
                
                } catch (final UnsupportedAudioFileException | IOException e) {
                    
                    e.printStackTrace();
                }
                endGame();
                myScore = 0;     


            } else if (theArgument instanceof TetrisPiece) {
                myNextPiece = (TetrisPiece) theArgument;
                myScore += -INITIAL_SCORE;
                try {
                    myDropSound.start();
                
                } catch (final UnsupportedAudioFileException | IOException e) {

                    e.printStackTrace();
                }

            } 
            
            repaint();
            
        }
    }




}
