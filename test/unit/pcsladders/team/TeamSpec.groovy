package pcsladders.team

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(Team)
class TeamSpec extends Specification {

    Team team
    Team anotherTeam

    def setup() {
        team = new Team()
        anotherTeam = new Team()
    }

    void "test des contraintes sur une equipe valide"() {
        given: "Une equipe valide"
        team.teamId = aTeamId
        team.name = aName
        team.status = aStatus
        team.tag = aTag
        team.createDate = aCreateDate
        team.addDate = aAddDate
        team.tierRift = aTierRift
        team.divisionRift = aDivisionRift
        team.leaguePointsRift = aLeaguePointsRift
        team.rankedScore = aRankedScore
        team.winsRift = aWinsRift
        team.loosesRift = aLoosesRift
        team.rankingTierRift = aRankingTierRift

        when: "On declenche la validation sur cette equipe"
        def res = team.validate()

        then: "L'equipe n'a pas d'erreurs de validation"
        res == true
        !team.hasErrors()

        where:
        aTeamId                                     | aName          | aStatus     | aTag   | aCreateDate   | aAddDate   | aTierRift | aDivisionRift | aLeaguePointsRift | aRankedScore | aWinsRift | aLoosesRift | aRankingTierRift
        "TEAM-4357c190-3c37-11e4-a33e-c81f66db96d8" | "2nigh in PCS" | "Titulaire" | "2PCS" | 1410742077000 | 1442679300 | "GOLD"    | "III"         | 20                | 1537         | 37        | 0           | Mock(TeamRankingTierRift)
        "TEAM-4357c190-3c37-11e4-a33e-c81f66db96d8" | "2nigh in PCS" | "School"    | "2PCS" | 1410742077000 | 1442679300 | "GOLD"    | "III"         | 20                | 0            | 0         | 30          | Mock(TeamRankingTierRift)
        "TEAM-4357c190-3c37-11e4-a33e-c81f66db96d8" | "2nigh in PCS" | "School"    | "2PCS" | 1410742077000 | 1442679300 | "GOLD"    | "III"         | 0                 | 7450         | 66        | 55          | Mock(TeamRankingTierRift)
    }

