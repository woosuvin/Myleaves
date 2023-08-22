package com.itwill.myleaves.repository.member;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, String> {

	Optional<Member> findByEmail(String email);
	
	Member findByUserId(String userId);

	Member findByNameAndEmail(String name, String email);

	@Query(value = "SELECT * " + "FROM (SELECT rownum AS rn, u.* " + "FROM user_info u) sub "
			+ "WHERE sub.rn > (:pageNum - 1) * :amount AND sub.rn <= :pageNum * :amount "
			+ "ORDER BY sub.join_date DESC", nativeQuery = true)
	List<Member> readWithPaging(@Param("pageNum") int pageNum, @Param("amount") int amount);

	///

	@Query(value = "SELECT * " + "FROM (SELECT rownum AS rn, u.* " + "FROM user_info u WHERE u.name = :keyword) sub "
			+ "WHERE sub.rn > (:pageNum - 1) * :amount AND sub.rn <= :pageNum * :amount "
			+ "ORDER BY sub.join_date DESC", nativeQuery = true)
	List<Member> readWithPagingByName(@Param("pageNum") int pageNum, @Param("amount") int amount,
			@Param("keyword") String keyword);

	@Query(value = "SELECT * " + "FROM (SELECT rownum AS rn, u.* "
			+ "FROM user_info u WHERE u.birth = TO_NUMBER(:keyword)) sub "
			+ "WHERE sub.rn > (:pageNum - 1) * :amount AND sub.rn <= :pageNum * :amount "
			+ "ORDER BY sub.join_date DESC", nativeQuery = true)
	List<Member> readWithPagingByBirth(@Param("pageNum") int pageNum, @Param("amount") int amount,
			@Param("keyword") String keyword);

	@Query(value = "SELECT * " + "FROM (SELECT rownum AS rn, u.* " + "FROM user_info u WHERE u.gender = :keyword) sub "
			+ "WHERE sub.rn > (:pageNum - 1) * :amount AND sub.rn <= :pageNum * :amount "
			+ "ORDER BY sub.join_date DESC", nativeQuery = true)
	List<Member> readWithPagingByGender(@Param("pageNum") int pageNum, @Param("amount") int amount,
			@Param("keyword") String keyword);

	@Query(value = "SELECT * " + "FROM (SELECT rownum AS rn, u.* "
			+ "FROM user_info u WHERE LOWER(u.user_id) LIKE LOWER('%' || :keyword || '%')) sub "
			+ "WHERE sub.rn > (:pageNum - 1) * :amount AND sub.rn <= :pageNum * :amount "
			+ "ORDER BY sub.join_date DESC", nativeQuery = true)
	List<Member> readByUserId(@Param("pageNum") int pageNum, @Param("amount") int amount,
			@Param("keyword") String keyword);

	@Query(value = "SELECT * " + "FROM (SELECT rownum AS rn, u.* " + "FROM user_info u WHERE u.phone = :keyword) sub "
			+ "WHERE sub.rn > (:pageNum - 1) * :amount AND sub.rn <= :pageNum * :amount "
			+ "ORDER BY sub.join_date DESC", nativeQuery = true)
	List<Member> readByPhone(@Param("pageNum") int pageNum, @Param("amount") int amount,
			@Param("keyword") String keyword);

	@Query(value = "SELECT * " + "FROM (SELECT rownum AS rn, u.* "
			+ "FROM user_info u WHERE LOWER(u.email) LIKE LOWER('%' || :keyword || '%')) sub "
			+ "WHERE sub.rn > (:pageNum - 1) * :amount AND sub.rn <= :pageNum * :amount "
			+ "ORDER BY sub.join_date DESC", nativeQuery = true)
	List<Member> readByEmail(@Param("pageNum") int pageNum, @Param("amount") int amount,
			@Param("keyword") String keyword);

	// 사이즈 --------------------------------------------
		
	int countByName(String name);

	int countByUserId(String userId);

	int countByPhone(String phone);

	int countByEmail(String email);

	int countByBirth(String birth);

	int countByGender(String gender);

}