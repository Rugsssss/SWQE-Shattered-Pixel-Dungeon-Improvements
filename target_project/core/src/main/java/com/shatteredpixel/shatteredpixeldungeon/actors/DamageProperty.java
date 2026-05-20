package com.shatteredpixel.shatteredpixeldungeon.actors;

import java.util.Set;
import java.util.EnumSet;

/*
This include properties that define how a damage instance should be handled by the target.
This replaces hardcoded type checks (like the prev instanceof Hunger) with an extensible DamageProperty system,
 as requested by the FIXME.

Why I thought of this Design?:
    Each enum constant represents a distinct game mechanic that alters
    damage calculation. This approach improves Maintainability (ISO 25010)
    by centralizing damage behavior rules, making it easier to add new
    damage types without modifying Char.damage() logic.
 */
public enum DamageProperty {
    /*
      Damage bypasses all shield calculations (like the ShieldBuff.absorbDamage).
      Replaces the old hardcoded "!(src instanceof Hunger)" check.
    */
    IGNORES_SHIELDS,
    
    /*
     Damage ignores all resistance modifiers from the target.
     The damage is applied at 100% effectiveness regardless of
     the target's resist() method returns.
    */
    IGNORES_RESISTANCE,
    
    /*
     True damage - cannot be reduced by any means (armor, shields, 
     resistances, invulnerability). Only Death Mark and Life Link
     can still affect the damage flow.
    */
    TRUE_DAMAGE,
    
    /*
     Damage is unblockable - bypasses block chance calculations
     in the attack flow before damage is even dealt.
    */
    UNBLOCKABLE;
    
    
    // check if damage has any special properties.
    public static boolean hasProperty(Set<DamageProperty> properties, DamageProperty prop) {
        return properties != null && properties.contains(prop);
    }
    
     // check if damage has ANY special properties.
    public static boolean hasAnyProperties(Set<DamageProperty> properties) {
        return properties != null && !properties.isEmpty();
    }
    
    
    // Pre-built property sets for common damage types.
    // (This reduces code duplication across blob implementations).
    
    public static final Set<DamageProperty> IGNORES_SHIELDS_SET = 
        EnumSet.of(IGNORES_SHIELDS);
    public static final Set<DamageProperty> IGNORES_RESISTANCE_SET = 
        EnumSet.of(IGNORES_RESISTANCE);
    public static final Set<DamageProperty> TRUE_DAMAGE_SET = 
        EnumSet.of(TRUE_DAMAGE, IGNORES_SHIELDS, IGNORES_RESISTANCE);
    public static final Set<DamageProperty> UNBLOCKABLE_SET = 
        EnumSet.of(UNBLOCKABLE);
}
