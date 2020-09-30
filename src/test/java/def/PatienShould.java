package def;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Patien should ")
class PatienShould {
    Patien patien;
    @BeforeEach
    void init () {
         patien = Patien.of("John", "Bush", 170, 150);
    }

    @Nested
    @DisplayName("return instances should be ")
    class InstancesTests {


        @Test
        @DisplayName("the same when name and surname inserted are the same but with updated rest of the fields")
        void retunTheSamePatienBasedOnNameAndSurname() {
            Patien patienToCompare = Patien.of("John", "Bush", 195, 100);
            assertEquals(patien.hashCode(), patienToCompare.hashCode());
            assertEquals(patien.getHeight(), patienToCompare.getHeight());
            assertEquals(patien.getWeight(), patienToCompare.getWeight());
        }


        @Test
        @DisplayName("different when new name or surname is inserted")
        void retunDifferentPatienBasedOnNameAndSurname() {
            Patien patienToCompare = Patien.of("Adam", "Bush", 195, 100);
            Patien patienToCompare2 = Patien.of("John", "Adam", 195, 100);
            assertAll(
                    () -> assertNotEquals(patien.hashCode(), patienToCompare.hashCode()),
                    () -> assertNotEquals(patien.hashCode(), patienToCompare2.hashCode())
            );
        }
    }
    @DisplayName("Stop insertion of null to fields and ")
    @Nested
    class InsertNullToFieldTest {
        @Test
        @DisplayName("throw error if null set for Name")
        void nullToName() {
            assertThrows(NullPointerException.class, () -> patien.setName(null));
        }
        @Test
        @DisplayName("throw error if null set for Surname")
        void nullToSurname() {
            assertThrows(NullPointerException.class, () -> patien.setSurname(null));
        }

    }



}