package pcsladders.riotapi

import dto.League.League
import dto.League.LeagueEntry
import dto.Team.Team
import main.java.riotapi.RiotApiException
import pcsladders.constant.QueueType
import spock.lang.Specification

class RiotApiServiceSpec extends Specification {

    RiotApiService riotApiService

    def setup() {
        riotApiService = new RiotApiService()
    }

    void "test de la methode getApiKey avec un fichier de proprites valide"() {
        given: "Un fichier de proprietes valide"

        when: "On execute la methode getApiKey"
        String key = riotApiService.getApiKey()

        then: "On obtient la valeur attendue"
        key != null
    }

/*    void "test de la methode getLeague4Team avec 2 equipes valides"() {
        given: "Une chaine de caracteres contenant les identifiants de 2 equipes valides"
        String teamIds = "TEAM-4494c210-88c6-11e5-8011-c81f66daeaa4,TEAM-8a30bc20-88c6-11e5-a363-c81f66dd32cd"

        when: "On execute la methode getLeague4Teams avec cette chaine de caracteres"
        Map<String, List<League>> leagues = riotApiService.getLeague4Teams(teamIds)

        then: "On obtient une map contenant 2 sous listes possedant chacune 2 League"
        leagues.size() != null
    }*/

    void "test de la methode getLeague4Team avec 1 equipe valide et 1 equipe non valide"() {
        given: "Une chaine de caracteres contenant les identifiants de 1 equipe valide et une equipe non valide"
        String teamIds = "TEAM-4494c210-88c6-11e5-8011-c81f66daeaa4,TEAM-BAD-ID"

        when: "On execute la methode getLeague4Teams avec cette chaine de caracteres"
        RiotApiException riotApiException
        try {
            riotApiService.getLeague4Teams(teamIds)
        }
        catch(RiotApiException e) {
            riotApiException = e
        }

        then: "Une exception a bien ete levee"
        riotApiException != null
    }

    void "test de la methode getTeamLeague une liste de League valide"() {
        given: "Une liste de League contenant une de type RANKED_TEAM_3x3 et une de type RANKED_TEAM_5x5"
        List<League> leagues = new ArrayList<League>()
        League leagueTeamRift = new League()
        League leagueTeamForest = new League()
        leagueTeamRift.queue = "RANKED_TEAM_5x5"
        leagueTeamForest.queue = "RANKED_TEAM_3x3"
        leagues.add(leagueTeamRift)
        leagues.add(leagueTeamForest)

        when: "On execute la methode getTeamLeague avec le type RANKED_TEAM_5x5"
        League actualResult = riotApiService.getTeamLeague(leagues,QueueType.RANKED_TEAM_5x5)

        then: "On recupere la league RANKED_TEAM_5x5"
        actualResult.queue.equals(QueueType.RANKED_TEAM_5x5.getName())

        when: "On execute la methode getTeamLeague avec le type RANKED_TEAM_3x3"
        actualResult = riotApiService.getTeamLeague(leagues,QueueType.RANKED_TEAM_3x3)

        then: "On recupere la league RANKED_TEAM_3x3"
        actualResult.queue.equals(QueueType.RANKED_TEAM_3x3.getName())
    }

    void "test de la methode getTeamLeague avec des parametres invalides"() {
        given: "Une liste contenant une unique League de type RANKED_TEAM_3x3"
        List<League> leagues = new ArrayList<League>()
        League leagueTeamForest = new League()
        leagueTeamForest.queue = "RANKED_TEAM_3x3"
        leagues.add(leagueTeamForest)

        when: "On execute la methode getTeamLeague avec le type RANKED_TEAM_5x5"
        League actualResult = riotApiService.getTeamLeague(leagues,QueueType.RANKED_TEAM_5x5)

        then: "On ne recupere aucune League"
        actualResult == null

        when: "On execute la methode getTeamLeague avec une liste de League vide"
        leagues = new ArrayList<League>()
        actualResult = riotApiService.getTeamLeague(leagues,QueueType.RANKED_TEAM_5x5)

        then: "On ne recupere aucune League"
        actualResult == null
    }

    void "test de la methode getLeagueTier"() {
        given: "une League de tier GOLD"
        League league = new League()
        league.tier = "GOLD"

        when: "On execute la methode getLeagueTier sur cette League"
        String actualResult = riotApiService.getLeagueTier(league)

        then: "On obtient la chaine de caracteres attendue"
        actualResult.equals("GOLD")
    }

    void "test de la methode getParticipantLeagueId"() {
        given: "une League associee un participant"
        League league = new League()
        league.participantId = "TEAM-ID-1"

        when: "On execute la methode getParticipantLeagueId sur cette League"
        String actualResult = riotApiService.getLeagueParticipantId(league)

        then: "On obtient la chaine de caracteres attendue"
        actualResult.equals("TEAM-ID-1")
    }

