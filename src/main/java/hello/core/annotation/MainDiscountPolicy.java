package hello.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.beans.factory.annotation.Qualifier;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
}

/*
* @Qualifier("mainDiscountPolicy") 이런식으로 문자를 적으면 컴파일시에 문자열 타입 체크가 안됨 그래서 애너테이션을 위처럼 재정의하는 것이 유용함
* 애노테이션은 상속이라는 개념이 없음, 이렇게 여러 애너테이션을 모아서 사용하는 기능은 스프링에서 제공해주는 기능
* @Qualifer뿐만 아니라 다른 애너테이션도 조합해서 사용할 수 있고 그래서 @Autowired도 재정의 가능
* 하지만? 무분별하게 스프링이 제공하는 기능을 재정의하면 유지보수에 혼란만 불러온다~
* */
