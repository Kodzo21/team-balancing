package org.kodzo21.teambalancing.service;

import org.kodzo21.teambalancing.model.Member;
import org.kodzo21.teambalancing.model.Team;

public class TeamService {

    private static TeamService instance;

    private TeamService() {
    }

    public static TeamService getInstance() {
        if (instance == null) {
            instance = new TeamService();
        }
        return instance;
    }

    public double getAverageRate(Team team) {
        return team.getMembers().stream()
                .mapToDouble(Member::rate)
                .average()
                .orElse(0);
    }

    public String getTeamMembersNames(Team team) {
        StringBuilder names = new StringBuilder();
        for (Member member : team.getMembers()) {
            names.append(member.name()).append(" ");
        }
        if (!names.isEmpty())
            names.deleteCharAt(names.length() - 1); // unnecessary space at the end
        return names.toString();
    }

    public int getNumberOfMembers(Team team) {
        return team.getMembers().size();
    }

    public String getTeamInfo(Team team) {
        return getNumberOfMembers(team) + " players: (" + getTeamMembersNames(team) + ") with average rate: " + getAverageRate(team);
    }

    public void addMember(Team team, Member member) {
        team.getMembers().add(member);
    }

}
