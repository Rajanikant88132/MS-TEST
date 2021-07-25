import java.text.SimpleDateFormat;
import java.util.*;
public class TestMeMain {
  public static void main(String[] args) {
    String[] array = new String[]{"A", "B", "C"};
    List<String> list1 = Arrays.asList(array);
    List<String> list2 = new ArrayList<>(Arrays.asList(array));
    List<String> list3 = new ArrayList<>(Arrays.asList("A", new String("B"), "C"));
    System.out.print(list1.equals(list2));
    System.out.print(list1.equals(list3));

    StringBuilder sb = new StringBuilder("hello");
    sb.deleteCharAt(0);
    sb.insert(0, "H");
    System.out.println(sb);
    String timeStamp = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
    System.out.println(timeStamp);
    String instring="Sir, I demand, I am a maid named Iris";
    System.out.println(instring);
    String words = instring.replaceAll("[^a-zA-Z ]", "").toLowerCase().replaceAll("\\s+","");

    //String string = String.valueOf(words);
	//words="XTXTXTXTTTTTXTXTXTX";
	System.out.println(" Is Paldrome "+isPalindrome(words));
    System.out.println(" Is valid "+validate(words));
    System.out.println(words);
   // ArrayList<String> words = new ArrayList<>(Arrays.asList("Hello", "World"));
  }
  public static boolean validate(String words)
  {

	  boolean validstring=true;
	 
	  long count=0;
	  for(int i=0;i<words.length();i++)
	  {
		  char checkChar=words.charAt(i);
	       count = words.chars().filter(ch -> ch == checkChar).count();
	       
	       if(count>words.length()/2)
	 	  { validstring=false;
	    		  System.out.println("Wrong Palidrom");
	    	   break;
	 	
	 	  }
	  }
	  
	  return validstring;
	  
  }
  
  public static boolean isPalindrome(String text){  
	    StringBuilder sb=new StringBuilder(text);  
	    sb.reverse();  
	    String rev=sb.toString();  
	    if(text.equals(rev)){  
	        return true;  
	    }else{  
	        return false;  
	    }  
	}  

}