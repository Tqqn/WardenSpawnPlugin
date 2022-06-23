package com.tqqn.wardenspawn.managers;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

public class SpawnManager {

    private Location location;
    private boolean spawning;

    public void spawnWarden(Location location) {
        calculateRadius(location);
        this.location.getWorld().spawnEntity(location, EntityType.WARDEN);
    }

    private void calculateRadius(Location location) {
        double radiusSquared = (double) 10 * (double) 10;
        double area = radiusSquared * Math.PI;

        this.location = new Location(location.getWorld(), (area - location.getX()),
                (area - location.getY()),
                (area - location.getZ()));
    }
    public boolean isSpawningAllowed() {
        return spawning;
    }
    public void setSpawningBoolean(boolean trueorfalse) {
        this.spawning = trueorfalse;
    }
}

