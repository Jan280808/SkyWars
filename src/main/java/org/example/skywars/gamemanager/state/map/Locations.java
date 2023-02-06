package org.example.skywars.gamemanager.state.map;

public enum Locations {

    LOCATION_END("end"),
    LOCATION_WAITING_LOBBY("lobby"),
    LOCATION_SPECTATOR_SPAWN("spectators"),
    LOCATION_PLAYER_ONE("playerSpawn1"),
    LOCATION_PLAYER_TWO("playerSpawn2"),
    LOCATION_PLAYER_THREE("playerSpawn3"),
    LOCATION_PLAYER_FORE("playerSpawn4"),
    LOCATION_PLAYER_FIVE("playerSpawn5"),
    LOCATION_PLAYER_SIX("playerSpawn6"),
    LOCATION_PLAYER_SEVEN("playerSpawn7"),
    LOCATION_PLAYER_EIGHT("playerSpawn8");

    private final String string;

    Locations(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
