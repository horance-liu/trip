package com.huawei.trip.entry;

import com.huawei.trip.customer.SelfDescribing;
import com.huawei.trip.customer.Rule;

import static com.huawei.trip.util.Preconditions.requirePositive;

public class Commercials implements SelfDescribing {
  private boolean carried;
  private double value;

  public Commercials(double value) {
    this.value = requirePositive(value, "totalValueOfAllArticle can't be negative");
    this.carried = this.value > 0;
  }

  public double value() {
    return value;
  }

  public Rule noBusinessTrip() {
    return () -> !carried;
  }

  public Rule businessTrip() {
    return () -> !carried || value <= 400;
  }

  @Override
  public void describeTo(StringBuilder buff) {
    buff.append("Carrying Commercials Merchandise : " + carried + "\n");
    buff.append("Total Value of all Article : " + value + "\n");
  }
}
