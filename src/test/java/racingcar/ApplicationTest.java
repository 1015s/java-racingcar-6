package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    void 전진_정지() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi,woni", "1");
                assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
            },
            MOVING_FORWARD, STOP
        );
    }

    @Test
    void 지문_예시() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni,jun", "5");
                    assertThat(output()).contains(
                            "pobi : -", "woni : ", "jun : -",
                            "pobi : --", "woni : -", "jun : --",
                            "pobi : ---", "woni : --", "jun : ---",
                            "pobi : ----", "woni : ---", "jun : ----",
                            "pobi : -----", "woni : ----", "jun : -----",
                            "최종 우승자 : pobi, jun");
                },
                MOVING_FORWARD, STOP,MOVING_FORWARD,
                MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD,
                MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD,
                MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD,
                MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD
        );
    }

    @Test
    void 이름에_대한_예외_처리() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 자동차이름_공백_입력_예외_처리(){
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("", "1"))
                        .isInstanceOf(IllegalArgumentException.class));
    }

    @Test
    void 자동차이름_마지막_쉼표_예외_처리(){
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,", "1"))
                        .isInstanceOf(IllegalArgumentException.class));
    }

    @Test
    void 이동횟수_공백_입력_예외_처리(){
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi", " "))
                        .isInstanceOf(IllegalArgumentException.class));
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
