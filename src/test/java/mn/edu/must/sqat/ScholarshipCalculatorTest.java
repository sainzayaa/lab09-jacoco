package mn.edu.must.sqat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ScholarshipCalculatorTest {

    private final ScholarshipCalculator calc = new ScholarshipCalculator();

    @Test void letterGradeA() { assertEquals("A", calc.letterGrade(95)); }
    @Test void letterGradeB() { assertEquals("B", calc.letterGrade(85)); }
    @Test void gpaTwoScores() { assertEquals(3.5, calc.gpa(new double[]{95, 85}), 0.001); }
    @Test void scholarshipFull() { assertEquals(100, calc.scholarship(3.9, 16, false)); }
    @Test void scholarshipSeventy() { assertEquals(70, calc.scholarship(3.5, 13, false)); }

    @Test void letterGradeC() { assertEquals("C", calc.letterGrade(75)); }
    @Test void letterGradeD() { assertEquals("D", calc.letterGrade(65)); }
    @Test void letterGradeF() { assertEquals("F", calc.letterGrade(30)); }
    @Test void letterGradeNegativeThrows() { assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(-1)); }
    @Test void letterGradeOver100Throws() { assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(101)); }

    @Test void gpaNullThrows() { assertThrows(IllegalArgumentException.class, () -> calc.gpa(null)); }
    @Test void gpaEmptyThrows() { assertThrows(IllegalArgumentException.class, () -> calc.gpa(new double[]{})); }
    @Test void gpaLowGradesCDF() { assertEquals(1.0, calc.gpa(new double[]{75, 65, 30}), 0.001); }

    @Test void scholarshipNegativeGpaThrows() { assertThrows(IllegalArgumentException.class, () -> calc.scholarship(-0.1, 16, false)); }
    @Test void scholarshipGpaOver4Throws() { assertThrows(IllegalArgumentException.class, () -> calc.scholarship(4.1, 16, false)); }
    @Test void scholarshipNegativeCreditsThrows() { assertThrows(IllegalArgumentException.class, () -> calc.scholarship(3.5, -1, false)); }
    @Test void scholarshipViolationGivesZero() { assertEquals(0, calc.scholarship(3.9, 16, true)); }
    @Test void scholarshipHighGpaLowCreditsGivesFifty() { assertEquals(50, calc.scholarship(3.9, 10, false)); }
    @Test void scholarshipMidGpaLowCreditsGivesFifty() { assertEquals(50, calc.scholarship(3.5, 10, false)); }
    @Test void scholarshipLowGpaEnoughCreditsGivesFifty() { assertEquals(50, calc.scholarship(3.2, 16, false)); }
    @Test void scholarshipSeventyWith16Credits() { assertEquals(70, calc.scholarship(3.5, 16, false)); }
    @Test void scholarshipLowGpaGivesZero() { assertEquals(0, calc.scholarship(2.0, 16, false)); }

    // ---- Part C: loop boundary (0 / 1 / 2+) ----
    @Test void gpaSingleScore() { assertEquals(4.0, calc.gpa(new double[]{95}), 0.001); }
    @Test void gpaManyScores() { assertEquals(2.5, calc.gpa(new double[]{95, 85, 75, 65}), 0.001); }

    // ---- Part D: boundary values ----
    @Test void boundary60IsD() { assertEquals("D", calc.letterGrade(60)); }
    @Test void boundary5999IsF() { assertEquals("F", calc.letterGrade(59.99)); }
    @Test void boundary6999IsD() { assertEquals("D", calc.letterGrade(69.99)); }
    @Test void boundary0IsF() { assertEquals("F", calc.letterGrade(0)); }
    @Test void boundary100IsA() { assertEquals("A", calc.letterGrade(100)); }
    @Test void boundary8999IsB() { assertEquals("B", calc.letterGrade(89.99)); }
    @Test void boundary90IsA() { assertEquals("A", calc.letterGrade(90)); }
}
