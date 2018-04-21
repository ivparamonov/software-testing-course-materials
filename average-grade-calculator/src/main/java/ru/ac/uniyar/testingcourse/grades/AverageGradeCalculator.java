package ru.ac.uniyar.testingcourse.grades;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Класс, ответственный за вычисление средних оценок по данным из базы данным.
 * Однажды полученные данные кэшируются и впоследствии получаются без обращения к базе данных.
 * Также класс позволяет находить лучшего студента и сохранять информацию о нём в базе данных.
 */
public class AverageGradeCalculator {
    private final Dao dao;
    private final Table<String, Integer, Double> cache = HashBasedTable.create();
    
    /**
     * Конструктор.
     * @param dao Объект доступа к данным
     */
    public AverageGradeCalculator(Dao dao) {
        this.dao = dao;
    }
    
    private Double calculateAverage(List<Integer> list) {
        return new Double(list.stream()
                .reduce(0, (acc, element) -> acc + element) 
                / list.size());
    }
    
    /**
     * Вычисление среднего балла студента за определённый месяц.
     * Вычисленный результат кэшируется, и при повторном запросе обращение
     * к базе данных не производится.
     * @param studentName Имя студента.
     * @param month Номер месяца.
     * @return Средний балл студента за указанный месяц; null, если оценок 
     * этого студента за указанный метод в базе данных нет.
     */
    public Double calculateAverageForStudent(String studentName, int month) {
        if (cache.contains(studentName, month)) {
            return cache.get(studentName, month);
        }
        List<Integer> grades = dao.retrieveGradesForStudent(studentName, month);
        if (grades.isEmpty()) return null;
        Double result = calculateAverage(grades);
        cache.put(studentName, month, result);
        return result;
    }
    
    /**
     * Очистка кэша с данными о средней успеваемости студентов.
     */
    public void invalidateCache() {
        cache.clear();
    }
    
    /**
     * Нахождение лучшего по итогам месяца студента и запись информации о нём в
     * базу данных.
     * Данный метод не читает и не записывает данные в кэш.
     * @param month Номер месяца.
     * @return Имя лучшего студента; 
     * имена студенов через запятую в произвольном порядке, если таких студентов
     * несколько (например, "Иванов, Петров");
     * null, если база данных пуста.
     */
    public String recalculateAndStoreBestStudent(int month) {
        Map<String, List<Integer>> grades = dao.retrieveAllGrades(month);
        Map<String, Double> averages = grades.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> calculateAverage(entry.getValue())));
        Double maxGrade = averages.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get().getValue();
        String result = averages.entrySet().stream()
                .filter(entry -> entry.getValue().equals(maxGrade))
                .map(Map.Entry::getKey).collect(Collectors.joining());
        return result;
    }
}
