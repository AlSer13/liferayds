package com.liferayds.jsfportlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferayds.api.api.StringApi;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * @author almdudler
 * @date 2019-02-12
 */

@ManagedBean
@SessionScoped
public class BackingBean implements Serializable {

    private static final String DEFAULT_STRING = "default value";

    private static final Log log = LogFactoryUtil.getLog(BackingBean.class);
    private StringApiServiceTracker stringApiServiceTracker;
    private String string = DEFAULT_STRING;

    @PostConstruct
    public void postConstruct() {
        Bundle bundle = FrameworkUtil.getBundle(this.getClass());
        BundleContext bundleContext = bundle.getBundleContext();
        stringApiServiceTracker = new StringApiServiceTracker(bundleContext);
        stringApiServiceTracker.open();
        reloadString();
    }

    @PreDestroy
    public void preDestroy() {
        stringApiServiceTracker.close();
    }

    public void reloadString() {
        if (stringApiServiceTracker != null) {
            StringApi stringApi = stringApiServiceTracker.getService();

            if (stringApi != null) {
                string = stringApi.getString();
            } else {
                log.error("MyApiService not found");
            }
        } else {
            log.error("No ServiceTracker");
        }
    }

    public void defaultString(){
        this.string = DEFAULT_STRING;
    }

    public String getString(){
        return string;
    }
}

