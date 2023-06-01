import java.awt.*;

public abstract class Vivace extends Vegetal {
    public Vivace(Color c,int nombre) {
        super(c,nombre);
    }
    public abstract void positionGrille(int cellX,int cellY,Graphics g, Espece espece);

}