//This class is designed to allow opens a text file called level1 etc. allowing the loading of differently designed 
//boards on which to play the game

import java.util.*;
import java.io.*;
import java.lang.*;

class Level extends Grid{
	
//method to test if all the enemies have been wiped out and so if a level should end - this is not used in the current 
//iteration of the game
	boolean wipeoutvictory(Grid gd){
		int i, j, nonenemies=0;
		for(i=0;i<10;i++){
			for(j=0;j<15;j++){
				if(gd.getbmcell(i, j)!=3){
					nonenemies++;
				}
			}
		}
		if(nonenemies==150){
			System.out.println(nonenemies);
			return(true);
		}
		else{
			return(false);
		}

	}

// filetogrid() creates a row called 'temp', opens file, splits the information in the file into strings arrays for so that it can be 
// put into the rows of the grid and then dealt with cell by cell (and the grid itself can be passed around methods)

	Grid filetogrid(String filename, Grid gd){
		int i, j, x=0, y=0;
		int[] temp = new int[15];
		filename=filename+".txt";
		try{
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line = new String();
			for(i=0; i<10; i++){
					line = reader.readLine();
					temp = translate(line);
					for(j=0; j<15; j++){
						gd.setbmcell(i, j, temp[j]); 
					}
			}
			reader.close();
		}
		catch (Exception e){
			System.err.format("Exception occurred trying to read '%s'.", filename);
			e.printStackTrace();
			return null;
		}
		return(gd);
	}

// a method to take a line of a file and turn it into a row of our grid. This allows the loading of pre-set levels by storing them in
// files (see level1.txt). It also allows the reloading of previously saved games as an extension to this program (not written yet).
	int[] translate(String a){
		int i;
		String[] cells = a.split(" ");
		int[] numbs = new int[15];
		for(i=0; i<15; i++){
		   numbs[i]=Integer.parseInt(cells[i]);
	    }
		return(numbs);
	}
}