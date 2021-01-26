package mainpackage.Input;

import mainpackage.Game;
import mainpackage.objects.PyscCircle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Input implements KeyListener, MouseListener, MouseMotionListener {

    private Game game;

    private int[] values = new int[5]; // x : y : zoom : quit : blank
    private int[] mousePos = new int[2]; // x : y
    private int[] mousevals = new int[3]; //Leftclick : Rightclick : wheelclick

    public Input(Game game){
        game.canvas.addKeyListener(this);
        game.canvas.addMouseListener(this);
        game.canvas.addMouseMotionListener( this );
        this.game = game;

    }

    //
    //  KEYBOARD
    //

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case( KeyEvent.VK_UP ):
                values[1] = 1;
                break;
            case( KeyEvent.VK_DOWN ):
                values[1] = -1;
                break;
            case( KeyEvent.VK_RIGHT ):
                values[0] = 1;
                break;
            case( KeyEvent.VK_LEFT ):
                values[0] = -1;
                break;
            case( KeyEvent.VK_Q ):
                values[2] = 1;
                break;
            case( KeyEvent.VK_E ):
                values[2] = -1;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case( KeyEvent.VK_UP ):
                values[1] = 0;
                break;
            case( KeyEvent.VK_DOWN ):
                values[1] = 0;
                break;
            case( KeyEvent.VK_RIGHT ):
                values[0] = 0;
                break;
            case( KeyEvent.VK_LEFT ):
                values[0] = 0;
                break;
            case( KeyEvent.VK_Q ):
                values[2] = 0;
                break;
            case( KeyEvent.VK_E ):
                values[2] = 0;
                break;
        }
    }

    public int[] getValues(){ return values; }

    //
    //  MOUSE
    //

    private void addCircle( int[] vector ){
        //objects.add(new PyscCircle( gravityEngine,5,mousePos[0],mousePos[1],vector[0],vector[1] ));

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        mousePos[0] = e.getX();
        mousePos[1] = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if( e.getButton() == MouseEvent.BUTTON1 ){
            int[] vector = dragVector( e.getX() , e.getY() );
            game.addCircle( mousePos[0] / game.cam.zoom + game.cam.x - game.width / (2 * game.cam.zoom )
                    ,-mousePos[1] / game.cam.zoom + game.cam.y + game.height / (2 * game.cam.zoom ), vector[0] , vector[1] );
        }

        else if( e.getButton() == MouseEvent.BUTTON3 ){
            int[] vector = dragVector( e.getX() , e.getY() );
            PyscCircle c = new PyscCircle(10 ,mousePos[0] / game.cam.zoom + game.cam.x - game.width / (2 * game.cam.zoom )
                    ,-mousePos[1] / game.cam.zoom + game.cam.y + game.height / (2 * game.cam.zoom ), vector[0] , vector[1]);
            c.isStatic = true;
            game.addCircle( c );

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private int[] dragVector( int x , int y ){
        int[] result = { mousePos[0] - x , -mousePos[1] + y };
        return result;
    }

    int lastX = 10000;
    int lastY = 10000;
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    private void addStaticCircle(){
        PyscCircle c = new PyscCircle(10 ,mousePos[0] / game.cam.zoom + game.cam.x - game.width / (2 * game.cam.zoom )
                ,-mousePos[1] / game.cam.zoom + game.cam.y + game.height / (2 * game.cam.zoom ), 0 , 0);
        c.isStatic = true;
        game.addCircle( c );
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
