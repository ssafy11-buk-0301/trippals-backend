package com.ssafy.trippals;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Tag("integration")
@SpringBootTest
class TrippalsApplicationTests {

    @Test
    void contextLoads() {
        String[] args = new String[0];
        Assertions.assertThatNoException()
                .isThrownBy(() -> TrippalsApplication.main(args));
    }

}
