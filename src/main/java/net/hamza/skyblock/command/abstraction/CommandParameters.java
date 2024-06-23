package net.hamza.skyblock.command.abstraction;

import net.hamza.skyblock.rank.SkyBlockRank;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandParameters {
    String[] aliases() default {};
    String description() default "";
    String usages() default "/<command>";
    SkyBlockRank rank() default SkyBlockRank.DEFAULT;
    boolean requireOperator() default false;
}
