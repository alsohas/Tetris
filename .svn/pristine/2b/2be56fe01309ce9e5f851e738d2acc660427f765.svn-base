package view;

import actions.BoardResizeAction;
import actions.EndGameAction;
import actions.HelpAction;
import actions.NewGameAction;
import actions.ResizeAction;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import model.TetrisPiece;

/**
 * The JFrame on which everything will be displayed.
 * @author Abdullah
 * @version 3/1/2016 
 */
public class Tetris extends JFrame {
    
    /**
     * Arbitrary integer value determining width for eastern panel of the frame.
     */
    public static final int EAST_PANEL_WIDTH = 185;

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 9024664215589683789L;
    
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
     * BoardPanel thats going in the center and contains all tetris graphics.
     */
    private final BoardPanel myPanel;
    
    /**
     * Menubar.
     */
    private final JMenuBar myMenuBar;
    
    /**
     * Next piece panel.
     */
    private JPanel myNextPiecePanel;
    
    /**
     * Panel with directions.
     */
    private JPanel myDirectionPanel;
    
    /**
     * This is the score panel.
     */
    private JPanel myScorePanel;

    /**
     * This is the label showing current level/difficulty.
     */
    private final JLabel myLevelLabel = new JLabel("Level: ");

    /**
     * Label shows current score.
     */
    private final JLabel myScoreLabel = new JLabel("Score: ");

    /**
     * Label shows number of lines cleared.
     */
    private final JLabel myLineClearLabel = new JLabel("Line cleared: ");

    /**
     * Label shows number of lines to be cleared for next level.
     */
    private final JLabel myRemainingLineLabel = new JLabel("Lines to lv: ");

    /**
     * This is the eastern panel.
     */
    private final JPanel myEastPanel;
    
    /**
     * Shows status.
     */
    private JLabel myStatusLabel;

   
    
    /**
     * Setting things up.
     * @throws Exception 
     * @throws Throwable 
     */
    public Tetris() throws Exception {
        super("Tetris");
        setLayout(new BorderLayout());        
        myPanel = new BoardPanel();

        
        myEastPanel = new JPanel();
        myMenuBar = new JMenuBar();

        

           

        setUpUtil();
        final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(d.width / 2 - getSize().width / 2, 
                      d.height / 2 - getSize().height / 2);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }


    /**
     * Calls bunch of other methods setting things up.
     */
    private void setUpUtil() {
        setUpNextPiecePanel();
        setUpDirectionPanel();
        setUpScorePanel();
        setFileMenu();
        setUpResizeMenu(); 
        setUpColorMenu();
        setUpHelptMenu();
        setUpEastPanel();
        add(myEastPanel, BorderLayout.EAST);
        add(myPanel, BorderLayout.CENTER);
        
        final int artbitraryResizeVal = 3;
        setPreferredSize(new Dimension(myPanel.getPreferredSize().width  
                                       + ((myPanel.getBoardScale() + 1) 
                                       * myPanel.getBoardHeight()) 
                                       + BoardPanel.BOARD_GAP * artbitraryResizeVal, 
                                       (myPanel.getBoardHeight()  
                                       * myPanel.getBlockSize())  
                                       + (BoardPanel.BOARD_GAP * artbitraryResizeVal)));


        pack();
        setResizable(false);
    }


