package com.huawei.trip.util;

import com.huawei.trip.customer.Rule;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public final class Preconditions {
  public static <T> T allowEmpty(T obj, T defaultValue) {
    return obj == null ? defaultValue : obj;
  }

  public static String allowEmpty(String str) {
    return allowEmpty(str, "");
  }

  public static <T> List<T> allowEmpty(List<T> list) {
    return allowEmpty(list, Collections.emptyList());
  }

  public static String requireNonEmpty(String str, String msg) {
    return require(str, msg, () ->
      str != null && !str.isEmpty()
    );
  }

  public static <T> T requireNonNull(T obj, String msg) {
    return require(obj, msg, () ->
        obj != null
    );
  }

  public static LocalDate requireBeforeNow(LocalDate date, String msg) {
    return require(date, msg, () ->
        date.isBefore(LocalDate.now())
    );
  }

  public static double requirePositive(double d, String msg) {
    return require(d, msg, () ->
        d >= 0
    );
  }

  private static <T> T require(T obj, String msg, Rule rule) {
    if (!rule.approved())
      throw new IllegalArgumentException(msg);
    return obj;
  }

  private Preconditions() {
  }
}

