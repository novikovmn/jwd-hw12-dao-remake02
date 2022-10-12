package by.epam.hw12.remake.dao_project.dao.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class ElectricalApplianceReader {

	// для поиска нужной категории товаров в файле
	private final String OVEN_CATEGORY_PREFIX = "Oven";
	private final String LAPTOP_CATEGORY_PREFIX = "Laptop";

	// путь к источнику данных
	private final File applianceDBTxt = new File("data" + File.separator + "appliance_db.txt");

	private static final ElectricalApplianceReader instance = new ElectricalApplianceReader();

	private ElectricalApplianceReader() {

	}

	public static ElectricalApplianceReader getInstance() {
		return instance;
	}

	// читаем строки, в зависимости от категории
	public List<String> take(String categoryName) {
		List<String> specificCategoriesLines = new ArrayList<>();

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(applianceDBTxt))) {

			while (bufferedReader.ready()) {
				String line = bufferedReader.readLine();
				if (line.startsWith(categoryName) && categoryName.equals(OVEN_CATEGORY_PREFIX)) {
					specificCategoriesLines.add(line);
				} else if (line.startsWith(categoryName) && categoryName.equals(LAPTOP_CATEGORY_PREFIX)) {
					specificCategoriesLines.add(line);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return specificCategoriesLines;
	}

	// читаем весь файл
	public List<String> take() {
		List<String> allLines = null;

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(applianceDBTxt))) {

			allLines = bufferedReader.lines().collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		return allLines;
	}

}
