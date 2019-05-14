package com.huawei.trip.entry;

import com.huawei.trip.customer.Regulation;
import com.huawei.trip.customer.SelfDescribing;
import com.huawei.trip.customer.Rule;

import static com.huawei.trip.customer.Regulation.*;

public class ChoiceList implements SelfDescribing {

  private static final int DEFAULTS = 0x01;

  public static final int BRING_FRUITS = DEFAULTS << 0;
  public static final int BRING_MEATS = DEFAULTS << 1;
  public static final int BRING_SOIL = DEFAULTS << 2;
  public static final int BRING_DISEASE_AGENTS = DEFAULTS << 3;
  public static final int CARRYING_10K = DEFAULTS << 4;
  public static final int CLOSED_LIVING_STOCK = DEFAULTS << 5;

  private int value;

  public void enable(int selected) {
    value |= selected;
  }

  private boolean select(int selected) {
    return (value & selected) == selected;
  }

  public Rule rule(Regulation allower) {
    return Rule.all(
      allower.allow(select(BRING_FRUITS), FRUITS_OK),
      allower.allow(select(BRING_MEATS), MEATS_OK),
      allower.allow(select(BRING_SOIL), SOIL_OK),
      allower.allow(select(BRING_DISEASE_AGENTS), DISEASE_AGENTS_OK),
      allower.allow(select(CARRYING_10K), CARRYING_10K_OK),
      allower.allow(select(CLOSED_LIVING_STOCK), CLOSED_LIVING_STOCK_OK)
    );
  }

  @Override
  public void describeTo(StringBuilder buff) {
    buff.append("Bringing Fruits         : " + select(BRING_FRUITS) + "\n");
    buff.append("         Meats          : " + select(BRING_MEATS) + "\n");
    buff.append("         Soil           : " + select(BRING_SOIL) + "\n");
    buff.append("         Disease Agents : " + select(BRING_DISEASE_AGENTS) + "\n");
    buff.append("Carrying 10K Cash: " + select(CARRYING_10K) + "\n");
    buff.append("Is Closed Living Stockings: " + select(CLOSED_LIVING_STOCK) + "\n");
  }
}
