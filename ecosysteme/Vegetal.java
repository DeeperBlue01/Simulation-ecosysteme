import java.awt.*;

public abstract class Vegetal implements Espece {
    protected int nombre = 0;
    protected Color c;

    Vegetal(Color c,int nombre) {
        setNombre(nombre);
        this.c = c;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        if (nombre<0){
            throw new RuntimeException("Vous avez mis comme nombre "+nombre+" or il faudra mettre un nombre strictement positif");
        }
        this.nombre = nombre;
    }

    public Color getCouleur() {
        return c;
    }

    public void setCouleur(Color c) {
        this.c = c;
    }
    @Override
    public void manger(Zone zone) {
    }

    @Override
    public void boire(Zone zone) {
    }

    @Override
    public void reproduction() {
    }

    @Override
    public void deplacer(Terrain terrain, Zone zone, int i, int j) {

    }
    public abstract void positionGrille(int cellX,int cellY,Graphics g, Espece espece);
}
