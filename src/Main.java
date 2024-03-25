git remote add origin https://github.com/yuuichi27/practiceAssignment.git
git branch -M main
git push -u origin main

import java.util.*;

public class Main {

    /*
    Задание: Реализовать систему ввода и отображения информации о студентах университета, включающую следующие сущности и их атрибуты:
    Студент (ФИО, Дата рождения, группа)
    Группа (Номер, название факультета)
    
    Система должна иметь функционал:
    - отображение списка групп
    - добавление новой группы, редактирование и удаление
    - отображение списка студентов
    - фильтрация списка студентов по Фамилии и по номеру группы
    - добавление нового студента, редактирование и удаление
    - система должна иметь защиту от удаления группы, в которой содержатся студенты
    */

    public static void main(String[] args) {
        Map<String, String> groups = new HashMap<>();
        Map<String, Integer> students = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        int condition = 0;

        System.out.println("Для вызова меню напишите /команды");

        if(scanner.nextLine().equals("/команды")){
            System.out.println("Чтобы создать группу введите «Создать группу»");
            System.out.println("Чтобы удалить группу введите «Удалить группу»");
            System.out.println("Чтобы редактировать группу введите «Редактировать группу»");
            System.out.println("Чтобы создать студента введите «Создать студента»");
            System.out.println("Чтобы удалить студента введите «Удалить студента»");
            System.out.println("Чтобы редактировать студента введите «Редактировать студента»");
            System.out.println("Чтобы сортировать студентов введите «Сортировать студентов по фамилии» или «Сортировать студентов по номеру группы»");
            System.out.println("Чтобы вывести список групп введите «Список групп»");
            System.out.println("Чтобы вывести список студентов введите «Список студентов»");
            System.out.println("Чтобы закончить работу введите «Закончить работу»");
        } else {
            System.out.println("Неправильно введена команда");
        }

        while(condition == 0) {
            String choice = scanner.nextLine();

            switch (choice) {
                case ("Создать группу"):
                    System.out.println("Для создания группы необходимо ввести номер и название факультета в следующем формате (без лишних пробелов): \nНомер группы («20») \nНазвание факультета («Физика»)");
                    Group group = new Group(scanner.nextLine(), scanner.nextLine());
                    groups.put(group.getNumber(), group.getFacultyName());
                    System.out.println(group + " создана");
                    break;

                case ("Удалить группу"):
                    System.out.println("Для удаления группы необходимо ввести её номер в следующем формате (без лишних пробелов): \nНомер группы («20»)");
                    String removedGroup = scanner.nextLine();
                    if(students.containsValue(removedGroup)){
                        System.out.println("Нельзя удалить группу, в которой находятся студенты");
                        continue;
                    }
                    groups.remove(removedGroup);
                    System.out.println("Группа " + removedGroup + " удалена");
                    break;

                case ("Редактировать группу"):
                    System.out.println("Редактировать можно только факультет группы, для редактирования самой группы, можно сначала удалить, а потом создать заново." +
                            "\nДля изменения факультета необходимо ввести номер группы и новое название факультета в следующем формате (без лишних пробелов): \nНомер группы («20») \nНазвание факультета («Физика»)");
                    String changeGroup = scanner.nextLine();
                    if(!groups.containsKey(changeGroup)){
                        System.out.println("Такой группы не существует");
                        continue;
                    }
                    groups.put(changeGroup, scanner.nextLine());
                    System.out.println("Факультет группы " + changeGroup + " изменён");
                    break;

                case ("Создать студента"):
                    System.out.println("Введите номер группы (без лишних пробелов), в которую вы хотите определить студента, например: Номер группы («20»)");
                    Integer group1 = Integer.valueOf(scanner.nextLine());
                    if(!groups.containsKey(String.valueOf(group1))){
                        System.out.println("Такой группы не существует, сначала создайте группу командой «Создать группу»");
                        continue;
                    }
                    System.out.println("Для создания студента необходимо ввести ФИО, дату рождения и номер группы в следующем формате (без лишних пробелов):" +
                            "\nФИО («Иванов Иван Иванович») \nДата рождения («01.01.2001») \nНомер группы определён из предыдущего сообщения");
                    Student student = new Student(scanner.nextLine(), scanner.nextLine(), group1);
                    students.put(student.getFullName() + " " + student.getBirthday(), student.getGroup());
                    System.out.println(student + " создан(а)");
                    break;

                case ("Удалить студента"):
                    System.out.println("Для удаления студента необходимо ввести ФИО и дату рождения в следующем формате (без лишних пробелов): " +
                            "\n «Иванов Иван Иванович 01.01.2001»");
                    String removedStudent = scanner.nextLine();
                    students.remove(removedStudent);
                    System.out.println("Студент(ка) " + removedStudent + " удален(а)");
                    break;

                case ("Редактировать студента"):
                    System.out.println("Редактировать можно только группу студента, для редактирования самого студента, можно сначала удалить, а потом создать заново." +
                            "\nДля изменения группы необходимо ввести ФИО, дату рождения и номер группы в следующем формате (без лишних пробелов): " +
                            "\n «Иванов Иван Иванович 01.01.2001» \nНомер группы («20»)");
                    String changeStudent = scanner.nextLine();
                    if(!students.containsKey(changeStudent)){
                        System.out.println("Такого студента не существует");
                        continue;
                    }
                    students.put(changeStudent, Integer.valueOf(scanner.nextLine()));
                    System.out.println("Студент(ка) " + changeStudent + " изменён(а)");
                    break;

                case ("Сортировать студентов по фамилии"):
                    Map<String, Integer> sortedMap = new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
                    sortedMap.putAll(students);

                    for (Map.Entry<String, Integer> pair : sortedMap.entrySet()) {
                        String key = pair.getKey();
                        Integer value = pair.getValue();
                        System.out.println(key + " из группы " + value);
                    }
                    break;

                case ("Сортировать студентов по номеру группы"):
                    List list = new ArrayList(students.entrySet());
                    Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                        @Override
                        public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                            return a.getValue() - b.getValue();
                        }
                    });

                    for(int i = 0; i < list.size(); i++){
                        System.out.println(list.get(i));
                    }
                    break;

                case ("Список групп"):
                    for (Map.Entry<String, String> pair : groups.entrySet()) {
                        String key = pair.getKey();
                        String value = pair.getValue();
                        System.out.println("Группа " + key + " по факультету " + value);
                    }
                    break;

                case ("Список студентов"):
                    for (Map.Entry<String, Integer> pair : students.entrySet()) {
                        String key = pair.getKey();
                        Integer value = pair.getValue();
                        System.out.println("Студент " + key + " из группы " + value);
                    }
                    break;

                case ("Закончить работу"):
                    condition = 1;
                    System.out.println("Работа закончена");
                    break;

                default:
                    System.out.println("Набрана неправильная команда");
                    break;
            }
        }
    }
}