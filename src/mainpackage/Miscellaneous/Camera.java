package mainpackage.Miscellaneous;


import mainpackage.objects.PyscCircle;

import java.awt.*;
import java.util.ArrayList;

public class Camera {

    public double x , y;
    private double width , height;
    public double zoom;
    public double speed = 0.0000001;

    public Camera( double x , double y , double width , double height , double zoom ){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.zoom = zoom;
    }

    public void setZoom( double zoom ){ this.zoom = zoom; }

    public int translateX(double x){
        return (int)( (x - this.x) * zoom + width/2);
    }

    public int translateY(double y){
        return (int)( (-y + this.y) * zoom + height/2 );
    }

    public void render2D( ArrayList<PyscCircle> circles , Graphics g){

        g.setColor(Color.GREEN);
        g.drawLine( translateX(0) , 0 , translateX(0) ,720);
        g.setColor(Color.RED);
        g.drawLine( 0 , translateY(0) , 1280 ,translateY(0));
        for( PyscCircle o : circles ){
            g.setColor( o.color );
            g.fillOval( translateX(o.x) - (int)(o.size * zoom/2),translateY(o.y) - (int)(o.size * zoom/2),
                    (int)Math.ceil( o.size * zoom ),(int)Math.ceil(o.size * zoom ));

            //g.fillOval( (int)( ((o.x - (o.size/2 * zoom)) * zoom + ( width/2 - x * zoom ))),(int)( -((o.y - (o.size/2 * zoom)) ) * zoom + ( height/2 + y * zoom )),(int)Math.ceil( o.size * zoom ),(int)Math.ceil(o.size * zoom ));
        }//- (o.size/2 * zoom)
    }

}
