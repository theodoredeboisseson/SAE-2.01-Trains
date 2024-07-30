package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class CentreDeRenseignements extends Carte {
    public CentreDeRenseignements() {
        super("Centre de renseignements", TypeCarte.Action,1,4);
    }

    @Override
    public void jouer(Joueur joueur) {
        genererValeurPour(joueur);

        ListeDeCartes devoilees = new ListeDeCartes();
        devoilees.addAll(joueur.piocher(4));
        joueur.log("Dévoile les cartes "+devoilees);

        if(devoilees.isEmpty()){
            joueur.log("Il n'y a pas de carte dans le deck, cette carte n'auras aucun effet");
        }

        List<Bouton> boutons = new ArrayList<>();
        for(String s: devoilees.getNoms())
            boutons.add(new Bouton(s));

        String input = joueur.choisir(
                "Veuillez choisir une carte à prendre parmi les "+devoilees.size()+" dévoilées",
                null,
                boutons,
                true
        );

        if(!input.isEmpty()){
            joueur.getMain().add(devoilees.retirer(input));
            joueur.log("Garde la carte "+input);
            boutons.remove(new Bouton(input));
        }


        while(!devoilees.isEmpty()){
            input = joueur.choisir(
                    "Remettez les cartes restantes sur le deck, dans l'ordre que vous voulez ("+devoilees.size()+" restant(es))",
                    null,
                    boutons,
                    false
            );

            joueur.getPioche().add(0,devoilees.retirer(input));
            boutons.remove(new Bouton(input));
        }

        joueur.log("A remis le reste sur la pile");
    }
}
