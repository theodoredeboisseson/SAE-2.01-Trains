package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.List;

public class AtelierDeMaintenance extends Carte {
    public AtelierDeMaintenance() {
        super("Atelier de maintenance", TypeCarte.Action,0,5);
    }

    @Override
    public void jouer(Joueur joueur) {
//        Carte carteTrain = devoilerCarteTrain(joueur);
//        if(carteTrain != null) joueur.ajouterMain(joueur.getJeu().prendreDansLaReserve(carteTrain.getNom()));
        List<String> choix = joueur.getMain().selection(TypeCarte.Train,-1,-1).getNoms();

        if(choix.isEmpty()){
            joueur.log("Vous n'avez pas de carte Train dans votre main, cette carte n'auras aucun effet");
            return;
        }

        String input = joueur.choisir(
              "Choisissez une carte Train à dévoiler de votre main. Vous recevrez un double depuis la réserve, si il y en a",
                choix,
                null,
                false
        );


        joueur.getJeu().log("Le joueur "+joueur.getNom()+" dévoile la carte "+input+" de sa main");

        Carte doublon = joueur.getJeu().prendreDansLaReserve(input);
         if(doublon == null)
             joueur.log("Il n'y a pas de carte "+input+" dans la réserve, le joueur a dévoilé une carte pour rien (la honte)");
         else {
             joueur.getCartesRecues().add(doublon);
             joueur.log("Le joueur reçoit un double de la carte dévoilée depuis la réserve");
         }
    }


    /* ex code de Emrys
    public Carte devoilerCarteTrain(Joueur joueur){
        List<String> choixPossibles = new ArrayList<>();
        for (Carte carte : joueur.getMain())
            if (carte.getType().equals(TypeCarte.Train))
                choixPossibles.add(carte.getNom());
        if (choixPossibles.isEmpty()) {
            System.out.println("Il n'y a pas de carte \u001B[36mTrain\u001B[0m dans votre main.");
            return null;
        }
        String choix = joueur.choisir("Dévoilez une carte \u001B[36mTrain\u001B[0m", choixPossibles, null, false);
        for (Carte carte : joueur.getMain())
            if (carte.getNom().equals(choix))
                return carte;
        return null;
    }*/


}
