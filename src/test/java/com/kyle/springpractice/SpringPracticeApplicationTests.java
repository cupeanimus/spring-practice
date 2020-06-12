//package com.kyle.springpractice;
//
//import com.kyle.springpractice.domain.entity.Member;
//import com.kyle.springpractice.persistence.MemberRepository;
//import com.kyle.springpractice.persistence.MemberRepsoitorySupport;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.util.List;
//
//@SpringBootTest
//class SpringPracticeApplicationTests {
//
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private MemberRepsoitorySupport memberRepsoitorySupport;
//
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }
//
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Test
//    public void memberQuerydslTest(){
//
//        String email = "test@test.com";
//        String lastname = "tester";
//        Member member = new Member(email,lastname);
//        System.out.println(member.toString());
//
//        memberRepository.save(member);
//
//        List<Member> memberList = memberRepository.findMemberCustom();
//
//        assertTrue(memberList.size()==1);
//        assertTrue(memberList.get(0).getLastname().equals(lastname));
//
//    }
//
//}
