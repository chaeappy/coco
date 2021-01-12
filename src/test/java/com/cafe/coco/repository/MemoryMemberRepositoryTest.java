package com.cafe.coco.repository;

import com.cafe.coco.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;


class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // Test 메서드 실행이 끝날 떄 마다 실행됨
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setId("chaea");
        repository.save(member);
        Member result = repository.findByNum(member.getNum()).get();
//        System.out.println("result = " + (result == member));
//        Assertions.assertEquals(result, null);
        Assertions.assertThat(result).isEqualTo(member);
    }

    @Test
    public void findById() {
        Member member1 = new Member();
        member1.setId("chaea");
        repository.save(member1);

        Member member2 = new Member();
        member2.setId("coco");
        repository.save(member2);

        Member result =  repository.findById("coco").get();
        Assertions.assertThat(result).isEqualTo(member2);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setId("chaea");
        repository.save(member1);

        Member member2 = new Member();
        member2.setId("coco");
        repository.save(member2);

        List<Member> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);

    }



}
