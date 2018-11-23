package com.ahmednts.scylla;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.ahmednts.scylla.utils.Logger;

public class MainActivity
    extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Logger.tag("MainActivity")
        .d("asdasdlkahd kjlasd kjhgdjl gaawdawdawdawdawdawd aw da wd awd aw da sjkd hgaskd");
    Logger.tag("HomeActivity").d("4 kjlasd 4565 gasjkd hgaskd");
    Logger.tag("MainActivity").d("piopiop kjlasd kjhgdjl gasjkd hgaskd");
    Logger.tag("MainActivity").d("45645 kjlasd kjhgdjl gasjkd hgaskd");
    Logger.d("rty kjlasd kjhgd hgaskd");
  }
}
