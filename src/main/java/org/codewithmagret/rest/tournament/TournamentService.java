package org.codewithmagret.rest.tournament;

import org.codewithmagret.rest.member.Member;
import org.codewithmagret.rest.member.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Service class for Tournament business logic.
 */
@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final MemberRepository memberRepository;

    /**
     * Constructs a TournamentService.
     *
     * @param tournamentRepository tournament repository
     * @param memberRepository member repository
     */
    public TournamentService(TournamentRepository tournamentRepository, MemberRepository memberRepository) {
        this.tournamentRepository = tournamentRepository;
        this.memberRepository = memberRepository;
    }

    /**
     * Creates and saves a new tournament.
     *
     * @param tournament tournament to save
     * @return saved tournament
     */
    public Tournament createTournament(Tournament tournament) {
        if (tournament == null) {
            throw new IllegalArgumentException("tournament is null");
        }

        if (tournament.getStartDate() == null || tournament.getLocation() == null) {
            throw new IllegalArgumentException("Start date and location are required");
        }

        boolean exists = tournamentRepository.existsByStartDateAndLocationIgnoreCase(
                tournament.getStartDate(),
                tournament.getLocation()
        );

        if (exists) {
            throw new IllegalArgumentException("Tournament already exists with same start date and location");
        }

        return tournamentRepository.save(tournament);
    }

    /**
     * Returns all tournaments.
     *
     * @return list of tournaments
     */
    public List<Tournament> getAllTournaments() {
        if (tournamentRepository.findAll().isEmpty()) {
            throw new IllegalArgumentException("tournament is empty");
        }
        return tournamentRepository.findAll();
    }

    /**
     * Returns a tournament by ID.
     *
     * @param id tournament ID
     * @return matching tournament
     */
    public Tournament getTournamentById(Long id) {
        return tournamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tournament not found with id: " + id));
    }

    /**
     * Adds a member to a tournament.
     *
     * @param tournamentId tournament ID
     * @param memberId member ID
     * @return updated tournament
     */
    public Tournament addMemberToTournament(Long tournamentId, Long memberId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new RuntimeException("Tournament not found with id: " + tournamentId));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + memberId));

        tournament.addMember(member);

        return tournamentRepository.save(tournament);
    }

    /**
     * Removes a member from a tournament.
     *
     * @param tournamentId tournament ID
     * @param memberId member ID
     * @return updated tournament
     */
    public Tournament removeMemberFromTournament(Long tournamentId, Long memberId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new RuntimeException("Tournament not found with id: " + tournamentId));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + memberId));

        tournament.removeMember(member);

        return tournamentRepository.save(tournament);
    }

    /**
     * Searches tournaments by start date.
     *
     * @param startDate tournament start date
     * @return list of matching tournaments
     */
    public List<Tournament> searchByStartDate(LocalDate startDate) {
        return tournamentRepository.findByStartDate(startDate);
    }

    /**
     * Searches tournaments by location.
     *
     * @param location tournament location
     * @return list of matching tournaments
     */
    public List<Tournament> searchByLocation(String location) {
        return tournamentRepository.findByLocationContainingIgnoreCase(location);
    }

    /**
     * Searches tournaments by member ID.
     *
     * @param memberId member ID
     * @return list of matching tournaments
     */
    public List<Tournament> searchByMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + memberId));

        return tournamentRepository.findByMembersContaining(member);
    }

    /**
     * Updates an existing tournament.
     *
     * @param id tournament ID
     * @param updatedTournament updated tournament data
     * @return updated tournament
     */
    public Tournament updateTournament(Long id, Tournament updatedTournament) {
        Tournament existingTournament = tournamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tournament not found with id: " + id));

        existingTournament.setStartDate(updatedTournament.getStartDate());
        existingTournament.setEndDate(updatedTournament.getEndDate());
        existingTournament.setLocation(updatedTournament.getLocation());
        existingTournament.setEntryFee(updatedTournament.getEntryFee());
        existingTournament.setPrize(updatedTournament.getPrize());

        return tournamentRepository.save(existingTournament);
    }

    /**
     * Deletes a tournament by ID.
     *
     * @param id tournament ID
     */
    public void deleteTournament(Long id) {
        Tournament tournament = tournamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tournament not found with id: " + id));

        for (Member member : Set.copyOf(tournament.getMembers())) {
            tournament.removeMember(member);
        }

        tournamentRepository.delete(tournament);
    }
}