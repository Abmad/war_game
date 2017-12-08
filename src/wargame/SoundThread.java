package wargame;

import javazoom.jl.player.Player;

import java.io.FileInputStream;

/**
 * classe permet de gerer les sons du jeu la cl
 */
public class SoundThread extends Thread {

    private String path;
    private boolean running;
    public SoundThread(String soundPath) {
        path = soundPath;

    }

    @Override
    public void run() {
        try {
            while (true) {
//                        Generic.this.mediaPlayer.play();
                try {
                    FileInputStream fis = new FileInputStream(path);
                    Player playMP3 = new Player(fis);
                    playMP3.play();
                } catch (Exception exc) {
                    exc.printStackTrace();
                    System.out.println("Failed to play the file.");
                }
                this.interrupt();
                if(!running)
                    yield();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void pauseThread() throws InterruptedException
    {
        yield();
        running = false;
    }

    public void resumeThread()
    {
        running = true;
    }


}
