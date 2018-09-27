
public class driver {
	
	
	static Job[] testingJobs;
	int last_processor = 0; //initially 0, first job goes on processor 0
	int total_processors = 3; //3016%3 + 2 = 3 (per assignment instructions)
	
	int time_ms; //overall timer for keeping track of run time.
	
	
	public static void main(String[] args)
	{
		//initalize testing jobs (array of 12 from assignment)
		testingJobs = new Job[12];
		testingJobs[0] = new Job(4,9);
		testingJobs[1] = new Job(15,2);
		testingJobs[2] = new Job(18,16);
		testingJobs[3] = new Job(20,3);
		testingJobs[4] = new Job(26,29);
		testingJobs[5] = new Job(29,198);
		testingJobs[6] = new Job(35,7);
		testingJobs[7] = new Job(45,170);
		testingJobs[8] = new Job(57,180);
		testingJobs[9] = new Job(83,178);
		testingJobs[10] = new Job(88,73);
		testingJobs[11] = new Job(95,8);
		
		
		
		
		//run test jobs
		for(int i = 0; i < testingJobs.length; i++) 
		{
			
		}
		
		
		
	}
	
	
	/*
	 * method determine processor decides what processor gets the next job and assigns it
	 * 
	 * ??????
	 */
	public void determineProcessor()
	{
		//NOTE: it takes 1ms to put a job onto any processor
		time_ms = time_ms+1;
	}
}

