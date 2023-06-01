import java.awt.*;

public abstract class Arbre extends Vegetal {
    public Arbre(Color c,int nombre) {
        super(c,nombre);
    }

    public abstract void positionGrille(int cellX,int cellY,Graphics g, Espece espece);

}