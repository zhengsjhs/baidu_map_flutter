// Copyright 2018 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package com.github.zhengsjhs.baidumaps;

import android.content.Context;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import io.flutter.plugin.common.PluginRegistry;
import java.util.concurrent.atomic.AtomicInteger;

class BaiduMapBuilder implements BaiduMapOptionsSink {
  private final BaiduMapOptions options = new BaiduMapOptions();
  private boolean trackCameraPosition = false;
  private boolean myLocationEnabled = false;

  BaiduMapController build(
      int id, Context context, AtomicInteger state, PluginRegistry.Registrar registrar) {
    final BaiduMapController controller =
        new BaiduMapController(id, context, state, registrar, options);
    controller.init();
    controller.setMyLocationEnabled(myLocationEnabled);
    controller.setTrackCameraPosition(trackCameraPosition);
    return controller;
  }

  public void setInitialMyLocationData(MyLocationData position) {
    if(position != null) {
      options.mapStatus(new MapStatus.Builder().target(new LatLng(position.latitude, position.longitude)).zoom(12.0F).build());
    }
  }

  @Override
  public void setCompassEnabled(boolean compassEnabled) {
    options.compassEnabled(compassEnabled);
  }

  @Override
  public void setCameraTargetBounds(LatLngBounds bounds) {
    if(bounds != null) {
      options.mapStatus(new MapStatus.Builder().target(bounds.getCenter()).zoom(12.0F).build());
    }
  }

  @Override
  public void setMapType(int mapType) {
    options.mapType(mapType);
  }

  @Override
  public void setMinMaxZoomPreference(Float min, Float max) {
    /*if (min != null) {
      options.minZoomPreference(min);
    }
    if (max != null) {
      options.maxZoomPreference(max);
    }*/
  }

  @Override
  public void setTrackCameraPosition(boolean trackCameraPosition) {
    this.trackCameraPosition = trackCameraPosition;
  }

  @Override
  public void setRotateGesturesEnabled(boolean rotateGesturesEnabled) {
    options.rotateGesturesEnabled(rotateGesturesEnabled);
  }

  @Override
  public void setScrollGesturesEnabled(boolean scrollGesturesEnabled) {
    options.scrollGesturesEnabled(scrollGesturesEnabled);
  }

  @Override
  public void setTiltGesturesEnabled(boolean tiltGesturesEnabled) {
    //options.tiltGesturesEnabled(tiltGesturesEnabled);
  }

  @Override
  public void setZoomGesturesEnabled(boolean zoomGesturesEnabled) {
    options.zoomGesturesEnabled(zoomGesturesEnabled);
  }

  @Override
  public void setMyLocationEnabled(boolean myLocationEnabled) {
    this.myLocationEnabled = myLocationEnabled;
  }
}
