package doc_algorithm;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ArrayList text=new ArrayList();
		ArrayList name=new ArrayList();
		name.add("С��");
		name.add("����");
		name.add("����");
		text.add("ȫ��������ȼ������ɽ����������������죬����ȫ��ᣬ���ڿ���Ӧ����Ա�����Ӧ��֪ʶ�뼼�ܵ�ȫ���Լ����ˮƽ������ϵ���ɼ��ϸ��ߣ��ɻ�ý������������İ䷢��");
		text.add("ȫ��������ȼ������ɽ����������������죬����ȫ��ᣬ���ڿ���Ӧ����Ա�����Ӧ��֪ʶ�뼼�ܵ�ȫ���Լ����ˮƽ������ϵ���ɼ��ϸ��ߣ��ɻ�ý������������İ䷢��"
+"��ܽ�ݸ�");
text.add("ȫ��������ȼ������ɽ����������������죬����ȫ��ᣬ���ڿ���Ӧ����");
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
			System.out.println(outname.get(i+i)); // ��ͬѧ
			System.out.println(outname.get(i+i+1)); // ��ͬѧ
		    System.out.println(outsimilarity.get(i)); // ��������ͬѧ�����ƶ�
		}
		// �浽���ݿ�ģ�����˵
//		chaCong.savePersimilaity(o);
	}

}
