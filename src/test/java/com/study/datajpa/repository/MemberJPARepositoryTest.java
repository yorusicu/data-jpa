package com.study.datajpa.repository;

import com.study.datajpa.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
//@Rollback(value = false)
class MemberJPARepositoryTest {
    @Autowired
    MemberJPARepository memberJPARepository;

    @Test
    @Transactional
    void MemberRepositoryTest() throws Exception{
        // given
        Member member = new Member("memberA");
        Member savedMember = memberJPARepository.save(member);

        // when
        Member findMember = memberJPARepository.find(savedMember.getId());

        // then
        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getName()).isEqualTo(member.getName());
        assertThat(findMember).isEqualTo(member);


    }

}