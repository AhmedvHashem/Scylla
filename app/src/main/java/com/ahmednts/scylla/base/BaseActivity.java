package com.ahmednts.scylla.base;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Ahmed Hashem on 10/25/2017.
 */

public abstract class BaseActivity<P extends BaseContract.Presenter>
    extends AppCompatActivity
    implements BaseContract.View<P> {

  protected P presenter;

  @Override
  public void setPresenter(P presenter) {
    this.presenter = presenter;
  }

  @Override
  protected void onDestroy() {
    if (presenter != null) presenter.dispose();
    presenter = null;
    super.onDestroy();
  }

  public AppCompatActivity getMyActivity() {
    return this;
  }

  public Context getMyContext() {
    return this;
  }

  public Context getMyAppContext() {
    return getApplicationContext();
  }
}
