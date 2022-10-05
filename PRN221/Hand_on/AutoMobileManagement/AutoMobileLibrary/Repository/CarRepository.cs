using AutoMobileLibrary.BussinessObject;
using AutoMobileLibrary.DataAccess;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AutoMobileLibrary.Repository
{
    public class CarRepository : ICarRepository
    {
        public void DeleteCar(int carID)
        {
            CarDbContext.Instant.Remove(carID);
        }

        public Car GetCarByID(int carID)
        {
           return CarDbContext.Instant.GetCarByID(carID);
        }

        public IEnumerable<Car> GetCars()
        {
            return CarDbContext.Instant.GetCarsList();
        }

        public void InsertCar(Car car)
        {
            CarDbContext.Instant.AddNew(car);
        }

        public void UpdateCar(Car car)
        {
            CarDbContext.Instant.Update(car);
        }
    }
}
