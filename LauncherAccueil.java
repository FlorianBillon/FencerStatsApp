import javax.swing.JApplet;

public class LauncherAccueil{
    
    private static Acceuil aAcceuil;
    
    public static void main(){
        aAcceuil  = new Acceuil ();

        try
        {
            aAcceuil.createGUI();
        }
        catch (java.net.URISyntaxException urise)
        {
            urise.printStackTrace();
        }
    }
    
}


