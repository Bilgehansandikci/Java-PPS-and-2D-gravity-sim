package mainpackage;

import mainpackage.Display;
import mainpackage.Input.Input;
import mainpackage.Miscellaneous.Camera;
import mainpackage.Physics.Gravityengine;
import mainpackage.Physics.PhysicsEngine;
import mainpackage.Physics.PpsEngine;
import mainpackage.objects.PyscCircle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.io.Writer;
import java.util.*;


public class Game{

    public Camera cam;

    public JFrame frame;
    public Canvas canvas;
    public JLabel label;
    private Graphics g;
    private BufferStrategy bs;

    //int x = 0;
    public int width, height;

    private boolean running = false;

    //Input
    Input input;

    //UI
    JTextField fpsText;

    //Scene
    public ArrayList<PyscCircle> objects = new ArrayList<PyscCircle>();
    ArrayList<PyscCircle> objects2 = new ArrayList<PyscCircle>();
    ArrayList<PyscCircle> registered = new ArrayList<PyscCircle>();
    //ArrayList<PyscCircle> removed = new ArrayList<PyscCircle>();
    //ArrayList<PyscCircle> created = new ArrayList<PyscCircle>();

    //Engine
    Gravityengine gravityEngine = new Gravityengine(ScenePreferences.getGravityEnginePreferences()[0]);
    PpsEngine ppsEngine = new PpsEngine(ScenePreferences.getPPSEnginePreferences());
    PpsEngine ppsEngine2 = new PpsEngine(0,1,50,6.7);
    PhysicsEngine engine;

    public Game( Display display ){

        this.frame = display.getFrame();
        this.canvas = display.getCanvas();
        this.label = display.getLabel();
        height = this.canvas.getHeight();
        width = this.canvas.getWidth();
        running = true;

        start();
        loop();
    }

    private  void start(){
        loadpreferences();
        cam = new Camera(0,0,width,height ,0.3);

        canvas.createBufferStrategy(3);

        input = new Input(this);
    }

    private void loadpreferences(){
        if( ScenePreferences.getmode() == 1 ){
            for(int i = 0 ; i < ScenePreferences.getCircleCount() ; i++ ){
                objects.add( new PyscCircle(Math.random() * 10,3*(Math.random() * width - width/2),
                        3*(Math.random() * height - height/2), (Math.random()-.5) * 300,(Math.random()-.5) * 300));
            } engine = gravityEngine;
        }
        else if( ScenePreferences.getmode() == 2 ){
            for(int i = 0 ; i < ScenePreferences.getCircleCount() ; i++ ){
                objects.add( new PyscCircle( 10 , 0 , 0 , Math.random() * 360 ) );
            }engine = ppsEngine;
        }
        else if( ScenePreferences.getmode() == 2 ){
            for(int i = 0 ; i < ScenePreferences.getCircleCount() ; i++ ){
                //objects.add( new PyscCircle(10 , 0 , 0, Math.random() * 360));
                objects.add( new PyscCircle(10 , 2*(Math.random() * width - width/2),
                        2*(Math.random() * height - height/2) , Math.random() * 360));
                //objects.add( new PyscCircle(10 , Math.random() * width , Math.random() * height , Math.random() * 360));
            } engine = ppsEngine;
        }

    }

    //Timer and fps vars
    long totalTime = 0;
    long lastTime = System.nanoTime(); //1000 ms in sec
    long now;
    int fps = ScenePreferences.getfps();
    int realfps;
    int timePerFrame = 1000000000 / fps;
    long delta = 0;
    long deltatimelastframe;
    long frametime1;
    long frametime2 = System.nanoTime();


    private void loop(){

        while(running){

            now = System.nanoTime();
            delta += now - lastTime;
            //System.out.println(timePerFrame);
            //System.out.println(now - lastTime);
            lastTime = now;



            if( delta >= timePerFrame )
            {
                totalTime += delta;
                frametime1 = System.nanoTime();
                deltatimelastframe = (frametime1 - frametime2);
                realfps = (int) (1000000000 / deltatimelastframe);
                frametime2 = System.nanoTime();

                //System.out.println( "fps = " + realfps);
                //System.out.println(totalTime);

                update();
                render();
                //System.out.println(keyInput);

                delta = 0;
            }



        }
    }

    private void update(){

        //Gravity part
        //objects2 = ppsEngine2.Update2( objects2 , objects, 1.0 / realfps );
        objects = engine.Update( objects ,1.0 / realfps );

        //Input processing
        cam.x += input.getValues()[0] * cam.speed *5* deltatimelastframe;
        cam.y += input.getValues()[1] * cam.speed *5* deltatimelastframe;
        cam.zoom += input.getValues()[2] * cam.speed * deltatimelastframe * 0.01 * cam.zoom;


    }

    private void render(){

        bs = canvas.getBufferStrategy();
        g = bs.getDrawGraphics();

        //Clear last frame
        g.setColor(Color.black);
        g.fillRect(0,0 , width , height );



        //Draw

        //cam.setZoom( (totalTime / 1000000000000000.0) );
        cam.render2D( objects , g );
        mergeObjects();
        //cam.render2D( objects2 , g );

        //g.fillOval(x,50,50,50);

        //Update

        //Write Info
        label.setText( "FPS : " + realfps );

        //x++;
        bs.show();
        g.dispose();
    }

    //Input Triggered Functions
    public void addCircle( double x , double y , double xvel , double yvel ){

        for(int i = 0 ; i < 1 ; i++)
        registered.add(new PyscCircle( 10 , x , y , xvel , yvel ));
        //objects.add( new PyscCircle( 10 , x , y , xvel , yvel ) );
    }
    public void addCircle( PyscCircle c ){

        for(int i = 0 ; i < 1 ; i++)
            registered.add( c );
        //objects.add( new PyscCircle( 10 , x , y , xvel , yvel ) );
    }

    private void mergeObjects(){
        for( PyscCircle c : registered ){
            objects.add( c );
        }
        registered = new ArrayList<PyscCircle>();
    }
}
