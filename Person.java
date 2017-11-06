//This class creates a profile for each character (read: 'knight') including movement, health and which team they are on. 
//In the game's current iteration, most of this information is not used (only position and allegiance are used),
// but lays the foundation for the next steps.

import java.util.*;
import java.io.*;
import java.lang.*;

class Person{
   private int allegiance; //1=neutral, 2=player1, 3=player2
   private String type;
   private int health; //if health == 0 then the person becomes inactive
   private int position[] = new int[2]; //unique
   private int attack;
   private int movement;
   
   //A setter to change the type of 
   void settype(String newtype){
      type=newtype;
      startuptype();
   }

   //a simple method to insert the 'stats' of a particular type of character
   void startuptype(){
      if(type.equals("Elite_knight1")){
         allegiance = 2;
         health = 150;
         attack = 40;
         movement = 3;

      }
      else if(type.equals("Light_knight1")){
         allegiance = 2;
         health = 100;
         attack = 30;
         movement = 4;

      }
      else if(type.equals("Elite_knight2")){
         allegiance = 3;
         health = 150;
         attack = 40;
         movement = 3;

      }
      else if(type.equals("Light_knight2")){
         allegiance = 3;
         health = 100;
         attack = 30;
         movement = 4;

      }

   }

   //The following methods are getters and setters for adapting and returning stats related to characters: (not currently used in the game)
   void changehealth(int deltah){
      health=health-deltah;
   }

   void changeposition(int i, int j){
      position[0]=i;
      position[1]=j;
   }

   int getallegiance(){
      return(allegiance);
   }

   int[] getposition(){
      return(position);
   }

   int getattack(){
      return(attack);
   }

   int gethealth(){
      return(health);
   }
   int getmovement(){
      return(movement);
   }
}