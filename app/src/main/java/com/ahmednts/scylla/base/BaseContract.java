package com.ahmednts.scylla.base;

/**
 * Created by Ahmed Hashem on 1/21/2018.
 */

public interface BaseContract {

  interface Presenter {

    void dispose();
  }

  interface View<P extends Presenter> {

    void setPresenter(P presenter);
  }
}
