import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kodzo21.teambalancing.balancer.TeamBalancer;
import org.kodzo21.teambalancing.exception.InvalidInputException;
import org.kodzo21.teambalancing.model.Member;
import org.kodzo21.teambalancing.model.Team;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class TeamBalancerTest {

    private TeamBalancer teamBalancer;

    @BeforeEach
    public void setUp() {
        teamBalancer = new TeamBalancer();
    }


    @Test
    void shouldThrowExceptionWhenNumberOfTeamsIsZero() {
        //given
        List<Member> memberList = new ArrayList<>();
        memberList.add(new Member("Scarlet", 5));
        memberList.add(new Member("Johnny", 8));
        memberList.add(new Member("Robbie", 5));
        memberList.add(new Member("Juliet", 3));
        memberList.add(new Member("Jude", 9));
        memberList.add(new Member("Deborah", 6));

        int numberOfTeams = 0;
        //when
        final Exception e = assertThrows(InvalidInputException.class, () -> teamBalancer.balance(memberList, numberOfTeams));
        //then
        assertEquals("Number of teams must be greater than 0", e.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNumberOfMembersIsZero() {
        //given
        List<Member> memberList = new ArrayList<>();
        int numberOfTeams = 3;
        //when
        final Exception e = assertThrows(InvalidInputException.class, () -> teamBalancer.balance(memberList, numberOfTeams));
        //then
        assertEquals("Number of members must be greater than 0", e.getMessage());
    }

    @Test
    void shouldReturnBalancedTeams() {
        //given
        List<Member> memberList = new ArrayList<>();
        memberList.add(new Member("Scarlet", 5));
        memberList.add(new Member("Johnny", 8));
        memberList.add(new Member("Robbie", 5));
        memberList.add(new Member("Juliet", 3));
        memberList.add(new Member("Jude", 9));
        memberList.add(new Member("Deborah", 6));

        int numberOfTeams = 3;
        //when
        List<Team> teams = teamBalancer.balance(memberList, numberOfTeams);
        //then
        assertEquals(3, teams.size());
        assertEquals(2, teams.get(0).getMembers().size());
        assertEquals(2, teams.get(1).getMembers().size());
        assertEquals(2, teams.get(2).getMembers().size());
    }

    @Test
    void shouldReturnTeamsStandardDeviation(){
        //given
        List<Team> teams = new ArrayList<>();

        List<Member> members1 = new ArrayList<>();
        List<Member> members2 = new ArrayList<>();

        Team team1 = new Team(members1);
        Team team2 = new Team(members2);

        members1.add(new Member("Scarlet", 5));
        members1.add(new Member("Johnny", 8));

        members2.add(new Member("Robbie", 5));
        members2.add(new Member("Juliet", 3));

        teams.add(team1);
        teams.add(team2);
        //when
        double standardDeviation = teamBalancer.calculateStandardDeviation(teams);
        //then
        assertEquals(1.25, standardDeviation);
    }

    @Test
    void shouldThrowExceptionWhenTeamsAreEmpty(){
        //given
        List<Team> teams = new ArrayList<>();
        //when
        final Exception e = assertThrows(InvalidInputException.class, () -> teamBalancer.calculateStandardDeviation(teams));
        //then
        assertEquals("Number of teams must be greater than 0", e.getMessage());
    }
}
