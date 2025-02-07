package org.example.trambleo;

import java.io.*;
import java.util.ArrayList;
import java.util.UUID;

public class Employe {
    UUID idUtilisateur = UUID.randomUUID();
    String nom;
    String prenom;
    String email;
    String motDePasse;
    String poste; // au sein de l'entreprise
    static Employe employeSelected;
    static ArrayList<Projet> listeProjet = new ArrayList<Projet>();
    static ArrayList<Employe> listeEmploye = new ArrayList<>();
    boolean isSupprime;
    boolean isDansEquipe;

    public Employe(String nom, String prenom, String email, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.isSupprime = false;
        this.isDansEquipe = false;
    }

    public static void  importEmploye() {
        String filePath = "src/main/resources/Employe.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 5) {
                    try {
                        UUID idUtilisateur = UUID.randomUUID();
                        String nom = parts[1];
                        String prenom = parts[2];
                        String email = parts[3];
                        String motDePasse = parts[4];
                        String poste = parts[5];
                        Employe employe = new Employe(nom, prenom, email, motDePasse);
                        listeEmploye.add(employe);
                    } catch (NumberFormatException e) {
                        System.out.println("Erreur de conversion sur cette ligne : " + line);
                    }
                } else {
                    System.out.println("Ligne mal formée, ignorée : " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }

    public String toCSV(){
        return idUtilisateur + ";" + nom + ";" + prenom + ";" + email + ";" + motDePasse + ";" + poste;
    }
    public static void saveEmploye(Employe employe) {
        String filePath = "src/main/resources/Employe.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) // `true` pour ajouter à la fin
        {
            writer.write(employe.toCSV());
            writer.newLine();
            System.out.println("Le CSV a été mis à jour");

        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier : " + e.getMessage());
        }
    }

    // <editor-fold desc="Getters and setters">


    public static void setEmployeSelected(Employe employeSelected) {
        Employe.employeSelected = employeSelected;
    }

    public static Employe getEmployeSelected() {
        return employeSelected;
    }

    public UUID getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(UUID idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }


    public ArrayList<Projet> getListeProjet() {
        return listeProjet;
    }

    public void setListeProjet(ArrayList<Projet> listeProjet) {
        this.listeProjet = listeProjet;
    }

    public boolean isSupprime() {
        return isSupprime;
    }

    public void setSupprime(boolean supprime) {
        isSupprime = supprime;
    }

    public boolean isDansEquipe() {
        return isDansEquipe;
    }

    public void setDansEquipe(boolean dansEquipe) {
        isDansEquipe = dansEquipe;
    }

    // </editor-fold>

    public String toString() {
        return "Identifiant : " + idUtilisateur + "\nNom : " + nom + "\nPrénom : " + prenom + "\nEmail : " + email + "\nMot de passe : " + motDePasse + "\nPoste : " + poste + "\nListe des projets : " + listeProjet.size() + "\n";
    }

    public void modifierEmploye(String attribut, String nouvelleValeur) {
        switch (attribut.toLowerCase()) {
            case "nom":
                this.nom = nouvelleValeur;
                break;
            case "prenom":
                this.prenom = nouvelleValeur;
                break;
            case "email":
                this.email = nouvelleValeur;
                break;
            case "motDePasse":
                this.motDePasse = nouvelleValeur;
                break;
            case "poste":
                this.poste = nouvelleValeur;
                break;
        }
    }

    public void supprimerEmploye() {
        this.isSupprime = true;
        System.out.println("L'employé " + prenom + " " + nom + " a bien été supprimé");
    }
}
