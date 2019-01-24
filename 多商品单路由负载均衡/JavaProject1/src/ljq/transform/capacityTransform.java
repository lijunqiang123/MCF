package ljq.transform;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class capacityTransform {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("src/ljq/cplex/allData.dat");
		FileWriter fw = null;
		try {
			if(!file.exists())
			{
				file.createNewFile();
			}
			fw  = new FileWriter(file.getPath(),true);
			fw.write("capacity = [");
			for(int ii=0;ii<72;ii++)
			{
			if(ii==71)
			{
				fw.write("2500];\n");
			}
			else 
			{
				fw.write("2500, ");	
			}
			}
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("容量矩阵已经生成，见cplex文件夹中的allData.dat");
	}

}
