using AutoMobileLibrary.BussinessObject;
using System;
using System.Collections.Generic;


namespace AutoMobileLibrary.DataAccess
{
    /// <summary>
    /// Chứa thông tin các xe mẫu 
    /// </summary>
    internal class CarDbContext
    {
        //Initialize
        private static List<Car> CarLists = new List<Car>();
        public static void addDemoCar()
        {
            CarLists.Add(new Car(1, "Xe1", "HCM", 2000, 2021));
            CarLists.Add(new Car(2, "Xe2", "Ha Noi", 5000, 2020));
        }

        //Singletorn parrtern: chỉ có 1 instanse duy nhất, dấu contructor đi
        private CarDbContext() { }
        private static CarDbContext isntant = null;
        private static readonly object instantlock = new object();

        public static CarDbContext Instant
        {
            get
            {
                lock (instantlock)
                {
                    if (isntant == null)
                    {
                        isntant = new CarDbContext();
                        addDemoCar();
                    }
                    return isntant;
                }
            }
        }

        // lay ra list car
        public List<Car> GetCarsList()
        {
            return CarLists;
        }

        // Get car by id

        public Car GetCarByID(int carID)
        {
            Car car = null;
            foreach(Car o in CarLists)
            {
                if(o.ID == carID)
                {
                    car = o;
                }
                
            }
            return car;
        }
        //add new car
        public void AddNew(Car car)
        {
            Car check = GetCarByID(car.ID);
            if(check == null)
            {
                CarLists.Add(car);
            }
            else
            {
                throw new Exception("Car nay da co roi");
            }
        }

        // update car
        public void Update(Car car)
        {
            Car c = GetCarByID(car.ID);
            if(c != null)
            {
                var index = CarLists.IndexOf(c);
                CarLists[index] = car;
            }
            else
            {
                throw new Exception("Eo co cai xe nay");
            }
        }

        // remove
        public void Remove(int carID)
        {
            Car c = GetCarByID(carID);

            if (c != null)
            {
                CarLists.Remove(c);
            }
            else
            {
                throw new Exception("Eo co cai xe nay");
            }
        }


    }
}
