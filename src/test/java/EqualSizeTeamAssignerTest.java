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

        //when
        List<Team> balancedTeams = assigner.assign(memberList, 3);
        //then
        assertNotNull(balancedTeams);
        assertEquals(3, balancedTeams.size());
        assertEquals(2, balancedTeams.get(0).getMembers().size());
        assertEquals(2, balancedTeams.get(1).getMembers().size());
        assertEquals(2, balancedTeams.get(2).getMembers().size());
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

        //when
        final Exception e = assertThrows(UnbalancedTeamsException.class, () -> assigner.assign(memberList, 3));
        //then
        assertEquals("Number of members must be divisible by number of teams", e.getMessage());
    }

}
