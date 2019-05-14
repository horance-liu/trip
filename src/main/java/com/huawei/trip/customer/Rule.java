package com.huawei.trip.customer;

import java.util.Arrays;

@FunctionalInterface
public interface Rule {
  boolean approved();

  static Rule always() {
    return () -> true;
  }

  static Rule all(Rule... rules) {
    return () -> Arrays.stream(rules).allMatch(Rule::approved);
  }
}
