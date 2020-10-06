#include <Arduino.h>
#include "../../utils//Device/LED-Tisch/LED-Tisch.h"
#include "../../utils/Log.h"

class Snake_game{
    private:
        int length=2;
        int arraySizeX=10;
        int arraySizeY=15;
        //int snake[10][15][2];
        int snake[15*10][2];

        int food[10][2];
        int foodanzahl=0;

        void moveSnake();



    public:

    int direction=0;
    /*
        0 - rechts
        1 - links
        2 - oben
        3 - unten
    */

    Snake_game();
    void draw(long verlauf,int u,LEDTisch* ledtisch);
    void createSnake(int x,int y);
    void move(LEDTisch* ledtisch);
    void addPixel();
    int WandKontrolle();  
    void clearFood();
    /*
        -1 - keine wandkollision
         0 - oben
         1 - rechts
         2 - unten
         3 - links 
    */  
   int SnakeKontrolle();
   /*
        -1 - keine Snake kollision
        bei kollision gibt es die position in der Snake wo die kollision ist zurück
   */
  void createRandomFood(int anzahl);
  void drawFood(LEDTisch* ledtisch);
  int foodCheck();
  void deleteFood(int stelle);
  void setLength(int length);
  int getLength();


};

