package detect;

import doc_algorithm.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DocResultAction extends ActionSupport{

	private static final int BUFFER_SIZE = 16*1024;
	public static String parentpath = "d:";
	public static String path = "d:\\homework";
	private static String docresultstr = "";
	ArrayList text=new ArrayList();
	ArrayList name=new ArrayList();
	int i=0;int j=0;
	public String execute() throws Exception{
		detDoc(parentpath, path);
		
//		name.add("С��");
//		name.add("����");
//		name.add("����");
//		text.add("ȫ��������ȼ������ɽ����������������죬����ȫ��ᣬ���ڿ���Ӧ����Ա�����Ӧ��֪ʶ�뼼�ܵ�ȫ���Լ����ˮƽ������ϵ���ɼ��ϸ��ߣ��ɻ�ý������������İ䷢��");
//		text.add("ȫ��������ȼ������ɽ����������������죬����ȫ��ᣬ���ڿ���Ӧ����Ա�����Ӧ��֪ʶ�뼼�ܵ�ȫ���Լ����ˮƽ������ϵ���ɼ��ϸ��ߣ��ɻ�ý������������İ䷢��"
//+"��ܽ�ݸ�");
//text.add("ȫ��������ȼ������ɽ����������������죬����ȫ��ᣬ���ڿ���Ӧ����");
		// ��һ������������
		ChaChong chaCong=new ChaChong();
		// �ڶ����������㷨���õ��������
//		Object[] o=chaCong.CalculateSimilarityAlgrithm(text, name); // �㷨һ
		Object[] o=chaCong.EDcalculate(text, name); // �㷨�������������
		// ���������������֣����ƶȣ���ȡ�õ��Ķ����еĽ��
		ArrayList outname= (ArrayList) o[0];
		ArrayList outsimilarity=(ArrayList) o[1];
		// ���Ĳ���ѭ��ȡ�����ݣ���ʾ����ҳ�ϣ���������ʾ������̨��Ϊ���ӡ�
		for(int i=0;i<outsimilarity.size();i++){
			docresultstr=docresultstr.concat((String)outname.get(i+i)); // ��ͬѧ
			docresultstr=docresultstr.concat((String)outname.get(i+i+1)); // ��ͬѧ
			docresultstr=docresultstr.concat(outsimilarity.get(i)+"<br>"); // ��������ͬѧ�����ƶ�
		}

//		 �浽���ݿ�ģ�����˵
//		chaCong.savePersimilaity(o);
    	ActionContext.getContext().getSession().put("docresultstr",docresultstr);	
		return "success";
	
	}
	
	public void detDoc(String parentpath, String path) throws Exception{
		try{
    		File file = new File(path);
    		if(!file.isDirectory()){
    			String fileName=file.getName();
    		    String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
//    		    if(prefix.equals("doc") || prefix.equals("docx"))
    		    	if(prefix.equals("txt") )
    				copy(parentpath, file);
    		}
    		else if(file.isDirectory()){
    			String[] filelist = file.list();
    			for(int i=0; i<filelist.length; i++){
    				File readfile = new File(path + "\\" + filelist[i]);
    				if(!readfile.isDirectory()){
    					String fileName=readfile.getName();
    	    		    String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
//    	    		    if(prefix.equals("doc") || prefix.equals("docx")){
    	    		    	if(prefix.equals("txt") ){
    	    				copy(path, readfile);
    	    		    File aa = new File(path);
    	    		    name.add( aa.getName());
    	    		    j++;
    	    		    }
    				}
    				else if(readfile.isDirectory()){
    					detDoc(path, path + "\\" + filelist[i]);
    				}    				
   			    }
    		}
    	}catch (FileNotFoundException e) {
    		e.printStackTrace();
    	}

	}
    
    public void copy(String parentpath,File src){
     	InputStream in = null;
     	
       String codestr = "";
       
    	try{
    		in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
    		
    		byte[] buffer = new byte[BUFFER_SIZE];
    		String strread;
    		int len = 0;
    	   	while((len=in.read(buffer))>0){
    	   		strread = new String(buffer);
//    			strread = String.copyValueOf(strread.toCharArray(), 0, len); 
    			codestr = codestr.concat(strread);
    		}
    	   	text.add(codestr);
    	   	
    	   	i++;
    		
   		

    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		if(in != null)
    			try{
    				in.close();
    			}catch(IOException e){
    				e.printStackTrace();
    			}
    		
    	}

	}
	    

}

