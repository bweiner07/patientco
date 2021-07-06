package com.patientco.bradhomework.business;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SplitinatiorTest {

    private final Splitinator splitinator = new Splitinator();
    @Test
    void testSplit(){
        String unsplit = "2, \"Doe, John\", 99, \"\"\"Turquoise\"\"\"";
        List<String> expected = List.of("2", "Doe, John", "99", "\"Turquoise\"");
        List<String> actual = splitinator.split(unsplit, ',');
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    void testNullString(){
        List<String> actual = splitinator.split(null, ',');
        assertThat(actual).isEmpty();
    }

}
