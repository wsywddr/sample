package com.wsywddr.sample.model.factory;

/**
 * ���󹤳���,���ظ��ּ̳�AudiCar�ĸ����������
 * @author max
 * @time 2016��2��13��
 *
 */
public abstract class AudiFactory {
	
	public abstract <T extends AudiCar> T createAudiCar(Class<T> clz);
}
