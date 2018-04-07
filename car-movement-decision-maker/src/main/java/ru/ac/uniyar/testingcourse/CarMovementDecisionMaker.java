package ru.ac.uniyar.testingcourse;

/**
 * Класс предназначен для определения действия водителя автомобиля 
 * на светофоре.
 */
public class CarMovementDecisionMaker { 
    
    private boolean red = false;
    private Ternar yellow = Ternar.OFF;
    private boolean green = false;

    /** Установка состояния сигналов светофора. */
    public void setTrafficLightState(boolean red, Ternar yellow, boolean green) {
        this.red = red;
        this.yellow = yellow;
        this.green = green;
    }

    /** Метод определяет, может ли водитель ехать. */
    public boolean isToGo() {
        return !(red && (yellow == Ternar.OFF || yellow == Ternar.ON) && !green);
    }
    
    /**
     * Метод определяет, должен ли водитель приготовиться к тому, что
     * возможность движения в ближайшее время изменится (движение будет 
     * запрещено или наоборот разрешено).
     */
    public boolean isToBePrepared() {
        return yellow == Ternar.ON && !green;
    }
    
    /** 
     * Метод определяет, должен ли водитель уделять повышенное внимание в силу
     * того, что данный перекрёсток является нерегулируемым (движение через
     * нерегулируемый перекрёсток всегда разрешено).
     */
    public boolean isToBeCautious() {
        return !(
                red && yellow == Ternar.OFF && !green ||
                yellow == Ternar.ON && !green ||
                !red && yellow == Ternar.OFF && green);
    }
}
