import java.awt.*;
import java.util.Arrays;

public interface Espece {
    Color getCouleur();

    static double getProbabilite(EspeceDisponible especeDisponible) {
        return switch (especeDisponible) {
            case capucine -> 0.7;
            case arnica -> 0.8;
            case sapin -> 0.65;
            case chene -> 0.8;
            case pigeon -> 0.7;
            case perroquet -> 0.8;
            case lion -> 0.8;
            case tigre -> 0.6;
            case zebre -> 0.8;
            case girafe -> 0.6;
            case fourmi -> 0.4;
            case mouche -> 0.5;
            case guepe -> 0;
            default -> throw new IllegalArgumentException("Espece inconnue : " + especeDisponible);
        };
    }
    static EspeceDisponible getEspeceDisponible(Espece espece){
        return Arrays.stream(EspeceDisponible.values())
                .filter(e -> e.name().equalsIgnoreCase(espece.getClass().getSimpleName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Espece inconnue : " + espece));
    }

    int getNombre();
    void setNombre(int nombre);
    //Cette fonction n'est plus utilisée dans le programme, elle est remplacée par une nouvelle version plus pratique
    void positionGrille(int cellX, int cellY, Graphics g, Espece espece);

    void manger(Zone zone);
    void boire(Zone zone);
    void deplacer(Terrain terrain,Zone zone,int i, int j);
    void reproduction();


}