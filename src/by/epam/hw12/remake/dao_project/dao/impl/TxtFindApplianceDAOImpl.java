package by.epam.hw12.remake.dao_project.dao.impl;

import java.util.List;

import by.epam.hw12.remake.dao_project.dao.FindApplianceDAO;
import by.epam.hw12.remake.dao_project.dao.util.ConvertorToElectricalAppliance;
import by.epam.hw12.remake.dao_project.dao.util.ElectricalApplianceDataMatcher;
import by.epam.hw12.remake.dao_project.dao.util.ElectricalApplianceReader;
import by.epam.hw12.remake.dao_project.entity.ElectricalAppliance;

import by.epam.hw12.remake.dao_project.entity.property.Property;

public final class TxtFindApplianceDAOImpl implements FindApplianceDAO {

	private static final TxtFindApplianceDAOImpl instance = new TxtFindApplianceDAOImpl();

	private TxtFindApplianceDAOImpl() {
	}

	public static TxtFindApplianceDAOImpl getInstance() {
		return instance;
	}

	@Override
	public List<ElectricalAppliance> find(Property properties) {

		List<String> linesFromDB = null;

		ElectricalApplianceReader electricalApplianceReader = ElectricalApplianceReader.getInstance();

		if (properties.getCategoryName() != null) {
			linesFromDB = electricalApplianceReader.take(properties.getCategoryName());
		} else {
			linesFromDB = electricalApplianceReader.take();
		}

		ElectricalApplianceDataMatcher electricalApplianceDataMatcher = new ElectricalApplianceDataMatcher(linesFromDB,
				properties);

		List<String> matchedAppliancesLines = electricalApplianceDataMatcher.match();

		ConvertorToElectricalAppliance covertor = new ConvertorToElectricalAppliance(matchedAppliancesLines);

		List<ElectricalAppliance> electricalAppliances = covertor.convert();

		return electricalAppliances;
	}

}
