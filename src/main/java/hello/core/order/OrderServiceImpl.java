package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor //final 붙은 변수의 생성자 생성한다.
public class OrderServiceImpl implements OrderService{
    /*
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private  final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private  final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private  DiscountPolicy discountPolicy;//null ptr excetion 발생한다.
    */
    private  final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //@Autowired private MemberRepository memberRepository;
    //@Autowired private discountPolicy;


    /*
    // set에 autowired를 하면 bean등록과 의존관계 주입이 따로
    @Autowired(required=false)
    public void setMemberRepository(MemberRepository memberRepository){
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }
    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy){
        System.out.println("discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy
    }
    */

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, /*@Qualifier("mainDiscountPolicy")*/ DiscountPolicy discountPolicy) {
        //생성자에 autowired를 하면 bean 등록하면서 의존관계주입이 같이 된다
        System.out.println("memberRepository = " + memberRepository);
        System.out.println("discountPolicy = " + discountPolicy);

        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //for test
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
