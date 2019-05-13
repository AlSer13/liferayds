package com.liferayds.impl;
import com.liferayds.api.api.StringApi;
import org.osgi.service.component.annotations.Component;

/**
 * @author almdudler
 */
@Component(
	immediate = true, service = StringApi.class
)
public class StringApiImpl implements StringApi {
	@Override
	public String getString() {
		return "MyApiImplString";
	}

}