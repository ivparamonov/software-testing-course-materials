package ru.ac.uniyar.testingcourse.grades;

import java.util.List;
import java.util.Map;

/**
 * Интерфейс класса, предоставляющих доступ к данным об успеваемости студентов.
 */
public interface Dao {
    /**
     * Получение всех оценок заданного студента за указанный месяц.
     * @param studentName Имя студента.
     * @param month Номер месяца.
     * @return Список с оценками студента (пустой, если данных нет).
     */
    List<Integer> retrieveGradesForStudent(String studentName, int month);
    
    /**
     * Получение оценок всех студентов за указанный месяц.
     * @param month Номер месяца.
     * @return Ассоциативный массив, сопоставляющий каждому студенту (по имени)
     * список с его оценками за месяц.
     */
    Map<String, List<Integer>> retrieveAllGrades(int month);
    
    /**
     * Сохранение имени лучшего студента в БД.
     * @param studentName Имя студента.
     */
    void storeBestStudentName(String studentName);
    
    /**
     * Получение имени лучшего студента из БД.
     * @return Имя лучшего студента; null, если лучший студент не определён.
     */
    String retrieveBestStudentName();
}
