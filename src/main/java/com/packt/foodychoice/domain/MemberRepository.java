package com.packt.foodychoice.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<Member, String> {
	//null예외를 막기 위해 ostional을 반환해줌(값이 없어도 null이 발생하지 않음)
	Optional<Member> findByUserid(String userid);

}
