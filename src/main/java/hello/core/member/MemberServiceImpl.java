package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component("memberService2")
@Component()
public class MemberServiceImpl implements  MemberService{
    //추상화에도 의존(MemberRepository) 구체화에도 의존(new MemoryMemberRepository()), dip 위반
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private  final MemberRepository memberRepository;

    @Autowired //ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long MemberId) {
        return memberRepository.findById(MemberId);
    }

    //for test
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
