package de.ft.ledwall.nativeapps.snake

import de.ft.ledwall.Application
import de.ft.ledwall.SystemInterface



class SnakeGame:Application {


    var snake:Snake
    var schritt: Float = 0f
    var takt: Long = 0
    var u = 10
    init {
        snake = Snake()
    }

    fun GameOver() {
        snake.clearFood()
        SystemInterface.table.clear()
        snake.direction=0;
        snake.createRandomFood(1);
        snake.length = 2;
        snake.createSnake(5,7);
    }

    override fun onCreate() {
        takt = System.currentTimeMillis()

        snake.createSnake(5, 7)
        snake.createRandomFood(1)
        SystemInterface.table.clear()
        SystemInterface.table.setColor(0,255,0)

    }


    override fun onDraw() {

        if (schritt > 255 / u) {
            snake.move()
            if (snake.WandKontrolle() != -1 || snake.SnakeKontrolle() != -1) {
                GameOver()
            }
            schritt = 0f
        }


            snake.draw(schritt.toLong(), u)
            snake.drawFood()
        schritt+=2.5f
        if (snake.foodCheck() != -1) {
            //Serial.println("fooddelete: "+snake.foodCheck());
            snake.deleteFood(snake.foodCheck())
            snake.createRandomFood(1)
            snake.addPixel()
        }


    }


    override fun onRun() {


    }

    override fun onDataReceive(data: String, playerID: Int) {
        if(data.contentEquals("h")) snake.direction=2;
        if(data.contentEquals("r")) snake.direction=0;
        if(data.contentEquals("l"))  snake.direction=1;
        if(data.contentEquals("d")) snake.direction=3;
    }

    override fun getName(): String {
        return "Snake"
    }

    override fun onStop() {
    }

    override fun getVersion(): String {
        return "-1";
    }

    override fun getUUID(): String {
        return "79bcd736-7056-11eb-8638-0242ac110002";
    }
}
