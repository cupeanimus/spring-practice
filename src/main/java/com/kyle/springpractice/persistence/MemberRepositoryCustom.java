package com.kyle.springpractice.persistence;

import com.kyle.springpractice.domain.entity.Member;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> findMemberCustom();
}
