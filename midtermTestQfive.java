
public class midtermTestQfive {

	public static int mystery(int n)
	{
		if (n<0) 
		{ 
			return -mystery(-n); 
		}
		else if (n == 0)
		{ 
			return 0; 
		}
		else 
		{ 
			System.out.println(mystery(n/10)*10+9-(n%10));
			return mystery(n/10)*10+9-(n%10); 
		}
	}
	
	
 public static void main(String args[]) 
 	{
	 int n = -3;
	mystery(n);
		 
	 }
}
