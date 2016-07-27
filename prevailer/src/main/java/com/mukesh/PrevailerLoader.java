package com.mukesh;

import android.content.Context;
import android.support.v4.content.Loader;

public class PrevailerLoader<I> extends Loader<I> {

  private I preservedInstance;
  private final PrevailerFactory<I> factory;

  /**
   * Stores away the application context associated with context.
   * Since Loaders can be used across multiple activities it's dangerous to
   * store the context directly; always use {@link #getContext()} to retrieve
   * the Loader's Context, don't use the constructor argument directly.
   * The Context returned by {@link #getContext} is safe to use across
   * Activity instances.
   *
   * @param context used to retrieve the application context.
   */
  public PrevailerLoader(Context context, PrevailerFactory<I> factory) {
    super(context);
    this.factory = factory;
  }

  @Override protected void onStartLoading() {
    if (preservedInstance != null) {
      deliverResult(preservedInstance);
    } else {
      forceLoad();
    }
  }

  @Override protected void onForceLoad() {
    this.preservedInstance = factory.create();
    deliverResult(this.preservedInstance);
  }
}
