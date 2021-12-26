package hello.core.discount;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class RateDiscountPolicyTest {

  RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

  @Test
  public void vip_o() throws Exception{
    //given
    Member member = new Member(1L, "memberVIP", Grade.VIP);
    //when
    int discount = discountPolicy.discount(member, 10000);
    //then
    assertThat(discount).isEqualTo(1000);
  }

  @Test
  public void vip_x() throws Exception{
    //given
    Member member = new Member(2L, "memberVIP", Grade.BASIC);
    //when
    int discount = discountPolicy.discount(member, 10000);
    //then
    assertThat(discount).isEqualTo(0);
  }

}