package dev.helis.helper.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.Tag;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Tag("unit-tests")
public @interface UnitTest {

}
