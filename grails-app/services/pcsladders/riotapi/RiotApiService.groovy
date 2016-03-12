package pcsladders.riotapi

import dto.League.League
import dto.League.LeagueEntry
import dto.Team.Team
import grails.util.Environment
import main.java.riotapi.RiotApiException
import org.springframework.transaction.annotation.Transactional
import main.java.riotapi.RiotApi
import constant.Region
import pcsladders.constant.QueueType
import pcsladders.util.TransformTeamDto2Team

@Transactional
class RiotApiService {

    private static final Region API_REGION = Region.EUW
    private static final String PROPERTIES_FILE_NAME = "riotapi.properties"
    private static final String PROPERTY_KEY = "key"

    RiotApi riotApi

    RiotApiService() {
        String apiKey = getApiKey()
        riotApi = new RiotApi(apiKey,API_REGION)
    }

    String getApiKey() {
        if(Environment.current == Environment.DEVELOPMENT || Environment.current == Environment.TEST) {
            return getApiKeyFromProperties()
        }
        else {
            return System.getenv("RIOT_KEY");
        }
    }

    private String getApiKeyFromProperties() throws IOException, FileNotFoundException, IllegalArgumentException {
        Properties properties = new Properties()
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME)

        if (inputStream != null) {
            try {
                properties.load(inputStream)
            }
            catch (IOException e) {
                throw new IOException(e.getMessage())
            }
            catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e.getMessage())
            }
        } else {
            throw new FileNotFoundException("Le fichier " + PROPERTIES_FILE_NAME + " n'a pas ete trouve")
        }

        String apiKey = properties.getProperty(PROPERTY_KEY)
        inputStream.close()

        return apiKey
    }

    /**
     * Retourne la map de liste de League de toutes les equipes passees en parametre.
     * @param teamIds la chaine de caracteres contenant les identifiants des equipes
     * @return une map de liste de League
     */
    Map<String, List<League>> getLeague4Teams(String teamIds) throws RiotApiException{
        return riotApi.getLeagueByTeams(teamIds)
    }

    /**
     * Retourne la League de type queueType.
     * @param league une liste de League
     * @param queueType le type de League souhaitee (RANKED_SOLO_5x5, RANKED_TEAM_3x3, RANKED_TEAM_5x5)
     * @return la league de type queueType
     */
    League getTeamLeague(List<League> leagues, QueueType queueType){
        boolean find = false;
        int i = 0;
        League teamLeagueRift

        while(i < leagues.size() && !find) {
            League league = leagues.get(i)
            String queueLeague = league.queue

            if(queueLeague.equals(queueType.getName())) {
                teamLeagueRift = league
                find = true
            }

            i++
        }

        return teamLeagueRift
    }

    /**
     * Retourne le tier de la League passee en parametre.
     * @param league une League
     * @return le tier de la League
     */
    String getLeagueTier(League league) {
        return league.tier
    }

    /**
     * Retourne l'id du participant de la League passee en parametre.
     * @param league une League
     * @return l'id du participant
     */
    String getLeagueParticipantId(League league) {
        return league.participantId
    }

    /**
     * Retourne la division de la LeagueEntry (eq. equipe) passee en parametre.
     * @param leagueEntry une LeagueEntry
     * @return la division de la LeagueEntry
     */
    String getLeagueEntryDivision(LeagueEntry leagueEntry) {
        return leagueEntry.division
    }

    /**
     * Retourne le nombre de LP de la LeagueEntry (eq. equipe) passee en parametre.
     * @param leagueEntry une LeagueEntry
     * @return le nombre de LP de la LeagueEntry
     */
    int getLeagueEntryLP(LeagueEntry leagueEntry) {
        return leagueEntry.leaguePoints
    }
    /**
     * Retourne le nombre de victoires de la LeagueEntry (eq. equipe) passee en parametre.
     * @param leagueEntry une LeagueEntry
     * @return le nombre de victoire de la LeagueEntry
     */
    int getLeagueEntryWins(LeagueEntry leagueEntry) {
        return leagueEntry.wins
    }

    /**
     * Retourne le nombre de victoires de la LeagueEntry (eq. equipe) passee en parametre.
     * @param leagueEntry une LeagueEntry
     * @return le nombre de defaites de la LeagueEntry
     */
    int getLeagueEntryLooses(LeagueEntry leagueEntry) {
        return leagueEntry.losses
    }

    /**
     * Retourne la LeagueEntry associee a l'equipe passee en parametre.
     * Si aucune LeagueEntry n'est trouvee pour cette equipe, cette methode renvoie null.
     * @param leagueEntries une liste de LeagueEntry
     * @param teamId l'identifiant de l'equipe
     * @return la LeagueEntry associee a l'equipe sinon null
     */
    LeagueEntry getTeamLeagueEntry(List<LeagueEntry> leagueEntries, String teamId) {
        boolean find = false;
        int i = 0;
        LeagueEntry teamLeagueEntry

        while(i < leagueEntries.size() && !find) {
            LeagueEntry leagueEntry = leagueEntries.get(i)
            String participantId = leagueEntry.playerOrTeamId

            if(participantId.equals(teamId)) {
                teamLeagueEntry = leagueEntry
                find = true
            }

            i++
        }

        return teamLeagueEntry
    }

    /**
     * Recupere l'ID d'un invocateur a partir de son nom.
     * @param name le nom de l'invocateur
     * @return l'ID de l'invocateur
     * @throws RiotApiException
     */
    long getSummonerId(String name) throws RiotApiException {
        return riotApi.getSummonerByName(name).id
    }

    /**
     * Recupere les equipes d'un invocateur a partir de son ID.
     * @param id l'ID de l'invocateur
     * @return une map contenant la liste des equipes associees a l'invocateur
     */
    List<Team> getSummonerTeamsDto(long id) throws RiotApiException {
        return riotApi.getTeamsBySummonerId(id)
    }

    List<pcsladders.team.Team> getSummonerTeams(String name) throws RiotApiException {
        long id = getSummonerId(name)
        List<Team> teamsDto = getSummonerTeamsDto(id)
        List<pcsladders.team.Team> teams = TransformTeamDto2Team.transformTeamDtoList2TeamList(teamsDto)
        return teams
    }
}
