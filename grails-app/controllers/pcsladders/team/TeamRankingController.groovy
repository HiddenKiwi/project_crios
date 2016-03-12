package pcsladders.team

import grails.transaction.Transactional

@Transactional(readOnly = true)
class TeamRankingController {

    TeamService teamService

    def indexTitulaireTierRift(){
        def teams = teamService.findTeamsTitulaire()
        render model: [teams: teams], view: "indexTitulaireTierRift"
    }

    def indexSchoolTierRift(){
        def teams = teamService.findTeamsSchool()
        render model: [teams: teams], view: "indexSchoolTierRift"
    }
}
