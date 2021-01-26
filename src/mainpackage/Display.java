package mainpackage;

import javax.swing.*;
import java.awt.*;
public class Display {

    private JFrame frame;
    private Canvas canvas;
    private JLabel label;

    private int width, height;
    private String title;

    public Display( int width , int heigth , String title ){

        this.width = width;
        this.height = heigth;
        this.title = title;

        initialize();
    }

    private void initialize(){
        frame = new JFrame( title );
        //frame.setSize( width , height );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable( true );
        //frame.setLocationRelativeTo( null );


        label = new JLabel();
        label.setSize( new Dimension( 60 , 20 ) );
        label.setFont(Font.getFont(Font.SANS_SERIF));

        canvas = new Canvas();
        canvas.setSize(  new Dimension( width ,height ) );

        frame.add(label);
        frame.add(canvas);
        //label.setForeground( Color.WHITE );

        frame.setSize(  new Dimension( width ,height )  );
        frame.setVisible( true );
        frame.pack();
    }

    public Canvas getCanvas(){
        return canvas;
    }

    public  JFrame getFrame(){ return frame; }

    public  JLabel getLabel(){ return label; }
}
