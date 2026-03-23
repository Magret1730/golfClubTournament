package org.codewithmagret.rest.tournament;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * REST controller for Tournament endpoints.
 */
@RestController
@RequestMapping("/tournaments")
public class TournamentController {

    private final TournamentService tournamentService;

    /**
     * Constructs a TournamentController.
     *
     * @param tournamentService tournament service
     */
    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    /**
     * Creates a new tournament.
     *
     * @param tournament tournament request body
     * @return saved tournament
     */
    @PostMapping
    public Tournament createTournament(@RequestBody Tournament tournament) {
        return tournamentService.createTournament(tournament);
    }

    /**
     * Returns all tournaments.
     *
     * @return list of tournaments
     */
    @GetMapping
    public List<Tournament> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }

    /**
     * Returns a tournament by ID.
     *
     * @param id tournament ID
     * @return matching tournament
     */
    @GetMapping("/{id}")
    public Tournament getTournamentById(@PathVariable Long id) {
        return tournamentService.getTournamentById(id);
    }

    /**
     * Adds a member to a tournament.
     *
     * @param tournamentId tournament ID
     * @param memberId member ID
     * @return updated tournament
     */
    @PostMapping("/{tournamentId}/members/{memberId}")
    public Tournament addMemberToTournament(@PathVariable Long tournamentId, @PathVariable Long memberId) {
        return tournamentService.addMemberToTournament(tournamentId, memberId);
    }

    /**
     * Removes a member from a tournament.
     *
     * @param tournamentId tournament ID
     * @param memberId member ID
     * @return updated tournament
     */
    @DeleteMapping("/{tournamentId}/members/{memberId}")
    public Tournament removeMemberFromTournament(@PathVariable Long tournamentId, @PathVariable Long memberId) {
        return tournamentService.removeMemberFromTournament(tournamentId, memberId);
    }

    /**
     * Searches tournaments by start date.
     *
     * @param startDate tournament start date
     * @return list of matching tournaments
     */
    @GetMapping("/search/date")
    public List<Tournament> searchByStartDate(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate
    ) {
        return tournamentService.searchByStartDate(startDate);
    }

    /**
     * Searches tournaments by location.
     *
     * @param location tournament location
     * @return list of matching tournaments
     */
    @GetMapping("/search/location")
    public List<Tournament> searchByLocation(@RequestParam String location) {
        return tournamentService.searchByLocation(location);
    }

    /**
     * Searches tournaments by member ID.
     *
     * @param memberId member ID
     * @return list of matching tournaments
     */
    @GetMapping("/search/member/{memberId}")
    public List<Tournament> searchByMember(@PathVariable Long memberId) {
        return tournamentService.searchByMember(memberId);
    }

    /**
     * Updates an existing tournament.
     *
     * @param id tournament ID
     * @param tournament updated tournament data
     * @return updated tournament
     */
    @PutMapping("/{id}")
    public Tournament updateTournament(@PathVariable Long id, @RequestBody Tournament tournament) {
        return tournamentService.updateTournament(id, tournament);
    }

    /**
     * Deletes a tournament by ID.
     *
     * @param id tournament ID
     */
    @DeleteMapping("/{id}")
    public void deleteTournament(@PathVariable Long id) {
        tournamentService.deleteTournament(id);
    }
}