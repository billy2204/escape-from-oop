package graphics.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class GameWinOverlay {

    private boolean audioPlayed = false;
    private Clip clip;

    public void draw(Graphics g, int screenWidth, int screenHeight) {
        if (!audioPlayed) {
            playWinAudio();
            audioPlayed = true;
        }

        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, screenWidth, screenHeight);

        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        String text = "YOU PASSED OOP COURSE!";
        int textWidth = g.getFontMetrics().stringWidth(text);
        g.drawString(text, (screenWidth - textWidth) / 2, screenHeight / 2 - 50);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        String text2 = "Press ENTER to return to Menu";
        int textWidth2 = g.getFontMetrics().stringWidth(text2);
        g.drawString(text2, (screenWidth - textWidth2) / 2, screenHeight / 2 + 20);
    }

    private void playWinAudio() {
        try {
            File audioFile = new File("resources/audio/win.wav");
            if (audioFile.exists()) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();
            }
        } catch (Exception e) {
            // Ignore audio errors
        }
    }

    public void resetAudio() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
        audioPlayed = false;
    }
}