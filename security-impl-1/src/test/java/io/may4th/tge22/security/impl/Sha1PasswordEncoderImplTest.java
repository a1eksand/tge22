package io.may4th.tge22.security.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Sha1PasswordEncoderImplTest {

    @Test
    void testMatches() {
        var passwordEncoder = new Sha1PasswordEncoderImpl();
        assertTrue(passwordEncoder.matches("XXX", "BexnfeM0a0Vlhob9j0cn4m64hQgeGu+RSGc96G4mGxTDJxQi0kpYV+Gpn4EK8fBaWVkcWQ=="));
        assertTrue(passwordEncoder.matches("XXX", passwordEncoder.encode("XXX")));
        assertFalse(passwordEncoder.matches("XXX", passwordEncoder.encode("ZZZ")));
    }
}
