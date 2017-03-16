package com.wsywddr.sample.model.factory;

import android.util.Log;
import android.widget.Toast;

/**
 * ��������A
 * @author max
 * @time 2016��2��13��
 */
public class CarA extends AudiCar {

	@Override
	public void drive() {
		Log.e("factory", "drive Q3");
	}

	@Override
	public void selfNavigation() {
		Log.e("factory", "Q3 selfNavigation");
	}

}
