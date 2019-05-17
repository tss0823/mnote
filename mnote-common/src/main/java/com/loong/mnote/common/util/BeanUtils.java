/**
 * 
 */
package com.loong.mnote.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Bean 工具类
 * 
 * @author tangss
 * 
 * @2013-4-8 @上午10:14:03
 */
public class BeanUtils {

	private final static Logger log = LoggerFactory.getLogger(BeanUtils.class);

	/**
	 * bean 转换 Map<String,Object>
	 * 
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> beanToQueryMap(Object bean) {
		try {
			return PropertyUtils.describe(bean);
		} catch (Exception e) {
			log.error("bean to queryMap failed! ", e);
			return null;
		}
	}

	/**
	 * Map<String,Object> 转换 bean
	 * @param map
	 * @param cls
	 * @return
	 */
	public static<T> T mapToBean(Map<String,Object> map,Class cls){
		ObjectMapper mapper = new ObjectMapper();
		Object instance = mapper.convertValue(map, cls);
		return (T) instance;
	}
	
	/**
	 * bean 复制
	 * @param srcBean bean 源对象
	 * @param destClass 目标对象class
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T beanCopy(Object srcBean,Class destClass){
		if(srcBean == null){
			return null;
		}
		try {
			Object destObj = destClass.newInstance();
			PropertyUtils.copyProperties(destObj, srcBean);
			return (T) destObj;
		} catch (Exception e) {
			throw new RuntimeException("bean copy error! ",e);
		}
	}

	/**
	 * bean复制 - 批量
	 * @param source 源对象
	 * @param target 目标对象
	 */
	public static <T,S> List<T> listBeanCopy(List<S> source, Class<T> target) {
		if (CollectionUtils.isEmpty(source)) {
			return Lists.newArrayList();
		}

		List<T> targetObj = new ArrayList<T>(source.size());
		for (S s : source) {
			targetObj.add(beanCopy(s, target));
		}

		return targetObj;
	}

	/**
	 * 对象深度复制，条件各引用对象必须实现序列化
	 * @param srcObj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static<T> T objDepthCopy(T srcObj) {
		T destObj = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(srcObj);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			destObj = (T) ois.readObject();
		} catch (Exception e) {
			log.error("depth object copy failed! ", e);
			return null;
		}
		return destObj;
	}
	
	/**
	 * bean复制，对于NULL则不覆盖
	 * @param source
	 * @param target
	 */
	public static void beanCopy(Object source, Object target)  {
		beanCopy(source, target, null, null);
	}
	
	/**
	 * bean复制，对于NULL则不覆盖
	 * @param source
	 * @param target
	 * @param editable
	 * @param ignoreProperties
	 */
	public static void beanCopy(Object source, Object target, Class<?> editable, String[] ignoreProperties){
		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null");

		Class<?> actualEditable = target.getClass();
		if (editable != null) {
			if (!editable.isInstance(target)) {
				throw new IllegalArgumentException("Target class [" + target.getClass().getName() +
						"] not assignable to Editable class [" + editable.getName() + "]");
			}
			actualEditable = editable;
		}
		PropertyDescriptor[] targetPds = org.springframework.beans.BeanUtils.getPropertyDescriptors(actualEditable);
		List<String> ignoreList = (ignoreProperties != null) ? Arrays.asList(ignoreProperties) : null;

		for (PropertyDescriptor targetPd : targetPds) {
			if (targetPd.getWriteMethod() != null &&
					(ignoreProperties == null || (!ignoreList.contains(targetPd.getName())))) {
				PropertyDescriptor sourcePd = org.springframework.beans.BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
				if (sourcePd != null && sourcePd.getReadMethod() != null) {
					try {
						Method readMethod = sourcePd.getReadMethod();
						if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
							readMethod.setAccessible(true);
						}
						Object value = readMethod.invoke(source);
						Method writeMethod = targetPd.getWriteMethod();
						if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
							writeMethod.setAccessible(true);
						}
						if(value != null){
							writeMethod.invoke(target, value);
						}
					}
					catch (Throwable ex) {
						throw new FatalBeanException("Could not copy properties from source to target", ex);
					}
				}
			}
		}
	}

	/**
	 * 去掉bean中所有属性为字符串的前后空格
	 * @param bean
	 * @throws Exception
	 */
	public static void beanAttributeValueTrim(Object bean) throws Exception {
		if (bean != null) {
			//获取所有的字段包括public,private,protected,private
			Field[] fields = bean.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field f = fields[i];
				if (f.getType().getName().equals("java.lang.String")) {
					String key = f.getName();//获取字段名
					Object value = getFieldValue(bean, key);

					if (value == null)
						continue;

					setFieldValue(bean, key, value.toString().trim());
				}
			}
		}
	}

	/**
	 * 利用反射通过get方法获取bean中字段fieldName的值
	 * @param bean
	 * @param fieldName
	 * @return
	 * @throws Exception
	 */
	private static Object getFieldValue(Object bean, String fieldName)
			throws Exception {
		StringBuffer result = new StringBuffer();
		String methodName = result.append("get")
				.append(fieldName.substring(0, 1).toUpperCase())
				.append(fieldName.substring(1)).toString();

		Object rObject = null;
		Method method = null;

		@SuppressWarnings("rawtypes")
		Class[] classArr = new Class[0];
		method = bean.getClass().getMethod(methodName, classArr);
		rObject = method.invoke(bean, new Object[0]);

		return rObject;
	}

	/**
	 * 利用发射调用bean.set方法将value设置到字段
	 * @param bean
	 * @param fieldName
	 * @param value
	 * @throws Exception
	 */
	private static void setFieldValue(Object bean, String fieldName, Object value)
			throws Exception {
		StringBuffer result = new StringBuffer();
		String methodName = result.append("set")
				.append(fieldName.substring(0, 1).toUpperCase())
				.append(fieldName.substring(1)).toString();

		/**
		 * 利用发射调用bean.set方法将value设置到字段
		 */
		Class[] classArr = new Class[1];
		classArr[0]="java.lang.String".getClass();
		Method method=bean.getClass().getMethod(methodName,classArr);
		method.invoke(bean,value);
	}
	
}
