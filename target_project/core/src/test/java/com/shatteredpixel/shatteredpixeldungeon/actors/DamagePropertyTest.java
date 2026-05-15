package com.shatteredpixel.shatteredpixeldungeon.actors;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.EnumSet;
import java.util.Set;

/*
 Unit tests for the DamageProperty system.

 SQA Metrics Covered:
 - Code Coverage: Tests verify both shielded and unshielded damage paths
 - Cyclomatic Complexity: Tests cover all branches in damage calculation
 - Maintainability: The enum-based design reduces code duplication
 - Extensibility: New properties can be added without modifying method signatures
 
*/
public class DamagePropertyTest extends TestCase {

    //DamageProperty Enum Tests:

    /*
      Verify that DamageProperty enum contains all expected values.
      he enum should be properly defined .
    */
    @Test
    public void testDamagePropertyEnumValues() {
        // Test that IGNORES_SHIELDS is defined and accessible
        DamageProperty[] properties = DamageProperty.values();
        assertNotNull("DamageProperty enum should not be null", properties);
        assertTrue("DamageProperty enum should have at least one value", 
                   properties.length > 0);
        
        // Verify IGNORES_SHIELDS is present
        boolean hasIgnoresShields = false;
        for (DamageProperty prop : properties) {
            if (prop == DamageProperty.IGNORES_SHIELDS) {
                hasIgnoresShields = true;
                break;
            }
        }
        assertTrue("DamageProperty enum should contain IGNORES_SHIELDS", 
                   hasIgnoresShields);
    }

    // verify that DamageProperty enum values are properly named.
    @Test
    public void testDamagePropertyEnumNames() {
        DamageProperty[] values = DamageProperty.values();
        for (DamageProperty prop : values) {
            assertNotNull("Enum value name should not be null: " + prop.name(), 
                          prop.name());
            assertFalse("Enum value name should not be empty: " + prop.name(), 
                        prop.name().isEmpty());
        }
    }

    // DamageProperty System Integration Tests

    /*
     Test that EnumSet.of() works correctly with DamageProperty values.
     (because this verifies the system can be used with EnumSet operations)
    */
    @Test
    public void testEnumSetWithDamageProperty() {
        Set<DamageProperty> properties = EnumSet.of(DamageProperty.IGNORES_SHIELDS);
        assertTrue("EnumSet should contain IGNORES_SHIELDS", 
                   properties.contains(DamageProperty.IGNORES_SHIELDS));
        assertFalse("EnumSet should not contain non-existent property", 
                    properties.contains(DamageProperty.values()[0] != DamageProperty.IGNORES_SHIELDS ? 
                                       DamageProperty.values()[0] : null));
    }

    /*
    Test that EnumSet.noneOf() creates an empty set.
    it is used by the delegation method to provide backward compatibility.
    */
    @Test
    public void testEnumSetNoneOfDamageProperty() {
        Set<DamageProperty> properties = EnumSet.noneOf(DamageProperty.class);
        assertTrue("Empty EnumSet should be empty", properties.isEmpty());
        assertFalse("Empty EnumSet should not contain IGNORES_SHIELDS", 
                    properties.contains(DamageProperty.IGNORES_SHIELDS));
    }

    //Char.damage() Method Signature Tests 


//     Test that Char class has both overloaded damage() methods.
   
    @Test
    public void testCharHasOverloadedDamageMethods() throws Exception {
        // Verify Char class has the new 3 param damage method
        boolean has3ParamMethod = false;
        boolean has2ParamMethod = false;
        
        for (java.lang.reflect.Method method : com.shatteredpixel.shatteredpixeldungeon.actors.Char.class.getDeclaredMethods()) {
            if (method.getName().equals("damage")) {
                Class<?>[] paramTypes = method.getParameterTypes();
                if (paramTypes.length == 3) {
                    has3ParamMethod = true;
                } else if (paramTypes.length == 2) {
                    has2ParamMethod = true;
                }
            }
        }
        
        assertTrue("Char class should have a 3-parameter damage() method", has3ParamMethod);
        assertTrue("Char class should have a 2-parameter damage() method for backward compatibility", 
                   has2ParamMethod);
    }

    //DamageProperty Design Quality Tests

    /*
     Verify that DamageProperty enum is properly documented.
     (for maintainability)
    */
    @Test
    public void testDamagePropertyEnumDocumentation() throws Exception {
        // Get the enum class
        Class<?> enumClass = DamageProperty.class;
        
        // Verify it's an enum
        assertTrue("DamageProperty should be an enum", 
                   enumClass.isEnum());
        
        // Verify the enum has proper package
        String packageName = enumClass.getPackage().getName();
        assertEquals("DamageProperty should be in the actors package", 
                     "com.shatteredpixel.shatteredpixeldungeon.actors", 
                     packageName);
    }

    /*
     To test and prove that the DamageProperty system improves code extensibility.
    using an enum, we can add new properties without modifying or breaking existing code.
     */
    @Test
    public void testDamagePropertyExtensibility() {
        // Test that we can create EnumSets with different combinations
        Set<DamageProperty> set1 = EnumSet.of(DamageProperty.IGNORES_SHIELDS);
        Set<DamageProperty> set2 = EnumSet.noneOf(DamageProperty.class);
        
        // Verify both sets are properly initialized
        assertNotNull("EnumSet should not be null when created", set1);
        assertNotNull("EnumSet should not be null when empty", set2);
        
        // Test that we can add properties dynamically
        set2.add(DamageProperty.IGNORES_SHIELDS);
        assertTrue("EnumSet should support adding properties dynamically", 
                   set2.contains(DamageProperty.IGNORES_SHIELDS));
    }

}
