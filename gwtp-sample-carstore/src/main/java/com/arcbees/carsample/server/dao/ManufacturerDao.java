package com.arcbees.carsample.server.dao;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;

import com.arcbees.carsample.shared.domain.Manufacturer;

public class ManufacturerDao extends BaseDao<Manufacturer> {
    @Inject
    public ManufacturerDao(Provider<EntityManager> entityManagerProvider) {
        super(Manufacturer.class, entityManagerProvider);
    }
}
