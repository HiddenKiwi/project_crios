package pcsladders.team

import grails.transaction.Transactional
import main.java.riotapi.RiotApiException
import pcsladders.riotapi.RiotApiService
import pcsladders.util.AddResult

@Transactional(readOnly = true)
class TeamController {

    RiotApiService riotApiService
    TeamService teamService
    List<Team> summonerTeams

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        params.sort = "name"
        respond Team.list(params), model:[teamInstanceCount: Team.count()]
    }

    def manage() {
        params.sort = "name"
        [teams: Team.list(params)]
    }

    def remove(Team team) {
        if(team != null) {
            teamService.delete(team)
        }
        redirect(action: "manage")
    }

    def statut(Team team) {
        if(team != null) {
            teamService.changeStatus(team)
        }
        redirect(action: "manage")
    }

    def show(Team teamInstance) {
        respond teamInstance
    }

    def search() {
        summonerTeams = new ArrayList<Team>()
        render(view: 'add',  model: [summonerTeams: summonerTeams])
    }

    def summoner() {
        String summonername = params.summonerName
        if(summonername != null) {
            try {
                summonerTeams = riotApiService.getSummonerTeams(summonername)
                render(view: 'add', model: [summonerTeams: summonerTeams])
            }
            catch (RiotApiException e) {
                render(view: 'add', model: [searchHasErrors: true, code: e.getErrorCode(), summonerName: summonername])
            }
        }
        else {
            render(view: 'add', model: [searchNull: true])
        }
    }

    def add() {
        if(summonerTeams != null) {
            def ids = params.teamsToAdd
            List<Team> teamsToAdd = teamService.findTeamsByIdArray(summonerTeams, ids)
            List<AddResult> addResults = teamService.addTeams(teamsToAdd)
            render(view: 'add', model: [addResults: addResults])
        }
    }
}
