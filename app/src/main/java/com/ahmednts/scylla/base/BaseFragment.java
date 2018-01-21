package com.ahmednts.scylla.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Ahmed Hashem on 10/25/2017.
 */

public abstract class BaseFragment<P extends BaseContract.Presenter>
    extends Fragment
    implements BaseContract.View<P> {

  protected P presenter;

  @Override
  public void setPresenter(P presenter) {
    this.presenter = presenter;
  }

  @Override
  public void onDestroy() {
    if (presenter != null) presenter.dispose();
    presenter = null;
    super.onDestroy();
  }

  public FragmentActivity getMyActivity() {
    return getActivity();
  }

  public Context getMyContext() {
    return getContext();
  }

  public Context getMyAppContext() {
    return getContext().getApplicationContext();
  }
}
