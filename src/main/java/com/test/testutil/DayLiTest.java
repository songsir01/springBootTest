/**
 * Project Name:SpringBootTest
 * File Name:DayLiTest.java
 * Package Name:com.test.testutil
 * Date:2018年2月24日上午11:23:09
 * Copyright (c) 2018, songsir01@163.com All Rights Reserved.
 *
*/

package com.test.testutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;


/**
 * ClassName:DayLiTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年2月24日 上午11:23:09 <br/>
 * @author   SongYapeng
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class DayLiTest {
	
	public static void main(String[] args) {
		try {
			FileOutputStream fileStream = new FileOutputStream("MyGame.ser");
			ObjectOutputStream oS = new ObjectOutputStream(fileStream);
			
			oS.writeObject("abc1");
			oS.writeObject("abc2");
			oS.writeObject("abc3");
			
			oS.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
			
		}
		
	}

}
class fanxulie{
	public static void main(String[] args) {
		try {
			FileInputStream inputStream = new FileInputStream("MyGame.ser");
			
			ObjectInputStream oStream = new ObjectInputStream(inputStream);
			
			Object one = oStream.readObject();
			Object two = oStream.readObject();
			Object three = oStream.readObject();
			
			oStream.close();
			
			System.out.println(one);
			System.out.println(two);
			System.out.println(three);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	}
}

class FileRead{
	public static void main(String[] args) {
		try {
			File file = new File("MyGame.ser");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = null;
			while((line=bufferedReader.readLine())!=null) {
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
}
class thissi{
	public static void main(String[] args) {
		
		try {
			Socket socket = new Socket("127.0.0.1", 44244);
			
			InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
			
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			String readLine = bufferedReader.readLine();
			
			System.out.println("this:"+readLine);
			
			bufferedReader.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
		
	}
}



class VerySimpleChatServer {
	ArrayList clientOutputStream;
	
	public class ClientHandler implements Runnable {
		
		BufferedReader reader;
		Socket sock;
		
		public void ClientHandler(Socket clientSoket) {
			try {
				sock = clientSoket;
				InputStreamReader usReader = new InputStreamReader(sock.getInputStream());
				reader = new BufferedReader(usReader);
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		@Override
		public void run() {
			String message;
			try {
				while ((message = reader.readLine())!= null) {
					System.out.println("read "+message);
					tellEveryOne(message);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		
		
		
	}
	private void tellEveryOne(String message) {
		Iterator it = clientOutputStream.iterator();
		while(it.hasNext()) {
			try {
				PrintWriter writer = (PrintWriter) it.next();
				writer.println(message);
				writer.flush();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	public void go(){
		clientOutputStream = new ArrayList();
		
	}
	
}

class MySort{
	ArrayList<String> songList = new ArrayList<String>();
	
	void getSongs() {
		try {
			File file = new File("E://songList.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String line = null;
			while((line = reader.readLine())!= null) {
				addSong(line);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	void addSong(String lineToParse) {
		String[] tokents = lineToParse.split("/");
		songList.add(tokents[0]);
	}
	
	public void go(){
		getSongs();
		System.out.println("排序前："+songList);
		Collections.sort(songList);
		System.out.println("排序后："+songList);
	}
	public static void main(String[] args) {
		new MySort().go();
	}
}


class Person {
	private int pid;
	private String name;
	private String sex;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Person(int pid, String name, String sex) {
		super();
		this.pid = pid;
		this.name = name;
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "Person [pid=" + pid + ", name=" + name + ", sex=" + sex + "]";
	}
	
	
	
}

class TestSortMy {
	
	static ArrayList<Person> pList = new ArrayList<Person>();
	
	
	static class ArtListCompara implements Comparator<Person> {

		@Override
		public int compare(Person o1, Person o2) {
			
			return o1.getName().compareTo(o2.getName());
		}
		
	}
	
	public static void main(String[] args) {
		Person p1 = new Person(1,"aa","nv");
		Person p2 = new Person(2,"xx","na");
		Person p3 = new Person(3,"ss","na");
		Person p4 = new Person(4,"bb","nv");
		pList.add(p1);
		pList.add(p2);
		pList.add(p3);
		pList.add(p4);
		
		for (Person p : pList) {
			System.out.print(p.toString()+",");
		}
		System.out.println();
		ArtListCompara artListCompara = new ArtListCompara();
		Collections.sort(pList,artListCompara);
		
		for (Person p : pList) {
			System.out.print(p.toString()+",");
		}
		
		while(true) {
			System.out.println(123);
		}
		
	}
	
}
class sadkjlag {
	
	public static void main(String[] args) {
		DecimalFormat format = new DecimalFormat("#0.00");
		String format2 = format.format(0.2);
		System.out.println(format2);
		
	}
}

class hhh{
	
	public static void main(String[] args) {
		
		aa();
		
	}
	public static int aa() {
		
		Random a = new Random();
		System.out.println(a);
		
		
		return 0;
	}
}