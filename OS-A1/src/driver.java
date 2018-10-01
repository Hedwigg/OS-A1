/*
 * @author Joel Lechman
 * 
 * Operating Systems Fall 2018 
 * Programming Assignment # 1 
 * 
 * Assignment instructions found @ https://www.cs.montana.edu/bhz/classes/fall-2018/csci460/assign1.pdf
 * 
 * You can run this program without any arguments. To get a txt file output you need to have the directory C:\temp\ created on your computer. Otherwise the output is just printed to the console.
 * 
 * Please contact me @ joel1500@bresnan.net if I need to demonstrate running the program for my grade.
 * 
 */





import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class driver {
	
	static Job[] testingJobs;
	
	static int total_processors = 3; //3016%3 + 2 = 3 (per assignment instructions)
	
	static Processor[] processorList = new Processor[total_processors]; //array of processors 

	static int pCount = 0; //current processor for circular method. initially 0
	
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
		
		//keep track of what jobs we are running (random or the group laid out in the assignment instructions).
		Job[] currentJobList;
		
		//array of turn-around times to store each sequence of 100 jobs
		int[] turnAroundTimesCircular = new int[100];
		int[] turnAroundTimesOther = new int[100];
		
		//run 100 sequences of 100 generated jobs and record turn around times for each sequence
		for(int i = 0; i< 100; i++)	
		{
			currentJobList = generateRandomJobs();
	
			runJobsCircular(currentJobList);
			turnAroundTimesCircular[i] = determineTurnAroundTime(currentJobList);
			
			runJobsOther(currentJobList);
			turnAroundTimesOther[i] = determineTurnAroundTime(currentJobList);
			
		}
		
		//Write output to a file (needs a folder 'temp' in c: drive of the computer to work)
		try 
		{
			PrintWriter writer  = new PrintWriter("C:\\temp\\Lechman-1.output.txt");
	
			writer.println("****Randomly generated job sequences of 100****");
			writer.println("***********************************************");
			//Display min, max, average and STD for each set of 100 turn around times.
			writer.println("Statistics for CIRCULAR method:");
			writer.println("Max:" + getMax(turnAroundTimesCircular)+"ms");
			writer.println("Min:" + getMin(turnAroundTimesCircular)+"ms");
			writer.println("Average:" + getAverage(turnAroundTimesCircular)+"ms");
			writer.println("Standard Deviation:" + getSTD(turnAroundTimesCircular)+"ms");
			writer.println("---------------------------------------------");
			writer.println("Statistics for OTHER (lowest queue) method:");
			writer.println("Max:" + getMax(turnAroundTimesOther)+"ms");
			writer.println("Min:" + getMin(turnAroundTimesOther)+"ms");
			writer.println("Average:" + getAverage(turnAroundTimesOther)+"ms");
			writer.println("Standard Deviation:" + getSTD(turnAroundTimesOther)+"ms");
			writer.println("---------------------------------------------");
			if(getAverage(turnAroundTimesOther) < getAverage(turnAroundTimesCircular))
			{
				writer.println("Hurray, the other method beat the circular method overall for the random jobs!");
			}
			writer.println("");
			writer.println("");
			writer.println("");
			writer.println("");
			writer.println("****Test Job Sequences (array from assignment instructions)****");
			currentJobList = testingJobs;
			runJobsCircular(currentJobList);
			writer.println("Turn around time CIRCULAR: " + determineTurnAroundTime(currentJobList) + "ms");
			runJobsOther(currentJobList);
			writer.println("Turn around time OTHER (lowest queue) : " + determineTurnAroundTime(currentJobList) + "ms");
			if(getAverage(turnAroundTimesOther) < getAverage(turnAroundTimesCircular))
			{
				writer.println("Hurray, the other method beat the circular method overall for the preset array of  jobs!");
			}
			writer.close();//close file writer
			
		}catch(FileNotFoundException e)	//if the file cannot be written to. Just print to console (if grader is just running in a IDE)
		{			
			System.out.println("****Randomly generated job sequences of 100****");
			System.out.println("***********************************************");
			//Display min, max, average and STD for each set of 100 turn around times.
			System.out.println("Statistics for CIRCULAR method:");
			System.out.println("Max:" + getMax(turnAroundTimesCircular)+"ms");
			System.out.println("Min:" + getMin(turnAroundTimesCircular)+"ms");
			System.out.println("Average:" + getAverage(turnAroundTimesCircular)+"ms");
			System.out.println("Standard Deviation:" + getSTD(turnAroundTimesCircular)+"ms");
			System.out.println("---------------------------------------------");
			System.out.println("Statistics for OTHER (lowest queue) method:");
			System.out.println("Max:" + getMax(turnAroundTimesOther)+"ms");
			System.out.println("Min:" + getMin(turnAroundTimesOther)+"ms");
			System.out.println("Average:" + getAverage(turnAroundTimesOther)+"ms");
			System.out.println("Standard Deviation:" + getSTD(turnAroundTimesOther)+"ms");
			System.out.println("---------------------------------------------");
			if(getAverage(turnAroundTimesOther) < getAverage(turnAroundTimesCircular))
			{
				System.out.println("Hurray, the other method beat the circular method overall for the random jobs!");
			}
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("****Test Job Sequences (array from assignment instructions)****");
			currentJobList = testingJobs;
			runJobsCircular(currentJobList);
			System.out.println("Turn around time CIRCULAR: " + determineTurnAroundTime(currentJobList) + "ms");
			runJobsOther(currentJobList);
			System.out.println("Turn around time OTHER (lowest queue) : " + determineTurnAroundTime(currentJobList) + "ms");
			if(getAverage(turnAroundTimesOther) < getAverage(turnAroundTimesCircular))
			{
				System.out.println("Hurray, the other method beat the circular method overall for the preset array of  jobs!");
			}
		}
	}
	
	
	
	
	
	/*
	 * method determines the longest jobQueueTime for all 3 processors then subtracts the arrival time for the first job. -  i.e the total processing time for the program (job sequence)
	 */
	public static int determineTurnAroundTime(Job[] currentJobList)
	{
		int t;
		int p1t = processorList[0].jobQueueTime;
		int p2t = processorList[1].jobQueueTime;
		int p3t = processorList[2].jobQueueTime;
		t= Math.max(p1t, p2t);
		t= Math.max(p2t, p3t);
		
		//turn around time = total processing time + the first job's arrival time (all processing times + the time all processors spent waiting for the first one to arrive).
		int firstArrivalTime = currentJobList[0].arrival_time;
		t = t + firstArrivalTime;
		
		return t;
	}
	
	
	/*
	 * method to determine processor and add job to it based on a circular fashion ((last_processor + 1) %k i.e. just cycle through the processors)
	 */
	public static void determineProcessorCircular(Job j)
	{
		Processor p;	
		
		if(pCount >= processorList.length)	//reset counter if at end of processor list. (i.e circular)
		{
			pCount = 0;
		}
		p = processorList[pCount];
		p.addJob(j);
		//NOTE: it takes 1ms to put a job onto any processor
		p.jobQueueTime += 1;
		pCount++;
		
	}
	
	/*
	 * method to determine processor for OTHER method: pick the processor with the shortest total processing time queue and add it to that.
	 */
	public static void determineProcessorOther(Job j)
	{
		//logic statement to determine shortest queue
		Processor lowest = processorList[0];
		
		if(processorList[1].jobQueueTime < lowest.jobQueueTime)
		{
			lowest = processorList[1];
		}
		if (processorList[2].jobQueueTime < lowest.jobQueueTime)
		{
			lowest = processorList[2];
		}

		lowest.addJob(j);
		//NOTE: it takes 1ms to put a job onto any processor
		lowest.jobQueueTime += 1;
	}
	
	
	//method to run an array of jobs in a circular fashion
	public static void runJobsCircular(Job[] jobs)
	{
		//reset processors first
		for(int i = 0; i < processorList.length; i++)
		{
			Processor p = processorList[i];
			p.reset();
		}
		pCount = 0; //initially 0, first job goes on processor 0 for circular method.

		//determine processor for all jobs.
		for(Job j : jobs)
		{
			determineProcessorCircular(j);

		}
	}
	
	
	//method to run an array of jobs in the other fashion (add to shortest queue), 
	public static void runJobsOther(Job[] jobs)
	{

		//reset processors first
		for(int i = 0; i < total_processors; i++)
		{
			processorList[i].reset();
		}
		
		//for each job in jobs
		for(Job j : jobs)
		{
			determineProcessorOther(j);
		}
	}
	
	
	/*
	 * produces randomly generated job sequences of 100 which arrive every 1ms, each job has
	 * a random processing time between 1ms and 500ms. Returns a list of 100 'randomly' generated jobs.
	 */
	public static Job[] generateRandomJobs()
	{
		Job[] jobList = new Job[100];
		Random rand = new Random();
		int r;
		
		for(int i = 0; i<100; i++)
		{
			r = rand.nextInt(500)+1;  //generate a random int (between 1 and 500 inclusive) for processing time.
			jobList[i] = new Job(i,r);

		}
		return jobList;
	}
	
	
	/*
	 * A group of Math helper methods used for computing statistics. They are pretty self-explanatory by their method names.
	 */
	
	public static int getMax(int[] nums)
	{
		  int maxValue = nums[0];
		  for(int i=1;i < nums.length; i++)
		  {
		    if(nums[i] > maxValue)
		    {
			  maxValue = nums[i];
			}
		  }
		  return maxValue;
	}
	
	public static int getMin(int[] nums)
	{
		  int minValue = nums[0];
		  for(int i=1;i<nums.length;i++)
		  {
		    if(nums[i] < minValue)
		    {
			  minValue = nums[i];
			}
		  }
		  return minValue;
	}
	
	public static int getAverage(int[] nums)
	{
		int sum = 0;
		for(int i = 0; i<nums.length; i++)
		{
			sum += nums[i];
		}
		return sum/nums.length;
	}
	
	//STD = standard deviation
	public static double getSTD(int[] nums)
	{
		double STD = 0.0;
		int average = getAverage(nums);
		int length = nums.length;
		
		for(int num: nums)
		{
			STD += Math.pow(num - average,  2);
		}
		return Math.sqrt(STD/length);
		
	}
	
	
	
	
}

