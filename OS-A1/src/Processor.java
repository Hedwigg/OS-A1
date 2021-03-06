public class Processor {	
	
	public int jobQueueTime; //total processing time of all jobs assigned to the processor.
	
	public Processor()
	{
		jobQueueTime = 0; //initialize to 0
	}
	
	
	public void addJob(Job j)
	{
		jobQueueTime += j.processing_time; //add job time to total queue
	}
	
	/*
	 * reset jobQueueTime for processor.
	 */
	public void reset()
	{
		jobQueueTime = 0;
	}
}
