package flca.mda.test.model;

import mda.type.IServiceType;

public interface TstService extends IServiceType
{
	void doSomething();
	
	void doSomethingThatMayFail(); //TODO throws Exception;
	
	String helloWorld();
	
	String ping(String aMessage);
	
	String pingMe(String aMessage1, String aMessage2);

}
