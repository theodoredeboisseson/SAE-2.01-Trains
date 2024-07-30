package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class CentreDeControle extends Carte {
    public CentreDeControle() {
        super("Centre de contrôle", TypeCarte.Action,0,3);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.getMain().addAll(joueur.piocher(1));

        List<Bouton> boutons = new ArrayList<>();
        for(String s: joueur.getJeu().getListeNomsCartes())
            boutons.add(new Bouton(s));

        String input = joueur.choisir(
                "Nommez une carte",
                null,
                boutons,
                false
        );

        Carte devoilee = joueur.piocher();
        joueur.log("Dévoile la carte "+devoilee.getNom());

        if(devoilee.getNom().equals(input)){
            joueur.getMain().add(devoilee);
            joueur.log("La carte dévoilée est bien celle nommée : prend la carte dans sa main");
        } else {
            joueur.getPioche().add(0,devoilee);
            joueur.log("La carte dévoilée n'est pas celle nommée : la carte retourne sur le deck");
        }
    }
}
