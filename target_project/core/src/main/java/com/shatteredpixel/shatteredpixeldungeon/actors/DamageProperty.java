package com.shatteredpixel.shatteredpixeldungeon.actors;

/*
 Properties that define how a damage instance should be handled by the target.
 This replaces hardcoded type checks (e.g., instanceof Hunger)
    with an extensible DamageProperty system as requested by the FIXME.
 */
public enum DamageProperty {
    /*
     Damage bypasses all shield calculations. 
     This replaces the old hardcoded "!(src instanceof Hunger)" check.
     */
    IGNORES_SHIELDS
}
