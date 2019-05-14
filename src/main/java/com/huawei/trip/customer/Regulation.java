package com.huawei.trip.customer;

public class Regulation {

  private static final int DEFAULTS = 0x01;

  public static final int FRUITS_OK = DEFAULTS << 0;
  public static final int MEATS_OK  = DEFAULTS << 1;
  public static final int SOIL_OK   = DEFAULTS << 2;
  public static final int DISEASE_AGENTS_OK = DEFAULTS << 3;
  public static final int CARRYING_10K_OK = DEFAULTS << 4;
  public static final int CLOSED_LIVING_STOCK_OK = DEFAULTS << 5;

  private int value;

  public Regulation() {
    this(0);
  }

  public Regulation(int value) {
    this.value = value;
  }

  public Rule allow(boolean selected, int regulation) {
    return () -> !selected || allowed(regulation);
  }

  private boolean allowed(int regulation) {
    return (value & regulation) == regulation;
  }
}
