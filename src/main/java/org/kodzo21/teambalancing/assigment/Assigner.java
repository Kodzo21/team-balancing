package org.kodzo21.teambalancing.assigment;

import org.kodzo21.teambalancing.model.Member;
import org.kodzo21.teambalancing.model.Team;

import java.util.List;

public interface Assigner {
    List<Team> assign(List<Member> members, int numberOfTeams);
}
