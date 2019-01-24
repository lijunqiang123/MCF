package ljq.transform;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class numTransform {

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
			fw.write("linksNum = 72;\n");
			fw.write("nodesNum = 23;\n");
			fw.write("backPathNum = 5;\n");	
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("节点、链路、备份路径数目已经生成，见cplex文件夹中的allData.dat");
	}

}
