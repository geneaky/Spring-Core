package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

  private  final MemberRepository memberRepository;
  private  final DiscountPolicy discountPolicy;
  /*
  * spring이 container를 만들고 빈을 등록한 후 의존관계를 설정하는 2단계로 나뉨
  * 생성자 주입, setter주입 등록하면 그 순서는 보장되지 않음
  * */

  /* 필드 주입은 외부에서 변경할 수 없으므로 테스트가 어렵다
  테스트하려면 setter만들고 넣어야되는데 실제 프로덕션에서 안쓰고 테스트에 쓸걸 만들어두기도 좀;;
  * @Autowired private MemberRepository memberRepository;
  * @Autowired private DiscoundPolicy discountPolicy;
  * */

  /*
  * 일반 메서드 주입
  * (보통 생성자 주입이나 setter 주입을 사용하므로 잘 사용안함)
  * @Autowired
  * public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy){
  *   this.memberRepository = memberRepository;
  *   this.discountPolicy = discountPolicy
  * */

/*
  @Autowired
  public void setMemberRepository(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Autowired
  public void setDiscountPolicy(DiscountPolicy discountPolicy) {
    this.discountPolicy = discountPolicy;
  }
  */
  /*
  public OrderServiceImpl(){
  }
  생성자가 2개 이상일 경우 @Autowired를 명시적으로 붙여줘야 spring에서 관리해야하는 빈으로 인식해서
  injection을 해준다.
*/
  /*
  * 생성자 주입
  * 생성자 호출시점에 딱 1번만 호출되는 것이 보장됨
  * 불변, 필수 의존관계에 사용
  */

//  @Autowired 생성자가 하나일 경우 해당 애너테이션을 붙이지 않아도됨
  public OrderServiceImpl(MemberRepository memberRepository,
      DiscountPolicy discountPolicy) {
    System.out.println("memberRepository = " + memberRepository);
    System.out.println("discountPolicy = " + discountPolicy);
    this.memberRepository = memberRepository;
    this.discountPolicy = discountPolicy;
  }

  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member member = memberRepository.findById(memberId);
    int discountPrice = discountPolicy.discount(member, itemPrice);

    return new Order(memberId,itemName,itemPrice,discountPrice);
  }

  //test
  public MemberRepository getMemberRepository() {
    return memberRepository;
  }
}
