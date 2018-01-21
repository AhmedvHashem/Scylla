package com.ahmednts.scylla.base;

import android.support.annotation.CallSuper;

/**
 * Created by Ahmed Hashem on 10/25/2017.
 */

public abstract class BasePresenter<V extends BaseContract.View>
    implements BaseContract.Presenter {

  protected V view;

  public BasePresenter(V view) {
    this.view = view;
  }

  @CallSuper
  @Override
  public void dispose() {
    view = null;
  }
}
