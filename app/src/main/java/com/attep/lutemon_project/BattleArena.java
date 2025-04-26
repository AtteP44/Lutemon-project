package com.attep.lutemon_project;

public class BattleArena extends LutemonLocation {

    @Override
    public String getLocationName() {
        return "Battle Arena";
    }

    public BattleResult fightOnce(Lutemon attacker, Lutemon defender) {
        int attackValue  = attacker.getAttackRandomized();
        int defenseValue = defender.getDefenseRandomized();

        int damage = defender.defense(attackValue,defenseValue);
        boolean isOver = defender.getHealth() <= 0;

        String fightStr;
        int winnerId = -1;
        if (isOver) {
            fightStr = defender.getName() + " fainted!";
            attacker.addWin();
            attacker.levelUp();
            winnerId = attacker.getId();
        } else {
            fightStr = defender.getName() + " took " + damage + " damage!";
        }

        return new BattleResult(
                attacker.getId(),
                defender.getId(),
                attackValue,
                defenseValue,
                damage,
                defender.getHealth(),
                isOver,
                winnerId,
                fightStr
        );
    }
}
