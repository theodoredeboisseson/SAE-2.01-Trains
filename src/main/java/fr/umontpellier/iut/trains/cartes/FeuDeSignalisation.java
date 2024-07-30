package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.Arrays;
import java.util.List;

public class FeuDeSignalisation extends Carte {

    public FeuDeSignalisation() {
        super("Feu de signalisation", TypeCarte.Action,0,2);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.log("Pioche 1 carte");
        joueur.getMain().addAll(joueur.piocher(1));

        Carte c = joueur.piocher();
        joueur.log("Dévoile la carte "+c);

        List<Bouton> boutons = Arrays.asList(
                new Bouton("Remettre sur le dessus du deck","non"),
                new Bouton("Défausser","oui")
        );

        String input = joueur.choisir(
                "Choisissez une action",
                null,
                boutons,
                false
        );

        if(input.equals("non")){
            joueur.getPioche().add(0,c);
            joueur.log("Replace la carte sur le deck");
        } else {
            joueur.getDefausse().add(c);
            joueur.log("Défausse la carte");
        }
    }
}
