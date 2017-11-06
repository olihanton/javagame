//This class deals with the 'grid' (/battlemap) which is the 10x15 cell array in which our game happens. 
import java.util.*;
import java.io.*;
import java.lang.*;

class Grid{
	private int battlemap[][] = new int[10][15];

   //to initialise the battlemap and set every cell to empty (0)
	void initialisebm(){
      int i, j;
      for(i=0; i<10; i++){
         for(j=0; j<15; j++){
            battlemap[i][j]=0;
         }
      }
	}

   //to return the value in a cell of the battlemap
	int getbmcell(int i, int j){
      int temp=0;
      temp=battlemap[i][j];
      return(temp);
	}

   //to set the value in a cell of the battlemap
   void setbmcell(int i, int j, int temp){
      battlemap[i][j]=temp;
   }

   //to pass an instance of the 2d array: grid that has been set up in a certain way to another class
   int[][] passmapfromgrid(){
      int i, j;
      for(i=0; i<10; i++){
         for(j=0; j<15; j++){
         }
      }
      return(battlemap);
   }
//testing below: commentted out to avoid interference with functions
   /*void test(){
      Grid gd = new Grid();
      gd=initialisebm()
      assert(0==(gd.battlemap[0][3]));
      assert(0==(gd.battlemap[5][9]));
      assert(0==(gd.battlemap[0][3]));
      assert(0==(gd.battlemap[0][3]));
      assert(0==(gd.battlemap[9][9]));
      setbmcell(0,4,5)
      assert(5==(gd.battlemap[0][4]));
      setbmcell(9,9,3)
      assert(3==(gd.battlemap[9][9]));
      setbmcell(2,8,6)
      assert(6==(gd.battlemap[2][8]));
      setbmcell(1,2,3)
      assert(3==(gd.battlemap[1][2]));
      setbmcell(6,1,4)
      assert(4==(gd.battlemap[6][1]));


   }

   public static void main(String[] args) {
      boolean testing = false;
        assert(testing = true);
         if (testing){
            Grid gd = new Grid();
         g.test();
      }
   }*/
}