    void "test de la methode getLeagueEntryDivision"() {
        given: "une LeagueEntry valide"
        LeagueEntry leagueEntry = new LeagueEntry()
        leagueEntry.division = "V"

        when: "On execute la methode getLeagueEntryDivision sur cette LeagueEntry"
        String actualResult = riotApiService.getLeagueEntryDivision(leagueEntry)

        then: "On obtient la chaine de caracteres attendue"
        actualResult.equals("V")
    }

    void "test de la methode getLeagueEntryLP"() {
        given: "une LeagueEntry valide"
        LeagueEntry leagueEntry = new LeagueEntry()
        leagueEntry.leaguePoints = 100

        when: "On execute la methode getLeagueEntryLP sur cette LeagueEntry"
        int actualResult = riotApiService.getLeagueEntryLP(leagueEntry)

        then: "On obtient la chaine de caracteres attendue"
        actualResult == 100
    }

    void "test de la methode getLeagueEntryWins"() {
        given: "une LeagueEntry valide"
        LeagueEntry leagueEntry = new LeagueEntry()
        leagueEntry.wins = 42

        when: "On execute la methode getLeagueEntryWins sur cette LeagueEntry"
        int actualResult = riotApiService.getLeagueEntryWins(leagueEntry)

        then: "On obtient la chaine de caracteres attendue"
        actualResult == 42
    }

    void "test de la methode getLeagueEntryLooses"() {
        given: "une LeagueEntry valide"
        LeagueEntry leagueEntry = new LeagueEntry()
        leagueEntry.losses = 11

        when: "On execute la methode getLeagueEntryLooses sur cette LeagueEntry"
        int actualResult = riotApiService.getLeagueEntryLooses(leagueEntry)

        then: "On obtient la chaine de caracteres attendue"
        actualResult == 11
    }

    void "test de la methode getTeamLeagueEntry avec une liste de LeagueEntry valide"() {
        given: "Un liste de LeagueEntry valide et l'id d'une equipe existante dans la liste de LeagueEntry"
        List<LeagueEntry> leagueEntries = new ArrayList<LeagueEntry>()
        String teamId = "TEAM-ID-6"

        LeagueEntry leagueEntry1 = new LeagueEntry()
        LeagueEntry leagueEntry2 = new LeagueEntry()
        LeagueEntry leagueEntry3 = new LeagueEntry()
        LeagueEntry leagueEntry4 = new LeagueEntry()
        LeagueEntry leagueEntry5 = new LeagueEntry()
        LeagueEntry leagueEntry6 = new LeagueEntry()
        LeagueEntry leagueEntry7 = new LeagueEntry()
        LeagueEntry leagueEntry8 = new LeagueEntry()
        LeagueEntry leagueEntry9 = new LeagueEntry()
        LeagueEntry leagueEntry10 = new LeagueEntry()

        leagueEntry1.playerOrTeamId = "TEAM-ID-1"
        leagueEntry2.playerOrTeamId = "TEAM-ID-2"
        leagueEntry3.playerOrTeamId = "TEAM-ID-3"
        leagueEntry4.playerOrTeamId = "TEAM-ID-4"
        leagueEntry5.playerOrTeamId = "TEAM-ID-5"
        leagueEntry6.playerOrTeamId = "TEAM-ID-6"
        leagueEntry7.playerOrTeamId = "TEAM-ID-7"
        leagueEntry8.playerOrTeamId = "TEAM-ID-8"
        leagueEntry9.playerOrTeamId = "TEAM-ID-9"
        leagueEntry10.playerOrTeamId = "TEAM-ID-10"

        leagueEntries.add(leagueEntry1)
        leagueEntries.add(leagueEntry2)
        leagueEntries.add(leagueEntry3)
        leagueEntries.add(leagueEntry4)
        leagueEntries.add(leagueEntry5)
        leagueEntries.add(leagueEntry6)
        leagueEntries.add(leagueEntry7)
        leagueEntries.add(leagueEntry8)
        leagueEntries.add(leagueEntry9)
        leagueEntries.add(leagueEntry10)

        when: "On execute la methode getTeamLeagueEntry sur cette liste"
        LeagueEntry actualResult = riotApiService.getTeamLeagueEntry(leagueEntries, teamId)

        then: "On recupere la LeagueEntry associee a cette equipe"
        actualResult.playerOrTeamId.equals(teamId)
    }

