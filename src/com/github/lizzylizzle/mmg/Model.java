package com.github.lizzylizzle.mmg;

/**
 * Game Model holding all configurations and game data
 */
class Model {

    private String theme;
    private final int candies;


    /**
     * Constructor for our Game Model
     * It currently holds: lives, score and high score
     */
    public Model() {
        theme = "summer";
        candies = 8;
    }

    /**
     * Get our theme
     * @return theme given for graphics
     */
    public String getTheme() {
        return theme;
    }

    /**
     * Get our amount of different candies
     * @return amount
     */
    public int getCandies() {
        return candies;
    }

}
