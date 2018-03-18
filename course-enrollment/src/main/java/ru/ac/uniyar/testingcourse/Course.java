package ru.ac.uniyar.testingcourse;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс предназначен для учёта студентов, записанных на учебный курс.
 * Для курса задаётся максимально допустимое количество студентов.
 * Студенты определяются своими идентификаторами (целые числа).
 * При попытке записать на курс большее количество студентов, чем возможно,
 * происходит добавление студентов в список ожидания. Если отменяется запись
 * студента из списка записавшихся, а список ожидания не пуст, то первый студент
 * из него перемещается в список записавшихся. Каждый студент может быть
 * записан не более одного раза.
 */
public class Course {

    private int maxStudents;
    private LinkedList<Integer> enrollList = new LinkedList<>();
    private LinkedList<Integer> waitingList = new LinkedList<>();
    
    /**
     * Конструктор.
     * @param maxStudents максимальное количество студентов.
     */
    public Course(int maxStudents) {
        this.maxStudents = maxStudents;
    }
    
    /**
     * Запись студента на курс. Если курс полностью заполнен,
     * студент помещается в список ожидания.
     * @param studentId идентификатор записываемого студента.
     */
    public void enroll(int studentId) {
        if (enrollList.contains(studentId) || waitingList.contains(studentId)) {
            return;
        }
        if (hasWaitingList()) {
            waitingList.add(studentId);
        } else {
            enrollList.add(studentId);
        }
    }
    
    /**
     * Удаление студента из числа записавшихся/помещённых в список ожидания.
     * @param studentId идентификатор удаляемого студента.
     */
    public void unenroll(int studentId) {
        if (waitingList.contains(studentId)) {
            waitingList.remove(studentId);
        }
        if (enrollList.contains(studentId)) {
            enrollList.remove(studentId);
            if (hasWaitingList()) {
                enrollList.add(waitingList.removeFirst());
            }
        }
    }
    
    /**
     * Проверка, что на курс записалось максимально возможное количество студентов.
     */
    public boolean isFullyEnrolled() {
        return enrollList.size() == maxStudents;
    }
    
    /**
     * Проверка, что список ожидаения для курса не пуст.
     */
    public boolean hasWaitingList() {
        return waitingList.size() > 0;
    }
    
    /**
     * Получение списка записавшихся студентов.
     */
    public List<Integer> getEnrollmentList() {
        return Collections.unmodifiableList(enrollList);
    }
    
    /**
     * Получение списка ожидания.
     */
    public List<Integer> getWaitingList() {
        return Collections.unmodifiableList(waitingList);
    }
}
