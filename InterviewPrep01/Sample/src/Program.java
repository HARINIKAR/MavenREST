import java.util.ArrayList;
import java.util.List;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 List<Integer> list = new ArrayList<Integer>();
	        list.add(10);
	        list.add(10);
	        
	        System.out.print(list.size());
	        ////
	        list.remove(new Integer(10));
	        System.out.print(list.size());
	}

}
