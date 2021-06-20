package com.ashwin;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection mysqlConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/mysqldemo",
					"ashwin", "ashwin");
			
			
			long startTime = System.currentTimeMillis();
			int i = 1;
			for (; i <= 1000; i++) {

				User user = new User();
				user.setFirstName("FirstName " + i);
				user.setSurname("surName " + i);
				user.setAge(i);
				user.setHeight((int) (Math.random() * 100));
				// insert to Database
				Statement stmt = (Statement) mysqlConn.createStatement();
				String query1 = "INSERT INTO USER " + "VALUES ('"+user.getFirstName() +"','"+user.getSurname() + "'," + user.getAge() + "," + user.getHeight()+ ")";
				stmt.executeUpdate(query1);
				stmt.close();
				
				// write to file system in D:/output
				try {
					FileOutputStream fout = new FileOutputStream(new File("D://output//user" + i + ".txt"));
					String output = "FirstName : " + user.getFirstName() + ", Surname: " + user.getSurname() + ", Age "
							+ user.getAge();
					fout.write(output.getBytes());
					fout.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// System.out.println("Users created " + i);
			}
			
			long stopTime = System.currentTimeMillis();
			System.out.println(i + " users were created in " + (stopTime - startTime) + " milli seconds");
			System.out.println("I am going to read now.....");
			i = 1;
			for (; i <= 1000; i++) {
				try {
					FileInputStream fin = new FileInputStream(new File("D://output//user" + i + ".txt"));
					byte[] byteArray = new byte[fin.available()];
					fin.read(byteArray);
					String fileReadContent = new String(byteArray);
					System.out.println(fileReadContent);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
//		System.out.println(" Byte length : " + Byte.SIZE + " and Min Value is " + Byte.MIN_VALUE + " and Max Value is " + Byte.MAX_VALUE);
//		System.out.println(" Short length : " + Short.SIZE+ " and Min Value is " + Short.MIN_VALUE + " and Max Value is " + Short.MAX_VALUE);
//		System.out.println(" Integer length : " + Integer.SIZE+ " and Min Value is " + Integer.MIN_VALUE + " and Max Value is " + Integer.MAX_VALUE);
//		System.out.println(" Long length : " + Long.SIZE+ " and Min Value is " + Long.MIN_VALUE + " and Max Value is " + Long.MAX_VALUE);
//		System.out.println(" Float length : " + Float.SIZE+ " and Min Value is " + Float.MIN_VALUE + " and Max Value is " + Float.MAX_VALUE);
//		System.out.println(" Double length : " + Double.SIZE+ " and Min Value is " + Double.MIN_VALUE + " and Max Value is " + Double.MAX_VALUE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
