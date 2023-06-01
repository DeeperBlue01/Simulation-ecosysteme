import java.awt.*;

public abstract class Mamifere extends Animal {
    public Mamifere(Color c,int nombre) {
        super(c,nombre);
    }
    public abstract void positionGrille(int cellX,int cellY,Graphics g, Espece espece);

}