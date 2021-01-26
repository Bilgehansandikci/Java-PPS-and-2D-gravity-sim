package mainpackage.Physics;

import mainpackage.objects.PyscCircle;

import java.awt.*;
import java.util.ArrayList;

public class PpsEngine extends PhysicsEngine{

    double alpha;
    double beta;
    double range;
    double vel;
    ArrayList<ArrayList<ArrayList<PyscCircle>>> chunks = new ArrayList<ArrayList<ArrayList<PyscCircle>>>();

    public PpsEngine( double alpha, double beta, double range, double vel){
        this.alpha = alpha * Math.PI / 180.0;
        this.beta = beta * Math.PI / 180.0;
        this.range = range;
        this.vel = vel;

        initchunks();
    }
    public PpsEngine( double[] values ){
        this.alpha = values[0] * Math.PI / 180.0;
        this.beta = values[1] * Math.PI / 180.0;
        this.range = values[2];
        this.vel = values[3];

        initchunks();
    }

    private void initchunks(){
        for(int i = 0 ; i < 80 ; i ++){
            chunks.add( new ArrayList<ArrayList<PyscCircle>>() );
            for(int j = 0 ; j < 80 ; j ++){
                chunks.get(i).add( new ArrayList<PyscCircle>());
            }
        }

    }

    //helper
    ArrayList<PyscCircle> updcircles = new ArrayList<PyscCircle>();

    public ArrayList<PyscCircle> Update( ArrayList<PyscCircle> circles , double deltaTime ){

        PyscCircle circle;
        updcircles = circles;

        int N = 0;
        int Nt = 0;
        int Nt5 = 0;

        for( int i = 0 ; i < circles.size(); i++ ){
            for( int j = 0 ; j < circles.size(); j++ ){
                if( i != j ){

                    double xdif = circles.get(j).x - circles.get(i).x;
                    double ydif = circles.get(j).y - circles.get(i).y;
                    double d = Math.sqrt( xdif * xdif + ydif * ydif );

                    if( d <= range ){
                        if( d <= range * 0.3 )
                            Nt5++;
                        updcircles.get(i).color = Color.red;
                        Nt++;
                        N += calculateN( circles.get(i).rotation , xdif , ydif );
                    }
                }


            }

            circle = circles.get(i);
            if( !circle.isStatic ){
                circle.rotation +=  alpha + beta * Math.signum( N ) * Nt;            //Update angle
                //circle.rotation %= 2 * Math.PI;                 //Mod the angle to ensure 0 < A < 360
                circle.x += Math.cos( circle.rotation ) * vel;  //Update x
                circle.y += Math.sin( circle.rotation ) * vel;  //Update y
                circle.color = setcolor( Nt , Nt5 );                             //Set color
                N = 0;                                          //Reset N
                Nt = 0;
                Nt5 = 0;
            }


        }

        return circles;
    }

    public ArrayList<PyscCircle> Update2( ArrayList<PyscCircle> circles , ArrayList<PyscCircle> circles2 , double deltaTime ){
        ArrayList<PyscCircle> changed = new ArrayList<PyscCircle>();
        PyscCircle circle;
        updcircles = circles;

        int N = 0;
        int Nt = 0;

        for( int i = 0 ; i < circles.size(); i++ ){
            for( int j = 0 ; j < circles2.size(); j++ ){

                double xdif = circles2.get(j).x - circles.get(i).x;
                double ydif = circles2.get(j).y - circles.get(i).y;
                double d = Math.sqrt( xdif * xdif + ydif * ydif );

                if( d <= range ){
                    if( d < circles.get(i).size && !changed.contains(circles.get(i))
                            && !circles2.get(j).color.equals( Color.MAGENTA ) && !circles2.get(j).color.equals( Color.PINK )
                            && !circles2.get(j).color.equals( Color.GREEN ) && !circles2.get(j).color.equals( Color.WHITE )  )
                    {
                        changed.add(circles.get(i));
                    }
                    Nt++;
                    N += calculateN( circles.get(i).rotation , xdif , ydif );
                }

            }

            circle = circles.get(i);
            circle.rotation +=  alpha + beta * Math.signum( N ) * Nt;            //Update angle
            //circle.rotation %= 2 * Math.PI;                 //Mod the angle to ensure 0 < A < 360
            circle.x += Math.cos( circle.rotation ) * vel;  //Update x
            circle.y += Math.sin( circle.rotation ) * vel;  //Update y
            circle.color = Color.red;
            Nt = 0;//Set color
            N = 0;                                          //Reset N


        }

        for (PyscCircle o: changed) {
            circles2.add(o);
            circles.remove(o);
        }

        changed.clear();

        return circles;
    }

