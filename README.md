# Тестовое задание для отбора на Летнюю ИТ-школу КРОК по разработке

## Условие задания
В теории управления проектами существует такое понятие как стейкхолдер или, иначе говоря, заинтересованная сторона (ЗС). Это лицо, которое может иметь влияние на ход проекта и с чьим мнением нужно считаться по совершенно разным причинам.

Тем не менее, очевидно, что каждый из стейкхолдеров по-своему важен для проекта, что является следствием разного уровня показателей интереса и влияния, потому реакция на обратную связь от каждого из стейкхолдеров определяется в соответствии с матрицей заинтересованных лиц, которая позволяет наглядно определить наиболее и наименее весомых из них.

Чаще всего такая матрица представляет собой график с осями X и Y, где по оси X располагается величина влияния, а по оси Y - величина интереса, и так как значения величин - сугубо неотрицательные числа, работа идет в правом верхнем квадранте. В свою очередь такой срез оси координат делится на 4 части в центрах осей.

Рассмотрим на примере. Допустим, у нас есть 5 ЗС. Первым делом построим ось координат XY и нанесем деления по обеим осям от 0 до 5. Далее поделим пополам каждую из осей (это будут точки (0; 2.5) и (2.5; 0)) и построим пунктирную линию для разделения матрицы на 4 квадранта. Таким образом, мы получим будущую матрицу стейкхолдеров, на которой сможем визуально расположить их по степени влияния и интереса.
![Матрица стейкхолдеров](https://github.com/croc-code/school2024-test-task5/blob/master/stakeholders_matrix.png)

Для того, чтобы определить положение стейкхолдера, используется метод попарного сравнения, схожий с рейтинговыми таблицами в спорте. Строятся матрицы (для интереса и влияния отдельно), в строках и столбцах которых располагаются стейкхолдеры в одинаковом порядке следования, а на пересечении строк и столбцов выставляются значения 0, 0.5 и 1 по следующим правилам:
- 1, если стейкхолдер в текущей строке имеет большее влияние/интерес, чем стейкхолдер в текущем столбце;
- 0.5, если стейкхолдеры в текущих строке и столбце имеют одинаковое влияние/интерес;
- 0, если стейкхолдер в текущей строке имеет меньшее влияние/интерес, чем стейкхолдер в текущем столбце.

По диагоналям значения не выставляются, так как сравнивать стейкхолдера с самим собой некорректно.

Далее полученные величины в строках складываются и получается итоговый ранг заинтересованной стороны. То же самое, но наглядно:
![alt text](https://github.com/croc-code/school2024-test-task5/blob/master/pair_compair.png)

Построив такие матрицы для интереса и влияния, мы получаем “координаты” каждого из стейкхолдеров в матрице и можем расположить в соответствующих квадратах.
Самыми важными для проекта ЗС будут те, что располагаются в правом верхнем квадранте, а те, чье мнение не обязательно учитывать здесь и сейчас, - в левом нижнем.

Вам необходимо реализовать программу, которая по полученным данным матриц влияния и интереса будет определять самых важных стейкхолдеров. В качестве входных данных используются два текстовых файла:
- interest.txt (матрица попарного сравнения стейкхолдеров по уровню интереса)
- influence.txt (матрица попарного сравнения стейкхолдеров по уровню влияния)

Каждый из файлов имеет следующий формат:
- в первой строке файла располагаются наименования заинтересованных сторон, разделенные символов “|”, соответствующие наименованию строк и столбцов матрицы в одинаковом порядке следования;
- последующие строки представляют собой матрицу попарного сравнения со значениями 0, 0.5, 1 в соответствии с правилами формирования;
- на главной диагонали (сравнение ЗС самой с собой) вместо значения идет символ “_”.

Пример содержимого одного из входных файлов:
```
Stakeholder 1 | Stakeholder 2 | Stakeholder 3 | Stakeholder 4 | Stakeholder 5
_ 0 1 0 0
1 _ 0.5 1 1
0 0.5 _ 0 0.5
1 0 1 _ 1
1 0 0.5 0 _
```

В результате работы программа должна сформировать файл (result.txt), в котором будут располагаться самые важные стейкхолдеры (в произвольном порядке). Пример содержимого выходного файла:
```
Stakeholder 1
Stakeholder 3
```

## Автор решения

## Описание реализации
  Решение задачи выполнено на языке Java. В программе происходит чтение данных из файлов interest.txt, influence.txt, в которых уже прописаны матрицы интересов и
влияния, определение важных стейкхолдеров, опираясь на их влияния и интересы, и, наконец, запись искомых стейкхолдеров в файл с результатами result.txt(изначально 
файл пустой).Все файлы расположены в корне проекта.Ниже предоставлена подробная реализация программы:

  В начале указываются пути к файлам interest.txt, influence.txt и result.txt для чтения интересов, влияния и записи результатов соответственно. Инициализируются 
списки для хранения Заинтересованных Сторон(стейĸхолдеров), а также интересов и влияния.

  Далее происходит чтение файла interest.txt(данные считываются построчно): сначала считаваются имена стейкхолдеров в первой строке, разбиваются символом "|" и 
добавляются в список stakeholders; затем строки(начиная со 2й) разбиваются по пробелу на значения интересов и сконвертированными добавляются в список 
interestMatrix(для конвертации используется метод parseValue, который преобразует все значения из строкового типа в double; при этом символ "_" меняется на 0 для
упрощения нахождения суммы в последующем). Аналогично происходит чтение файла influence.txt и заполнение матрицы влияния(только здесь мы первую строку пропускаем,
поскольку имена стейкхолдеров уже считаны в предыдущем шаге.

  Следующим шагом определяются важные стейкхолдеры: применяется метод findImportantStakeholders с именами стейкхолдеров и матрицами интересов и влияния в качестве 
параметров. Метод findImportantStakeholders работает следующим образом: принимаются списки с имена стейкхолдеров, матрицы интересов и влияния; создается список для
хранения важных стейкхолдеров; вычисляются суммы значений в каждой строке матриц интересов и влияния и помещаются в массивы interestSums и influenceSums 
соответственно(для подсчёта сумм используется отдельный метод для вычисления сумм значений в каждой строке матрицы). Далее находятся максимальные суммы интересов и
влияния для определения самых важных стейкхолдеров(поскольку нам нужны стейкхолдеры в правом верхнем квадрате графика по условию, то мы делим максимальные значения 
по осям влияния и интереса на 2 - находим середину графика - и добавляем в список importantStakeholders те имена стейкхолдеров, чьи значения сумм по оси интересов 
и влияния(координаты) ОДНОВРЕМЕННО больше, чем серединные значения, то есть расположены в правом верхнем квадрате графика), метод возвращает список с именами важных
стейкхолдеров. 

  И наконец, все элементы списка importantStakeholders(важные стейкхолдеры) записываются в файл result.txt.

## Инструкция по сборке и запуску решения
1)Скачать репозиторий и открыть проект в среде разработке(например, intelliJ idea)
2)Убедиться в корректном расположении файлов interest.txt, influence.txt и result.txt и ,по своему усмотрению, заполнить первые 2 файла другими значениями
3)Запустить файл TaskSolution.java
4)Список важных стейкхолдеров будет представлен в файле result.txt
