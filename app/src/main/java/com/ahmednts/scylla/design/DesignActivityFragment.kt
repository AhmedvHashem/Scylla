package com.ahmednts.scylla.design

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ahmednts.scylla.R

/**
 * A placeholder fragment containing a simple view.
 */
class DesignActivityFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_design, container, false)
  }
}