    public ArrayList<PyscCircle> Update3( ArrayList<PyscCircle> circles , ArrayList<PyscCircle> circles2 , double deltaTime ){
        ArrayList<PyscCircle> changed = new ArrayList<PyscCircle>();
        PyscCircle circle;
        updcircles = circles;



        int N = 0;
        int Nt = 0;
        int Nt5 = 0;

        for( int i = 0 ; i < circles.size(); i++ ){
            for( int j = 0 ; j < circles.size(); j++ ){
                if( i != j ){

                    double xdif = circles.get(j).x - circles.get(i).x;
                    double ydif = circles.get(j).y - circles.get(i).y;
                    double d = Math.sqrt( xdif * xdif + ydif * ydif );

                    if( d <= range ){
                        if( d <= range * 0.3 )
                            Nt5++;
                        Nt++;
                        N += calculateN( circles.get(i).rotation , xdif , ydif );
                    }
                }


            }

            circle = circles.get(i);

            if( circle.color.equals(Color.white) && !changed.contains(circle) ){
                changed.add(circle);
            }

            circle.rotation +=  alpha + beta * Math.signum( N ) * Nt;            //Update angle
            //circle.rotation %= 2 * Math.PI;                 //Mod the angle to ensure 0 < A < 360
            circle.x += Math.cos( circle.rotation ) * vel;  //Update x
            circle.y += Math.sin( circle.rotation ) * vel;  //Update y
            circle.color = setcolor( Nt , Nt5 );                             //Set color
            N = 0;                                          //Reset N
            Nt = 0;
            Nt5 = 0;

        }

        for (PyscCircle o: changed) {
            o.color = Color.red;
            circles2.add(o);
            circles.remove(o);
        }

        changed.clear();

        return circles;
    }

    public ArrayList<PyscCircle> UpdateC( ArrayList<PyscCircle> circles , double deltaTime ){

        //ArrayList<ArrayList<ArrayList<PyscCircle>>> chunks = new ArrayList<ArrayList<ArrayList<PyscCircle>>>();


        PyscCircle circle;
        updcircles = circles;

        for( int i = 0 ; i < circles.size(); i++ ){
            chunks.get((int)(circles.get(i).x / 50) + 50).get((int)(circles.get(i).y / 50) + 50).add(circles.get(i)); //[(int)(circles.get(i).x / 50) + 50][(int)(circles.get(i).y / 50) + 50].add(circles.get(i));
        }
        int N = 0;
        int Nt = 0;
        int Nt5 = 0;

        for( int ci = 1 ; ci < chunks.size() - 1 ; ci ++ ) {
            for (int cj = 1; cj < chunks.get(ci).size() - 1; cj++){
                for (PyscCircle o1 : chunks.get(ci).get(cj)) {
                    for (PyscCircle o2 : chunks.get(ci).get(cj)) {

                        double xdif = o2.x - o1.x;
                        double ydif = o2.y - o1.y;
                        double d = Math.sqrt(xdif * xdif + ydif * ydif);

                        if (d <= range) {
                            if (d <= range * 0.4)
                                Nt5++;
                            Nt++;
                            N += calculateN(o1.rotation, xdif, ydif);
                        }
                    }

                    circle = updcircles.get(updcircles.indexOf(o1));
                    circle.rotation += alpha + beta * Math.signum(N) * Nt;            //Update angle
                    //circle.rotation %= 2 * Math.PI;                 //Mod the angle to ensure 0 < A < 360
                    circle.x += Math.cos(circle.rotation) * vel;  //Update x
                    circle.y += Math.sin(circle.rotation) * vel;  //Update y
                    circle.color = setcolor(Nt, Nt5);                             //Set color
                    N = 0;                                          //Reset N
                    Nt = 0;
                    Nt5 = 0;
                }
            }
        }

        return updcircles;
    }


    private int calculateN( double rotA , double xdif , double ydif){
        return (int) Math.signum( ( xdif * Math.sin(-rotA) + ydif * Math.cos(-rotA) ) );
    }

    private Color setcolor( int n , int m ){

        Color color = Color.GREEN;


        if ( m > 10 && m <= 17 )
            color = Color.PINK;
        else if ( m > 17 && m <= 28 )
            color = Color.getColor("#f29f72"); //Brown
        else if( n > 7 && n <= 17)
            color = Color.MAGENTA;
        else if( n > 17 && n <= 28 )
            color = Color.BLUE;
        else if( n > 28 )
            color = Color.YELLOW;
        else if( m > 70 || n > 70 )
            color = color.WHITE;






        return color;

    }

}
