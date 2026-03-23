package org.codewithmagret.rest.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repository for Member entity operations.
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * Finds members whose names contain the given value, ignoring case.
     *
     * @param name member name
     * @return list of matching members
     */
    List<Member> findByNameContainingIgnoreCase(String name);

    /**
     * Finds members by membership type, ignoring case.
     *
     * @param membershipType membership type
     * @return list of matching members
     */
    List<Member> findByMembershipTypeIgnoreCase(String membershipType);

    /**
     * Finds members by phone number.
     *
     * @param phoneNumber phone number
     * @return list of matching members
     */
    List<Member> findByPhoneNumber(String phoneNumber);

    /**
     * Finds members participating in tournaments with the given start date.
     *
     * @param startDate tournament start date
     * @return list of matching members
     */
    @Query("SELECT DISTINCT m FROM Member m JOIN m.tournaments t WHERE t.startDate = :startDate")
    List<Member> findMembersByTournamentStartDate(@Param("startDate") LocalDate startDate);

    /**
     * Finds members by email
     * @param email email of member
     * @return true or false
     */
    boolean existsByEmail(String email);
}
