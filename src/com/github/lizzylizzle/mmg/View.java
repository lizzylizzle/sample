package com.github.lizzylizzle.mmg;

import com.github.lizzylizzle.mmg.ui.*;

import javax.swing.*;
import java.awt.*;

/**
 * Our main Game view holding all UI components
 */
class View {

    private final JFrame frame;
    private final JLayeredPane layer;


    // PUBLIC ON VIEW (for example view.myCandy can be used in controller)
    public viewCandy myViewCandy;


    /**
     * Create basic frame with a layered panel (easy to add elements and change position)
     */
    public View() {

        frame = new JFrame();
        frame.setTitle("M&M World by Lizzylizzle");
        frame.setPreferredSize(new Dimension(600, 530));
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.WHITE);


        layer = new JLayeredPane();

        frame.add(layer, BorderLayout.CENTER);
        layer.setBounds(0, 0, 600, 480);


        frame.pack();
        frame.setVisible(true);

    }


    /**
     * Our main ui view has different UI components
     * @param theme Our ui theme (graphics)
     */
    void ui(int max, String theme) {

        myViewCandy = new viewCandy(theme);

        myViewCandy.init(layer, max);
    }




    /**
     * In our ui loop we update all objects which can move/animate
     * @param sleep time in between loop
     */
    void update(long sleep) {
        myViewCandy.update(sleep);
    }


    /**
     * Get our screen height
     * @return height of current screen
     */
    public int getScreenHeight() {
        return this.frame.getHeight();
    }

}

