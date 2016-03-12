package pcsladders.setTest

import grails.transaction.Transactional
import pcsladders.team.Team
import pcsladders.team.TeamRankingTierRift


@Transactional
class SetTestTeamService {

    def team1, team2, team3, team4, team5, team6, team7, team8, team9, team10, team11, team12, team13, team14, team15, team16, team17

    def createSetTestsForTeam() {
        team1 = new Team(teamId: "TEAM-87928bd0-ef5e-11e4-88d0-c81f66dba0e7",
                         name: "PCS Aincrads Guardians",
                         status: "Titulaire",
                         tag: "Pcs AG",
                         createDate: 1410742077000L,
                         addDate: 1442679300L,
                         tierRift: "DIAMOND",
                         divisionRift: "V",
                         leaguePointsRift: 100,
                         winsRift: 51,
                         loosesRift: 38,
                         rankingTierRift: new TeamRankingTierRift(currentPosition: 2, lastPosition: 0)).save(flush: true, failOnError: true)

        team2 = new Team(teamId: "TEAM-d8c0ea20-5bc3-11e5-909f-c81f66dd7106",
                name: "PCS Avengers",
                status: "Titulaire",
                tag: "PCS AV",
                createDate: 1410742077000L,
                addDate: 1442679300L,
                tierRift: "PLATINUM",
                divisionRift: "V",
                leaguePointsRift: 26,
                winsRift: 33,
                loosesRift: 28,
                rankingTierRift: new TeamRankingTierRift(currentPosition: 7, lastPosition: 0)).save(flush: true)

        team3 = new Team(teamId: "TEAM-418d1630-dbac-11e3-aea5-782bcb4ce61a",
                name: "PCS 2 Graves 1 cup",
                status: "Titulaire",
                tag: "PCS GG",
                createDate: 1410742077000L,
                addDate: 1442679300L,
                tierRift: "PLATINUM",
                divisionRift: "IV",
                leaguePointsRift: 0,
                winsRift: 70,
                loosesRift: 65,
                rankingTierRift: new TeamRankingTierRift(currentPosition: 6, lastPosition: 0)).save(flush: true)

        team4 = new Team(teamId: "TEAM-ca171430-c1e1-11e4-b090-c81f66db96d8",
                name: "PCS Baiting Theory",
                status: "Titulaire",
                tag: "Pcs AG",
                createDate: 1410742077000L,
                addDate: 1442679300L,
                tierRift: "DIAMOND",
                divisionRift: "V",
                leaguePointsRift: 30,
                winsRift: 87,
                loosesRift: 79,
                rankingTierRift: new TeamRankingTierRift(currentPosition: 3, lastPosition: 0)).save(flush: true)

        team5 = new Team(teamId: "TEAM-0caff1b0-54b1-11e5-b9ef-c81f66dd32cd",
                name: "PCS Coconut",
                status: "Titulaire",
                tag: "PCSNUT",
                createDate: 1410742077000L,
                addDate: 1442679300L,
                tierRift: "PLATINUM",
                divisionRift: "III",
                leaguePointsRift: 40,
                winsRift: 38,
                loosesRift: 36,
                rankingTierRift: new TeamRankingTierRift(currentPosition: 5, lastPosition: 0)).save(flush: true)

        team6 = new Team(teamId: "TEAM-ea22e6f0-1524-11e5-abc6-c81f66dd32cd",
                name: "PCS Glimmer",
                status: "Titulaire",
                tag: "PCS GL",
                createDate: 1410742077000L,
                addDate: 1442679300L,
                tierRift: "GOLD",
                divisionRift: "I",
                leaguePointsRift: 0,
                winsRift: 57,
                loosesRift: 48,
                rankingTierRift: new TeamRankingTierRift(currentPosition: 9, lastPosition: 0)).save(flush: true)

        team7 = new Team(teamId: "TEAM-bb381540-9297-11e5-8238-c81f66daeaa4",
                name: "Reach for the Stars",
                status: "Titulaire",
                tag: "PCS RS",
                createDate: 1410742077000L,
                addDate: 1442679300L,
                tierRift: "GOLD",
                divisionRift: "I",
                leaguePointsRift: 82,
                winsRift: 36,
                loosesRift: 28,
                rankingTierRift: new TeamRankingTierRift(currentPosition: 8, lastPosition: 0)).save(flush: true)

        team8 = new Team(teamId: "TEAM-30ae8850-e85b-11e4-a32b-c81f66db920c",
                name: "PCS Sigma",
                status: "Titulaire",
                tag: "PCS Si",
                createDate: 1410742077000L,
                addDate: 1442679300L,
                tierRift: "DIAMOND",
                divisionRift: "IV",
                leaguePointsRift: 22,
                winsRift: 60,
                loosesRift: 46,
                rankingTierRift: new TeamRankingTierRift(currentPosition: 1, lastPosition: 0)).save(flush: true)

        team9 = new Team(teamId: "TEAM-4494c210-88c6-11e5-8011-c81f66daeaa4",
                name: "PCS Phantom",
                status: "Titulaire",
                tag: "PCSPT",
                createDate: 1410742077000L,
                addDate: 1442679300L,
                tierRift: "UNRANKED",
                divisionRift: "-",
                leaguePointsRift: 0,
                winsRift: 0,
                loosesRift: 0,
                rankingTierRift: new TeamRankingTierRift(currentPosition: 10, lastPosition: 0)).save(flush: true)

        team10 = new Team(teamId: "TEAM-ee40a610-7a64-11e4-b4ad-c81f66db8bc5",
                name: "PCS Wot the Guk",
                status: "Titulaire",
                tag: "PCSWTG",
                createDate: 1410742077000L,
                addDate: 1442679300L,
                tierRift: "PLATINUM",
                divisionRift: "II",
                leaguePointsRift: 32,
                winsRift: 43,
                loosesRift: 42,
                rankingTierRift: new TeamRankingTierRift(currentPosition: 4, lastPosition: 0)).save(flush: true)

        team11 = new Team(teamId: "TEAM-df9b5ae0-2722-11e5-83d9-c81f66daeaa4",
                name: "PCS Sakaple",
                status: "School",
                tag: "PCSSKP",
                createDate: 1410742077000L,
                addDate: 1442679300L,
                tierRift: "PLATINUM",
                divisionRift: "I",
                leaguePointsRift: 74,
                winsRift: 34,
                loosesRift: 23,
                rankingTierRift: new TeamRankingTierRift(currentPosition: 1, lastPosition: 0)).save(flush: true)

        team12 = new Team(teamId: "TEAM-8bda9810-8c96-11e5-a232-c81f66daeaa4",
                name: "PCS TEST Red Box",
                status: "School",
                tag: "PCSREB",
                createDate: 1410742077000L,
                addDate: 1442679300L,
                tierRift: "UNRANKED",
                divisionRift: "-",
                leaguePointsRift: 0,
                winsRift: 0,
                loosesRift: 0,
                rankingTierRift: new TeamRankingTierRift(currentPosition: 7, lastPosition: 0)).save(flush: true)

        team13 = new Team(teamId: "TEAM-43643fa0-5ad7-11e5-a4c9-c81f66dd7106",
                name: "PCS Panda",
                status: "School",
                tag: "PCSP",
                createDate: 1410742077000L,
                addDate: 1442679300L,
                tierRift: "GOLD",
                divisionRift: "II",
                leaguePointsRift: 0,
                winsRift: 8,
                loosesRift: 3,
                rankingTierRift: new TeamRankingTierRift(currentPosition: 5, lastPosition: 0)).save(flush: true)

        team14 = new Team(teamId: "TEAM-4a65e740-8f0c-11e5-8ccb-c81f66dd32cd",
                name: "PCS Pandora TEST",
                status: "School",
                tag: "PCS Tt",
                createDate: 1410742077000L,
                addDate: 1442679300L,
                tierRift: "GOLD",
                divisionRift: "I",
                leaguePointsRift: 100,
                winsRift: 23,
                loosesRift: 18,
                rankingTierRift: new TeamRankingTierRift(currentPosition: 2, lastPosition: 0)).save(flush: true)

        team15 = new Team(teamId: "TEAM-a4ad38e0-3adc-11e5-b07a-c81f66dd30e5",
                name: "PCS Express",
                status: "School",
                tag: "PCSEE",
                createDate: 1410742077000L,
                addDate: 1442679300L,
                tierRift: "GOLD",
                divisionRift: "II",
                leaguePointsRift: 62,
                winsRift: 10,
                loosesRift: 4,
                rankingTierRift: new TeamRankingTierRift(currentPosition: 3, lastPosition: 0)).save(flush: true)

        team16 = new Team(teamId: "TEAM-99244be0-7c0d-11e5-bef0-c81f66dd30e5",
                name: "PCS Pumpkin",
                status: "School",
                tag: "PCS PU",
                createDate: 1410742077000L,
                addDate: 1442679300L,
                tierRift: "GOLD",
                divisionRift: "II",
                leaguePointsRift: 32,
                winsRift: 11,
                loosesRift: 7,
                rankingTierRift: new TeamRankingTierRift(currentPosition: 4, lastPosition: 0)).save(flush: true)

        team17 = new Team(teamId: "TEAM-4646fc10-7695-11e5-bf2c-c81f66daeaa4",
                name: "PCS Rise Is On Throw",
                status: "School",
                tag: "PCSRIO",
                createDate: 1410742077000L,
                addDate: 1442679300L,
                tierRift: "SILVER",
                divisionRift: "II",
                leaguePointsRift: 0,
                winsRift: 51,
                loosesRift: 38,
                rankingTierRift: new TeamRankingTierRift(currentPosition: 6, lastPosition: 0)).save(flush: true)
    }
}
