import indexHandler.MyIndexWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import preprocess.PreProcessedCorpusReader;

import java.util.Map;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws Exception {
		DemoApplication demoApplication = new DemoApplication();
		demoApplication.WriteIndex();
		SpringApplication.run(DemoApplication.class, args);
	}

	public void WriteIndex() throws Exception {
		// Initiate pre-processed collection file reader
		PreProcessedCorpusReader corpus=new PreProcessedCorpusReader();

		// initiate the output object
		MyIndexWriter output=new MyIndexWriter();

		// initiate a doc object, which can hold document number and document content of a document
		Map<String, Object> doc = null;

		int count=0;
		// build index of corpus document by document

		while ((doc = corpus.nextDocument()) != null) {
			// load document number and content of the document
			String docno = doc.keySet().iterator().next();
			char[] content = (char[]) doc.get(docno);
			// index this document
			output.index(docno, content);

			count++;
			if(count%10000==0)
				System.out.println("finish "+count+" docs");
		}
		System.out.println("totaly document count:  "+count);
		output.close();
	}

}