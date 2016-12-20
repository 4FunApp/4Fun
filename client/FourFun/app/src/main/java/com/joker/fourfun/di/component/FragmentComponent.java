package com.joker.fourfun.di.component;

import com.joker.fourfun.di.PerFragment;
import com.joker.fourfun.di.module.FragmentModule;
import com.joker.fourfun.ui.fragment.MediaFragment;
import com.joker.fourfun.ui.fragment.PictureChildFragment;
import com.joker.fourfun.ui.fragment.PictureFragment;
import com.joker.fourfun.ui.fragment.ReadFragment;

import dagger.Component;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by joker on 2016/12/1.
 */
@PerFragment
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    SupportFragment fragment();

    void inject(ReadFragment fragment);

    void inject(PictureFragment fragment);

    void inject(PictureChildFragment fragment);

    void inject(MediaFragment fragment);
}
