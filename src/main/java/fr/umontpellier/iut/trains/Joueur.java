package fr.umontpellier.iut.trains;

import java.util.*;

import fr.umontpellier.iut.trains.cartes.Carte;
import fr.umontpellier.iut.trains.cartes.FabriqueListeDeCartes;
import fr.umontpellier.iut.trains.cartes.ListeDeCartes;
import fr.umontpellier.iut.trains.cartes.TypeCarte;
import fr.umontpellier.iut.trains.plateau.*;
import static java.lang.Integer.parseInt;

public class Joueur {

    /**
     * Le jeu auquel le joueur est rattaché
     */
    private Jeu jeu;

    /**
     * Nom du joueur (pour les affichages console et UI)
     */
    private String nom;

    /**
     * Quantité d'argent que le joueur a (remis à zéro entre les tours)
     */
    private int argent;

    /**
     * Nombre de points rails dont le joueur dispose. Ces points sont obtenus en
     * jouant les cartes RAIL (vertes) et remis à zéro entre les tours
     */
    private int pointsRails;

    /**
     * Nombre de jetons rails disponibles (non placés sur le plateau)
     */
    private int nbJetonsRails;

    /**
     * Liste des cartes en main
     */
    private ListeDeCartes main;

    /**
     * Liste des cartes dans la pioche (le début de la liste correspond au haut de
     * la pile)
     */
    private ListeDeCartes pioche;

    /**
     * Liste de cartes dans la défausse
     */
    private ListeDeCartes defausse;

    /**
     * Liste des cartes en jeu (cartes jouées par le joueur pendant le tour)
     */
    private ListeDeCartes cartesEnJeu;

    /**
     * Liste des cartes reçues pendant le tour
     */
    private ListeDeCartes cartesRecues;

    /**
     * Couleur du joueur (utilisé par l'interface graphique)
     */
    private CouleurJoueur couleur;

    /**
     * Compteur de points victoire du joueur (gagnés avec les cartes Victoire)
     */
    private int marqueurScore;

    /**
     * La liste des noms des effets que le joueur accumule durant son tour
     */
    private List<Effet> listeEffets;


    public Joueur(Jeu jeu, String nom, CouleurJoueur couleur) {
        this.jeu = jeu;
        this.nom = nom;
        this.couleur = couleur;
        argent = 0;
        pointsRails = 0;
        nbJetonsRails = 20;
        main = new ListeDeCartes();
        defausse = new ListeDeCartes();
        pioche = new ListeDeCartes();
        cartesEnJeu = new ListeDeCartes();
        cartesRecues = new ListeDeCartes();
        marqueurScore = 0;
        listeEffets = new ArrayList<>();

        // créer 7 Train omnibus (non disponibles dans la réserve)
        pioche.addAll(FabriqueListeDeCartes.creerListeDeCartes("Train omnibus", 7));
        // prendre 2 Pose de rails de la réserve
        for (int i = 0; i < 2; i++) {
            pioche.add(jeu.prendreDansLaReserve("Pose de rails"));
        }
        // prendre 1 Gare de la réserve
        pioche.add(jeu.prendreDansLaReserve("Gare"));

        // mélanger la pioche
        pioche.melanger();
        // Piocher 5 cartes en main
        // Remarque : on peut aussi appeler piocherEnMain(5) si la méthode est écrite
        for (int i = 0; i < 5; i++) {
            main.add(pioche.remove(0));
        }

    }

    public Jeu getJeu() {
        return jeu;
    }

    public String getNom() {
        return nom;
    }

    public void incrementerArgent(int n){
        argent += n;
    }

    public void obtenirPointsRails(){
        pointsRails ++;
    }

    public ListeDeCartes getMain() {
        return main;
    }

    public ListeDeCartes getPioche() {
        return pioche;
    }

    public ListeDeCartes getDefausse() {
        return defausse;
    }

    public ListeDeCartes getCartesEnJeu() {
        return cartesEnJeu;
    }

    public ListeDeCartes getCartesRecues() {
        return cartesRecues;
    }

    public CouleurJoueur getCouleur() {
        return couleur;
    }

    public List<Effet> getListeEffets() {
        return listeEffets;
    }

    public int getNbJetonsRails() {
        return nbJetonsRails;
    }

    public void incrementerMarqueurScore(int marqueurScore){
        this.marqueurScore += marqueurScore;
    }

    public void ajouterEffet(Effet effet){
        if(!listeEffets.contains(effet))
            listeEffets.add(effet);
    }

