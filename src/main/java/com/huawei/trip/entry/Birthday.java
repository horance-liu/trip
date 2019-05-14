package com.huawei.trip.entry;

import com.huawei.trip.customer.CompanionList;
import com.huawei.trip.customer.Regulation;
import com.huawei.trip.customer.SelfDescribing;
import com.huawei.trip.customer.Rule;

import java.time.LocalDate;
import java.time.Period;

import static com.huawei.trip.util.Preconditions.*;

public class Birthday implements SelfDescribing {
  private LocalDate birthday;

  public Birthday(LocalDate birthday) {
    this.birthday = requireNonNull(birthday, "birthday can't be null");
    this.birthday = requireBeforeNow(birthday, "birthday should before today");
  }

  public boolean isAdult() {
    return Period.between(birthday, LocalDate.now()).getYears() > 18;
  }

  public Rule rule(Regulation regulation, Address address, CompanionList companions) {
    return isAdult() ? Rule.always() : suite(regulation, address, companions);
  }

  private static Rule suite(Regulation regulation, Address address, CompanionList companions) {
    return Rule.all(
      companions.accept(address),
      companions.passed(regulation)
    );
  }

  @Override
  public void describeTo(StringBuilder buff) {
    buff.append("Birthday: " + birthday.toString() + "\n");
  }
}
