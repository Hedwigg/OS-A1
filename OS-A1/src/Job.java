public class Job {
	
	public int arrival_time;
	public int processing_time;
	
	public Job(int a, int p)
	{
		arrival_time = a;
		processing_time = p;
	}
	
	public int getArrival()
	{
		return arrival_time;
	}
	
	
	public int getProcessing()
	{
		return processing_time;
	}
}


/*testing*/