    /**
     * Renvoie le score total du joueur
     * <p>
     * Le score total est la somme des points obtenus par les effets suivants :
     * <ul>
     * <li>points de rails (villes et lieux éloignés sur lesquels le joueur a posé
     * un rail)
     * <li>points des cartes possédées par le joueur (cartes VICTOIRE jaunes)
     * <li>score courant du joueur (points marqués en jouant des cartes pendant la
     * partie p.ex. Train de tourisme)
     * </ul>
     * 
     * @return le score total du joueur
     */
    public int getScoreTotal() {
        // À FAIRE : compter les points sur le plato
        // FAIT : Points sur les cartes victoires du joueur + marqueurScore
        // FAIT : Points sur les tuiles Villes et Eloignees
        int points = marqueurScore;

        points += main.getPointsVictoire() + defausse.getPointsVictoire() + pioche.getPointsVictoire();

        List<Tuile> tuiles = jeu.getTuiles().stream().filter(t -> t.hasRail(this)).toList();
        for (Tuile t : tuiles){
            if (t instanceof TuileVille tVille){
                if(tVille.getNbGares()>0) points += Math.pow(2, tVille.getNbGares());
            }
            if (t instanceof TuileEtoile tEtoile) points += tEtoile.getValeur();
        }

        return points;
    }

    /**
     * Retire et renvoie la première carte de la pioche.
     * <p>
     * Si la pioche est vide, la méthode commence par mélanger toute la défausse
     * dans la pioche.
     *
     * @return la carte piochée ou {@code null} si aucune carte disponible
     */
    public Carte piocher() {
        if (pioche.isEmpty()) {
            defausse.melanger();
            pioche.addAll(defausse);
            defausse.clear();
        }
        if (!pioche.isEmpty())
            return pioche.remove(0);


        return null;
    }

    /**
     * Retire et renvoie les {@code n} premières cartes de la pioche.
     * <p>
     * Si à un moment, il faut encore piocher des cartes et que la pioche est vide,
     * la défausse est mélangée et toutes ses cartes sont déplacées dans la pioche.
     * S'il n'y a plus de cartes à piocher la méthode s'interrompt et les cartes qui
     * ont pu être piochées sont renvoyées.
     * 
     * @param n nombre de cartes à piocher
     * @return une liste des cartes piochées (la liste peut contenir moins de n
     *         éléments si pas assez de cartes disponibles dans la pioche et la
     *         défausse)
     */
    public List<Carte> piocher(int n) {
        List<Carte> cartesPioche = new ListeDeCartes();
        for (int i = 0; i < n; i++){
            Carte c = piocher();
            if(c != null)
                cartesPioche.add(c);
            else{
                log("Plus de cartes à piocher !");
                break;
            }
        }
        return cartesPioche;
    }


    /**
     * Déplace dans la défausse et renvoie la carte ayant pour nom nomCarte
     */
    public void defausser(String nomCarte){
        Carte c = main.retirer(nomCarte);
        if(c != null){
            defausse.add(c);
            log("Défausse de sa main la carte "+nomCarte);
        }
    }

    public void obtenirFerraile(int quantity){
        if (listeEffets.contains(Effet.pasDeFerraille)) return;
        for(int i = 0; i < quantity; i++){
            Carte ferraille = jeu.prendreDansLaReserve("Ferraille");
            if(ferraille == null)
                break;
            cartesRecues.add(ferraille);
        }
    }


    /**
     * Met la carte dans la main ayant pour nom nomCarte sur sa pile correspondante dans la résèrve.
     */
    public void remettreSurLaPile(String nomCarte){
        Carte carte = main.retirer(nomCarte);

        ListeDeCartes pile = jeu.getReserve().get(nomCarte);
        pile.add(carte);
    }


    /**
     * Déplace dans la liste des cartes écartées du jeu et renvoie la carte ayant pour nom nomCarte
     */
    public Carte ecarter(String nomCarte){
        Carte c = cartesEnJeu.retirer(nomCarte);
        if(c != null)
            jeu.getCartesEcartees().add(c);
        return c;
    }