    void "test des contraintes sur une equipe invalide"() {
        given: "Une equipe invalide"
        team.teamId = aTeamId
        team.name = aName
        team.status = aStatus
        team.tag = aTag
        team.createDate = aCreateDate
        team.addDate = aAddDate
        team.tierRift = aTierRift
        team.divisionRift = aDivisionRift
        team.leaguePointsRift = aLeaguePointsRift
        team.rankedScore = aRankedScore
        team.winsRift = aWinsRift
        team.loosesRift = aLoosesRift
        team.rankingTierRift = aRankingTierRift

        when: "On declenche la validation sur cette equipe"
        def res = team.validate()

        then: "L'equipe a des erreurs de validation"
        res == false
        team.hasErrors()

        where:
        aTeamId                                     | aName          | aStatus     | aTag   | aCreateDate    | aAddDate    | aTierRift | aDivisionRift | aLeaguePointsRift | aRankedScore | aWinsRift | aLoosesRift | aRankingTierRift
        ""                                          | "2nigh in PCS" | "Titulaire" | "2PCS" | 1410742077000L | 1442679300L | "GOLD"    | "III"         | 20                | 4223         | 30        | 50          | Mock(TeamRankingTierRift)
        null                                        | "2nigh in PCS" | "School"    | "2PCS" | 1410742077000L | 1442679300L | "GOLD"    | "III"         | 20                | 4223         | 30        | 50          | Mock(TeamRankingTierRift)
        "TEAM-4357c190-3c37-11e4-a33e-c81f66db96d8" | ""             | "School"    | "2PCS" | 1410742077000L | 1442679300L | "GOLD"    | "III"         | 20                | 4223         | 30        | 50          | Mock(TeamRankingTierRift)
        "TEAM-4357c190-3c37-11e4-a33e-c81f66db96d8" | null           | "School"    | "2PCS" | 1410742077000L | 1442679300L | "GOLD"    | "III"         | 20                | 4223         | 30        | 50          | Mock(TeamRankingTierRift)
        "TEAM-4357c190-3c37-11e4-a33e-c81f66db96d8" | "2nigh in PCS" | ""          | "2PCS" | 1410742077000L | 1442679300L | "GOLD"    | "III"         | 20                | 4223         | 30        | 50          | Mock(TeamRankingTierRift)
        "TEAM-4357c190-3c37-11e4-a33e-c81f66db96d8" | "2nigh in PCS" | null        | "2PCS" | 1410742077000L | 1442679300L | "GOLD"    | "III"         | 20                | 4223         | 30        | 50          | Mock(TeamRankingTierRift)
        "TEAM-4357c190-3c37-11e4-a33e-c81f66db96d8" | "2nigh in PCS" | "Another"   | "2PCS" | 1410742077000L | 1442679300L | "GOLD"    | "III"         | 20                | 4223         | 30        | 50          | Mock(TeamRankingTierRift)
        "TEAM-4357c190-3c37-11e4-a33e-c81f66db96d8" | "2nigh in PCS" | "School"    | ""     | 1410742077000L | 1442679300L | "GOLD"    | "III"         | 20                | 4223         | 30        | 50          | Mock(TeamRankingTierRift)
        "TEAM-4357c190-3c37-11e4-a33e-c81f66db96d8" | "2nigh in PCS" | "School"    | null   | 1410742077000L | 1442679300L | "GOLD"    | "III"         | 20                | 4223         | 30        | 50          | Mock(TeamRankingTierRift)
        "TEAM-4357c190-3c37-11e4-a33e-c81f66db96d8" | "2nigh in PCS" | "School"    | "2PCS" | 1410742077000L | 1442679300L | ""        | "III"         | 20                | 4223         | 30        | 50          | Mock(TeamRankingTierRift)
        "TEAM-4357c190-3c37-11e4-a33e-c81f66db96d8" | "2nigh in PCS" | "School"    | "2PCS" | 1410742077000L | 1442679300L | "GOLD"    | ""            | 20                | 4223         | 30        | 50          | Mock(TeamRankingTierRift)
        "TEAM-4357c190-3c37-11e4-a33e-c81f66db96d8" | "2nigh in PCS" | "School"    | "2PCS" | 1410742077000L | 1442679300L | "GOLD"    | "III"         | -20               | 4223         | 30        | 50          | Mock(TeamRankingTierRift)
        "TEAM-4357c190-3c37-11e4-a33e-c81f66db96d8" | "2nigh in PCS" | "School"    | "2PCS" | 1410742077000L | 1442679300L | "GOLD"    | "III"         | 101               | 4223         | 30        | 50          | Mock(TeamRankingTierRift)
        "TEAM-4357c190-3c37-11e4-a33e-c81f66db96d8" | "2nigh in PCS" | "School"    | "2PCS" | 1410742077000L | 1442679300L | "GOLD"    | "III"         | 101               | 4223         | -30       | 50          | Mock(TeamRankingTierRift)
        "TEAM-4357c190-3c37-11e4-a33e-c81f66db96d8" | "2nigh in PCS" | "School"    | "2PCS" | 1410742077000L | 1442679300L | "GOLD"    | "III"         | 101               | 4223         | 30        | -50         | Mock(TeamRankingTierRift)
        "TEAM-4357c190-3c37-11e4-a33e-c81f66db96d8" | "2nigh in PCS" | "School"    | "2PCS" | 1410742077000L | 1442679300L | "GOLD"    | "III"         | 101               | -50          | 30        | 50          | Mock(TeamRankingTierRift)
    }

