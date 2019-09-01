package lucene;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
  
public class LuceneFileHelper {  
  
    private final static String FIELD_CONTENT = "content";  
    private final static String FIELD_FILEPATH = "filePath";  
    private final static String FIELD_FILENAME = "fileName";  
      
    /** 
     * 创建索引 
     * @param sourceFile
     * @param indexFileDir 
     */  
    public static void createIndex(File sourceFile,String indexFileDir){  
        Directory directory = null;  
        IndexWriter indexWriter = null;  
        try {  
            //1.创建Directory  
            directory = FSDirectory.open(Paths.get(indexFileDir));
            //2.创建IndexWriter  
            IndexWriterConfig indexWriterConf = new IndexWriterConfig(new StandardAnalyzer());  
            indexWriter = new IndexWriter(directory,indexWriterConf);  
            //3.创建Document  
            Document document = new Document();  
            document.add(new TextField(FIELD_CONTENT,new FileReader(sourceFile)));  
            document.add(new TextField(FIELD_FILENAME,sourceFile.getName(),Field.Store.YES));  
            document.add(new TextField(FIELD_FILEPATH,sourceFile.getAbsolutePath(),Field.Store.YES));  
            //4.把Document写到索引中  
            indexWriter.addDocument(document);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }finally{  
            if(indexWriter != null){  
                try {  
                    indexWriter.close();  
                } catch (IOException e) {  
                    indexWriter = null;  
                }  
            }  
        }  
    }  
    /** 
     * 在指定的索引目录下搜索指定的内容，并返回指定内容所在的文件名 
     * @param searcherContent 
     * @param indexDir 
     * @return 
     */  
    public static List<String> searcher(String searcherContent, String indexDir){  
        List<String> filePaths = new ArrayList<String>();  
        try {  
            //1.创建Directory  
            Directory directory = FSDirectory.open(Paths.get(indexDir));  
            //2.创建IndexReader  
            IndexReader indexReader = DirectoryReader.open(directory);  
            //3.根据IndexReader创建IndexSearcher  
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);  
            //4.创建搜索的Query  
            //先创建QueryParser,确定要搜索文档中的field  
            QueryParser parser = new QueryParser(FIELD_CONTENT,new StandardAnalyzer());  
            //指定要搜索的内容  
            Query query = parser.parse(searcherContent);  
            //5.根据IndexSearcher搜索并返回TopDocs  
            TopDocs topDocs = indexSearcher.search(query, 10);//最多返回10个  
            //6.根据TopDocs获取ScoreDoc[]对象  
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;  
            for(ScoreDoc sd : scoreDocs){  
                Document document = indexSearcher.doc(sd.doc);  
                filePaths.add(document.get(FIELD_FILEPATH));  
            }  
              
            indexReader.close();  
        } catch (Exception e) {  
              
            e.printStackTrace();  
        }  
          
        return filePaths;  
    }  
    
    
    public static void main(String[] args) {
		System.out.println("请运行：LuceneFileHelperTest");
	}
}  
