import java.util.Comparator;

public class Node implements Comparable {
	String suggestions[];
	
	int f, c;
	

	public Node(String[] suggestions, int f, int c) {

		this.suggestions = suggestions;
		this.f = f;
		this.c = c;
	}

	public String[] getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(String[] suggestions) {
		this.suggestions = suggestions;
	}

	

	public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}
	
        

		@Override
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			int comparelenght=((Node)o).getSuggestions().length;
	        /* For Ascending order*/
	        return this.getSuggestions().length-comparelenght;
		}
    

}
