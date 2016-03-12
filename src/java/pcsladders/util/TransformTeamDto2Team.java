package pcsladders.util;


import pcsladders.team.Team;

import java.util.ArrayList;
import java.util.List;

public class TransformTeamDto2Team {
    public static List<Team> transformTeamDtoList2TeamList(List<dto.Team.Team> teamsDto) {
        List<Team> teams = new ArrayList<Team>();
        for(dto.Team.Team teamDto : teamsDto){
            Team team = new Team();
            team.setTeamId(teamDto.getFullId());
            team.setName(teamDto.getName());
            team.setTag(teamDto.getTag());
            team.setStatus("School");
            team.setCreateDate(teamDto.getCreateDate());
            team.setAddDate(System.currentTimeMillis());
            teams.add(team);
        }
        return teams;
    }
}
