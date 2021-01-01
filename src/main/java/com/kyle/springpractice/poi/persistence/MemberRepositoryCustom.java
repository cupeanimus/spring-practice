package com.kyle.springpractice.poi.persistence;

import com.kyle.springpractice.poi.entity.Member;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> findMemberCustom();
}
