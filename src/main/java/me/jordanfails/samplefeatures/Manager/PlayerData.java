package me.jordanfails.samplefeatures.Manager;


import java.util.UUID;

public class PlayerData {
    private UUID uniqueID;

    public PlayerData(UUID uuid) {
        this.uniqueID = uuid;
    }

    public UUID getUniqueID() {
        return this.uniqueID;
    }
}
