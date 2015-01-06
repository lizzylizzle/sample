package com.github.lizzylizzle.mmg;

import com.github.lizzylizzle.mmg.ui.utilSprite;


/**
 * Game controller
 */
class Controller {

    private Thread fThread;

    /**
     * Our ui loop runs when everything is initialised.
     * True goes into update loop. False blocks updating.
     */
    private boolean gameRunning = true;

    /**
     * Time between our frames in milliseconds
     */
    private long sleep = 0L;

    /**
     * We calculate frames in our loop, every 40st frame we will try to drop a candy
     */
    private int mmFallCount = 0;


    /**
     * Have our model and view foo
     */
    private final Model model;
    private final View view;


    /**
    * Our ui controller
    * @param  model our generic model for our ui
    * @param  view our generic ui view
    */
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }



    /**
     * Every frame calls this function. Here is were our ui is at.
     */
    void updateScreen() {

        // UPDATE UI ELEMENTS
        view.update(this.sleep);

        // ONE CANDY EACH 40 FRAME UPDATES
        if (this.mmFallCount == 40) {
            this.mmFallCount = 0;
            view.myViewCandy.show();
        }
        ++mmFallCount;

        /**
         * Find out if we did catch our Candy or if it reached the ground
         */
        int screenHeight = view.getScreenHeight();

        utilSprite[] mm = view.myViewCandy.getCandy();

        /**
         * Check for every candy which is occupied if it catch or reached ground
         */
        for (int x = 0; x < model.getCandies(); ++x) {

            // A FALLING M&M
            if (!mm[x].notOccupied) {

                int mmTopTop = mm[x].getY(); // MM top top

                // DID MM REACH GROUND?
                if (mmTopTop > screenHeight - 120) {

                    /**
                     * Candy reached ground
                     */
                    mm[x].Speed = 0;
                    mm[x].notOccupied = true;


                }

            }
        }
    }


    /**
     * Start new thread
     */
    void initThread() {
        try {
            if (this.fThread == null) {
                this.fThread = new Thread(Thread.currentThread());
                this.fThread.start();
            }
        } catch (Exception ex) {
            System.out.println("Error" + ex.getMessage());
        }

    }



    /**
     * Our magic ui loop!
     */
    void gameLoop() {

        long beforeTime = System.currentTimeMillis();

        if (this.fThread != null) {
            // Runs forever
            while (true) {

                this.sleep = System.currentTimeMillis() - beforeTime;
                beforeTime = System.currentTimeMillis();

                if (gameRunning) {
                    this.updateScreen();
                }

                try {
                    // FRAME SLEEPING TIME FOR NEXT UPDATE
                    int DELAY = 30;
                    Thread.sleep((long) DELAY);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }


    /**
     * Controller of our Game starts
     */
    public void init() {

        // Items come from model and passed into our view so they can paint
        view.ui(model.getCandies(), model.getTheme());

        initThread();

        /**
         * Important this function loops forever
         * So put as last in our setup
         */
        gameLoop();

    }


}

