package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

  @Test
  public void configurationTest() throws Exception{
    //given
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        AppConfig.class);
    MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
    OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
    MemberRepository memberRepository3 = ac.getBean("memberRepository", MemberRepository.class);

    MemberRepository memberRepository1 = memberService.getMemberRepository();
    MemberRepository memberRepository2 = orderService.getMemberRepository();

    System.out.println("memberService -> memberRepository1 = " + memberRepository1);
    System.out.println("orderService -> memberRepository2 = " + memberRepository2);
    System.out.println("memberRepository3 = " + memberRepository3);

    Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository3);
    Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository3);
    Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberService.getMemberRepository());
  }
  
  @Test
  public void configurationDeep() throws Exception{
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        AppConfig.class);

    AppConfig bean = ac.getBean(AppConfig.class);

    System.out.println("bean.getClass() = " + bean.getClass());

  }

}
