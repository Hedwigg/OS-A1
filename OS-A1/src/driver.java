import java.util.Random;

public class driver {
	
	
	static Job[] testingJobs;
	
	static int last_processor_count = 0; //initially 0, first job goes on processor 0
	
	static int total_processors = 3; //3016%3 + 2 = 3 (per assignment instructions)
	
	static int time_ms; //overall timer for keeping track of run time???
	
	static Processor[] processorList = new Processor[total_processors]; //array of processors 
	
	static int [] allTurnaroundTimes = new int[100]; //array for storing the turnaround times of
	
	
	
	
	public static void main(String[] args)
	{
				
		
		//Initialize processors
		for(int i = 0; i < total_processors; i++)
		{
			processorList[i] = new Processor();
		}
		
		
		
		
		//Initialize testing jobs (array of 12 from assignment)
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
		
		
		Job[] currentJobList;
		
		//array of turn-around times for each sequence of 100
		int[] turnaroundTimesCircular = new int[100];
		
		
		for(int i = 0; i< 100; i++)	//run 100 sequences and record turnaound times
		{
			currentJobList = generateRandomJobs();
			//TODO calculate turnaound time: for 1 sequence is the max processor job queue (because they are running in parallel).
		}
		
		
		//TODO calculate min, max, average and STD for each set of turnaround times.

		
		
		
	}
	
	
	
	
	/*
	 * TURNAROUND TIME IS CALCULATED WRONG EVERWHERE. YOU DON'T NEED A TOTAL TIME. JUST THE INDIVIDUAL TIMES FOR EACH PROCESSOR. THE MAX VALUE OF THOSE
	 * IS THE TURNAROUND TIME FOR THE WHOLE PROGRAM.
	 * TURNAROUNDTIMESCIRCULAR SHOULD BE AN ARRAY OF THE TURNADOUND TIMES FOR EACH SEQUENCE OF 100 JOBS (I.E 1 PROGRAM)
	 */
	
	
	
	
	
	/*
	 * method to determine processor and add job to it based on a circular fashion 
	 */
	public static void determineProcessorCircular(Job j)
	{
		Processor p;
		
		if(last_processor_count == 2)
		{
			p = processorList[0];
		}else
		{
			p = processorList[last_processor_count + 1]; //
		}
		
		p.addJob(j);
		//NOTE: it takes 1ms to put a job onto any processor
		time_ms = time_ms+1;//TODO WRONG need to add the 1ms time to the processer time 
		
	}
	
	
	/*
	 * method to determine processor based on **************?
	 */
	public void determineProcessorOther(Job j)
	{
		//NOTE: it takes 1ms to put a job onto any processor
		time_ms = time_ms+1;//TODO WRONG need to add the 1ms time to the processer time 
	}
	
	
	//method to run an array of jobs in a circular fashion, returns turnaround time
	public static int runJobsCircular(Job[] jobs)
	{
		//reset processors first
		for(int i = 0; i > total_processors; i++)
		{
			processorList[i].reset();
		}
		
		
		//for each job in jobs
		for(Job j : jobs)
		{
			determineProcessorCircular(j);
		}
		
		//for each processor 
		for(Processor p : processorList)
		{
			time_ms += p.jobQueueTime; //add processorQueue time to total time.
		}	
		
		return time_ms; //return turnaround time.
	}
	
	public int runJobsOther(Job[] jobs)
	{

		//reset processors first
		for(int i = 0; i > total_processors; i++)
		{
			processorList[i].reset();
		}
		//reset total time 
		time_ms = 0;
		
		//for each job in jobs
		for(Job j : jobs)
		{
			determineProcessorOther(j);
		}
		
		return time_ms; //return turn-around time
	}
	
	
	
	/*produces randomly generated job sequences of 100 which arrive every 1ms, each job has
	* a random processing time between 1ms and 500ms.
	*/
	public static Job[] generateRandomJobs()
	{
		Job[] jobList = new Job[100];
		Random rand = new Random();
		int r;
		
		for(int i = 0; i<100; i++)
		{
			r = rand.nextInt(500)+1;  //generate a random int between  1 and 500 inclusive
			jobList[i] = new Job(i,r);

		}
		return jobList;
	}
	
}

