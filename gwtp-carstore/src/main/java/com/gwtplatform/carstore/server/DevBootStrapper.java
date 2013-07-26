/**
 * Copyright 2013 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.gwtplatform.carstore.server;

import java.util.Date;

import javax.inject.Inject;

import com.gwtplatform.carstore.server.authentication.PasswordSecurity;
import com.gwtplatform.carstore.server.dao.CarDao;
import com.gwtplatform.carstore.server.dao.CarPropertiesDao;
import com.gwtplatform.carstore.server.dao.ManufacturerDao;
import com.gwtplatform.carstore.server.dao.RatingDao;
import com.gwtplatform.carstore.server.dao.UserDao;
import com.gwtplatform.carstore.server.dao.domain.Car;
import com.gwtplatform.carstore.server.dao.domain.Manufacturer;
import com.gwtplatform.carstore.server.dao.domain.Rating;
import com.gwtplatform.carstore.server.dao.domain.User;
import com.gwtplatform.carstore.shared.dto.CarDto;
import com.gwtplatform.carstore.shared.dto.CarPropertiesDto;
import com.gwtplatform.carstore.shared.dto.ManufacturerDto;
import com.gwtplatform.carstore.shared.dto.RatingDto;
import com.gwtplatform.carstore.shared.dto.UserDto;

public class DevBootStrapper {
    private final UserDao userDao;
    private final PasswordSecurity passwordSecurity;
    private final ManufacturerDao manufacturerDao;
    private final CarDao carDao;
    private final RatingDao ratingDao;
    private final CarPropertiesDao carPropertiesDao;

    @Inject
    DevBootStrapper(UserDao userDao,
                    PasswordSecurity passwordSecurity,
                    ManufacturerDao manufacturerDao,
                    CarDao carDao,
                    RatingDao ratingDao,
                    CarPropertiesDao carPropertiesDao) {
        this.userDao = userDao;
        this.passwordSecurity = passwordSecurity;
        this.manufacturerDao = manufacturerDao;
        this.carDao = carDao;
        this.ratingDao = ratingDao;
        this.carPropertiesDao = carPropertiesDao;

        init();
    }

    public void init() {
        deleteAllEntities();

        long userCount = userDao.countAll();

        if (userCount == 0) {
            createBasicUser();
        }

        createMockData();
    }

    private void deleteAllEntities() {
        manufacturerDao.deleteAll();
        carDao.deleteAll();
        ratingDao.deleteAll();
        carPropertiesDao.deleteAll();
    }

    private void createBasicUser() {
        UserDto userDto = new UserDto("admin", passwordSecurity.hashPassword("qwerty"), "FirstName", "LastName");
        userDao.put(User.create(userDto));
    }


    private void createMockData() {
        long manufacturerCount = manufacturerDao.countAll();

        if (manufacturerCount == 0) {
            ManufacturerDto honda = new ManufacturerDto("Honda");
            ManufacturerDto mitsubishi = new ManufacturerDto("Mitsubishi");

            honda = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(honda)));
            mitsubishi = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(mitsubishi)));

            ManufacturerDto honda2 = new ManufacturerDto("Honda");
            ManufacturerDto mitsubishi2 = new ManufacturerDto("Mitsubishi");

            honda2 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(honda2)));
            mitsubishi2 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(mitsubishi2)));

            ManufacturerDto honda3 = new ManufacturerDto("Honda");
            ManufacturerDto mitsubishi3 = new ManufacturerDto("Mitsubishi");

            honda3 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(honda3)));
            mitsubishi3 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(mitsubishi3)));


            ManufacturerDto honda4 = new ManufacturerDto("Honda");
            ManufacturerDto mitsubishi4 = new ManufacturerDto("Mitsubishi");

            honda4 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(honda4)));
            mitsubishi4 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(mitsubishi4)));


            ManufacturerDto honda5 = new ManufacturerDto("Honda");
            ManufacturerDto mitsubishi5 = new ManufacturerDto("Mitsubishi");

            honda5 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(honda5)));
            mitsubishi5 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(mitsubishi5)));


            ManufacturerDto honda6 = new ManufacturerDto("Honda");
            ManufacturerDto mitsubishi6 = new ManufacturerDto("Mitsubishi");

            honda6 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(honda6)));
            mitsubishi6 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(mitsubishi6)));


            ManufacturerDto honda7 = new ManufacturerDto("Honda");
            ManufacturerDto mitsubishi7 = new ManufacturerDto("Mitsubishi");

            honda7 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(honda7)));
            mitsubishi7 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(mitsubishi7)));


            ManufacturerDto honda21 = new ManufacturerDto("Honda");
            ManufacturerDto mitsubishi21 = new ManufacturerDto("Mitsubishi");

            honda21 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(honda21)));
            mitsubishi21 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(mitsubishi21)));


            ManufacturerDto honda9 = new ManufacturerDto("Honda");
            ManufacturerDto mitsubishi9 = new ManufacturerDto("Mitsubishi");

            honda9 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(honda9)));
            mitsubishi9 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(mitsubishi9)));


            ManufacturerDto honda10 = new ManufacturerDto("Honda");
            ManufacturerDto mitsubishi10 = new ManufacturerDto("Mitsubishi");

            honda10 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(honda10)));
            mitsubishi10 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(mitsubishi10)));


            ManufacturerDto honda11 = new ManufacturerDto("Honda");
            ManufacturerDto mitsubishi11 = new ManufacturerDto("Mitsubishi");

            honda11 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(honda11)));
            mitsubishi11 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(mitsubishi11)));


            ManufacturerDto honda12 = new ManufacturerDto("Honda");
            ManufacturerDto mitsubishi12 = new ManufacturerDto("Mitsubishi");

            honda12 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(honda12)));
            mitsubishi12 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(mitsubishi12)));


            ManufacturerDto honda13 = new ManufacturerDto("Honda");
            ManufacturerDto mitsubishi13 = new ManufacturerDto("Mitsubishi");

            honda13 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(honda13)));
            mitsubishi13 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(mitsubishi13)));


            ManufacturerDto honda14 = new ManufacturerDto("Honda");
            ManufacturerDto mitsubishi14 = new ManufacturerDto("Mitsubishi");

            honda14 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(honda14)));
            mitsubishi14 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(mitsubishi14)));


            ManufacturerDto honda15 = new ManufacturerDto("Honda");
            ManufacturerDto mitsubishi15 = new ManufacturerDto("Mitsubishi");

            honda15 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(honda15)));
            mitsubishi15 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(mitsubishi15)));


            ManufacturerDto honda16 = new ManufacturerDto("Honda");
            ManufacturerDto mitsubishi16 = new ManufacturerDto("Mitsubishi");

            honda16 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(honda16)));
            mitsubishi16 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(mitsubishi16)));


            ManufacturerDto honda17 = new ManufacturerDto("Honda");
            ManufacturerDto mitsubishi17 = new ManufacturerDto("Mitsubishi");

            honda17 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(honda17)));
            mitsubishi17 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(mitsubishi17)));


            ManufacturerDto honda18 = new ManufacturerDto("Honda");
            ManufacturerDto mitsubishi18 = new ManufacturerDto("Mitsubishi");

            honda18 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(honda18)));
            mitsubishi18 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(mitsubishi18)));


            ManufacturerDto honda19 = new ManufacturerDto("Honda");
            ManufacturerDto mitsubishi19 = new ManufacturerDto("Mitsubishi");

            honda19 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(honda19)));
            mitsubishi19 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(mitsubishi19)));


            ManufacturerDto honda20 = new ManufacturerDto("Honda");
            ManufacturerDto mitsubishi20 = new ManufacturerDto("Mitsubishi");

            honda20 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(honda20)));
            mitsubishi20 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(mitsubishi20)));


            honda21 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(honda21)));
            mitsubishi21 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(mitsubishi21)));


            ManufacturerDto honda22 = new ManufacturerDto("Honda");
            ManufacturerDto mitsubishi22 = new ManufacturerDto("Mitsubishi");

            honda22 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(honda22)));
            mitsubishi22 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(mitsubishi22)));


            ManufacturerDto honda23 = new ManufacturerDto("Honda");
            ManufacturerDto mitsubishi23 = new ManufacturerDto("Mitsubishi");

            honda23 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(honda23)));
            mitsubishi23 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(mitsubishi23)));


            ManufacturerDto honda24 = new ManufacturerDto("Honda");
            ManufacturerDto mitsubishi24 = new ManufacturerDto("Mitsubishi");

            honda24 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(honda24)));
            mitsubishi24 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(mitsubishi24)));


            ManufacturerDto honda25 = new ManufacturerDto("Honda");
            ManufacturerDto mitsubishi25 = new ManufacturerDto("Mitsubishi");

            honda25 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(honda25)));
            mitsubishi25 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(mitsubishi25)));


            ManufacturerDto honda26 = new ManufacturerDto("Honda");
            ManufacturerDto mitsubishi26 = new ManufacturerDto("Mitsubishi");

            honda26 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(honda26)));
            mitsubishi26 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(mitsubishi26)));


            ManufacturerDto honda27 = new ManufacturerDto("Honda");
            ManufacturerDto mitsubishi27 = new ManufacturerDto("Mitsubishi");

            honda27 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(honda27)));
            mitsubishi27 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(mitsubishi27)));


            ManufacturerDto honda28 = new ManufacturerDto("Honda");
            ManufacturerDto mitsubishi28 = new ManufacturerDto("Mitsubishi");

            honda28 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(honda28)));
            mitsubishi28 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(mitsubishi28)));


            ManufacturerDto honda29 = new ManufacturerDto("Honda");
            ManufacturerDto mitsubishi29 = new ManufacturerDto("Mitsubishi");

            honda29 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(honda29)));
            mitsubishi29 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(mitsubishi29)));


            ManufacturerDto honda30 = new ManufacturerDto("Honda");
            ManufacturerDto mitsubishi30 = new ManufacturerDto("Mitsubishi");

            honda30 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(honda30)));
            mitsubishi30 = Manufacturer.createDto(manufacturerDao.put(Manufacturer.create(mitsubishi30)));

            CarPropertiesDto carPropertiesCivic = new CarPropertiesDto("Cat", 0, new Date());
            carPropertiesCivic = carPropertiesDao.put(carPropertiesCivic);

            CarPropertiesDto carPropertiesAccord = new CarPropertiesDto("Fish", 1, new Date());
            carPropertiesAccord = carPropertiesDao.put(carPropertiesAccord);

            CarPropertiesDto carPropertiesLancer = new CarPropertiesDto("Dog", 2, new Date());
            carPropertiesLancer = carPropertiesDao.put(carPropertiesLancer);

            CarPropertiesDto carPropertiesMitsubishi = new CarPropertiesDto("Cow", 3, new Date());
            carPropertiesMitsubishi = carPropertiesDao.put(carPropertiesMitsubishi);

            CarDto civic = new CarDto("Civic", honda, carPropertiesCivic);
            CarDto accord = new CarDto("Accord", honda, carPropertiesAccord);
            CarDto lancer = new CarDto("Lancer", mitsubishi, carPropertiesLancer);
            CarDto galant = new CarDto("Galant", mitsubishi, carPropertiesMitsubishi);

            civic = Car.createDto(carDao.put(Car.create(civic)));
            accord = Car.createDto(carDao.put(Car.create(accord)));
            lancer = Car.createDto(carDao.put(Car.create(lancer)));
            galant = Car.createDto(carDao.put(Car.create(galant)));

            RatingDto rating1 = new RatingDto(accord, 4);
            RatingDto rating2 = new RatingDto(civic, 2);
            RatingDto rating3 = new RatingDto(galant, 3);
            RatingDto rating4 = new RatingDto(lancer, 4);

            ratingDao.put(Rating.create(rating1));
            ratingDao.put(Rating.create(rating2));
            ratingDao.put(Rating.create(rating3));
            ratingDao.put(Rating.create(rating4));
        }
    }
}

