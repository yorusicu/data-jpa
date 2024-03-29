package com.study.datajpa.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Rollback(value = false)
class MemberTest {
    @PersistenceContext
    EntityManager em;
    
    @Test
    @Transactional
    void testEntity() throws Exception{
        // given
        Team teamA = new Team("TeamA");
        Team teamB = new Team("TeamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        em.flush(); // 강제 쿼리 날리기
        em.clear(); // 영속성 캐시 날리기

        // when
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();

        // then
        for (Member m  : members) {
            System.out.println("member = " + m);
            System.out.println(">>> member.team = " + m.getTeam());
        }
        
    }
}