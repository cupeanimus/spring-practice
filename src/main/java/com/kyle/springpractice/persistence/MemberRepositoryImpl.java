//package com.kyle.springpractice.persistence;
//
//import com.kyle.springpractice.domain.entity.Member;
//import com.kyle.springpractice.domain.entity.QMember;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.RequiredArgsConstructor;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//public class MemberRepositoryImpl implements MemberRepositoryCustom {
//    private final JPAQueryFactory queryFactory;
//
//
//    @Override
//    public List<Member> findMemberCustom(){
//        QMember member = QMember.member;
//        return queryFactory.selectFrom(member)
//                .where()
//                .fetch();
//    }
//}
