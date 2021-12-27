package hello.core.singleton;

import static org.assertj.core.api.Assertions.*;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingleTonTest {

  @Test
  public void 스프링_없는_순수한_DI_컨테이너() throws Exception{

    AppConfig appConfig = new AppConfig();
    //1. 조회: 호출할 때 마다 객체를 생성
    MemberService memberService1 = appConfig.memberService();

    //2. 조회: 호출할 때 마다 객체를 생성
    MemberService memberService2 = appConfig.memberService();

    //참조값이 다른 것을 확인
    System.out.println("memberService1 = " + memberService1);
    System.out.println("memberService2 = " + memberService2);

    assertThat(memberService1).isNotSameAs(memberService2);
  }

  @Test
  public void 싱글톤_패턴_적용한_객체_사용() throws Exception{
    //given
    SingletonService instance = SingletonService.getInstance();
    SingletonService instance2 = SingletonService.getInstance();
    //then
    System.out.println("instance = " + instance);
    System.out.println("instance2 = " + instance2);

    assertThat(instance).isSameAs(instance2);
    //equals 논리적 동치성
    //same 물리적 동치성
  }

  @Test
  public void 스프링_컨테이너_싱글톤() throws Exception{
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        AppConfig.class);
    //1. 조회: 호출할 때 마다 객체를 생성
    MemberService memberService1 = ac.getBean("memberService",MemberService.class);

    //2. 조회: 호출할 때 마다 객체를 생성
    MemberService memberService2 = ac.getBean("memberService",MemberService.class);

    //참조값이 다른 것을 확인
    System.out.println("memberService1 = " + memberService1);
    System.out.println("memberService2 = " + memberService2);

    assertThat(memberService1).isSameAs(memberService2);
  }
}
