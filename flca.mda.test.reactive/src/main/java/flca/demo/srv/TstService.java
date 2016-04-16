package flca.demo.srv;

import java.util.List;

import mda.type.IServiceType;
import flca.demo.dto.TstDto;
import flca.demo.entity.Tsta;

public interface TstService extends IServiceType
{
	// void no-params
	void doSomething();

	// return, no-params
	String helloWorld();
	
	// return, 1-param
	String ping(String aMessage);
	
	// return multiples params
	String pingMe(String aMessage1, String aMessage2);

	// complex return type
	Tsta saveTestA(Tsta aValue);

	// complex collection return type
	List<Tsta> searchTestA(String aName);
	
	// dto return type and dto param
	TstDto getDto(TstDto aValue);
}
