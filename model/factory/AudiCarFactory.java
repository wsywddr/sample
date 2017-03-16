package com.wsywddr.sample.model.factory;

/**
 * �򵥹���ģʽ,�̳г�����,���ݲ�ͬ��Ʒ����class����������
 * @author Administrator
 *
 */
public class AudiCarFactory extends AudiFactory {

	@SuppressWarnings("unchecked")
	@Override
	public <T extends AudiCar> T createAudiCar(Class<T> clz) {
		AudiCar car = null;
		
		try {
			car = (AudiCar) Class.forName(clz.getName()).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (T) car;
	}

}
