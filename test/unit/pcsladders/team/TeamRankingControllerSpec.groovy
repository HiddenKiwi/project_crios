package pcsladders.team

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(TeamRankingController)
@Mock(TeamService)
class TeamRankingControllerSpec extends Specification {

    void "Test - L'action indexTeamsTitulaireTierRift retourne le bon modele"() {
        given: "Le mock de la classe TeamService"
        def teamService = mockFor(TeamService)
        teamService.demand.findTeamsTitulaire(1) {
            return Team.findAllByStatus("Titulaire")
        }
        controller.teamService = teamService.createMock()

        when: "L'action indexTeamsTitulaireTierRift est executee"
        controller.indexTitulaireTierRift()

        then: "Le modele contient l'instance du domaine transmis"
        view == "/teamRanking/indexTitulaireTierRift"
        model.teams != null
    }

    void "Test - L'action indexTeamsSchoolTierRift retourne le bon modele"() {
        given: "Le mock de la classe TeamService"
        def teamService = mockFor(TeamService)
        teamService.demand.findTeamsSchool(1) {
            return Team.findAllByStatus("Titulaire")
        }
        controller.teamService = teamService.createMock()

        when: "L'action indexTeamsSchoolTierRift est executee"
        controller.indexSchoolTierRift()

        then: "Le modele contient l'instance du domaine transmis"
        view == "/teamRanking/indexSchoolTierRift"
        model.teams != null
    }
}
