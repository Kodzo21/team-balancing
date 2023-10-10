package org.kodzo21.teambalancing.balancer;

import org.kodzo21.teambalancing.model.Member;
import org.kodzo21.teambalancing.model.Team;

import java.util.List;

public interface Balancer {

    List<Team> balance(List<Member> members, int numberOfTeams);
    double calculateStandardDeviation(List<Team> teams);
}
