package com.supinfo.java.day1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StudentsGradeNotationTests {

    @Test
    void testSwitchCase_thenPostponed() {
        // Given
        StudentsGradeNotation studentsGradeNotation = new StudentsGradeNotation();

        // When
        String notation = studentsGradeNotation.evaluateStudent(5);

        // Then
        Assertions.assertNotNull(notation);
        Assertions.assertEquals("Postponed", notation);
    }

    @Test
    void testSwitchCase_thenFair() {
        // Given
        StudentsGradeNotation studentsGradeNotation = new StudentsGradeNotation();

        // When
        String notation = studentsGradeNotation.evaluateStudent(11);

        // Then
        Assertions.assertNotNull(notation);
        Assertions.assertEquals("Fair", notation);
    }

    @Test
    void testSwitchCase_thenFairlyGood() {
        // Given
        StudentsGradeNotation studentsGradeNotation = new StudentsGradeNotation();

        // When
        String notation = studentsGradeNotation.evaluateStudent(13);

        // Then
        Assertions.assertNotNull(notation);
        Assertions.assertEquals("Fairly good", notation);
    }

    @Test
    void testSwitchCase_thenGood() {
        // Given
        StudentsGradeNotation studentsGradeNotation = new StudentsGradeNotation();

        // When
        String notation = studentsGradeNotation.evaluateStudent(15);

        // Then
        Assertions.assertNotNull(notation);
        Assertions.assertEquals("Good", notation);
    }

    @Test
    void testSwitchCase_thenVeryGood() {
        // Given
        StudentsGradeNotation studentsGradeNotation = new StudentsGradeNotation();

        // When
        String notation = studentsGradeNotation.evaluateStudent(17);

        // Then
        Assertions.assertNotNull(notation);
        Assertions.assertEquals("Very good", notation);
    }

    @Test
    void testSwitchCase_thenExcellent() {
        // Given
        StudentsGradeNotation studentsGradeNotation = new StudentsGradeNotation();

        // When
        String notation = studentsGradeNotation.evaluateStudent(20);

        // Then
        Assertions.assertNotNull(notation);
        Assertions.assertEquals("Excellent", notation);
    }
}
