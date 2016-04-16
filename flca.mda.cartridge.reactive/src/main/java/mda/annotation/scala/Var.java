package mda.annotation.scala;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({FIELD}) 
@Retention(RUNTIME)

/**
 * this annotation indicates that this field is a "var".
 * Note default is "var"
 */
public @interface Var {
}
