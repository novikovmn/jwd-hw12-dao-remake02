package by.epam.hw12.remake.dao_project.main;

import by.epam.hw12.remake.dao_project.dao.impl.TxtFindApplianceDAOImpl;
import by.epam.hw12.remake.dao_project.entity.Laptop;
import by.epam.hw12.remake.dao_project.entity.property.Property;
import by.epam.hw12.remake.dao_project.entity.property.SearchProperty;
import by.epam.hw12.remake.dao_project.view.ConsoleOutput;

public class Main {

	public static void main(String[] args) {

		// создаем БД для техники (пишем в файл)
		ApplianceDBCreator.writeItemsToFile();

		// экземпляр DAO, имеющий доступ к БД (файлу)
		TxtFindApplianceDAOImpl finder = TxtFindApplianceDAOImpl.getInstance();

		// для печати результатов
		ConsoleOutput output = new ConsoleOutput();

		
		// поиск во всех категориях 
//		Property allCategoryProperties = new Property();
//		output.printAppliances(finder.find(allCategoryProperties));
		
		// поиск ноутов на линуксе
		Property laptopProperties = new Property(Laptop.class.getSimpleName());
		laptopProperties.addProperty(SearchProperty.Laptop.OS.name(), "Linux");
		output.printAppliances(finder.find(laptopProperties));
		
	
		
		


	}

}
