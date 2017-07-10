// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Implements register allocation using graph coloring algorithm.
 */

public class NGraphRegisterAllocator extends NRegisterAllocator {

	private boolean[][] adjMatrix;
	
	private int degree;
	
	private Stack<NInterval> track;
	
	private ArrayList<ArrayList<NInterval>> adjList;
    /**
     * Construct a NGraphRegisterAllocator.
     * 
     * @param cfg
     *            an instance of a control flow graph.
     */

    public NGraphRegisterAllocator(NControlFlowGraph cfg) {
        super(cfg);
        track = new Stack<NInterval>();
        degree = NPhysicalRegister.MAX_COUNT;
        adjList = new ArrayList<ArrayList<NInterval>>();
        
        int numReg = cfg.intervals.size();
        adjMatrix = new boolean[numReg][numReg];
        
        for(int i = 0; i < numReg; i++)
        	for(int j = 0; j < numReg; j++)
        		adjMatrix[i][j] = false;
    }

    /**
     * Build intervals with register allocation information in them.
     */

    public void allocation() {
        buildIntervals();
        buildInterferenceGraph();
        buildAdjacencyList();
        pruneGraph(adjList);
        
        for (int i = 0; i < 32; i++) {
            if (cfg.registers.get(i) != null) {
                cfg.intervals.get(i).pRegister = (NPhysicalRegister) cfg.registers
                        .get(i);
            }
        }
        
        while(!track.isEmpty()){
        	NInterval current = track.pop();
        	int regNum = 8;
        	
        	for(int d = 1; d < degree; d++){
        		for(int i = 32; i < cfg.intervals.size(); i++){
        			boolean neighbor = i < current.vRegId ? adjMatrix[i][current.vRegId] : 
        													adjMatrix[current.vRegId][i];
        			if(neighbor)
        				if(cfg.intervals.get(i).pRegister != null &&
        						cfg.intervals.get(i).pRegister.number == regNum)
        					regNum++;
        		}
        	}
        	
        	current.pRegister = NPhysicalRegister.regInfo[regNum];
        	
        }
        
    }
    
    private void buildInterferenceGraph(){
    	
    	for(int i = 32; i < cfg.intervals.size() - 1; i++)
    		for(int j = i + 1; j < cfg.intervals.size(); j++)
    			adjMatrix[i][j] = isInterference(cfg.intervals.get(i), cfg.intervals.get(j));
    	
    }
    
    private void buildAdjacencyList(){
    	
    	for(int i = 32; i < cfg.intervals.size(); i++){
    		ArrayList<NInterval> tmp = new ArrayList<NInterval>();
    		for(int j = 32; j < cfg.intervals.size(); j++){
    			boolean inter = i < j ? adjMatrix[i][j] : adjMatrix[j][i]; 
    			if(inter)
    				tmp.add(cfg.intervals.get(j));
    		}
    		adjList.add(tmp);
    	}
    	
    }
    
    private void pruneGraph(ArrayList<ArrayList<NInterval>> temp){
    	while(track.size() < cfg.intervals.size() - 32){
    		for(int i = 0; i < degree; i++){
    			for(int j = 0; j < temp.size(); j++){
    				ArrayList<NInterval> current = temp.get(j);
    				NInterval cur = cfg.intervals.get(j + 32);
    				if(current.size() == i && !track.contains(cur)){
    					track.push(cur);
    					for(ArrayList<NInterval> list : temp)
    						list.remove(cur);
    				}
    			}
    		}
    	}
    }
    
    private boolean isInterference(NInterval n1, NInterval n2){
    	if (!(n1.equals(n2))){
    		for(NRange range : n2.ranges){
    			if(n1.isLiveAt(range.start))
    				return true;
    		}
    	}
    	return false;
    }

}
