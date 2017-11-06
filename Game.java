//to call grid
//to contain turn based logic and win logic

import java.util.*;
import java.io.*;
import java.lang.*;


class Game{
   private int turncounter;
   private boolean victory=true;
   private List<Person> personlist = new ArrayList<Person>();
   private List<Person> player2list = new ArrayList<Person>();
   private Person player1person1 = new Person();
	private Grid gd = new Grid();

   //This method runs a level of the game on the text file "levelnumb.txt"
   Game run(String levelnumb){
      //Grid gd = new Grid();
      Level lvl = new Level();
      Game gm = new Game();
      
      gm.initialiselvl();
      lvl.filetogrid(levelnumb, gd);
      gm.createPerson("Elite_knight1", 0, 3, gd);
      gm.createPerson("Elite_knight1", 0, 5, gd);
      gm.createPerson("Elite_knight1", 0, 7, gd);
      gm.createPerson("Elite_knight2", 14, 3, gd);
      gm.createPerson("Elite_knight2", 14, 5, gd);
      gm.createPerson("Elite_knight2", 14, 7, gd);
      gd.passmapfromgrid(); //temporary - print to console output

      while(victory==false){
         //gm.turn();
         turncounter++;
         victory=lvl.wipeoutvictory(gd);

         victory=true; //temporary - so it doesnt keep looping until we have a victory condition
      }
   return(gm);
	}

   //method to extract the grid from a specific game while also implementing the function "run()"
   int[][] playgame(Game newgame){
      int temp[][] = new int[10][15];
      newgame.run("level1");
      temp=newgame.passgrid();
      return(temp);
   }

   //method to insert a Person into the Herolist N.b. we don't need a method to delete heros or enemies 
   //as they can just stay in the array list with no health
   // need to set the type and the position (dictated by i and j)
   // then proceeds to insert the hero into the grid
   void createPerson(String newtype, int j, int i, Grid gd){
      Person pn = new Person();
      pn.settype(newtype);
      pn.startuptype();
      pn.changeposition(i, j);
      personlist.add(pn);
      player1person1=pn;
      if(newtype=="Elite_knight1"){
         gd.setbmcell(i, j, 2);   //2 denominates a friendly character in the grid
      }
      else if(newtype=="Elite_knight2"){
         gd.setbmcell(i, j, 3);   //2 denominates a friendly character in the grid
      }
   }

   //method to retrieve a Persons's profile from the personlist specified by their position - by copying it over and then
   // returning that object... We do not need this method for this iteration of the game
   Person retrieveprofile(int x, int y){
      Person pn = new Person();
      int[] position = new int[2];
      int n=0;
      //while(personlist.get(n)!=null){
         /*pn=personlist.get(n);*/
            /*position=pn.gethealth();
         n++;
         if(position[0]==x && position[1]==y){
            return(pn);
         }*/
      //}
      return(pn);
   }

   //to initialise a level: reset turncounter to 0 and victory to false
   void initialiselvl(){
      turncounter=1;
      victory=false;
   }

   //to get the turncounter
   int gettc(){
      int temp=0;
      temp=turncounter;
      return(temp);
   }

   //A getter to take the grid and pass it from the grid class up the tree
   int[][] passgrid(){
      int temp[][] = new int[10][15];
      temp=gd.passmapfromgrid();
      return(temp);
   }
//testing below: commentted out to avoid interference with functions
   /*void test(){
      Game g = new Game();
      g=run("level1")
      assert(2==(g.gd[3][0]));
      assert(3==(g.gd[5][14]));
      assert(2==(g.gd[3][0]));
      assert(2==(g.gd[3][0]));
      assert(0==(g.gd[9][9]));
      g=run("level2")
      assert(3==(g.gd[5][14]));
      assert(3==(g.gd[7][14]));
      assert(2==(g.gd[5][0]));
      assert(1==(g.gd[5][8]));
      assert(0==(g.gd[9][9]));
      g=run("level3")
      assert(3==(g.gd[3][14]));
      assert(3==(g.gd[5][14]));
      assert(3==(g.gd[7][14]));
      assert(1==(g.gd[10][7]));
      assert(0==(g.gd[9][6]));
   }

   public static void main(String[] args){
      boolean testing = false;
      assert(testing = true);
      if (testing){
         Game g = new Game();
         g.test();
      }
   }*/
}
