package com.joker.fourfun.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by joker on 2016/11/28.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}