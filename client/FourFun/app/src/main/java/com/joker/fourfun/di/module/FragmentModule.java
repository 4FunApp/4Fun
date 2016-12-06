package com.joker.fourfun.di.module;

import com.joker.fourfun.di.PerFragment;

import dagger.Module;
import dagger.Provides;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by joker on 2016/12/1.
 */
@Module
public class FragmentModule {
    private SupportFragment mFragment;

    public FragmentModule(SupportFragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @PerFragment
    SupportFragment provideSupportFragment() {
        return mFragment;
    }
}
