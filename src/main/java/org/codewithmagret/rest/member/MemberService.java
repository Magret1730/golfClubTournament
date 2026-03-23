package org.codewithmagret.rest.member;

import org.codewithmagret.rest.tournament.Tournament;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Service class for Member business logic.
 */
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * Constructs a MemberService.
     *
     * @param memberRepository member repository
     */
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * Creates and saves a new member.
     *
     * @param member member to save
     * @return saved member
     */
    public Member createMember(Member member) {
        if (member == null) {
            throw new IllegalArgumentException("member is empty");
        }

        if (member.getEmail() == null || member.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }

        Member existing = memberRepository.existsByEmail(member.getEmail()) ? member : null;
        if (existing != null) {
            throw new IllegalArgumentException("member email already exists");
        }

        return memberRepository.save(member);
    }

    /**
     * Returns all members.
     *
     * @return list of members
     */
    public List<Member> getAllMembers() {
        if (memberRepository.findAll().isEmpty()) {
            throw new IllegalArgumentException("Member list is empty");
        }
        return memberRepository.findAll();
    }

    /**
     * Returns a member by ID.
     *
     * @param id member ID
     * @return matching member
     */
    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + id));
    }

    /**
     * Searches members by name.
     *
     * @param name member name
     * @return list of matching members
     */
    public List<Member> searchByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name is empty");
        }
        return memberRepository.findByNameContainingIgnoreCase(name);
    }

    /**
     * Searches members by membership type.
     *
     * @param membershipType membership type
     * @return list of matching members
     */
    public List<Member> searchByMembershipType(String membershipType) {
        if (membershipType == null || membershipType.isEmpty()) {
            throw new IllegalArgumentException("membershipType is empty");
        }
        return memberRepository.findByMembershipTypeIgnoreCase(membershipType);
    }

    /**
     * Searches members by phone number.
     *
     * @param phoneNumber phone number
     * @return list of matching members
     */
    public List<Member> searchByPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("phoneNumber is empty");
        }
        return memberRepository.findByPhoneNumber(phoneNumber);
    }

    /**
     * Searches members by tournament start date.
     *
     * @param startDate tournament start date
     * @return list of matching members
     */
    public List<Member> searchByTournamentStartDate(LocalDate startDate) {
        if (startDate == null || startDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("startDate is empty");
        }
        return memberRepository.findMembersByTournamentStartDate(startDate);
    }

    /**
     * Updates an existing member.
     *
     * @param id member ID
     * @param updatedMember updated member data
     * @return updated member
     */
    public Member updateMember(Long id, Member updatedMember) {
        Member existingMember = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + id));

        existingMember.setName(updatedMember.getName());
        existingMember.setAddress(updatedMember.getAddress());
        existingMember.setEmail(updatedMember.getEmail());
        existingMember.setMembershipType(updatedMember.getMembershipType());
        existingMember.setPhoneNumber(updatedMember.getPhoneNumber());
        existingMember.setMembershipStartDate(updatedMember.getMembershipStartDate());
        existingMember.setMembershipDuration(updatedMember.getMembershipDuration());

        return memberRepository.save(existingMember);
    }

    /**
     * Deletes a member by ID.
     *
     * @param id member ID
     */
    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + id));

        for (Tournament tournament : Set.copyOf(member.getTournaments())) {
            tournament.removeMember(member);
        }
        memberRepository.delete(member);
    }
}
