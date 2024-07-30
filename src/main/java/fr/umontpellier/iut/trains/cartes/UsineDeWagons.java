package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Jeu;
import fr.umontpellier.iut.trains.Joueur;


import java.util.ArrayList;
import java.util.List;

public class UsineDeWagons extends Carte {
    public UsineDeWagons() {
        super("Usine de wagons", TypeCarte.Action, 0, 5);
    }

    /* Input attendu : Le nom d'une carte TRAIN que le joueur a en main, puis "ACHAT:" suivi du nom d'une carte TRAIN disponible
     * en réserve dont le coût d'achat est inférieur ou égal au coût d'achat de la première carte choisie + 3.
     */
    @Override
    public void jouer(Joueur joueur) {
        //Fais la liste des cartes trains de la main
        Jeu jeu = joueur.getJeu();
        ListeDeCartes main = joueur.getMain();

        // "Ecartez une carte train de votre main
        List<String> choixPossibles = main.selection(TypeCarte.Train, -1, -1).getNoms();

        if (choixPossibles.isEmpty()){
            joueur.log("Vous ne possédez aucune carte Train dans votre main, cette carte n'auras aucun effet.");
            return;
        }

        String input = joueur.choisir(
                "Veuillez choisir une carte Train à écarter de votre main",
                choixPossibles,
                null,
                false
        );

        Carte carteEcartee = main.retirer(input);
        joueur.getJeu().getCartesEcartees().add(carteEcartee);
        joueur.log("écarte "+input);

        // "Recevez une carte train coutant jusqu'à +3"
        ListeDeCartes cartesReserve = jeu.getReserveCards();
        choixPossibles = cartesReserve.selection(TypeCarte.Train, -1, carteEcartee.getCout() + 3).getNoms().stream().distinct().toList();
        List<Bouton> boutons = new ArrayList<>();
        for(String s: choixPossibles)
            boutons.add(new Bouton(s,"ACHAT:"+s));

        input = joueur.choisir(
                "Choisissez une carte Train avec au plus +3 de cout par rapport celle écartée",
                null,
                boutons,
                false
        );

        input = input.split(":")[1];
        main.add(jeu.prendreDansLaReserve(input));
    }
}
