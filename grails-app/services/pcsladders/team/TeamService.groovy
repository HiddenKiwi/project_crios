package pcsladders.team

import dto.League.League
import dto.League.LeagueEntry
import grails.transaction.Transactional
import main.java.riotapi.RiotApiException
import org.springframework.validation.ObjectError
import pcsladders.constant.QueueType
import pcsladders.riotapi.RiotApiService
import pcsladders.util.AddResult
import pcsladders.util.TeamUtil
import pcsladders.util.TransformSet2List


@Transactional
class TeamService {

    // Constantes
    private static final int FRAME = 10;

    // Attributs
    RiotApiService riotApiService

    /**
     * Retourne toutes les equipes titulaires de l'application
     * @return la liste des equipes titulaires
     */
    List<Team> findTeamsTitulaire() {
        List<Team> teams = Team.findAllByStatus("Titulaire")
        teams.sort {it.rankedScore}
        teams = teams.reverse()
        return teams
    }

    /**
     * Retourne toutes les equipes school de l'application
     * @return la liste des equipes school
     */
    List<Team> findTeamsSchool() {
        List<Team> teams = Team.findAllByStatus("School")
        teams.sort {it.rankedScore}
        teams = teams.reverse()
        return teams
    }

    void rankTeams() {
        for(Team team : Team.list()) {
            team.rankedScore = TeamUtil.getTierValue(team) + TeamUtil.getDivisionValue(team) + team.leaguePointsRift;
            team.save()
        }
    }

    /**
     * Supprime l'equipe passee en parametre
     * @param team l'equipe a supprimer
     * @return true si la suppression a reussi sinon false
     */
    void delete(Team team) {
        team.delete(flush: true)
    }

    /**
     * Met a jour toutes les equipes en base a partir de l'API Riot
     * @return true si la mise a jour n'a rencontre aucune erreur majeur, false sinon
     * Cas d'echecs possibles :
     *  - Impossible de recuperer la liste des equipes en base -> exception SQL a gerer (erreur majeure) [TRAITEE]
     *  - Une erreur survient au moment de recuperer les informations a partir de l'API Riot -> exception API Riot a attraper (erreur majeure) [A TRAITER]
     *  - Aucune LeagueEntry n'est trouvee pour une equipe -> non bloquant pour le reste de la mise a jour ** signaler l'echec de mise a jour d'une equipe **
     *  - Une erreur survient au moment de sauvegarder l'equipe mise a jour -> non bloquant pour le reste de la mise a jour ** signaler l'echec de la mise a jour d'une equipe **
     */
    void updateAllTeams() throws RiotApiException {
        // Recuperer la liste des IDs de toutes l'equipe en base
        List<Team> teams = Team.findAll()

        // Decouper la liste principale en une map de listes de 10 elements
        Map<Integer, List<Team>> teamsMap = splitTeamList(teams, FRAME)

        // Pour chaque sous liste
        for(int i = 0; i < teamsMap.size(); i++) {
            // Transformer les 10 elements de la liste en 1 chaine de caracteres
            List<Team> teamSubList = teamsMap.get(i)
            String teamsIds = concatTeamIds(teamSubList)

            // Recuperer les informations de palier, de division et de LP a partir de l'API Riot
            Map<String,List<League>> teamLeagues = riotApiService.getLeague4Teams(teamsIds)
            // On recupere les identifiants de tous les participants (eq. les equipes) associes aux Leagues
            List<String> participantIds = TransformSet2List.transformSet2List(teamLeagues.keySet())

            // Pour chaque equipe
            for(int j = 0; j < teamLeagues.size(); j++){
                String teamId = participantIds.get(j)
                List<League> leagues = teamLeagues.get(teamId)

                // Recuperer sa League 5c5
                League teamLeagueRift = riotApiService.getTeamLeague(leagues, QueueType.RANKED_TEAM_5x5)

                if(teamLeagueRift != null) {
                    // Recuperation de l'ID de l'equipe courante
                    //String teamId = riotApiService.getLeagueParticipantId(teamLeagueRift)

                    // Recuperation du tier de l'equipe courante
                    String teamTier = riotApiService.getLeagueTier(teamLeagueRift)

                    // Recuperation de la LeagueEntry de l'equipe courante
                    List<LeagueEntry> leagueEntries = teamLeagueRift.entries
                    LeagueEntry teamLeagueEntry = riotApiService.getTeamLeagueEntry(leagueEntries, teamId)

                    if(teamLeagueEntry != null) {
                        // Recuperation de la division, du nombre de LP, de wins et de looses de l'equipe
                        String teamDivision = riotApiService.getLeagueEntryDivision(teamLeagueEntry)
                        int teamLP = riotApiService.getLeagueEntryLP(teamLeagueEntry)
                        int teamWins = riotApiService.getLeagueEntryWins(teamLeagueEntry)
                        int teamLoooses = riotApiService.getLeagueEntryLooses(teamLeagueEntry)

                        // Mettre a jour ses donnees avec celles recuperees
                        Team teamToUpdate = findTeamById(teams, teamId)
                        updateTeam(teamToUpdate, teamTier, teamDivision, teamLP, teamWins, teamLoooses)
                    }
                }
            }
        }
    }

