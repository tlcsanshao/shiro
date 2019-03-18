package com.sanshao.shiro.config;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;


public class Util {

	public static String getEncryptedPassword(String username,String password){
		//原始 密码
		String source = password;
		//盐
		String salt = username+"mars";
		//散列次数
		int hashIterations = 10;

		//构造方法中：
		//第一个参数：明文，原始密码
		//第二个参数：盐，通过使用随机数
		//第三个参数：散列的次数，比如散列两次，相当 于md5(md5(''))
		Md5Hash md5Hash = new Md5Hash(source, salt, hashIterations);
		String password_md5 = md5Hash.toString();
		System.out.println(password_md5);

		//第一个参数：散列算法
		SimpleHash simpleHash = new SimpleHash("md5", source, salt, hashIterations);
		return simpleHash.toString();
	}

}
