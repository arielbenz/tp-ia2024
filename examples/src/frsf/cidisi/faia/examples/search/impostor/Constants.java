package frsf.cidisi.faia.examples.search.impostor;

import java.util.HashMap;

public class Constants {

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
  public static final int INITIAL_AGENT_POSITION = ROOM_LOWER_ENGINE;
  public static final int[] INITIAL_CREW_PER_ROOM = new int[] { 0, 0, 1, 0, 0 };
  public static final int INITIAL_TOTAL_CREW = 2;
  public static final int[] INITIAL_SABOTAGE_ROOMS = new int[] { ROOM_REACTOR };
  public static final int Q_CONSUME_ENERGY = 1;

  // Game data structure
  public static final int[][] SHIP = new int[5][4];
  public static final int[] CREW_PER_ROOM = new int[INITIAL_CREW_PER_ROOM.length];
  public static final int[] SABOTAGE_ROOMS = new int[INITIAL_SABOTAGE_ROOMS.length];
  public static final int[] AGENT_ORIENTATION = new int[4];

  public Constants() {
    this.init();
  }

  public void init() {
    SHIP[Constants.ROOM_UPPER_ENGINE][Constants.UP] = Constants.WALL;
    SHIP[Constants.ROOM_UPPER_ENGINE][Constants.DOWN] = Constants.HALL_F;
    SHIP[Constants.ROOM_UPPER_ENGINE][Constants.LEFT] = Constants.WALL;
    SHIP[Constants.ROOM_UPPER_ENGINE][Constants.RIGHT] = Constants.WALL;

    // Security = 1
    SHIP[Constants.ROOM_SECURITY][Constants.UP] = Constants.WALL;
    SHIP[Constants.ROOM_SECURITY][Constants.DOWN] = Constants.WALL;
    SHIP[Constants.ROOM_SECURITY][Constants.LEFT] = Constants.HALL_F;
    SHIP[Constants.ROOM_SECURITY][Constants.RIGHT] = Constants.WALL;

    // Lower Engine = 2
    SHIP[Constants.ROOM_LOWER_ENGINE][Constants.UP] = Constants.HALL_F;
    SHIP[Constants.ROOM_LOWER_ENGINE][Constants.DOWN] = Constants.WALL;
    SHIP[Constants.ROOM_LOWER_ENGINE][Constants.LEFT] = Constants.WALL;
    SHIP[Constants.ROOM_LOWER_ENGINE][Constants.RIGHT] = Constants.WALL;

    // Reactor = 3
    SHIP[Constants.ROOM_REACTOR][Constants.UP] = Constants.WALL;
    SHIP[Constants.ROOM_REACTOR][Constants.DOWN] = Constants.WALL;
    SHIP[Constants.ROOM_REACTOR][Constants.LEFT] = Constants.WALL;
    SHIP[Constants.ROOM_REACTOR][Constants.RIGHT] = Constants.HALL_F;

    // Hall F = 4
    SHIP[Constants.HALL_F][Constants.UP] = Constants.ROOM_UPPER_ENGINE;
    SHIP[Constants.HALL_F][Constants.DOWN] = Constants.ROOM_LOWER_ENGINE;
    SHIP[Constants.HALL_F][Constants.LEFT] = Constants.ROOM_REACTOR;
    SHIP[Constants.HALL_F][Constants.RIGHT] = Constants.ROOM_SECURITY;
  }

  public static int getShipPosition(int row, int col) {
    return SHIP[row][col];
  }
}
