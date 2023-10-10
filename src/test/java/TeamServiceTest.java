import org.junit.jupiter.api.Test;
import org.kodzo21.teambalancing.model.Member;
import org.kodzo21.teambalancing.model.Team;
import org.kodzo21.teambalancing.service.TeamService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeamServiceTest {

    private final TeamService teamService = TeamService.getInstance();

    @Test
    public void shouldReturnAverageRate() {
        //given
        Team team = new Team();
        teamService.addMember(team, new Member("Scarlet", 5));
        teamService.addMember(team, new Member("Johnny", 8));
        teamService.addMember(team, new Member("Robbie", 5));
        //when
        double averageRate = teamService.getAverageRate(team);
        //then
        assertEquals(6, averageRate);
    }

    @Test
    public void shouldReturnTeamPlayersNames() {
        //given
        Team team = new Team();
        teamService.addMember(team, new Member("Scarlet", 5));
        teamService.addMember(team, new Member("Johnny", 8));
        teamService.addMember(team, new Member("Robbie", 5));
        //when
        String teamPlayersNames = teamService.getTeamMembersNames(team);
        //then
        assertEquals("Scarlet Johnny Robbie", teamPlayersNames);
    }

    @Test
    public void shouldReturnTeamInfo() {
        //given
        Team team = new Team();
        teamService.addMember(team, new Member("Scarlet", 5));
        teamService.addMember(team, new Member("Johnny", 8));
        teamService.addMember(team, new Member("Robbie", 5));
        //when
        String teamInfo = teamService.getTeamInfo(team);
        //then
        assertEquals("3 players: (Scarlet Johnny Robbie) with average rate: 6.0", teamInfo);
    }

    @Test
    public void shouldReturnNumberOfPlayers() {
        //given
        Team team = new Team();
        teamService.addMember(team, new Member("Scarlet", 5));
        teamService.addMember(team, new Member("Johnny", 8));
        teamService.addMember(team, new Member("Robbie", 5));
        //when
        int numberOfPlayers = teamService.getNumberOfMembers(team);
        //then
        assertEquals(3, numberOfPlayers);
    }


}
