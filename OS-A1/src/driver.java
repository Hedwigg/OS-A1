
public class driver {
	
	
	static Job[] testingJobs;
	int last_processor = 0; //initially 0, first job goes on processor 0
	int total_processors = 3; //3016%3 +1 = 3 per assignment instructions
	
	
	
	public static void main(String[] args)
	{
		testingJobs = new Job[12];
		testingJobs[0] = new Job(4,9);
		testingJobs[1] = new Job(15,2);
		testingJobs[2] = new Job(18,16);
		testingJobs[3] = new Job(20,3);
		testingJobs[4] = new Job(26,29);
		testingJobs[5] = new Job(29,198);
		testingJobs[6] = new Job();
		testingJobs[7] = new Job();
		testingJobs[8] = new Job();
		testingJobs[9] = new Job();
		testingJobs[10] = new Job();
		testingJobs[11] = new Job();
		
	}
	
	
	
	public int determineProcessor()
	{
		return 0;
	}
}

