package com.attep.lutemon_project;

public class BattleResult {
    public final int attackerId;
    public final int defenderId;
    public final int attackValue;
    public final int defenseValue;
    public final int damageDealt;
    public final int defenderRemainingHp;
    public final boolean isFinished;
    public final int winnerId;
    public final String fightStr;

    public BattleResult(int attackerId,
                        int defenderId,
                        int attackValue,
                        int defenseValue,
                        int damageDealt,
                        int defenderRemainingHp,
                        boolean isFinished,
                        int winnerId,
                        String fightStr) {
        this.attackerId          = attackerId;
        this.defenderId          = defenderId;
        this.attackValue         = attackValue;
        this.defenseValue        = defenseValue;
        this.damageDealt         = damageDealt;
        this.defenderRemainingHp = defenderRemainingHp;
        this.isFinished          = isFinished;
        this.winnerId            = winnerId;
        this.fightStr            = fightStr;
    }
}