    void "test de la methode getTeamLeagueEntry avec des parametres invalides"() {
        given: "Un liste de LeagueEntry valide et  l'id d'une equipe n'existant pas dans la liste de LeagueEntry"
        List<LeagueEntry> leagueEntries = new ArrayList<LeagueEntry>()
        String teamId = "TEAM-ID-6"

        LeagueEntry leagueEntry1 = new LeagueEntry()
        LeagueEntry leagueEntry2 = new LeagueEntry()
        LeagueEntry leagueEntry3 = new LeagueEntry()
        LeagueEntry leagueEntry4 = new LeagueEntry()
        LeagueEntry leagueEntry5 = new LeagueEntry()
        LeagueEntry leagueEntry7 = new LeagueEntry()
        LeagueEntry leagueEntry8 = new LeagueEntry()
        LeagueEntry leagueEntry9 = new LeagueEntry()
        LeagueEntry leagueEntry10 = new LeagueEntry()

        leagueEntry1.playerOrTeamId = "TEAM-ID-1"
        leagueEntry2.playerOrTeamId = "TEAM-ID-2"
        leagueEntry3.playerOrTeamId = "TEAM-ID-3"
        leagueEntry4.playerOrTeamId = "TEAM-ID-4"
        leagueEntry5.playerOrTeamId = "TEAM-ID-5"
        leagueEntry7.playerOrTeamId = "TEAM-ID-7"
        leagueEntry8.playerOrTeamId = "TEAM-ID-8"
        leagueEntry9.playerOrTeamId = "TEAM-ID-9"
        leagueEntry10.playerOrTeamId = "TEAM-ID-10"

        leagueEntries.add(leagueEntry1)
        leagueEntries.add(leagueEntry2)
        leagueEntries.add(leagueEntry3)
        leagueEntries.add(leagueEntry4)
        leagueEntries.add(leagueEntry5)
        leagueEntries.add(leagueEntry7)
        leagueEntries.add(leagueEntry8)
        leagueEntries.add(leagueEntry9)
        leagueEntries.add(leagueEntry10)

        when: "On execute la methode getTeamLeagueEntry sur cette liste"
        LeagueEntry actualResult = riotApiService.getTeamLeagueEntry(leagueEntries, teamId)

        then: "On ne recupere aucune LeagueEntry associee a cette equipe"
        actualResult == null

        when: "On execute la methode getTeamLeagueEntry sur une liste de LeagueEntry vide"
        leagueEntries = new ArrayList<LeagueEntry>()
        actualResult = riotApiService.getTeamLeagueEntry(leagueEntries, teamId)

        then: "On ne recupere aucune LeagueEntry associee a cette equipe"
        actualResult == null
    }

    void "test du service getSummonerId avec un nom valide"() {
        given: "Un nom d'invocateur valide"
        String summonerName = "PCS Hidden"

        when: "On execute le service avec ce nom d'invocateur"
        long result = riotApiService.getSummonerId(summonerName)

        then: "On obtient l'ID de l'invocateur"
        result != 0
    }

    void "test du service getSummonerId avec un nom invalide"() {
        given: "Un nom d'invocateur valide"
        String summonerName = "PCS Hiddden"

        when: "On execute le service avec ce nom d'invocateur"
        boolean hasThrownException = false;
        try {
            riotApiService.getSummonerId(summonerName)
        }
        catch(RiotApiException e) {
           hasThrownException = true;
        }


        then: "Une exception a ete leve"
        hasThrownException
    }

    void "test du service getSummonnerTeamsDto avec un id valide"() {
        given: "Un ID d'invocateur valide"
        long summonerId = 75569426;

        when: "On execute le service avec cet id"
        List<Team> summonerTeams = riotApiService.getSummonerTeamsDto(summonerId)

        then: "On obtient la liste des equipes associees a l'invocateur"
        summonerTeams.size() != 0
    }

    void "test du service getSummonnerTeamsDto avec un id invalide"() {
        given: "Un ID d'invocateur invalide"
        long summonerId = -1;

        when: "On execute le service avec cet id"
        boolean hasThrownException = false;
        try {
            riotApiService.getSummonerTeamsDto(summonerId)

        }
        catch (RiotApiException e) {
            hasThrownException = true
        }

        then: "Une exception a ete leve"
        hasThrownException
    }

    void "test du service getSummonerTeams avec un nom valide"() {
        given: "Un nom d'invocateur valide"
        String summonerName = "PCS Hidden"

        when: "On execute le service avec ce nom d'invocateur"
        List<pcsladders.team.Team> summonerTeams = riotApiService.getSummonerTeams(summonerName)

        then: "On obtient la liste des equipes associees a cet invocateur"
        summonerTeams.size() != 0
    }

    void "test du service getSummonerTeams avec un nom invalide"() {
        given: "Un nom d'invocateur valide"
        String summonerName = "PCS Hiddden"

        when: "On execute le service avec ce nom d'invocateur"
        boolean hasThrownException = false;
        try {
            riotApiService.getSummonerTeams(summonerName)
        }
        catch(RiotApiException e) {
            hasThrownException = true;
        }


        then: "Une exception a ete leve"
        hasThrownException
    }
}
