using AutoMobileLibrary.BussinessObject;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AutoMobileLibrary.Repository
{
    public interface ICarRepository
    {
         Car GetCarByID(int carID);
         IEnumerable<Car> GetCars();
         void InsertCar(Car car);
         void UpdateCar(Car car);
         void DeleteCar(int carID);
    }
}
