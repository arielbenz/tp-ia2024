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

  // Starting values
  public static final int START_ENERGY = 100;
  public static final int TOTAL_CREW = 2;

  public static final int Q_CONSUME_ENERGY = 1;

}
