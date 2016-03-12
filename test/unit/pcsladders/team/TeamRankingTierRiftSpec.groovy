package pcsladders.team

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(TeamRankingTierRift)
class TeamRankingTierRiftSpec extends Specification {

    TeamRankingTierRift teamRankingTierRift
    TeamRankingTierRift anotherTeamRankingTierRift

    def setup() {
        teamRankingTierRift = new TeamRankingTierRift()
        anotherTeamRankingTierRift = new TeamRankingTierRift()
    }

    void "test des contraintes sur un classement tier 5c5 valide"() {
        given: "Un classement tier 5c5 valide"
        teamRankingTierRift.team = aTeam
        teamRankingTierRift.currentPosition = aCurrentPosition
        teamRankingTierRift.lastPosition = aLastPosition

        when: "On declenche la validite de ce classement"
        def res = teamRankingTierRift.validate()

        then: "Le classement n'a pas d'erreurs de validite"
        res == true
        !teamRankingTierRift.hasErrors()

        where:
        aTeam       | aCurrentPosition | aLastPosition
        Mock(Team) | 1                | 3
        Mock(Team) | 3                | 0
        Mock(Team) | 5                | 6
    }

    void "test des contraintes sur un classement tier 5c5 invalide"() {
        given: "Un classement tier 5c5 invalide"
        teamRankingTierRift.team = aTeam
        teamRankingTierRift.currentPosition = aCurrentPosition
        teamRankingTierRift.lastPosition = aLastPosition

        when: "On declenche la validite de ce classement"
        def res = teamRankingTierRift.validate()

        then: "Le classement a des erreurs de validite"
        res == false
        teamRankingTierRift.hasErrors()

        where:
        aTeam      | aCurrentPosition | aLastPosition
        null       | 1                | 3
        Mock(Team) | 0                | 3
        Mock(Team) | -10              | 1
        Mock(Team) | 5                | -5
    }

    void "test de la methode toString sur un classement tier 5c5 valide"() {
        given: "Un classement tier 5c5 valide"
        teamRankingTierRift.team = aTeam
        teamRankingTierRift.currentPosition = aCurrentPosition
        teamRankingTierRift.lastPosition = aLastPosition

        when: "On declenche la methode toString sur ce classement"
        def res = teamRankingTierRift.toString()

        then: "Le resultat correspond exactement a la chaine de caractere suivante"
        res.equals("1")

        where:
        aTeam      | aCurrentPosition | aLastPosition
        Mock(Team) | 1                | 3
    }

    void "test de la methode equals sur deux classements tier 5c5 valides"() {
        given: "Deux classements tier 5c5 valides"
        teamRankingTierRift.team = aTeam
        teamRankingTierRift.currentPosition = aCurrentPosition
        teamRankingTierRift.lastPosition = aLastPosition

        anotherTeamRankingTierRift.team = aTeam
        anotherTeamRankingTierRift.currentPosition = aCurrentPosition
        anotherTeamRankingTierRift.lastPosition = aLastPosition

        when: "On declenche la methode equals sur ces classements"
        def res = teamRankingTierRift.equals(anotherTeamRankingTierRift)

        then: "Les deux classements sont identiques"
        res == true

        where:
        aTeam      | aCurrentPosition | aLastPosition
        Mock(Team) | 1                | 3
    }

    void "test de la methode hashCode sur un classement tier 5c5 valide"() {
        given: "Un classement tier 5c5 valide"
        teamRankingTierRift.team = aTeam
        teamRankingTierRift.currentPosition = aCurrentPosition
        teamRankingTierRift.lastPosition = aLastPosition

        when: "On declenche deux fois la methode hashCode sur ce classement"
        def res1 = teamRankingTierRift.hashCode()
        def res2 = teamRankingTierRift.hashCode()

        then: "On obtient le meme resultat"
        res1 == res2

        where:
        aTeam      | aCurrentPosition | aLastPosition
        Mock(Team) | 1                | 3
    }
}
