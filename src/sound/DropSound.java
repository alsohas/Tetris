package sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * @author Abdullah
 * @version 6/5/16
 */
public class DropSound {
    

    /**
     * 
     */
    private static final long DELAY = 0;

    /**
     * 
     */
    private static Mixer mixer;
    
    /**
     * 
     */
    private static Clip clip;
    
    /**
     * @throws UnsupportedAudioFileException ok
     * @throws IOException ok
     */
    public DropSound() throws UnsupportedAudioFileException, IOException {

    }
    /**
     * @throws IOException 
     * @throws UnsupportedAudioFileException 
     * 
     */

    
    /**
     * @throws IOException 
     * @throws UnsupportedAudioFileException 
     * 
     */
    public void start() throws UnsupportedAudioFileException, IOException {
        final Mixer.Info[] mixInfor = AudioSystem.getMixerInfo();
        
        mixer = AudioSystem.getMixer(mixInfor[0]);
        
        final DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
        
        try {
            clip = (Clip) mixer.getLine(dataInfo);
        } catch (final LineUnavailableException e) {
            e.printStackTrace();
        }
        
        try {
            final File file = new File("drop.wav");
            final AudioInputStream in = AudioSystem.getAudioInputStream(file);

            clip.open(in);
        } catch (final LineUnavailableException e) {
            e.printStackTrace();
        }
        
        clip.start();
        do {
            try {
                Thread.sleep(DELAY);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        } while (clip.isActive());
    }

}
