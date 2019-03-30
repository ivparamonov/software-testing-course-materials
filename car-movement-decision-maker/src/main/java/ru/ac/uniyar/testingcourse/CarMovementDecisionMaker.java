package ru.ac.uniyar.testingcourse;

import static ru.ac.uniyar.testingcourse.Ternar.BLINKING;
import static ru.ac.uniyar.testingcourse.Ternar.OFF;
import static ru.ac.uniyar.testingcourse.Ternar.ON;

/**
 * Класс предназначен для определения действия водителя автомобиля 
 * на перекрёстке со светофором.
 */
public class CarMovementDecisionMaker { 
    
    private boolean red = false;
    private Ternar yellow = OFF;
    private Ternar green = OFF;

    /** Установка состояния сигналов светофора. */
    public void setTrafficLightState(boolean red, Ternar yellow, Ternar green) {
        this.red = red;
        this.yellow = yellow;
        this.green = green;
    }

    /** Метод определяет, может ли водитель въехать на перекрёсток. */
    public boolean isToGo() {
        return !(red && yellow != BLINKING && green == OFF ||
                !red && yellow == ON && green == OFF);
    }
    
    /**
     * Метод определяет, должен ли водитель приготовиться к тому, что
     * возможность движения в ближайшее время изменится (движение будет 
     * запрещено или наоборот разрешено).
     */
    public boolean isToBePrepared() {
        return red && yellow == ON && green == OFF || !red && yellow == OFF && green == BLINKING;
    }
    
    /** 
     * Метод определяет, должен ли водитель уделять повышенное внимание в силу
     * того, что данный перекрёсток является нерегулируемым (если движение разрешено),
     * или должен ли он завершить манёвр, если не может остановиться, не применяя
     * экстренного торможения (если движение запрещено).
     */
    public boolean isToBeCautious() {
        return !(
                red && yellow == OFF && green == OFF ||
                red && yellow == ON && green == OFF ||
                !red && yellow == OFF && green ==ON ||
                !red && yellow == OFF && green == BLINKING);
    }
}
