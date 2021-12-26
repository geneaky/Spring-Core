package hello.core.beanfind;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindTest {
  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

  @Test
  public void 빈_이름으로_조회() throws Exception{
    //given
    MemberService memberService = ac.getBean("memberService", MemberService.class);
    System.out.println("memberService = " + memberService);
    System.out.println("memberService.getClass() = " + memberService.getClass());

    //when

    //then
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }

  @Test
  public void 빈_타입으로_조회() throws Exception{
    //given
    MemberService memberService = ac.getBean(MemberService.class);

    //when

    //then
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }

  @Test
  public void 구체_타입으로_조회() throws Exception{
    //given
    MemberService memberService = ac.getBean("memberService",MemberServiceImpl.class);

    //when

    //then
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }

  @Test
  public void 빈_이름으로_조회x() throws Exception{
    //given

    //when

    //then
    assertThrows(NoSuchBeanDefinitionException.class,() -> {
      ac.getBean("xxxxx",MemberService.class);
    });
  }
}
