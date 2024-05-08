package frsf.cidisi.faia.examples.search.impostor;

import java.util.HashMap;

public class ShipStructure {

  // Rooms codification
  public static final int ROOM_UPPER_ENGINE = 0;
  public static final int ROOM_SECURITY = 1;
  public static final int ROOM_LOWER_ENGINE = 2;
  public static final int ROOM_REACTOR = 3;
  public static final int HALL_F = 4;

  public static final int WALL = -1;

  public static final HashMap<Integer, String> ROOMS = new HashMap<>();
  static {
    ROOMS.put(WALL, "Wall");
    ROOMS.put(ROOM_UPPER_ENGINE, "Upper Engine");
    ROOMS.put(ROOM_SECURITY, "Security");
    ROOMS.put(ROOM_LOWER_ENGINE, "Lower Engine");
    ROOMS.put(ROOM_REACTOR, "Reactor");
    ROOMS.put(HALL_F, "Hall F");
  }

  // Orientation codification
  public static final int UP = 0;
  public static final int DOWN = 1;
  public static final int LEFT = 2;
  public static final int RIGHT = 3;

  // Initial values
  public static final int INITIAL_AGENT_ENERGY = 100;
  public static final int INITIAL_AGENT_POSITION = ROOM_SECURITY;

  public static final int[] INITIAL_CREW_PER_ROOM = new int[] { 0, 0, 0, 0, 0 };
  public static final int INITIAL_TOTAL_CREW = 0;

  public static final int[] INITIAL_SABOTAGE_ROOMS = new int[] { ROOM_REACTOR, ROOM_LOWER_ENGINE };

  public static final int Q_CONSUME_ENERGY = 1;

  // Game data structure
  public static final int[][] SHIP = new int[5][4];
  public static final int[] AGENT_ORIENTATION = new int[4];
  public static final int[] CREW_PER_ROOM = new int[5];

  public ShipStructure() {
  }

  public void init() {
    SHIP[ROOM_UPPER_ENGINE][UP] = WALL;
    SHIP[ROOM_UPPER_ENGINE][DOWN] = HALL_F;
    SHIP[ROOM_UPPER_ENGINE][LEFT] = WALL;
    SHIP[ROOM_UPPER_ENGINE][RIGHT] = WALL;

    // Security = 1
    SHIP[ROOM_SECURITY][UP] = WALL;
    SHIP[ROOM_SECURITY][DOWN] = WALL;
    SHIP[ROOM_SECURITY][LEFT] = HALL_F;
    SHIP[ROOM_SECURITY][RIGHT] = WALL;

    // Lower Engine = 2
    SHIP[ROOM_LOWER_ENGINE][UP] = HALL_F;
    SHIP[ROOM_LOWER_ENGINE][DOWN] = WALL;
    SHIP[ROOM_LOWER_ENGINE][LEFT] = WALL;
    SHIP[ROOM_LOWER_ENGINE][RIGHT] = WALL;

    // Reactor = 3
    SHIP[ROOM_REACTOR][UP] = WALL;
    SHIP[ROOM_REACTOR][DOWN] = WALL;
    SHIP[ROOM_REACTOR][LEFT] = WALL;
    SHIP[ROOM_REACTOR][RIGHT] = HALL_F;

    // Hall F = 4
    SHIP[HALL_F][UP] = ROOM_UPPER_ENGINE;
    SHIP[HALL_F][DOWN] = ROOM_LOWER_ENGINE;
    SHIP[HALL_F][LEFT] = ROOM_REACTOR;
    SHIP[HALL_F][RIGHT] = ROOM_SECURITY;
  }

  public static int getShipPosition(int room, int orientation) {
    return SHIP[room][orientation];
  }

  public static int[] getRowShipPosition(int room) {
    return new int[] { SHIP[room][UP], SHIP[room][DOWN], SHIP[room][LEFT], SHIP[room][RIGHT] };
  }
}
