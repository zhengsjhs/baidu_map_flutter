package com.github.zhengsjhs.baidumaps;

import static io.flutter.plugin.common.PluginRegistry.Registrar;

import android.content.Context;
import android.content.ContextWrapper;
import android.app.Presentation;
import android.util.Log;

import com.baidu.mapapi.map.MyLocationData;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugin.platform.PlatformViewFactory;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class BaiduMapFactory extends PlatformViewFactory {

  private final AtomicInteger mActivityState;
  private final Registrar mPluginRegistrar;

  public BaiduMapFactory(AtomicInteger state, Registrar registrar) {
    super(StandardMessageCodec.INSTANCE);
    mActivityState = state;
    mPluginRegistrar = registrar;
  }

  @SuppressWarnings("unchecked")
  @Override
  public PlatformView create(Context context, int id, Object args) {
    Map<String, Object> params = (Map<String, Object>) args;
    final BaiduMapBuilder builder = new BaiduMapBuilder();
    //Presentation presentation = (Presentation)((ContextWrapper)context).getBaseContext();
   // Log.i("baidumap", "Dialog "+presentation.getContext().toString());
    Convert.interpretGoogleMapOptions(params.get("options"), builder);
    if (params.containsKey("initialMyLocationData")) {
      MyLocationData position = Convert.toMyLocationData(params.get("initialMyLocationData"));
      builder.setInitialMyLocationData(position);
    }
    return builder.build(id, mPluginRegistrar.activity(), mActivityState, mPluginRegistrar);
  }
}
