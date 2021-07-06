package com.patientco.bradhomework.business;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

public class DefaultBusinessServiceTest {

    private final Splitinator mockSplitinator = Mockito.mock(Splitinator.class);

    @Test
    void testRead() {
        String delimiter = ",";
        String path = "src/test/resources/test-input.txt";

        given(mockSplitinator.split(anyString(), anyChar())).willReturn(List.of("1", "Bob", "23", "Red"))
                .willReturn(List.of("2", "Doe, John", "99", "\"Turquoise\""));

        List<Map<String, Object>> expected = getExpectedForTestRead();
        DefaultBusinessService service = new DefaultBusinessService(mockSplitinator);
        List<Map<String, Object>> actual = service.read(delimiter, path);

        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
        then(mockSplitinator).should(times(2)).split(anyString(), anyChar());
    }

    private List<Map<String, Object>> getExpectedForTestRead() {
        return List.of(Map.of("ID", 1, "Name", "Bob", "Age", 23, "Favorite Color", "Red"),
                Map.of("ID", 2, "Name", "Doe, John", "Age", 99, "Favorite Color", "\"Turquoise\""));
    }

    @Test
    void testReadEmptyFile(){
        String delimiter = ",";
        String path = "src/test/resources/empty.txt";

        DefaultBusinessService service = new DefaultBusinessService(mockSplitinator);
        List<Map<String, Object>> actual = service.read(delimiter, path);

        assertThat(actual).isEmpty();
        then(mockSplitinator).shouldHaveNoInteractions();
    }

    @Test
    void testReadErrorFindingFile() {
        DefaultBusinessService service = new DefaultBusinessService(mockSplitinator);
        assertThrows(FailedToParseFileException.class, () -> service.read(",", "narnia"));
        then(mockSplitinator).shouldHaveNoInteractions();
    }

}

