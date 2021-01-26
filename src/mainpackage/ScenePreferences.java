package mainpackage;

public class ScenePreferences {

    private String ID;

    private static int fps = 60;
    private static int mode;
    private static int physCirclecount;
    private static double[] PPSEnginePreferences = { 179 , 17 , 50 , 6.7 };
    private static double[] GravityEnginePreferences = { 0.005 };

    public static void setGravityEnginePreferences( double[] values ){ GravityEnginePreferences = values; }
    public static double[] getGravityEnginePreferences(){ return GravityEnginePreferences;}

    public static void setPPSEnginePreferences( double[] values ){ PPSEnginePreferences = values; }
    public static double[] getPPSEnginePreferences(){ return PPSEnginePreferences;}

    public String getID(){ return this.ID; }

    public static void setPhysCirclecount( int value ){
        physCirclecount = value;
    }
    public static int getCircleCount() {
        return physCirclecount;
    }

    public static void setmode( int value ){ mode = value; }
    public static int getmode() { return mode; }

    public static void setfps( int value ){ fps = value; }
    public static int getfps() { return fps; }
}
