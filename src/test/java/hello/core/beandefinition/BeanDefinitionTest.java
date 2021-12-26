package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanDefinitionTest {
  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
  
  @Test
  public void 빈_설정_메타정보_확인() throws Exception{
    //given
    String[] beanDefinitionNames = ac.getBeanDefinitionNames();
    for (String beanDefinitionName : beanDefinitionNames) {
      BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

      if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
        System.out.println("beanDefinition = " + beanDefinition + "beanDefinitionName = " + beanDefinitionName);
      }
    }

    //when
    
    //then
  }
}
