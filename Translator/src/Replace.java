import java.io.IOException;
import java.text.NumberFormat;
import java.text.DecimalFormat;

public class Replace {
static long before=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
static long start=System.currentTimeMillis();
private static final String INPUT_FILE = "C:\\Users\\elcot\\Desktop\\Desktop downloads\\Translator\\shakespeare.txt";
private static final String OUTPUT_FILE="C:\\Users\\elcot\\Desktop\\Desktop downloads\\Translator\\output.txt";
public static void main(String args[]) throws IOException{
	FrenchTranslator futils=new FrenchTranslator();
	futils.replaceTextFile(INPUT_FILE,OUTPUT_FILE);
	long after=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
	long actual=after-before;
	long end=System.currentTimeMillis();
	NumberFormat formatter=new DecimalFormat("#0.00000");
	System.out.println("Memory-Usage:"+(double)actual*1024*1024+"MB");
	System.out.println("Running-Time:"+formatter.format((end-start)/1000d)+"Seconds");
}
}
