package hello.core.beanfind;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import hello.core.beanfind.ApplicationContextSameBeanFindTest.SameBeanConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicatoinContextExtendsFindTest {
  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

  @Test
  public void 부토_타입으로_조회시_자식이_둘_이상_있으면_중복_오류_발생() throws Exception{
    assertThrows(NoUniqueBeanDefinitionException.class,() -> {
      ac.getBean(DiscountPolicy.class);
    });
  }

  @Test
  public void 부모_타입으로_조회시_자식이_둘_이상_있으면_빈_이름을_지정하면_된다() throws Exception{
    DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
    assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
  }

  @Test
  public void 특정_하위_타입으로_조회() throws Exception{
    RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
    assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
  }

  @Test
  public void 부모타입으로_모두_조회() throws Exception{
    Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
    assertThat(beansOfType.size()).isEqualTo(2);
    for (String key : beansOfType.keySet()) {
      System.out.println("key = " + key + " value = " + beansOfType.get(key));
    }
  }

  @Test
  public void 부모타입으로_모두_조회하기_object() throws Exception{
    Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
    for (String key : beansOfType.keySet()) {
      System.out.println("key = " + key + " value = " + beansOfType.get(key));
    }
  }

  @Configuration
  static class TestConfig{

    @Bean
    public DiscountPolicy rateDiscountPolicy(){
      return new RateDiscountPolicy();
    }

    @Bean
    public DiscountPolicy fixDiscountPolicy(){
      return new FixDiscountPolicy();
    }
  }
}
