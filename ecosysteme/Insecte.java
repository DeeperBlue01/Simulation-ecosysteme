import java.awt.*;

public abstract class Insecte extends Animal {
    public Insecte(Color c,int nombre) {
        super(c,nombre);
    }
    public abstract void positionGrille(int cellX,int cellY,Graphics g, Espece espece);
}