    /**
     * Concantene les identifiants des equipes du tableau en 1 chaine de caracteres.
     * @param teams le tableau d'equipe
     * @return une chaine de caracteres contenant les identifiants de toutes les equipes
     */
    String concatTeamIds(List<Team> teams) {
        String str = ""

        for(int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i)
            str += team.getTeamId()

            if(i != teams.size()-1) {
                str += ","
            }
        }

        return str
    }

    /**
     * Decoupe une liste d'equipes en map de sous listes de n elements.
     * @param teams une liste d'equipe
     * @return une map contenant les sous listes de n elements
     */
    Map<Integer,List<Team>> splitTeamList(List<Team> teams, int frame) {
        Map<Integer, List<Team>> res = new HashMap<Integer, List<Team>>()
        int size = teams.size()
        int numberOfIteration = (int) Math.ceil((float)size / frame)
        int remainingTeams = size % frame
        int cptIteration = 0
        boolean exactDecade = remainingTeams == 0

        for (int i = 0; i < size; i += frame) {
            cptIteration++

            if(!exactDecade && cptIteration == numberOfIteration) {
                res.put(cptIteration - 1,teams.subList(i, i + remainingTeams))
            }
            else {
                res.put(cptIteration - 1, teams.subList(i, i + frame))
            }
        }

        return res
    }

    /**
     * Met a jour les attributs tier, division, nombre de victoires et de defaites de l'equipe
     * passee en parametre.
     * @param team l'equipe
     * @param tier le palier
     * @param division la division
     * @param leaguePoints le nombre de league points
     * @param wins le nombre de victoire
     * @param looses le nombre de defaites
     * @return l'equipe mise a jour
     */
    Team updateTeam(Team team, String tier, String division, int leaguePoints, int wins, int looses) {
        team.tierRift = tier
        team.divisionRift = division
        team.leaguePointsRift = leaguePoints
        team.winsRift = wins
        team.loosesRift = looses
        team.save(flush: true, failOnError: true)
        return team
    }

    /**
     * Recherche l'equipe avec l'identifiant teamId dans la liste teams.
     * Si l'equipe n'existe pas dans la liste, renvoie null.
     * @param teams une liste d'equipe
     * @param teamId l'identifiant d'une equipe
     * @return l'equipe recherche, sinon null
     */
    Team findTeamById(List<Team> teams, String teamId) {
        return teams.find { it.teamId.equals(teamId)}
    }

    /**
     * Retourne toutes les equipes de la liste passee en parametre, a partir du tableau d'ID.
     * @param ids un tableau d'ID d'equipe
     * @param summonerTeams une liste d'equipe
     * @return la liste des equipes trouvees
     */
    List<Team> findTeamsByIdArray(List<Team> summonerTeams, String... ids) {
        List<Team> teamsToAdd = new ArrayList<Team>()

        for(int i = 0; i < ids.length; i++) {
            Team team = summonerTeams.find { it -> it.teamId == ids[i]}
            if(team != null) {
                teamsToAdd.add(team)
            }
        }

        return teamsToAdd
    }

    /**
     * Ajoute en base la liste d'equipe passée en parametre. Cette methode retourne une liste contenant
     * les resultats de la tentative d'ajout de chaque equipe.
     * @param teams la liste d'equipe a ajouter a l'application
     * @return la liste des resultats des ajouts tentes
     */
    List<AddResult> addTeams(List<Team> teams) {
        List<AddResult> addResults = new ArrayList<AddResult>()

        for(Team team : teams) {
            if(!team.save(flush: true)) {
                List<ObjectError> objectErrorList = team.errors.allErrors
                int size = objectErrorList.size()
                ObjectError lastObjectError = objectErrorList.get(size-1)
                AddResult addResult = new AddResult(false)
                addResult.setTeamName(team.name)
                addResult.setErrorCode(lastObjectError.code)
                addResults.add(addResult)
            }
            else {
                AddResult addResult = new AddResult(true)
                addResult.setTeamName(team.name)
                addResults.add(addResult)
            }
        }

        return addResults
    }

    void changeStatus(Team team) {
        if(team.status == "Titulaire") {
            team.status = "School"
        }
        else {
            team.status = "Titulaire"
        }
        team.save(flush: true)
    }
}
