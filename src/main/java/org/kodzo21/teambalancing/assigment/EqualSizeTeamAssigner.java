package org.kodzo21.teambalancing.assigment;

import org.kodzo21.teambalancing.exception.UnbalancedTeamsException;
import org.kodzo21.teambalancing.model.Member;
import org.kodzo21.teambalancing.model.Team;
import org.kodzo21.teambalancing.service.TeamService;

import java.util.Collections;
import java.util.List;

public class EqualSizeTeamAssigner implements Assigner {
    private final TeamService teamService = TeamService.getInstance();

    @Override
    public List<Team> assign(List<Member> members, List<Team> teams) {
        validateInput(members, teams);

        sortMembersByRate(members);

        //assign members to teams
        final int numberOfTeams = teams.size();
        int teamIndex = 0;
        for (Member member : members) {
            teamService.addPlayer(teams.get(teamIndex), member);
            teamIndex = (teamIndex + 1) % numberOfTeams;
            if (teamIndex == 0) {
                Collections.reverse(teams);
            }
        }
        return teams;
    }

    private void validateInput(List<Member> members, List<Team> teams) {
        //handle unbalanced teams
        if (members.size() % teams.size() != 0) {
            throw new UnbalancedTeamsException("Number of members must be divisible by number of teams");
        }
    }

    private void sortMembersByRate(List<Member> members) {
        //sort members by rate in descending order
        members.sort((p1, p2) -> Double.compare(p2.rate(), p1.rate()));
    }
}