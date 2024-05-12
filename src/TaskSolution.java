import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;

public class TaskSolution {
    public static void main(String[] args) {

        // Указываем пути к файлам
        String interestFilePath = "interest.txt";
        String influenceFilePath = "influence.txt";
        String resultFilePath = "result.txt";

        // Инициализируем списки для хранения ЗС, а также  интересов и влияния
        List<String> stakeholders = new ArrayList<>();
        List<List<Double>> interestMatrix = new ArrayList<>();
        List<List<Double>> influenceMatrix = new ArrayList<>();

        try {

            // Чтение файла interest.txt и заполнение матрицы интересов
            BufferedReader interestReader = new BufferedReader(new FileReader(interestFilePath));
            String line = interestReader.readLine();

            //Инициализация массива для хранения имен стейкхолдеров
            String[] stakeholderNames = line.split("\\|");
            for (int i = 0; i < stakeholderNames.length; i++) {
                stakeholders.add(stakeholderNames[i].trim());
            }
            while ((line = interestReader.readLine()) != null) {
                String[] values = line.split(" ");
                List<Double> str = new ArrayList<>();
                for (int i = 0; i < values.length; i++) {
                    str.add(parseValue(values[i]));
                }
                interestMatrix.add(str);
            }
            interestReader.close();

            // Чтение файла influence.txt и заполнение матрицы влияния
            BufferedReader influenceReader = new BufferedReader(new FileReader(influenceFilePath));
            influenceReader.readLine(); // Пропускаем первую строку с именами стейкхолдеров
            while ((line = influenceReader.readLine()) != null) {
                String[] values = line.split(" ");
                List<Double> str = new ArrayList<>();
                for (int i = 0; i < values.length; i++) {
                    str.add(parseValue(values[i]));
                }
                influenceMatrix.add(str);
            }
            influenceReader.close();

            // Определяем важных стейкхолдеров
            List<String> importantStakeholders = findImportantStakeholders(stakeholders, interestMatrix, influenceMatrix);

            // Запись важных стейкхолдеров в файл result.txt
            FileWriter writer = new FileWriter(resultFilePath);
            for (String importantStakeholder : importantStakeholders) {
                writer.write(importantStakeholder + "\n");
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Метод для конвертации значений из string в double
    private static double parseValue(String value) {
        if (value.equals("_")) {
            return 0.0;
        } else {
            return Double.parseDouble(value);
        }
    }

    // Метод для вычисления сумм значений в каждой строке матрицы
    private static double[] calculateSums(List<List<Double>> matrix) {
        double[] sums = new double[matrix.size()];
        for (int i = 0; i < matrix.size(); i++) {
            double sum = 0.0;
            for (double value : matrix.get(i)) {
                sum += value;
            }
            sums[i] = sum;
        }
        return sums;
    }

    // Метод для определения важных стейкхолдеров
    private static List<String> findImportantStakeholders(List<String> stakeholders, List<List<Double>> interestMatrix, List<List<Double>> influenceMatrix) {
        List<String> importantStakeholders = new ArrayList<>();

        // Вычисление сумм значений в каждой строке матрицы интересов и влияния
        double[] interestSums = calculateSums(interestMatrix);
        double[] influenceSums = calculateSums(influenceMatrix);

        // Нахождение максимальных сумм для поиска важных стейкхолдеров
        double maxInfluence = influenceSums[0];
        double maxInterest = interestSums[0];
        for (int i = 1; i < interestSums.length; i++) {
            maxInterest = max(maxInterest, interestSums[i]);
            maxInfluence = max(maxInfluence, influenceSums[i]);
        }

        for (int i = 0; i < stakeholders.size(); i++) {

                // Проверяем, находится ли стейкхолдер в правом верхнем квадрате обеих матриц
            if (interestSums[i] > maxInterest / 2 && influenceSums[i] > maxInfluence / 2) {
                importantStakeholders.add(stakeholders.get(i));
            }
        }
        return importantStakeholders;
    }

}
