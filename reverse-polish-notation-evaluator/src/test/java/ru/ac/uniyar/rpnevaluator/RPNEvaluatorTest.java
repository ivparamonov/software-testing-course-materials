package ru.ac.uniyar.rpnevaluator;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.EmptyStackException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@RunWith(JUnitParamsRunner.class)
public class RPNEvaluatorTest {

    private static Object[] tc(String rpn, Object result) {
        return new Object[] {
                rpn.isEmpty() ? new String[0] : rpn.split(","), result
        };
    }

}
