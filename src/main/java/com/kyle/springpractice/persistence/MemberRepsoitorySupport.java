package com.kyle.springpractice.persistence;

import com.kyle.springpractice.domain.entity.Member;
import com.kyle.springpractice.domain.entity.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepsoitorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public MemberRepsoitorySupport(JPAQueryFactory queryFactory) {
        super(Member.class);
        this.queryFactory = queryFactory;
    }

    public List<Member> findMember(){
        QMember member = QMember.member;
        return queryFactory.selectFrom(member)
                .where()
                .fetch();
    }
}
