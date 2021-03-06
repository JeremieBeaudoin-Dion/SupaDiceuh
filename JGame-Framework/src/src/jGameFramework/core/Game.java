package jGameFramework.core;

import addResourceLoaderHere.GameInformation;
import jGameFramework.core.threadObjects.GameThreadHandler;
import jGameFramework.exceptions.ActionInvocationException;
import jGameFramework.exceptions.EmptyThreadException;
import jGameFramework.physicalObjects.Position;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * The Main class for the game
 * This class handles game flow
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Game {

    final static String GAME_NAME = GameInformation.GAME_NAME;

    public static int WINDOW_WIDTH = GameInformation.WINDOW_WIDTH;
    public static int WINDOW_HEIGHT = GameInformation.WINDOW_HEIGHT;

    final static boolean ANTIALIASING = GameInformation.ANTIALIASING;

    final static boolean RESIZABLE = GameInformation.RESIZABLE;

    // Variables for the game
    private static boolean isRunning = true;
    private static final long FPS = GameInformation.FRAMES_PER_SECOND;
    private static final long OPTIMAL_TIME = 1000000000 / FPS;

    // Instances of the jGameFramework.core elements of the game
    private ImageHandler imageHandler;
    private GameThreadHandler gameThreadHandler;

    /**
     * Main method to run the game
     */
    public static void main(String[] args) {

        Game game = null;
        try {
            game = new Game();

        } catch (IOException | UnsupportedAudioFileException | ActionInvocationException | LineUnavailableException e) {
            e.printStackTrace();
            System.exit(0);
        }

        game.run();

    }

    /**
     * This method will set up everything needed for the game to run
     */
    public Game() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        imageHandler = new ImageHandler();

        gameThreadHandler = new GameThreadHandler();

        imageHandler.addKeyListener(new KeyHandler(gameThreadHandler));
        imageHandler.addMouseListener(new MouseHandler(gameThreadHandler));
        imageHandler.addComponentListener(new FrameResizeHandler(gameThreadHandler));
        imageHandler.addMouseWheelListener(new MouseWheelHandler(gameThreadHandler));
    }

    /**
     * This method runs the game in a loop
     *
     * This loop doesn't uses multi thread to avoid concurrent
     * modification exceptions and to ensure that there
     * is no delay between what the player sees and what
     * the game actually looks like
     *
     * Inspired by Kevin Glass' Variable TimeStep loop
     */
    private void run() {

        long lastLoopTime = System.nanoTime();

        while (isRunning) {

            // work out how long its been since the last update, this
            // will be used to calculate how far the entities should
            // move this loop
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength / (double) OPTIMAL_TIME;

            try {

                update(delta);

            } catch (ActionInvocationException | EmptyThreadException e) {
                /*
                 * Something went wrong in the code, it is due to
                 * bad implementation or use of the Actions. A change
                 * to the code is required.
                 */
                e.printStackTrace();
                isRunning = false;
            }

            /*
             * Sleep the real value of time needed to keep the desired FrameRate.
             */
            try {
                if (lastLoopTime - System.nanoTime() + OPTIMAL_TIME > 0){
                    Thread.sleep((lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!isRunning) {
            System.exit(0);
        }

    }

    /**
     * Updates the game every frame
     */
    private void update(double deltaValue) {

        try {
            gameThreadHandler.update(deltaValue);
            imageHandler.update(gameThreadHandler.getImages());

            imageHandler.resizeIfNotNull(gameThreadHandler.getResizeAndSetNull());

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    static void resize(Position newSize) {
        WINDOW_WIDTH = newSize.getX();
        WINDOW_HEIGHT = newSize.getY();
    }

}