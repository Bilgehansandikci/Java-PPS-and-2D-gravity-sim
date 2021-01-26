package mainpackage.Physics;

import mainpackage.objects.PyscCircle;

import java.awt.*;
import java.util.ArrayList;

public class Gravityengine extends PhysicsEngine{

    //Helper Objects
    private ArrayList<PyscCircle> merged = new ArrayList<PyscCircle>();
    private ArrayList<PyscCircle> collided = new ArrayList<PyscCircle>();

    //
    private double G;

    public Gravityengine(double G){
        this.G = G;
    }

    public ArrayList<PyscCircle> Update( ArrayList<PyscCircle> circles , double deltaTime ){

        double xdif;
        double ydif;
        double r;
        double f;
        PyscCircle o1,o2,o3;

        //Velocity Update
        for( int i = 0 ; i < circles.size() -1 ; i++ ){
            for( int j = i + 1 ; j < circles.size() ; j++ ){
                o1 = circles.get(i);
                o2 = circles.get(j);

                if( o1 != o2 ){

                    xdif = o2.x - o1.x;
                    ydif = o2.y - o1.y;
                    r = Math.sqrt( ydif * ydif + xdif * xdif );

                    f = G * o1.mass * o2.mass / r * r;

                    o1.xvel += xdif * f / (r * o1.mass) * deltaTime;
                    o1.yvel += ydif * f / (r * o1.mass) * deltaTime;
                    o2.xvel += -xdif * f / (r * o2.mass) * deltaTime;
                    o2.yvel += -ydif * f / (r * o2.mass) * deltaTime;


                }
            }
        }

        //Collision Update
        boolean iscollided = false;

        for( int i = 0 ; i < circles.size() - 1 ; i++ ){
            for( int j = i + 1 ; j < circles.size() ; j++ ){

                o1 = circles.get(i);
                o2 = circles.get(j);

                if( o1 != o2 ){

                    xdif = o2.x - o1.x;
                    ydif = o2.y - o1.y;
                    r = Math.sqrt( ydif * ydif + xdif * xdif );

                    if( r < (o1.size + o2.size)/2 && !collided.contains(o2) && !collided.contains(o1)){ //Collided

                        //iscollided = true;


                            o3 = new PyscCircle(0,0,0,0,0);

                            o3.x = (o1.x * o1.mass + o2.x * o2.mass) / ( o1.mass + o2.mass );
                            o3.y = (o1.y * o1.mass + o2.y * o2.mass) / ( o1.mass + o2.mass );
                            o3.xvel = (o1.xvel * o1.mass + o2.xvel * o2.mass) / ( o1.mass + o2.mass );
                            o3.yvel = (o1.yvel * o1.mass + o2.yvel * o2.mass) / ( o1.mass + o2.mass );

                            o3.size = Math.sqrt( o1.mass + o2.mass);
                            o3.mass = o3.size * o3.size;

                            merged.add(o3);
                            collided.add(o1);
                            collided.add(o2);


                    }

                }
            }
            iscollided = false;
        }



        for( PyscCircle o : collided ){
            circles.remove(o);
        }
        collided.clear();

        for( PyscCircle o : merged ){
            circles.add(o);
        }
        merged.clear();

        for(PyscCircle o : circles){
            o.x += o.xvel * deltaTime;
            o.y += o.yvel * deltaTime;
        }

        /*COPY
        for( int i = 0 ; i < circles.size() - 1 ; i++ ){
            for( int j = i + 1 ; j < circles.size() ; j++ ){
                o1 = circles.get(i);
                o2 = circles.get(j);
                if( o1 != o2 ){

                    xdif = o2.x - o1.x;
                    ydif = o2.y - o1.y;
                    r = Math.sqrt( ydif * ydif + xdif * xdif );

                    if( r < (o1.size + o2.size)/2 && !iscollided){

                        iscollided = true;

                        o3 = new PyscCircle(0,0,0,0,0);

                        o3.x = (o1.x * o1.mass + o2.x * o2.mass) / ( o1.mass + o2.mass );
                        o3.y = (o1.y * o1.mass + o2.y * o2.mass) / ( o1.mass + o2.mass );
                        o3.xvel = (o1.xvel * o1.mass + o2.xvel * o2.mass) / ( o1.mass + o2.mass );
                        o3.yvel = (o1.yvel * o1.mass + o2.yvel * o2.mass) / ( o1.mass + o2.mass );

                        o3.size = Math.sqrt( o1.mass + o2.mass);
                        o3.mass = o1.size * o1.size;

                        if( !collided.contains(o2) && !collided.contains(o1) )
                        {
                            merged.add(o3);
                            collided.add(o1);
                            collided.add(o2);
                        }




                    }
                    else{

                        f = G * o1.mass * o2.mass / r * r;

                        o1.xvel += xdif * f / (r * o1.mass);
                        o1.yvel += ydif * f / (r * o1.mass);
                        o2.xvel += -xdif * f / (r * o2.mass);
                        o2.yvel += -ydif * f / (r * o2.mass);
                    }

                }
            }
            iscollided = false;
        }



        for( PyscCircle o : collided ){
            circles.remove(o);
        }

        for( PyscCircle o : merged ){
            circles.add(o);

        }
        merged.clear();

        for(PyscCircle o : circles){
            o.x += o.xvel * deltaTime;
            o.y += o.yvel * deltaTime;
        }
        COPY
        */

        return circles;
    }

}
