package org.kodzo21.teambalancing.balancer;

import org.kodzo21.teambalancing.model.Member;
import org.kodzo21.teambalancing.model.Team;
import org.kodzo21.teambalancing.assigment.Assigner;
import org.kodzo21.teambalancing.assigment.EqualSizeTeamAssigner;
import org.kodzo21.teambalancing.exception.InvalidInputException;
import org.kodzo21.teambalancing.service.TeamService;

import java.util.List;

public class TeamBalancer implements Balancer {

    private final TeamService teamService = TeamService.getInstance();

    @Override
    public List<Team> balance(List<Member> members, int numberOfTeams) {
        validateInput(members, numberOfTeams);

        Assigner assigner = new EqualSizeTeamAssigner();
        var teams = assigner.assign(members, numberOfTeams);

        printTeamsDetails(teams);
        return teams;
    }

    private void validateInput(List<Member> members, int numberOfTeams) {
        if (members == null || members.isEmpty()) {
            throw new InvalidInputException("Number of members must be greater than 0");
        }
        if (numberOfTeams <= 0) {
            throw new InvalidInputException("Number of teams must be greater than 0");
        }
    }

    @Override
    public double calculateStandardDeviation(List<Team> teams) {
        if (teams == null || teams.isEmpty()) {
            throw new InvalidInputException("Number of teams must be greater than 0");
        }

        double average = calculateTeamsAverageRate(teams);
        double sumOfSquares = teams.stream()
                .mapToDouble(team -> Math.pow(teamService.getAverageRate(team) - average, 2))
                .sum();

        return Math.sqrt(sumOfSquares / teams.size());
    }

    private double calculateTeamsAverageRate(List<Team> teams) {
        return teams.stream()
                .mapToDouble(teamService::getAverageRate)
                .average()
                .orElse(0);
    }

    private void printTeamsDetails(List<Team> teams) {
        for (Team team: teams) {
            System.out.println("Team no " + (teams.indexOf(team)+1) + ": " + teamService.getTeamInfo(team));
        }
        System.out.println("Teams rate standard deviation: " + String.format("%.2f", calculateStandardDeviation(teams)));
    }
}
