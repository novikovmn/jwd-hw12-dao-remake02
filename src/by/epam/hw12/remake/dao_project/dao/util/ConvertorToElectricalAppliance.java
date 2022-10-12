package by.epam.hw12.remake.dao_project.dao.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epam.hw12.remake.dao_project.entity.ElectricalAppliance;
import by.epam.hw12.remake.dao_project.entity.Laptop;
import by.epam.hw12.remake.dao_project.entity.Oven;
import by.epam.hw12.remake.dao_project.entity.property.SearchProperty;

public class ConvertorToElectricalAppliance {

	// для поиска фрагментов вида "key=value"
	private final String KEY_VALUE_PATTERN = "\\w+=\\w+\\.\\w+|\\w+=\\w+";

	private final Pattern pattern = Pattern.compile(KEY_VALUE_PATTERN);

	// для поиска нужной категории товаров в файле
	private final String OVEN_CATEGORY_PREFIX = "Oven";
	private final String LAPTOP_CATEGORY_PREFIX = "Laptop";

	// количество агрументов в конструкторе Oven
	private final int OVEN_PROPERTY_LENGTH = SearchProperty.Oven.values().length;

	// количество агрументов в конструкторе Laptop
	private final int LAPTOP_PROPERTY_LENGTH = SearchProperty.Laptop.values().length;

	private List<String> matchedAppliancesLines;

	public ConvertorToElectricalAppliance(List<String> matchedAppliancesLines) {
		this.matchedAppliancesLines = matchedAppliancesLines;
	}

	public List<ElectricalAppliance> convert() {
		List<ElectricalAppliance> appliances = new ArrayList<>();

		for (String line : matchedAppliancesLines) {

			int index = 0;
			Matcher matcher = pattern.matcher(line);

			if (line.startsWith(OVEN_CATEGORY_PREFIX)) {
				Oven oven;
				String[] ovenProperties = new String[OVEN_PROPERTY_LENGTH];

				while (matcher.find()) {
					ovenProperties[index++] = matcher.group().split("=")[1];
				}

				oven = new Oven(ovenProperties[0], Double.parseDouble(ovenProperties[1]),
						Integer.parseInt(ovenProperties[2]));
				appliances.add(oven);
			} else if (line.startsWith(LAPTOP_CATEGORY_PREFIX)) {
				Laptop laptop;
				String[] laptopProperties = new String[LAPTOP_PROPERTY_LENGTH];

				while (matcher.find()) {
					laptopProperties[index++] = matcher.group().split("=")[1];
				}

				laptop = new Laptop(laptopProperties[0], Double.parseDouble(laptopProperties[1]), laptopProperties[2],
						Integer.parseInt(laptopProperties[3]), Boolean.parseBoolean(laptopProperties[4]));

				appliances.add(laptop);
			}
		}

		return appliances;
	}

}
