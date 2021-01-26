package mainpackage.objects;

import mainpackage.Physics.PhysicsEngine;

import java.awt.*;

public class PyscCircle {

    public PhysicsEngine engine;
    public double size , mass;
    public double x, y;
    public double xvel, yvel;
    public double rotation;
    public Color color = Color.WHITE;
    public boolean isStatic = false;

    //Some Engine
    public PyscCircle( PhysicsEngine engine ,double size , double x , double y , double xvel , double yvel ){
        this.engine = engine;
        this.size = size;
        this.x = x;
        this.y = y;
        this.xvel = xvel;
        this.yvel = yvel;
        this.mass = size * size ;

    }

    //GravityEngine
    public PyscCircle(double size , double x , double y , double xvel , double yvel ){
        this.size = size;
        this.x = x;
        this.y = y;
        this.xvel = xvel;
        this.yvel = yvel;
        this.mass = size * size ;
    }

    //PPSEngine
    public PyscCircle( double size , double x , double y , double rotation ){
        this.size = size;
        this.x = x;
        this.y = y;
        this.rotation = rotation / 180.0 * Math.PI;
        color = isStatic ? color.gray : color.gray;
    }

}
