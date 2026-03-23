package org.codewithmagret.rest.member;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * REST controller for Member endpoints.
 */
@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    /**
     * Constructs a MemberController.
     *
     * @param memberService member service
     */
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * Creates a new member.
     *
     * @param member member request body
     * @return saved member
     */
    @PostMapping
    public Member createMember(@RequestBody Member member) {
        return memberService.createMember(member);
    }

    /**
     * Returns all members.
     *
     * @return list of members
     */
    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    /**
     * Returns a member by ID.
     *
     * @param id member ID
     * @return matching member
     */
    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    /**
     * Searches members by name.
     *
     * @param name member name
     * @return list of matching members
     */
    @GetMapping("/search/name")
    public List<Member> searchByName(@RequestParam String name) {
        return memberService.searchByName(name);
    }

    /**
     * Searches members by membership type.
     *
     * @param type membership type
     * @return list of matching members
     */
    @GetMapping("/search/type")
    public List<Member> searchByMembershipType(@RequestParam String type) {
        return memberService.searchByMembershipType(type);
    }

    /**
     * Searches members by phone number.
     *
     * @param phone phone number
     * @return list of matching members
     */
    @GetMapping("/search/phone")
    public List<Member> searchByPhoneNumber(@RequestParam String phone) {
        return memberService.searchByPhoneNumber(phone);
    }

    /**
     * Searches members by tournament start date.
     *
     * @param startDate tournament start date
     * @return list of matching members
     */
    @GetMapping("/search/tournament-date")
    public List<Member> searchByTournamentStartDate(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate
    ) {
        return memberService.searchByTournamentStartDate(startDate);
    }

    /**
     * Updates an existing member.
     *
     * @param id member ID
     * @param member updated member data
     * @return updated member
     */
    @PutMapping("/{id}")
    public Member updateMember(@PathVariable Long id, @RequestBody Member member) {
        return memberService.updateMember(id, member);
    }

    /**
     * Deletes a member by ID.
     *
     * @param id member ID
     */
    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }
}