import java.util.*;

import acm.util.*;
import acm.program.*;
import java.util.Map;

public class IntersectHashMaps extends ConsoleProgram implements Map{
	public void run() {
		map<String,Integers> mapA = new hashmap<String,Integers>();
		map<String,Integers> mapB = new hashmap<String,Integers>();
		map<String,Integers> mapC = new hashmap<String,Integers>();
		
		mapA.put(Janet,87);
		mapA.put(Logan,62);
		mapA.put(Whitaker,46);
		mapA.put(Alyssa,100);
		mapA.put(Stefanie,80);
		mapA.put(Jeff,88);
		mapA.put(Kim,52);
		mapA.put(Sylvia,96);
		
		mapB.put(Logan,62);
		mapB.put(Kim,52);
		mapB.put(Whitaker,52);
		mapB.put(Jeff,88);
		mapB.put(Stefanie,80);
		mapB.put(Brian,60);
		mapB.put(Lisa,83);
		mapB.put(Sylvia,87);
		
		mapC = intersect(mapA, mapB);
		for(String name: mapC.keyset()) {
			println(name + "=" + mapC.get(name));
		}
		
	}
	
	
	private map<String,Integers> intersect(map<String,Integers> mapA, map<String,Integers> mapB) {
		
		map<String,Integers> mapC = new hashmap<String,Integers>();
		
		for(String name: mapA.keyset()) {
			if(mapA.get(name) == mapB.get(name)) {
				mapC.put(name, mapA.get(name));
			}
		}
		
		return mapC;
	}
}