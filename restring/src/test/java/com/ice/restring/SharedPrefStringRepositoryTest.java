package com.ice.restring;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class SharedPrefStringRepositoryTest {

    @Before
    public void setUp() {
    }

    @Test
    public void shouldSetAndGetStringPairs() {
        final String LANGUAGE = "en";
        Map<String, String> strings = generateStrings(10);

        StringRepository stringRepository = new SharedPrefStringRepository(RuntimeEnvironment.application);
        stringRepository.setStrings(LANGUAGE, strings);

        StringRepository newRepository = new SharedPrefStringRepository(RuntimeEnvironment.application);
        assertEquals(strings, newRepository.getStrings(LANGUAGE));
    }

    @Test
    public void shouldGetSingleString() {
        final String LANGUAGE = "en";
        final int STR_COUNT = 10;
        Map<String, String> strings = generateStrings(STR_COUNT);

        StringRepository stringRepository = new SharedPrefStringRepository(RuntimeEnvironment.application);
        stringRepository.setStrings(LANGUAGE, strings);

        StringRepository newRepository = new SharedPrefStringRepository(RuntimeEnvironment.application);
        for (int i = 0; i < STR_COUNT; i++) {
            assertEquals(newRepository.getString(LANGUAGE, "key" + i), "value" + i);
        }
    }

    @Test
    public void shouldSetSingleString() {
        final String LANGUAGE = "en";
        final int STR_COUNT = 10;
        Map<String, String> strings = generateStrings(STR_COUNT);

        StringRepository stringRepository = new SharedPrefStringRepository(RuntimeEnvironment.application);
        stringRepository.setStrings(LANGUAGE, strings);
        stringRepository.setString(LANGUAGE, "key5", "aNewValue");

        StringRepository newRepository = new SharedPrefStringRepository(RuntimeEnvironment.application);
        assertEquals(newRepository.getString(LANGUAGE, "key5"), "aNewValue");
    }

    private Map<String, String> generateStrings(int count) {
        Map<String, String> strings = new LinkedHashMap<>();
        for (int i = 0; i < count; i++) {
            strings.put("key" + i, "value" + i);
        }
        return strings;
    }
}