    /**
     * Sets up the holi checkbox menu.
     */
    private void setUpColorMenu() {
        final JCheckBoxMenuItem colorButton = new JCheckBoxMenuItem("Holi mode");
        
        colorButton.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(final ItemEvent theEvent) {
                final Object source = theEvent.getItemSelectable();
                if (source.equals(colorButton)) {
                    try {
                        myPanel.activateColorMode();
                    
                    } catch (final UnsupportedAudioFileException | IOException e) {
                        e.printStackTrace();
                    }
                }
                if (theEvent.getStateChange() == ItemEvent.DESELECTED) {
                    myPanel.deactivateColorMode();
                }
            }        
        });
        
        final JMenu modeMenuItem = new JMenu("Game modes");
        modeMenuItem.add(colorButton);
        
        myMenuBar.add(modeMenuItem);
        
        
        
    }


    /**
     * Sets the eastern panel.
     */
    private void setUpEastPanel() {
        myEastPanel.setPreferredSize(new Dimension(EAST_PANEL_WIDTH, EAST_PANEL_WIDTH));
        myEastPanel.add(myNextPiecePanel);
        myEastPanel.add(myDirectionPanel);
        myEastPanel.add(myScorePanel);
        
        myStatusLabel = new JLabel("Game status: " + myPanel.getStatus());
        myEastPanel.add(myStatusLabel);
        
        
        
    }


    /**
     * Sets up scoring panel.
     */
    private void setUpScorePanel() {
        myScorePanel = new JPanel();
        myScorePanel.setLayout(new FlowLayout());
        final int arbitrarySizingVal = 120;
        myScorePanel.setPreferredSize(new Dimension(EAST_PANEL_WIDTH, arbitrarySizingVal));
        myScorePanel.setSize(new Dimension(EAST_PANEL_WIDTH, arbitrarySizingVal));
        
        myScorePanel.add(myLevelLabel);
        myScorePanel.add(myScoreLabel);
        myScorePanel.add(myLineClearLabel);
        myScorePanel.add(myRemainingLineLabel);
        
        final Border border = BorderFactory.createTitledBorder(
                       BorderFactory.createLoweredBevelBorder(), "Score");
        ((TitledBorder) border).setTitlePosition(TitledBorder.ABOVE_TOP);
        myScorePanel.setBorder(border);
        
    }


    /**
     * Sets up the panel with playing direction.
     */
    private void setUpDirectionPanel() {
        myDirectionPanel = new JPanel();
        myDirectionPanel.setLayout(new FlowLayout());
        
        myDirectionPanel.setPreferredSize(new Dimension(EAST_PANEL_WIDTH, EAST_PANEL_WIDTH));
        myDirectionPanel.setSize(new Dimension(EAST_PANEL_WIDTH, EAST_PANEL_WIDTH));
        
        myDirectionPanel.add(new JLabel("Right: right arrow"));
        myDirectionPanel.add(new JLabel("Left: left arrow")); 
        myDirectionPanel.add(new JLabel("Down: down arrow")); 
        myDirectionPanel.add(new JLabel("Drop: space"));
        myDirectionPanel.add(new JLabel("Rotate clockwise: x")); 
        myDirectionPanel.add(new JLabel("Rotate counter clockwise: z"));
        myDirectionPanel.add(new JLabel("Pause/resume: p"));
        

        final Border border = BorderFactory.createTitledBorder(
                       BorderFactory.createLoweredBevelBorder(), "How to play");
        ((TitledBorder) border).setTitlePosition(TitledBorder.ABOVE_TOP);
        myDirectionPanel.setBorder(border);

    }

    /**
     * Set up next piece panel.
     */
    private void setUpNextPiecePanel() {
        myNextPiecePanel = new JPanel() {
            /***/
            private static final long serialVersionUID = 1L;
            @Override
            public void paintComponent(final Graphics theG) {
                final int height = 120;
                super.paintComponent(theG);
                final Graphics2D g2 = (Graphics2D) theG;
                setSize(new Dimension(EAST_PANEL_WIDTH, height));
                setPreferredSize(new Dimension(EAST_PANEL_WIDTH, height));
                final TetrisPiece nextPiece = myPanel.getNextPiece();
                final int arbitraryCoordinateTransformVal1 = 20;
                final int arbitraryCoordinateTransformVal2 = 40;
                
                for (int i = nextPiece.getPoints().length - 1; i >= 0; i--) {
                    g2.setColor(BLOCK_COLOR[nextPiece.getBlock().ordinal() - 1]);
                    g2.fillRect(nextPiece.getPoints()[i].x() 
                                * arbitraryCoordinateTransformVal1 
                                + arbitraryCoordinateTransformVal2, 
                                -nextPiece.getPoints()[i].y() 
                                * arbitraryCoordinateTransformVal1 
                                + arbitraryCoordinateTransformVal2 * 2, 
                                arbitraryCoordinateTransformVal1, 
                                arbitraryCoordinateTransformVal1);
                    g2.setColor(Color.GRAY);
                    g2.setStroke(new BasicStroke(2));
                    g2.drawRect(nextPiece.getPoints()[i].x() 
                                * arbitraryCoordinateTransformVal1 
                                + arbitraryCoordinateTransformVal2, 
                                -nextPiece.getPoints()[i].y() 
                                * arbitraryCoordinateTransformVal1 
                                + arbitraryCoordinateTransformVal2 * 2, 
                                arbitraryCoordinateTransformVal1, 
                                arbitraryCoordinateTransformVal1);
                }
                myLevelLabel.setText("Current level: " + myPanel.getCurrentLevel());

                myScoreLabel.setText("Current score: " + myPanel.getScore());
                
                myLineClearLabel.setText("Lines cleared: " + myPanel.getLineCleared());
                
                myRemainingLineLabel.setText("Lines until level " + (myPanel.getCurrentLevel() 
                                + 1) + ": " 
                                + (myPanel.getLineUntilLevel()));
                
                myStatusLabel.setText("Game status: " + myPanel.getStatus());
                myEastPanel.repaint();
            }
        }; 
        
        final Border border = BorderFactory.createTitledBorder
                        (BorderFactory.createLoweredBevelBorder(), "Next piece");
        ((TitledBorder) border).setTitlePosition(TitledBorder.ABOVE_TOP);
        myNextPiecePanel.setBorder(border);
    }
    

    /**
     * Sets up the menu option for resizing window.
     */
    private void setUpResizeMenu() {
        final JRadioButtonMenuItem smallMenuItem = 
                        new JRadioButtonMenuItem(new ResizeAction("Small", myPanel, this));
        final JRadioButtonMenuItem mediumMenuItem = 
                        new JRadioButtonMenuItem(new ResizeAction("Medium", myPanel, this));
        final JRadioButtonMenuItem largeMenuItem = 
                        new JRadioButtonMenuItem(new ResizeAction("Large", myPanel, this));
        
        final JRadioButtonMenuItem defaultBoardMenuItem = 
                        new JRadioButtonMenuItem(new BoardResizeAction("10 x 20 (Default)",
                                                                       myPanel, this));
        final JRadioButtonMenuItem mediumBoardMenuItem = 
                        new JRadioButtonMenuItem(new BoardResizeAction("20 x  40", 
                                                                       myPanel, this));
        
        final JRadioButtonMenuItem largeBoardMenuItem = 
                        new JRadioButtonMenuItem(new ResizeAction("30 x 60", myPanel, this));
        
        final ButtonGroup resizeButtons = new ButtonGroup();
        resizeButtons.add(smallMenuItem);
        resizeButtons.add(mediumMenuItem);
        resizeButtons.add(largeMenuItem);
        
        final ButtonGroup resizeBoardButtons = new ButtonGroup();
        resizeBoardButtons.add(defaultBoardMenuItem);
        resizeBoardButtons.add(mediumBoardMenuItem);
        resizeBoardButtons.add(largeBoardMenuItem);
        
        final JMenu windowMenuItem = new JMenu("Resize window");
        
        final JMenu boardMenuItem = new JMenu("Resize board");
        
        windowMenuItem.add(smallMenuItem);
        windowMenuItem.add(mediumMenuItem);
        windowMenuItem.add(largeMenuItem);
        
        boardMenuItem.add(defaultBoardMenuItem);
        boardMenuItem.add(mediumBoardMenuItem);
        boardMenuItem.add(largeBoardMenuItem);
        
        final JMenu sizeMenuItem = new JMenu("Resize");
        sizeMenuItem.add(windowMenuItem);
        sizeMenuItem.add(boardMenuItem);

        
        myMenuBar.add(sizeMenuItem);
        setJMenuBar(myMenuBar);
        
    }
    
    /**
     * New game button is being set up.
     */
    private void setFileMenu() {
        final JMenuItem newGameMenuItem = 
                        new JMenuItem(new NewGameAction("New game", myPanel, this));
        final JMenuItem endGameMenuItem = 
                        new JMenuItem(new EndGameAction("End game", myPanel));
        
        final JMenu fileMenuItem = new JMenu("File");
        
        fileMenuItem.add(newGameMenuItem);
        fileMenuItem.add(endGameMenuItem);
        myMenuBar.add(fileMenuItem);
        
    }
    
    /**
     * Sets up help menu.
     */
    private void setUpHelptMenu() {
        final JMenu aboutMenu = new JMenu("Help");
        final JMenuItem aboutMenuItem = 
                        new JMenuItem(new HelpAction("About"));
        final JMenuItem scoringMenuItem = 
                        new JMenuItem(new HelpAction("Scoring"));
        aboutMenu.add(scoringMenuItem);
        aboutMenu.add(aboutMenuItem);
        myMenuBar.add(aboutMenu);
        
    }
    
    
}


        
    


