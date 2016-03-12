package pcsladders.team


import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(TeamController)
@Mock(Team)
class TeamControllerSpec extends Specification {

    String paramTeamId = "TEAM-4357c190-3c37-11e4-a33e-c81f66db96d8"
    String paramName = "2nigh in PCS"
    String paramStatus = "Titulaire"
    String paramTag = "2PCS"
    long paramCreateDate = 1410742077000L
    long paramAddDate = 1442679300L
    String paramTierRift = "GOLD"
    String paramDivisionRift = "III"
    String paramLeaguePointsRift = 20
    TeamRankingTierRift paramRankingTierRift = Mock(TeamRankingTierRift)

    void "Test - L'action index retourne le bon modele"() {

        when: "L'action index est executee"
        controller.index()

        then: "Le modele est correct"
        !model.teamInstanceList
        model.teamInstanceCount == 0
    }

    void "Test - L'action show retourne le bon modele"() {
        when: "L'action show est executee sur un domaine null"
        controller.show(null)

        then: "Une erreur 404 est retournee"
        response.status == 404

        when: "Une instance valide du domaine est passee a l'action show"
        Team team = new Team(teamId: paramTeamId, name: paramName, status: paramStatus, tag: paramTag, createDate: paramCreateDate, addDate: paramAddDate, tierRift: paramTierRift, divisionRift: paramDivisionRift, leaguePointsRift: paramLeaguePointsRift, rankingTierRift: paramRankingTierRift)
        controller.show(team)

        then: "Le modele contient l'instance du domaine transmis"
        model.teamInstance == team
    }

    void "Test - L'action search retourne le bon modele"() {
        when: "L'action search est executee"
        controller.search()

        then: "Le modele est correct"
        model.summonerTeams != null
        view == '/team/add'
    }

    void "Test - L'action summoner retourne le bon modele avec un nom d'invocateur valide"() {
        given: "Un parametre summonerName recupere et valide"
        controller.params.summonerName = "pcs hidden"

        when: "L'action summoner est executee"
        controller.search()

        then: "Le modele est correct"
        model.summonerTeams != null
        view == '/team/add'
    }
}
