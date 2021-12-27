package hello.core.singleton;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

class StatefulServiceTest {



  @Test
  public void statefulServiceSingleton() throws Exception{
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        TestConfig.class);

    StatefulService statefulService1 = ac.getBean(StatefulService.class);
    StatefulService statefulService2 = ac.getBean(StatefulService.class);

    //ThreadA: A사용자 10000원 주문
    statefulService1.order("userA",10000);

    //ThreadB: B사용자 20000원 주문
    statefulService2.order("suerB",20000);

    //ThreadA: 사용자A 주문 금액 조회
    int price = statefulService1.getPrice();
    System.out.println("price = " + price);

    Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
  }

  static class TestConfig{

    @Bean
    public StatefulService statefulService(){
      return new StatefulService();
    }
  }

}