package de.ft.ledwall.nativeapps.snake

import de.ft.ledwall.SystemInterface

class Snake {

    var length = 2
    var arraySizeX = 10
    var arraySizeY = 15
    var snake = Array(15 * 10) { IntArray(2) }
    var food = Array(10) { IntArray(2) }
    var foodanzahl = 0
    var direction = 0

    fun draw(verlauf: Long, u: Int) {
        for (l in 0 until this.length) {
            SystemInterface.table.setColor(255, 255, 0)
            SystemInterface.table.drawPixel(snake[l][0], snake[l][1])
        }
        SystemInterface.table.setColor((verlauf * u).toInt(), (verlauf * u).toInt(), 0)
        SystemInterface.table.drawPixel(snake[0][0], snake[0][1])
        SystemInterface.table.setColor((255 - verlauf * u).toInt(), (255 - verlauf * u).toInt(), 0)
        SystemInterface.table.drawPixel(snake[length - 1][0], snake[length - 1][1])

        //SHOW
    }

    fun addPixel() {
        length++
    }

    fun createSnake(xpos: Int, ypos: Int) {
        for (i in 0 until arraySizeX * arraySizeY) {
            snake[i][0] = -1
            snake[i][1] = -1
        }
        for (i in 0 until length + 1) {
            snake[i][0] = xpos
            snake[i][1] = ypos + i
        }
    }

    fun move() {
        SystemInterface.table.setColor(0, 0, 0)
        SystemInterface.table.drawPixel(snake[length - 1][0], snake[length - 1][1])

        for (i in length - 1 downTo 1) {
            snake[i][0] = snake[i - 1][0]
            snake[i][1] = snake[i - 1][1]
        }


        when (direction) {
            0 -> {
                direction = 0
                snake[0][0] += 1
            }
            1 -> {
                direction = 1
                snake[0][0] += -1
            }
            2 -> {
                direction = 2
                snake[0][1] += 1
            }
            3 -> snake[0][1] += -1
        }
    }

    fun WandKontrolle(): Int {
        if (snake[0][0] > 9) {//rechts kollision
            return 1
        } else
            if (snake[0][0] < 0) {//links kollision
                return 3
            } else
                if (snake[0][1] > 14) {//oben kollision
                    return 0
                } else
                    if (snake[0][1] < 0) {//unten kollision
                        return 2
                    } else {
                        return -1
                    }
    }

    fun SnakeKontrolle():Int {
        var k = -1
        for (i in 1 until length) {
            k = if (snake[0][0] === snake[i][0] && snake[0][1] === snake[i][1]) {
                return i
            } else {
                -1
            }
        }
        return k
    }

    fun createRandomFood(anzahl:Int) {
        for (i in 0 until anzahl) {
            var u = false
            var randomx: Int =0
            var randomy: Int =0
            while (!u) {
                randomx = (Math.random()*10).toInt()
                randomy = (Math.random()*10).toInt()
                for (j in 0 until foodanzahl) {
                    if (food[j][0] !== randomx && food[j][1] !== randomy) {
                        for (k in 0 until length) {
                            if (randomx != snake[k][0] && randomy != snake[k][1]) {
                                u = true
                            }
                        }
                    }
                }
                if (foodanzahl == 0) {
                    for (k in 0 until length) {
                        if (randomx != snake[k][0] && randomy != snake[k][1]) {
                            u = true
                        }
                    }
                }
            }
            food[foodanzahl][0] = randomx
            food[foodanzahl][1] = randomy
            foodanzahl++
        }
    }

    fun drawFood() {
        for (i in 0 until foodanzahl) {
            SystemInterface.table.setColor(255,0,0)
            SystemInterface.table.drawPixel(food[i][0], food[i][1])
        }
    }

    fun clearFood() {
        for (i in 0 until foodanzahl) {
            food[i][0] = -1
            food[i][1] = -1
        }
        foodanzahl = 0
    }

    fun foodCheck():Int {
        for (i in 0 until foodanzahl) {
            return if (snake[0][0] === food[i][0] && snake[0][1] === food[i][1]) {
                i
            } else {
                -1
            }
        }

        return 0
    }

    fun deleteFood(stelle:Int) {

        for(i in stelle until foodanzahl) {
            food[i][0]=food[i+1][0];
            food[i][1]=food[i+1][1];

        }
        foodanzahl-=1;
    }


}
