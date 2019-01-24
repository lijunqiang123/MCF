package ljq.ksp;

import java.util.List;


import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class TestKsp {

	public static File file;	
	public static FileWriter fw;

    public static void main(String args[]) {
        String graphFilename, sourceNode, targetNode;
        int K;

	     
	    file = new File("src/ljq/ksp/result.txt");
        try{

            if(!file.exists()){
             file.createNewFile();
            }
            fw = new FileWriter(file.getPath(),true);

           }catch(IOException e){
            e.printStackTrace();
           }
	     

        graphFilename = "src/ljq/ksp/topo.txt";
        System.out.print("Reading data from file... ");
        System.out.println("complete.");

        for(int sourceNodeid=1;sourceNodeid<24;sourceNodeid++)
        {
        	for(int targetNodeid=1;targetNodeid<24;targetNodeid++)
        	{
        		if(sourceNodeid==targetNodeid)
        		{
        			continue;
        		}
        		else
        		{
        	        sourceNode = Integer.toString(sourceNodeid);
        	        targetNode = Integer.toString(targetNodeid);
        	        K = 5;
        	        usageExample1(graphFilename,sourceNode,targetNode,K);
        		}
        	}
        }
        System.out.println("Find path complete.See result in result.txt");

        try {
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       


    }

    public static void usageExample1(String graphFilename, String source, String target, int k) {

        Graph graph = new Graph(graphFilename);

        List<Path> ksp;
        ksp yenAlgorithm = new ksp();
        ksp = yenAlgorithm.ksp(graph, source, target, k);

        int n = 0;
        try {
			fw.write("["+source+"]"+"-"+"["+target+"]"+":\n");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        for (Path p : ksp) {
        	String temp = ++n+") " + p+"\n";
        	try {		
				fw.write(temp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
}
}
      
