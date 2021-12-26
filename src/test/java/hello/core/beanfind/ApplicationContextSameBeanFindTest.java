package hello.core.beanfind;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextSameBeanFindTest {

  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

  @Test
  public void 타입으로_조회시_같은_타입이_둘_이상_있으면_중복_오류_발생() throws Exception{
    //given

    //when
    
    //then
    assertThrows(NoUniqueBeanDefinitionException.class, () -> {
      ac.getBean(MemberRepository.class);
    });
  }

  @Test
  public void 타입으로_조회시_같은_타입이_둘_이상_있으면_빈_이름을_지정() throws Exception{
    //given
    MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

    //when

    //then
    assertThat(memberRepository).isInstanceOf(MemberRepository.class);
  }

  @Test
  public void 특정_타입을_모두_조회하기() throws Exception{
    //given
    Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);

    for (String key : beansOfType.keySet()) {
      System.out.println("key = " + key + " value = " + beansOfType.get(key));
    }

    System.out.println("beansOfType = " + beansOfType);
    assertThat(beansOfType.size()).isEqualTo(2);

    //when

    //then
  }

  @Configuration
  static class SameBeanConfig{

    @Bean
    public MemberRepository memberRepository(){
      return new MemoryMemberRepository();
    }

    @Bean
    public MemberRepository memberRepository2(){
      return new MemoryMemberRepository();
    }
  }

}
