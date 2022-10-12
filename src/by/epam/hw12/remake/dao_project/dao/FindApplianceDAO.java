package by.epam.hw12.remake.dao_project.dao;

import java.util.List;

import by.epam.hw12.remake.dao_project.entity.ElectricalAppliance;
import by.epam.hw12.remake.dao_project.entity.property.Property;

public interface FindApplianceDAO {

	List<ElectricalAppliance> find(Property properties);

}
