// Generated code from Butter Knife. Do not modify!
package com.mncomunity1.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class MainActivityTap$$ViewInjector<T extends com.mncomunity1.activity.MainActivityTap> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493014, "field 'mViewpager'");
    target.mViewpager = finder.castView(view, 2131493014, "field 'mViewpager'");
    view = finder.findRequiredView(source, 2131493013, "field 'mTabs'");
    target.mTabs = finder.castView(view, 2131493013, "field 'mTabs'");
  }

  @Override public void reset(T target) {
    target.mViewpager = null;
    target.mTabs = null;
  }
}
