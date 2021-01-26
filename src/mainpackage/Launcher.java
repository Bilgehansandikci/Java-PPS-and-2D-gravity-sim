package mainpackage;

import javax.swing.*;
import java.util.Scanner;

public class Launcher {

    Display display;
    static Scanner scan = new Scanner(System.in);

    static boolean started = false;

    public static void main(String[] args) {
        mainMenu();

        Display display = new Display(1280 , 720 , "Test" );
        Game game = new Game( display );
    }

    public static void mainMenu(){

        while( !started ) {

            //Menu
            System.out.print("1 - Start\n2 - Preferences\n> ");
            int s1 = scan.nextInt();

            if (s1 == 1) {
                launchMenu();
            }
            else if (s1 == 2) {
                preferencesMenu();
            }
        }
    }

    private static void preferencesMenu(){

        System.out.print("::PREFERENCES::\n1 - fps\n2 - Gravity Engine Preferences\n3 - PPS Engine Preferences\n> ");
        int s21 = scan.nextInt();

        if( s21 == 1 ){
            System.out.print("Fps\n10 is recommended for PPS, 30+ is recommended for gravity\n> ");
            int s211 = scan.nextInt();
            ScenePreferences.setfps( s211 );
        }
        else if( s21 == 2 ){
            System.out.print("Enter Gravity Engine values in the form -Gravitational constant- without \"-\"\ndefault: 0.005\n>");
            double[] s212 = { scan.nextDouble() };
            ScenePreferences.setGravityEnginePreferences( s212 );
        }
        else if( s21 == 3 ){
            System.out.print("Enter PPS Engine values in the form -Alpha(degree) Beta(degree) Range Velocity- without \"-\"\ndefault: 179 17 50 6,7\n> ");
            double[] s213 = { scan.nextDouble() , scan.nextDouble() , scan.nextDouble() , scan.nextDouble() };
            ScenePreferences.setPPSEnginePreferences( s213 );
        }


    }

    private static void launchMenu(){
        System.out.print("::MODES::\n1 - Gravity\n2 - PPS\n3 - Return\n> ");
        int s11 = scan.nextInt();

        if (s11 == 1) {
            System.out.print("Enter number of balls> ");
            int pcc = scan.nextInt();
            ScenePreferences.setPhysCirclecount(pcc);
            ScenePreferences.setmode(1);
            started = true;
        }
        else if (s11 == 2) {
            System.out.print("Enter number of balls> ");
            int pcc = scan.nextInt();
            ScenePreferences.setPhysCirclecount(pcc);
            ScenePreferences.setmode(2);
            started = true;
        }
        else if( s11 == 3 ){
            mainMenu();
        }
    }
}
