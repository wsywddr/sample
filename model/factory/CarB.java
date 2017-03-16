package com.wsywddr.sample.model.factory;

import android.util.Log;

/**
 * ��������B
 * @author max
 * @time 2016��2��13��
 */
public class CarB extends AudiCar {

	@Override
	public void drive() {
		Log.e("factory", "drive A6");

	}

	@Override
	public void selfNavigation() {
		Log.e("factory", "A6 selfNavigation");

	}

}
