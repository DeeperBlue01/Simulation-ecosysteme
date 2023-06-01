import java.awt.*;

public abstract class Herbivore extends Mamifere {
    public Herbivore(Color c,int nombre) {
        super(c,nombre);
    }

    public abstract void positionGrille(int cellX,int cellY,Graphics g, Espece espece);

}