    /**
     * Joue un tour complet du joueur
     * <p>
     * Le tour du joueur se déroule en plusieurs étapes :
     * <ol>
     * <li>Initialisation
     * <p>
     * Dans ce jeu il n'y a rien de particulier à faire en début de tour à part un
     * éventuel affichage dans le log.
     * 
     * <li>Boucle principale
     * <p>
     * C'est le cœur de la fonction. Tant que le tour du joueur n'est pas terminé,
     * il faut préparer la liste de tous les choix valides que le joueur peut faire
     * (jouer des cartes, poser des rails, acheter des cartes, etc.), puis demander
     * au joueur de choisir une action (en appelant la méthode {@code choisir}).
     * <p>
     * Ensuite, en fonction du choix du joueur il faut exécuter l'action demandée et
     * recommencer la boucle si le tour n'est pas terminé.
     * <p>
     * Le tour se termine lorsque le joueur décide de passer (il choisit {@code ""})
     * ou lorsqu'il exécute une action qui termine automatiquement le tour (par
     * exemple s'il choisit de recycler toutes ses cartes Ferraille en début de
     * tour)
     * 
     * <li>Finalisation
     * <p>
     * Actions à exécuter à la fin du tour : réinitialiser les attributs
     * du joueur qui sont spécifiques au tour (argent, rails, etc.), défausser
     * toutes les
     * cartes, piocher 5 nouvelles cartes en main, etc.
     * </ol>
     */
    public void jouerTour() {
        // Initialisation
        jeu.log("<div class=\"tour\">Tour de " + toLog() + "</div>");

        boolean aucuneActionEffectue = true;
        boolean finTour = false;
        // Boucle principale
        while (!finTour) {
            // Préparation de la liste des choix possibles
            List<String> choixPossibles = new ArrayList<>();
            choixPossibles.add("Ferraille");
            choixPossibles.add("debug");

            for(Carte c : main)
                choixPossibles.add(c.getNom());

            for(String nomCarte : jeu.getReserve().keySet())
                if(!jeu.getReserve().get(nomCarte).isEmpty())
                    choixPossibles.add("ACHAT:"+nomCarte);

            for(int i = 0; i < getJeu().getTuiles().toArray().length; i++){
                choixPossibles.add("TUILE:"+i);
            }

            // Choix de l'action à réaliser
            String choix = choisir(String.format("Tour de %s", this.nom), choixPossibles, null, true);


            // Exécution de l'action demandée par le joueur
            if (choix.equals("Ferraille")) {
                if(aucuneActionEffectue){
                    while(main.contains("Ferraille"))
                        remettreSurLaPile("Ferraille");
                    log("Passe son tour et remet tout sa ferraille sur la pile");
                    return;
                } else
                    log("Vous avez déjà joué une fois, impossible de vider votre Ferraille");
            } else if (choix.equals("debug")) {
                debuggerCartes();
            } else if(choix.equals("ACHAT:Ferraille")){
                log("Vous ne pouvez pas acheter de la Ferraille (pourquoi tu veux faire ca ?)");

            } else if(choix.startsWith("ACHAT:")) {
                acheterCarte(choix);

            } else if (choix.startsWith("TUILE:")) {
                if(!(nbJetonsRails==0 || pointsRails==0))
                    placerRail(choix);
                else
                    log("Vous n'avez pas de points rails");

            } else if(!choix.isEmpty()) {
                jouerCarte(choix);

            } else {
                finTour = true;
            }

            aucuneActionEffectue = false;
        }
        // Finalisation
        listeEffets.clear();

        defausse.addAll(main);
        main.clear();
        defausse.addAll(cartesRecues);
        cartesRecues.clear();
        defausse.addAll(cartesEnJeu);
        cartesEnJeu.clear();

        main.addAll(piocher(5));

        argent = 0;
        pointsRails = 0;
    }

    public void debuggerCartes() {
        List<String> choixPossible = FabriqueListeDeCartes.getDicoCards().keySet().stream().toList();

        String input = choisir(
                "Nom d'une carte à débugger",
                choixPossible,
                null,
                true
        );

        Carte c = FabriqueListeDeCartes.nomToCarte(input);
        if(c != null)
            main.add(c);
    }


    public void acheterCarte(String choix){
        String nomCarte = choix.split(":")[1];
        Carte carte = jeu.prendreDansLaReserve(nomCarte);
        int cout = carte.getCout();

        if (argent < cout)
            log("Argent insuffisant pour acheter la carte " + nomCarte);
        else {
            log("Achète la carte " + nomCarte + " de cout" + cout);
            if (listeEffets.contains(Effet.cartesAchetees_auDeck)){
                List<Bouton> boutons = Arrays.asList(
                        new Bouton("Placer sur le dessus du deck","oui"),
                        new Bouton("Annuler","non")
                );
                String choix2 = choisir("Mettre cette carte sur le haut du deck ?", null, boutons, false);
                if (Objects.equals(choix2, "oui")) pioche.add(0, carte);
                else cartesRecues.add(carte);
            }
            else cartesRecues.add(carte);
            argent -= cout;
            if (carte.getType() == TypeCarte.Victoire) //seulement les cartes victoire ont un effet sur l'achat donc flemme de coder un truc pour toutes les cartes
                carte.jouer(this);
        }
    }

