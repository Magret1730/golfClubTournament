package org.codewithmagret.rest.tournament;

import jakarta.persistence.*;
import org.codewithmagret.rest.member.Member;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Tournament {
    /**
     * ID of each tournament
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Start date of tournament
     */
    private String startDate;

    /**
     * End date of tournament
     */
    private String endDate;

    /**
     * Location of tournament
     */
    private String location;

    /**
     * Entry fee of tournament
     */
    private double entryFee;

    /**
     * Prize of tournament
     */
    private double prize;

    /**
     * Many-to-many relationship between Member and Tournament.
     * A member can enroll in multiple tournaments, and a tournament can have multiple members.
     * The relationship is managed through a join table named "member_tournament".
     */
    @ManyToMany
    @JoinTable(
            name = "member_tournament",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"member_id", "tournament_id"})
    )
    private Set<Member> members = new HashSet<>();

     /**
     * Default constructor required by JPA.
     * Initializes a new instance of the Tournament class.
     */
    public Tournament() {
    }

    /**
     * Gets ID of tournament
     * @return ID of the tournament
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the start date of tournament
     * @return startDate of the tournament
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Sets the startdate of the tournament
     * @param startDate of the tournament
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets location of the tournament
     * @return location of the tournament
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets location of the tournament
     * @param location of the tournament
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets end date of the tournament
     * @return endDate of the tournament
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Sets end date of the tournament
     * @param endDate of the tournament
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets entry fee of the tournament
     * @return entryFee of the tournament
     */
    public double getEntryFee() {
        return entryFee;
    }

    /**
     * Sets entryFee of the tournament
     * @param entryFee of the tournament
     */
    public void setEntryFee(double entryFee) {
        this.entryFee = entryFee;
    }

    /**
     * Gets prize of the tournament
     * @return prize of the tournament
     */
    public double getPrize() {
        return prize;
    }

    /**
     * Sets prize fo the torunament
     * @param prize of the tournament
     */
    public void setPrize(double prize) {
        this.prize = prize;
    }

    /**
     * Gets the set of participating members.
     *
     * @return set of members
     */
    public Set<Member> getMembers() {
        return members;
    }

    /**
     * Sets the participating members of the tournament.
     *
     * @param members set of members
     */
    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    /**
     * Adds a member to the tournament.
     * Also ensures both sides of the relationship stay in sync.
     *
     * @param member the member to add
     */
    public void addMember(Member member) {
        this.members.add(member);
        member.getTournaments().add(this);
    }

    /**
     * Removes a member from the tournament.
     *
     * @param member the member to remove
     */
    public void removeMember(Member member) {
        this.members.remove(member);
        member.getTournaments().remove(this);
    }

    /**
     * Overrides toString method of the tournament class
     * @return string representation of the tournament object
     */
    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", location='" + location + '\'' +
                ", entryFee=" + entryFee +
                ", prize=" + prize +
                '}';
    }
}

