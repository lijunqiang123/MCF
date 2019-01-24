package ljq.transform;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class demandTransform {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("src/ljq/cplex/allData.dat");
		FileWriter fw = null;
		Random rd = new Random();
		try {
			if(!file.exists())
			{
				file.createNewFile();
			}
			fw  = new FileWriter(file.getPath(),true);
			fw.write("demand = [");
			for(int ii=0;ii<506;ii++)
			{
			int temp = rd.nextInt(30)+30;
			if(ii==505)
			{
				fw.write(temp+"];\n");
			}
			else
			{
				fw.write(temp+", ");	
			}
			}
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("需求矩阵已经生成，见cplex文件夹中的allData.dat");
	}

}
