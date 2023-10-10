package org.kodzo21.teambalancing.assigment;

import org.kodzo21.teambalancing.exception.UnbalancedTeamsException;
import org.kodzo21.teambalancing.model.Member;
import org.kodzo21.teambalancing.model.Team;
import org.kodzo21.teambalancing.service.TeamService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EqualSizeTeamAssigner implements Assigner {

    private final TeamService teamService = TeamService.getInstance();

    @Override
    public List<Team> assign(List<Member> members, int numberOfTeams) {
        validateInput(members, numberOfTeams);

        final List<Team> teams = createTeams(numberOfTeams);

        sortMembersByRate(members);

        int teamIndex = 0;
        for (Member member : members) {
            teamService.addMember(teams.get(teamIndex), member);
            teamIndex = (teamIndex + 1) % numberOfTeams;
            if (teamIndex == 0) {
                Collections.reverse(teams);
            }
        }
        return teams;
    }

    private void validateInput(List<Member> members, int numberOfTeams) {
        if (members.size() % numberOfTeams != 0) {
            throw new UnbalancedTeamsException("Number of members must be divisible by number of teams");
        }
    }

    private void sortMembersByRate(List<Member> members) {
        members.sort((p1, p2) -> Double.compare(p2.rate(), p1.rate()));
    }

    private List<Team> createTeams(int numberOfTeams) {
        List<Team> teams = new ArrayList<>();
        for (int i = 0; i < numberOfTeams; i++) {
            teams.add(new Team());
        }
        return teams;
    }
}