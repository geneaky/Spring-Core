package hello.core.autowired;

import hello.core.member.Member;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

public class AutowiredTest {

  @Test
  public void autowiredOption() throws Exception{

    ApplicationContext ac = new AnnotationConfigApplicationContext(
        TestBean.class);
    //given


    //when

    //then
  }

  static class TestBean{

    //required = false이면 메서드 자체를 호출하지 않음
    @Autowired(required = false)
    public void setNoBean1(Member noBean1){
      System.out.println("noBean1 = " + noBean1);
    }

    //@Nullable이면 호출은 되지만 null이 들어옴
    @Autowired
    public void setNoBean2(@Nullable Member noBean2){
      System.out.println("noBean2 = " + noBean2);
    }

    //해당하는 스프링 빈이 없으면 optional empty로 들어오고 값이 있으면 optional로 감싸서 나옴
    @Autowired
    public void setNoBean3(Optional<Member> noBean3){
      System.out.println("noBean3 = " + noBean3);
    }
  }

}