    public void placerRail(String choix){
        int numeroTuile = parseInt(choix.split(":")[1]);
        Tuile tuileCiblee = jeu.getTuile(numeroTuile);
        if(tuileEstJouable(tuileCiblee)) {
            int coutTotal = calculerSurcout(tuileCiblee);

            if (tuileCiblee instanceof TuileMer)
                log("Impossibilité de poser un rail sur la mer");
            else {
                if (argent >= coutTotal) {
                    tuileCiblee.ajouterRail(this);
                    argent -= coutTotal;
                    pointsRails--;
                    nbJetonsRails--;
                    obtenirFerraile(1);
                }
                else {
                    log("Pas assez d'argent (nécessite "+coutTotal+" argent)");
                }
            }

        } else
            log("Vous n'avez pas de rail voisin à cette tuile");
    }

    public boolean tuileEstJouable(Tuile tuileCiblee){
        return tuileCiblee.getVoisines().stream().anyMatch(t -> t.hasRail(this)) && !tuileCiblee.hasRail(this);
    }

    public int calculerSurcout(Tuile tuileCiblee){
        if (listeEffets.contains(Effet.aucunSurcout)) return 0;

        int surcoutTotal;

        if (!tuileCiblee.estVide() && listeEffets.contains(Effet.aucunSurcoutSurAdversaire))
            surcoutTotal = 0;
        else
            surcoutTotal = tuileCiblee.getRails().toArray().length;

        if (tuileCiblee instanceof TuileTerrain newTuileCiblee) {
            if (newTuileCiblee.getType()==TypeTerrain.FLEUVE && !listeEffets.contains(Effet.aucunSurcoutSurRiviere))
                surcoutTotal += 1;

            else if (newTuileCiblee.getType()==TypeTerrain.MONTAGNE && !listeEffets.contains(Effet.aucunSurcoutSurMontagne))
                surcoutTotal += 2;
        }

        if ((tuileCiblee instanceof TuileVille newTuileCiblee) && !(listeEffets.contains(Effet.aucunSurcoutSurVille)))
            surcoutTotal += 1 + newTuileCiblee.getNbGares();

        if (tuileCiblee instanceof TuileEtoile nvTuileCiblee)
            surcoutTotal += nvTuileCiblee.getValeur();

        return  surcoutTotal;
    }



    public void jouerCarte(String choix){
        Carte carte = main.retirer(choix);

        if (!(carte.getType()==TypeCarte.Victoire)) {
            cartesEnJeu.add(carte);
            log("Joue " + carte);
            carte.jouer(this);

            if (carte.getType()==TypeCarte.Rail && listeEffets.contains(Effet.gagne2argentCarteRailJouee)){
                argent += 2;
                log("Obtiens 2 d'argent pour le rail placé");
            }

        } else {
            main.add(carte);
            log("Impossible de jouer une carte Victoire");
        }
    }


