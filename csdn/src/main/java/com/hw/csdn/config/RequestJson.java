package com.hw.csdn.config;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Documented
public @interface RequestJson {

}