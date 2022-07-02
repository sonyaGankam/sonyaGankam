package com.example.application1;

import java.util.UUID;


public class ClientController {
    String idClient, localizationAddress;
    double[] distanceArray;
    double[] vitesseArray;
    double[] accelarationArray;
    int [] tempsArray;
    int periodCadence = 5; // Par exemple, 5 secondsGeoLocationModel geoLocationModel = new EasyLocationFetch(context,GoogleApiKey).getLocationData();




    public ClientController(String idClient, String localizationAddress, double[] distanceArray, double[] vitesseArray, double[] accelarationArray, int[] tempsArray) {
        this.idClient = idClient;
        this.localizationAddress = localizationAddress;
        this.distanceArray = distanceArray;
        this.vitesseArray = vitesseArray;
        this.accelarationArray = accelarationArray;
        this.tempsArray = tempsArray;
    }
          // generate unique id
           UUID id = UUID.randomUUID();

    public String getIdClient() {
        //Lire l'ID du client de l'intance de cette classe.
        // int aleatoire // Rand() dans le setter
        return idClient;
    }


    public String getLocalizationAddress() {
        // Lire la position actu de la voiture (client) en coordonees Google Maps.
        return localizationAddress;
    }

    public void setLocalizationAddress(String localizationAddress) {
        //Ecrir a la classe la position actu de la voiture client en coordonnes Google Maps
        //Utiliser la librairie  Easy Location Fetch / Location Service pour affecter a la classe sa la position du client.
        this.localizationAddress = localizationAddress;
    }

    public double[] getDistanceArray() {
        //Lire Tableau
        return distanceArray;
    }

    public void setDistanceArray(double[] distanceArray) {
        //Tableau remplis chaque period de temps (5 secondes)
        this.distanceArray = distanceArray;
    }

    public double[] getVitesseArray() {
        //Lire Tableau
        return vitesseArray;
    }

    public void setVitesseArray(double[] vitesseArray) {
        //Tableau remplis chaque period de temps (5 secondes)
        this.vitesseArray = vitesseArray;
    }

    public double[] getAccelarationArray() {
        //Lire tableau
        return accelarationArray;
    }

    public void setAccelarationArray(double[] accelarationArray) {
        //Tableau remplis chaque period de temps (5secondes)
        this.accelarationArray = accelarationArray;
    }

    public int[] getTempsArray() {
        //Lire tableau de temps
        return tempsArray;
    }

    public void setTempsArray(int[] tempsArray) {
        //Tableau a remplir chaque periode de temps T (5 seconds)
        //Ici tu va seulement ajouter 5 seconds a la prochaine entree, pr que l'ecart reste 5 secondes
        this.tempsArray = tempsArray;
    }

    // maintenant les methods qui agissent

    double calculDistance (int positionInit, int positionFinale, int tempsPeriod){
       int distance = 0;
        //Calcul de distance entre point initiale et point finale
         return distance;
    }

    void updateDetails (int tempsPeriode){

        // Cette methods va ajouter des valeurs dans tout les tableau chaque 5 secondes.
        // Il va aussi declencher les methods qui ont pour but de calculer  la vitesse et l'acceleration
        // apres avoir eu ces resultats, on va donc populer les tableaux correspondant...

        //Donc, on mets a jour les tableau de distance et temps chaque 5 secondes...
        // Quand on arrive a la deuxiemme itteration, on ajoute aussi dans leurs tableaux les valeurs calcules de vitesse et acceleration.


    }
}