    /**
     * Attend une entrée de la part du joueur (au clavier ou sur la websocket) et
     * renvoie le choix du joueur.
     * <p>
     * Cette méthode lit les entrées du jeu ({@code Jeu.lireligne()}) jusqu'à ce
     * qu'un choix valide (un élément de {@code choix} ou la valeur d'un élément de
     * {@code boutons} ou éventuellement la chaîne vide si l'utilisateur est
     * autorisé à passer) soit reçu.
     * Lorsqu'un choix valide est obtenu, il est renvoyé par la fonction.
     * <p>
     * Exemple d'utilisation pour demander à un joueur de répondre à une question
     * par "oui" ou "non" :
     * <p>
     * 
     * <pre>{@code
     * List<String> choix = Arrays.asList("oui", "non");
     * String input = choisir("Voulez-vous faire ceci ?", choix, null, false);
     * }</pre>
     * <p>
     * Si par contre on voulait proposer les réponses à l'aide de boutons, on
     * pourrait utiliser :
     * 
     * <pre>{@code
     * List<String> boutons = Arrays.asList(new Bouton("Oui !", "oui"), new Bouton("Non !", "non"));
     * String input = choisir("Voulez-vous faire ceci ?", null, boutons, false);
     * }</pre>
     * 
     * (ici le premier bouton a le label "Oui !" et envoie le String "oui" s'il est
     * cliqué, le second a le label "Non !" et envoie le String "non" lorsqu'il est
     * cliqué)
     *
     * <p>
     * <b>Remarque :</b> Normalement, si le paramètre {@code peutPasser} est
     * {@code false} le choix
     * {@code ""} n'est pas valide. Cependant s'il n'y a aucun choix proposé (les
     * listes {@code choix} et {@code boutons} sont vides ou {@code null}), le choix
     * {@code ""} est accepté pour éviter un blocage.
     * 
     * @param instruction message à afficher à l'écran pour indiquer au joueur la
     *                    nature du choix qui est attendu
     * @param choix       une collection de chaînes de caractères correspondant aux
     *                    choix valides attendus du joueur (ou {@code null})
     * @param boutons     une liste d'objets de type {@code Bouton} définis par deux
     *                    chaînes de caractères (label, valeur) correspondant aux
     *                    choix valides attendus du joueur qui doivent être
     *                    représentés par des boutons sur l'interface graphique (le
     *                    label est affiché sur le bouton, la valeur est ce qui est
     *                    envoyé au jeu quand le bouton est cliqué) ou {@code null}
     * @param peutPasser  booléen indiquant si le joueur a le droit de passer sans
     *                    faire de choix. S'il est autorisé à passer, c'est la
     *                    chaîne de caractères vide ({@code ""}) qui signifie qu'il
     *                    désire passer.
     * @return le choix de l'utilisateur (un élement de {@code choix}, ou la valeur
     *         d'un élément de {@code boutons} ou la chaîne vide)
     */
    public String choisir(
            String instruction,
            Collection<String> choix,
            List<Bouton> boutons,
            boolean peutPasser) {
        if (choix == null)
            choix = new ArrayList<>();
        if (boutons == null)
            boutons = new ArrayList<>();

        HashSet<String> choixDistincts = new HashSet<>(choix);
        choixDistincts.addAll(boutons.stream().map(Bouton::valeur).toList());
        if (peutPasser || choixDistincts.isEmpty()) {
            // si le joueur a le droit de passer ou s'il n'existe aucun choix valide, on
            // ajoute "" à la liste des choix possibles
            choixDistincts.add("");
        }

        String entree;
        // Lit l'entrée de l'utilisateur jusqu'à obtenir un choix valide
        while (true) {
            jeu.prompt(instruction, boutons, peutPasser);
            entree = jeu.lireLigne();
            log(entree);
            // si une réponse valide est obtenue, elle est renvoyée
            if (choixDistincts.contains(entree)) {
                return entree;
            }
        }
    }

    /**
     * Ajoute un message dans le log du jeu
     * 
     * @param message message à ajouter dans le log
     */
    public void log(String message) {
        jeu.log(message);
    }

    @Override
    public String toString() {
        // Vous pouvez modifier cette fonction comme bon vous semble pour afficher
        // d'autres informations si nécessaire
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add(String.format("=== %s (%d pts) ===", nom, getScoreTotal()));
        joiner.add(String.format("  Argent: %d  Rails: %d", argent, pointsRails));
        joiner.add("  Cartes en jeu: " + cartesEnJeu);
        joiner.add("  Cartes reçues: " + cartesRecues);
        joiner.add("  Cartes en main: " + main);
        return joiner.toString();
    }

    /**
     * @return une représentation du joueur pour l'affichage dans le log du jeu
     */
    public String toLog() {
        return String.format("<span class=\"joueur %s\">%s</span>", couleur.toString(), nom);
    }

    /**
     * @return une représentation du joueur sous la forme d'un dictionnaire de
     *         valeurs sérialisables (qui sera converti en JSON pour l'envoyer à
     *         l'interface graphique)
     */
    Map<String, Object> dataMap() {
        return Map.ofEntries(
                Map.entry("nom", nom),
                Map.entry("couleur", couleur),
                Map.entry("scoreTotal", getScoreTotal()),
                Map.entry("argent", argent),
                Map.entry("rails", pointsRails),
                Map.entry("nbJetonsRails", nbJetonsRails),
                Map.entry("main", main.dataMap()),
                Map.entry("defausse", defausse.dataMap()),
                Map.entry("cartesEnJeu", cartesEnJeu.dataMap()),
                Map.entry("cartesRecues", cartesRecues.dataMap()),
                Map.entry("pioche", pioche.dataMap()),
                Map.entry("actif", jeu.getJoueurCourant() == this));
    }

}
