package org.kodzo21.teambalancing.model;

import java.util.ArrayList;
import java.util.List;

public class Team{
    private final List<Member> members;

    public Team(){
        this.members = new ArrayList<>();
    }

    public Team(List<Member> members){
        this.members = members;
    }

    public List<Member> getMembers(){
        return members;
    }
}

