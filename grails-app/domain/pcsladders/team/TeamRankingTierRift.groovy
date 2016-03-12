package pcsladders.team

class TeamRankingTierRift {

    Team team
    int currentPosition
    int lastPosition

    static constraints = {
        team nullable: false
        currentPosition min: 1
        lastPosition min: 0
    }

    @Override
    public String toString() {
        return currentPosition
    }

    @Override
    boolean equals(Object obj) {
        return obj instanceof TeamRankingTierRift && (this.currentPosition == obj.currentPosition) && (this.lastPosition == obj.lastPosition)
    }

    @Override
    int hashCode() {
       return super.hashCode()
    }
}
