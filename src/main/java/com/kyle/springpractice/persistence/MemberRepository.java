package com.kyle.springpractice.persistence;

import com.kyle.springpractice.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>{
}
