package pcsladders.team
// test
class Team {

    String teamId
    String name
    String status
    String tag
    long createDate
    long addDate
    String tierRift
    String divisionRift
    int leaguePointsRift
    int rankedScore
    int winsRift
    int loosesRift

    static hasOne = [rankingTierRift:TeamRankingTierRift]

    static constraints = {
        teamId blank: false, unique: true, nullable: false
        name blank: false, nullable: false
        status inList: ["Titulaire", "School"], blank: false , nullable: false
        tag blank: false, nullable: false
        tierRift blank: false, nullable: true
        divisionRift blank: false, nullable: true
        leaguePointsRift min: 0, max: 100
        rankedScore min: 0
        winsRift min: 0
        loosesRift min: 0
        rankingTierRift nullable: true
    }

    @Override
    public String toString() {
        return name
    }

    @Override
    boolean equals(Object obj) {
        return obj instanceof Team && teamId.equals(obj.teamId)
    }

    @Override
    int hashCode() {
        return super.hashCode()
    }
}
