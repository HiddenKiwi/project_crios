package pcsladders.util;


import pcsladders.constant.Division;
import pcsladders.constant.Tier;
import pcsladders.team.Team;

public class TeamUtil {

    public static int getTierValue(Team team) {
        switch (team.getTierRift()) {
            case "URANKED":
                return Tier.UNRANKED.getValue();
            case "BRONZE":
                return Tier.BRONZE.getValue();
            case "SILVER":
                return Tier.SILVER.getValue();
            case "GOLD":
                return Tier.GOLD.getValue();
            case "PLATINUM":
                return Tier.PLATINUM.getValue();
            case "DIAMOND":
                return Tier.DIAMOND.getValue();
            case "MASTER":
                return Tier.MASTER.getValue();
            case "CHALLENGER":
                return Tier.CHALLENGER.getValue();
            default:
                return 0;
        }
    }

    public static int getDivisionValue(Team team) {
        switch (team.getDivisionRift()) {
            case "V":
                return Division.V.getValue();
            case "IV":
                return Division.IV.getValue();
            case "III":
                return Division.III.getValue();
            case "II":
                return Division.II.getValue();
            case "I":
                return Division.I.getValue();
            default:
                return 0;
        }
    }
}
