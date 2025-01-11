package org.example.trambleo;

import java.util.ArrayList;
import java.util.UUID;

public class Equipe {
    UUID idEquipe = UUID.randomUUID();
    ArrayList<Employe> listeMembre;
    boolean isProjetAssocie;

    // <editor-fold desc="Getters and setters">

    public UUID getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(UUID idEquipe) {
        this.idEquipe = idEquipe;
    }

    public ArrayList<Employe> getListeMembre() {
        return listeMembre;
    }

    public void setListeMembre(ArrayList<Employe> listeMembre) {
        this.listeMembre = listeMembre;
    }

    public boolean isProjetAssocie() {
        return isProjetAssocie;
    }

    public void setProjetAssocie(boolean projetAssocie) {
        isProjetAssocie = projetAssocie;
    }

    // </editor-fold>
}
