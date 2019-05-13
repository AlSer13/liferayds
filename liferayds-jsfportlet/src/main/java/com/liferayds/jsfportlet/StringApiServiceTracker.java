package com.liferayds.jsfportlet;

import com.liferayds.api.api.StringApi;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class StringApiServiceTracker extends ServiceTracker<StringApi, StringApi> {

    public StringApiServiceTracker(BundleContext bundleContext) {
        super(bundleContext, StringApi.class, null);
    }
}