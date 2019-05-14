package com.huawei.trip.entry;

import com.huawei.trip.customer.CommercialsLimit;
import com.huawei.trip.customer.SelfDescribing;
import com.huawei.trip.customer.Rule;

public class BusinessTrip implements SelfDescribing {
  private boolean isBusiness;

  public static BusinessTrip of() {
    return new BusinessTrip(true);
  }

  public static BusinessTrip no() {
    return new BusinessTrip(false);
  }

  private BusinessTrip(boolean isBusiness) {
    this.isBusiness = isBusiness;
  }

  public Rule rule(Commercials commercials, CommercialsLimit limit) {
    return isBusiness ? business(commercials, limit) : nonBusiness(commercials, limit);
  }

  private static Rule business(Commercials commercials, CommercialsLimit limit) {
    return Rule.all(
      commercials.businessTrip(),
      limit.upper(2000)
    );
  }

  private static Rule nonBusiness(Commercials commercials, CommercialsLimit limit) {
    return Rule.all(
      commercials.noBusinessTrip(),
      limit.upper(500)
    );
  }

  @Override
  public void describeTo(StringBuilder buff) {
    buff.append("Business Trip: " + isBusiness + "\n");
  }
}
