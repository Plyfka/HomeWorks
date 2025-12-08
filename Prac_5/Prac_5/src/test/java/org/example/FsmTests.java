package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FsmTests {

    @ParameterizedTest
    @CsvSource({
            "abcTESTabc, F",
            "abcTES,     THREE",
            "TEST,       F",
            "TE,         TWO",
            "X,          S",
            "TTEST,      F",
            "TETEST,     F",
            "TESTEST,    F",
            "abcTESTabc, F"
    })
    void testOriginalFsm(String input, State expected) {
        StringFsm fsm = new StringFsm();
        assertEquals(expected, fsm.process(input));

    }

    @ParameterizedTest
    @CsvSource({
            "abcTESTabc, F",
            "abcTES,     THREE",
            "TEST,       F",
            "TE,         TWO",
            "X,          S",
            "TTEST,      F",
            "TETEST,     F",
            "TESTEST,    F",
            "abcTESTabc, F"
    })

    void testSmartFsm(String input, State expected) {
        SmartStringFsm fsm = new SmartStringFsm();
        assertEquals(expected, fsm.process(input));
    }

}
