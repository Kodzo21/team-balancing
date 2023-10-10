import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kodzo21.teambalancing.assigment.Assigner;
import org.kodzo21.teambalancing.assigment.EqualSizeTeamAssigner;
import org.kodzo21.teambalancing.exception.UnbalancedTeamsException;
import org.kodzo21.teambalancing.model.Member;
import org.kodzo21.teambalancing.model.Team;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EqualSizeTeamAssignerTest {

    private Assigner assigner;

    @BeforeEach
    public void setUp() {
        assigner = new EqualSizeTeamAssigner();
    }


    @Test
    public void shouldAssignMembersToTeams() {
        //given
        List<Member> memberList = new ArrayList<>();
        memberList.add(new Member("Scarlet", 5));
        memberList.add(new Member("Johnny", 8));
        memberList.add(new Member("Robbie", 5));
        memberList.add(new Member("Juliet", 3));
        memberList.add(new Member("Jude", 9));
        memberList.add(new Member("Deborah", 6));

        List<Team> teams = new ArrayList<>();
        teams.add(new Team());
        teams.add(new Team());
        teams.add(new Team());

        //when
        List<Team> balancedTeams = assigner.assign(memberList, teams);
        //then
        assertNotNull(balancedTeams);
        assertEquals(3, balancedTeams.size());
    }


    @Test
    public void shouldThrowExceptionWhenNumberOfMembersIsNotDivisibleByNumberOfTeams() {
        //given
        List<Member> memberList = new ArrayList<>();
        memberList.add(new Member("Scarlet", 5));
        memberList.add(new Member("Johnny", 8));
        memberList.add(new Member("Robbie", 5));
        memberList.add(new Member("Juliet", 3));
        memberList.add(new Member("Jude", 9));

        List<Team> teams = new ArrayList<>();
        teams.add(new Team());
        teams.add(new Team());
        teams.add(new Team());

        //when
        final Exception e = assertThrows(UnbalancedTeamsException.class, () -> assigner.assign(memberList, teams));
        //then
        assertEquals("Number of members must be divisible by number of teams", e.getMessage());
    }

}
