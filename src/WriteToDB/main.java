package WriteToDB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ReadFromDB.Compare;

public class main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ComputeFingerPrint aa=new ComputeFingerPrint("file1.txt");//��ȡ�ļ�
		ComputeFingerPrint.compute(); //����ָ��
		aa.writeinDB();  //��ָ��д�����ݿ�
		Compare bbb=new Compare();  //�Ƚϴ�ҵ�ָ��
		bbb.compareWith();  
		bbb.print();  //��ӡ�г�Ϯ���ɵ���
    }
}

