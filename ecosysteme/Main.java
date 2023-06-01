import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int nbCasesL = 5, nbCasesH = 6;
        // On crée un terrain 
        Terrain terrain = new Terrain(nbCasesL, nbCasesH, 160);

        int i, j;

        for (i = 0; i < nbCasesL; i++)
            for (j = 0; j < nbCasesH; j++) {
                if (i < j) terrain.colorieFond(i, j, TypeCase.foret);
                else terrain.colorieFond(i, j, TypeCase.plaine);
            }

        terrain.redessine();

        //Puis, pause de 2s
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        for (i = 0; i < nbCasesL; i++)
            for (j = 0; j < nbCasesH; j++) {

                // Placer les différentes espèces au hasard selon des probabilités

                terrain.addEspece(i, j);

                //Puis, pause de 2s
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                terrain.redessine();
            }
        for (int cycle=0;cycle<4;cycle++)
            cycle(nbCasesL, nbCasesH, terrain);


        System.out.println("Voulez vous faire une simulation ? (Répondre par oui ou non)");
        Scanner sc=new Scanner(System.in);

        String choix=sc.nextLine();
        if (choix.toLowerCase().equals("oui")){
            boolean choice=true;
            while (choice){
                System.out.println("Choisir une option :\n1)Simulez levée de température\n2)Simulez invasion de guèpe asiatique\n3)Simulez un cycle\n*)Quittez");
                choix=sc.nextLine();
                switch (choix){
                    case "1"->{
                        try {
                        System.out.println("Choisir la température (Pour passer au desert il faut minimum une température de 46):");
                        choix=sc.nextLine();
                        float temp=Integer.parseInt(choix);
                        System.out.println("Choisir les coordonnées de la zone (Colonnes en premier, Lignes en deuxième) : Le nombre de colonnes est "+ nbCasesL+" le nombre de lignes est : "+nbCasesH);
                        choix=sc.nextLine();
                        int temp1=Integer.parseInt(choix);
                        choix=sc.nextLine();
                        int temp2=Integer.parseInt(choix);
                        if (temp1-1>nbCasesL || temp2-1>nbCasesH){
                            System.out.println("Vous n'avez pas respecté la plage");
                            break;
                        }
                        terrain.getZones(temp1-1,temp2-1).setTemperature(temp);
                        terrain.getZones(temp1-1,temp2-1).basculeZone();
                        terrain.redessine();

                        }catch (Exception e){
                            System.out.println("Vous n'avez pas saisi un chiffre");
                        }
                    }
                    case "2"->{
                        System.out.println("Choisir les coordonnées de la zone attaquées (Colonnes en premier, Lignes en deuxième) : Le nombre de colonnes est "+ nbCasesL+" le nombre de lignes est : "+nbCasesH);
                        choix=sc.nextLine();
                        int temp1=Integer.parseInt(choix);
                        choix=sc.nextLine();
                        int temp2=Integer.parseInt(choix);
                        if (temp1-1>nbCasesL || temp2-1>nbCasesH){
                            System.out.println("Vous n'avez pas respecté la plage");
                            break;
                        }
                        Espece espece=EspeceFactory.getInstance(EspeceDisponible.guepe,50);
                        terrain.getZones(temp1-1,temp2-1).lEspeces.add(espece);
                        cycle(nbCasesL, nbCasesH, terrain);
                        terrain.getZones(temp1-1,temp2-1).invasionGuepe();
                        terrain.redessine();
                    }
                    case "3"->{
                        cycle(nbCasesL, nbCasesH, terrain);
                    }
                    default -> {
                        choice=false;
                    }
                }
            }


        }
        System.out.println("Au revoir");


    }

    private static void cycle(int nbCasesL, int nbCasesH, Terrain terrain) {
        int i;
        int j;
        for (i = 0; i < nbCasesL; i++){
            for (j = 0; j < nbCasesH; j++) {

                terrain.besoinEspece(i, j);

                //Puis, pause de 2s
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                terrain.redessine();


            }
        }
    }
}
