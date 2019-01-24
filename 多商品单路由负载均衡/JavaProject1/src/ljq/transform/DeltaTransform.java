package ljq.transform;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;


public class DeltaTransform {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		File file = new File();	
		HashMap<Integer, ArrayList<String>>pathId = new HashMap<>();
//		HashMap<Integer, Integer[][]>ksp = new HashMap<>();
		int ksp[][][] = new int[506][72][5];
		FileReader fr1 = null;
		FileReader fr2 = null;
//		File file = null;
		FileWriter fw = null;
		File file = new File("src/ljq/cplex/allData.dat");
		 if(!file.exists()){
             try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            }
		 
		try {
			fw = new FileWriter(file.getPath(),true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader br1 = null;
		BufferedReader br2 = null;
		try {
			fr1 = new FileReader("src/ljq/ksp/topo.txt");
			br1 = new BufferedReader(fr1);
			fr2 = new FileReader("src/ljq/ksp/result.txt");
			br2 = new BufferedReader(fr2);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str;	
		int index=0;
		try {
			while((str=br1.readLine())!=null)
			{
				
				ArrayList<String>te=new ArrayList<>();
				String temp[] = str.split(" ");
				te.add(temp[0]);
				te.add(temp[1]);
				pathId.put(index, te);
				index++;	
			}
			br1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//打印pathId
//		System.out.println(pathId.size());
//		Set keys = pathId.keySet();
//		for(Object key:keys)
//		{
//			System.out.println(key+"="+pathId.get(key));
//		}	
		
		int index2 = 0;
		int kspindex=-1;
		int[][] deltaArray =new int[72][5];
		ArrayList<String>infAL=null;
		try {
			while((str=br2.readLine())!=null)
			{	
				index2++;
				if((index2%6)==1)
				{
					deltaArray=null;
					deltaArray = new int[72][5];
					kspindex++;
					continue;
				}
//				if(kspindex==1)
//				{
//					System.exit(0);
//				}
//				ArrayList<String>tee=new ArrayList<>();
				String temp[] = str.split(" ");
				String inf[] = temp[2].replace("[", "").replace("]", "").split("-");
				infAL = new ArrayList(Arrays.asList(inf));
//				System.out.println(infAL.size());
//				System.out.println(index2);
				for(int ii=0;ii<infAL.size()-1;ii++)
				{ 
					for(int jj=0;jj<pathId.size();jj++)
					{
//						System.out.println(infAL.subList(ii, ii+2));
						if(infAL.subList(ii, ii+2).equals(pathId.get(jj)))
						{
							if(index2%6==0)
							{
								deltaArray[jj][4]=1;
								continue;
//								System.out.println(deltaArray[jj][4]);
							}
							else 
							{
								deltaArray[jj][index2%6-2]=1;	
//								System.out.print("jj="+jj);
//								System.out.println("index="+(index2%6-2));
							}
							
						}	
					}
					
				}
				
				if((index2%6)==0)
				{
					for(int jj=0;jj<72;jj++)
					{
						for(int kk=0;kk<5;kk++)
						{
//							System.out.print(deltaArray[jj][kk]+",");
							ksp[kspindex][jj][kk]=deltaArray[jj][kk];
						}
//						System.out.println();
					}
				}
				
			}
			br2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			fw.write("delta = [");
			for(int ii=0;ii<506;ii++)
			{
				fw.write("\n"+"\t"+"[");
				for(int jj=0;jj<72;jj++)
				{
					if(jj==0) {fw.write("[");}
					else{fw.write("\n"+"\t"+"[");}
					for(int kk=0;kk<5;kk++)
					{
						if(kk==4) {fw.write(ksp[ii][jj][kk]+"");continue;}
						fw.write(ksp[ii][jj][kk]+",");
					}
					if(jj==71){fw.write("]");continue;}
					fw.write("]"+",");
				}
				fw.write("]"+",");
			}
			fw.write("\n"+"];"+"\n");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Delta矩阵已经生成，见cplex文件夹中的allData.dat");
		
}
}

