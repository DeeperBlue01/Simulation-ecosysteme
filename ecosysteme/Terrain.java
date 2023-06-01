import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class Terrain extends JPanel {
    private final int nbCasesL;
    private final int nbCasesH;
    private final int nbPixelCoteCase;
    private final Zone[][] zones;

    public Zone getZones(int i, int j) {
        try {
            return zones[i][j];
        } catch (Exception e) {
            throw new RuntimeException("Zone problème erreur de ségmentation");
        }
    }

    public int getNbCasesL() {
        return nbCasesL;
    }

    public int getNbCasesH() {
        return nbCasesH;
    }

    /**
     * Constructeur.
     *
     * @param nbCasesL        La largeur (en nombre de cases) de la grille.
     * @param nbCasesH        La hauteur (en nombre de cases) de la grille.
     * @param nbPixelCoteCase Nb de Pixel d'une case de la grille
     **/
    Terrain(int nbCasesL, int nbCasesH, int nbPixelCoteCase) {
        int i, j;
        this.nbCasesL = nbCasesL;
        this.nbCasesH = nbCasesH;
        this.nbPixelCoteCase = nbPixelCoteCase;

        JFrame window = new JFrame();
        window.setSize(nbCasesL * nbPixelCoteCase + 50, nbCasesH * nbPixelCoteCase + 50);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(this);
        window.setVisible(true);

        this.zones = new Zone[nbCasesL][nbCasesH];
        for (i = 0; i < nbCasesL; i++)
            for (j = 0; j < nbCasesH; j++) {
                Random random = new Random();
                float nombre = random.nextFloat() * (1000);
                //Cette partie du code nous permet de spécifier la température et de lui donner une fourchette
                zones[i][j] = new Zone(30, nombre);
            }
    }

    public void redessine() {
        repaint();
    }

    public void colorieFond(int i, int j, TypeCase typeCase) {
        switch (typeCase) {
            case foret -> {
                zones[i][j].setCouleur(Color.GREEN);
                zones[i][j].setTypeCase(TypeCase.foret);
            }
            case plaine -> {
                zones[i][j].setCouleur(Color.CYAN);
                zones[i][j].setTypeCase(TypeCase.plaine);
            }
            case desert -> {
                zones[i][j].setCouleur(Color.ORANGE);
                zones[i][j].setTypeCase(TypeCase.desert);
            }
            default -> throw new RuntimeException("Zone non défini !!!!");
        }
    }

    public void addEspece(int i, int j) {
        Random rand = new Random();
        Arrays.stream(EspeceDisponible.values())
                .forEach(espece -> {
                    if (Espece.getProbabilite(espece) >= rand.nextDouble()) {
                        zones[i][j].addEspece(espece, rand.nextInt((6 - 1) + 1) + 1);
                    }
                });
    }

    public void besoinEspece(int i, int j) {
        double probaManger = 0.9; // probabilité de 90% pour manger
        double probaBoire = 0.8; // probabilité de 80% pour boire
        double probaDeplacer = 0.5; // probabilité de 50% pour se déplacer
        double probaReproduction = 0.3; // probabilité de 30% pour se reproduire

        this.zones[i][j].lEspeces.forEach(e -> {
            if (Math.random() < probaManger) {
                e.manger(this.zones[i][j]);
            }
            if (Math.random() < probaBoire) {
                e.boire(this.zones[i][j]);
            }
            if (Math.random() < probaDeplacer) {
                e.deplacer(this, this.zones[i][j], i, j);
            }
            if (Math.random() < probaReproduction) {
                e.reproduction();
            }
        });
        zones[i][j].cleanEspece();
        zones[i][j].basculeZone();
        zones[i][j].cleanDesert();

    }


    @Override
    //Fonction d'affichage de la grille appelée par repaint
    protected void paintComponent(Graphics g) {
        //Colorie les cases de casesAColorier
        super.paintComponent(g);
        int i, j;
        for (i = 0; i < nbCasesL; i++)
            for (j = 0; j < nbCasesH; j++) {
                int cellX = 10 + (i * nbPixelCoteCase);
                int cellY = 10 + (j * nbPixelCoteCase);
                g.setColor(zones[i][j].getCouleur());
                g.fillRect(cellX, cellY, nbPixelCoteCase, nbPixelCoteCase);

                // Place des Espèces

                for (Espece espece : zones[i][j].lEspeces) {
                    Random random=new Random();
                    g.setColor(espece.getCouleur());
                    g.fillOval(cellX + random.nextInt(100), cellY+random.nextInt(100) , espece.getNombre()*5, espece.getNombre()*5);
                }

            }


        // Redessine la grille
        g.setColor(Color.BLACK);
        g.drawRect(10, 10, nbCasesL * nbPixelCoteCase, nbCasesH * nbPixelCoteCase);

        for (i = 10; i <= nbCasesL * nbPixelCoteCase; i += nbPixelCoteCase) {
            g.drawLine(i, 10, i, nbCasesH * nbPixelCoteCase + 10);
        }

        for (i = 10; i <= nbCasesH * nbPixelCoteCase; i += nbPixelCoteCase) {
            g.drawLine(10, i, nbCasesL * nbPixelCoteCase + 10, i);
        }
    }


}