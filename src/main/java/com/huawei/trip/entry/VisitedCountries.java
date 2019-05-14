package com.huawei.trip.entry;

import com.huawei.trip.customer.SelfDescribing;

import java.util.List;

import static com.huawei.trip.util.Preconditions.allowEmpty;

public class VisitedCountries implements SelfDescribing {
  private List<String> countriesVisited;

  public VisitedCountries(List<String> countriesVisited) {
    this.countriesVisited = allowEmpty(countriesVisited);
  }

  @Override
  public void describeTo(StringBuilder buff) {
    buff.append("Visited: " + String.join(", ", countriesVisited) + "\n");
  }
}
