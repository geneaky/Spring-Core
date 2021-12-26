package hello.core.xml;

import static org.assertj.core.api.Assertions.*;

import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

public class XmlAppContext {

  @Test
  public void xmlAppContext() throws Exception{
    //given
    GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
    MemberService memberService = ac.getBean("memberService", MemberService.class);
    //when

    //then
    assertThat(memberService).isInstanceOf(MemberService.class);
  }

}