    void "test de la methode toString"() {
        given: "Une equipe valide"
        team.teamId = aTeamId
        team.name = aName
        team.status = aStatus
        team.tag = aTag
        team.createDate = aCreateDate
        team.addDate = aAddDate
        team.tierRift = aTierRift
        team.divisionRift = aDivisionRift
        team.leaguePointsRift = aLeaguePointsRift
        team.rankedScore = aRankedScore
        team.winsRift = aWinsRift
        team.loosesRift = aLoosesRift
        team.rankingTierRift = aRankingTierRift

        when: "On déclenche la methode toString sur cette équipe"
        def res = team.toString()

        then: "On recupere le nom de cette equipe"
        res.equals(team.name)

        where:
        aTeamId                                     | aName          | aStatus     | aTag   | aCreateDate   | aAddDate   | aTierRift | aDivisionRift | aLeaguePointsRift | aRankedScore | aWinsRift | aLoosesRift | aRankingTierRift
        "TEAM-4357c190-3c37-11e4-a33e-c81f66db96d8" | "2nigh in PCS" | "Titulaire" | "2PCS" | 1410742077000 | 1442679300 | "GOLD"    | "III"         | 20                | 4223         | 50        | 30          | Mock(TeamRankingTierRift)
    }

    void "test de la methode equals"() {
        given: "Deux equipes valides"
        team.teamId = aTeamId
        team.name = aName
        team.status = aStatus
        team.tag = aTag
        team.createDate = aCreateDate
        team.addDate = aAddDate
        team.tierRift = aTierRift
        team.divisionRift = aDivisionRift
        team.leaguePointsRift = aLeaguePointsRift
        team.rankedScore = aRankedScore
        team.winsRift = aWinsRift
        team.loosesRift = aLoosesRift
        team.rankingTierRift = aRankingTierRift

        anotherTeam.teamId = aTeamId
        anotherTeam.name = aName
        anotherTeam.status = aStatus
        anotherTeam.tag = aTag
        anotherTeam.createDate = aCreateDate
        anotherTeam.addDate = aAddDate
        anotherTeam.tierRift = aTierRift
        anotherTeam.divisionRift = aDivisionRift
        anotherTeam.leaguePointsRift = aLeaguePointsRift
        anotherTeam.rankedScore = aRankedScore
        anotherTeam.winsRift = aWinsRift
        anotherTeam.loosesRift = aLoosesRift
        anotherTeam.rankingTierRift = aRankingTierRift

        when: "On déclenche la methode equals sur cette équipe"
        def res = team.equals(anotherTeam)

        then: "Les deux equipes sont identiques"
        res == true

        where:
        aTeamId                                     | aName          | aStatus     | aTag   | aCreateDate   | aAddDate   | aTierRift | aDivisionRift | aLeaguePointsRift | aRankedScore | aWinsRift | aLoosesRift | aRankingTierRift
        "TEAM-4357c190-3c37-11e4-a33e-c81f66db96d8" | "2nigh in PCS" | "Titulaire" | "2PCS" | 1410742077000 | 1442679300 | "GOLD"    | "III"         | 20                | 4223         | 50        | 30          | Mock(TeamRankingTierRift)
    }

    void "test de la methode hashCode"() {
        given: "Une equipe valide"
        team.teamId = aTeamId
        team.name = aName
        team.status = aStatus
        team.tag = aTag
        team.createDate = aCreateDate
        team.addDate = aAddDate
        team.tierRift = aTierRift
        team.divisionRift = aDivisionRift
        team.leaguePointsRift = aLeaguePointsRift
        team.rankedScore = aRankedScore
        team.winsRift = aWinsRift
        team.loosesRift = aLoosesRift
        team.rankingTierRift = aRankingTierRift

        when: "On déclenche deux fois la méthode hashCode sur cette équipe"
        def exec1 = team.hashCode()
        def exec2 = team.hashCode()

        then: "Les deux hash codes sont identiques"
        exec1 == exec2

        where:
        aTeamId                                     | aName          | aStatus     | aTag   | aCreateDate   | aAddDate   | aTierRift | aDivisionRift | aLeaguePointsRift | aRankedScore | aWinsRift | aLoosesRift | aRankingTierRift
        "TEAM-4357c190-3c37-11e4-a33e-c81f66db96d8" | "2nigh in PCS" | "Titulaire" | "2PCS" | 1410742077000 | 1442679300 | "GOLD"    | "III"         | 20                | 4223         | 50        | 30          | Mock(TeamRankingTierRift)
    }
}
