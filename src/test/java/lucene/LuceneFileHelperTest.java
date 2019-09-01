package lucene;
import java.io.File;
import java.util.List;

import org.junit.Test;  
  
public class LuceneFileHelperTest {  
  
    private static final String INDEX = "index";
	private static final String SOURCE = "source";
	@Test  
    public void indexTest(){  
        String sourceFileDir = SOURCE;  
        String indexFileDir = INDEX;  
          
        for(File file : new File(sourceFileDir).listFiles()){  
            LuceneFileHelper.createIndex(file, indexFileDir);
        }  
    }  
    @Test  
    public void searcherTest(){  
        String indexFileDir = INDEX;  
        List<String> filePaths = LuceneFileHelper.searcher("lucene", indexFileDir);  
        System.out.println("========含有\"lucene\"的文件===========");  
        System.out.println(filePaths.toString());  
          
          
        filePaths = LuceneFileHelper.searcher("hello", indexFileDir);  
        System.out.println("========含有\"hello\"的文件===========");  
        System.out.println(filePaths.toString());  
    }  
}  