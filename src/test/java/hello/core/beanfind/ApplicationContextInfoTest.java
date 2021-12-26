package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

  @Test
  public void 모든_빈_출력() throws Exception{
    //given
    String[] beanDefinitionNames = ac.getBeanDefinitionNames();

    for (String beanDefinitionName : beanDefinitionNames) {
      Object bean = ac.getBean(beanDefinitionName);
      System.out.println("beanDefinitionName = " + beanDefinitionName + " object = " + bean);
    }

    //when

    //then
  }

  @Test
  public void 애플리케이션_빈_출력하기() throws Exception{
    //given
    String[] beanDefinitionNames = ac.getBeanDefinitionNames();

    for (String beanDefinitionName : beanDefinitionNames) {

      BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

      // Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
      // Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
      if(beanDefinition.getRole() == BeanDefinition.ROLE_INFRASTRUCTURE){
        Object bean = ac.getBean(beanDefinitionName);
        System.out.println("name = " + beanDefinitionName + " object = " + bean);
      }

    }

    //when

    //then
  }

}
