package fr.umontpellier.iut.trains;

import fr.umontpellier.iut.trains.cartes.Aiguillage;
import fr.umontpellier.iut.trains.cartes.Carte;
import fr.umontpellier.iut.trains.cartes.Ferraille;
import fr.umontpellier.iut.trains.cartes.TrainOmnibus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JoueurEtuTest extends BaseTestClass{

    @Test
    void test_vider_ferraille(){
        setupJeu();
        initialisation();

        Carte f1 = new Ferraille();
        Carte f2 = new Ferraille();
        Carte f3 = new Ferraille();
        Carte aiguillage = new Aiguillage();
        Carte omni = new TrainOmnibus();

        addAll(main,f1,f2,f3,aiguillage,omni);

        jouerTourPartiel("Ferraille");

        assertTrue(containsReferences(main, aiguillage, omni));
        assertTrue(containsReferences(pioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu));
        assertTrue(containsReferences(cartesRecues));
    }

    @Test
    void test_vider_ferraille_invalide(){
        setupJeu();
        initialisation();

        Carte f1 = new Ferraille();
        Carte f2 = new Ferraille();
        Carte f3 = new Ferraille();
        Carte aiguillage = new Aiguillage();
        Carte omni = new TrainOmnibus();

        addAll(main,f1,f2,f3,aiguillage,omni);

        jouerTourPartiel("Aiguillage","Ferraille");

        assertTrue(containsReferences(main,f1,f2,f3, omni));
        assertTrue(containsReferences(pioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu,aiguillage));
        assertTrue(containsReferences(cartesRecues));
    }
}
