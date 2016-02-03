package WriteToDB;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

//����Ԥ����ɾ���ո񣬻��У���ע��

public class PreHandle {
	private String text;
	private String filename;
	PreHandle(){
		
	}
	PreHandle(String name) throws IOException{
		filename=name;
		readfile();
	}
    public void readfile() throws IOException{
    	FileInputStream fi=new FileInputStream(new File(filename));
    	
        byte[] b=new byte[fi.available()];  //available()�����ܴ��������������ֽ���
        fi.read(b);  //���ļ������ֽ�������
        text=new String(b);//�ٽ��ֽ������е�����ת�����ַ�����ʽ���
        //ȥע��
    	text = text.replaceAll("\\/\\/[^\\n]*|\\/\\*([^\\*^\\/]*|[\\*^\\/*]*|[^\\**\\/]*)*\\*\\/", "");
    	//System.out.println(text);
    	text=text.replace("\t",""); 
    	text=text.replace("\n","");  
    	text=text.replace("\r","");
    	text=text.replace(" ","");
    	//System.out.println(text);
    	fi.close();

	 }
    public String getContent(){
    	return text;
    }
    
}
