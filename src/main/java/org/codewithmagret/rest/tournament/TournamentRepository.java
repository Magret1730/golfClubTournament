package org.codewithmagret.rest.tournament;

import org.codewithmagret.rest.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository for Tournament entity operations.
 */
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    /**
     * Finds tournaments by start date.
     *
     * @param startDate tournament start date
     * @return list of matching tournaments
     */
    List<Tournament> findByStartDate(LocalDate startDate);

    /**
     * Finds tournaments whose location contains the given value, ignoring case.
     *
     * @param location location
     * @return list of matching tournaments
     */
    List<Tournament> findByLocationContainingIgnoreCase(String location);

    /**
     * Finds tournaments containing the given member.
     *
     * @param member member
     * @return list of matching tournaments
     */
    List<Tournament> findByMembersContaining(Member member);

    /**
     * Checks if a tournament exists with the given start date and location.
     *
     * @param startDate tournament start date
     * @param location tournament location
     * @return true if exists, false otherwise
     */
    boolean existsByStartDateAndLocationIgnoreCase(LocalDate startDate, String location);
}