/*package detect;

import doc_algorithm.ChaCong;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DocResultAction extends ActionSupport{

	private static final int BUFFER_SIZE = 16*1024;
	public static String parentpath = "d:";
	public static String path = "d:\\homework";
	static String codestr = "";
 
	
	public String execute() throws Exception{
		String[] text = detDoc(parentpath, path);
//    	ActionContext.getContext().getSession().put("name",name);
//   	ActionContext.getContext().getSession().put("codestr",codestr);
		calculate();
		return "success";
	
	}
	
    public String docStr = null;
	
	public String[] detDoc(String parentpath, String path, ) throws Exception{
		List<String> listDoc = new ArrayList<String>();
		List<String> listName = new ArrayList<String>();
		try{
    		File file = new File(path);
    		if(!file.isDirectory()){
    			String fileName=file.getName();
    		    String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
    		    if(prefix.equals("doc") || prefix.equals("docx"))
                    //���ı�ת�����ַ���
    		    	docStr = changeToString(parentpath, file);
    		        //��ӵ�listDoc
    		        listDoc.add(docStr);
    		        
    		}
    		else if(file.isDirectory()){
    			String[] filelist = file.list();
    			for(int i=0; i<filelist.length; i++){
    				File readfile = new File(path + "\\" + filelist[i]);
    				if(!readfile.isDirectory()){
    					String fileName=readfile.getName();
    	    		    String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
    	    		    if(prefix.equals("doc") || prefix.equals("docx"))
    	    		    	 //���ı�ת�����ַ���
    	    		    	docStr = changeToString(parentpath, file);
    	    		        //��ӵ�listDoc
    	    		        listDoc.add(docStr);
    	    		        listName.add(filelist[i]);

    				}
    				else if(readfile.isDirectory()){
    					detDoc(path, path + "\\" + filelist[i]);
    				}    				
   			    }
    		}
    	}catch (FileNotFoundException e) {
    		e.printStackTrace();
    	}
		String[] text = new String[listDoc.size()];
	    text = listDoc.toArray(text);
	    String[] name = new String[listName.size()];
	    name = listName.toArray(text);
        return text;
	}
    
	
	public void calculate(String[] text, String[] fileName){
		// ��һ������������
	    ChaCong chaCong=new ChaCong();
		// �ڶ����������㷨���õ��������
	    Object[] o=chaCong.EDcalculate(text, fileName);
	    // ���������������֣����ƶȣ���ȡ�õ��Ķ����еĽ��
	    ArrayList outname= (ArrayList) o[0];
	    ArrayList outsimilarity=(ArrayList) o[1];
	 	// ���Ĳ���ѭ��ȡ�����ݣ���ʾ����ҳ�ϣ���������ʾ������̨��Ϊ���ӡ�
	 	for(int i=0;i<outsimilarity.size();i++){
	 			System.out.println(outname.get(i+i)); // ��ͬѧ
	 			System.out.println(outname.get(i+i+1)); // ��ͬѧ
	 		    System.out.println(outsimilarity.get(i)); // ��������ͬѧ�����ƶ�
	 		}
	}
	//���ĵ�ת��Ϊ�ַ���
    public String changeToString(String parentpath,File src){
     	InputStream in = null;
     	String strRead = null;
    	try{
    		in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);  	
    		byte[] buffer = new byte[BUFFER_SIZE];
    		int len = 0;
    	   	while((len=in.read(buffer))>0){
    	   		strRead = new String(buffer);
//    			codestr = codestr.concat(strread);
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		if(in != null)
    			try{
    				in.close();
    			}catch(IOException e){
    				e.printStackTrace();
    			}    		
    	}
	   	return strRead;
	}
}*/
